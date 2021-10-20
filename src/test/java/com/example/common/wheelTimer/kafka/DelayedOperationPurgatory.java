package com.example.common.wheelTimer.kafka;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/10/20 14:10
 * @Description:  启动类，可以理解为时间推进，实例化该类的时候，就会启动定时器，最终通过timeoutTimer.advanceClock(timeoutMs)进行时间推进。
 */
public class DelayedOperationPurgatory {
	Timer timeoutTimer;
	// 实例化
	public DelayedOperationPurgatory(Timer timeoutTimer) {
		this.timeoutTimer = timeoutTimer;
		ExpiredOperationReaper expirationReaper = new ExpiredOperationReaper("ExpirationReaper");
		// ExpiredOperationReaper 继承自 Thread 这个位置启动线程
		expirationReaper.start();
	}
	void advanceClock(long timeoutMs) {
		timeoutTimer.advanceClock(timeoutMs);
	}
	private class ExpiredOperationReaper extends ShutdownableThread {

		public ExpiredOperationReaper(String name) {
			super(name);
		}

		@Override
		public void doWork() {
			advanceClock(200L);
		}
	}
}
