package com.example.demo4;

import com.alibaba.fastjson.JSON;
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
		HashMap<Integer, Object> map = find(1000);
		System.out.println(map);
	}

	public static void main(String[] args) {
		HashMap<Integer, Object> totalMap = new HashMap<>();
		int num =1;
		while (true) {
		HashMap<Integer, Object> map = find(100000);
			totalMap.putAll(map);
			if(map.size() < 100000 || num++>=0) {
				break;
			}
		}
		System.out.println(totalMap);
	}

	public static HashMap<Integer, Object> find(int num) {
		int threadNum = (num + SPAN - 1) / SPAN;
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);

		ArrayList<Future<Integer>> objects = new ArrayList<>();
		for (int i = 0; i < num; i += SPAN) {
			int finalI = i;
			Future<Integer> submit = executor.submit(() -> finalI);
			objects.add(submit);
		}
		executor.shutdown();
		HashMap<Integer, Object> map = new HashMap<>(num);
		for (Future<Integer> object : objects) {
			try {
				map.put(object.get(), object.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}













