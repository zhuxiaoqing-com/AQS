package com.youxi.dataStructure.box2d3_2.jgk;


import com.youxi.math.box2dGJK.b2ShapeProxy;
import com.youxi.math.box2dGJK.shape.B2MyPolygon;
import com.youxi.math.box2dGJK.shape.B2Vec2;
import org.junit.Test;

public class Test01 {

    @Test
    public void test01() {

        // aabb 打 aabb 不碰撞

        B2MyPolygon aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 0f);
        B2MyPolygon aabb2 = new B2MyPolygon(1f, 1f, new B2Vec2(4, 4), 0f);
        B2Vec2 b2Vec2 = new B2Vec2(1, 0);
        b2Vec2.Normalize();

        bsDistance(aabb1, aabb2, "");
        // b2ShapeCast(aabb1, aabb2, b2Vec2, "");

        aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 0f);
        aabb2 = new B2MyPolygon(1f, 1f, new B2Vec2(12, 12), 0f);
        b2Vec2 = new B2Vec2(12, 12).sub(new B2Vec2(2, 2)).multiply(10);


       // bsDistance(aabb1, aabb2, "");
       // b2ShapeCast(aabb1, aabb2, b2Vec2, "");
    }

    private static void b2ShapeCast(B2MyPolygon aabb1, B2MyPolygon aabb2, B2Vec2 b2Vec2, String param) {
        b2ShapeCastPairInput b2ShapeCastInput = new b2ShapeCastPairInput(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), b2Vec2);
        b2CastOutput b2CastOutput = Distance.b2ShapeCast(b2ShapeCastInput);

        System.out.printf("param %s hit %s aabb1 %s   aabb2 %s dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s %n",
                param, b2CastOutput.hit,
                aabb1, aabb2, b2Vec2, b2CastOutput.point,
                b2CastOutput.normal, b2CastOutput.fraction, b2CastOutput.iterations);
    }

    private static void bsDistance(B2MyPolygon aabb1, B2MyPolygon aabb2, String param) {
        b2DistanceInput b2DistanceInput = new b2DistanceInput(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), true);
        b2DistanceOutput b2DistanceOutput = Distance.b2ShapeDistance(b2DistanceInput);

        System.out.printf("param %s  hit %s  aabb1 %s   aabb2 %s b2DistanceOutput pointA %s pointB %s distance %s iterations %s%n",
                param,b2DistanceOutput.distance<0.0001f,
                aabb1, aabb2, b2DistanceOutput.pointA, b2DistanceOutput.pointB,
                b2DistanceOutput.distance, b2DistanceOutput.iterations);
    }

}
