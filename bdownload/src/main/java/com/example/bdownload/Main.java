package com.example.bdownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/9/3 10:12
 * @Description:
 */
public class Main {
	public static final Logger logger = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) throws Exception {
		/*logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");*/
		CfgBean.init();
		CheckHistory.init(CfgBean.downPath);

		new DownPageVideo().a(CfgBean.keyWord);
	}
}
