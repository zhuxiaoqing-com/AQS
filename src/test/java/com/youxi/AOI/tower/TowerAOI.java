package com.youxi.AOI.tower;

import com.example.代码优化.design_mode.TowerAOI.Vector2f;

import java.util.*;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/2/3 10:40
 * @Description:
 */
public class TowerAOI {
	private EventEmitter eventEmitter;

	private int width;

	private int height;

	private int towerWidth;

	private int towerHeight;

	private Tower[][] towers;

	private int rangeLimit = 5;

	private int maxX;
	private int maxY;

	public TowerAOI(int width, int height, int towerWidth, int towerHeight, int rangeLimit) {
		this.width = width;
		this.height = height;
		this.towerWidth = towerWidth;
		this.towerHeight = towerHeight;
		if (rangeLimit > 0) {
			this.rangeLimit = rangeLimit;
		}
		this.init();
	}

	public void init() {
		int maxWidth = (int) Math.ceil(this.width / this.towerWidth);
		int maxHeight = (int) Math.ceil(this.height / this.towerHeight);
		this.maxX = maxWidth - 1;
		this.maxY = maxWidth - 1;

		// 初始化灯塔
		for (int i = 0; i < maxWidth; i++) {
			for (int j = 0; j < maxHeight; j++) {
				this.towers[i][j] = new Tower();
			}
		}
	}

	/**
	 * Get given type object ids from tower aoi by range and types
	 *
	 * @param vector2f 坐标
	 * @param range    范围
	 * @param types    类型 (player, monster, npc 等)
	 */
	public void getIdsByRange(Vector2f vector2f, int range, List<Integer> types) {
		if (!checkPos(vector2f) || range < 0 || range > rangeLimit) {
			return;
		}
	}

	private int tranPos(Vector2f vector2f) {
		return tranPos(vector2f.getX(), vector2f.getY());
	}

	private int tranPos(float x, float y) {
		int towerX = (int) Math.floor(x / this.towerWidth);
		int towerY = (int) Math.floor(y / this.towerHeight);
		return (towerX << 16) | towerY;
	}

	private int findTowerX(int pos) {
		return pos >> 16;
	}

	private int findTowerY(int pos) {
		return pos & 0x0000_FFFF;
	}

	private List<Integer> getPosLimit(int pos, int range) {
		int towerX = findTowerX(pos);
		int towerY = findTowerY(pos);
		int startX;
		int startY;
		int endX;
		int endY;

		if (towerX - range < 0) {
			startX = 0;
			endX = 2 * range;
		} else if (towerX + range > maxX) {
			endX = maxX;
			startX = maxX - 2 * range;
		} else {
			startX = towerX - range;
			endX = towerX + range;
		}

		if (towerY - range < 0) {
			startY = 0;
			endY = 2 * range;
		} else if (towerX + range > maxX) {
			endY = maxX;
			startY = maxX - 2 * range;
		} else {
			startY = towerY - range;
			endY = towerY + range;
		}

		startX = startX > 0 ? startX : 0;
		endX = endX > 0 ? endX : 0;
		startY = startY > 0 ? startY : 0;
		endY = endY > 0 ? endY : 0;

		List<Integer> points = new ArrayList<>();

		for (int i = startX; i <= endX; i++) {
			for (int j = startY; j <= endY; j++) {
				points.add(tranPos(i, j));
			}
		}
		return points;
	}


	/**
	 * Check if the pos is valid;
	 *
	 * @param vector2f vector2f
	 * @return
	 */
	private boolean checkPos(Vector2f vector2f) {
		if (vector2f == null) {
			return false;
		}
		if (vector2f.getX() < 0 || vector2f.getY() < 0 || vector2f.getX() >= width || vector2f.getY() >= height) {
			return false;
		}
		return true;
	}
}
























