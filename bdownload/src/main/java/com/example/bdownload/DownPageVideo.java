package com.example.bdownload;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/9/2 19:48
 * @Description:
 */
public class DownPageVideo {

	public static final Logger logger = LoggerFactory.getLogger(DownPageVideo.class);

	private int num = 1;
	public String searchAddr = "https://search.bilibili.com/all?keyword=";

	public String pageParam = "&page=";

	public void a(String keyWord) throws Exception {
		HttpResponse res = HttpRequest.get(findSearchAddr(keyWord, 1)).timeout(2000).execute();
		String html = res.body();
		Document document = Jsoup.parse(html);
		int lastPage = findLastPage(document);
		for (int i = 0; i < lastPage; i++) {
			downSinglePage(keyWord, i);
		}
		logger.info("已经全部下载完毕！！！keyword:{} pageNum:{}", keyWord, lastPage);
	}

	private void downSinglePage(String keyWord, int pageNum) throws Exception {
		HttpResponse res = HttpRequest.get(findSearchAddr(keyWord, pageNum)).timeout(2000).execute();
		String html = res.body();
		Document document = Jsoup.parse(html);

		List<String> videoNames = findVideoNames(document);
		for (String videoName : videoNames) {
			// 如果已经下载过了
			if(CheckHistory.alreadyDownloaded(videoName)) {
				continue;
			}

			if (CfgBean.downVideoNum > 0 && CfgBean.waitTimeMinuteMin > 0) {
				// 每下载10个视频休息3到5分钟
				if (num % CfgBean.downVideoNum == 0) {
					Thread.sleep(random(CfgBean.waitTimeMinuteMin * 60 * 1000, CfgBean.waitTimeMinuteMax * 60 * 1000));
				}
			}
			Download download = new Download(videoName);
			download.parser();
			num++;
		}
	}


	private List<String> findVideoNames(Document document) {
		ArrayList<String> videosName = new ArrayList<>();
		Elements elementsByClass = document.getElementsByClass("video-item matrix");
		for (Element byClass : elementsByClass) {
			String href = byClass.getElementsByTag("a").attr("href");
			String name = href.substring(href.lastIndexOf("/") + 1, href.indexOf("?"));
			videosName.add(name);
		}
		return videosName;
	}

	private int findLastPage(Document document) {
		Elements elementsByClass = document.getElementsByClass("page-item last");
		String button = elementsByClass.get(0).getElementsByTag("button").get(0).text();
		System.out.println(button);
		return Integer.parseInt(button);
	}

	private String findSearchAddr(String keyWord, int pageNum) throws Exception {
		String encode = URLEncoder.encode(keyWord, "utf-8");
		if (pageNum <= 1) {
			return searchAddr + encode;
		}
		return searchAddr + keyWord + pageParam + pageNum;
	}

	private static int random(int min, int max) {
		if (min >= max) {
			return min;
		}
		return ThreadLocalRandom.current().nextInt(max - min + 1) + min;
	}
}
