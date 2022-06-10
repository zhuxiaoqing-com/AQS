package com.youxi.dataStructure.collisionTree1.collisionTree;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/6/8 15:59
 * @Description: 这个矩形没有管角度
 */
public abstract class Entity {
	private int x; // 是矩形的左下角坐标
	private int y; // 是矩形的左下角坐标
	private boolean isValid;


	public Entity(int x, int y) {
		this.x = x;
		this.y = y;

		this.isValid = true;
	}


	public abstract void rePosition(int x, int y);

	public abstract void reSize(int width, int height);

	/**
	 * 测试自身是否与rect矩形相交
	 * 包含也是相交
	 */
	public abstract boolean intersect(TreeRect rect);

	/**
	 * 测试自身矩形是否被rect矩形包含
	 */
	public abstract boolean beContain(TreeRect rect);

	public int getIndex(CollisionTree tree, boolean checkIsInner) {
		TreeRect rect = tree.getRect();
		if (!this.beContain(rect)) {
			if (!this.intersect(rect)) {
				/**
				 * 不相交
				 */
				return -4;
			}
			/**
			 * 相交
			 */
			return -3;
		}

		return 0;
	}


	/**
	 * 获取 rect 在哪几个象限中
	 */
	public List<Integer> getIndexs(CollisionTree tree) {
		TreeRect rect = tree.getRect();
		if (!intersect(rect)) {
			return Collections.emptyList();
		}
		return null;
	}

	public void destroy() {
		this.isValid = false;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isValid() {
		return isValid;
	}
}
