package com.example.demo1.util.lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

public class LockUtils {
	private static ObjectLockHolder holder = new ObjectLockHolder();

	public static ChainLock getLock(Object... objects) {
		List<ObjectLock> locks = loadLocks(objects);
		return new ChainLock(locks);
	}
	
	public static ChainLock getLock(List<Object> objects) {
		List<ObjectLock> locks = loadLocks(objects.toArray());
		return new ChainLock(locks);
	}

	private static List<ObjectLock> loadLocks(Object[] arrayOfObject) {
		List<ObjectLock> locks = new ArrayList<ObjectLock>();
		for (int i = 0; i < arrayOfObject.length; i++) {
			Object obj = arrayOfObject[i];
			ObjectLock lock = holder.getLock(obj);
			if ((lock != null) && (!locks.contains(lock))) {
				locks.add(lock);
			}
		}
		// todo ce shi
		//ObjectLock a = (ObjectLock)holder.getTieLock(Integer.class);

		Collections.sort(locks);
		
		TreeSet<Integer> idx = new TreeSet<Integer>();

		ObjectLock lock1,lock2;
		for (int i = 1; i < locks.size(); i++) {
			lock1 = (ObjectLock) locks.get(i - 1);
			lock2 = (ObjectLock) locks.get(i);
			if (lock1.isTie(lock2)) {
				idx.add(i);
			}
		}

		if (idx.isEmpty()) {
			return locks;
		}
		
		List<ObjectLock> newsLocks = new ArrayList<ObjectLock>(locks.size() + idx.size());
		newsLocks.addAll(locks);
		
		for(Integer i : idx) {
			
			//TODO 父类转子类。。。。可能有问题
			ObjectLock lock = locks.get(i);
			ReentrantLock tieLock = holder.getTieLock(lock.getClz());
			// 这里会报错 类型转换错误
			newsLocks.add((ObjectLock) tieLock);
		}
		return newsLocks;
	}
}
