package com.example.common.wheelTimer.kafka;


import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/10/20 14:14
 * @Description:
 */
public class SystemTimer implements Timer{
	String executorName;
	long tickMs;
	int wheelSize;
	long startMs;
	DelayQueue<TimerTaskList> delayQueue = new DelayQueue<>();
	AtomicInteger taskCounter = new AtomicInteger(0);
	TimingWheel timingWheel;

	// 延迟任务到期执行线程
	ExecutorService taskExecutor = Executors.newFixedThreadPool(1);

	ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
	ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


	public SystemTimer(String executorName, long tickMs, int wheelSize, long startMs) {
		this.executorName = executorName;
		this.tickMs = tickMs;
		this.wheelSize = wheelSize;
		this.startMs = startMs;
		timingWheel = new TimingWheel(
				tickMs,
				wheelSize,
				startMs,
				taskCounter,
				delayQueue
		);
	}

	@Override
	public void add(TimerTask timerTask) {
		readLock.lock();
		try {
			// 添加延迟任务到时间轮
			addTimerTaskEntry(new TimerTaskEntry(timerTask, timerTask.delayMs + System.currentTimeMillis()));
		} finally {
			readLock.unlock();
		}
	}

	private void addTimerTaskEntry(TimerTaskEntry timerTaskEntry) {
		// 执行添加延迟任务到时间轮
		if (!timingWheel.add(timerTaskEntry)) {
			// 添加失败，要么是任务已经取消，要么是已到期
			if(!timerTaskEntry.cancelled()) {
				// 如果任务已经到期，异步执行任务
				taskExecutor.submit(timerTaskEntry.timerTask);
			}
		}
	}

	// 将延迟任务重新插入新的时间轮槽位，以此来触发到期
	Consumer<TimerTaskEntry> reinsert = this::addTimerTaskEntry;

	// 时间推进
	@Override
	public Boolean advanceClock(Long timeoutMs) {
		try {
			// 通过阻塞的方式获取到期的槽位,减少循环的次数
			TimerTaskList bucket = delayQueue.poll(timeoutMs, TimeUnit.MILLISECONDS);
			//delayQueue.take();
			if(bucket != null) {
				writeLock.lock();
			}
			try {
				while (bucket != null) {
					// 将时间轮指针指向当前槽位的位置，最终就是修改currentTime
					timingWheel.advanceClock(bucket.getExpiration());
					// 清空该槽，重新将槽内的任务插入到时间轮新的位置，将到期的任务直接执行
					// 重新加入的原因是，上层时间轮的间隔不是1ms了，所以存在一个槽位存在多个不同延迟的任务，
					// 通过重新加入时间轮，就会对该槽位所有的任务进行时间轮降级
					// 比方有2个任务，一开始延迟时间为25ms,36ms，这两个就会加入同一个槽位[20, 40)
					// 当时间推进到20的时候就会,该槽位就会取出，当前时间加了20ms，然后这2个任务就会加入到第一层时间轮对应的位置，
					// 下次再推进到[5, 6)，就会执行25ms这个任务了。同理[16, 17)的时候就会执行36ms任务了
					bucket.flush(reinsert);
					// 再次尝试立刻获取过期的任务，因为存在前面重新入时间轮刚好没过期，这里再次取一次有任务可以直接获取到
					// 这里为了提高性能，所以直接使用最快捷的poll()也就是尝试取第一个槽对象判断是否过期
					bucket = delayQueue.poll();
				}
			}finally {
				writeLock.unlock();
			}
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
}
