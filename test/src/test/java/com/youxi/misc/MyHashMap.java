package com.youxi.misc;


import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/5/24 09:24
 * @Description:
 */
public class MyHashMap<K, V> extends HashMap<K, V> {
	Map<String, ArrayDeque<String>> record = new ConcurrentHashMap<>();

	@Override
	public V put(K key, V value) {
		V put = super.put(key, value);
		record(key);
		return put;
	}


	@Override
	public V remove(Object key) {
		V remove = super.remove(key);
		record(key);
		return remove;
	}

	private void record(Object key) {
		String data = createData(key);

		ArrayDeque<String> strings = record.computeIfAbsent(Thread.currentThread().getName(), a -> new ArrayDeque<>());
		if (strings.size() > 10) {
			strings.removeFirst();
		}

		strings.add(data);

		if (record.size() >= 2) {
			print(new HashMap<>(record));
			record.clear();
		}

	}

	private String createData(Object key) {
		StringBuilder stringBuilder = new StringBuilder();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
		//String time = TimeUtil.genTs();
		stringBuilder.append(time);
		stringBuilder.append(System.getProperty("line.separator"));

		stringBuilder.append("key ====  ").append(key);
		stringBuilder.append(System.getProperty("line.separator"));

		String collect = Stream.of(Thread.currentThread().getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining(System.getProperty("line.separator")));
		stringBuilder.append(collect);
		stringBuilder.append(System.getProperty("line.separator"));
		return stringBuilder.toString();
	}

	private void print(Map<String, ArrayDeque<String>> record) {

		int index = 0;
		StringBuilder stringBuilder = new StringBuilder();
		for (Entry<String, ArrayDeque<String>> entry : record.entrySet()) {
			stringBuilder.append(index++ + "  线程 " + entry.getKey());
			stringBuilder.append(System.getProperty("line.separator"));

			for (String s : entry.getValue()) {
				stringBuilder.append(s);
				stringBuilder.append(System.getProperty("line.separator"));
				stringBuilder.append(System.getProperty("line.separator"));
				stringBuilder.append(System.getProperty("line.separator"));
			}

		}
	/*	Scene.logger.error("battle多线程并发 battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发");
		Scene.logger.error(stringBuilder.toString());
*/
		System.out.println("battle多线程并发 battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发");
		System.out.println(stringBuilder.toString());
	}

}
