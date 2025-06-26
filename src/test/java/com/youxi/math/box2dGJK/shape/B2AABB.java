package com.youxi.math.box2dGJK.shape;

import com.youxi.math.box2dGJK.B2Math;

public class B2AABB {

    public B2Vec2 center = new B2Vec2();
    public B2Vec2 halfB2Vec2 = new B2Vec2();
    public B2Vec2 lowerBound = new B2Vec2();	///< the lower vertex
    public B2Vec2 upperBound = new B2Vec2();	///< the upper vertex

    public B2AABB() {
    }

    public B2AABB(B2Vec2 lowerBound, B2Vec2 upperBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.center = lowerBound.add(upperBound).multiply(0.5f);
        this.halfB2Vec2 = upperBound.sub(lowerBound).multiply(0.5f);
    }

    /// Verify that the bounds are sorted.
   public boolean IsValid(){
        return true;
    }

    /// Get the center of the AABB.
    public B2Vec2 GetCenter()
    {
        //return 0.5f * (lowerBound + upperBound);
        //return lowerBound.add(upperBound).multiply(0.5f);
        return center;
    }

    /// Get the extents of the AABB (half-widths).
    public B2Vec2 GetExtents()
    {
        //return 0.5f * (upperBound - lowerBound);
        //return upperBound.sub(lowerBound).multiply(0.5f);
        return halfB2Vec2;
    }

    /// Get the perimeter length 得到周长
  public  float GetPerimeter()
    {
        float wx = upperBound.x - lowerBound.x;
        float wy = upperBound.y - lowerBound.y;
        return 2.0f * (wx + wy);
    }

    /// Combine an AABB into this one.
   public void Combine(B2AABB  aabb)
    {
        lowerBound = B2Math.b2Min(lowerBound, aabb.lowerBound);
        upperBound = B2Math.b2Max(upperBound, aabb.upperBound);
    }

    /// Combine two AABBs into this one. 把两个aabb组合成一个。
   public void Combine(B2AABB aabb1, B2AABB aabb2)
    {
        lowerBound = B2Math.b2Min(aabb1.lowerBound, aabb2.lowerBound);
        upperBound = B2Math.b2Max(aabb1.upperBound, aabb2.upperBound);
    }

    /// Does this aabb contain the provided AABB.
  public  boolean Contains(B2AABB aabb) {
      boolean result = true;
        result = result && lowerBound.x <= aabb.lowerBound.x;
        result = result && lowerBound.y <= aabb.lowerBound.y;
        result = result && aabb.upperBound.x <= upperBound.x;
        result = result && aabb.upperBound.y <= upperBound.y;
        return result;
    }



    public void copy(B2AABB aabb) {
        lowerBound.setX(aabb.lowerBound.x);
        lowerBound.setY(aabb.lowerBound.y);
        upperBound.setX(aabb.upperBound.x);
        upperBound.setY(aabb.upperBound.y);
    }

    public void CombineZeroCG(B2AABB aabb1, B2AABB aabb2)
    {
        lowerBound.setX(Math.min(aabb1.lowerBound.x, aabb2.lowerBound.x));
        lowerBound.setY(Math.min(aabb1.lowerBound.y, aabb2.lowerBound.y));
        upperBound.setX(Math.max(aabb1.upperBound.x, aabb2.upperBound.x));
        upperBound.setY(Math.max(aabb1.upperBound.y, aabb2.upperBound.y));

        //lowerBound = B2Math.b2Min(aabb1.lowerBound, aabb2.lowerBound);
        //upperBound = B2Math.b2Max(aabb1.upperBound, aabb2.upperBound);
    }

    public static float GetPerimeterZeroCG(B2AABB aabb1, B2AABB aabb2)
    {
        float lowerx = Math.min(aabb1.lowerBound.x, aabb2.lowerBound.x);
        float lowery = Math.min(aabb1.lowerBound.y, aabb2.lowerBound.y);
        float upperx = Math.max(aabb1.upperBound.x, aabb2.upperBound.x);
        float uppery = Math.max(aabb1.upperBound.y, aabb2.upperBound.y);
        float wx = upperx - lowerx;
        float wy = uppery - lowery;
        return 2.0f * (wx + wy);
    }

    // public  bool RayCast(b2RayCastOutput* output, const b2RayCastInput& input) const;


    @Override
    public String toString() {
        return "{" +
                 lowerBound +
                ", " + upperBound +
                '}';
    }
}
