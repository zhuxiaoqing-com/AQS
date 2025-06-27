package com.youxi.entity;

import com.youxi.entity.Position;

public class Circle extends Shape {
	private long regionRadiusSquare; // 半径的平方
	private int regionRadius; // 半径



	public Circle(Position centerPos, int radius) {
		super(centerPos);
		this.regionRadius = radius;
		this.regionRadiusSquare = (long) radius * radius;
		this.margin = radius;
	}

	public boolean inRegion(Position pos) {
		return pos.getDirectDistanceSquare(center) < regionRadiusSquare;
	}



	public long getRegionRadiusSquare() {
		return regionRadiusSquare;
	}

	public int getRegionRadius() {
		return regionRadius;
	}

}
