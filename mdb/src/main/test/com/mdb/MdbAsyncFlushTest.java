package com.mdb;

import com.mdb.entity.MdbPlayerInfo;
import com.mdb.util.FastJson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xbean.logic.Player;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;

import static org.junit.Assert.*;

public class MdbAsyncFlushTest {
    private Mdb mdb;
    private FakeStorageEngine storage;
    private PlayerTable table;

    @Before
    public void setUp() {
        resetMdb();
        mdb = Mdb.getInstance();
        mdb.setPlayerFlushDelayMill(0L);
        storage = new FakeStorageEngine();

        Tables tables = new Tables();
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
    public void flushShouldPersistLoadedPlayerOnTick() {
        long key = 2001L;
        table.add(key, player(key, 11, "async"));
        loadPlayer(key);

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
        table.add(key, player(key, 12, "cancel"));
        loadPlayer(key);
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

        assertNull(storage.getStored("player", "2002"));
        assertEquals(1, table.getCacheList().size());
        assertEquals(0, flushCallbackCount.get());
    }

    @Test
    public void flushFailureShouldKeepDirtyRecordForRetry() {
        long key = 2004L;
        table.add(key, player(key, 14, "retry"));
        loadPlayer(key);

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
        assertNull(storage.getStored("player", "2004"));
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
        Mdb instance = Mdb.getInstance();
        instance.running = true;
        instance.close();
    }

    private static final class PlayerTable extends TTable<Long, Player> {
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
}
