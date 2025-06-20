package com.youxi.dataStructure.box2d3_2.jgk;


import com.youxi.math.box2dGJK.B2Distance;
import com.youxi.math.box2dGJK.B2ShapeCastInput;
import com.youxi.math.box2dGJK.B2ShapeCastOutput;
import com.youxi.math.box2dGJK.b2ShapeProxy;
import com.youxi.math.box2dGJK.shape.B2Circle;
import com.youxi.math.box2dGJK.shape.B2MyPolygon;
import com.youxi.math.box2dGJK.shape.B2OBB;
import com.youxi.math.box2dGJK.shape.B2Vec2;
import org.junit.Test;

public class Test01 {
    public static final boolean PRINT = true;

    @Test
    public void test02() {

        // obb打obb碰撞 需要打射线才在一起在一起
        // 圆打obb 需要打射线才在一起在一起
        B2MyPolygon aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        B2Circle b2Circle1 = new B2Circle(new B2Vec2(20, 2), 2);
        B2Vec2 b2Vec2 = new B2Vec2(1, 0);
        b2Vec2 = b2Vec2.Normalize();
        b2Vec2 = b2Vec2.multiply(30);
  //      bsDistance(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(aabb1), "圆打obb 需要打射线才在一起在一起");
        b2ShapeCast(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(aabb1), b2Vec2, "圆打obb 需要打射线才在一起在一起");


    }

    @Test
    public void test01() {
        // obb打obb碰撞 一开始就重叠在一起
        B2MyPolygon aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        B2MyPolygon aabb2 = new B2MyPolygon(1f, 1f, new B2Vec2(3, 3), 50f);
        B2Vec2 b2Vec2 = new B2Vec2(1, 0);
        b2Vec2 = b2Vec2.Normalize();
        bsDistance(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), "obb打obb碰撞 一开始就重叠在一起");
        b2ShapeCast(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), b2Vec2, "obb打obb碰撞 一开始就重叠在一起");
        System.out.println("-------------");
        System.out.println("-------------");

        // obb打obb碰撞 需要打射线才在一起在一起
        aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        aabb2 = new B2MyPolygon(1f, 1f, new B2Vec2(20, 2), 50f);
        new B2OBB(new B2Vec2(2, 2), new B2Vec2(1, 1), 50);
        b2Vec2 = new B2Vec2(-1, 0);
        b2Vec2 = b2Vec2.Normalize();
        b2Vec2 = b2Vec2.multiply(30);
        bsDistance(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), "obb打obb碰撞 需要打射线才在一起在一起");
        b2ShapeCast(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), b2Vec2, "obb打obb碰撞 需要打射线才在一起在一起");
        System.out.println("-------------");
        System.out.println("-------------");


        // obb打圆 一开始就重叠在一起
        aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        B2Circle b2Circle1 = new B2Circle(new B2Vec2(3, 3), 2);
        b2Vec2 = new B2Vec2(-1, 0);
        b2Vec2 = b2Vec2.Normalize();
        bsDistance(new b2ShapeProxy(aabb1), new b2ShapeProxy(b2Circle1), "obb打圆 一开始就重叠在一起");
        b2ShapeCast(new b2ShapeProxy(aabb1), new b2ShapeProxy(b2Circle1), b2Vec2, "obb打圆 一开始就重叠在一起");
        System.out.println("-------------");
        System.out.println("-------------");

        // obb打圆 需要打射线才在一起在一起
        aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        b2Circle1 = new B2Circle(new B2Vec2(20, 2), 2);
        b2Vec2 = new B2Vec2(-1, 0);
        b2Vec2 = b2Vec2.Normalize();
        b2Vec2 = b2Vec2.multiply(30);
        bsDistance(new b2ShapeProxy(aabb1), new b2ShapeProxy(b2Circle1), "obb打圆 需要打射线才在一起在一起");
        b2ShapeCast(new b2ShapeProxy(aabb1), new b2ShapeProxy(b2Circle1), b2Vec2, "obb打圆 需要打射线才在一起在一起");
        System.out.println("-------------");
        System.out.println("-------------");

        // 圆打obb 一开始就重叠在一起
        aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        b2Circle1 = new B2Circle(new B2Vec2(3, 3), 2);
        b2Vec2 = new B2Vec2(-1, 0);
        b2Vec2 = b2Vec2.Normalize();
        bsDistance(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(aabb1), "圆打obb 一开始就重叠在一起");
        b2ShapeCast(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(aabb1), b2Vec2, "圆打obb 一开始就重叠在一起");
        System.out.println("-------------");
        System.out.println("-------------");

        // 圆打obb 需要打射线才在一起在一起
        aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        b2Circle1 = new B2Circle(new B2Vec2(20, 2), 2);
        b2Vec2 = new B2Vec2(-1, 0); // 这里需要反向 因为a在 b的右边
        b2Vec2 = b2Vec2.Normalize();
        b2Vec2 = b2Vec2.multiply(30);
        bsDistance(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(aabb1), "圆打obb 需要打射线才在一起在一起");
        b2ShapeCast(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(aabb1), b2Vec2, "圆打obb 需要打射线才在一起在一起");
        System.out.println("-------------");
        System.out.println("-------------");

        // 圆打圆 一开始就重叠在一起
        b2Circle1 = new B2Circle(new B2Vec2(2, 2), 1f);
        B2Circle b2Circle2 = new B2Circle(new B2Vec2(3, 3), 1f);
        b2Vec2 = new B2Vec2(1, 0);
        b2Vec2 = b2Vec2.Normalize();
        bsDistance(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(b2Circle2), "圆打圆 一开始就重叠在一起");
        b2ShapeCast(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(b2Circle2), b2Vec2, "圆打圆 一开始就重叠在一起");
        System.out.println("-------------");
        System.out.println("-------------");


        // 圆打圆 需要打射线才在一起在一起
        b2Circle1 = new B2Circle(new B2Vec2(2, 2), 1f);
        b2Circle2 = new B2Circle(new B2Vec2(20, 2), 2f);
        b2Vec2 = new B2Vec2(-1, 0);
        b2Vec2 = b2Vec2.Normalize();
        b2Vec2 = b2Vec2.multiply(30);
        bsDistance(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), "圆打圆 需要打射线才在一起在一起");
        b2ShapeCast(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), b2Vec2, "圆打圆 需要打射线才在一起在一起");
        System.out.println("-------------");
        System.out.println("-------------");
/*
        // aabb 打 aabb碰撞

        B2MyPolygon aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 0f);
        B2MyPolygon aabb2 = new B2MyPolygon(1f, 1f, new B2Vec2(4, 4), 0f);
        B2Vec2 b2Vec2 = new B2Vec2(1, 0);
        b2Vec2 = b2Vec2.Normalize();

        bsDistance(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), "aabb 打 aabb碰撞");
        b2ShapeCast(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), b2Vec2, "aabb 打 aabb碰撞");

        // aabb 打 aabbb不碰撞
        aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 0f);
        aabb2 = new B2MyPolygon(1f, 1f, new B2Vec2(12, 12), 0f);
        b2Vec2 = new B2Vec2(12, 12).sub(new B2Vec2(2, 2)).multiply(10);

        bsDistance(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), "aabb 打 aabbb不碰撞");
        b2ShapeCast(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), b2Vec2, "aabb 打 aabbb不碰撞");

        // 圆打obb
        B2Circle b2Circle = new B2Circle(new B2Vec2(2, 2), 2);
        aabb2 = new B2MyPolygon(1f, 1f, new B2Vec2(12, 12), 0f);
        b2Vec2 = new B2Vec2(12, 12).sub(new B2Vec2(2, 2)).multiply(10);

        bsDistance(new b2ShapeProxy(b2Circle), new b2ShapeProxy(aabb2), "圆打obb不碰撞");
        b2ShapeCast(new b2ShapeProxy(b2Circle), new b2ShapeProxy(aabb2), b2Vec2, "圆打obb不碰撞");

        // 圆打obb 碰撞
        B2MyPolygon aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 0f);
        B2MyPolygon aabb2 = new B2MyPolygon(1f, 1f, new B2Vec2(4, 4), 0f);
        B2Vec2 b2Vec2 = new B2Vec2(1, 0);
        b2Vec2 = b2Vec2.Normalize();

        bsDistance(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), "aabb 打 aabb碰撞");
        b2ShapeCast(new b2ShapeProxy(aabb1), new b2ShapeProxy(aabb2), b2Vec2, "aabb 打 aabb碰撞");

        // obb打圆

        // 圆打圆*/
    }


    private static void bsDistance(b2ShapeProxy aabb1, b2ShapeProxy aabb2, String param) {
        b2DistanceInput b2DistanceInput = new b2DistanceInput(aabb1, aabb2, true);
        b2DistanceOutput b2DistanceOutput = Distance.b2ShapeDistance(b2DistanceInput);

        if (PRINT)
            System.out.printf("param %s  hit %s b2DistanceOutput pointA %s pointB %s distance %s iterations %s%n",
                    param, b2DistanceOutput.distance < 0.0001f,
                    b2DistanceOutput.pointA, b2DistanceOutput.pointB,
                    b2DistanceOutput.distance, b2DistanceOutput.iterations);

        bsDistance2(aabb1, aabb2, param);
        System.out.println();
    }

    private static void b2ShapeCast(b2ShapeProxy aabb1, b2ShapeProxy aabb2, B2Vec2 b2Vec2, String param) {
        b2ShapeCastPairInput b2ShapeCastInput = new b2ShapeCastPairInput(aabb1, aabb2, b2Vec2);
        b2CastOutput b2CastOutput = Distance.b2ShapeCast(b2ShapeCastInput);

        if (PRINT)
            System.out.printf("param %s hit %s dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s %n",
                    param, b2CastOutput.hit,
                    b2Vec2, b2CastOutput.point,
                    b2CastOutput.normal, b2CastOutput.fraction, b2CastOutput.iterations);

        b2ShapeCast2(aabb1, aabb2, b2Vec2, param);
        System.out.println();
    }


    private static void bsDistance2(b2ShapeProxy aabb1, b2ShapeProxy aabb2, String param) {
        B2Distance.b2DistanceInput b2DistanceInput = new B2Distance.b2DistanceInput(aabb1, aabb2, true);
        B2Distance.b2DistanceOutput b2DistanceOutput = new B2Distance.b2DistanceOutput();
        new B2Distance().b2Distance(b2DistanceOutput, b2DistanceInput);

        if (PRINT)
            System.out.printf("param %s hit %s  b2DistanceOutput pointA %s pointB %s distance %s iterations %s%n", param, b2DistanceOutput.distance < 0.0001f,
                    b2DistanceOutput.pointA, b2DistanceOutput.pointB, b2DistanceOutput.distance, b2DistanceOutput.iterations);
    }


    private static void b2ShapeCast2(b2ShapeProxy aabb1, b2ShapeProxy aabb2, B2Vec2 b2Vec2, String param) {
        B2ShapeCastInput b2ShapeCastInput = new B2ShapeCastInput(aabb1, aabb2, b2Vec2);
        B2ShapeCastOutput b2ShapeCastOutput = new B2ShapeCastOutput();
        boolean b = new B2Distance().b2ShapeCast(b2ShapeCastOutput, b2ShapeCastInput);

        if (PRINT)
            System.out.printf("param %s hit %s  dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s %n", param, b,
                    b2Vec2, b2ShapeCastOutput.point, b2ShapeCastOutput.normal, b2ShapeCastOutput.lambda, b2ShapeCastOutput.iterations);
    }


}
