package com.example.demo4;

import org.slf4j.Logger;

public class TimeCostPrint {
	String param;
	Logger logger;
	long startTime;

	long errorMill = 5000;

	public TimeCostPrint(Logger logger, String param) {
		this.param = param;
		this.logger = logger;
		start();
	}

	public TimeCostPrint(Logger logger, long errorSecond, String param) {
		this.param = param;
		this.logger = logger;
		this.errorMill = errorSecond * TimeUtil.secondMills;
		start();
	}

	public void start() {
		startTime = System.nanoTime();
	}

	public void printError() {
		print(false);
	}

	public void print() {
		print(true);
	}

	public void print(boolean printInfo) {
		long endTime = System.nanoTime();
		long mill = (endTime - startTime) / TimeUtil.millsNano;
		if (mill > errorMill) {
			logger.error(" 时间统计 error!!! costMill {}    param {} ", mill, param, new RuntimeException());
			return;
		}

		if (printInfo) {
			logger.info(" 时间统计 costMill {}    param {} ", mill, param);
		}
	}
}
