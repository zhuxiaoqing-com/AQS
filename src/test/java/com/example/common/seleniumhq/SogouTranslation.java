package com.example.common.seleniumhq;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/7/19 17:07
 * @Description: 搜狗翻译
 */
public class SogouTranslation {
	WebDriver driver = new ChromeDriver();

	static {
		System.setProperty("webdriver.chrome.driver", "F:\\mh\\game_server-develop\\AQS\\src\\test\\resources\\driver\\chromedriver.exe");
	}


	@Test
	public void test01() {
		try {
			selectFile();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void selectFile() throws InterruptedException {
		String path = "https://fanyi.sogou.com/document";
		driver.get(path);

		// 等待登陆
		waitLogin();
		System.out.println("登陆成功！！！");

		//定位上传按钮， 添加本地文件
		driver.findElement(By.name("file")).sendKeys("D:\\upload_file.txt");

	}

	private void waitLogin() {
		while (isLogin()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isLogin() {
		WebElement login = driver.findElement(By.className("login"));
		if (login == null) {
			return false;
		}
		return !login.getText().equals("登陆");
	}
}
