package com.example.redis.entity;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 14:57
 * @Description:
 */
public class RUser {

	private int id;
	private String name;

	public RUser(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
