package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.example.代码优化.design_mode.TowerAOI.Vector2f;
import com.youxi.building.Misc;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.models.auth.In;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test15 {
	public final static int SPAN = 10000;

	@Test
	public void test01() {
		short changeType = 0x00;
		System.out.println(changeType);
	}

	public static void find(int num) {
		int threadNum = (num + SPAN - 1) / SPAN;
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);

		for (int i = 0; i < num; i += SPAN) {
			executor.execute(() -> {
				while (true) {
					// todo 在线程里面利用  SPAN 让每个线程都取不同的区间
					// todo 插入到 阻塞队列; 如果阻塞队列满了 会自动阻塞住的;
				}
			});
		}

		executor.shutdown();
		// todo 如果查询完了  可以插入阻塞队列一个特殊标识  标识 已经阻塞队列已经没有东西了;
	}



	@Test
	public void test02() {

	}

	private float dot(Vector2f v1, Vector2f v2) {
		return v1.getX() * v2.getX() + v1.getY() * v2.getY();
	}
}













