package com.example.common.wheelTimer.kafka;


/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/10/20 14:13
 * @Description:
 */
public interface Timer {
	void add(TimerTask timerTask);
	Boolean advanceClock(Long timeoutMs);
}
