package com.youxi.dataStructure.collisionTree;

import com.youxi.entity.Dir;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/6/10 16:05
 * @Description:
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

		super.rect = new Rect(x, y, width, height);
	}

	public AABB(int midX, int midY, Dir size) {

		this.width = size.getX()*2;
		this.height = size.getZ() * 2;

		this.x = midX - size.getX();
		this.y = midY - size.getZ();

		this.minX = this.x;
		this.midX =midX;
		this.maxX = this.minX + this.width;

		this.minY = this.y;
		this.midY = midY;
		this.maxY = this.minY + this.height;

		this.isValid = true;

		super.rect = new Rect(x, y, width, height);
	}
	/**
	 * 测试自身矩形是否与rect矩形相交
	 */
	public boolean intersect(AABB rect) {
		return rect.maxX >= this.minX &&
				rect.maxY >= this.minY &&
				rect.minX <= this.maxX &&
				rect.minY <= this.maxY;
	}


	/**
	 * 测试自身矩形是否包含rect矩形
	 */
	public boolean contain(AABB rect) {
		return this.minX <= rect.maxX &&
				this.minY <= rect.maxY &&
				this.maxX >= rect.minX &&
				this.maxY >= rect.minY;
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
