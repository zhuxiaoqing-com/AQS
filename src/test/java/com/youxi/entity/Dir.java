package com.youxi.entity;

import java.util.Objects;

/**
 * 朝向
 *
 * @author Eric.Ma
 */
public class Dir {
	public static final Dir ZERO = new Dir(0, 0, 0);
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

	public Dir(double x, double z) {
		this((float) x,(float)z);
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

	public Dir negDir(ModifyDir returnDir) {
		int x = -getX();
		int z = -getZ();
		returnDir.setX(x);
		returnDir.setZ(z);
		return returnDir;
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
		double oaLength = Math.sqrt((long) getX() * getX() + (long) getZ() * getZ());
		float oaNormalizeX = (float) (getX() / oaLength);
		float oaNormalizeZ = (float) (getZ() / oaLength);
		return new Dir(oaNormalizeX, 0, oaNormalizeZ);
	}

	public Dir getRotateDir(float radians) {
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		return new Dir((int) (getX() * cos - getZ() * sin),
				(int) ((getZ() * cos + getX() * sin)));
	}

	public int length() {
		return (int) Math.sqrt((long) getX() * getX() + (long)getZ() * getZ());
	}

	public long lengthSqr() {
		return( (long) getX() * getX() + (long)getZ() * getZ())/1000;
	}

	/**
	 * sub
	 *
	 * @param subPos
	 * @return
	 */
	public Dir sub(Position subPos) {
		return new Dir(getX() - subPos.getX(), 0, getZ() - subPos.getZ());
	}

	/**
	 * sub
	 *
	 */
	public Dir sub(Dir subDir) {
		return new Dir(getX() - subDir.getX(), 0, getZ() - subDir.getZ());
	}


	/**
	 *
	 *
	 * @param v
	 * @return
	 */
	public Dir multiply(float v) {
		return new Dir((int) (getX() * v), (int) (0 * v), (int) (getZ() * v));
	}

	public Dir add(Dir addDir) {
		return new Dir(getX() + addDir.x, getY() + addDir.y, getZ() + addDir.z);
	}

	public Dir add(Position position) {
		return new Dir(getX() + position.getX(), getY() + position.getY(), getZ() + position.getZ());
	}

	public ModifyPosition toPosition(ModifyPosition returnPos) {
		returnPos.setX(x);
		returnPos.setY(y);
		returnPos.setZ(z);
		return returnPos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Dir)) return false;
		Dir dir = (Dir) o;
		return x == dir.x && z == dir.z && y == dir.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, z, y);
	}
}