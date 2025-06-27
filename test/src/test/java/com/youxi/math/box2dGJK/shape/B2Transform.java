package com.youxi.math.box2dGJK.shape;


public class B2Transform {
	public B2Vec2 p = new B2Vec2(0,0);
	public B2Rot q = new B2Rot();

	public void Set(B2Vec2 p, float angle) {
		this.p = p;
		this.q = new B2Rot();
		q.Set(angle);
	}
}
