package com.example.代码优化.设计模式之美.组合和继承.impl;

import com.example.代码优化.设计模式之美.组合和继承.EggLayable;
import com.example.代码优化.设计模式之美.组合和继承.Flyable;
import com.example.代码优化.设计模式之美.组合和继承.Tweetable;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/14 20:21
 * @Description:
 */
// 麻雀
public class Sparrow implements Flyable, Tweetable, EggLayable {

	@Override
	public void lagEgg() {
		
	}

	@Override
	public void fly() {

	}

	@Override
	public void tweet() {

	}
}
