package com.youxi.dataStructure.松散四叉树;

import com.youxi.dataStructure.collisionTree.AABB;

/**
 * https://blog.csdn.net/skillart/article/details/113170232
 */
public class QRect {
	public QPoint origin;
	public QPoint size;

	public QRect(QPoint origin, QPoint size) {
		this.origin = origin;
		this.size = size;
	}

	public static boolean intersects(QRect rect, QRect target) {
		if (rect.getYMin() > target.getYMax()) {
			return false;
		}
		if (rect.getYMax() < target.getYMin()) {
			return false;
		}
		if (rect.getXMax() < target.getXMin()) {
			return false;
		}
		if (rect.getXMin() > target.getXMax()) {
			return false;
		}
		return true;
	}

	public float getCenterX() {
		return this.origin.x + this.size.getX() * 0.5f;
	}

	public float getCenterY() {
		return this.origin.y + this.size.getY() * 0.5f;
	}

	public float getXMin() {
		return this.origin.x;
	}

	public float getYMin() {
		return this.origin.y;
	}

	public float getXMax() {
		return this.origin.x + this.size.getX();
	}

	public float getYMax() {
		return this.origin.y + this.size.getY();
	}
}
