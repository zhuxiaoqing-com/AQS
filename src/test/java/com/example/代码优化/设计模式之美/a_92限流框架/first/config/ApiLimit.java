package com.example.代码优化.设计模式之美.a_92限流框架.first.config;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/1 20:18
 * @Description:
 */
public class ApiLimit {
	private static final int DEFAULT_TIME_UNIT = 1; // 1 second
	private String api;
	private int limit;
	private int unit = DEFAULT_TIME_UNIT;

	public ApiLimit() {
	}

	public ApiLimit(String api, int limit) {
		this(api, limit, DEFAULT_TIME_UNIT);
	}

	public ApiLimit(String api, int limit, int unit) {
		this.api = api;
		this.limit = limit;
		this.unit = unit;
	}


	public static int getDefaultTimeUnit() {
		return DEFAULT_TIME_UNIT;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}
}
