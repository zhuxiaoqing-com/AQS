package com.example.common.wheelTimer.netty;


import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/10/20 17:10
 * @Description:
 */
public class Test {

	public static void main(String[] args) {
		HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(r -> {
			Thread thread = new Thread(r);
			thread.setUncaughtExceptionHandler((t1, e) -> System.out.println(t1.getName() + e.getMessage()));
			thread.setName("-HashedTimerWheelInstance-");
			return thread;
		}, 100, TimeUnit.MILLISECONDS, 64);

		hashedWheelTimer.newTimeout(a -> System.out.println(""), 3, TimeUnit.SECONDS);
	}


}
