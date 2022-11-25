package com.youxi.dataStructure.weight;

import com.mysql.fabric.Server;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuxiaoqing
 * @Date: 2022/10/26 17:22
 */
public class ServerInfoWeight {
	static Map<Integer, ServerInfo> serverMap = new HashMap<>();
	// 计算总权重值，只需要计算一次，因此放在静态代码块中执行
	private static int weightTotal = 0;

	// 求和总权重值，后续动态伸缩节点时，再次调用该方法即可。
	public static void sumWeightTotal(){
		for (ServerInfo weight : serverMap.values()) {
			weightTotal += weight.getWeight();
		}
	}


	public void addServer(int serverId, int weight) {
		serverMap.put(serverId, new ServerInfo(serverId, weight));
	}

// https://zhuanlan.zhihu.com/p/60347577
	public ServerInfo getNextSeverInfo() {
		int total = 0;
		ServerInfo maxServerInfo = null;
		for (ServerInfo value : serverMap.values()) {
			value.setCur_weight(value.getWeight() + value.getCur_weight());
			total += value.getWeight();

			if (maxServerInfo == null || maxServerInfo.getCur_weight() < value.getCur_weight()) {
				maxServerInfo = value;
			}
		}
		maxServerInfo.setCur_weight(maxServerInfo.getCur_weight() - total);

		return maxServerInfo;
	}


	@Test
	public void test01() {
		ServerInfoWeight serverInfoWeight = new ServerInfoWeight();
		serverInfoWeight.addServer(1,3);
		serverInfoWeight.addServer(2,2);
		serverInfoWeight.addServer(3,1);
		serverInfoWeight.addServer(4,1);
		serverInfoWeight.addServer(5,1);
		serverInfoWeight.addServer(6,1);

		int index = 20;
		for (int skillId = 0; skillId < index; skillId++) {
			ServerInfo nextSeverInfo = serverInfoWeight.getNextSeverInfo();
			System.out.println(nextSeverInfo);
		}

		System.out.println("----------------------");
		System.out.println("----------------------");
		System.out.println("----------------------");
		System.out.println("----------------------");
		System.out.println(serverMap);
	}
}
