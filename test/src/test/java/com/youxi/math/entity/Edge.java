package com.youxi.math.entity;

import com.youxi.entity.Position;

public class Edge {
	Position start;
	Position end;
	Position normal; // 垂足
	long distance; // 长度
	private int index;

	public Edge(Position start, Position end) {
		this.start = start;
		this.end = end;
	}

	public Position getStart() {
		return start;
	}

	public Position getEnd() {
		return end;
	}

	public void setStart(Position start) {
		this.start = start;
	}

	public void setEnd(Position end) {
		this.end = end;
	}

	public Position getNormal() {
		return normal;
	}

	public void setNormal(Position normal) {
		this.normal = normal;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
