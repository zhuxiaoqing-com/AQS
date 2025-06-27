package com.youxi.entity;

import com.youxi.xstream.A;

public class Shape {

	public Position center;

	public Shape(Position center) {
		this.center = center;
	}

	public Position getCenter() {
		return center;
	}

	public void setCenter(Position center) {
		this.center = center;
	}


	public int margin; //gjk使用 差额

	public Position support(Dir dir) {
		return center;
	}
}
