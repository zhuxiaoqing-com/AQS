package com.youxi.math.GJK;

import com.youxi.dataStructure.collisionTree.AABB;
import com.youxi.entity.*;
import com.youxi.math.entity.Edge;
import com.youxi.math.entity.SupportList;
import com.youxi.util.MathExt;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 如果两个多边形不相交的情况下  可以求两个多边形的最近点;
 * 因为其算法已经将离原点最近的所有可能都求出来了;只要进行对比就好了;
 * <p>
 * 如果碰撞了的话 就不能求最近点了; 因为遍历完成的时候并没有将多边形画全;
 *
 *
 * GJK算法论文: https://ieeexplore.ieee.org/document/2083?arnumber=2083
 * EPA (Expanding Polytope Algorithm): http://www.dyn4j.org/2010/05/epa-expanding-polytope-algorithm

 https://zhuanlan.zhihu.com/p/603328999?utm_id=0
 */
public class EPATest01_2 {

	@Test
	public void test01() {
		//Circle circle = new Circle(new Position(0, 0), 1000);
		Shape obb2D1 = new OBB2D(new Position(152428, 0, 63156), new Dir(100000, 0, 100000), -351);
		Shape obb2D = new OBB2D(new Position(127915, 0, 70742), new Dir(100000, 0, 100000), -351);
		obb2D1 = new OBB2D(new Position(-600,0,-600), new Dir(1000,0,1000), 0);
		obb2D = new OBB2D(new Position(1500,0,1500), new Dir(1000,0,1000), 0);
		obb2D1 = new Circle(new Position(-2000,0,-2000), 500);
		System.out.println(((OBB2D)obb2D).getPoints());
		//obb2D1 = new OBB2D(new Position(15300, 0, 6800), new Dir(1000, 0, 1000), 0);
		//obb2D = new OBB2D(new Position(13100, 0, 7300), new Dir(1000, 0, 1000), 0);


		if (obb2D instanceof OBB2D && obb2D1 instanceof OBB2D) {
			AABB aabb = new AABB(obb2D1.getCenter().getX(), obb2D1.getCenter().getZ(), ((OBB2D) obb2D1).getSizeDir());
			AABB aabb2 = new AABB(obb2D.getCenter().getX(), obb2D.getCenter().getZ(), ((OBB2D) obb2D).getSizeDir());
			System.out.println(aabb2.intersect(aabb));
		}



		String as = "(0.7, 0.9)(-0.2, 0.2)(0.2, -0.9)(1.2, -0.9)(1.6, 0.2)";
		String bs = "(0.0, 1.0)(-0.9, 0.3)(-0.5, -0.8)(0.5, -0.8)(0.9, 0.3)";


		//obb2D1 = new ConvexPolygon(parsePosStr(as));
		//obb2D = new ConvexPolygon(parsePosStr(bs));

		ArrayList<Position> objects = new ArrayList<>();
		System.out.println(GJK(obb2D1, obb2D, objects) + " ------------ " + objects);
	}

	private List<Position> parsePosStr(String as) {
		as = as.substring(1,as.length()-1);
		String[] split = as.split("\\)\\(");
		System.out.println(Arrays.toString(split));
		ArrayList<Position> objects1 = new ArrayList<>();
		for (String s : split) {
			List<Double> collect = Arrays.stream(s.split(",")).map(String::trim).map(Double::parseDouble).collect(Collectors.toList());
			Position position = new Position(collect.get(0), collect.get(1));
			objects1.add(position);
		}
		return objects1;
	}


	/**
	 * <a href="https://www.bilibili.com/video/BV16P4y1q7GZ/?vd_source=45efa9402ce6ca32c998c7a15ba39467">...</a>
	 * <p>
	 * support ——> 图形a往dir方向找最远的点 图形b往neg(dir)方向找最远的点 相减 得到了一个support
	 * 获取dir1 两个图形中心点 相减 support1
	 * 获取dir2 原点-dir1  获取 support2
	 * 检测support2是否是一个有效的支撑点;检测support2和support1中间是否越过原点;
	 * 使用support-origin dot dir2  >0 说明跨过原点
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
		returnDir.d = findFirstDir(shape1, shape2);
		// 取第一个方向上的最远的点 减去 相反方向的最远的点
		SupportList.SupportBean simplex = support(shape1, shape2, returnDir.d);
		simplexList.add(simplex);

		// 获取第二个点 是原点 减去 simplex
		returnDir.d = Position.ZERO.sub(simplex.getSupport()).toDir().normalize();
		SupportList.SupportBean simplex2 = support(shape1, shape2, returnDir.d);
		simplexList.add(simplex2);

		// 获取的第三个支撑点的朝向
		returnDir.d = getClosestPointToOrigin(simplexList.getList().get(0).getSupport(), simplexList.getList().get(1).getSupport()).toDir();
		//lineCase(simplexList, returnDir);

		boolean intersect = false;
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

				if (triangleCase(simplexList, returnDir)) {
					intersect = true;
					break;
				}
			}
		} finally {
			if (count > 100) {
				System.out.println("count :" + count);
			}
		}


		if (intersect) {
			Position epa = EPA(shape1, shape2, simplexList);
			positions.add(epa);
		} else {
			positions.addAll(ComputeClosetPoint(simplexList));
		}

		return intersect;

	}

	private static Dir findFirstDir(Shape shape1, Shape shape2) {
		Dir normalize = shape1.getCenter().sub(shape2.getCenter()).toDir().normalize();
		if (normalize.length() <= 0) {
			return new Dir(-951, -690);
		}
		return normalize;
	}

	private List<Position> ComputeClosetPoint(SupportList simplexList) {
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
		SupportList.SupportBean A = simplexList.getList().get(0);
		SupportList.SupportBean B = simplexList.getList().get(1);
		Position L = B.getSupport().sub(A.getSupport());
		long lengthSqrL = L.lengthSqr();
		if (lengthSqrL < 0.00001f) {
			closestOnA = A.getFromA();
			closestOnB = A.getFromB();
			return Arrays.asList(closestOnA, closestOnB);
		}

		float r2 = -(L.dot(A.getSupport()) * 0.1f / lengthSqrL);
		// 将其限制在0-1之间
		r2 = MathExt.clamp(r2, 0, 1);

		float r1 = 1.0f - r2;
		closestOnA = A.getFromA().multiply(r1).add(B.getFromA().multiply(r2));
		closestOnB = A.getFromB().multiply(r1).add(B.getFromB().multiply(r2));

		return Arrays.asList(closestOnA, closestOnB);
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
		Dir normalize = tripleProd(ab, ao, ab).toDir().normalize();
		if(normalize.equals(Dir.ZERO)) {
			d.d = Dir.ZERO.sub(b.toDir());
			return;
		}
		d.d = normalize;
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


	///////////////////////EPA/////////////////////////////
	///////////////////////EPA/////////////////////////////
	///////////////////////EPA/////////////////////////////
	///////////////////////EPA/////////////////////////////
	///////////////////////EPA/////////////////////////////

	/**
	 * 当碰撞发生时，原点到最近的闵可夫斯基差集多边形的边(下文称作“差集最近边”)的距离，就是穿透深度，原点到该边的垂直向量就是穿透向量的方向。
	 * 因此，核心问题就转换成了，如何求得距离原点最近的差集边。
	 *
	 * 当碰撞检测完毕后，我们会得到一个单形体(Simplex)，该单形体可能包含两个或三个support点，将这些support点首尾相连构成封闭的多边形。
	 * EPA算法每次迭代的时候，从这个多边形中选择一条最近的边，沿着该边的法线方向(原点到边的垂线方向)，向外扩展。直到某条边无法向外扩展时，则该边就是差集最近边。
	 *
	 * 可以看到，EPA算法和GJK求最近距离算法很相似，都是在找一个差集最近边。
	 * 不过EPA用于发生碰撞的情况下，GJK求最近距离用于没有发生碰撞的情况下。理论上EPA也可以用于求两个多边形的最近边，只不过EPA收敛的速度没有GJK算法快。
	 * ————————————————
	 * 版权声明：本文为CSDN博主「游蓝海」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
	 * 原文链接：https://blog.csdn.net/you_lan_hai/article/details/108300750
	 * @param s1
	 * @param s2
	 * @param simplexList
	 * @return
	 */
	public Position EPA(Shape s1, Shape s2, SupportList simplexList) {
		/*
		 * 当GJK算法结束后，如果某个单形体的边经过了原点或者离原点非常近，则这条边的法线方向将会无法正确的计算出来，会导致后续的EPA扩张方向出现混乱，
		 * 无法正确的计算出穿透向量。因此，最稳妥的方法是，进入EPA算法的时候，仅保留单形体的一条边，也就是只留下2个顶点。
		 * 则EPA初始的边集只有两个有向边 ab和ba，而且两者的法线方向刚好相反，后续会沿着相反的方向进行扩张，不会产生交叉混乱的情况。
		 */
		// 删除一条边
		if (simplexList.getList().size() >= 3) {
			deleteEdge(simplexList);
		}

		// 构造一个首尾相连的多边形
		List<Edge> edges = initEdges(simplexList);

		int count = 0;
		while (true) {
			count++;
			// 找到距离原点最近的边
			Edge closestEdge = findClosestEdge(edges);
			// 沿着边的法线方向，尝试找一个新的support点
			Position support = support(s1, s2, closestEdge.getNormal().toDir()).getSupport();
			/**
			 * 为什么要用法线找的话
			 * 应该是因为 法线(就是边的垂直线;这里需要的是远离顶点的垂直线)
			 * 垂直过去找应该是最快的 找到的边也是最远的
			 */
			// 这里起始就是如果获取的support 没有比原来的边更远离顶点的话;那就找到了离顶点最近的Minkowsk(可夫斯基)集合的边了
			// 无法找到能够跨越该边的support点了。也就是说，该边就是差集最近边
			long dot = support.dot(closestEdge.getNormal());
			if (dot - closestEdge.getDistance() <= 0) {
				System.out.println(count);
				// 返回穿透向量
				return closestEdge.getNormal().multiply(dot * 0.001f);
			}
			// 将新的support点插入到多边形中。
			// 也就是将边e从support点位置分割成两条新的边，并替换到多边形集合中。
			insertEdgePoint(edges, closestEdge, support);
		}
	}

	/**
	 * 将边e断开，e原来的两个端点分别与support点进行连接，然后重新插入到边的集合中。
	 */
	private void insertEdgePoint(List<Edge> edges, Edge closestEdge, Position support) {
		Edge e1 = createEdge(closestEdge.getStart(), support, closestEdge.getIndex());
		// 覆盖原来的位置
		edges.set(closestEdge.getIndex(), e1);

		Edge e2 = createEdge(support, closestEdge.getEnd(), edges.size());
		// 插入新的边
		edges.add(e2);
		// 重新分配边的索引 上面set过了 不需要重新分配
	}


	/**
	 * 找到距离原点最近的边
	 *
	 * @param edges edges
	 */
	public Edge findClosestEdge(List<Edge> edges) {
		long minDistance = Integer.MAX_VALUE;
		Edge minEdge = null;
		for (Edge edge : edges) {
			if (minDistance >= edge.getDistance()) {
				minDistance = edge.getDistance();
				minEdge = edge;
			}
		}
		return minEdge;
	}


	/**
	 * 我们需要得到的是单形体多边形的边的集合，只用将support点首尾即可。为了方便后续计算，我们把每条边的法线和到原点的距离也计算出来。法线就是原点到边的垂线，取向外的方向。
	 * <p>
	 * 有一个特殊的情况，如果GJK算法结束时，原点恰好位于单形体的某条边上。此时单形体中将会只有两个support点，且这两点的连线是经过原点的。
	 * 这种情况下，无法用计算垂足的方法计算垂线，需要用到数学的方法：向量(x, y)的垂直向量为: (y, -x)，或者(-y, x)。随便用哪一个都行，
	 * 因为两个support点首尾相连，会构成两条方向相反的边，恰好用数学方法求得的垂直向量方向也自然是相反的，EPA算法将会沿着两个相反的方向向外扩展。
	 */
	ArrayList<Edge> initEdges(SupportList simplexList) {
		ArrayList<Edge> edges = new ArrayList<>();
		int n = simplexList.getList().size();
		for (int i = 0; i < n; i++) {
			int iNext = (i + 1) % n;
			Edge edge = createEdge(simplexList.getList().get(i).getSupport(), simplexList.getList().get(iNext).getSupport(), i);
			edges.add(edge);
		}
		return edges;
	}

	private Edge createEdge(Position start, Position end, int index) {
		Edge e = new Edge(start, end);
		e.setIndex(index);
		// 计算垂足Q。则法线向量为 OQ = Q - (0, 0) = Q
		e.setNormal(getPerpendicularToOrigin(start, end));
		long lengthSqr = e.getNormal().lengthSqr();
		if(lengthSqr> 1) {
			e.setDistance(lengthSqr);
			e.setNormal(e.getNormal().normalize());
		}
		// 用数学的方法来得到直线的垂线，刚好另外一边是反着来的
		Position v = start.sub(end);
		v = v.normalize();
		/**
		 * 点是顺时针旋转用 y -x 是逆时针旋转用 -y,x
		 * if (winding == CLOCKWISE) {
		 *   n = e.left(); // (y, -x)
		 * } else {
		 *   n = e.right(); // (-y, x)
		 * }
		 */
		e.setNormal(new Position(-v.getZ(), v.getX()));
		return e;
	}

	/// 获得原点到直线ab的垂点c。(c - o)就是原点到ab的垂线
	public Position getPerpendicularToOrigin(Position a, Position b) {
		Position ab = b.sub(a);
		Position ao = Position.ZERO.sub(a);
		long lengthSqr = ab.lengthSqr();
		if (lengthSqr < 0.0001) {
			return Position.ZERO;
		}

		float projection = ab.dot(ao) * 1.0f / lengthSqr;
		return a.add(ab.multiply(projection));
	}


	private void deleteEdge(SupportList simplexList) {
		Position crossOnCA = getClosestPointToOrigin(simplexList.getList().get(2).getSupport(), simplexList.getList().get(0).getSupport());
		Position crossOnCB = getClosestPointToOrigin(simplexList.getList().get(2).getSupport(), simplexList.getList().get(1).getSupport());
		if (crossOnCA.lengthSqr() < crossOnCB.lengthSqr())
		{
			simplexList.remove(1);
		}
		else
		{
			simplexList.remove(0);
		}
	}

	public static void main(String[] args) {
		Position start = new Position(600, 0, 1800);
		Position end = new Position(-600, 0, -1800);
		Position sub = start.sub(end);
		System.out.println(sub);
		Position position = new Position(-sub.getZ(), sub.getX());
		System.out.println(position);
	}
}








