package com.example.demo1.util.lock;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ObjectLockHolder {
	private static final ConcurrentHashMap<Class<?>, Holder> HOLDERS = new ConcurrentHashMap<>();

	public ObjectLock getLock(Object object) {
		Holder holder = getHolder(object.getClass());
		ObjectLock lock = holder.getLock(object);
		return lock;
	}

	private Holder getHolder(Class<?> clz) {
		Holder holder = (Holder) HOLDERS.get(clz);
		if (holder != null) {
			return holder;
		}
		HOLDERS.putIfAbsent(clz, new Holder(clz));
		return (Holder) HOLDERS.get(clz);
	}

	public ReentrantLock getTieLock(Class<?> clz) {
		Holder holder = getHolder(clz);
		return holder.getTieLock();
	}

	public int count(Class<?> clz) {
		if (HOLDERS.containsKey(clz)) {
			Holder holder = getHolder(clz);
			return holder.count();
		}
		return 0;
	}

	public class Holder {
		@SuppressWarnings("unused")
		private final Class<?> clz;
		private final ReentrantLock tieLock = new ReentrantLock();

		private final Map<Object, ObjectLock> locks = Collections.synchronizedMap(new WeakHashMap<Object, ObjectLock>());

		private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

		public Holder(Class<?> clz) {
			this.clz = clz;
		}

		public ObjectLock getLock(Object object) {
			Lock lock = this.lock.readLock();
			try {
				lock.lock();
				ObjectLock result = (ObjectLock) this.locks.get(object);
				if (result != null) {
					ObjectLock localObjectLock1 = result;
					return localObjectLock1;
				}
			} finally {
				lock.unlock();
			}
			return createLock(object);
		}

		private ObjectLock createLock(Object object) {
			Lock lock = this.lock.writeLock();
			try {
				lock.lock();
				ObjectLock result = (ObjectLock) this.locks.get(object);
				// ObjectLock localObjectLock1;
				if (result != null) {
					return result;
				}
				result = new ObjectLock(object);
				this.locks.put(object, result);
				return result;
			} finally {
				lock.unlock();
			}
		}

		public ReentrantLock getTieLock() {
			return this.tieLock;
		}

		public int count() {
			return this.locks.size();
		}
	}
}
