package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestStat;

import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 19:27
 * @Description:
 */
public class ConsoleViewer implements StatViewer {

	@Override
	public void output(Map<String, RequestStat> requestStats, long startTimeInMills, long endTimeInMills) {
		System.out.println(requestStats);
	}
}
