package com.example.代码优化.设计模式之美.a_64状态模式.a3状态模式;

import com.example.代码优化.设计模式之美.a_64状态模式.State;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 17:00
 * @Description:
 */
public class CapeMario implements IMario {
	private MarioStateMachine machine;

	public CapeMario() {
	}

	public CapeMario(MarioStateMachine machine) {

	}

	@Override
	public State getName() {
		return State.SMALL;
	}

	@Override
	public void obtainMushroom() {

	}

	@Override
	public void obtainCape() {

	}

	@Override
	public void obtainFireFlower() {

	}

	@Override
	public void meetMonster() {

	}
}
