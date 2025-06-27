package com.example.common.selenium.Bzhan;

import com.youxi.util.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/*

<a href="https://upos-sz-mirrorkodo.bilivideo.com/upgcxcode/16/86/229108616/229108616-1-64.flv?
e=ig8euxZM2rNcNbNz7wdVhwdlhbhMhwdVhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8x
NEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6m
qF2fka1mqx6gqj0eN0B599M=&amp;uipk=5&amp;nbs=1&amp;deadline=1630582028&amp;gen=playurlv
2&amp;os=kodobv&amp;oi=1018278203&amp;trid=c5226036a6a64479a48d4ffad9565458u&amp;plat
form=pc&amp;upsig=8a4f397554f08e30f5ac8c884638231c&amp;uparams=e,uipk,nbs,deadline,ge
n,os,oi,trid,platform&amp;mid=0&amp;bvc=vod&amp;nettype=0&amp;orderid=0,3&amp;agrr=1&a
mp;logo=80000000" referrerpolicy="unsafe-url" download="温医大眼视光相关视频_哔哩哔哩_bi
libili" title="温医大眼视光相关视频_哔哩哔哩_bilibili" target="_blank" style="font-size:
 12px; padding: 6px; background-color: rgb(0, 161, 214); color: rgb(255, 255, 255);
  display: inline-block; position: fixed; top: 30%; left: 0px; z-index: 2147483647;">下载视频 12M</a>

 */

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/9/1 16:45
 * @Description:
 */
public class BzhanVideo {
	private static final Logger logger = LoggerFactory.getLogger(BzhanVideo.class);

	static 	ChromeDriver driver;
	static Random random = ThreadLocalRandom.current();

	private String downloadsPath =  "E://ceshi";


	List<String> windows = new ArrayList<>();

	// 等待重命名的文件 k:等待换的名字 v:要换的新名字
	Map<String, String> waitRenameFile = new HashMap<>();
	Set<String> alreadyDownVideo = new HashSet<>();


	static {
		System.setProperty("webdriver.chrome.driver", "F:\\mh\\game_server-develop\\AQS\\src\\test\\resources\\driver\\chromedriver.exe");
		File file = new File("E:/ceshi/11");
		file.mkdir();
		System.setProperty("download.default_directory", "E:/ceshi/11");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");///此处的端口号一定要ChromeDebug模式启动的端口号相同
		driver = new ChromeDriver(options);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public BzhanVideo() throws Exception {
	}

	public static void main(String[] args) throws Exception {
		new BzhanVideo().climb();
	}

	public void climb() throws Exception {
		/*ChromeOptions options = new ChromeOptions();
		this.driver = new ChromeDriver();*/
		search();


		pageVideo();
		// 进行下一页
	}

	private void pageVideo() throws Exception {
		windows.add(driver.getWindowHandle());
		// 找到视频
		List<WebElement> videos = driver.findElements(By.className("img-anchor"));
		for (WebElement element : videos) {
			if (element.getTagName().equals("a")) {
				element.click();
				// 切换到最新页面
				nextWindows();
				videoDownload();
				driver.close();
				// 切换回上一个页面
				driver.switchTo().window(windows.get(1));
			}
		}
	}

	private void search() throws InterruptedException {
		windows.add(driver.getWindowHandle());
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		List<WebElement> searchInput = driver.findElements(By.className("nav-search-keyword"));
		searchInput.get(0).clear();
		searchInput.get(0).sendKeys("视光");
		List<WebElement> searchBtn = driver.findElements(By.className("nav-search-btn"));
		// 搜索
		searchBtn.get(0).click();
		// 切换到最新页面
		nextWindows();
		System.out.println("................"+driver.getTitle());
	}

	private void nextWindows() throws InterruptedException {
		Set<String> windowHandles = driver.getWindowHandles();
		windowHandles.removeAll(windows);
		driver.switchTo().window(windowHandles.iterator().next());
		waitTime();
	}

	private void videoDownload() throws Exception {
		List<WebElement> multi_page = driver.findElements(By.id("multi_page"));
		// 单p
		if (multi_page.isEmpty()) {
			singleVideoDownload(-1);
			return;
		}

		// 如果有多p
		List<WebElement> elements = driver.findElements(By.className("list-box"));
		List<WebElement> a = elements.get(0).findElements(By.tagName("a"));
		for (int i = 0; i < a.size(); i++) {
			nextP(i);
		}
	}

	private void nextP(int index) throws Exception {
		List<WebElement> elements = driver.findElements(By.className("list-box"));
		List<WebElement> a = elements.get(0).findElements(By.tagName("a"));

		a.get(index).click();
		driver.navigate().refresh();
		// 等待新窗口打开
		waitTime();
		singleVideoDownload(index);
	}

	private void singleVideoDownload(int pIndex) throws Exception {

		List<WebElement> download = driver.findElements(By.partialLinkText("下载视频"));
		WebElement webElement = download.get(0);
		String href = webElement.getAttribute("href");
		String substring = href.substring(0, href.indexOf("?"));
		String unique = substring.substring(substring.lastIndexOf("/") + 1);

		if(pIndex >= 0) {
			logger.info("下载多个视频 title:{} ：p:{} 视频唯一id:{}", driver.getTitle(), pIndex + 1, unique);
		}else {
			logger.info("下载单个视频 title:{} 视频唯一id:{}", driver.getTitle(), unique);
		}

		webElement.click();

		// 等待新窗口打开
		waitTime();
	}


	private String findVideoUnique() {
		String unique = "";
		List<WebElement> download = driver.findElements(By.partialLinkText("下载视频"));
		for (WebElement webElement : download) {
			//a(webElement.getAttribute("href"));
			String href = webElement.getAttribute("href");
			String substring = href.substring(0, href.indexOf("?"));
			unique = substring.substring(substring.lastIndexOf("/") + 1);
			break;
		}
		return unique;
	}

	/**
	 * 设置默认下载路径
	 * @param path
	 * @return
	 */
	private static DesiredCapabilities setDownloadsPath(String path) {
		Map<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.default_directory", path);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		return caps;
	}

/*	private void a(String link) throws IOException {
		URL url = new URL(link);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		//为了模仿Selenium用户，您可能需要复制Selenium拥有的cookie（例如，如果您正在测试需要登录的网站）。
		Set<Cookie> cookies = driver.manage().getCookies();
		StringBuilder cookieString = new StringBuilder();
		for (Cookie cookie : cookies) {
			cookieString.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
		}
		httpURLConnection.addRequestProperty("Cookie", cookieString.toString());
//		然后，您可以HttpURLConnection.getInputStream()用来将文件内容写入您的首选位置。
		try (InputStream in = httpURLConnection.getInputStream()) {
			Files.copy(in, new File("E:/ceshi/path/to/file.ext").toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		}
	}*/

	private void setAttribute(WebElement element, String attrName, String value) {
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		jsDriver.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, attrName, value);
	}

	private void waitTime() throws InterruptedException {
		Thread.sleep(RandomUtil.random(5000, 10000));
	}
}
