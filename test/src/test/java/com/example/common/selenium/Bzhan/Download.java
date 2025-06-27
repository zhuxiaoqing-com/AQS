package com.example.common.selenium.Bzhan;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 */
public class Download {
	/*

		https://upos-sz-mirrorkodo.bilivideo.com/upgcxcode/48/57/192445748/192445748_nb2-1-64.flv
		?e=ig8euxZM2rNcNbRj7wdVhwdlhWTMhwdVhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xN
		EV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6
		gqj0eN0B599M=&uipk=5&nbs=1&deadline=1630494896&gen=playurlv2&os=kodobv&oi=1018278203&trid=29407407f6
		67437990bcca0730cfec8bu&platform=pc&upsig=699e6fa053029391305ca56fe5412430&uparams=e,uipk,nbs,d
		eadline,gen,os,oi,trid,platform&mid=13317308&bvc=vod&nettype=0&orderid=0,3&agrr=1&logo=80000000

	 */
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
	private String FFMPEG_PATH = "F:\\mh\\game_server-develop\\AQS\\src\\test\\resources\\driver\\ffmpeg.exe";

	private String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36";

	private VideoInfo VIDEO_INFO = new VideoInfo();
	private String SAVE_PATH;

	public Download(String concrete_video_url, String save_path) {
		this.CONCRETE_VIDEO_URL = concrete_video_url;
		this.SAVE_PATH = save_path;

		this.VIDEO_URL = BILIBILI_VIDEO_URL + CONCRETE_VIDEO_URL;
		VIDEO_NAMES_URL = "https://api.bilibili.com/x/player/pagelist?bvid="+CONCRETE_VIDEO_URL+"&jsonp=jsonp";
	}

	public static void main(String[] args) {
		Download bv1x54y1Q7mo = new Download("BV1as411v7a9", "E:\\测试\\bDownload");
		bv1x54y1Q7mo.parser();
	}

	public void parser(){
		HttpResponse res = HttpRequest.get(VIDEO_URL).timeout(2000).execute();
		String html = res.body();
		Document document = Jsoup.parse(html);
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
			System.out.println("url地址 = " + url);
			HttpResponse res = HttpRequest.get(url).timeout(2000).execute();
			String html = res.body();
			// 视频名称
			System.out.println("视频名称== " + videoNames.get(i));
			VIDEO_INFO.videoName = videoNames.get(i);
			// 截取视频信息
			Pattern pattern = Pattern.compile("(?<=<script>window.__playinfo__=).*?(?=</script>)");
			Matcher matcher = pattern.matcher(html);
			if (matcher.find()) {
				VIDEO_INFO.videoInfo = new JSONObject(matcher.group());
			} else {
				System.err.println("未匹配到视频信息，退出程序！");
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
			System.err.println("未匹配到视频信息，退出程序！");
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
			System.out.println("--------------开始下载视频文件--------------");
			HttpResponse videoRes = HttpRequest.get(VIDEO_INFO.videoBaseUrl)
					.header("Referer", VIDEO_URL)
					.header("Range", "bytes=0-" + VIDEO_INFO.videoSize)
					.header("User-Agent", USER_AGENT)
					.execute();
			videoRes.writeBody(videoFile);
			System.out.println("--------------视频文件下载完成--------------");
		}


		// 下载音频
		File audioFile = new File(save_path + File.separator + VIDEO_INFO.videoName + "_audio.mp4");
		if (!audioFile.exists()){
			System.out.println("--------------开始下载音频文件--------------");
			HttpResponse audioRes = HttpRequest.get(VIDEO_INFO.audioBaseUrl)
					.header("Referer", VIDEO_URL)
					.header("Range", "bytes=0-" + VIDEO_INFO.audioSize)
					.header("User-Agent", USER_AGENT)
					.execute();
			audioRes.writeBody(audioFile);
			System.out.println("--------------音频文件下载完成--------------");
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
		System.out.println("--------------开始合并音视频--------------");
		String outFile = SAVE_PATH + File.separator + VIDEO_INFO.videoName + ".mp4";
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
			System.out.println("--------------音视频合并完成--------------");
			videoFile.delete();
			audioFile.delete();
		} catch (InterruptedException | IOException e) {
			System.err.println("音视频合并失败！");
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
