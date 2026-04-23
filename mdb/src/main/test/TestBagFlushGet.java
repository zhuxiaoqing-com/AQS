import com.mdb.Mdb;
import com.mdb.util.FastJson;
import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import xbean.logic.Bag;
import xbean.logic.Player;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class TestBagFlushGet {
    private static final String INPUT_XML = "mdb\\src\\main\\mdb.xml";

    public static void main(String[] args) throws Exception {
        Mdb.configLog();
        startMdb();

        long key = System.currentTimeMillis();
        try {
            Bag bag = new Bag();
            fillAllFields(key, bag);
            String beforeJson = FastJson.toJSONString(bag);

            table.logic.Bag.add(key, bag);
            flushSync(key);

            Bag actual = table.logic.Bag.get(key);
            String afterJson = FastJson.toJSONString(actual);

            System.out.println("beforeJson=" + beforeJson);
            System.out.println("afterJson =" + afterJson);

            if (!Objects.equals(beforeJson, afterJson)) {
                throw new IllegalStateException("flush 后重新 get 的 JSON 不一致, key=" + key);
            }

            System.out.println("TestBagFlushGet ok, key=" + key);
        } finally {
            Mdb.getInstance().close();
        }
    }

    private static void startMdb() throws Exception {
        MdbMetadata mdb = new MdbMetadata();
        mdb.initialize(XMLUtils.getRootElement(INPUT_XML));
        Mdb.getInstance().start(mdb);
    }

    private static void fillAllFields(long key, Bag bag) {
        bag.setPlayerId(key);
        bag.setBagtest(10086);

        Player mainPlayer = newPlayer(key, 1, "main");
        bag.setPlayer(new Player(mainPlayer));

        Player listPlayer1 = newPlayer(key, 11, "list_1");
        bag.getPlayerList().add(new Player(listPlayer1));

        Player setPlayer1 = newPlayer(key, 21, "set_1");
        bag.getPlayerSet().add(new Player(setPlayer1));

        bag.getPlayerMap().put(101L, new Player(newPlayer(key, 31, "map_1")));

        bag.getLongList().add(1001L);

        bag.getLongSet().add(2001L);

        bag.getLongMap().put(3001L, 4001L);

        bag.getLongListCow().add(5001L);

        bag.getLongListRw().add(6001L);

        bag.getLongQueue().add(7001L);
    }

    private static Player newPlayer(long key, int level, String name) {
        Player player = new Player();
        player.setPlayerId(key);
        player.setLevel(level);
        player.setPlayerName(name);
        return player;
    }

    private static void flushSync(long key) {
        AtomicBoolean success = new AtomicBoolean(false);
        AtomicReference<Exception> error = new AtomicReference<>(null);

        Mdb.getInstance().flush(key, true, (flushKey, allSuccess, exception) -> {
            success.set(allSuccess);
            error.set(exception);
        });

        if (error.get() != null) {
            throw new RuntimeException("flush exception key=" + key, error.get());
        }
        if (!success.get()) {
            throw new IllegalStateException("flush failed key=" + key);
        }
    }
}
