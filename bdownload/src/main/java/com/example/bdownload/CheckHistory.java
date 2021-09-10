package com.example.bdownload;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/9/3 10:10
 * @Description:
 */
public class CheckHistory {

	public static Set<String> alreadyDownVideo = new HashSet<>();
	/**
	 * https://www.bilibili.com/video/BV11L411W7pE?from=search&seid=6721115846966777373&spm_id_from=333.337.0.0
	 * BV11L411W7pE 是视频id
	 *
	 * 检测该视频Id是否已经下载过了
	 */
	public static boolean alreadyDownloaded(String videoUnique) {
		return alreadyDownVideo.contains(videoUnique);
	}


	public static void init(String downPath) {
		File file = new File(downPath);
		if(!file.exists()) {
			return;
		}
		alreadyDownVideo = Arrays.stream(file.listFiles())
				.map(f -> f.getName().substring(f.getName().lastIndexOf("_") + 1, f.getName().lastIndexOf(".")))
				.collect(Collectors.toSet());
	}
}
