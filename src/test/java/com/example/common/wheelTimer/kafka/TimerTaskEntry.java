package com.example.common.wheelTimer.kafka;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/10/20 14:17
 * @Description: 时间轮槽链表对象的节点
 */
public class TimerTaskEntry implements Comparable<TimerTaskEntry> {
	TimerTask timerTask;
	volatile TimerTaskList list = null;
	TimerTaskEntry next = null;
	TimerTaskEntry prev = null;

	// 过期时间
	Long expirationMs;

	public TimerTaskEntry(TimerTask timerTask, long expirationMs) {
		this.timerTask = timerTask;
		this.expirationMs = expirationMs;
		if (timerTask != null) {
			timerTask.setTimerTaskEntry(this);
		}
	}

	public Boolean cancelled() {
		return timerTask.getTimerTaskEntry() != this;
	}

	public void remove() {
		TimerTaskList currentList = list;
		while (currentList != null) {
			currentList.remove(this);
			currentList = list;
		}
	}


	@Override
	public int compareTo(TimerTaskEntry o) {
		return this.expirationMs.compareTo(o.expirationMs);
	}
}
