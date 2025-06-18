package com.youxi.dataStructure.box2d3_2.jgk;

import com.youxi.dataStructure.box2d3_2.aabbTree.b2ShapeCastInput;
import com.youxi.math.box2dGJK.b2ShapeProxy;
import com.youxi.math.box2dGJK.shape.B2Circle;
import com.youxi.math.box2dGJK.shape.B2MyPolygon;

public class B2ShapeCastShape {

/*
    b2CastOutput b2ShapeCastCapsule( b2ShapeCastInput input, b2Capsule shape )
    {
        b2ShapeCastPairInput pairInput;
        pairInput.proxyA = b2MakeProxy( &shape.center1, 2, shape.radius );
        pairInput.proxyB = input.proxy;
        pairInput.transformA = b2Transform_identity;
        pairInput.transformB = b2Transform_identity;
        pairInput.translationB = input.translation;
        pairInput.maxFraction = input.maxFraction;
        pairInput.canEncroach = input.canEncroach;

        b2CastOutput output = b2ShapeCast( &pairInput );
        return output;
    }*/

    public b2CastOutput b2ShapeCastCircle(b2ShapeCastInput input, B2Circle shape )
    {
        b2ShapeCastPairInput pairInput = new b2ShapeCastPairInput();
        pairInput.proxyA = new b2ShapeProxy(shape);
        pairInput.proxyB = input.proxy;
        pairInput.translationB = input.translation;
        pairInput.maxFraction = input.maxFraction;
        pairInput.canEncroach = input.canEncroach;

        b2CastOutput output = Distance.b2ShapeCast(pairInput );
        return output;
    }

    b2CastOutput b2ShapeCastPolygon(b2ShapeCastInput input, B2MyPolygon shape )
    {
        b2ShapeCastPairInput pairInput = new b2ShapeCastPairInput();
        pairInput.proxyA = new b2ShapeProxy(shape);
        pairInput.proxyB = input.proxy;
        pairInput.translationB = input.translation;
        pairInput.maxFraction = input.maxFraction;
        pairInput.canEncroach = input.canEncroach;

        b2CastOutput output = Distance.b2ShapeCast(pairInput );
        return output;
    }


}
