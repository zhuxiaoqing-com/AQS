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
		MDir returnDir = new MDir();
		List<Position> simplexList = new ArrayList<>();

		//得到初始的方向
		returnDir.d = findFirstDirection(shape1, shape2);

		// 得到首个support点
		simplexList.add(support(shape1, shape2, returnDir.d));
		// 得到第二个方向
		returnDir.d = returnDir.d.negDir();

		while (true) {
			Position p = support(shape1, shape2, returnDir.d);
			// 沿着dir的方向 已经找不到能够跨越原点的support点了
			if (p.dot(returnDir.d.toPosition()) < 0) {
				return false;
			}
			simplexList.add(p);
			//单形体包含了原点
			if (simplexList.size() >= 3 && new ConvexPolygon(simplexList).inRegion(Position.ZERO)) {        //todo 单形体包含了原点 没有实现
				return true;
			}
			returnDir.d = findNextDirection(simplexList);
		}

	}

	private Dir findNextDirection(List<Position> simplexList) {
		if(simplexList.size() == 2) {
			// 计算原点到直线01的垂足
			Dir verticalVector = findVerticalVector(simplexList.get(0), simplexList.get(1));
			return Dir.ZERO.toPosition().sub(verticalVector.toPosition()).toDir();
		}
		if(simplexList.size() == 3) {
			Dir crossOnCA = findVerticalVector(simplexList.get(2), simplexList.get(0));
			Dir crossOnCB = findVerticalVector(simplexList.get(2), simplexList.get(1));
			if(crossOnCA.length() < crossOnCB.length()) {
				simplexList.remove(1);
				return Position.ZERO.sub(crossOnCA.toPosition()).toDir().normalize();
			}else {
				simplexList.remove(0);
				return Position.ZERO.sub(crossOnCB.toPosition()).toDir().normalize();
			}
		}
		// 不可能执行到这里
		return null;
	}


	private Dir findFirstDirection(Shape s1, Shape s2) {
		return s1.getCenter().sub(s2.getCenter()).toDir();
	}


	/**
	 * 取第一个方向上的最远的点 减去 相反方向的最远的点
	 */
	public Position support(Shape s1, Shape s2, Dir dir) {
		Position position = supportWrapper(s1, dir);
		Position subPos = supportWrapper(s2, dir.negDir());
		return position.sub(subPos);
	}

	public Position supportWrapper(Shape s1, Dir dir) {
		if (s1 instanceof OBB2D) {
			return support((OBB2D) s1, dir);
		}

		if (s1 instanceof Circle) {
			return support((Circle) s1, dir);
		}

		if (s1 instanceof ConvexPolygon) {
			return support((ConvexPolygon) s1, dir);
		}
		return null;
	}

	/**
	 * 原点 + dir*radius
	 *
	 * @param dir 已经标准化过了
	 */
	public Position support(Circle circle, Dir dir) {
		return circle.getCenter().add(dir.toPosition().multiply(circle.getRegionRadius()/1000f));
	}

	/**
	 * 所有顶点和dir进行点乘 投影在dir上最长的线段的顶点就是支撑点
	 *
	 * @param dir 已经标准化过了
	 */
	public Position support(OBB2D obb2D, Dir dir) {
		return support(obb2D.getPoints(), dir);
	}

	public Position support(ConvexPolygon polygon, Dir dir) {
		return support(polygon.getVertexs(), dir);
	}

	public Position support(List<Position> positionList, Dir dir) {
		long maxPosDot = Integer.MIN_VALUE;
		Position maxPos = null;
		for (Position position : positionList) {
			long dot = position.dot(dir.toPosition());
			if (dot > maxPosDot) {
				maxPosDot = dot;
				maxPos = position;
			}
		}
		return maxPos;
	}

	// 计算原点到直线ab的垂足
	public Dir findVerticalVector(Position a, Position b) {
		Position origin = Position.ZERO;
		Position ao = origin.sub(a);
		Position ab = b.sub(a);
		long dot = ao.dot(ab);

		long abLenSqr = a.getDirectDistanceSquareIgnoreY(b);

		double t = dot * 1.0d / abLenSqr;

		Position add = a.add(ab.multiply((float) t));
		return add.toDir().normalize();
	}

}

