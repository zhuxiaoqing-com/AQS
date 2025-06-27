package com.youxi.entity;

import com.youxi.building.Misc;
import org.apache.regexp.RE;

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

	@Override
	public Position support(Dir supportDir) {
		int x = supportDir.getX() > 0 ? sizeDir.getX() : -sizeDir.getX();
		int z = supportDir.getZ() > 0 ? sizeDir.getZ() : -sizeDir.getZ();
		Position position = new Position(x, z);
		Position rotateDir = position.getRotateDir(dir);
		return center.add(rotateDir);
	}

	@Override
	public String toString() {
		return "OBB2D{" +
				", center=" + center +
				"sizeDir=" + sizeDir +
				", dir=" + dir +
				'}';
	}

	/**
	 * 假设 我们的obb是旋转的
	 *
	 * 要将p变换成obb的局部坐标轴;
	 * 其实就相当于 obb反向旋转degree度,
	 * 由于OBB在全局坐标系中是旋转的，我们需要通过旋转的逆变换（即旋转角度的反向应用）来将全局坐标系中的点转换到OBB的局部坐标系中。
	 * 这样做的原因是，旋转的逆变换可以将OBB的旋转“撤销”，使得转换后的点在局部坐标系中与OBB的方向对齐。
	 *
	 * 所以p旋转-degree是为了将其转换为obb的局部坐标系
	 *
	 * 为什么是逆变换,因为obb的当前中心点是世界坐标系进行旋转degree角度以后的坐标,
	 * 将其变换回局部坐标系的话,需要将整个当前坐标系里面的坐标全部反向应用degree，这样就变换成obb的局部坐标系了
	 *
	 * 为什么p要旋转-degree呢;
	 * 因为p这里是在obb有旋转的情况下的值,如果obb是局部坐标 那就相当于忽略了旋转;
	 * p也得是忽略旋转才行;需要反向旋转用来忽略旋转;
	 *
	 *
	 * 或者说obb是旋转的  那就将整个坐标系反向旋转来将其obb变换为没有旋转的情况：
	 * 那点p也是反向旋转.
	 * 其实要将其变为obb的局部坐标 也等于 将整个坐标系反向旋转这个角度
	 */
	public static boolean IsPointInPolygon(Position p,Position center, Dir sizeDir, float degree) {
		// 为什么这里是 -degree 而不是degree  因为下面已经默认Obb变换过-degree了 这里将点也同样应用,那他们就在一个坐标系内了
		Position rotateDir = CMath.getUnityDirByRotateYFromX(p.sub(center), -degree);
		int x = sizeDir.getX();
		int z = sizeDir.getZ();
		return rotateDir.getX() > -x && rotateDir.getX() < x && rotateDir.getZ() > -z && rotateDir.getZ() < z;
	}
}
