package com.youxi.math.box2dGJK.shape;


import com.youxi.entity.Dir;
import com.youxi.entity.Position;

public class B2Shape {

	public B2Vec2 center;

	public B2Shape(B2Vec2 center) {
		this.center = center;
	}

	public B2Vec2 getCenter() {
		return center;
	}

	public void setCenter(B2Vec2 center) {
		this.center = center;
	}
}
