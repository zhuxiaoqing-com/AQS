package com.youxi.math.box2dGJK;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class B2SimplexVertex {
	public B2Vec2 wA;	// proxyA的顶点
	public B2Vec2 wB;	// proxyB的顶点
	public B2Vec2 w;	// 这个是支撑点，也可能是单纯形的一个顶点，等于 wB - wA
	public float a;		// 参数，用于求重心坐标的
	public int indexA;	// wA的索引
	public int indexB;	// wB的索引



	public B2SimplexVertex() {
	}

	public B2SimplexVertex(B2Vec2 wA, B2Vec2 wB, B2Vec2 w, float a, int indexA, int indexB) {
		this.wA = wA;
		this.wB = wB;
		this.w = w;
		this.a = a;
		this.indexA = indexA;
		this.indexB = indexB;
	}

	public B2Vec2 getwA() {
		return wA;
	}

	public void setwA(B2Vec2 wA) {
		this.wA = wA;
	}

	public B2Vec2 getwB() {
		return wB;
	}

	public void setwB(B2Vec2 wB) {
		this.wB = wB;
	}

	public B2Vec2 getW() {
		return w;
	}

	public void setW(B2Vec2 w) {
		this.w = w;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public int getIndexA() {
		return indexA;
	}

	public void setIndexA(int indexA) {
		this.indexA = indexA;
	}

	public int getIndexB() {
		return indexB;
	}

	public void setIndexB(int indexB) {
		this.indexB = indexB;
	}


}
