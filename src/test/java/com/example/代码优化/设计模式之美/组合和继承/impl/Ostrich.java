package com.example.代码优化.设计模式之美.组合和继承.impl;

import com.example.代码优化.设计模式之美.组合和继承.EggLayAbility;
import com.example.代码优化.设计模式之美.组合和继承.EggLayable;
import com.example.代码优化.设计模式之美.组合和继承.TweetAbility;
import com.example.代码优化.设计模式之美.组合和继承.Tweetable;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/14 20:20
 * @Description:
 */

/**
 * 这里用 组合和委托的方式完美解决了 多层继承的关系
 */
// 鸵鸟
public class Ostrich implements Tweetable, EggLayable {

	TweetAbility tweetAbility = new TweetAbility();  // 组合
	EggLayAbility eggLayAbility = new EggLayAbility();// 组合

	@Override
	public void lagEgg() {
		tweetAbility.tweet(); // 委托
	}

	@Override
	public void tweet() {
		eggLayAbility.fly(); // 委托
	}

	@Override
	public int a() {
		return EggLayable.super.a();
	}
}
