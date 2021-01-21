package com.youxi.math;

import com.example.代码优化.design_mode.TowerAOI.Vector2f;
import com.youxi.sssh.fsm.Vector3f;
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
		// 线段在圆内
		if (startDistanceSquare > radius && endDistanceSquare > radius) {
			return true;
		}
		// 线段和圆相交
		if (startDistanceSquare > radius || endDistanceSquare > radius) {
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
	private double cross(Vector2f v1, Vector2f v2) {
		return v1.getX() * v2.getY() - v1.getY() * v2.getX();
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


	@Test
	public void test01() {
		//circleSegmentCrossing(500, new Vector2f(108.19f, 23.38f), , )
	}
}
