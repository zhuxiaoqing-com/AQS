package com.youxi.dataStructure.自己松散四叉树;

import com.example.demo1.util.vo.Vector2f;
import com.youxi.entity.Position;

public class MyLRect {
	public int x; // 左下角的点
	public int z; //  左下角的点

	public int maxX;
	public int maxZ;

	public MyLRect(int x, int z, int maxX, int maxZ) {
		this.x = x;
		this.z = z;
		this.maxX = maxX;
		this.maxZ = maxZ;
	}

	public MyLRect(Position minxy, Position size) {
		this.x = minxy.getX();
		this.z = minxy.getZ();
		this.maxX = size.getX();
		this.maxZ = size.getZ();
	}



	public boolean contain(int checkX, int chexkZ) {
		return x <= checkX && z <= chexkZ && maxX >= checkX && maxZ >= chexkZ;
	}

	public boolean contain(MyLRect myLRect) {
		return x <= myLRect.x && z <= myLRect.z && maxX >= myLRect.maxX && maxZ >= myLRect.maxZ;
	}


	/**
	 * 两个aabb有重叠
	 * 想象下两个aabb 有重叠; 上重叠和下重叠;他们的共同的地方是 other.maxPoint > minPoint && other.minPoint < maxPoint
	 *
	 * 最大和最小点相交 不算相交;
	 */
	public boolean overlaps(MyLRect other) {
		return other.maxX > x && other.maxZ > z && other.x < maxX && other.maxZ < maxZ;
	}


	public Position getCenter() {
		return new Position((x + maxX) / 2, (z + maxZ) / 2);
	}

}
