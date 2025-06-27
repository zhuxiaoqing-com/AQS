package com.youxi.dataStructure.weight;

/**
 * @author zhuxiaoqing
 * @Date: 2022/10/26 17:21
 */
public class ServerInfo {
	private int serverId;
	private int weight;
	private int cur_weight;

	public ServerInfo(int serverId, int weight) {
		this.serverId = serverId;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getCur_weight() {
		return cur_weight;
	}

	public void setCur_weight(int cur_weight) {
		this.cur_weight = cur_weight;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	@Override
	public String toString() {
		return "ServerInfo{" +
				"weight=" + weight +
				", cur_weight=" + cur_weight +
				", serverId=" + serverId +
				'}';
	}
}
