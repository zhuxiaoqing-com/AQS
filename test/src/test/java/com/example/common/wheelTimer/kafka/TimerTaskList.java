package com.example.common.wheelTimer.kafka;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/10/20 14:17
 * @Description:
 */
public class TimerTaskList implements Delayed {
	// 根节点
	private final TimerTaskEntry root;

	/**
	 * 这个槽位对应的延迟时间，经过类似向下取整的方式的刻度上的值
	 * 打个比方，钟表2020-02-26 01:20:30，假设是只看小时指针，那么这个值就是取 2020-02-26 01:00:00转换为的ms数，
	 * 其他层逻辑一致
	 */
	AtomicLong expiration = new AtomicLong(-1);

	// 全局任务计数器
	private final AtomicInteger taskCounter;

	public TimerTaskList(AtomicInteger taskCounter) {
		this.taskCounter = taskCounter;
		this.root = new TimerTaskEntry(null, -1);
		this.root.next = root;
		this.root.prev = root;
	}

	// 设置槽位过期时间，如果有更新说明是新的槽位
	public Boolean setExpiration(Long expirationMs) {
		return expiration.getAndSet(expirationMs) != expirationMs;
	}

	public Long getExpiration() {
		return expiration.get();
	}

	// 加入一个延迟任务节点
	public synchronized void add(TimerTaskEntry timerTaskEntry) {
		boolean done = false;
		while (!done) {
			timerTaskEntry.remove();
			synchronized (this) {
				if (timerTaskEntry.list == null) {
					TimerTaskEntry tail = root.prev;
					timerTaskEntry.next = root;
					timerTaskEntry.prev = tail;
					timerTaskEntry.list = this;
					tail.next = timerTaskEntry;
					root.prev = timerTaskEntry;
					taskCounter.incrementAndGet();
					done = true;
				}
			}
		}
	}

	// 移除一个链表节点
	public synchronized void remove(TimerTaskEntry timerTaskEntry) {
		synchronized (this) {
			// 判断是属于当前链表的才能移除
			if (timerTaskEntry.list.compareTo(this) == 0) {
				timerTaskEntry.next.prev = timerTaskEntry.prev;
				timerTaskEntry.prev.next = timerTaskEntry.next;
				timerTaskEntry.next = null;
				timerTaskEntry.prev = null;
				timerTaskEntry.list = null;
				taskCounter.decrementAndGet();
			}
		}
	}

	// 由于时间推进，将当前链表的任务重新插入到时间轮，以此来触发到期任务执行
	public synchronized void flush(Consumer<TimerTaskEntry> consumer) {
		TimerTaskEntry head = root.next;
		while (!head.equals(root)) {
			remove(head);
			consumer.accept(head);
			head = root.next;
		}
		expiration.set(-1L);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(Math.max(getExpiration() - System.currentTimeMillis(), 0), TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed d) {
		TimerTaskList other = (TimerTaskList) d;
		if (getExpiration() < other.getExpiration()) {
			return -1;
		} else if (getExpiration() > other.getExpiration()) {
			return 1;
		}
		return 0;
	}

}
