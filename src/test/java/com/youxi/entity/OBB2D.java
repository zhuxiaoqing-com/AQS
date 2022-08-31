package com.youxi.entity;

public class OBB2D {
	private Position size;

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
		return size;
	}

	public void setSize(Position size) {
		this.size = size;
	}
}
