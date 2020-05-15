package com.example.代码优化.设计模式之美.a_14鉴权功能.bean;

import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/15 15:23
 * @Description:
 */
public class AuthToken {
	// 过期的时间间隔
	private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;
	private String token;
	private long createTime;
	private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

	public AuthToken(String token, long createTime) {
		this.token = token;
		this.createTime = createTime;
	}

	public AuthToken(String token, long createTime, long expiredTimeInterval) {
		this.token = token;
		this.createTime = createTime;
		this.expiredTimeInterval = expiredTimeInterval;
	}

	public static AuthToken create(String baseUrl, long createTime, Map<String, String> params) {
		// 这里将 baseUrl+createTime+params 加密 组成 token; 然后创建 AuthToken;
		String token = baseUrl + createTime + params.toString(); // 这步其实应该是用加密算法加密
		return new AuthToken(token, createTime);
	}

	public String getToken() {
		return token;
	}

	// 是否过期
	public boolean isExpired() {
		return System.currentTimeMillis() - createTime > expiredTimeInterval;
	}

	public boolean match(AuthToken authToken) {
		return authToken.getToken().equals(token);
	}

}










