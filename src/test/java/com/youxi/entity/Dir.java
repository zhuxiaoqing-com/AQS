package com.youxi.entity;

/**
 * 朝向
 *
 * @author Eric.Ma
 */
public class Dir {
	public int x; // x
	public int z; // z

	public int y; // y

	public Dir() {
	}

	public Dir(int x, int z) {
		super();
		this.x = x;
		this.z = z;
	}

	public Dir(float x, float z) {
		this.x = (int) (x * CMath.PRECISION);
		this.z = (int) (z * CMath.PRECISION);
	}

	public Dir(float x, float y, float z) {
		this.x = (int) (x * CMath.PRECISION);
		this.z = (int) (z * CMath.PRECISION);
		this.y = (int) (y * CMath.PRECISION);
	}

	public Dir(int x, int y, int z) {
		this.x = x;
		this.z = z;
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setZ(float z) {
		this.z = (int) (z * CMath.PRECISION);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setY(float y) {
		this.y = (int) (y * CMath.PRECISION);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setX(float x) {
		this.x = (int) (x * CMath.PRECISION);
	}

	public Dir copy() {
		return new Dir(getX(), getY(), getZ());
	}

	public Dir negDir() {
		return new Dir(-getX(), -getZ());
	}

	public Dir addDir(Dir add) {
		return new Dir(this.getX() + add.getX(), this.getY() + add.getY(), this.getZ() + add.getZ());
	}

	public Position toPosition() {
		return new Position(getX(), getZ());
	}

	@Override
	public String toString() {
		return new StringBuilder("Dir(").append(getX()).append(",").append(getY()).append(",").append(getZ())
				.append(")").toString();
	}

	public Dir normalize() {
		double oaLength = Math.sqrt(getX() * getX() + getZ() * getZ());
		float oaNormalizeX = (float) (getX() / oaLength);
		float oaNormalizeZ = (float) (getZ() / oaLength);
		return new Dir(oaNormalizeX, 0, oaNormalizeZ);
	}

}