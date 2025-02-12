package com.youxi.entity;

/**
 * 2d碰撞数学库
 * 
 * @author Eric.Ma
 *
 */
public class CMath {

	public static int PRECISION = 1000;
	public static int HALF_PRECISION = PRECISION / 2;
	public static float PRECISION_FACTOR = 0.001f;
	public static int ONE = PRECISION;
	public static int HALF = ONE / 2;
	public static final int negOne = -PRECISION;

	// 定点数
	public static final int EPSILON_FIXED = 1;

	public static long Cross2D(Position u, Position v) {
		return (long) (u.getX() * v.getZ() * PRECISION_FACTOR) - (long) (u.getZ() * v.getX() * PRECISION_FACTOR);
	}

	public static long Dot2D(Position d1, Dir d2) {
		return Dot2D(d1.getX(), d1.getZ(), d2.getX(), d2.getZ());
	}

	public static long Dot2D(Position d1, Position d2) {
		return Dot2D(d1.getX(), d1.getZ(), d2.getX(), d2.getZ());
	}

	public static long Dot2D(long x1, long z1, long x2, long z2) {
		return (long) (x1 * x2 * PRECISION_FACTOR) + (long) (z1 * z2 * PRECISION_FACTOR);
	}

	public static long Cross2D(long x1, long z1, long x2, long z2) {
		return (long) (x1 * z2 * PRECISION_FACTOR) - (long) (z1 * x2 * PRECISION_FACTOR);
	}

	public static Position Cross3D(Position a, Position b)
	{
		return new Position(
				((long) a.getY() * b.getZ() - (long) a.getZ() * b.getY()) * PRECISION_FACTOR,
				((long) a.getZ() * b.getX() - (long) a.getX() * b.getZ()) * PRECISION_FACTOR,
				((long) a.getX() * b.getY() - (long) a.getY() * b.getX())* PRECISION_FACTOR
		);
	}

	public static long fixedDiv(long a, long b) {
		return a * PRECISION / b;
	}

	public static long fixedMultiply(long a, long b) {
		return (long) (a * b * PRECISION_FACTOR);
	}

	public static long square(long v) {
		return fixedMultiply(v, v);
	}

	public static long sqrt(long v) {
		return (long) (Math.sqrt(v * PRECISION));
	}

	/**
	 * 获得两个向量的夹角.返回值为度数
	 *
	 * @param x1
	 * @param z1
	 * @param x2
	 * @param z2
	 * @return Position1 在 position2 的顺时针方向 角度为正数; 逆时针方向为负数
	 */
	public static double getDegree(long x1, long z1, long x2, long z2) {
		double n = CMath.Dot2D(x1, z1, x2, z2);
		double cc = CMath.Cross2D(x1, z1, x2, z2);

		//(x1*x1 + z1*z1 ) *(x2*x2 + z2*z2 )
		/*double m = CMath.fixedMultiply(CMath.sqrt(CMath.square(x1) + CMath.square(z1)),
				CMath.sqrt(CMath.square(x2) + CMath.square(z2)));*/

		double m = (Math.sqrt(x1*x1+z1*z1) * Math.sqrt(x2*x2+z2*z2))*CMath.PRECISION_FACTOR;
		if (m == 0)
			return 0; // 正常情况不会出现这样，除非朝向向量为空。

		// 因为改为定点数以后有误差，导致cos值可能超过1，所以做矫正在-1，1范围内
		double ac = Math.min(Math.max(-1, n / m), 1);
		return Math.acos(ac) * (180 / Math.PI) * (cc > 0 ? -1 : 1);
	}

	public static double getDegree(Dir dir1, Dir dir2) {
		return getDegree(dir1.getX(), dir1.getZ(), dir2.getX(), dir2.getZ());
	}
	public static double getDegree(Position dir1, Position dir2) {
		return getDegree(dir1.getX(), dir1.getZ(), dir2.getX(), dir2.getZ());
	}

	/**
	 * a向量转Unity欧拉角
	 *
	 * @param dir
	 * @return
	 */
	public static float getUnityRotateYDegree(Dir dir) {
		// 从(0,1)到dir的角度就是unity的欧拉角,1000为定点数
		return (float) getDegree(0, 1000, dir.getX(), dir.getZ());
	}
	public static Dir getUnityDirByRotateYFromX(Dir dir, float degree) {
		float radians = (float) Math.toRadians(-degree);
		// 因为unity中朝向是从相对z顺时针转动，所以上面1，0，下面逆时针算法做一次取负
		return getRotateDir(dir, radians);
	}

	public static Position getUnityDirByRotateYFromX(Position position, float degree) {
		float radians = (float) Math.toRadians(-degree);
		// 因为unity中朝向是从相对z顺时针转动，所以上面1，0，下面逆时针算法做一次取负
		return getRotateDir(position, radians);
	}

	/**
	 * Unity角度转向量 a根据绕y轴角度偏移算出来向量，其实坐标上就体现为x和z （用于策划配置比如怪物的初始朝向）
	 *
	 * @return
	 */
	public static Dir getUnityDirByRotateYFromX(float degree) {
		// 定点数
		Dir dirXZ = new Dir(0, 1000);
		float radians = (float) Math.toRadians(-degree);
		// 因为unity中朝向是从相对z顺时针转动，所以上面1，0，下面逆时针算法做一次取负
		return getRotateDir(dirXZ, radians);
	}
	/**
	 * (x*cosθ- y * sinθ, y*cosθ + x * sinθ)， 这只是点绕原点的计算公式，计算向量时要先把起点假设为原点。逆时针时θ为正数，
	 * 顺时针是θ为负数
	 *
	 * @param dir
	 * @param radians 弧度 0~2PI
	 * @return
	 */
	public static Dir getRotateDir(Dir dir, float radians) {
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		return new Dir((int) (dir.getX() * cos - dir.getZ() * sin),
				(int) ((dir.getZ() * cos + dir.getX() * sin)));
	}



	public static Position getRotateDir(Position pos, float radians) {
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		return new Position((int) (pos.getX() * cos - pos.getZ() * sin),
				(int) ((pos.getZ() * cos + pos.getX() * sin)));
	}

	public static float[] getRotateDir(float x, float z, float radians) {
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		return new float[]{(float) (x * cos - z * sin), (float) (z * cos + x * sin)};
	}



	public static long min(long... value) {
		long min = Long.MAX_VALUE;
		for (long l : value) {
			if(l< min) {
				min = l;
			}
		}
		return min;
	}

	public static long max(long... value) {
		long max = Long.MIN_VALUE;
		for (long l : value) {
			if(l> max) {
				max = l;
			}
		}
		return max;
	}

	public static void main(String[] args) {
//		1876,1384,

		long d = Dot2D(1876, 1384, 1876, 1384);
		System.err.println(d);
	}
}
