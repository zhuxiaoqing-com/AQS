package com.example.代码优化.设计模式之美.a_64状态模式.a1分支逻辑法;

import com.example.代码优化.设计模式之美.a_64状态模式.State;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 16:08
 * @Description:
 */
public class ApplicationDemo {
	public static void main(String[] args) {
		MarioStateMachine mario = new MarioStateMachine();
		mario.obtainMushroom();
		int score = mario.getScore();
		State currentState = mario.getCurrentState();
		System.out.println(score + "...." + currentState);
	}
}
