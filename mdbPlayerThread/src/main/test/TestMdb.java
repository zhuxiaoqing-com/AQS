import com.mdb.Mdb;
import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;

import java.util.Random;

public class TestMdb {
	public static String inputXml = "mdb\\src\\main\\mdb.xml";
	private static Mdb mdb;
	public static void main(String[] args) throws Exception {
		//Mdb.configLog();
		Mdb.configLog();
		MdbMetadata metadata = new MdbMetadata();
		metadata.initialize(XMLUtils.getRootElement(inputXml));
		mdb = new Mdb();
		mdb.start(metadata);
//		Thread.sleep(3000L);
		
		long max = 1000;

		//主线程预先加载数据
		for(long playerId = 1;playerId < max;playerId++){
			getData(playerId);
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				Random random = new Random();
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//每秒修改1000 
					for(int i=0;i<100;i++){
						//随机，修改数据
						long playerId = random.nextInt((int)max);
						upData(playerId);
					}
				}
			}
		}).start();
		
		
		for(int i=0;i<100;i++){
			mdb.flush(new Random().nextInt(20), (key, allSuccess, exception) -> {
			});
		}
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for(long pid = 1;pid < 20;pid++){
						xbean.logic.Bag3 p = table.logic.Bag3.get(mdb, pid);
						p.setBagtest(new Random().nextInt());
					}
				}
				
				
			}
		}).start();
		
//		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				xbean.logic.Player p = table.logic.Player.get(mdb, 1L);
				p.setLevel(11112);
				
			}
		}).start();
//		
//		table.logic.Player.get(16L);
//		table.logic.Player.get(17L);
		
		//Mdb.getInstance().flush(playerId);
		

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
		
		xbean.logic.Bag1 b1 = table.logic.Bag1.get(mdb, playerId);
		if (b1 == null) {
			b1 = new xbean.logic.Bag1();
			b1.setPlayerId(playerId);
			table.logic.Bag1.add(mdb, playerId, b1);
		}
		
		
		xbean.logic.Bag2 b2 = table.logic.Bag2.get(mdb, playerId);
		if (b2 == null) {
			b2 = new xbean.logic.Bag2();
			b2.setPlayerId(playerId);
			table.logic.Bag2.add(mdb, playerId, b2);
		}
		
		
		xbean.logic.Bag3 b3 = table.logic.Bag3.get(mdb, playerId);
		if (b3 == null) {
			b3 = new xbean.logic.Bag3();
			b3.setPlayerId(playerId);
			table.logic.Bag3.add(mdb, playerId, b3);
		}
		
	}
	
	
	
	static void upData(long playerId){
		Random random = new Random();
		xbean.logic.Player player = table.logic.Player.get(mdb, playerId);
		player.setLevel(random.nextInt());
		
		xbean.logic.Bag1 b1 = table.logic.Bag1.get(mdb, playerId);
		b1.setBagtest(random.nextInt());
		
		
		xbean.logic.Bag2 b2 = table.logic.Bag2.get(mdb, playerId);
		b2.setBagtest(random.nextInt());
		
		
		xbean.logic.Bag3 b3 = table.logic.Bag3.get(mdb, playerId);
		b3.setBagtest(random.nextInt());
		
	}
}
