package com.youxi.math.box2dGJK;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class B2ShapeCastInput {
	b2ShapeProxy proxyA;
	b2ShapeProxy proxyB;
	B2Vec2 translationB;

	public B2ShapeCastInput(b2ShapeProxy proxyA, b2ShapeProxy proxyB, B2Vec2 translationB) {
		this.proxyA = proxyA;
		this.proxyB = proxyB;
		this.translationB = translationB;
	}
}
