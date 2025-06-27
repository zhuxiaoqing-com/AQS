package com.example.common.wheelTimer.kafka;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/10/20 14:16
 * @Description:
 */
abstract class ShutdownableThread extends Thread{
	CountDownLatch shutdownInitiated = new CountDownLatch(1);
	CountDownLatch shutdownComplete = new CountDownLatch(1);

	public ShutdownableThread(String name) {
		super(name);
		this.setDaemon(false);
	}
	Boolean isShutdownInitiated() {
		return shutdownInitiated.getCount() == 0;
	}
	Boolean isRunning() {
		return !isShutdownInitiated();
	}
	@Override
	public void run() {
	//	logger.info("Starting");
		try {
			while (isRunning()) {
				// 通过一个可终止的死循环进行时间推进
				doWork();
			}
		} catch (Error e) {
			shutdownInitiated.countDown();
			shutdownComplete.countDown();
	//		logger.info("stopped");
			System.exit(1);
		} catch (Throwable t) {
			if (isRunning()) {
	//			logger.error("error due to", t);
			}
		} finally {
			shutdownComplete.countDown();
		}
	//	logger.info("Stopped");
	}

	public abstract void doWork();
}
