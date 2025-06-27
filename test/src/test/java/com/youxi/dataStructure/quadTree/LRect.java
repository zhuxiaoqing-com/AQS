package com.youxi.dataStructure.quadTree;

import com.example.demo1.util.vo.Vector2f;

public class LRect {
	// 左下角 x,y
	public float x;
	public float y;
	public float xMax;
	public float yMax;

	public LRect(float x, float y, float width, float heigh) {
		this.x = x;
		this.y = y;
		this.xMax = x + width;
		this.yMax = y + heigh;
	}

	public LRect(Vector2f position, Vector2f size) {
		this.x = position.x;
		this.y = position.y;
		this.xMax = x + size.x;
		this.yMax = y + size.y;
	}


	public static LRect CreateRect(Vector2f center, Vector2f halfSize) {
		return new LRect(center.sub(halfSize), halfSize.multiply(2));
	}



	public void Set(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.xMax = x + width;
		this.yMax = y + height;
	}

	public Vector2f getPosition() {
		return new Vector2f(x, y);
	}

	public Vector2f getCenter() {
		return new Vector2f((x + xMax) / 2, (y + yMax) / 2);
	}

	/// <summary>
	///   <para>The position of the minimum corner of the rectangle.</para>
	/// </summary>
	public Vector2f min() {

		return new Vector2f(this.x, this.y);
	}


	/// <summary>
	///   <para>The position of the maximum corner of the rectangle.</para>
	/// </summary>
	public Vector2f max() {
		return new Vector2f(this.xMax, this.yMax);
	}


	/// <summary>
	///   <para>The width and height of the rectangle.</para>
	/// </summary>
	public Vector2f size() {
		return new Vector2f(xMax - x, yMax - y);
	}


	public Vector2f halfSize() {
		return new Vector2f(xMax - x, yMax - y).multiply(0.5f);
	}


	public boolean Contains(Vector2f point) {
		return point.x >= this.x && point.x < this.xMax &&
				point.y >= this.y && point.y < this.yMax;
	}


	private static LRect OrderMinMax(LRect rect) {
		if (rect.x > rect.xMax) {
			float xMin = rect.x;
			rect.x = rect.xMax;
			rect.xMax = xMin;
		}

		if (rect.y > rect.yMax) {
			float yMin = rect.y;
			rect.y = rect.yMax;
			rect.yMax = yMin;
		}

		return rect;
	}

	/// <summary>
	///   <para>Returns true if the other rectangle overlaps this one. If allowInverse is present and true, the widths and heights of the LRects are allowed to take negative values (ie, the min value is greater than the max), and the test will still work.</para>
	/// </summary>
	/// <param name="other">Other rectangle to test overlapping with.</param>
	/// <param name="allowInverse">Does the test allow the widths and heights of the LRects to be negative?</param>
	public boolean Overlaps(LRect other) {
		return
				other.xMax > this.x
						&& other.x < this.xMax
						&& other.yMax > this.y
						&& other.y < this.yMax;
	}

}
