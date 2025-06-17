package com.youxi.dataStructure.box2d3_2.jgk;

public class B2ShapeCastShape {


    b2CastOutput b2ShapeCastCapsule( b2ShapeCastInput input, b2Capsule shape )
    {
        b2ShapeCastPairInput pairInput;
        pairInput.proxyA = b2MakeProxy( &shape->center1, 2, shape->radius );
        pairInput.proxyB = input->proxy;
        pairInput.transformA = b2Transform_identity;
        pairInput.transformB = b2Transform_identity;
        pairInput.translationB = input->translation;
        pairInput.maxFraction = input->maxFraction;
        pairInput.canEncroach = input->canEncroach;

        b2CastOutput output = b2ShapeCast( &pairInput );
        return output;
    }
}
