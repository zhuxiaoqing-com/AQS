package com.youxi.entity;

import com.youxi.building.Misc;

public class OBB2D {
	private Position center;
	private Dir sizeDir;
	private float dir;

	private Position p0;
	private Position p1;
	private Position p2;
	private Position p3;


	public Position getP0() {
		return p0;
	}

	public void setP0(Position p0) {
		this.p0 = p0;
	}

	public Position getP1() {
		return p1;
	}

	public void setP1(Position p1) {
		this.p1 = p1;
	}

	public Position getP2() {
		return p2;
	}

	public void setP2(Position p2) {
		this.p2 = p2;
	}

	public Position getP3() {
		return p3;
	}

	public void setP3(Position p3) {
		this.p3 = p3;
	}

	public Position getCenter() {
		return center;
	}


	public Dir getSizeDir() {
		return sizeDir;
	}

	public float getDir() {
		return dir;
	}


	public void setSizeDir(Dir sizeDir) {
		this.sizeDir = sizeDir;
	}

	public void setDir(float dir) {
		this.dir = dir;
	}

	public void setCenter(Position center) {
		this.center = center;
	}

	/**
	 * 矩形内随机一个点
	 */
	public Position randomPosition() {
		Position left = new Position(center.getX() - sizeDir.x, 0, center.getZ() - sizeDir.z);
		Position right = new Position(center.getX() + sizeDir.x, 0, center.getZ() + sizeDir.z);
		Position position = new Position(Misc.getRandomBetween(left.getX(), right.getX()), Misc.getRandomBetween(right.getZ(), left.getZ()));
		Position rotateDir = position.sub(center).getRotateDir(dir);
		Position add = rotateDir.add(center);
		return add;
	}

	public Position  randomPosition2() {
		Position position = new Position(Misc.getRandomBetween(-sizeDir.getX(), sizeDir.getX()), Misc.getRandomBetween(-sizeDir.getZ(), sizeDir.getZ()));
		Position rotateDir = position.getRotateDir(dir).add(center);
		return rotateDir;
	}
}
