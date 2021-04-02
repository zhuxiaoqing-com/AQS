package com.youxi.db;

import org.junit.Test;

import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/4/2 14:53
 * @Description:
 */
public class DbTest {

	@Test
	public void fun1() {
		Role111 roleClosure = new Role111();
		new Thread(()->{
			List<Integer> list = roleClosure.getList();
			while (true) {
				list.add(1);
				list.remove(0);
			}
		}).start();

		new Thread(()-> {
			GameSqlBuilder gameSqlBuilder = new GameSqlBuilder();
			Object[] objects = gameSqlBuilder.buildInsertParams(roleClosure, roleClosure.getClass());
		}).start();

		while (true) {

		}
	}
}
