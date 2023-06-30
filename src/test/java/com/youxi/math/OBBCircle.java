package com.youxi.math;

import com.youxi.dataStructure.collisionTree.AABB;
import com.youxi.entity.Dir;
import com.youxi.entity.OBB2D;
import com.youxi.entity.Position;
import org.junit.Test;

public class OBBCircle {
	public static class  Direction {
		public static final int UP = 1; // 上
		public static final int DOWN = 2; // 下
		public static final int LEFT = 3; // 左
		public static final int RIGHT = 4; // 右
	}

	public float sqDistPointOBB(Position p, OBB2D b) {
		return 0;
	}


	public int pointOBBNearestSegment(Position p, Position center, Dir size, float angle) {
		// 转回来
		return pointAABBNearestSegment(p.sub(center).getRotateDir(-angle), size);
	}

	public int pointAABBNearestSegment(Position p, AABB aabb) {
		return pointAABBNearestSegment(p.sub(new Position(aabb.getMidX(), aabb.getMidY())), new Dir(aabb.getWidth() / 2, aabb.getHeight() / 2));
	}
	public int pointAABBNearestSegment(Position p, Dir size) {
		Dir min = new Dir(-size.getX(), -size.getZ());
		Dir max = size;
		int resultX = 0;
		int resultZ = 0;

		int upDownSegment = 0;
		int leftRightSegment = 0;
		if (p.getX() < min.getX()) {
			resultX = min.getX();
			leftRightSegment = Direction.LEFT;

		}
		if (p.getX() > max.getX()) {
			resultX = max.getX();
			leftRightSegment = Direction.RIGHT;
		}
		if (p.getZ() < min.getZ()) {
			resultZ = min.getZ();
			upDownSegment = Direction.DOWN;
		}
		if (p.getZ() > max.getZ()) {
			resultZ = max.getZ();
			upDownSegment = Direction.UP;
		}


		if (upDownSegment == 0 && leftRightSegment == 0) { // 最近点在多边形内

		} else { // 最近点在多边形外面
			if (upDownSegment > 0 && leftRightSegment > 0) {
				// 长宽比 往左右两边偏的时候长短了宽不变; 那就是值小了; 往上下两边偏的话就是长长了宽度短了值变大了
				double currRadio = 1.0 * size.getZ() / size.getX();

				int x = leftRightSegment == Direction.RIGHT ? max.getX() : min.getX();
				int y = upDownSegment == Direction.UP ? max.getZ() : min.getZ();
				double i = 1.0 *Math.abs(p.getZ() - y) / Math.abs(p.getX() - x);

				if (i < currRadio) {
					upDownSegment = 0;
				} else {
					leftRightSegment = 0;
				}
			}
		}
		return upDownSegment + leftRightSegment;
	}


	@Test
	public void test01() {
		/**
		 * 上 下 左 右 上左 上右 右上 右下 下右 下左 左下 左上
		 */


		Position center = new Position(500, 500);
		Dir size = new Dir(200, 100);
		int angle = 300;

		AABB aabb = new AABB(100, 100, new Dir(100, 50));
		Position p;

		System.out.println("点在中心点");
		p = new Position(100, 100);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb上方线上");
		p = new Position(100, 150);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb下方线上");
		p = new Position(100, 50);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb左边线上");
		p = new Position(0, 100);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb右边线上");
		p = new Position(200, 100);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点在右上方对角线那");
		p = new Position(200, 150);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb上方上");
		p = new Position(100, 170);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb下方上");
		p = new Position(100, 40);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb左边上");
		p = new Position(-100, 100);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb右边上");
		p = new Position(300, 100);
		System.out.println(pointAABBNearestSegment(p, aabb));



		System.out.println("点上aabb上方线上 左边");
		p = new Position(100, 150);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb下方线上");
		p = new Position(100, 50);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb左边线上");
		p = new Position(0, 100);
		System.out.println(pointAABBNearestSegment(p, aabb));

		System.out.println("点上aabb右边线上");
		p = new Position(200, 100);
		System.out.println(pointAABBNearestSegment(p, aabb));

	}
}
