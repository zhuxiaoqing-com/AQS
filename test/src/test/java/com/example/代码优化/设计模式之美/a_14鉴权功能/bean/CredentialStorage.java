package com.example.代码优化.设计模式之美.a_14鉴权功能.bean;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/15 15:32
 * @Description:
 */
public interface CredentialStorage {
	String getPasswordByAppId(String appId);
}
