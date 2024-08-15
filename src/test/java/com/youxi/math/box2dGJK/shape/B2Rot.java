package com.youxi.math.box2dGJK.shape;

public class B2Rot {
	public float s; //sin
	public float c; //cos
	public float angle;

	public void Set(float angle) {
		s = (float) Math.sin(angle);
		c = (float) Math.cos(angle);
		this.angle = angle;
	}

}
