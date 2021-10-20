package com.example.common.wheelTimer.kafka;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/10/20 14:17
 * @Description: 时间轮槽链表对象的节点上的延迟任务
 */
public class TimerTask implements Runnable{
	long delayMs;
	String name;
	TimerTaskEntry timerTaskEntry;

	public TimerTask(long delayMs, String name) {
		this.delayMs = delayMs;
		this.name = name;
	}

	public void cancel() {
		synchronized (this) {
			if(timerTaskEntry != null) {
				timerTaskEntry.remove();
			}
			timerTaskEntry = null;
		}
	}

	// 将延迟任务关联上链表上的一个节点
	public void setTimerTaskEntry(TimerTaskEntry entry) {
		synchronized (this) {
			if(timerTaskEntry != null && timerTaskEntry != entry) {
				timerTaskEntry.remove();
			}
			timerTaskEntry = entry;
		}
	}

	public TimerTaskEntry getTimerTaskEntry() {
		return timerTaskEntry;
	}
}
