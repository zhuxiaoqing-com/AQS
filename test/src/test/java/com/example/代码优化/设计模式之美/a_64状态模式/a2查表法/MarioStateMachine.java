package com.example.代码优化.设计模式之美.a_64状态模式.a2查表法;

import com.example.代码优化.设计模式之美.a_64状态模式.Event;
import com.example.代码优化.设计模式之美.a_64状态模式.State;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 16:20
 * @Description:
 */
public class MarioStateMachine {
	private int score;
	private State currentState;

	private static final State[][] transitionTable = {
			{State.SUPER, State.CAPE, State.FIRE, State.SMALL},
			{State.SUPER, State.CAPE, State.FIRE, State.SMALL},
			{State.CAPE, State.CAPE, State.CAPE, State.SMALL},
			{State.FIRE, State.FIRE, State.FIRE, State.SMALL}
	};

	private static final int[][] actionTable = {
			{+100, +200, +300, +0},
			{+0, +200, +300, -100},
			{+0, +0, +0, -200},
			{+0, +0, +0, -300}
	};

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


	private void executeEvent(Event event) {
		int stateValue = currentState.getValue();
		int eventValue = event.getValue();
		this.currentState = transitionTable[stateValue][eventValue];
		this.score += actionTable[stateValue][eventValue];
	}

	public int getScore() {
		return score;
	}

	public State getCurrentState() {
		return currentState;
	}
}
