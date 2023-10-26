package com.youxi.math.GJK;

import com.youxi.entity.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GJKTest02 {

	@Test
	public void test01() {

	}


	/**
	 * <a href="https://blog.csdn.net/you_lan_hai/article/details/108293780">...</a>
	 *
	 * @param shape1
	 * @param shape2
	 * @return
	 */
	public boolean GJK(Shape shape1, Shape shape2) {
		ModifyDir returnDir = new ModifyDir();
		List<Position> simplexList = new ArrayList<>();

		//得到初始的方向
		returnDir.d = findFirstDirection();

		// 得到首个support点
		simplexList.add(support(returnDir.d));
		// 得到第二个方向
		returnDir.d = returnDir.d.negDir();

		while (true) {
			Position p = support(returnDir.d);
			// 沿着dir的方向 已经找不到能够跨越原点的support点了
			if (p.dot(returnDir.d.toPosition()) < 0) {
				return false;
			}
			simplexList.add(p);
			//单形体包含了原点
			//todo
			returnDir.d = findNextDirection();
		}

	}

	private Dir findNextDirection() {
		return null;
	}

	private Position support(Dir d) {
		return null;
	}

	private Dir findFirstDirection() {
		return null;
	}

}








