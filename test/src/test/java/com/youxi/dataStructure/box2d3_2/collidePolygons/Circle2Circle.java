package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Circle;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class Circle2Circle {


    public static boolean circle2Circle(B2Circle a, B2Circle b) {
        B2Vec2 sub = B2Math.sub(a.center, b.center);
        float subLengthSqr = B2Math.b2LengthSquared(sub);
        float radiusAdd = a.getRegionRadius() + b.getRegionRadius();

        return subLengthSqr <= radiusAdd * radiusAdd;
    }

}
