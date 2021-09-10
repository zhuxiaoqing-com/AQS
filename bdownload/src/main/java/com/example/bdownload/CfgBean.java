package com.example.bdownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

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

	public static String FFMPEG_PATH;

	public static final Logger logger = LoggerFactory.getLogger(CfgBean.class);
	public static void init() throws Exception {
		Properties properties = new Properties();
		File configPath = getConfigPath("cfg.properties");

		try {
			properties.load(new InputStreamReader(new FileInputStream(configPath), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		keyWord = String.valueOf(properties.get("keyWord"));
		downPath = String.valueOf(properties.get("downPath"));
		downVideoNum = Integer.parseInt(properties.get("downVideoNum").toString());
		waitTimeMinuteMin = Integer.parseInt(properties.get("waitTimeMinuteMin").toString());
		waitTimeMinuteMax = Integer.parseInt(properties.get("waitTimeMinuteMax").toString());


		FFMPEG_PATH = getConfigPath("ffmpeg.exe").getPath();
	}

	/**
	 * 获取配置文件
	 *
	 * @param subDir
	 * @return
	 */
	public static File getConfigPath(String subDir) {
		File configFile;

		/**
		 * 先从根目录获取配置文件
		 */
		String path = System.getProperty("user.dir") + File.separator + subDir;
		configFile = new File(path);
		if (!configFile.exists()) {
			/**
			 *  从classpath 获取
			 */
			path = CfgBean.class.getResource("/").getPath() + subDir;
			configFile = new File(path);
		}


		return configFile;
	}
}
