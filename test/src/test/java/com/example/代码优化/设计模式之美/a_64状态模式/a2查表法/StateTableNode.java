package com.example.代码优化.设计模式之美.a_64状态模式.a2查表法;

import com.example.代码优化.设计模式之美.a_64状态模式.State;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 16:26
 * @Description:
 */
public class StateTableNode {
	private State state;
	private int score;

	private StateTableNode(State state, int score) {
		this.state = state;
		this.score = score;
	}

	public State getState() {
		return state;
	}

	public int getScore() {
		return score;
	}

	public static StateTableNode valueOf(State state, int score) {
		return new StateTableNode(state, score);
	}
}
