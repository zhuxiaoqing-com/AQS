package com.youxi.math.GJK2.GJK;

import com.youxi.dataStructure.collisionTree.AABB;
import com.youxi.entity.*;
import com.youxi.match.game.match.Pair;
import com.youxi.util.MathExt;
import io.protostuff.ListAdapter;
import io.swagger.models.auth.In;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果两个多边形不相交的情况下  可以求两个多边形的最近点;
 * 因为其算法已经将离原点最近的所有可能都求出来了;只要进行对比就好了;
 * <p>
 * 如果碰撞了的话 就不能求最近点了; 因为遍历完成的时候并没有将多边形画全;
 * <p>
 * <p>
 * 维基百科: https://en.wikipedia.org/wiki/Gilbert%E2%80%93Johnson%E2%80%93Keerthi_distance_algorithm
 * GJK算法论文: https://ieeexplore.ieee.org/document/2083?arnumber=2083
 * dyn4j很详细的GJK算法教程(英文): http://www.dyn4j.org/2010/04/gjk-gilbert-johnson-keerthi/#gjk-iteration
 * AndrewFan，对dyn4j教程的中文翻译: https://blog.csdn.net/AndrewFan/article/details/101694644
 * Wyman，原始GJK详解: https://www.qiujiawei.com/collision-detection-2
 * ————————————————
 * 版权声明：本文为CSDN博主「游蓝海」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/you_lan_hai/article/details/108293780
 */
public class GJKRecast {

	@Test
	public void test01() {
		//Circle circle = new Circle(new Position(0, 0), 1000);
		OBB2D obb2D1 = new OBB2D(new Position(152428, 0, 63156), new Dir(10000, 0, 10000), -351);
		OBB2D obb2D = new OBB2D(new Position(127915, 0, 70742), new Dir(10000, 0, 10000), -351);
		obb2D1 = new OBB2D(new Position(-600, 0, -600), new Dir(1000, 0, 1000), 0);
		obb2D = new OBB2D(new Position(1500, 0, 1500), new Dir(1000, 0, 1000), 0);
		obb2D1 = new OBB2D(new Position(3000, 0, 3000), new Dir(3000, 0, 3000), 0);
		obb2D = new OBB2D(new Position(9000, 0, 9000), new Dir(3000, 0, 3000), 0);
		// obb2D1 = new OBB2D(new Position(15300,0,6800), new Dir(1000,0,1000), 0);
		//obb2D = new OBB2D(new Position(13100,0,7300), new Dir(1000,0,1000), 0);

		AABB aabb = new AABB(obb2D1.getCenter().getX(), obb2D1.getCenter().getZ(), obb2D1.getSizeDir());
		AABB aabb2 = new AABB(obb2D.getCenter().getX(), obb2D.getCenter().getZ(), obb2D.getSizeDir());
		System.out.println(aabb2.intersect(aabb));

		//System.out.println(GJKRecast(obb2D1, obb2D));
	}


	/**
	 * 传入参数 shapeA shapeB castStart castDir
	 * <p>
	 * shapeB 是起始点
	 * 先两个中心点相减 获取第一个Dir
	 * 获取第一个support
	 * 然后neg(support) 获取第二个朝向
	 * <p>
	 * 记录一个参数currDist=第一个support距离原点的位置这个位置的平方 (dot(support,support))
	 * 记录第二个参数 minDist 最小的距离原点的距离

	 * @param castDir 是B方向的射线
	 */
	public boolean GJKRecast(Shape shapeA, Shape shapeB, Dir castDir, int inflation) {
		int maxDist = Integer.MAX_VALUE;
		float lambda = 0;
		Position x = Position.ZERO; // 单纯形到圆心的距离被延长了多少

		Dir initDir = shapeA.getCenter().sub(shapeB.getCenter()).toDir();
		initDir = initDir.normalize();

		Position initSupportA = shapeA.support(initDir.negDir());
		Position initSupportB = shapeB.support(initDir);

		/*Position[] Q = new Position[]{initSupportA.sub(initSupportB), Position.ZERO, Position.ZERO};
		Position[] A = new Position[]{initSupportA, Position.ZERO, Position.ZERO};
		Position[] B = new Position[]{initSupportB, Position.ZERO, Position.ZERO};
*/
		List<Position> Q = new ArrayList<>();
		List<Position> A = new ArrayList<>();
		List<Position> B = new ArrayList<>();
		Q.add(initSupportA.sub(initSupportB));
		A.add(initSupportA);
		B.add(initSupportB);

		// 第二个support的方向
		Dir v = Q.get(0).toDir().negDir();

		Position supportA = initSupportA;
		Position supportB = initSupportB;
		Position support = Q.get(0);
		int size = 1;

		int inflationPlusEps = inflation;
		long inflationSqr = (long) inflation * inflation / 1000;

		Position closet = Q.get(0);
		Position preCloset = closet;

		long currDist = v.toPosition().dot(v.toPosition());
		long minDist = currDist;

		//while不终止的条件
		boolean notTerminated = currDist > 0;
		// 是否退化
		boolean notDegenerated = true;
		Dir nor = v;    // 这个值只有在射线被延长的时候才会被赋值

		while (notTerminated) {
			minDist = currDist;
			preCloset = closet;

			Dir vNorm = v.normalize();
			Dir nvNorm = vNorm.negDir();

			supportA = shapeA.support(vNorm);
			supportB = shapeB.support(nvNorm).add(x);

			support = supportA.sub(supportB);

			// origin-support
			Dir w = support.toDir().negDir();
			// 实际距离原点的长度
			long vw = w.toPosition().dot(vNorm.toPosition()) - inflationPlusEps;
			if (vw > 0) {
				// 不能跨越原点 需要将射线延长
				//因为是shapeB的射线;support想<=0就是 a-b<=0
				// 就是b要更大; 又因为b是找nvNorm方向;所以b要更大 就castDir要放nvNorm方向生长;也就是要往vNorm的反方向伸长
				long vr = vNorm.toPosition().dot(castDir.toPosition());
				if (vr > 0) {
					return false;
				} else {
					float oldLambda = lambda;
					// 因为vr是负数 所以 vw * 1f / vr是负数
					// 所以 lambda- 其实是+; 就是加上vw缺的那一部分;加上以后就刚好变成0
					lambda = lambda - vw * 1f / vr;

					if (lambda > oldLambda) {
						// 射线延长的太多了 超过了100%
						if (lambda > 1) {
							return false;
						}

						// 获取之前延长了多少
						Position preCenter = x;
						// 计算当前可以延长多少
						x = castDir.multiply(lambda).toPosition();
						Position offset = x.sub(preCenter);

						// 将B的用来计算单纯形的点延长
						for (int i = 0; i < B.size(); i++) {
							B.set(i, B.get(i).sub(offset));
						}
						// 重新计算单纯性
						for (int i = 0; i < Q.size(); i++) {
							Q.set(i, A.get(i).sub(B.get(i)));
						}


						// 重新求 supportB supportB这次被延长了 x距离
						supportB = shapeB.support(nvNorm).add(x);
						support = supportA.sub(supportB);
						minDist = maxDist; // 这个方法是要求最小长度;因为b已经被重新延长过了  所以最小长度可能是不正确的 将其置为最大值重新计算
						nor = v;
					}
				}
			}

			/*A[size] = supportA;
			B[size] = supportB;
			Q[size] = support;*/
			size++;
			GJKCPairDoSimplex(Q, A, B, support);

		}
		return true;
	}

	private Position GJKCPairDoSimplex(List<Position> q, List<Position> a, List<Position> b, Position support) {
		int size = q.size();
		switch (size) {
			case 1:
				return support;
			case 2:
				return closestPtPointSegment(q);


			case 3:

		}
		return support;
	}

	/**
	 * 其实就是找线段离原点最近的点
	 *
	 * @return true:退化
	 */
	private Position closestPtPointSegment(List<Position> q) {
		Position a = q.get(0);
		Position b = q.get(1);

		Position ab = b.sub(a);
		long denom = ab.dot(ab);
		Dir ap = a.toDir().negDir(); //V3Sub(origin, a);
		long nom = ap.toPosition().dot(ab);

		// 两个支撑点靠的太近了 退化
		if (0 >= denom) {
			q.remove(q.size() - 1);
			return q.get(0);
		}
		double v = nom * 1.0d / denom;
		v = MathExt.clamp(v, 0, 1);
		return a.add(ab.multiply((float) v));
	}


	public void closestPtPointTriangle(List<Position> q, List<Position> a, List<Position> b) {

	}

}








