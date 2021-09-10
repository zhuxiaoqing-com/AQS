package com.example.bdownload;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 */
public class Download {
	private static final Logger logger = LoggerFactory.getLogger(Download.class);

	/** 视频地址 */
	// bilibili 通用视频连接
	private String BILIBILI_VIDEO_URL = "https://www.bilibili.com/video/";
	// 具体视频连接
	private String CONCRETE_VIDEO_URL = "BV1x54y1Q7mo";
	// 获取全部视频信息接口--为了获取全部视频名称
	private String VIDEO_NAMES_URL = "https://api.bilibili.com/x/player/pagelist?bvid="+CONCRETE_VIDEO_URL+"&jsonp=jsonp";
	// 具体的视频接口
	private String VIDEO_URL = BILIBILI_VIDEO_URL + CONCRETE_VIDEO_URL;

	// ffmpeg 本地地址
	private String FFMPEG_PATH = CfgBean.FFMPEG_PATH;

	private String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36";

	private VideoInfo VIDEO_INFO = new VideoInfo();
	private String SAVE_PATH = CfgBean.downPath;

	public Download(String concrete_video_url) {
		this.CONCRETE_VIDEO_URL = concrete_video_url;

		this.VIDEO_URL = BILIBILI_VIDEO_URL + CONCRETE_VIDEO_URL;
		VIDEO_NAMES_URL = "https://api.bilibili.com/x/player/pagelist?bvid="+CONCRETE_VIDEO_URL+"&jsonp=jsonp";
	}

	public static void main(String[] args) {
		Download bv1x54y1Q7mo = new Download("BV1as411v7a9");
		bv1x54y1Q7mo.parser();
	}

	public void parser(){
		HttpResponse res = HttpRequest.get(VIDEO_URL).timeout(2000).execute();
		String html = res.body();
		Document document = Jsoup.parse(html);
		Element title = document.getElementsByTag("title").first();
		logger.info("开始下载视频 b站视频id:{} title:{}", CONCRETE_VIDEO_URL, title.text());
		Element multi_page = document.getElementById("multi_page");
		if(multi_page != null) {
			htmlParserList();
		}else {
			htmlParser(html);
		}
	}

	/**
	 * @Description 解析多个网页信息获取视频集合
	 * @return void
	 */
	private void htmlParserList() {
		// 获取全部视频名称
		List<String> videoNames = getVideoNames();
		String url = "";

		for (int i = 0; i < videoNames.size(); i++) {
			url = VIDEO_URL + "?p=" + (i+1);
			logger.info("url地址 = {}", url);
			HttpResponse res = HttpRequest.get(url).timeout(2000).execute();
			String html = res.body();
			// 视频名称
			logger.info("视频名称== " + videoNames.get(i));
			VIDEO_INFO.videoName = videoNames.get(i);
			// 截取视频信息
			Pattern pattern = Pattern.compile("(?<=<script>window.__playinfo__=).*?(?=</script>)");
			Matcher matcher = pattern.matcher(html);
			if (matcher.find()) {
				VIDEO_INFO.videoInfo = new JSONObject(matcher.group());
			} else {
				logger.error("未匹配到视频信息，退出程序！");
				return;
			}
			getVideoInfo();
		}

	}

	/** 解析HTML获取相关信息 */
	private void htmlParser(String html) {
	/*	HttpResponse res = HttpRequest.get(VIDEO_URL).timeout(2000).execute();
		String html = res.body();*/
		Document document = Jsoup.parse(html);
		Element title = document.getElementsByTag("title").first();
		// 视频名称
		VIDEO_INFO.videoName = title.text();
		// 截取视频信息
		Pattern pattern = Pattern.compile("(?<=<script>window.__playinfo__=).*?(?=</script>)");
		Matcher matcher = pattern.matcher(html);
		if (matcher.find()) {
			VIDEO_INFO.videoInfo = new JSONObject(matcher.group());
		} else {
			logger.error("未匹配到视频信息，退出程序！");
			return;
		}
		getVideoInfo();
	}

	/**
	 * @Description  获取全部视频名称集合
	 * @return java.util.List<java.lang.String>
	 */
	private List<String> getVideoNames(){
		HttpResponse res = HttpRequest.get(VIDEO_NAMES_URL).timeout(2000).execute();
		String jsonUrl = res.body();
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonUrl);
		com.alibaba.fastjson.JSONArray pages = jsonObject.getJSONArray("data");

		List<String> list = new ArrayList<>();

		com.alibaba.fastjson.JSONObject object = null;
		for (int i = 0; i < pages.size(); i++) {
			object = pages.getJSONObject(i);
			list.add(object.getString("part"));
		}

		return list;
	}

	/** 解析视频和音频的具体信息 */
	private void getVideoInfo() {
		// 获取视频的基本信息
		JSONObject videoInfo = VIDEO_INFO.videoInfo;
		JSONArray videoInfoArr = videoInfo.getJSONObject("data").getJSONObject("dash").getJSONArray("video");
		VIDEO_INFO.videoBaseUrl = videoInfoArr.getJSONObject(0).getStr("baseUrl");
		VIDEO_INFO.videoBaseRange = videoInfoArr.getJSONObject(0).getJSONObject("SegmentBase").getStr("Initialization");
		HttpResponse videoRes = HttpRequest.get(VIDEO_INFO.videoBaseUrl)
				.header("Referer", VIDEO_URL)
				.header("Range", "bytes=" + VIDEO_INFO.videoBaseRange)
				.header("User-Agent", USER_AGENT)
				.timeout(2000)
				.execute();
		VIDEO_INFO.videoSize = videoRes.header("Content-Range").split("/")[1];

		// 获取音频基本信息
		JSONArray audioInfoArr = videoInfo.getJSONObject("data").getJSONObject("dash").getJSONArray("audio");
		VIDEO_INFO.audioBaseUrl = audioInfoArr.getJSONObject(0).getStr("baseUrl");
		VIDEO_INFO.audioBaseRange = audioInfoArr.getJSONObject(0).getJSONObject("SegmentBase").getStr("Initialization");
		HttpResponse audioRes = HttpRequest.get(VIDEO_INFO.audioBaseUrl)
				.header("Referer", VIDEO_URL)
				.header("Range", "bytes=" + VIDEO_INFO.audioBaseRange)
				.header("User-Agent", USER_AGENT)
				.timeout(2000)
				.execute();
		VIDEO_INFO.audioSize = audioRes.header("Content-Range").split("/")[1];

		downloadFile();
	}

	/** 下载音视频 */
	private void downloadFile(){
		// 保存音视频的位置
		String save_path = SAVE_PATH;
		File fileDir = new File(save_path);
		if (!fileDir.exists()){
			fileDir.mkdirs();
		}

		// 下载视频
		File videoFile = new File(save_path + File.separator + VIDEO_INFO.videoName + "_video.mp4");
		if (!videoFile.exists()){
			logger.info("--------------开始下载视频文件--------------");
			HttpResponse videoRes = HttpRequest.get(VIDEO_INFO.videoBaseUrl)
					.header("Referer", VIDEO_URL)
					.header("Range", "bytes=0-" + VIDEO_INFO.videoSize)
					.header("User-Agent", USER_AGENT)
					.execute();
			videoRes.writeBody(videoFile);
			logger.info("--------------视频文件下载完成--------------");
		}


		// 下载音频
		File audioFile = new File(save_path + File.separator + VIDEO_INFO.videoName + "_audio.mp4");
		if (!audioFile.exists()){
			logger.info("--------------开始下载音频文件--------------");
			HttpResponse audioRes = HttpRequest.get(VIDEO_INFO.audioBaseUrl)
					.header("Referer", VIDEO_URL)
					.header("Range", "bytes=0-" + VIDEO_INFO.audioSize)
					.header("User-Agent", USER_AGENT)
					.execute();
			audioRes.writeBody(audioFile);
			logger.info("--------------音频文件下载完成--------------");
		}

		mergeFiles(videoFile,audioFile);

	}


	/**
	 * @Description  合并音视频
	 * @param videoFile :
	 * @param audioFile :
	 * @return void
	 */
	private void mergeFiles(File videoFile,File audioFile){
		String outFile = SAVE_PATH + File.separator + VIDEO_INFO.videoName + "_" + CONCRETE_VIDEO_URL + ".mp4";
		logger.info("--------------开始合并音视频-------------- file:{}", outFile);
		List<String> commend = new ArrayList<>();
		commend.add(FFMPEG_PATH);
		commend.add("-i");
		commend.add(videoFile.getAbsolutePath());
		commend.add("-i");
		commend.add(audioFile.getAbsolutePath());
		commend.add("-vcodec");
		commend.add("copy");
		commend.add("-acodec");
		commend.add("copy");
		commend.add(outFile);

		ProcessBuilder builder = new ProcessBuilder();
		builder.command(commend);
		try {
			builder.inheritIO().start().waitFor();
			logger.info("--------------音视频合并完成--------------");
			videoFile.delete();
			audioFile.delete();
		} catch (InterruptedException | IOException e) {
			logger.error("音视频合并失败！");
			e.printStackTrace();
		}

	}


}

class VideoInfo{  // 真实项目中不推荐直接使用`public`哦😯
	public String videoName;
	public JSONObject videoInfo;
	public String videoBaseUrl;
	public String audioBaseUrl;
	public String videoBaseRange;
	public String audioBaseRange;
	public String videoSize;
	public String audioSize;
}
