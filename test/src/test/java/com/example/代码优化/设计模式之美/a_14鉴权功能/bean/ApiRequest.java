package com.example.代码优化.设计模式之美.a_14鉴权功能.bean;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/15 15:31
 * @Description:
 */
public class ApiRequest {
	private String baseUrl;
	private String token;
	private String appId;
	private long timestamp;

	public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
		this.baseUrl = baseUrl;
		this.token = token;
		this.appId = appId;
		this.timestamp = timestamp;
	}


	public static ApiRequest createFromFullUrl(String url) {
		// 将url 解析成上述的成员变量 这里就不解析了
		return new ApiRequest("", "", "", 0);
	}


	public String getBaseUrl() {
		return baseUrl;
	}

	public String getToken() {
		return token;
	}

	public String getAppId() {
		return appId;
	}

	public long getTimestamp() {
		return timestamp;
	}
}

