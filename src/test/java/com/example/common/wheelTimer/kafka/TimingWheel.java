package com.example.common.wheelTimer.kafka;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/10/20 14:16
 * @Description:
 */
public class TimingWheel {
	// 精度 也就是 两个刻度之间的时间差，这里第一层就是1ms，第二层20ms，第三层400ms以此类推
	Long tickMs;
	// 时间轮槽刻 度数，也就是槽数
	int wheelSize;
	// 时间推进起始时间 也就是当前时间
	Long startMs;
	// 任务计数器
	AtomicInteger taskCounter;
	// 根据延迟时间槽位刻度值，保存槽位FIFO延迟队列
	DelayQueue<TimerTaskList> queue;
	// 当前层时间轮总共时间 tickMs * wheelSize;
	Long interval;
	// 推进到的当前时间
	Long currentTime;
	// 父级时间轮
	volatile TimingWheel overflowWheel;
	// 当面时间轮保存延迟任务的固定大小数字,TimerTaskList是一个双向链表
	TimerTaskList[] buckets;

	public TimingWheel(Long tickMs, int wheelSize, Long startMs, AtomicInteger taskCounter, DelayQueue<TimerTaskList> queue) {
		this.tickMs = tickMs;
		this.wheelSize = wheelSize;
		this.startMs = startMs;
		this.taskCounter = taskCounter;
		this.queue = queue;

		this.interval = tickMs * wheelSize;
		// startMs = 11; tickMs = 10;  currentTime = 11 - (11%10) = 11- 1= 10; 就是将startMs 修正为当前时间为当前刻度的整数倍
		this.currentTime = startMs - (startMs % tickMs);
		buckets = new TimerTaskList[wheelSize];

		// 初始化时间轮存储数组
		for (int i = 0; i < wheelSize; i++) {
			buckets[i] = new TimerTaskList(taskCounter);
		}
	}

	// 延迟时间超出当前时间轮，进行时间轮升级
	public synchronized void addOverflowWheel() {
		if (overflowWheel == null) {
			overflowWheel = new TimingWheel(interval, wheelSize, currentTime, taskCounter, queue);
		}
	}

	// 在时间轮中执行加入延迟任务操作
	public Boolean add(TimerTaskEntry timerTaskEntry) {
		// 获取到加入的任务的延迟时间ms数;
		long expiration = timerTaskEntry.expirationMs;
		// 已经取消加入失败
		if (timerTaskEntry.cancelled()) {
			return false;
		}

		// 过期时间小于下一个刻度，说明已经过期
		if (expiration < currentTime + tickMs) {
			return false;
		}

		// 过期时间没有超出当前层时间轮的总时长
		if (expiration < currentTime + interval) {
			// 计算出可插入的槽位
			long virtualId = expiration / tickMs;
			int index = (int) (virtualId % wheelSize);
			TimerTaskList bucket = buckets[index];
			// 将任务加入到对应的槽位
			bucket.add(timerTaskEntry);
			// 设置槽位的过期时间,如果设置成功，说明原来这个槽位的链表是没有任务的
			if (bucket.setExpiration(virtualId * tickMs)) {
				// 如果设置成功说明是新槽位，加入到延迟队列中等待时间推进
				queue.offer(bucket);
			}
			return true;
		} else {
			// 如果超出当前时间轮，加入到上层时间轮，逻辑一样
			if (overflowWheel == null) {
				addOverflowWheel();
			}
			return overflowWheel.add(timerTaskEntry);
		}
	}

	// 时间推进逻辑
	public void advanceClock(Long timeMs) {
		// 判断时间推进的距离
		if (timeMs >= currentTime + tickMs) {
			// 如果超过当前时间轮的一个刻度数，将当前时间推进到timeMs向下取整的刻度上
			currentTime = timeMs - (timeMs % tickMs);
		}
		if(overflowWheel != null) {
			overflowWheel.advanceClock(currentTime);
		}
	}
}
