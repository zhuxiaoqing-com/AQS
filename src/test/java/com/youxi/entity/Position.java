package com.youxi.entity;

/**
 * 逻辑坐标 (当前角色坐标就是逻辑坐标) 服务器与客户端通信的坐标均是该类型
 * 
 */
public final class Position extends BasicPos {

	public static final Position ZERO = new Position(0, 0, 0);
	public Position(float x, float y, float z) {
		super(x, y, z);
	}

	public Position(int x, int y, int z) {
		super(x, y, z);
	}

	public Position(float x, float y) {
		super(x, y);
	}

	public Position(double x, double y) {
		super((float) x, (float)y);
	}


	public Position(int x, int y) {
		super(x, 0, y);
	}

	/**
	 * @param o 终点
	 * @return 中间点
	 */
	public Position getMidPos(final Position o) {
		return new Position((getX() + o.getX()) / 2, (getY() + o.getY()) / 2, getZ());
	}

	@Override
	public String toString() {
		return new StringBuilder("Pos(").append(getX()).append(",").append(getY()).append(",").append(getZ())
				.append(")").toString();
	}
	public Position copy() {
		return new Position(getX(), getY(), getZ());
	}

	public long getDirectDistanceSquareIgnoreY(BasicPos to) {
		if (!to.getClass().equals(this.getClass()))
			throw new IllegalArgumentException("不同的坐标类型");

		int decx = this.getX() - to.getX();
		int decz = this.getZ() - to.getZ();
		return (long) decx * decx + (long) decz * decz;
	}

	/**
	 * sub
	 * 
	 * @param subPos
	 * @return
	 */
	public Position sub(Position subPos) {
		return new Position(getX() - subPos.getX(), 0, getZ() - subPos.getZ());
	}

	/**
	 *
	 *
	 * @param v
	 * @return
	 */
	public Position multiply(float v) {
		return new Position((int) (getX() * v), (int) (0 * v), (int) (getZ() * v));
	}

	public Position add(Dir addDir) {
		return new Position(getX() + addDir.x, getY() + addDir.y, getZ() + addDir.z);
	}

	public Position add(Position position) {
		return new Position(getX() + position.getX(), getY() + position.getY(), getZ() + position.getZ());
	}

	public Dir toDir() {
		return new Dir(getX(),getY(),getZ());
	}


	public boolean isInvalid() {
		return getX() == 0 && getY() == 0 && getZ() == 0;
	}


	public boolean relativeEquals(Position o) {
		int distanceIgnoreY = this.getDistanceIgnoreY(o);

		// 由于精度问题允许50的误差
		return distanceIgnoreY <= 50;
	}

	public Position normalize() {
		double oaLength = Math.sqrt((long) getX() * getX() + (long) getZ() * getZ());
		float oaNormalizeX = (float) (getX() / oaLength);
		float oaNormalizeZ = (float) (getZ() / oaLength);
		return new Position(oaNormalizeX, 0, oaNormalizeZ);
	}
	public Position getRotateDir(float radians) {
		radians = (float) Math.toRadians(radians);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		return new Position((int) (getX() * cos - getZ() * sin),
				(int) ((getZ() * cos + getX() * sin)));
	}

	public long dot(Position p) {
		return CMath.Dot2D(this,p);
	}
}
