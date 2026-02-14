import com.mdb.collection.ConcurrentArrayListX;
import com.mdb.util.FastJson;
import xbean.logic.Bag;
import xbean.logic.Player;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestJsonCollection {
	public static String inputXml = "mdb\\src\\main\\mdb.xml";
	public static void main(String[] args) throws Exception {

	/*	List<Integer> arrayList = new ConcurrentArrayListX<>();
		int index = 10;
		for (int i = 0; i < index; i++) {
			arrayList.add(i);
		}

		FastJson.toJSONString(arrayList);*/


		bagJson();

	}

	private static void bagJson() {
		Bag bag ;
		bag = new Bag();


		int i = ThreadLocalRandom.current().nextInt(100000);
		/*bag.setPlayerId(i);
		bag.setBagtest(i);*/
	/*	Player player = new Player();
		player.setLevel(i);
		player.setPlayerId(i);
		player.setPlayerName(i+"");*/
		//bag.setPlayer(player);

	/*	bag.getPlayerList().clear();
		bag.getPlayerList().add(new Player(player));
		bag.getPlayerSet().clear();
		bag.getPlayerSet().add(new Player(player));
		bag.getPlayerMap().clear();
		bag.getPlayerMap().put((long) i,new Player(player));*/


		bag.getLongList().clear();
		bag.getLongList().add((long) i);
		bag.getLongSet().clear();
		bag.getLongSet().add((long) i);
		bag.getLongMap().clear();
		bag.getLongMap().put((long) i,(long) i);
		bag.getLongListCow().clear();
		bag.getLongListCow().add((long) i);
		bag.getLongListRw().clear();
		bag.getLongListRw().add((long) i);
		//bag.getLongQueue().clear();
		//bag.getLongQueue().add((long) i);
		FastJson.toJSONString(bag);
	}

}
