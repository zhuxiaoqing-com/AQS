package com.youxi.dataStructure.box2d3_2.collidePolygons.zeroGC;

import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Circle;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class Circle2CircleZero {


    public static boolean circle2Circle(B2Circle a, B2Circle b, OutValueZeroGC outValue) {
        outValue.reset();

        B2Vec2 sub = B2Math.subZeroGC(a.center, b.center,outValue.incrCacheVec());
        float subLengthSqr = B2Math.b2LengthSquared(sub);
        float radiusAdd = a.getRegionRadius() + b.getRegionRadius();

        return subLengthSqr <= radiusAdd * radiusAdd;
    }

}
