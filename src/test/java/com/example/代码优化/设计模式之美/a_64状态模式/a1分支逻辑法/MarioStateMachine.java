package com.example.代码优化.设计模式之美.a_64状态模式.a1分支逻辑法;

import com.example.代码优化.设计模式之美.a_64状态模式.State;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 16:04
 * @Description:
 */
public class MarioStateMachine {
	private int score;
	private State currentState;

	public MarioStateMachine() {
		this.score = 0;
		this.currentState = State.SMALL;
	}

	/**
	 * 获得蘑菇
	 */
	public void obtainMushroom() {
		if (currentState.equals(State.SMALL)) {
			this.currentState = State.SUPER;
			this.score += 100;
		}
	}

	/**
	 * 获得斗篷
	 */
	public void obtainCape() {
		if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER)) {
			this.currentState = State.CAPE;
			this.score += 200;
		}
	}

	/**
	 * 获得火焰
	 */
	public void obtainFireFlower() {
		if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER)) {
			this.currentState = State.FIRE;
			this.score += 300;
		}
	}

	/**
	 * 遇见怪物
	 */
	public void meetMonster() {
		if (currentState.equals(State.SUPER)) {
			this.currentState = State.SMALL;
			this.score -= 100;
			return;
		}

		if (currentState.equals(State.CAPE)) {
			this.currentState = State.SMALL;
			this.score -= 200;
		}

		if (currentState.equals(State.FIRE)) {
			this.currentState = State.SMALL;
			this.score -= 300;
		}
	}

	public int getScore() {
		return score;
	}

	public State getCurrentState() {
		return currentState;
	}
}
