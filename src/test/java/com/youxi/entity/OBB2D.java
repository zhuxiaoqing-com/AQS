package com.youxi.entity;

import com.youxi.building.Misc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OBB2D extends Shape {
	private Dir sizeDir;
	private float dir;

	private Position p0;
	private Position p1;
	private Position p2;
	private Position p3;

	public OBB2D(Position center) {
		super(center);
	}

	public OBB2D(Position center, Dir sizeDir, float dir) {
		super(center);
		this.sizeDir = sizeDir;
		this.dir = dir;


	}

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

	public List<Position> getPoints() {
		Position center = getCenter();
		Dir[] localSizeDirArray = new Dir[4];
		localSizeDirArray[0] = new Dir(-sizeDir.getX(), -sizeDir.getZ());// -x -z
		localSizeDirArray[1] = new Dir(-sizeDir.getX(), sizeDir.getZ());// -x +z
		localSizeDirArray[2] = new Dir(sizeDir.getX(), sizeDir.getZ());// +x +z
		localSizeDirArray[3] = new Dir(sizeDir.getX(), -sizeDir.getZ());// +x -z

		Position[] points = new Position[4];

		Dir dir0 = CMath.getUnityDirByRotateYFromX(localSizeDirArray[0], dir);
		Dir dir1 = CMath.getUnityDirByRotateYFromX(localSizeDirArray[1], dir);
		Dir dir2 = CMath.getUnityDirByRotateYFromX(localSizeDirArray[2], dir);
		Dir dir3 = CMath.getUnityDirByRotateYFromX(localSizeDirArray[3], dir);
		points[0] = center.add(dir0);
		points[1] = center.add(dir1);
		points[2] = center.add(dir2);
		points[3] = center.add(dir3);
		return Arrays.stream(points).collect(Collectors.toList());
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
