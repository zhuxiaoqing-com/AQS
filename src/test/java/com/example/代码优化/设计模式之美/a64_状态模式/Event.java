package com.example.代码优化.设计模式之美.a64_状态模式;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 16:18
 * @Description:
 */
public enum Event {
	GOT_MUSHROOM(0),
	GOT_CAPE(1),
	GOT_FIRE(2),
	GOT_MONSTER(3);
	private int value;

	private Event(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
