package com.youxi.entity;



import java.util.Arrays;
import java.util.List;

import static com.youxi.entity.CMath.getUnityDirByRotateYFromX;

/**
 * fixed-point collision
 * 
 * @author Eric.Ma
 *
 */
public class Collision2D {

	public static long TestSegmentSegment(Position p0, Position p1, Position p2, Position p3) {
		Position diff = p2.sub(p0);
		Position d1 = p1.sub(p0);
		Position d2 = p3.sub(p2);

		long demo = CMath.Cross2D(d1, d2);// det
		if (Math.abs(demo) < CMath.EPSILON_FIXED) {
			return CMath.negOne;
		}
		long t1 = CMath.fixedDiv(CMath.Cross2D(d2, diff), demo); // Cross2D(diff,-d2)
		long t2 = CMath.fixedDiv(CMath.Cross2D(d1, diff), demo); // Dot(v1,pd0) == cross(d0,d1)

		if ((t1 >= 0 && t1 <= 1) && (t2 >= 0 && t2 <= 1))
			return t1; // return p0 + (p1-p0) * t1
		return CMath.negOne;
	}

	public static long TestRaySegment(Position o, Dir d, Position p2, Position p3) {

		Position diff = p2.sub(o);
		Position d2 = p3.sub(p2);
		Position d1 = new Position(d.getX(), d.getZ());
		long demo = CMath.Cross2D(d1, d2);
		if (Math.abs(demo) < CMath.EPSILON_FIXED)
			return CMath.negOne;
		long t1 = CMath.fixedDiv(CMath.Cross2D(d2, diff), demo);
		long t2 = CMath.fixedDiv(CMath.Cross2D(d1, diff), demo);
		if (t1 >= 0 && (t2 >= 0 && t2 <= 1))
			return t1;
		return CMath.negOne;

	}

	public static boolean TestRayPolygon(Position o, Dir dir, List<Position> points, OutValue ov) {
		long t = Long.MAX_VALUE;
		for (int i = 0; i < points.size(); i++) {
			Position b1 = points.get(i);
			Position b2 = points.get((i + 1) % points.size());
			long inter = TestRaySegment(o, dir, b1, b2);
			if (inter >= 0) {
				if (inter < t) {
					t = inter;
				}
			}
		}
		if (t < Long.MAX_VALUE) {
			ov.setResult(t, 0);
			nearest(ov,o,dir,t);
			return true;
		}

		return false;
	}

	/**
	 * 实现原理，可查看下面两个网址
	 * https://math.stackexchange.com/questions/311921/get-location-of-vector-circle-intersection
	 * https://stackoverflow.com/questions/1073336/circle-line-segment-collision-detection-algorithm#
	 * https://www.pianshen.com/article/2429198750/
	 */
	public static boolean TestRayCircle(Position cPos, int cR, Position rB, Dir rDir, OutValue ov) {
		ModifyPosition d = rDir.toPosition(ov.circleCache.d);
		ModifyPosition f = rB.sub(cPos, ov.circleCache.f);
		long a = CMath.Dot2D(d, d); // length= |d||d|cos0 cos0= 1,
		if (a == 0) {
			d.normalize(d).multiply(0.1f, d);// 最小0.1米
			a = CMath.Dot2D(d, d);
		}
		long b = 2 * CMath.Dot2D(f, d);
		long c = CMath.Dot2D(f, f) - CMath.fixedMultiply(cR, cR);
		long discriminant = CMath.fixedMultiply(b, b) - 4 * CMath.fixedMultiply(a, c);
		if (discriminant < 0) {
			// no intersection
			return false;
		} else {
			//当前 rDir 向量的百分之t，会和圆有交点`
			discriminant = CMath.sqrt(discriminant);
			long t1 = CMath.fixedDiv((-b - discriminant), (2 * a));
			long t2 = CMath.fixedDiv((-b + discriminant), (2 * a));
			if (t1 >= 0 && t1 <= CMath.ONE) {
//				System.err.println(String.format("11111111 t1:%s, t2: %s", t1, t2));
				if (ov != null) {
					ov.setResult(t1, 0);
					nearest(ov, rB,rDir,t1);
				}
				return true;
			}

			if (t2 >= 0 && t2 <= CMath.ONE) {
//				System.err.println(String.format("22222222 t1:%s, t2: %s", t1, t2));
				if (ov != null) {
					ov.setResult(t2, 0);
					nearest(ov,rB, rDir, t2);
				}
				return true;
			}

			return false;
		}
	}

	/**
	 * ray-obb
	 * 
	 * @param src    rayStart
	 * @param dir  rayDir
	 * @param center    Box-center
	 * @param sizeDirRightTop size(length,width)
	 * @param deg  rotate degree
	 * @param ov nearest t
	 * @return
	 */
	public static boolean TestRayOBB(Position src, Dir dir, Position center, Dir sizeDirRightTop, float deg, OutValue ov) {
		// 计算从起点位置到中心点位置的朝向
		MapHelper.getDirBySrcAndDesPos(center, src, ov.obbCache.fo);
		CMath.Rotate(ov.obbCache.fo, deg, ov.obbCache.fo);
		CMath.Rotate(dir, deg, ov.obbCache.fd);
		ov.obbCache.fo.toPosition(ov.obbCache.fo2p);
		boolean r = TestRayAABB(ov.obbCache.fo2p, ov.obbCache.fd, sizeDirRightTop.negDir(ov.obbCache.sizeDirRightTopNegDir), sizeDirRightTop, ov, false);
		// 多个碰撞盒子有没有打中是通过 有没有击中点来判断的;这里没有打中也有击中点会导致多个碰撞盒子判断错误
		if (r && ov != null)
			nearest(ov, src, dir, ov.getT());
		return r;
	}

	/**
	 * ray-aabb
	 * 实现原理，可查看下面网址
	 *  <a href="https://blog.csdn.net/ring0hx/article/details/7677418">...</a>
	 *  <a href="https://blog.csdn.net/u012325397/article/details/50807880">...</a>
	 *  <a href="https://blog.csdn.net/i_dovelemon/article/details/38342739">...</a>
	 *
	 * @param o   rayStart
	 * @param dir rayDir
	 * @param min aabb-min
	 * @param max aabb-max
	 * @param ov  nearest- t
	 */
	public static boolean TestRayAABB(Position o, Dir dir, Dir min, Dir max, OutValue ov) {
		return TestRayAABB(o, dir, min, max, ov, true);
	}
	public static boolean TestRayAABB(Position o, Dir dir, Dir min, Dir max, OutValue ov, boolean calcNearest) {
		long tmin = Long.MIN_VALUE; // set to -FLT_MAX to get first hit on line
		long tmax = Long.MAX_VALUE; // set to max distance ray can travel (for segment)
//		Position d = new Position(dir.getX(), dir.getZ());
		// For all three slabs 0=x,1=y

		boolean xSwap = false;
		boolean zSwap = false;
		boolean minSwap = false;
		if (Math.abs(dir.getX()) < CMath.EPSILON_FIXED) {
			// Ray is parallel to slab. No hit if origin not within slab
			if (o.getX() < min.getX() || o.getX() > max.getX())
				return false;
		} else {
			// Compute intersection t value of ray with near and far plane of slab
			long ood = CMath.fixedDiv(CMath.ONE, dir.getX());
			long t1 = CMath.fixedMultiply(min.getX() - o.getX(), ood);
			long t2 = CMath.fixedMultiply(max.getX() - o.getX(), ood);
			// Make t1 be intersection with near plane, t2 with far plane
			if (t1 > t2) {
				xSwap = true;
				long temp = t1;
				t1 = t2;
				t2 = temp;
			}

			// Compute the intersection of slab intersections intervals
			tmin = Math.max(tmin, t1);
			tmax = Math.min(tmax, t2);
			// Exit with no collision as soon as slab intersection becomes empty
			if (tmin > tmax)
				return false;
		}

		if (Math.abs(dir.getZ()) < CMath.EPSILON_FIXED) {
			// Ray is parallel to slab. No hit if origin not within slab
			if (o.getZ() < min.getZ() || o.getZ() > max.getZ())
				return false;
		} else {
			// Compute intersection t value of ray with near and far plane of slab
			long ood = CMath.fixedDiv(CMath.ONE, dir.getZ());
			long t1 = CMath.fixedMultiply(min.getZ() - o.getZ(), ood);
			long t2 = CMath.fixedMultiply(max.getZ() - o.getZ(), ood);
			// Make t1 be intersection with near plane, t2 with far plane
			if (t1 > t2) {
				zSwap = true;
				long temp = t1;
				t1 = t2;
				t2 = temp;
			}

			if (t1 > tmin) minSwap = true;
			// Compute the intersection of slab intersections intervals
			tmin = Math.max(tmin, t1);
			tmax = Math.min(tmax, t2);
			// Exit with no collision as soon as slab intersection becomes empty
			if (tmin > tmax)
				return false;
		}

		// Does the ray start inside the box?
		// Does the ray intersect beyond the max fraction?
		// tmin > 1000 代表射线够不到盒子
		if(tmin > CMath.PRECISION) {
			return false;
		}

		int direction = 0; //1234 前后左右
		if (tmin < 0) {
			if (tmax < 0) {
				// 射线在盒子的上方并且往反方向打
				return false;
			}
			// 射线在盒子内部够不到盒子的出去的面的时候 tmax > CMath.PRECISION
			// 射线在盒子内部够得到盒子的出去的面的时候 tmax <= CMath.PRECISION
			tmin = Math.min(tmax, CMath.PRECISION);
		} else { // tim大于等于是说明射线是从盒子外面进来的
			if (minSwap) { //min交换过说明是从上或者下进入的;
				//zSwap 如果没有交换过默认是从下面射过来的;交换过就是从上面射过来的
				direction = zSwap ? CMath.Direction.UP : CMath.Direction.DOWN;
			} else {
				//xSwap 如果没有交换过默认是从左面射过来的;交换过就是从右面射过来的
				direction = xSwap ? CMath.Direction.RIGHT : CMath.Direction.LEFT;
			}
		}
		if (ov != null) {
			ov.setResult(tmin, direction);
			if (calcNearest) {
				nearest(ov, o, dir, tmin);
			}
		}


		return true;
	}


	public static boolean testCircleOBB(Position circleCenter, int circleRadius,
										Position obbCenter, int obbRadius, Dir obbSizeB, Position obbRadiusUP){
		Position diff = circleCenter.sub(obbCenter);
		int allRadius = circleRadius + obbRadius;
		//circle 判定CollisionHelper
		if (diff.lengthSqr() > (long) allRadius * allRadius) {
			return false;
		}

		//空间转换
		long absX = Math.abs(CMath.Dot2D(diff, new Position(obbRadiusUP.getZ(), -obbRadiusUP.getX())));
		long absY = Math.abs(CMath.Dot2D(diff, obbRadiusUP));

		long x = Math.max(absX - obbSizeB.getX(), 0);
		long y = Math.max(absY - obbSizeB.getZ(), 0);
		return x * x + y * y <= (long) circleRadius * circleRadius;
	}

	public static boolean testCircleCircle(Position posA, int rA, Position posB, int rB){
		Position diff = posA.sub(posB);
		long allRadius = rA + rB;
		return diff.lengthSqr() <= allRadius * allRadius;
	}
	public static boolean IsPointInCircle(Position c, int r, Position p) {
		return p.getDirectDistanceSquareIgnoreY(c) < (long) r * r;

	}

	public static boolean IsPointInPolygon(Position p,Position center, Dir sizeDir, float degree) {
		Position rotateDir = getUnityDirByRotateYFromX(p.sub(center), -degree);
		int x = sizeDir.getX();
		int z = sizeDir.getZ();
		return rotateDir.getX() > -x && rotateDir.getX() < x && rotateDir.getZ() > -z && rotateDir.getZ() < z;
	}


	/**
	 * 获取点到该线段的最短距离
	 */
	public static float distancePtSegSqrF2D(Position pt, Position p, Position q) {
		float pqx = (q.getX() - p.getX())*0.001f;
		float pqz = (q.getZ() - p.getZ())*0.001f;
		float dx = (pt.getX() - p.getX())*0.001f;
		float dz = (pt.getZ() - p.getZ())*0.001f;


		float d = dot2dFloat(pqx, pqz, pqx, pqz);
		float t = dot2dFloat(pqx, pqz, dx, dz);
		if (d > 0) t /= d;
		if (t < 0) t = 0;
		else if (t > 1) t = 1;
		dx = p.getX() * 0.001f + t * pqx - pt.getX() * 0.001f;
		dz = p.getZ() * 0.001f + t * pqz - pt.getZ() * 0.001f;
		return dx *dx + dz *dz;
	}
	private static float dot2dFloat(float x1, float z1, float x2, float z2) {
		return x1*x2+z1*z2;
	}

	/**
	 * 获得离该点最近的边
	 * 只有盒子有效
	 */
	public static int findMinDistancePointBox2D(Position p,Position center, Dir localSizeDir, float degree) {
		Position unityDirByRotateYFromX = getUnityDirByRotateYFromX(p.sub(center), degree);
		return pointAABBNearestSegment(unityDirByRotateYFromX, localSizeDir);
	}
	private static int pointAABBNearestSegment(Position p, Dir localSizeDir) {
		int minX =-localSizeDir.getX();
		int minZ = -localSizeDir.getZ();
		int maxX = localSizeDir.getX();
		int maxZ = localSizeDir.getZ();
		int resultX = 0;
		int resultZ = 0;

		int upDownSegment = 0;
		int leftRightSegment = 0;
		if (p.getX() < minX) {
			resultX = minX;
			leftRightSegment = CMath.Direction.LEFT;

		}
		if (p.getX() > maxX) {
			resultX = maxX;
			leftRightSegment = CMath.Direction.RIGHT;
		}
		if (p.getZ() < minZ) {
			resultZ = minZ;
			upDownSegment = CMath.Direction.DOWN;
		}
		if (p.getZ() > maxZ) {
			resultZ = maxZ;
			upDownSegment = CMath.Direction.UP;
		}


		if (upDownSegment == 0 && leftRightSegment == 0) { // 最近点在多边形内

		} else { // 最近点在多边形外面
			if (upDownSegment > 0 && leftRightSegment > 0) {
				// 长宽比 往左右两边偏的时候长短了宽不变; 那就是值小了; 往上下两边偏的话就是长长了宽度短了值变大了
				double currRadio = 1.0 * localSizeDir.getZ() / localSizeDir.getX();

				int x = leftRightSegment == CMath.Direction.RIGHT ? maxX : minX;
				int y = upDownSegment == CMath.Direction.UP ? maxZ : minZ;
				double i = 1.0 * Math.abs(p.getZ() - y) / Math.abs(p.getX() - x);

				if (i < currRadio) {
					upDownSegment = 0;
				} else {
					leftRightSegment = 0;
				}
			}
		}
		return upDownSegment + leftRightSegment;
	}

	/**
	 * return nerastPoint
	 * 
	 * @param start
	 * @param d
	 * @param t
	 * @return
	 */
	/*private static ModifyPosition nearest(Position start, Dir d, long t) {
		int destX = (int) (start.getX() + CMath.fixedMultiply(d.getX(), t));
		int destZ = (int) (start.getZ() + CMath.fixedMultiply(d.getZ(), t));
		return new ModifyPosition(destX, start.getY(), destZ);
	}*/

	public static ModifyPosition nearest(OutValue outValue, Position start, Dir d, long t) {
		int destX = (int) (start.getX() + CMath.fixedMultiply(d.getX(), t));
		int destZ = (int) (start.getZ() + CMath.fixedMultiply(d.getZ(), t));
		outValue.getModifyPos().setX(destX);
		outValue.getModifyPos().setZ(destZ);
		return outValue.getModifyPos();
	}


}
