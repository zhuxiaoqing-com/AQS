package com.example.代码优化.设计模式之美.a64_状态模式.a3状态模式;

import com.example.代码优化.设计模式之美.a64_状态模式.State;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 17:00
 * @Description:
 */
public class MarioStateMachine {
	private int score;
	private IMario currentState; // 不在使用枚举来表示状态

	public MarioStateMachine() {
		this.score  = 0;
		this.currentState = new SmallMario(this);
	}


	public void obtainMushroom() {
		this.currentState.obtainMushroom();
	}

	public void obtainCape() {
		this.currentState.obtainCape();
	}

	public void obtainFireFlower() {
		this.currentState.obtainFireFlower();
	}

	public void meetMonster() {
		this.currentState.meetMonster();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public IMario getCurrentState() {
		return currentState;
	}

	public void setCurrentState(IMario currentState) {
		this.currentState = currentState;
	}
}
