import com.mdb.Mdb;
import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestMdb2 {
	private static Mdb mdb;
	
	static final List<Long> playerIds = new ArrayList<Long>();

	public static void main(String[] args) throws Exception {
		//Mdb.configLog();
		Mdb.configLog();
		MdbMetadata metadata = new MdbMetadata();
		metadata.initialize(XMLUtils.getRootElement(TestMdb.inputXml));
		mdb = new Mdb();
		mdb.start(metadata);
		
		long max = 100000;
		
		//主线程预先加载数据
		for(long playerId = 1;playerId < max;playerId++){
			playerIds.add(playerId);
			getData(playerId);
		}
		
		
		System.out.println("加载完毕----------");

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				Random random = new Random();
//				while (true) {
//					if(playerIds.size() <= 0){
//						break;
//					}
//					try {
//						Thread.sleep(20);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					long playerId = playerIds.get(random.nextInt(playerIds.size()));
//					upData(playerId);
//					playerIds.remove(playerId);
//					if(playerIds.size() % 100 == 0){
//						System.out.println("size :" + playerIds.size());
//					}
//				}
//			}
//		}).start();

		Random random = new Random();
		while (true) {
			if(playerIds.size() <= 0){
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long playerId = playerIds.get(random.nextInt(playerIds.size()));
			if(upData(playerId)){
				playerIds.remove(playerId);
			}
			if(playerIds.size() % 100 == 0){
				System.out.println("size :" + playerIds.size());
			}
		}
		
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				Random random = new Random();
//				double cost = 0;
//				while (true) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					//每秒下线1000条数据
//					for(int i=0;i<500;i++){
//						//随机，修改数据
//						long playerId = random.nextInt((int)max);
//						long startTime = System.nanoTime();
//						Mdb.getInstance().flush(playerId);
//						double costTime = (System.nanoTime() - startTime) / 1000000.0;
//						if(costTime > cost){
//							cost = costTime;
//							System.out.println("玩家下线最高耗时：" + cost);
//						}
//					}
//				}
//				
//			}
//		}).start();

		System.err.println("回车退出");
		System.in.read();
		mdb.close();
		System.exit(-1);
	}
	
	static void getData(long playerId){
		xbean.logic.Player player = table.logic.Player.get(mdb, playerId);
		if (player == null) {
			player = new xbean.logic.Player();
			player.setPlayerId(playerId);
			table.logic.Player.add(mdb, playerId, player);
		}
		player.setLevel(0);
//		
//		xbean.logic.Bag1 b1 = table.logic.Bag1.get(playerId);
//		if (b1 == null) {
//			b1 = new xbean.logic.Bag1();
//			b1.setPlayerId(playerId);
//			table.logic.Bag1.add(playerId, b1);
//		}
//		
//		
//		xbean.logic.Bag2 b2 = table.logic.Bag2.get(playerId);
//		if (b2 == null) {
//			b2 = new xbean.logic.Bag2();
//			b2.setPlayerId(playerId);
//			table.logic.Bag2.add(playerId, b2);
//		}
//		
//		
//		xbean.logic.Bag3 b3 = table.logic.Bag3.get(playerId);
//		if (b3 == null) {
//			b3 = new xbean.logic.Bag3();
//			b3.setPlayerId(playerId);
//			table.logic.Bag3.add(playerId, b3);
//		}
		
	}
	
	
	
	static boolean upData(long playerId){
		xbean.logic.Player player = table.logic.Player.get(mdb, playerId);
		player.setLevel(player.getLevel() + 1);
		if(player.getLevel() == 3){
			mdb.flush(playerId, (key, allSuccess, exception) -> {
			});
			return true;
		}
		return false;
		
	}
}
