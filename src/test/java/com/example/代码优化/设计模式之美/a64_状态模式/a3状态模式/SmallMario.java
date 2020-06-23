package com.example.代码优化.设计模式之美.a64_状态模式.a3状态模式;

import com.example.代码优化.设计模式之美.a64_状态模式.State;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 17:00
 * @Description:
 */
public class SmallMario implements IMario {
	private MarioStateMachine machine;

	public SmallMario() {
	}

	public SmallMario(MarioStateMachine machine) {

	}

	@Override
	public State getName() {
		return State.SMALL;
	}

	@Override
	public void obtainMushroom() {
		machine.setCurrentState(new SuperMario(machine));
		machine.setScore(machine.getScore() + 100);
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

	public int sum(Integer a, Integer b) {
		int a1 = a==null?0: a;
		int b1 = b==null?0: b;
		return a1+b1;
	}
}
