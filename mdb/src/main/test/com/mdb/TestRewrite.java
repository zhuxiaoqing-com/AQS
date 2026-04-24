package com.mdb;

import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import xbean.logic.Player;

/**
 * a测试写db时候db无效，下次db开启时候重新写入
 * 
 * @author Eric.Ma
 *
 */
public class TestRewrite {
	public static void main(String[] args) throws Exception {

		Mdb.configLog();
		MdbMetadata mdb = new MdbMetadata();
		mdb.initialize(XMLUtils.getRootElement(TestMdb.inputXml));
		Mdb.getInstance().start(mdb);

		Player p = table.logic.Player.get(1L);
		if (p == null) {
			p = new Player();
			table.logic.Player.add(1L, p);
		}

		System.err.println("beforeset ");
		p.setLevel(11111);
		System.err.println("afterset ");
		// 1. 关闭mysql服务
		// 2. 等待多轮checkpoint
		// 3. 打开mysql服务
		// 4. 数据因为写入异常时候，修改了md5值mysql健康时会写入之前的值

		debugMode();

	}

	private static void debugMode() {
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				System.err.println("TMD 终于退出来了");
				Mdb.getInstance().close();
			}

		});
		// 开发模式开启，这样推出才能触发ShutdownHook
		System.err.println("press ENTER to call System.exit() {} and run the shutdown routine.");
		try {
			System.in.read();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

}
