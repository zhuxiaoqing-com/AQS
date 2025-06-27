package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestStat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 19:27
 * @Description:
 */
public class EmailViewer implements StatViewer {
	private List<String> toAddresses = new ArrayList<>();

	public EmailViewer(List<String> toAddresses) {
		this.toAddresses = toAddresses;
	}

	public EmailViewer() {
	}

	public void addToAddress(String address){
		toAddresses.add(address);
	}
	@Override
	public void output(Map<String, RequestStat> requestStats, long startTimeInMills, long endTimeInMills) {
		// format the requestStat to HTML style
	}
}
