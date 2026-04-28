package com.mdb;

import com.mdb.entity.MdbPlayerInfo;
import com.mdb.util.ConcurrentEnvironment;
import com.mdb.util.FastJson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xbean.logic.Player;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.Assert.*;

public class MdbAsyncFlushTest {
    private Mdb mdb;
    private FakeStorageEngine storage;
    private PlayerTable table;

    @Before
    public void setUp() {
        resetMdb();
        mdb = new Mdb();
        mdb.setPlayerFlushDelayMill(0L);
        mdb.accessDBScheduler = ConcurrentEnvironment.getInstance().newScheduledThreadPool("mdb.scheduler", 1);
        initPlayerExecutorsForTest();
        storage = new FakeStorageEngine();

        Tables tables = new Tables();
        tables.mdb = mdb;
        tables.storage = storage;

        table = new PlayerTable();
        tables.add(table);
        table.setTables(tables);
        storage.initTable(table.getName());

        mdb.getAllTables().add(tables);
    }

    @After
    public void tearDown() {
        resetMdb();
    }

    @Test
    public void firstLoadShouldCreatePlayerInfoAndFinishLoad() {
        long key = 2000L;
        storage.replace("player", FastJson.toJSONString(key), FastJson.toJSONString(player(key, 10, "load")));

        AtomicReference<MdbPlayerInfo> playerInfoRef = new AtomicReference<>();
        AtomicBoolean success = new AtomicBoolean(false);
        AtomicReference<Exception> error = new AtomicReference<>();

        mdb.loadPlayerAllTableToMemory(key, true, (playerInfo, allSuccess, exception) -> {
            playerInfoRef.set(playerInfo);
            success.set(allSuccess);
            error.set(exception);
        });

        waitUntil(() -> playerInfoRef.get() != null);
        assertEquals(Long.valueOf(key), playerInfoRef.get().getPlayerId());
        assertEquals(MdbPlayerInfo.load_finish, playerInfoRef.get().getState());
        assertTrue(success.get());
        assertNull(error.get());
        assertEquals(1, table.getCacheList().size());
    }

    @Test
    public void flushShouldPersistLoadedPlayerOnTick() {
        long key = 2001L;
        storage.replace("player", FastJson.toJSONString(key), FastJson.toJSONString(player(key, 10, "load")));
        loadPlayer(key);
        Player loaded = table.get(key);
        loaded.setLevel(11);
        loaded.setPlayerName("async");

        AtomicBoolean callbackSuccess = new AtomicBoolean(false);
        AtomicReference<Exception> callbackError = new AtomicReference<>();
        AtomicInteger callbackCount = new AtomicInteger();

        mdb.flush(key, (playerInfo, allSuccess, exception) -> {
            assertEquals(Long.valueOf(key), playerInfo.getPlayerId());
            callbackCount.incrementAndGet();
            callbackSuccess.set(allSuccess);
            callbackError.set(exception);
        }, System.currentTimeMillis());

        tickUntil(() -> callbackCount.get() == 1);

        assertNotNull(storage.getStored("player", "2001"));
        assertEquals(0, table.getCacheList().size());
        assertEquals(1, callbackCount.get());
        assertTrue(callbackSuccess.get());
        assertNull(callbackError.get());
    }

    @Test
    public void loadAgainDuringFlushWaitShouldCancelFlush() {
        long key = 2002L;
        String originalJson = FastJson.toJSONString(player(key, 10, "load"));
        storage.replace("player", FastJson.toJSONString(key), originalJson);
        loadPlayer(key);
        Player loaded = table.get(key);
        loaded.setLevel(12);
        loaded.setPlayerName("cancel");
        mdb.setPlayerFlushDelayMill(60_000L);

        AtomicInteger flushCallbackCount = new AtomicInteger();
        AtomicInteger loadCallbackCount = new AtomicInteger();

        mdb.flush(key, (playerInfo, allSuccess, exception) -> flushCallbackCount.incrementAndGet(),
                System.currentTimeMillis());
        mdb.loadPlayerAllTableToMemory(key, true, (playerInfo, allSuccess, exception) -> {
            assertEquals(Long.valueOf(key), playerInfo.getPlayerId());
            assertTrue(allSuccess);
            assertNull(exception);
            loadCallbackCount.incrementAndGet();
        });

        waitUntil(() -> loadCallbackCount.get() == 1);
        mdb.tick(System.currentTimeMillis() + 60_000L);

        assertEquals(originalJson, storage.getStored("player", "2002"));
        assertEquals(1, table.getCacheList().size());
        assertEquals(0, flushCallbackCount.get());
    }

    @Test
    public void flushDuringLoadIngShouldWaitLoadFinishThenFlush() {
        long key = 2003L;
        storage.replace("player", FastJson.toJSONString(key), FastJson.toJSONString(player(key, 13, "loading")));

        BlockingGetPlayerTable blockingTable = new BlockingGetPlayerTable();
        useTable(blockingTable);

        AtomicInteger loadCallbackCount = new AtomicInteger();
        AtomicInteger flushCallbackCount = new AtomicInteger();
        AtomicBoolean flushSuccess = new AtomicBoolean(false);

        mdb.loadPlayerAllTableToMemory(key, true, (playerInfo, allSuccess, exception) -> {
            assertTrue(allSuccess);
            loadCallbackCount.incrementAndGet();
        });

        blockingTable.awaitGetStarted();
        mdb.flush(key, (playerInfo, allSuccess, exception) -> {
            flushCallbackCount.incrementAndGet();
            flushSuccess.set(allSuccess);
        }, System.currentTimeMillis());

        assertEquals(0, flushCallbackCount.get());
        blockingTable.releaseGet();
        waitUntil(() -> loadCallbackCount.get() == 1);
        tickUntil(() -> flushCallbackCount.get() == 1);

        assertEquals(1, loadCallbackCount.get());
        assertEquals(1, flushCallbackCount.get());
        assertTrue(flushSuccess.get());
        assertNotNull(storage.getStored("player", "2003"));
        assertEquals(0, blockingTable.getCacheList().size());
    }

    @Test
    public void flushFailureShouldKeepDirtyRecordForRetry() {
        long key = 2004L;
        String originalJson = FastJson.toJSONString(player(key, 10, "load"));
        storage.replace("player", FastJson.toJSONString(key), originalJson);
        loadPlayer(key);
        Player loaded = table.get(key);
        loaded.setLevel(14);
        loaded.setPlayerName("retry");

        AtomicBoolean firstSuccess = new AtomicBoolean(true);
        AtomicReference<Exception> firstError = new AtomicReference<>();
        AtomicBoolean secondSuccess = new AtomicBoolean(false);
        AtomicReference<Exception> secondError = new AtomicReference<>();
        AtomicInteger callbackCount = new AtomicInteger();

        storage.failNextReplace();
        mdb.flush(key, (playerInfo, allSuccess, exception) -> {
            callbackCount.incrementAndGet();
            firstSuccess.set(allSuccess);
            firstError.set(exception);
        }, System.currentTimeMillis());

        tickUntil(() -> callbackCount.get() == 1);
        assertFalse(firstSuccess.get());
        assertEquals(originalJson, storage.getStored("player", "2004"));
        assertEquals(1, table.getCacheList().size());
        assertNull(firstError.get());

        mdb.flush(key, (playerInfo, allSuccess, exception) -> {
            callbackCount.incrementAndGet();
            secondSuccess.set(allSuccess);
            secondError.set(exception);
        }, System.currentTimeMillis());

        tickUntil(() -> callbackCount.get() == 2);
        assertTrue(secondSuccess.get());
        assertNull(secondError.get());
        assertNotNull(storage.getStored("player", "2004"));
        assertEquals(0, table.getCacheList().size());
    }

    @Test
    public void loadDuringFlushIngShouldReturnFlushingError() {
        long key = 2005L;
        AtomicReference<MdbPlayerInfo> loadPlayerInfo = new AtomicReference<>();
        AtomicBoolean loadSuccess = new AtomicBoolean(true);
        AtomicReference<Exception> loadError = new AtomicReference<>();

        MdbPlayerInfo flushingInfo = new MdbPlayerInfo(key, true, MdbPlayerInfo.flush_ing);
        putPlayerInfoForTest(key, flushingInfo);

        mdb.loadPlayerAllTableToMemory(key, true, (playerInfo, allSuccess, exception) -> {
            loadPlayerInfo.set(playerInfo);
            loadSuccess.set(allSuccess);
            loadError.set(exception);
        });

        waitUntil(() -> loadPlayerInfo.get() != null);
        assertEquals(Long.valueOf(key), loadPlayerInfo.get().getPlayerId());
        assertFalse(loadSuccess.get());
        assertNotNull(loadError.get());
        assertEquals("player is flushing", loadError.get().getMessage());
    }

    private void loadPlayer(long key) {
        AtomicReference<MdbPlayerInfo> playerInfoRef = new AtomicReference<>();
        AtomicBoolean success = new AtomicBoolean(false);
        AtomicReference<Exception> error = new AtomicReference<>();

        mdb.loadPlayerAllTableToMemory(key, true, (playerInfo, allSuccess, exception) -> {
            playerInfoRef.set(playerInfo);
            success.set(allSuccess);
            error.set(exception);
        });

        waitUntil(() -> playerInfoRef.get() != null);
        assertEquals(Long.valueOf(key), playerInfoRef.get().getPlayerId());
        assertTrue(success.get());
        assertNull(error.get());
    }

    private void useTable(PlayerTable newTable) {
        Tables tables = new Tables();
        tables.mdb = mdb;
        tables.storage = storage;
        newTable.setTables(tables);
        tables.add(newTable);
        mdb.getAllTables().clear();
        mdb.getAllTables().add(tables);
        table = newTable;
    }

    private void tickUntil(BooleanSupplier condition) {
        long deadline = System.currentTimeMillis() + 1000L;
        while (!condition.getAsBoolean() && System.currentTimeMillis() < deadline) {
            mdb.tick(System.currentTimeMillis());
            sleep();
        }
    }

    private void waitUntil(BooleanSupplier condition) {
        long deadline = System.currentTimeMillis() + 1000L;
        while (!condition.getAsBoolean() && System.currentTimeMillis() < deadline) {
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    private Player player(long playerId, int level, String name) {
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setLevel(level);
        player.setPlayerName(name);
        return player;
    }

    private void resetMdb() {
        if (mdb == null) {
            return;
        }
        mdb.running = true;
        mdb.close();
    }

    @SuppressWarnings("unchecked")
    private void initPlayerExecutorsForTest() {
        try {
            Field field = Mdb.class.getDeclaredField("playerExecutor");
            field.setAccessible(true);
            List<ThreadPoolExecutor> executors = (List<ThreadPoolExecutor>) field.get(mdb);
            if (!executors.isEmpty()) {
                return;
            }
            for (int i = 0; i < 2; i++) {
                executors.add(ConcurrentEnvironment.getInstance()
                        .newFixedThreadPool("mdb.player.executor." + i, 1));
            }
        } catch (Exception e) {
            throw new AssertionError("init player executors for test failed", e);
        }
    }

    @SuppressWarnings("unchecked")
    private void putPlayerInfoForTest(Long key, MdbPlayerInfo playerInfo) {
        try {
            Field field = Mdb.class.getDeclaredField("mdbPlayerMap");
            field.setAccessible(true);
            Map<Long, MdbPlayerInfo> playerMap = (Map<Long, MdbPlayerInfo>) field.get(mdb);
            playerMap.put(key, playerInfo);
        } catch (Exception e) {
            throw new AssertionError("put player info for test failed", e);
        }
    }

    private static class PlayerTable extends TTable<Long, Player> {
        @Override
        public String getName() {
            return "player";
        }

        @Override
        public boolean isSupportFlush() {
            return true;
        }

        @Override
        protected Player newValue() {
            return new Player();
        }

        @Override
        protected String marshalKey(Long key) {
            return FastJson.toJSONString(key);
        }

        @Override
        protected String marshalValue(Player value) {
            return FastJson.toJSONString(value);
        }

        @Override
        protected Player deepCopy(Player value) {
            return new Player(value);
        }

        @Override
        protected Long unmarshalKey(String value) {
            return FastJson.parseObject(value, Long.class);
        }

        @Override
        protected Player unmarshalValue(String value) {
            return FastJson.parseObject(value, Player.class);
        }
    }

    private static final class BlockingGetPlayerTable extends PlayerTable {
        private final CountDownLatch getStarted = new CountDownLatch(1);
        private final CountDownLatch releaseGet = new CountDownLatch(1);

        @Override
        public Player loadToCache(Long key) {
            getStarted.countDown();
            try {
                if (!releaseGet.await(1, TimeUnit.SECONDS)) {
                    throw new AssertionError("wait get release timeout");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AssertionError(e);
            }
            return super.loadToCache(key);
        }

        void awaitGetStarted() {
            try {
                if (!getStarted.await(1, TimeUnit.SECONDS)) {
                    throw new AssertionError("wait get start timeout");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AssertionError(e);
            }
        }

        void releaseGet() {
            releaseGet.countDown();
        }
    }

}
