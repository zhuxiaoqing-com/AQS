package com.youxi.math.GJK;

import com.youxi.dataStructure.collisionTree.AABB;
import com.youxi.entity.*;
import com.youxi.math.entity.SupportList;
import com.youxi.util.MathExt;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 如果两个多边形不相交的情况下  可以求两个多边形的最近点;
 * 因为其算法已经将离原点最近的所有可能都求出来了;只要进行对比就好了;
 *
 * 如果碰撞了的话 就不能求最近点了; 因为遍历完成的时候并没有将多边形画全;
 *
 *GJK算法论文: https://ieeexplore.ieee.org/document/2083?arnumber=2083
 * GJK – Distance & Closest Points: http://www.dyn4j.org/2010/04/gjk-distance-closest-points
 * ————————————————
 * 版权声明：本文为CSDN博主「游蓝海」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/you_lan_hai/article/details/108298767
 */
public class GJKTest01_1 {

	@Test
	public void test01() {
		//Circle circle = new Circle(new Position(0, 0), 1000);
		OBB2D obb2D1 = new OBB2D(new Position(152428, 0, 63156), new Dir(10000, 0, 10000), -351);
		OBB2D obb2D = new OBB2D(new Position(127915, 0, 70742), new Dir(10000, 0, 10000), -351);
		//obb2D1 = new OBB2D(new Position(-600,0,-600), new Dir(1000,0,1000), 0);
		//obb2D = new OBB2D(new Position(1500,0,1500), new Dir(1000,0,1000), 0);
		obb2D1 = new OBB2D(new Position(15300, 0, 6800), new Dir(1000, 0, 1000), 0);
		obb2D = new OBB2D(new Position(13100, 0, 7300), new Dir(1000, 0, 1000), 0);

		AABB aabb = new AABB(obb2D1.getCenter().getX(), obb2D1.getCenter().getZ(), obb2D1.getSizeDir());
		AABB aabb2 = new AABB(obb2D.getCenter().getX(), obb2D.getCenter().getZ(), obb2D.getSizeDir());
		System.out.println(aabb2.intersect(aabb));

		ArrayList<Position> objects = new ArrayList<>();
		System.out.println(GJK(obb2D1, obb2D, objects) + " ------------ " + objects);
	}


	/**
	 * <a href="https://www.bilibili.com/video/BV16P4y1q7GZ/?vd_source=45efa9402ce6ca32c998c7a15ba39467">...</a>
	 *
	 * support ——> 图形a往dir方向找最远的点 图形b往neg(dir)方向找最远的点 相减 得到了一个support
	 * 获取dir1 两个图形中心点 相减 support1
	 * 获取dir2 原点-dir1  获取 support2
	 * 检测support2是否是一个有效的支撑点;检测support2和support1中间是否越过原点;
	 * 	使用support-origin dot dir2  >0 说明跨过原点
	 * support1和support2组成一条线段;获取相对于原点的投影向量;  获取support3
	 * 然后用 沃罗诺伊地区(voronoi Regions)来分析 确定下一个dir
	 *
	 * @param shape1
	 * @param shape2
	 * @return
	 */
	public boolean GJK(Shape shape1, Shape shape2, List<Position> positions) {
		ModifyDir returnDir = new ModifyDir();

		SupportList simplexList = new SupportList();
		// 起始向量是两个形状的中心点向量相减
		returnDir.d = shape1.getCenter().sub(shape2.getCenter()).toDir().normalize();
		// 取第一个方向上的最远的点 减去 相反方向的最远的点
		SupportList.SupportBean simplex = support(shape1, shape2, returnDir.d);
		simplexList.add(simplex);

		// 获取第二个点 是原点 减去 simplex
		returnDir.d = Position.ZERO.sub(simplex.getSupport()).toDir().normalize();
		SupportList.SupportBean simplex2 = support(shape1, shape2, returnDir.d);
		simplexList.add(simplex2);

		// 获取的第三个支撑点的朝向
		lineCase(simplexList, returnDir);

		/*
		不发生碰撞的结束条件不同。上一章迭代算法最终结束时，可能是一个不包含原点的三角形，为了得到差集最近边，我们可以舍弃掉距离原点较远的那个边。
		这里我们让算法多迭代一次，恰好可以舍弃掉那个点，同时会额外会产生一个重复的support点。因此只要发现support点位置重叠了，就表明迭代算法可以结束了。
		 */

		int count = 0;
		try {
			while (true) {
				if (count++ > 200) {
					break;
				}
				// 通过第二个方向 获取新的支撑点 第二个支撑点
				SupportList.SupportBean A = support(shape1, shape2, returnDir.d);
				// 新点与之前的点重合了。也就是沿着dir的方向，已经找不到更近的点了。
				if (simplexList.getList().get(0).getSupport().getDistanceIgnoreY(A.getSupport()) <= 0.0001f ||
						simplexList.getList().get(1).getSupport().getDistanceIgnoreY(A.getSupport()) <= 0.0001f
				) {
					break;
				}

				// 如果满足那就将该点放入集合
				simplexList.add(A);

				if(triangleCase(simplexList, returnDir)) {
					return true;
				}
			}
		} finally {
			if (count > 100) {
				System.out.println("count :" + count);
			}
		}

		positions.addAll(ComputeClosetPoint(simplexList));
		return false;
	}

	private List<Position> ComputeClosetPoint(SupportList simplexList ) {
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
		 * Q 可以通过 A*r1 + B*r2 求得
		 * 其实Q也可以通过 Q = Qa-Qb 求得
		 *
		 * Q = A*r1 + B*r2
		 * Q = (AF1-AF2)*r1 + (BF1-BF2)*r2
		 * Qa-Qb = (AF1-AF2)*r1 + (BF1-BF2)*r2
		 * Qa-Qb = AF1*r1-AF2*r1 + BF1*r2-BF2*r2
		 * Qa-Qb = (AF1*r1+ BF1*r2) -(AF2*r1 +BF2*r2)
		 * Qa = AF1*r1+ BF1*r2
		 * Qb = AF2*r1 +BF2*r2
		 */

		Position closestOnA;
		Position closestOnB;
		SupportList.SupportBean A = simplexList.getList().get(0);
		SupportList.SupportBean B = simplexList.getList().get(1);
		Position L = B.getSupport().sub(A.getSupport());
		long lengthSqrL = L.lengthSqr();
		/**
		 * 首先，如果闵可夫斯基不同点\(\vec{a}\)和\(\vec{b}\)是同一点，那么\(\vec{l}\)将是零向量。这意味着稍后当我们除以\(\vec{l}\)的大小时，我们将除以0:不好。
		 * 这意味着离原点最近的点不在闵可夫斯基差的边缘上，而是一个点。
		 * 因此，制造\(\vec{a}\)和\(\vec{b}\)的支撑点是相同的，因此您可以只返回\(\vec{a}\)或\(\vec{b}\)的支撑点:
		 *
		 * 支撑点一样说明离原点最近的点是一个点;
		 * 一个点的话 那其实就是那个support点;
		 * 那两个 shape的最最近点其实就是 fromA fromB
		 */
		if (lengthSqrL < 0.00001f) {
			closestOnA = A.getFromA();
			closestOnB = A.getFromB();
			return Arrays.asList(closestOnA,closestOnB);
		}

		float r2 = -(L.dot(A.getSupport())*0.1f / lengthSqrL);
		// 将其限制在0-1之间
		r2 = MathExt.clamp(r2, 0, 1);

		float r1 = 1.0f - r2;
		closestOnA = A.getFromA().multiply(r1).add(B.getFromA().multiply(r2));
		closestOnB = A.getFromB().multiply(r1).add(B.getFromB().multiply(r2));

		return Arrays.asList(closestOnA,closestOnB);
	}



	/**
	 * 已经有两条线了 b,a 点
	 * 获取 ao投影到 ab 上的垂直向量; 这个向量标准化以后 可以当做第三个方向
	 */
	private void lineCase(SupportList simplexList, ModifyDir d) {
		Position b = simplexList.getList().get(0).getSupport();
		Position a = simplexList.getList().get(1).getSupport();
		Position ab = b.sub(a);
		Position ao = Position.ZERO.sub(a);
		d.d = tripleProd(ab, ao, ab).toDir().normalize();
	}

	/**
	 * 三角形的情况  我们计算他到底在 AB垂直 和AC垂直  三角形本身 的哪个区域
	 */
	private boolean triangleCase(SupportList simplexList, ModifyDir d) {
		Position c = simplexList.getList().get(0).getSupport();
		Position b = simplexList.getList().get(1).getSupport();
		Position a = simplexList.getList().get(2).getSupport();
		Position ab = b.sub(a);
		Position ac = c.sub(a);
		Position ao = Position.ZERO.sub(a);
		Dir ABperp = tripleProd(ac, ab, ab).toDir();
		Dir ACperp = tripleProd(ab, ac, ac).toDir();

		if (ABperp.toPosition().dot(ao) > 0) {
			// 在AB区域
			// 删除c点 使用新的方向
			simplexList.remove(0);
			d.d = ABperp;
			return false;
		}


		if (ACperp.toPosition().dot(ao) > 0) {
			// 在AB区域
			// 在AB区域
			// 删除c点 使用新的方向
			simplexList.remove(1);
			d.d = ACperp;
			return false;
		}


		// 两种情况都没有命中就是在三角形内;
		return true;
	}

	/**
	 * 三角形的情况  我们计算他到底在 AB垂直 和AC垂直  三角形本身 的哪个区域
	 *//*
	private boolean triangleCase(SupportList simplexList, ModifyDir d) {
		Position c = simplexList.getList().get(0).getSupport();
		Position b = simplexList.getList().get(1).getSupport();
		Position a = simplexList.getList().get(2).getSupport();
		Dir ABperp = getClosestPointToOrigin(a, b).toDir().normalize();
		Dir ACperp = getClosestPointToOrigin(a, c).toDir().normalize();

		if (ABperp.toPosition().dot(Position.ZERO.sub(a)) > 0) {
			// 在AB区域
			// 删除c点 使用新的方向
			simplexList.remove(0);
			d.d = ABperp;
			return false;
		}


		if (ACperp.toPosition().dot(Position.ZERO.sub(a)) > 0) {
			// 在AB区域
			// 在AB区域
			// 删除c点 使用新的方向
			simplexList.remove(0);
			d.d = ACperp;
			return false;
		}

		// 两种情况都没有命中就是在三角形内;
		return true;
	}*/


	/**
	 * 取第一个方向上的最远的点 减去 相反方向的最远的点
	 */
	public SupportList.SupportBean support(Shape s1, Shape s2, Dir dir) {
		Position position = supportWrapper(s1, dir);
		Position subPos = supportWrapper(s2, dir.negDir());
		Position support = position.sub(subPos);

		return new SupportList.SupportBean(support, position, subPos);
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

	/**
	 * ao投影到ab线段上的垂直向量
	 * <p>
	 * ao dot ab = |ao| * |ab| * sina
	 * <p>
	 * |ab|^2
	 * <p>
	 * t = |ao| * |ab| * sina / |ab|^2 = ao* sina / |ab|
	 * <p>
	 * 垂直向量 = a+ ab*t
	 */
/*	public Dir findVerticalVector(Position a, Position b, Position o) {
		Position ao = o.sub(a);
		Position ab = b.sub(a);
		long dot = ao.dot(ab);

		long abLenSqr = a.getDirectDistanceSquareIgnoreY(b);

		double t = dot * 1.0d / abLenSqr;

		Position add = a.add(ab.multiply((float) t));
		return o.sub(add).toDir();
	}*/


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

	public Position tripleProd(Position a, Position b, Position c) {
		return CMath.Cross3D(CMath.Cross3D(a, b).normalize(), c).normalize();
	}
}








