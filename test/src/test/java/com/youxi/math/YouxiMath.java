package com.youxi.math;

import com.example.demo1.util.vo.Vector2f;
import com.youxi.entity.Position;
import org.junit.Test;

import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/12/29 14:22
 * @Description:
 */
public class YouxiMath {

	/**
	 * 先判断线段的两个点是不是有一个在圆内;在园内就相交
	 * <p>
	 * 如果都不在圆内;如果圆心和线段的距离小于等于半径，
	 * 并且线段的两端不在圆心的同一侧;
	 */
	public boolean circleSegmentCrossing(int radius, Vector2f circleCenter, Vector2f segmentStart, Vector2f segmentEnd) {
		// 判断两个点是否在圆内
		double startDistanceSquare = findDistanceSquare(circleCenter, segmentStart);
		double endDistanceSquare = findDistanceSquare(circleCenter, segmentEnd);
		// 线段在圆内3
		if (startDistanceSquare < radius && endDistanceSquare < radius) {
			return true;
		}
		// 线段和圆相交
		if (startDistanceSquare > radius && endDistanceSquare < radius) {
			return true;
		}
		if (startDistanceSquare < radius && endDistanceSquare > radius) {
			return true;
		}

		// 线段和圆心的距离大于 半径
		if (pointToLineDistance(segmentStart, segmentEnd, circleCenter) > radius) {
			return false;
		}

		// 叉乘判断并且线段的两端在不在圆心的同一侧
		double v = cross(segmentStart, circleCenter) * cross(segmentEnd, circleCenter);
		return v < 0;
	}

	private double findDistanceSquare(Vector2f v1, Vector2f v2) {
		double v = Math.pow(v2.getX() - v1.getX(), 2) + Math.pow(v2.getY() - v1.getY(), 2);
		return Math.abs(v);
	}

	/**
	 * https://jingyan.baidu.com/article/cbcede0760944802f40b4d98.html
	 * https://blog.csdn.net/ardo_pass/article/details/80324754
	 * <p>
	 * 1、海伦公式计算点到直线的距离
	 * p(周长的1/2) = 1/2 * (a+b+c)
	 * 海伦公式  S(面积) = √(p(p-a)(p-b)(p-c));
	 */
	private double pointToLineDistance(Vector2f linePoint1, Vector2f linePoint2, Vector2f point) {
		double a = Math.sqrt(findDistanceSquare(linePoint1, linePoint2));
		double b = Math.sqrt(findDistanceSquare(linePoint1, point));
		double c = Math.sqrt(findDistanceSquare(linePoint2, point));

		double space = 0;
		// 点在线段上
		if (c + b == a) {
			return space;
		}

		// 不是一个线段，是一个点
		if (a <= 0.0001) {
			return b;
		}

		double p = (a + b + c) / 2; // 半周长

		double s = Math.sqrt(p * (p - a) * (p - b) * (p - c)); // 海伦公式求面积;

		double v = 2 * s / a;// // 返回点到线的距离（利用三角形面积公式求高）
		return v;
	}

	/**
	 * 点乘判断方向
	 *
	 * @return
	 */
	public static double cross(Vector2f v1, Vector2f v2) {
		return v1.getX() * v2.getY() - v1.getY() * v2.getX();
	}

	public static double cross(Vector2f point, Vector2f v1, Vector2f v2) {
		return cross(v1.sub(point), v2.sub(point));
	}
	public static double cross(Position v1, Position v2) {
		return v1.getX() * v2.getZ() - v1.getZ() * v2.getX();
	}


	/**
	 * 线段和多边形相交
	 * 1、线段的两个点都在凸多边形内
	 * 2、线段的其中一个点在凸多边形内;
	 * 3、线段是否和其中一条边相交
	 */
	public boolean segmentPolygonCrossing(List<Vector2f> polygon, Vector2f segmentStart, Vector2f segmentEnd) {
		return true;
	}

	/**
	 * https://zhuanlan.zhihu.com/p/160112849
	 * 点p是否在线段AB上
	 * 用点乘/两个线段的长度可以求出 cos值
	 *
	 * 方法一: 先检测AB两点是否重合, 以及p点和AB两点是否重合, 判断 [公式] 和 [公式] 的夹角余弦值是否是-1
	 *
	 * @param start
	 * @param end
	 * @param point
	 * @return
	 */
	public boolean pointSegment(Position start, Position end, Position point) {
		Position pointStart = point.sub(start);
		Position endStart = end.sub(start);
		Position pointEnd = point.sub(end);

		int length = pointStart.length();
		int length1 = pointEnd.length();
		int length2 = endStart.length();
		return length + length1 == length2;
	}

	@Test
	public void test01() {
		Position start = new Position(49861, 84411);
		Position end = new Position(56261, 85811);
		Position point = new Position(50176, 80613);
		boolean b1 = pointSegment(start, end, point);
		System.out.println(b1);
		//circleSegmentCrossing(500, new Vector2f(108.19f, 23.38f), , )
	}

	@Test
	public void test02() {
		Position start = new Position(49, 84);
		Position end = new Position(56, 85);
		Position point = new Position(50, 80);
		boolean b1 = pointSegment(start, end, point);
		System.out.println(b1);
		//circleSegmentCrossing(500, new Vector2f(108.19f, 23.38f), , )
	}

}
