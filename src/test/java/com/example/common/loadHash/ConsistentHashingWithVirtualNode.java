package com.example.common.loadHash;

import org.junit.Test;

import java.util.*;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/4 10:43
 * @Description: 一致性hash 引入虚拟节点
 */
public class ConsistentHashingWithVirtualNode {
	/**
	 * 集群地址列表
	 */
	private static String[] groups = {
			"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
			"192.168.0.3:111", "192.168.0.4:111"
	};

	/**
	 * 真实集群列表
	 */
	private static List<String> realGroups = new LinkedList<>();

	/**
	 * 虚拟节点映射关系
	 * 这里不保存真实节点;就保存好多个虚拟节点
	 */
	private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

	private static final int VIRTUAL_NODE_NUM = 1000;

	/**
	 * 初始化，将所有的服务器加入Hash环中
	 */
	static {
		// 先添加真实节点
		realGroups.addAll(Arrays.asList(groups));

		// 将虚拟节点映射到hash环上
		for (String realGroup : realGroups) {
			for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
				String virtualNodeName = getVirtualNodeName(realGroup, i);
				int hash = HashUtil.getHash(virtualNodeName);
				System.out.println("[" + virtualNodeName + "] launched @ " + hash);
				sortedMap.put(hash, virtualNodeName);
			}
		}
	}

	private static String getServer(String widgetKey) {
		if (sortedMap.isEmpty()) {
			return null;
		}
		int hash = HashUtil.getHash(widgetKey);
		SortedMap<Integer, String> tailMap = sortedMap.tailMap(hash);
		String virtualNodeName;

		// 如果该hash 在尾部就获取头部的 group
		if (tailMap.isEmpty()) {
			virtualNodeName = sortedMap.get(sortedMap.firstKey());
		} else {
			virtualNodeName = tailMap.get(tailMap.firstKey());
		}

		return getRealNodeName(virtualNodeName);
	}

	/**
	 * 将节点名字和当前是第几个虚拟节点拼接起来
	 * <p>
	 * 这里真实节点和虚拟节点的映射采用了字符串拼接的方式，
	 * 这种方式虽然简单但很有效，memcached官方也是这么实现的。
	 * 将虚拟节点的数量设置为1000，重新测试压力分布情况，结果如下：
	 */
	private static String getVirtualNodeName(String realNodeName, int num) {
		return realNodeName + "&&VN" + String.valueOf(num);
	}

	/**
	 * 将节点名字和当前是第几个虚拟节点拼接起来
	 */
	private static String getRealNodeName(String virtualNodeName) {
		return virtualNodeName.split("&&")[0];
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
		resMap.forEach((k, v) -> System.out.println("group " + k + ": " + v + "(" + v / 1000.0D + "%)"));
	}

}
