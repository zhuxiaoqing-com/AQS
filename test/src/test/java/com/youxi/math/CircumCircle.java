package com.youxi.math;

import com.example.demo1.util.vo.Vector2f;
import org.junit.Test;

public class CircumCircle {

	@Test
	public void test01() {
		Vector2f v1 = new Vector2f(111, 333);
		Vector2f v2 = new Vector2f(333, 544);
		Vector2f v3 = new Vector2f(213, 442);
		circumCircle1(v1,v2,v3);
		circumCircle2(v1,v2,v3);
	}

	public void circumCircle1(Vector2f v1, Vector2f v2, Vector2f v3) {
		float x1 = v1.getX();
		float y1 = v1.getY();
		float x2 = v2.getX();
		float y2 = v2.getY();
		float x3 = v3.getX();
		float y3 = v3.getY();
		float x0 = ((y2 - y1) * (y3 * y3 - y1 * y1 + x3 * x3 - x1 * x1) - (y3 - y1) * (
				y2 * y2 - y1 * y1 + x2 * x2 - x1 * x1)) /
				(2 * (x3 - x1) * (y2 - y1) - 2 * (x2 - x1) * (y3 - y1));

		float y0 = ((x2 - x1) * (x3 * x3 - x1 * x1 + y3 * y3 - y1 * y1) - (x3 - x1) * (
				x2 * x2 - x1 * x1 + y2 * y2 - y1 * y1)) /
				(2 * (y3 - y1) * (x2 - x1) - 2 * (y2 - y1) * (x3 - x1));

		float r = (float) Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
		System.out.println("----------circumCircle1---------");
		System.out.println(x0);
		System.out.println(y0);
		System.out.println(r);
	}


	public void circumCircle2(Vector2f v1, Vector2f v2, Vector2f v3) {
		float x1 = v1.getX();
		float y1 = v1.getY();
		float x2 = v2.getX();
		float y2 = v2.getY();
		float x3 = v3.getX();
		float y3 = v3.getY();

		float cp = v2.sub(v1).cross(v3.sub(v1));

		float v1Sq = v1.dot(v1);
		float v2Sq = v2.dot(v2);
		float v3Sq = v3.dot(v3);

		float x0 = (v1Sq * (v2.y - v3.y) + v2Sq * (v3.y - v1.y) + v3Sq * (v1.y - v2.y)) / (2 * cp);
		float y0 = (v1Sq * (v3.x - v2.x) + v2Sq * (v1.x - v3.x) + v3Sq * (v2.x - v1.x)) / (2 * cp);
		System.out.println("----------circumCircle2---------");
		System.out.println(x0);
		System.out.println(y0);
	}
}
