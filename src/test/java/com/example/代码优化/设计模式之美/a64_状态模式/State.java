package com.example.代码优化.设计模式之美.a64_状态模式;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 16:03
 * @Description:
 */
public enum State {
	SMALL(0),
	SUPER(1),
	FIRE(2),
	CAPE(3);

	private int value;

	private State(int value) {
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
}
