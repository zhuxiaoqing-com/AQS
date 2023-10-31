package com.youxi.math.GJK;

import com.youxi.entity.*;
import com.youxi.util.MathExt;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GJKTest03 {

	@Test
	public void test01() {

	}


	/**
	 * https://blog.csdn.net/you_lan_hai/article/details/108298767
	 * <p>
	 * 计算多边形之间的最近距离，才是GJK算法原本的目的。只有两个多边形不相交，计算最近距离才有效。如果相交，则最近距离无效，但是可以使用EPA算法要计算碰撞
	 * <p>
	 * 该算法与上一章的GJK碰撞检测算法很相似，但有两个细微的差别：
	 * 1.选择下次的迭代方向不同。上一章用的是原点到的垂线作为迭代方向；本章用的是原点到的最近向量，作为迭代方向。注意，原点到线段的最近距离不一定就是垂足，可能是线段的某个端点
	 * 2. 不发生碰撞的结束条件不同。上一章迭代算法最终结束时，可能是一个不包含原点的三角形，为了得到差集最近边，我们可以舍弃掉距离原点较远的那个边。
	 * 这里我们让算法多迭代一次，恰好可以舍弃掉那个点，同时会额外会产生一个重复的support点。因此只要发现support点位置重叠了，就表明迭代算法可以结束了。
	 *
	 * @param shape1
	 * @param shape2
	 * @return
	 */
	public boolean GJK(Shape shape1, Shape shape2) {
		ModifyDir returnDir = new ModifyDir();
		List<Position> simplexList = new ArrayList<>();
		List<Position> aList = new ArrayList<>();
		List<Position> bList = new ArrayList<>();

		//得到初始的方向
		returnDir.d = findFirstDirection(shape1, shape2);

		// 得到首个support点
		simplexList.add(support(shape1, shape2, returnDir.d, aList, bList));
		// 得到第二个方向
		returnDir.d = returnDir.d.negDir();
		// 得到第二个support点
		simplexList.add(support(shape1, shape2, returnDir.d, aList, bList));

		Position closestPointToOrigin = getClosestPointToOrigin(simplexList.get(0), simplexList.get(1));
		returnDir.d = closestPointToOrigin.toDir().negDir(); // 这里取反其实就是 origin-该方向;
		while (true) {
			Position p = support(shape1, shape2, returnDir.d, aList, bList);
			// 新点与之前的点重合了。也就是沿着dir的方向，已经找不到更近的点了。
			if (simplexList.get(0).getDistanceIgnoreY(p) <= 0.0001f ||
					simplexList.get(1).getDistanceIgnoreY(p) <= 0.0001f
			) {
				break;
			}

			simplexList.add(p);
			//单形体包含了原点 已经包含了原点 已经重叠了 得使用epa才能计算最近点了
			if(new ConvexPolygon(simplexList).inRegion(Position.ZERO)) { 		//todo 单形体包含了原点 没有实现
				return true;
			}
			returnDir.d = findNextDirection(simplexList);
		}

		// 计算最近点
		ComputeClosetPoint(simplexList, aList, bList);
		return false;
	}

	private void ComputeClosetPoint(List<Position> simplexList, List<Position> aList, List<Position> bList) {
		/*
		 *  L = AB，是Minkowski差集上的一个边，同时构成A、B两点的顶点也来自各自shape的边。
		 *  E1 = Aa - Ba，E2 = Ab - Bb
		 *  则求两个凸包的最近距离，就演变成了求E1和E2两个边的最近距离。
		 *
		 *  设Q点是原点到L的垂点，则有:
		 *      L = B - A
		 *      Q · L = 0
		 *  因为Q是L上的点，可以用r1, r2来表示Q (r1 + r2 = 1)，则有: Q = A * r1 + B * r2
		 *      (A * r1 + B * r2) · L = 0
		 *  用r2代替r1: r1 = 1 - r2
		 *      (A - A * r2 + B * r2) · L = 0
		 *      (A + (B - A) * r2) · L = 0
		 *      L · A + L · L * r2 = 0
		 *      r2 = -(L · A) / (L · L)
		 *
		 *   r1 = 1-r2
		 *  Qa =  Aa * r1 + Ba * r2
		 *  Qb = Ab * r1 + Bb * r2
		 *
		 *
		 * Qa a凸多边形的最近点
		 * Qb b凸多边形的最近点
		 *
		 * (A * r1 + B * r2)
		 * (A.a-A.b) * r1 + (B.a-B.b) * r2
		 * A.a*r1 - A.b*r1 + (B.a*r2 - B.b*r2)
		 *A.a*r1 - A.b*r1 - B.a*r2 + B.b*r2
		 */

		Position closestOnA;
		Position closestOnB;
		Position A = simplexList.get(0);
		Position B = simplexList.get(1);
		Position L = B.sub(A);
		long lengthSqrL = L.lengthSqr();
		if (lengthSqrL < 0.00001f) {
			closestOnA = aList.get(0);
			closestOnB = bList.get(0);
			return;
		}

		float r2 = -(L.dot(A)*0.1f / lengthSqrL);
		// 将其限制在0-1之间
		r2 = MathExt.clamp(r2, 0, 1);

		float r1 = 1.0f - r2;
		closestOnA = aList.get(0).multiply(r1).add(bList.get(0).multiply(r2));
		closestOnB = aList.get(1).multiply(r1).add(bList.get(1).multiply(r2));
	}


	/**
	 * 检测
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	private Position getClosestPointToOrigin(Position a, Position b) {
		Position ab = b.sub(a);
		Position ao = Position.ZERO.sub(a);

		long lengthSqrab = ab.lengthSqr();
		// ab重合了
		if (lengthSqrab <= 0.000001) {
			return a;
		}

		// 计算投影长度，并单位化到[0, 1]，需要额外除以ab的长度。因此这里就直接除以长度的平方了。
		// ab * ao * cosa / ab*ab = ao*cosa/ab = ao投影到ab的长度 /ab = ao是ab的百分之几
		float projection = (ab.dot(ao) * 0.1f) / lengthSqrab;
		if (projection <= 0) {
			return a;
		}
		if (projection >= 1) {
			return b;
		}
		return a.add(ab.multiply(projection));
	}


	private Dir findNextDirection(List<Position> simplexList) {
		if (simplexList.size() == 2) {
			// 计算原点到直线01的垂足
			Dir verticalVector = findVerticalVector(simplexList.get(0), simplexList.get(1));
			return Dir.ZERO.toPosition().sub(verticalVector.toPosition()).toDir();
		}
		if (simplexList.size() == 3) {
			Dir crossOnCA = findVerticalVector(simplexList.get(2), simplexList.get(0));
			Dir crossOnCB = findVerticalVector(simplexList.get(2), simplexList.get(1));
			if (crossOnCA.length() < crossOnCB.length()) {
				simplexList.remove(1);
				return Position.ZERO.sub(crossOnCA.toPosition()).toDir().normalize();
			} else {
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
	public Position support(Shape s1, Shape s2, Dir dir, List<Position> aList, List<Position> bList) {
		Position position = supportWrapper(s1, dir);
		Position subPos = supportWrapper(s2, dir.negDir());

		aList.add(position);
		bList.add(subPos);
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
		return circle.getCenter().add(dir.toPosition().multiply(circle.getRegionRadius() / 1000f));
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

