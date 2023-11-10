package com.youxi.math.GJK2.GJK;

import com.youxi.dataStructure.collisionTree.AABB;
import com.youxi.entity.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果两个多边形不相交的情况下  可以求两个多边形的最近点;
 * 因为其算法已经将离原点最近的所有可能都求出来了;只要进行对比就好了;
 *
 * 如果碰撞了的话 就不能求最近点了; 因为遍历完成的时候并没有将多边形画全;
 *
 *
 * 维基百科: https://en.wikipedia.org/wiki/Gilbert%E2%80%93Johnson%E2%80%93Keerthi_distance_algorithm
 * GJK算法论文: https://ieeexplore.ieee.org/document/2083?arnumber=2083
 * dyn4j很详细的GJK算法教程(英文): http://www.dyn4j.org/2010/04/gjk-gilbert-johnson-keerthi/#gjk-iteration
 * AndrewFan，对dyn4j教程的中文翻译: https://blog.csdn.net/AndrewFan/article/details/101694644
 * Wyman，原始GJK详解: https://www.qiujiawei.com/collision-detection-2
 * ————————————————
 * 版权声明：本文为CSDN博主「游蓝海」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/you_lan_hai/article/details/108293780
 *
 */
public class GJKTest01 {

	@Test
	public void test01() {
		//Circle circle = new Circle(new Position(0, 0), 1000);
		OBB2D obb2D1 = new OBB2D(new Position(152428,0,63156), new Dir(10000,0,10000), -351);
		OBB2D obb2D = new OBB2D(new Position(127915,0,70742), new Dir(10000,0,10000), -351);
		obb2D1 = new OBB2D(new Position(-600,0,-600), new Dir(1000,0,1000), 0);
		obb2D = new OBB2D(new Position(1500,0,1500), new Dir(1000,0,1000), 0);
		obb2D1 = new OBB2D(new Position(3000,0,3000), new Dir(3000,0,3000), 0);
		obb2D = new OBB2D(new Position(9000,0,9000), new Dir(3000,0,3000), 0);
		// obb2D1 = new OBB2D(new Position(15300,0,6800), new Dir(1000,0,1000), 0);
		 //obb2D = new OBB2D(new Position(13100,0,7300), new Dir(1000,0,1000), 0);

		AABB aabb = new AABB(obb2D1.getCenter().getX(), obb2D1.getCenter().getZ(), obb2D1.getSizeDir());
		AABB aabb2 = new AABB(obb2D.getCenter().getX(), obb2D.getCenter().getZ(), obb2D.getSizeDir());
		System.out.println(aabb2.intersect(aabb));

		System.out.println(GJK(obb2D1, obb2D));
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
	 */
	public boolean GJK(Shape shape1, Shape shape2) {
		ModifyDir returnDir = new ModifyDir();

		List<Position> simplexList = new ArrayList<>();
		// 起始向量是两个形状的中心点向量相减
		returnDir.d = findFirstDir(shape1, shape2);
		// 取第一个方向上的最远的点 减去 相反方向的最远的点
		Position simplex = support(shape1, shape2, returnDir.d);
		if (simplex.equals(Position.ZERO)) {
			return true;
		}
		simplexList.add(simplex);

		// 获取第二个点 是原点 减去 simplex
		returnDir.d = Position.ZERO.sub(simplex).toDir().normalize();

		int count = 0;
		try {
			while (true) {
				if (count++ > 200) {
					return false;
				}
				// 通过第二个方向 获取新的支撑点 第二个支撑点
				Position A = support(shape1, shape2, returnDir.d);
				// 现在要检测第二个支撑点是否有效; 如果第二个点和第一个点中间没有越过原点 (用第二个点和方向点乘来判断 >0说明越过了)
				// 朝着原点最远的点都没有跨过原点的话 那原点就不在内部
				if (A.dot(returnDir.d.toPosition()) < 0) {
					return false;
				}
				// 如果满足那就将该点放入集合
				simplexList.add(A);
				if(simplexList.size() == 3)
					System.out.println("simplexList ----    " + simplexList);

				if (handlerSimplex(simplexList, returnDir)) {
					return true;
				}
			}
		} finally {
			if (count > 100) {
				System.out.println("count :" + count);
			}

		}

	}

	private boolean handlerSimplex(List<Position> simplexList, ModifyDir d) {
		if (simplexList.size() == 2) {
			return lineCase(simplexList, d);
		}
		return triangleCase(simplexList, d);
	}

	/**
	 * 已经有两条线了 b,a 点
	 * 获取 ao投影到 ab 上的垂直向量; 这个向量标准化以后 可以当做第三个方向
	 */
	private boolean lineCase(List<Position> simplexList, ModifyDir d) {
		Position b = simplexList.get(0);
		Position a = simplexList.get(1);
		Position ab = b.sub(a);
		Position ao = Position.ZERO.sub(a);
		d.d = tripleProd(ab, ao, ab).toDir().normalize();
		return false;
	}

	/**
	 * 三角形的情况  我们计算他到底在 AB垂直 和AC垂直  三角形本身 的哪个区域
	 */
	private boolean triangleCase(List<Position> simplexList, ModifyDir d) {
		Position c = simplexList.get(0);
		Position b = simplexList.get(1);
		Position a = simplexList.get(2);
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
	 * 取第一个方向上的最远的点 减去 相反方向的最远的点
	 */
	public Position support(Shape s1, Shape s2, Dir dir) {
		Position position = supportWrapper(s1, dir);
		Position subPos = supportWrapper(s2, dir.negDir());
		Position support = position.sub(subPos);
		System.out.printf("dir %s  position1 %s position2 %s support %s%n", dir, position, subPos, support);
		return support;
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


	// 计算原点到直线ab的垂足
	public Dir findVerticalVector(Position a, Position b) {
		Position origin = Position.ZERO;
		Position ao = origin.sub(a);
		Position ab = b.sub(a);
		long dot = ao.dot(ab);
		long dot1 = ab.dot(ab);
		long abLenSqr = a.getDirectDistanceSquareIgnoreY(b);

		double t = dot * 1.0d / abLenSqr;

		Position add1 = a.add(ab);
		Position add = a.add(ab.multiply((float) t));
		return add.toDir().normalize();
	}

	public Position tripleProd(Position a, Position b, Position c) {
		return CMath.Cross3D(CMath.Cross3D(a, b).normalize(), c).normalize();
	}

	private static Dir findFirstDir(Shape shape1, Shape shape2) {
		Dir normalize = shape2.getCenter().sub(shape1.getCenter()).toDir().normalize();
		if (normalize.length() <= 0) {
			return new Dir(-951, -690);
		}
		return normalize;
	}
}








