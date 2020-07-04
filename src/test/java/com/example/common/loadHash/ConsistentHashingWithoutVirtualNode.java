package com.example.common.loadHash;

import io.swagger.models.auth.In;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/4 10:43
 * @Description: 一致性hash 没有虚拟节点
 */
public class ConsistentHashingWithoutVirtualNode {
	/**
	 * 集群地址列表
	 */
	private static String[] groups = {
			"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
			"192.168.0.3:111", "192.168.0.4:111"
	};

	/*
	 * 选择一种数据结构保存hash环，可以采用的方案有很多种, 最简单的是采用数组或链表.
	 * 但是这样查找的时候需要进行排序，如果节点数量多，速度就可能变得很慢。
	 *
	 * 对于集群负载均衡状态 读多写少的状态，很容易联想到使用二叉平衡树的结构去存储，
	 * 实际上可以使用 TreeMap 来作为hash环的存储结构
	 */
	/**
	 * 用于保存 hash 环上的节点
	 */
	private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

	/**
	 * 初始化，将所有的服务器加入Hash环中
	 */
	static {
		// 使用红黑树实现，插入效率比较差，但是查找效率极高
		for (String group : groups) {
			int hash = HashUtil.getHash(group);
			System.out.println("[" + group + "] launched @ " + hash);
			sortedMap.put(hash, group);
		}
	}

	/**
	 * 计算对应的widget加载在哪个group上
	 *
	 * @param widgetKey
	 * @return
	 */
	private static String getServer(String widgetKey) {
		if (sortedMap.isEmpty()) {
			return null;
		}
		int widgetHash = HashUtil.getHash(widgetKey);
		// 获取所有 Key 大约等于 widgetHash 的 map
		SortedMap<Integer, String> tailMap = sortedMap.tailMap(widgetHash);
		//  hash 值在最尾部，应该映射到第一个 group 上
		if (tailMap.isEmpty()) {
			return sortedMap.get(sortedMap.firstKey());
		}
		return tailMap.get(tailMap.firstKey());
	}


	@Test
	public void test01() {
		SortedMap<Integer, String> sortMap = new TreeMap<>();
		for (int i = 0; i < 10; i++) {
			sortMap.put(i, i + "a");
		}

		System.out.println(sortMap.tailMap(1));
	}

	@Test
	public void test02() {
		// 生成随机数进行测试
		Map<String, Integer> resMap = new HashMap<>();

		for (int i = 0; i < 100000; i++) {
			Integer widgetId = (int) (Math.random() * 10000);
			String server = getServer(widgetId.toString());
			if (resMap.containsKey(server)) {
				resMap.put(server, resMap.get(server) + 1);
			} else {
				resMap.put(server, 1);
			}
		}
		resMap.forEach((k, v) -> System.out.println("group " + k + ": " + v + "(" + v/1000.0D +"%)"));
	}

}
