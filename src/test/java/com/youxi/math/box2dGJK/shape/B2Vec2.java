package com.youxi.math.box2dGJK.shape;

import com.youxi.math.box2dGJK.B2Math;

import java.util.Objects;

public class B2Vec2 {

	public float x;

	public float y;


	public B2Vec2() {
	}

	public B2Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setXY(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public void setXY(B2Vec2 v) {
		this.x = v.getX();
		this.y = v.getY();
	}


	public B2Vec2 add(B2Vec2 B2Vec2) {
		return new B2Vec2(x + B2Vec2.x, y + B2Vec2.y);
	}

	public B2Vec2 sub(B2Vec2 B2Vec2) {
		return new B2Vec2(x - B2Vec2.x, y - B2Vec2.y);
	}


	public void subZeroGC(B2Vec2 B2Vec2) {
		x = x - B2Vec2.x;
		y = y - B2Vec2.y;
	}
	public void addZeroGC(B2Vec2 B2Vec2) {
		x = x + B2Vec2.x;
		y = y + B2Vec2.y;
	}


	public B2Vec2 multiply(float v) {
		return new B2Vec2(x * v, y * v);
	}


	public float cross(B2Vec2 vectorY) {
		return cross(this, vectorY);
	}

	public float dot(B2Vec2 vectorY) {
		return dot(this, vectorY);
	}
	public static float cross(B2Vec2 vectorX, B2Vec2 vectorY) {
		return vectorX.x * vectorY.y - vectorY.x * vectorX.y;
	}

	public static float dot(B2Vec2 a, B2Vec2 b) {
		return a.x * b.x + b.y * a.y;
	}

	public  float length() {
		return (float) Math.sqrt(x*x +y*y);
	}


	public void Set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void Set(B2Vec2 b2Vec2) {
		this.x = b2Vec2.getX();
		this.y = b2Vec2.getY();
	}


	public void SetZero() {
		this.x = 0.0f;
		this.y = 0.0f;
	}


	/// Get the length of this vector (the norm).
	public float Length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	/// Get the length squared. For performance, use this instead of
	/// b2Vec2::Length (if possible).
	public float LengthSquared(){
		return x * x + y * y;
	}

	/// Convert this vector into a unit vector. Returns the length.
	public B2Vec2 Normalize() {
		return B2Math.b2Normalize(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof B2Vec2)) return false;
		B2Vec2 b2Vec2 = (B2Vec2) o;
		return Float.compare(x, b2Vec2.x) == 0 && Float.compare(y, b2Vec2.y) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "{" +
				"" + x +
				", " + y +
				'}';
	}
}
