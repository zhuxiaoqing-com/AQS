package com.mdb;

import com.mdb.util.FastJson;
import com.mdb.xmlgen.Naming;
import com.mdb.xmlgen.xmlparse.Table;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

public class TTablePersistenceTest {
    static {
        /*System.setProperty("log4j2.statusLevel", "OFF");
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "OFF");*/
    }

    private FakeStorageEngine storage;
    private Mdb mdb;
    private PlayerTable table;
    private ThreadPoolExecutor dbExecutor;

    @BeforeClass
    public static void quietExpectedErrorLogs() {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.OFF);
        Configurator.setLevel("com.mdb.TTable", Level.OFF);
        Configurator.setLevel("com.mdb.Mdb", Level.OFF);
    }

    @Before
    public void setUp() throws Exception {
        storage = new FakeStorageEngine();
        mdb = new Mdb();
        mdb.registerLogicThreadSerialize(new DirectMdbInterface());

        Tables tables = new Tables();
        tables.mdb = mdb;
        tables.storage = storage;
        mdb.getAllTables().add(tables);

        table = new PlayerTable();
        table.setTables(tables);
        table.setMeta(newTableMeta(table.getName(), true, "player"));
        tables.add(table);
        storage.initTable(table.getName());

        dbExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        visitDbExecutors().add(dbExecutor);
    }

    @After
    public void tearDown() {
        if (dbExecutor != null) {
            dbExecutor.shutdownNow();
        }
    }

    @Test
    public void flushReplaceFailureKeepsRecordForRetry() throws Exception {
        PlayerBean player = player(1001L, 7, "first");
        assertTrue(table.add(1001L, player));

        storage.failNextReplace();
        assertFalse(flush(1001L));
        assertNull(storage.getStored("player", "1001"));

        PlayerBean cached = table.get(1001L);
        assertNotNull(cached);
        assertEquals(7, cached.getLevel());

        assertTrue(flush(1001L));
        assertNotNull(storage.getStored("player", "1001"));
        assertNull(table.getTRecordByCache(1001L));
    }

    @Test
    public void flushRemoveFailureKeepsRemoveStateForRetry() throws Exception {
        assertTrue(table.add(1002L, player(1002L, 8, "remove")));
        assertTrue(flush(1002L));
        assertNotNull(storage.getStored("player", "1002"));
        assertNull(table.getTRecordByCache(1002L));

        assertEquals(8, table.get(1002L).getLevel());
        assertTrue(table.remove(1002L));
        storage.failNextRemove();

        assertFalse(flush(1002L));
        assertNotNull(storage.getStored("player", "1002"));
        assertNull(table.get(1002L));

        assertTrue(flush(1002L));
        assertNull(storage.getStored("player", "1002"));
        assertNull(table.getTRecordByCache(1002L));
    }

    @Test
    public void checkpointReplaceBatchSuccessPersistsAddRecord() {
        assertTrue(table.add(1003L, player(1003L, 9, "checkpoint-add")));

        CheckpointBatch<Long, PlayerBean> batch = prepareCheckpointBatch(1003L);
        table.checkpointMdbSaveDB(
                batch.addCache,
                new HashMap<String, String>(),
                new HashMap<String, String>(),
                batch.addRecords,
                new ArrayList<TRecord>(),
                new ArrayList<TRecord>(),
                false
        );

        PlayerBean persisted = FastJson.parseObject(storage.getStored("player", "1003"), PlayerBean.class);
        assertNotNull(persisted);
        assertEquals(9, persisted.getLevel());
    }

    @Test
    public void checkpointRemoveBatchSuccessRemovesStoredRecord() {
        assertTrue(table.add(1004L, player(1004L, 10, "checkpoint-remove")));
        CheckpointBatch<Long, PlayerBean> addBatch = prepareCheckpointBatch(1004L);
        table.checkpointMdbSaveDB(
                addBatch.addCache,
                new HashMap<String, String>(),
                new HashMap<String, String>(),
                addBatch.addRecords,
                new ArrayList<TRecord>(),
                new ArrayList<TRecord>(),
                false
        );
        assertNotNull(storage.getStored("player", "1004"));

        assertTrue(table.remove(1004L));
        CheckpointBatch<Long, PlayerBean> removeBatch = prepareCheckpointBatch(1004L);
        table.checkpointMdbSaveDB(
                new HashMap<String, String>(),
                new HashMap<String, String>(),
                removeBatch.removeCache,
                new ArrayList<TRecord>(),
                new ArrayList<TRecord>(),
                removeBatch.removeRecords,
                false
        );

        assertNull(storage.getStored("player", "1004"));
    }

    @Test
    public void checkpointBatchFailureCanBeMarkedDirtyForManualRetry() {
        assertTrue(table.add(1005L, player(1005L, 11, "checkpoint-retry")));
        CheckpointBatch<Long, PlayerBean> batch = prepareCheckpointBatch(1005L);

        storage.failNextReplaceBatch();
        table.checkpointMdbSaveDB(
                batch.addCache,
                new HashMap<String, String>(),
                new HashMap<String, String>(),
                batch.addRecords,
                new ArrayList<TRecord>(),
                new ArrayList<TRecord>(),
                false
        );

        assertNull(storage.getStored("player", "1005"));
        batch.record.checkPointFail();
        assertTrue(batch.record.isModified(mdb, false));
    }

    private boolean flush(long key) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean success = new AtomicBoolean(false);
        AtomicReference<Exception> error = new AtomicReference<Exception>();
        mdb.flush(key, (flushKey, allSuccess, exception) -> {
            success.set(allSuccess);
            error.set(exception);
            latch.countDown();
        });
        assertTrue("flush callback timeout", latch.await(5, TimeUnit.SECONDS));
        assertNull(error.get());
        return success.get();
    }

    private PlayerBean player(long playerId, int level, String name) {
        PlayerBean player = new PlayerBean();
        player.setPlayerId(playerId);
        player.setLevel(level);
        player.setPlayerName(name);
        return player;
    }

    private CheckpointBatch<Long, PlayerBean> prepareCheckpointBatch(Long key) {
        TRecord<Long, PlayerBean> record = table.getTRecordByCache(key);
        assertNotNull(record);

        boolean modified = record.isModified(mdb, false);
        String valueJson = modified ? record.marshalValue() : null;
        int state = record.getState();
        ((XBean) record.getValue()).findVersionAtomic().set(0);
        record.checkpointRefreshState(state);

        CheckpointBatch<Long, PlayerBean> batch = new CheckpointBatch<Long, PlayerBean>();
        batch.record = record;
        switch (state) {
            case TRecord.ADD:
                batch.addCache.put(record.marshalKey(), valueJson);
                batch.addRecords.add(record);
                break;
            case TRecord.GET:
                batch.modifyCache.put(record.marshalKey(), valueJson);
                batch.modifyRecords.add(record);
                break;
            case TRecord.REMOVE:
                batch.removeCache.put(record.marshalKey(), "");
                batch.removeRecords.add(record);
                break;
            default:
                fail("unexpected state: " + state);
        }
        return batch;
    }

    @SuppressWarnings("unchecked")
    private List<ThreadPoolExecutor> visitDbExecutors() throws Exception {
        Field field = Mdb.class.getDeclaredField("visitDBExecutor");
        field.setAccessible(true);
        return (List<ThreadPoolExecutor>) field.get(mdb);
    }

    private Table newTableMeta(String tableName, boolean supportFlush, String threadName) throws Exception {
        Table meta = new Table();
        setField(Naming.class, meta, "name", tableName);
        setField(Table.class, meta, "supportFlush", supportFlush);
        setField(Table.class, meta, "serializeLogicThreadName", threadName);
        return meta;
    }

    private void setField(Class<?> owner, Object target, String fieldName, Object value) throws Exception {
        Field field = owner.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    private static final class PlayerTable extends TTable<Long, PlayerBean> {
        @Override
        public String getName() {
            return "player";
        }

        @Override
        protected PlayerBean newValue() {
            return new PlayerBean();
        }

        @Override
        protected String marshalKey(Long key) {
            return FastJson.toJSONString(key);
        }

        @Override
        protected String marshalValue(PlayerBean value) {
            return FastJson.toJSONString(value);
        }

        @Override
        protected PlayerBean deepCopy(PlayerBean value) {
            return new PlayerBean(value);
        }

        @Override
        protected Long unmarshalKey(String value) {
            return FastJson.parseObject(value, Long.class);
        }

        @Override
        protected PlayerBean unmarshalValue(String value) {
            return FastJson.parseObject(value, PlayerBean.class);
        }
    }

    private static final class PlayerBean extends XBean {
        private long playerId;
        private int level;
        private String playerName = "";

        private PlayerBean() {
        }

        private PlayerBean(PlayerBean other) {
            this.playerId = other.playerId;
            this.level = other.level;
            this.playerName = other.playerName;
        }

        public long getPlayerId() {
            return playerId;
        }

        public void setPlayerId(long playerId) {
            this.playerId = playerId;
            makeModify();
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
            makeModify();
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
            makeModify();
        }
    }

    private static final class CheckpointBatch<K, V> {
        private final HashMap<String, String> addCache = new HashMap<String, String>();
        private final HashMap<String, String> modifyCache = new HashMap<String, String>();
        private final HashMap<String, String> removeCache = new HashMap<String, String>();
        private final List<TRecord> addRecords = new ArrayList<TRecord>();
        private final List<TRecord> modifyRecords = new ArrayList<TRecord>();
        private final List<TRecord> removeRecords = new ArrayList<TRecord>();
        private TRecord<K, V> record;
    }

    private static final class DirectMdbInterface implements MdbInterface {
        @Override
        public void allExecute(Runnable runnable) {
            runnable.run();
        }

        @Override
        public void execute(String threadName, Object key, Runnable runnable) {
            runnable.run();
        }

        @Override
        public boolean checkThreadNameAndKey(String threadName, Object key) {
            return true;
        }

        @Override
        public boolean checkThreadName(String threadName) {
            return true;
        }

        @Override
        public boolean checkKeyBelongCurrThread(Object key) {
            return true;
        }
    }
}
