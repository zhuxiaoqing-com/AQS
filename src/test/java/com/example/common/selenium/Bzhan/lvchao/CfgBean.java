package com.example.common.selenium.Bzhan.lvchao;

import java.io.InputStream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/9/2 20:52
 * @Description:
 */
public class CfgBean {
	public static String keyWord;
	public static String downPath;
	public static int downVideoNum;
	public static int waitTimeMinuteMin;
	public static int waitTimeMinuteMax;

	public static void readCfg() {
		InputStream in = CfgBean.class.getClassLoader().getResourceAsStream("config/config.properties");
	}
}
