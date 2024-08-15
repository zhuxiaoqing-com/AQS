package com.youxi.math.box2dGJK.shape;

import com.youxi.entity.Position;
import com.youxi.entity.Shape;

public class B2Circle extends B2Shape {
	private float regionRadius; // 半径


	public B2Circle(B2Vec2 center, float regionRadius) {
		super(center);
		this.regionRadius = regionRadius;
	}

	public float getRegionRadius() {
		return regionRadius;
	}

	public void setRegionRadius(float regionRadius) {
		this.regionRadius = regionRadius;
	}
}
