

import com.mdb.Mdb;
import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import xbean.logic.Bag;
import xbean.logic.Player;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class TestFlush {

	public static void main(String[] args) throws Exception {
		Mdb.configLog();
		MdbMetadata mdb = new MdbMetadata();
		mdb.initialize(XMLUtils.getRootElement(TestMdb.inputXml));
		Mdb.getInstance().start(mdb);
		Thread.sleep(3000L);

		long key = 10000L;
		Bag bag = table.logic.Bag.get(key);
		if(bag == null) {
			bag = new Bag();
			table.logic.Bag.add(key, bag);
		}

		int i = ThreadLocalRandom.current().nextInt(100000);
		bag.setPlayerId(i);
		bag.setBagtest(i);
		Player player = new Player();
		player.setLevel(i);
		player.setPlayerId(i);
		player.setPlayerName(i+"");
		bag.setPlayer(player);

		bag.getPlayerList().clear();
		bag.getPlayerList().add(new Player(player));
		bag.getPlayerSet().clear();
		bag.getPlayerSet().add(new Player(player));
		bag.getPlayerMap().clear();
		bag.getPlayerMap().put((long) i,new Player(player));


		bag.getLongList().clear();
		bag.getLongList().add((long) i);
		bag.getLongSet().clear();
		bag.getLongSet().add((long) i);
		bag.getLongMap().clear();
		bag.getLongMap().put((long) i,(long) i);


		Thread.sleep(1000L);
		Mdb.getInstance().flush(1000L, null);

		System.err.println("回车退出 random : " + i);
		System.in.read();
		System.exit(-1);
	}
}
