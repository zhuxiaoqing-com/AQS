package com.example.代码优化.设计模式之美.a_14鉴权功能;

import com.example.代码优化.设计模式之美.a_14鉴权功能.bean.ApiRequest;
import com.example.代码优化.设计模式之美.a_14鉴权功能.bean.AuthToken;
import com.example.代码优化.设计模式之美.a_14鉴权功能.bean.CredentialStorage;

import java.util.Collections;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/15 15:58
 * @Description:
 */
public class DefaultApiAuthencatorImpl implements ApiAuthencator {
	private CredentialStorage credentialStorage;

	public DefaultApiAuthencatorImpl() {
		this.credentialStorage = null;
	}

	public DefaultApiAuthencatorImpl(CredentialStorage credentialStorage) {
		this.credentialStorage = credentialStorage;
	}


	@Override
	public void auth(String url) {
		ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
		auth(apiRequest);
	}

	@Override
	public void auth(ApiRequest apiRequest) {
		String appId = apiRequest.getAppId();
		String token = apiRequest.getToken();
		long timestamp = apiRequest.getTimestamp();
		String baseUrl = apiRequest.getBaseUrl();

		AuthToken clientAuthToken = new AuthToken(token, timestamp);
		if (clientAuthToken.isExpired()) {
			throw new RuntimeException("Token is expired");
		}

		String password = credentialStorage.getPasswordByAppId(appId);
		// 如果token 没有过期就计算服务器 token
		AuthToken serverAuthToken = AuthToken.create(baseUrl, timestamp, Collections.singletonMap("password", password));
		if(!serverAuthToken.match(clientAuthToken)){
			throw new RuntimeException("Token verfication failed");
		}
	}
}













