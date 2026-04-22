package com.mdb;

import com.mdb.util.FastJson;
import org.junit.Before;
import org.junit.Test;
import xbean.logic.Player;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class TTablePersistenceTest {
    private FakeStorageEngine storage;
    private PlayerTable table;

    @Before
    public void setUp() {
        storage = new FakeStorageEngine();
        Tables tables = new Tables();
        tables.storage = storage;

        table = new PlayerTable();
        table.setTables(tables);
        storage.initTable(table.getName());
    }

    @Test
    public void flushInsertFailureKeepsRecordForRetry() {
        Player player = player(1001L, 7, "first");
        assertTrue(table.add(1001L, player));

        storage.failNextInsert();
        assertFalse(table.flush(1001L));
        assertNull(storage.getStored("player", "1001"));

        Player cached = table.get(1001L);
        assertNotNull(cached);
        assertEquals(7, cached.getLevel());

        assertTrue(table.flush(1001L));
        assertNotNull(storage.getStored("player", "1001"));
    }

    @Test
    public void flushRemoveFailureKeepsRemoveStateForRetry() {
        assertTrue(table.add(1002L, player(1002L, 8, "remove")));
        assertTrue(table.flush(1002L));
        assertNotNull(storage.getStored("player", "1002"));
        assertNotNull(table.get(1002L));

        assertTrue(table.remove(1002L));
        storage.failNextRemove();

        assertFalse(table.flush(1002L));
        assertNotNull(storage.getStored("player", "1002"));
        assertNull(table.get(1002L));

        assertTrue(table.flush(1002L));
        assertNull(storage.getStored("player", "1002"));
    }

    @Test
    public void checkpointReplaceBatchFailureKeepsDirtyForNextCheckpoint() {
        assertTrue(table.add(1003L, player(1003L, 9, "checkpoint-add")));
        storage.failNextReplaceBatch();

        checkpoint(1L);
        assertNull(storage.getStored("player", "1003"));

        checkpoint(2L);
        Player persisted = FastJson.parseObject(storage.getStored("player", "1003"), Player.class);
        assertEquals(9, persisted.getLevel());
    }

    @Test
    public void checkpointRemoveBatchFailureKeepsRemoveStateForNextCheckpoint() {
        assertTrue(table.add(1004L, player(1004L, 10, "checkpoint-remove")));
        checkpoint(1L);
        assertNotNull(storage.getStored("player", "1004"));

        assertTrue(table.remove(1004L));
        storage.failNextRemoveBatch();

        checkpoint(2L);
        assertNotNull(storage.getStored("player", "1004"));

        checkpoint(3L);
        assertNull(storage.getStored("player", "1004"));
    }

    private void checkpoint(long checkpointCount) {
        table.checkpoint(
                checkpointCount,
                new HashMap<String, String>(),
                new HashMap<String, String>(),
                new HashMap<String, String>(),
                newRecordList(),
                newRecordList(),
                newRecordList(),
                false
        );
    }

    @SuppressWarnings("rawtypes")
    private List<TRecord> newRecordList() {
        return new java.util.ArrayList<>();
    }

    private Player player(long playerId, int level, String name) {
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setLevel(level);
        player.setPlayerName(name);
        return player;
    }

    private static final class PlayerTable extends TTable<Long, Player> {
        @Override
        public String getName() {
            return "player";
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
