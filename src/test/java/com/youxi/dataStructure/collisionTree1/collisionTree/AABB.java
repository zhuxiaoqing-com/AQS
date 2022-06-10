package com.youxi.dataStructure.collisionTree1.collisionTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/6/8 15:59
 * @Description: 这个矩形没有管角度
 */
public class AABB extends Entity {
	private int x; // 是矩形的左下角坐标
	private int y; // 是矩形的左下角坐标
	private int width; // 宽
	private int height; // 高

	private int minX; // 最小点
	private int midX; // 中心点
	private int maxX; // 最大点

	private int minY;
	private int midY;
	private int maxY;

	private boolean isValid;


	public AABB(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;

		this.x = x;
		this.y = y;

		this.minX = this.x;
		this.midX = this.minX + this.width / 2;
		this.maxX = this.minX + this.width;

		this.minY = this.y;
		this.midY = this.minY + this.height / 2;
		this.maxY = this.minY + this.height;

		this.isValid = true;
	}


	public void rePosition(int x, int y) {
		this.x = x;
		this.y = y;

		this.minX = this.x;
		this.midX = this.minX + this.width / 2;
		this.maxX = this.minX + this.width;

		this.minY = this.y;
		this.midY = this.minY + this.height / 2;
		this.maxY = this.minY + this.height;
	}

	public void reSize(int width, int height) {
		this.width = width;
		this.height = height;

		this.midX = this.minX + this.width / 2;
		this.maxX = this.minX + this.width;

		this.midY = this.minY + this.height / 2;
		this.maxY = this.minY + this.height;
	}

	/**
	 * 测试自身是否与rect矩形相交
	 */
	public boolean intersect(TreeRect rect) {
		return this.getMaxX() >= rect.getMinX() &&
				this.getMaxY() >= rect.getMinY() &&
				this.getMinX() <= rect.getMaxX() &&
				this.getMinY() <= rect.getMaxY();


	}


	/**
	 * 测试自身是否与rect矩形相交
	 */
	public boolean intersect(AABB rect) {
		return this.getMaxX() >= rect.getMinX() &&
				this.getMaxY() >= rect.getMinY() &&
				this.getMinX() <= rect.getMaxX() &&
				this.getMinY() <= rect.getMaxY();


	}


	/**
	 * 测试自身矩形是否被rect矩形包含
	 */
	public boolean beContain(TreeRect rect) {
		return rect.getMinX() <= this.getMaxX() &&
				rect.getMinY() <= this.getMaxY() &&
				rect.getMaxX() >= this.getMinX() &&
				rect.getMaxY() >= this.getMinY();
	}

	public int getIndex(CollisionTree tree, boolean checkIsInner) {
		int index = super.getIndex(tree, checkIsInner);
		if(index < 0) {
			return index;
		}

		TreeRect rect = tree.getRect();

		boolean rigth = getMinX() >= rect.getMidX();
		boolean left = getMaxX() <= rect.getMidX();
		boolean top = getMinY() >= rect.getMidY();
		boolean bottom = getMaxY() <= rect.getMidY();

		if (tree.getNodes().size() <= 0) {
			/*
			  自身没有孩子节点，rect属于自身
			 */
			return -1;
		}
		if (top) {
			if (rigth) {
				return 0;
			} else if (left) {
				return 1;
			}
		} else if (bottom) {
			if (left) {
				return 2;
			} else if (rigth) {
				return 3;
			}
		}
		/*
		 * 不单单属于某一个象限
		 */
		return -2;
	}


	/**
	 * 获取 rect 在哪几个象限中
	 */
	public List<Integer> getIndexs(CollisionTree tree) {
		List<Integer> indexs = super.getIndexs(tree);
		if(indexs != null) {
			return indexs;
		}

		TreeRect rect = tree.getRect();
		boolean rigth = getMinX() >= rect.getMidX();
		boolean left = getMaxX() <= rect.getMidX();
		boolean top = getMinY() >= rect.getMidY();
		boolean bottom = getMaxY() <= rect.getMidY();

		/**
		 * 0123 是四个象限
		 *
		 * 象限：
		 * 2   1
		 * 3   4
		 */
		ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

		if (!left) {
			//result.splice(1, 2);
			// 不在左边 删除象限 2,3
			result.remove(new Integer(2));
			result.remove(new Integer(3));
		}
		if (!rigth) {
			// 不在右边 删除象限 1,4
			//result.splice(3, 1);
			//result.splice(0, 1);
			result.remove(new Integer(1));
			result.remove(new Integer(4));
		}
		if (!top) {
			// 不在上边 删除象限 1,2
			//result.splice(0, 2);
			result.remove(new Integer(1));
			result.remove(new Integer(2));
		}
		if (!bottom) {
			// 不在下边 删除象限 3,4
			//result.splice(2, 2);
			result.remove(new Integer(3));
			result.remove(new Integer(4));
		}

		return result;
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getMinX() {
		return minX;
	}

	public int getMidX() {
		return midX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMidY() {
		return midY;
	}

	public int getMaxY() {
		return maxY;
	}

	public boolean isValid() {
		return isValid;
	}
}
