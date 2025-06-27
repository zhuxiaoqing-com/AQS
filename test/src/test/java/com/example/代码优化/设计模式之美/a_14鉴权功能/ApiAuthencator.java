package com.example.代码优化.设计模式之美.a_14鉴权功能;

import com.example.代码优化.设计模式之美.a_14鉴权功能.bean.ApiRequest;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/15 15:34
 * @Description:
 */
public interface ApiAuthencator {
	void auth(String url);
	void auth(ApiRequest apiRequest);
}
