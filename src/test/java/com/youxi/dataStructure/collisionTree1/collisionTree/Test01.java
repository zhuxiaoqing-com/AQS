package com.youxi.dataStructure.collisionTree1.collisionTree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/6/10 10:11
 * @Description:
 */
public class Test01 {
	@Test
	public void test01() {
		int w = 1000;
		int h = 500;
		ArrayList<Entity> rectsArr = new ArrayList<>();
		CollisionTree tree;

		tree = new CollisionTree(new TreeRect(0, 0, w, h), 0, null);

		// 随机创建
		for (int i = 0; i < 5000; i++) {
			rectsArr.add(new AABB((int) (Math.random() * (w - 20)), (int) Math.floor(Math.random() * (h - 20)), 20, 20));
		}

		// 添加到树
		rectsArr.forEach(tree::insert);

		// 移动
	//	rectsArr.forEach(rect -> rect.rePosition(600, 50));

		// 更新树
	//	tree.refresh();

		System.out.printf("碰撞体： %s 个", rectsArr.size());
		System.out.println();

		int m = 0;
		long start = System.currentTimeMillis();
		for (Entity rect : rectsArr) {
			for (Entity rect1 : rectsArr) {
				((AABB)rect1).intersect((AABB)rect);
				m++;
			}
		}
		long end = System.currentTimeMillis();
		System.out.printf("普通 time: %s  m: %s 次", end - start, m);
		System.out.println();


		int n = 0;
		start = System.currentTimeMillis();
		for (Entity rect : rectsArr) {
			List<Entity> collisions = tree.getCollisions(rect);
			for (Entity collision : collisions) {
				((AABB)collision).intersect((AABB)rect);
				n++;
			}
		}
		end = System.currentTimeMillis();
		System.out.printf("碰撞树 time: %s  n: %s 次", end - start, n);
		System.out.println();

		System.out.printf("运算次数减少 %s 百分比", (1 - n*1.0d / m) * 100);
		System.out.println();
	}
}
