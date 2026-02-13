import com.mdb.Mdb;
import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import xbean.logic.Bag;
import xbean.logic.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 目标：
 * 1. 用 Bag 做高覆盖 checkpoint 压测（含并发、checkpoint 混跑）。
 * 2. 结束时用 close 触发 strictCheck，做精细检测。
 * 3. 重启后按 key 精确比对，确认无脏写/漏写/覆盖问题。
 */
public class TestBagCheckpointCloseDeep {
    private static final String INPUT_XML = "mdb\\src\\main\\mdb.xml";
    private static final int KEY_COUNT = 64;
    private static final int WORKER_COUNT = 6;
    private static final int OPS_PER_WORKER = 1200;
    private static final int MAX_MUTATE_PER_OP = 6;
    private static final long BASE_SEED = 20260213L;

    public static void main(String[] args) throws Exception {
        Mdb.configLog();

        long runId = System.currentTimeMillis();
        List<Long> keys = buildKeys(runId, KEY_COUNT);
        Object[] keyLocks = buildKeyLocks(KEY_COUNT);
        Map<Long, Bag> expected = new ConcurrentHashMap<>();

        System.out.println("=== TestBagCheckpointCloseDeep start runId=" + runId + " keyCount=" + KEY_COUNT + " ===");

        // phase1: 并发随机操作 + checkpoint 混跑，最后 close 触发 strictCheck
        startMdb();
        try {
            initializeAllKeys(keys, keyLocks, expected);
            runConcurrentStress(keys, keyLocks, expected);
            verifyCurrentMemory("phase1-before-close", keys, expected);
        } finally {
            safeClose();
        }

        // 重启验证 phase1 的落库结果
        startMdb();
        try {
            verifyPersisted("phase1-after-reopen", keys, expected);
            // phase2: 只做数据修改，不手动 flush/checkpoint，直接 close 测 close 路径
            runCloseOnlyMutationPhase(keys, keyLocks, expected);
        } finally {
            safeClose();
        }

        // 再次重启验证 phase2（close-only）落库结果
        startMdb();
        try {
            verifyPersisted("phase2-close-only-after-reopen", keys, expected);
        } finally {
            safeClose();
        }

        System.out.println("=== TestBagCheckpointCloseDeep finished runId=" + runId + " ===");
    }

    private static void runConcurrentStress(List<Long> keys, Object[] keyLocks, Map<Long, Bag> expected) throws Exception {
        AtomicBoolean stopCheckpoint = new AtomicBoolean(false);
        AtomicReference<Throwable> asyncError = new AtomicReference<>(null);

        Thread checkpointThread = new Thread(() -> {
            Random r = new Random(BASE_SEED ^ 0x5A5A5A5AL);
            while (!stopCheckpoint.get()) {
                try {
                    Mdb.getInstance().checkpoint.checkpoint(System.currentTimeMillis(), false);
                    try {
                        Thread.sleep(60 + r.nextInt(90));
                    } catch (InterruptedException ignore) {
                        Thread.currentThread().interrupt();
                    }
                } catch (Throwable t) {
                    asyncError.compareAndSet(null, t);
                    stopCheckpoint.set(true);
                }
            }
        }, "bag-checkpoint-loop");

        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < WORKER_COUNT; i++) {
            final int workerIndex = i;
            Thread worker = new Thread(() -> runWorker(workerIndex, keys, keyLocks, expected, asyncError),
                    "bag-worker-" + workerIndex);
            workers.add(worker);
        }

        checkpointThread.start();
        workers.forEach(Thread::start);

        for (Thread worker : workers) {
            worker.join();
        }
        stopCheckpoint.set(true);
        checkpointThread.join();

        if (asyncError.get() != null) {
            throw new RuntimeException("async stress thread failed", asyncError.get());
        }

        // 收尾：显式跑几次 checkpoint，覆盖连续 checkpoint 场景
        Mdb.getInstance().checkpoint.checkpoint(System.currentTimeMillis(), false);
        Mdb.getInstance().checkpoint.checkpoint(System.currentTimeMillis(), false);
    }

    private static void runWorker(int workerIndex, List<Long> keys, Object[] keyLocks, Map<Long, Bag> expected,
                                  AtomicReference<Throwable> asyncError) {
        Random r = new Random(BASE_SEED + workerIndex * 1000003L);
        try {
            for (int i = 0; i < OPS_PER_WORKER; i++) {
                int idx = r.nextInt(keys.size());
                long key = keys.get(idx);
                Object keyLock = keyLocks[idx];
                synchronized (keyLock) {
                    randomOperateSingleKey(key, r, expected);
                }

            }
        } catch (Throwable t) {
            asyncError.compareAndSet(null, t);
        }
    }

    private static void randomOperateSingleKey(long key, Random r, Map<Long, Bag> expected) {
        int op = r.nextInt(100);

        if (op < 10) {
            table.logic.Bag.remove(key);
            expected.remove(key);
            return;
        }

        Bag bag = table.logic.Bag.get(key);

        if (bag == null || op < 22) {
            Bag newBag = newRandomBag(key, r);
            table.logic.Bag.add(key, newBag);
            Bag current = table.logic.Bag.get(key);
            if (current == null) {
                throw new IllegalStateException("bag add then get null key=" + key);
            }
            expected.put(key, new Bag(current));
            return;
        }

        int mutateTimes = 1 + r.nextInt(MAX_MUTATE_PER_OP);
        for (int i = 0; i < mutateTimes; i++) {
            mutateBagRandom(key, bag, r);
        }
        expected.put(key, new Bag(bag));
    }

    private static void runCloseOnlyMutationPhase(List<Long> keys, Object[] keyLocks, Map<Long, Bag> expected) {
        Random r = new Random(BASE_SEED ^ 0x13579BDFL);
        for (int i = 0; i < keys.size(); i++) {
            long key = keys.get(i);
            synchronized (keyLocks[i]) {
                Bag bag = table.logic.Bag.get(key);
                if (bag == null && r.nextInt(100) < 65) {
                    Bag newBag = newRandomBag(key, r);
                    table.logic.Bag.add(key, newBag);
                    bag = table.logic.Bag.get(key);
                }

                if (bag == null) {
                    expected.remove(key);
                    continue;
                }

                if (r.nextInt(100) < 20) {
                    table.logic.Bag.remove(key);
                    expected.remove(key);
                    continue;
                }

                int mutateTimes = 3 + r.nextInt(7);
                for (int m = 0; m < mutateTimes; m++) {
                    mutateBagRandom(key, bag, r);
                }
                expected.put(key, new Bag(bag));
            }
        }
        verifyCurrentMemory("phase2-before-close", keys, expected);
    }

    private static void initializeAllKeys(List<Long> keys, Object[] keyLocks, Map<Long, Bag> expected) {
        Random r = new Random(BASE_SEED ^ 0x2468ACE0L);
        for (int i = 0; i < keys.size(); i++) {
            long key = keys.get(i);
            synchronized (keyLocks[i]) {
                Bag bag = newRandomBag(key, r);
                table.logic.Bag.add(key, bag);
                Bag current = table.logic.Bag.get(key);
                if (current == null) {
                    throw new IllegalStateException("initialize add fail key=" + key);
                }
                expected.put(key, new Bag(current));
            }
        }
    }

    private static void verifyCurrentMemory(String stage, List<Long> keys, Map<Long, Bag> expected) {
        verify(stage, keys, expected);
    }

    private static void verifyPersisted(String stage, List<Long> keys, Map<Long, Bag> expected) {
        verify(stage, keys, expected);
    }

    private static void verify(String stage, List<Long> keys, Map<Long, Bag> expected) {
        long present = 0;
        long absent = 0;
        long fingerprint = 0;

        for (long key : keys) {
            Bag expect = expected.get(key);
            Bag actual = table.logic.Bag.get(key);

            if (expect == null) {
                if (actual != null) {
                    throw new IllegalStateException(stage + " mismatch key=" + key + " expected=null actual=" + bagSummary(actual));
                }
                absent++;
                continue;
            }

            if (actual == null) {
                throw new IllegalStateException(stage + " mismatch key=" + key + " expected=" + bagSummary(expect) + " actual=null");
            }

            if (!expect.equals(actual)) {
                throw new IllegalStateException(stage + " mismatch key=" + key
                        + " expected=" + bagSummary(expect)
                        + " actual=" + bagSummary(actual));
            }

            present++;
            fingerprint += expect.hashCode();
        }

        System.out.println(stage + " verify ok present=" + present + " absent=" + absent + " fingerprint=" + fingerprint);
    }

    private static String bagSummary(Bag bag) {
        return "bagtest=" + bag.getBagtest()
                + ",player=(" + bag.getPlayer().getPlayerId() + "," + bag.getPlayer().getLevel() + "," + bag.getPlayer().getPlayerName() + ")"
                + ",playerList=" + bag.getPlayerList().size()
                + ",playerSet=" + bag.getPlayerSet().size()
                + ",playerMap=" + bag.getPlayerMap().size()
                + ",longList=" + bag.getLongList().size()
                + ",longSet=" + bag.getLongSet().size()
                + ",longMap=" + bag.getLongMap().size();
    }

    private static void mutateBagRandom(long key, Bag bag, Random r) {
        switch (r.nextInt(22)) {
            case 0:
                bag.setBagtest(r.nextInt(1_000_000));
                break;
            case 1:
                bag.setPlayerId(key);
                break;
            case 2:
                bag.setPlayer(newRandomPlayer(key, r));
                break;
            case 3:
                bag.getPlayer().setLevel(r.nextInt(1_000_000));
                break;
            case 4:
                bag.getPlayer().setPlayerName("n_" + key + "_" + r.nextInt(1_000_000));
                break;
            case 5:
                bag.getPlayerList().add(newRandomPlayer(key, r));
                break;
            case 6:
                if (!bag.getPlayerList().isEmpty()) {
                    bag.getPlayerList().remove(r.nextInt(bag.getPlayerList().size()));
                }
                break;
            case 7:
                if (!bag.getPlayerList().isEmpty()) {
                    int idx = r.nextInt(bag.getPlayerList().size());
                    bag.getPlayerList().set(idx, newRandomPlayer(key, r));
                }
                break;
            case 8:
                if (bag.getPlayerList().size() > 30) {
                    bag.getPlayerList().clear();
                }
                break;
            case 9:
                bag.getPlayerSet().add(newRandomPlayer(key, r));
                break;
            case 10:
                if (!bag.getPlayerSet().isEmpty()) {
                    Iterator<Player> it = bag.getPlayerSet().iterator();
                    if (it.hasNext()) {
                        bag.getPlayerSet().remove(it.next());
                    }
                }
                break;
            case 11: {
                long mk = 1 + r.nextInt(500);
                bag.getPlayerMap().put(mk, newRandomPlayer(key, r));
                break;
            }
            case 12:
                if (!bag.getPlayerMap().isEmpty()) {
                    Long removeKey = bag.getPlayerMap().keySet().iterator().next();
                    bag.getPlayerMap().remove(removeKey);
                }
                break;
            case 13:
                bag.getLongList().add(1L + r.nextInt(1_000_000));
                break;
            case 14:
                if (!bag.getLongList().isEmpty()) {
                    bag.getLongList().remove(r.nextInt(bag.getLongList().size()));
                }
                break;
            case 15:
                if (bag.getLongList().size() > 30) {
                    bag.getLongList().clear();
                }
                break;
            case 16:
                bag.getLongSet().add(1L + r.nextInt(1_000_000));
                break;
            case 17:
                if (!bag.getLongSet().isEmpty()) {
                    Iterator<Long> it = bag.getLongSet().iterator();
                    if (it.hasNext()) {
                        bag.getLongSet().remove(it.next());
                    }
                }
                break;
            case 18:
            case 19: {
                long mapKey = 1L + r.nextInt(1_000_000);
                bag.getLongMap().put(mapKey, 1L + r.nextInt(1_000_000));
                break;
            }
            case 20:
                if (!bag.getLongMap().isEmpty()) {
                    Long removeKey = bag.getLongMap().keySet().iterator().next();
                    bag.getLongMap().remove(removeKey);
                }
                break;
            default:
                if (bag.getLongMap().size() > 50) {
                    bag.getLongMap().clear();
                }
                break;
        }
    }

    private static Bag newRandomBag(long key, Random r) {
        Bag bag = new Bag();
        bag.setPlayerId(key);
        bag.setBagtest(r.nextInt(1_000_000));
        bag.setPlayer(newRandomPlayer(key, r));

        int listCount = r.nextInt(4);
        for (int i = 0; i < listCount; i++) {
            bag.getPlayerList().add(newRandomPlayer(key, r));
        }

        int setCount = r.nextInt(4);
        for (int i = 0; i < setCount; i++) {
            bag.getPlayerSet().add(newRandomPlayer(key, r));
        }

        int mapCount = r.nextInt(4);
        for (int i = 0; i < mapCount; i++) {
            bag.getPlayerMap().put((long) (1 + r.nextInt(300)), newRandomPlayer(key, r));
        }

        int longListCount = r.nextInt(4);
        for (int i = 0; i < longListCount; i++) {
            bag.getLongList().add(1L + r.nextInt(1_000_000));
        }

        int longSetCount = r.nextInt(4);
        for (int i = 0; i < longSetCount; i++) {
            bag.getLongSet().add(1L + r.nextInt(1_000_000));
        }

        int longMapCount = r.nextInt(4);
        for (int i = 0; i < longMapCount; i++) {
            bag.getLongMap().put(1L + r.nextInt(1_000_000), 1L + r.nextInt(1_000_000));
        }

        return bag;
    }

    private static Player newRandomPlayer(long key, Random r) {
        Player p = new Player();
        p.setPlayerId(key);
        p.setLevel(r.nextInt(1_000_000));
        p.setPlayerName("p_" + key + "_" + r.nextInt(1_000_000));
        return p;
    }

    private static Object[] buildKeyLocks(int size) {
        Object[] locks = new Object[size];
        for (int i = 0; i < size; i++) {
            locks[i] = new Object();
        }
        return locks;
    }

    private static List<Long> buildKeys(long runId, int count) {
        long base = runId * 1000L;
        List<Long> keys = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            keys.add(base + i);
        }
        return keys;
    }

    private static void startMdb() throws Exception {
        MdbMetadata mdb = new MdbMetadata();
        mdb.initialize(XMLUtils.getRootElement(INPUT_XML));
        Mdb.getInstance().start(mdb);
    }

    private static void safeClose() {
        try {
            Mdb.getInstance().close();
        } catch (Throwable t) {
            throw new RuntimeException("close failed", t);
        }
    }
}
