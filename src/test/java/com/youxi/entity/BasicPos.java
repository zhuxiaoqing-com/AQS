package com.youxi.entity;

/**
 * 基本坐标信息
 * 
 */
public abstract class BasicPos {

	public static final int DIR_UNKNOW = -1; // 出错
	public static final int DIR_SELF = 0; // 就是自己
	public static final int DIR_UP = 1; // 上
	public static final int DIR_RIGHTUP = 2; // 右上
	public static final int DIR_RIGHT = 3; // 右
	public static final int DIR_RIGHTDOWN = 4; // 右下
	public static final int DIR_DOWN = 5; // 下
	public static final int DIR_LEFTDOWN = 6; // 左下
	public static final int DIR_LEFT = 7; // 左
	public static final int DIR_LEFTUP = 8; // 左上
	public static final int DIR_NUM = 8; // 全部方向的数目


	private int x;
	private int y;
	private int z;

	public BasicPos(float x0, float y0, float z0) {
		x = (int) (x0 * 1000);
		y = (int) (y0 * 1000);
		z = (int) (z0 * 1000);
	}

	public BasicPos(int x0, int y0, int z0) {
		x = x0;
		y = y0;
		z = z0;
	}

	/**
	 * 默认z=0
	 * 
	 * @param x0
	 * @param y0
	 */
	public BasicPos(float x0, float y0) {
		x = (int) (x0 * 1000);
		z = (int) (y0 * 1000);
		y = 0;
	}

	public BasicPos(int x0, int y0) {
		x = x0;
		z = y0;
		y = 0;
	}

	public int length() {
		return (int) Math.sqrt((long) getX() * getX() + (long)getZ() * getZ());
	}

	public long lengthSqr() {
		return( (long) getX() * getX() + (long)getZ() * getZ())/1000;
	}
	/**
	 * @param   this 起点
	 * @param o 终点
	 * @return 两点间距离
	 */
	public int getDistance(final BasicPos o) {
		if (!o.getClass().equals(this.getClass()))
			throw new IllegalArgumentException("不同的坐标类型");

		final int longValue = Math.abs((getX() - o.getX()));
		final int widthValue = Math.abs(getY() - o.getY());
		final int highValue = Math.abs(getZ() - o.getZ());

		int tmp = longValue > widthValue ? longValue : widthValue;
		return tmp > highValue ? tmp : highValue;
	}

	public int getXZRelateDistance(final BasicPos o) {
		if (!o.getClass().equals(this.getClass()))
			throw new IllegalArgumentException("不同的坐标类型");

		final int longValue = Math.abs((getX() - o.getX()));
		final int widthValue = Math.abs(getZ() - o.getZ());

		return Math.max(longValue, widthValue);
	}

	public int getDistanceIgnoreY(final BasicPos o) {
		if (!o.getClass().equals(this.getClass()))
			throw new IllegalArgumentException("不同的坐标类型");

		final int xValue = Math.abs((getX() - o.getX()));
		final int zValue = Math.abs(getZ() - o.getZ());

		return Math.max(xValue, zValue);
	}

	/**
	 * 获取到另一个点的像素直线距离
	 * 
	 * @param to
	 * @return
	 */
	public int getDirectDistance(BasicPos to) {
		if (!to.getClass().equals(this.getClass()))
			throw new IllegalArgumentException("不同的坐标类型");

		int decx = this.getX() - to.getX();
		int dscy = this.getY() - to.getY();
		int decz = this.getZ() - to.getZ();
		return (int) Math.sqrt((double) ((long) decx * decx + (long) dscy * dscy + (long) decz * decz));
	}

	/**
	 * 获得直线距离的平方，不开方式为了保障效率
	 * 
	 * @param to
	 * @return
	 */
	public long getDirectDistanceSquare(BasicPos to) {
		if (!to.getClass().equals(this.getClass()))
			throw new IllegalArgumentException("不同的坐标类型");

		int decx = this.getX() - to.getX();
		int dscy = this.getY() - to.getY();
		int decz = this.getZ() - to.getZ();
		return (long) decx * decx + (long) dscy * dscy + (long) decz * decz;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}

	/**
	 * 判断相邻的一个点在所在的方向
	 * 
	 * @param w 给定的参考点
	 * @return w 在 this 的哪个方向上
	 */
	public int where(final BasicPos w) {
		if (x == w.x && w.z == z)
			return DIR_SELF;
		else if (x == w.x && z + 1 == w.z)
			return DIR_UP;
		else if (x + 1 == w.x && z + 1 == w.z)
			return DIR_RIGHTUP;
		else if (x + 1 == w.x && w.z == z)
			return DIR_RIGHT;
		else if (x + 1 == w.x && z == w.z + 1)
			return DIR_RIGHTDOWN;
		else if (x == w.x && z == w.z + 1)
			return DIR_DOWN;
		else if (w.x + 1 == x && w.z + 1 == z)
			return DIR_LEFTDOWN;
		else if (w.x + 1 == x && w.z == z)
			return DIR_LEFT;
		else if (w.x + 1 == x && w.z == z + 1)
			return DIR_LEFTUP;

		// 对于不相邻点
		return DIR_UNKNOW;

	}

	public boolean isAround(final BasicPos o) {
		if (isSamePos(o))
			return true;

		final int dir = where(o);
		if (dir < 0)
			return false;
		if (dir > DIR_NUM)
			return false;

		return true;
	}

	/**
	 * 获取某方向的相反方向
	 * 
	 * @param dir 给定的方向
	 * @return 相反方向
	 */
	public static int getReverseDir(int dir) {
		if (dir == 4)
			return 8;

		return (dir + 4) % BasicPos.DIR_NUM;
	}

	@Override
	public int hashCode() {
		return (x << 18) + (y << 4) + z;
	}

	public boolean equals(BasicPos o) {

		if (this == o)
			return true;

		if (!this.getClass().equals(o.getClass()))
			return false;

		return x == o.x && y == o.y && z == o.z;
	}

	public boolean isSamePos(BasicPos o) {
		return this.equals(o);
	}

	public boolean is2dSamePos(BasicPos o) {
		return o.getX() == x && o.getZ() == z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int[] toArray() {
		return new int[] { x, y, z };
	}
}
