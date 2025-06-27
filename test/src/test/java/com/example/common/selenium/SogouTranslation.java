package com.example.common.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Date;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/7/19 17:07
 * @Description: 搜狗翻译
 */
public class SogouTranslation {
	WebDriver driver;

	static {
		System.setProperty("webdriver.chrome.driver", "F:\\mh\\game_server-develop\\AQS\\src\\test\\resources\\driver\\chromedriver.exe");
	}

	{
		// driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");///此处的端口号一定要ChromeDebug模式启动的端口号相同
		this.driver = new ChromeDriver(options);
	}

	public void sogoufanyi(String pathName) throws InterruptedException {
		/*String path = "https://fanyi.sogou.com/document";
		driver.get(path);
*/
		// 等待登陆
		//waitLogin();
		//Thread.sleep(1000);
		//填充翻译文件
		paddingFile(pathName);
		Thread.sleep(1000);
		// 点击翻译按钮
		clickTranslateBtn();
		Thread.sleep(1000);
		// 等待翻译
		waitTranslate();
		Thread.sleep(1000);
		// 下载
		download();
		System.out.println("开始下载！！！filename: " + pathName + "currMill:" + new Date());
		Thread.sleep(5000);
		// 翻译下一个
		translateNext();
		Thread.sleep(1000);
	}

	/**
	 * 翻译下一篇
	 */
	private void translateNext() {
		List<WebElement> downloadBtn = driver.findElements(By.className("btn-plain"));
		for (WebElement webElement : downloadBtn) {
			if (webElement.getAttribute("class").equals("btn-plain btn-upload ")) {
				webElement.click();
			}
		}
	}

	private void download() throws InterruptedException {
		List<WebElement> downloadBtn = driver.findElements(By.className("btn-primary"));
		if (downloadBtn.isEmpty()) {
			System.out.println("下载失败！！！！");
			return;
		}
		for (WebElement webElement : downloadBtn) {
			if (webElement.getAttribute("class").equals("btn-primary flexible")) {
				// 点击下载按钮
				webElement.click();
			}
		}

		// 遍历下载文档框
		List<WebElement> selectDiv = driver.findElements(By.className("trans-select"));
		if (!selectDiv.isEmpty()) {
			List<WebElement> div = selectDiv.get(0).findElements(By.tagName("div"));
			for (WebElement webElement : div) {
				// 将所有没选中的全部选中
				if (webElement.getAttribute("class").equals("")) {
					webElement.click();
				}
				// 等待一秒 以便其可以全部选中 (不等待不能全部选中)
				Thread.sleep(1000);
			}
		}

		// 将其全部下载
		List<WebElement> downloadBtnInters = driver.findElements(By.className("btn-primary"));
		for (WebElement downloadBtnInter : downloadBtnInters) {
			if (downloadBtnInter.getAttribute("class").equals("btn-primary")) {
				downloadBtnInter.click();
			}
		}

		// 关闭弹窗
		List<WebElement> title = driver.findElements(By.className("close"));
		for (WebElement element : title) {
			if (element.getAttribute("title").equals("关闭")) {
				element.click();
			}
		}
	}

	private void waitTranslate() {
		while (!isTranslateFinish()) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	private boolean isTranslateFinish() {
		boolean success = true;
		List<WebElement> waits = driver.findElements(By.className("btn-plain"));
		for (WebElement wait : waits) {
			if (wait.getText().equals("取消翻译")) {
				success = false;
				break;
			}
		}

		List<WebElement> waits2 = driver.findElements(By.className("btn-primary"));
		for (WebElement webElement : waits2) {
			if (webElement.getAttribute("class").equals("btn-primary flexible loading disabled")) {
				success = false;
				break;
			}
		}

		return success;
	}

	private void clickTranslateBtn() {
		// 点击翻译按钮
		List<WebElement> translateBtn = driver.findElements(By.className("btn-primary"));
		for (WebElement webElement : translateBtn) {
			if (webElement.getAttribute("class").equals("btn-primary medium")) {
				webElement.click();
			}
		}
	}

	// 填充翻译文件
	private void paddingFile(String pathName) {
		List<WebElement> upload_files = driver.findElements(By.id("upload_file"));
		if (!upload_files.isEmpty()) {
			upload_files.get(0).sendKeys(pathName);
		}
	}

	private void waitLogin() {
		while (!isLogin()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isLogin() {
		List<WebElement> login = driver.findElements(By.className("login"));
		if (login.isEmpty()) {
			return false;
		}
		boolean success = !login.get(0).getText().equals("登录");
		return success;
	}


	private void setAttribute(WebElement element, String attrName, String value) {
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		jsDriver.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, attrName, value);
	}

}
