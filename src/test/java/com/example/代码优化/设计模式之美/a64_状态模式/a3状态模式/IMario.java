package com.example.代码优化.设计模式之美.a64_状态模式.a3状态模式;

import com.example.代码优化.设计模式之美.a64_状态模式.State;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/23 16:57
 * @Description:
 */
public interface IMario { // 所有状态类的接口
	State getName();

	// 以下是定义的事件
	void obtainMushroom();
	void obtainCape();
	void obtainFireFlower();
	void meetMonster();
}
