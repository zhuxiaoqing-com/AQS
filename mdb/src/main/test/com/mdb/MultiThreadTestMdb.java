package com.mdb;

import com.mdb.util.ConcurrentEnvironment;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mdb.util.XMLUtils;
import xbean.logic.Player;

import java.util.concurrent.*;

/**
 * 启动若干线程定时随机操作db读写
 * 
 * @author Eric.Ma
 *
 */
public class MultiThreadTestMdb {

	private static boolean devMode = true;

	private static final Logger logger = LoggerFactory.getLogger(MultiThreadTestMdb.class);

	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(20, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>());

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("-devmode"))
				devMode = true;
		}
		
		testMdb();
	}

	private static void testMdb() throws Exception {

		Mdb.configLog();
		MdbMetadata mdb = new MdbMetadata();
		mdb.initialize(XMLUtils.getRootElement(TestMdb.inputXml));
		Mdb.getInstance().start(mdb);

		ScheduledThreadPoolExecutor poolExecutor = ConcurrentEnvironment.getInstance().newScheduledThreadPool("mdb.1111",
				1);
		poolExecutor.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						executor.execute(new Runnable() {

							@Override
							public void run() {
								try {
									randomAccess();
								} catch (Exception e) {
									logger.error("randomAccess error ... ", e);
								}
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("scheduler error", e);
				}
			}
		}, 1000, 1000, TimeUnit.MILLISECONDS);

		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				logger.error("{}TMD 终于退出来了", "mazj");
				Mdb.getInstance().close();
			}

		});
		if (devMode) {
			// 开发模式开启，这样推出才能触发ShutdownHook
			logger.warn("press ENTER to call System.exit() {} and run the shutdown routine.", 666);
			try {
				System.in.read();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}

	private static void randomAccess() {
		int rand = ThreadLocalRandom.current().nextInt(1, 3);
		long id = ThreadLocalRandom.current().nextInt(1, 10000);
		Player p = table.logic.Player.get(id);
//		System.err.println("remote get : " + p);
		if (rand % 2 == 0) {
			// write,
			if (p == null) {
				p = new Player();
				table.logic.Player.add(id, p);
			}
		}

	}
}
