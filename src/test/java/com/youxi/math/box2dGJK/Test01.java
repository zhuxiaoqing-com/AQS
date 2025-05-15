package com.youxi.math.box2dGJK;

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
        b2ShapeCast(aabb1, aabb2, b2Vec2, "");

        aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 0f);
        aabb2 = new B2MyPolygon(1f, 1f, new B2Vec2(12, 12), 0f);
        b2Vec2 = new B2Vec2(12, 12).sub(new B2Vec2(2, 2)).multiply(10);


        bsDistance(aabb1, aabb2, "");
        b2ShapeCast(aabb1, aabb2, b2Vec2, "");
    }

    private static void b2ShapeCast(B2MyPolygon aabb1, B2MyPolygon aabb2, B2Vec2 b2Vec2, String param) {
        B2ShapeCastInput b2ShapeCastInput = new B2ShapeCastInput(new B2DistanceProxy(aabb1), new B2DistanceProxy(aabb2), b2Vec2);
        B2ShapeCastOutput b2ShapeCastOutput = new B2ShapeCastOutput();
        new B2Distance().b2ShapeCast(b2ShapeCastOutput, b2ShapeCastInput);

        System.out.printf("param %s aabb1 %s   aabb2 %s dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s %n", param,
                aabb1, aabb2, b2Vec2, b2ShapeCastOutput.point, b2ShapeCastOutput.normal, b2ShapeCastOutput.lambda, b2ShapeCastOutput.iterations);
    }

    private static void bsDistance(B2MyPolygon aabb1, B2MyPolygon aabb2, String param) {
        B2Distance.b2DistanceInput b2DistanceInput = new B2Distance.b2DistanceInput(new B2DistanceProxy(aabb1), new B2DistanceProxy(aabb2), true);
        B2Distance.b2DistanceOutput b2DistanceOutput = new B2Distance.b2DistanceOutput();
        new B2Distance().b2Distance(b2DistanceOutput, b2DistanceInput);

        System.out.printf("param %s  aabb1 %s   aabb2 %s b2DistanceOutput pointA %s pointB %s distance %s iterations %s%n", param,
                aabb1, aabb2, b2DistanceOutput.pointA, b2DistanceOutput.pointB, b2DistanceOutput.distance, b2DistanceOutput.iterations);
    }


}
