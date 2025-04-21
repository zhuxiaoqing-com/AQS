package com.youxi.dataStructure.aabbTree;

import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class B2AABB {

    public B2Vec2 lowerBound;	///< the lower vertex
    public B2Vec2 upperBound;	///< the upper vertex
    /// Verify that the bounds are sorted.
   public boolean IsValid(){
        return true;
    }

    /// Get the center of the AABB.
    B2Vec2 GetCenter()
    {
        //return 0.5f * (lowerBound + upperBound);
        return lowerBound.add(upperBound).multiply(0.5f);
    }

    /// Get the extents of the AABB (half-widths).
    B2Vec2 GetExtents()
    {
        //return 0.5f * (upperBound - lowerBound);
        return upperBound.sub(lowerBound).multiply(0.5f);
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

    /// Combine two AABBs into this one.
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

 // public  bool RayCast(b2RayCastOutput* output, const b2RayCastInput& input) const;


}
