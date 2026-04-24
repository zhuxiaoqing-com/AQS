package com.mdb;

import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;

import java.util.Random;

public class TestMdb3 {

	public static void main(String[] args) throws Exception {
		//Mdb.configLog();
		Mdb.configLog();
		MdbMetadata mdb = new MdbMetadata();
		mdb.initialize(XMLUtils.getRootElement(TestMdb.inputXml));
		Mdb.getInstance().start(mdb);
//		Thread.sleep(3000L);
		
		long max = 1000;

		for(long playerId = 1;playerId < max;playerId++){
			getData(playerId);
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
						xbean.logic.Bag3 p = table.logic.Bag3.get(pid);
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
				xbean.logic.Player p = table.logic.Player.get(1L);
				p.setLevel(11112);
				
			}
		}).start();
//		
		table.logic.Player.get(16L);
		table.logic.Player.get(17L);
		

		System.err.println("回车退出");
		System.in.read();
		Mdb.getInstance().close();
		System.exit(-1);
	}
	
	static void getData(long playerId){
		xbean.logic.Player player = table.logic.Player.get(playerId);
		if (player == null) {
			player = new xbean.logic.Player();
			player.setPlayerId(playerId);
			table.logic.Player.add(playerId, player);
		}
		
		xbean.logic.Bag1 b1 = table.logic.Bag1.get(playerId);
		if (b1 == null) {
			b1 = new xbean.logic.Bag1();
			b1.setPlayerId(playerId);
			table.logic.Bag1.add(playerId, b1);
		}
		
		
		xbean.logic.Bag2 b2 = table.logic.Bag2.get(playerId);
		if (b2 == null) {
			b2 = new xbean.logic.Bag2();
			b2.setPlayerId(playerId);
			table.logic.Bag2.add(playerId, b2);
		}
		
		
		xbean.logic.Bag3 b3 = table.logic.Bag3.get(playerId);
		if (b3 == null) {
			b3 = new xbean.logic.Bag3();
			b3.setPlayerId(playerId);
			table.logic.Bag3.add(playerId, b3);
		}
		
	}
	
	
	
	static void upData(long playerId){
		Random random = new Random();
		xbean.logic.Player player = table.logic.Player.get(playerId);
		player.setLevel(random.nextInt());
		
		xbean.logic.Bag1 b1 = table.logic.Bag1.get(playerId);
		b1.setBagtest(random.nextInt());
		
		
		xbean.logic.Bag2 b2 = table.logic.Bag2.get(playerId);
		b2.setBagtest(random.nextInt());
		
		
		xbean.logic.Bag3 b3 = table.logic.Bag3.get(playerId);
		b3.setBagtest(random.nextInt());
		
	}
}
