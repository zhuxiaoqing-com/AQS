package com.mdb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public final class ConcurrentEnvironment {

	private static final Logger logger = LoggerFactory.getLogger(ConcurrentEnvironment.class);

	private final static ThreadFactory standaloneFactory = Worker.newFactory("Standalone", false);

	private final Map<String, ThreadPoolExecutor> map = new HashMap<String, ThreadPoolExecutor>();

	private final static ConcurrentEnvironment instance = new ConcurrentEnvironment();

	private ConcurrentEnvironment() {
	}

	public static ConcurrentEnvironment getInstance() {
		return instance;
	}

	public synchronized ThreadPoolExecutor newThreadPool(String name, int corePoolSize, boolean daemon) {
		if (map.containsKey(name))
			throw new IllegalStateException("duplicate ExecutorService " + name);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), Worker.newFactory(name, daemon));
		map.put(name, executor);
		return executor;
	}

	public ThreadPoolExecutor newThreadPool(String name, int corePoolSize) {
		return newThreadPool(name, corePoolSize, false);
	}

	public synchronized ThreadPoolExecutor newFixedThreadPool(String name, int size, boolean daemon) {
		if (map.containsKey(name))
			throw new IllegalStateException("duplicate ExecutorService " + name);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(size, size, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(), Worker.newFactory(name, daemon));
		map.put(name, executor);
		return executor;
	}

	public ThreadPoolExecutor newFixedThreadPool(String name, int size) {
		return newFixedThreadPool(name, size, false);
	}

	public synchronized ScheduledThreadPoolExecutor newScheduledThreadPool(String name, int corePoolSize,
			boolean daemon) {
		if (map.containsKey(name))
			throw new IllegalStateException("duplicate ExecutorService " + name);
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(corePoolSize,
				Worker.newFactory(name, daemon));
		map.put(name, executor);
		return executor;
	}

	public ScheduledThreadPoolExecutor newScheduledThreadPool(String name, int corePoolSize) {
		return newScheduledThreadPool(name, corePoolSize, false);
	}

	private void shutdown(String name) {
		ThreadPoolExecutor executor = map.remove(name);
		if (executor == null) {
			logger.warn("miss executor name = {}", name);
			return;
		}
		executor.shutdown();
	}

	public synchronized void shutdown(String... names) {
		for (String name : names)
			shutdown(name);
	}

	public synchronized void shutdown() {
		shutdown(map.keySet().toArray(new String[0]));
	}

	public Runnable executeStandaloneTask(Runnable r) {
		final Thread thread = standaloneFactory.newThread(r);
		thread.start();
		return new Runnable() {
			@Override
			public void run() {
				try {
					thread.join();
				} catch (InterruptedException e) {
				}
			}
		};
	}

	private static class Worker extends Thread {
		private final ThreadFactory threadFactory;

		private Worker(ThreadFactory threadFactory, String executorName, boolean daemon, Runnable r) {
			super(r);
			this.threadFactory = threadFactory;
			this.setDaemon(daemon);
			this.setName(executorName + "." + this.getId());
		}

		public ThreadFactory getThreadFactory() {
			return threadFactory;
		}

		@Override
		public void run() {
			try {
				super.run();
			} catch (Throwable e) {
				logger.error("worker catch Exception", e);
			}
		}

		public static ThreadFactory newFactory(final String executorName, final boolean daemon) {
			return new ThreadFactory() {
				@Override
				public Thread newThread(Runnable r) {
					return new Worker(this, executorName, daemon, r);
				}
			};
		}
	}
}
