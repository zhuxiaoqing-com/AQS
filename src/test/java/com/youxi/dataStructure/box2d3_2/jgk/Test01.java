package com.youxi.dataStructure.box2d3_2.jgk;


import com.youxi.dataStructure.box2d3_2.jgk.zeroGC.*;
import com.youxi.math.box2dGJK.B2Distance;
import com.youxi.math.box2dGJK.B2ShapeCastInput;
import com.youxi.math.box2dGJK.B2ShapeCastOutput;
import com.youxi.math.box2dGJK.b2ShapeProxy;
import com.youxi.math.box2dGJK.shape.B2Circle;
import com.youxi.math.box2dGJK.shape.B2MyPolygon;
import com.youxi.math.box2dGJK.shape.B2Vec2;
import org.junit.Test;

import java.util.Arrays;

public class Test01 {
    public static final boolean PRINT = true;

    public static B2ShapeCastPairInputZeroGC b2ShapeCastPairInputZeroGC = new B2ShapeCastPairInputZeroGC();
    public static B2CastOutputZeroGC b2CastOutputZeroGC = new B2CastOutputZeroGC();

    public static B2DistanceInputZeroGC b2DistanceInputZeroGC = new B2DistanceInputZeroGC();
    public static B2DistanceOutputZeroGC b2DistanceOutputZeroGC = new B2DistanceOutputZeroGC();

    public static B2CCDInputZeroGC b2CCDInputZeroGC = new B2CCDInputZeroGC();
    public static B2CCDOutputZeroGC b2CCDOutputZeroGC = new B2CCDOutputZeroGC();

    @Test
    public void test02() {


        // obb打圆 一开始就重叠在一起
        B2MyPolygon    aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        B2Circle b2Circle1 = new B2Circle(new B2Vec2(3, 3), 2);
        B2Vec2  b2Vec2 = new B2Vec2(-1, 0);
        b2Vec2 = b2Vec2.Normalize();
        bsDistance(new b2ShapeProxy(aabb1), new b2ShapeProxy(b2Circle1), "obb打圆 一开始就重叠在一起");
        b2ShapeCast(new b2ShapeProxy(aabb1), new b2ShapeProxy(b2Circle1), b2Vec2, "obb打圆 一开始就重叠在一起");
        System.out.println("-------------");
        System.out.println("-------------");


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
        //new B2OBB(new B2Vec2(2, 2), new B2Vec2(1, 1), 50);
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
        //b2Vec2 = new B2Vec2(-1, 0);
        b2Vec2 = b2Vec2.Normalize();
        bsDistance(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(aabb1), "圆打obb 一开始就重叠在一起");
        b2ShapeCast(new b2ShapeProxy(b2Circle1), new b2ShapeProxy(aabb1), b2Vec2, "圆打obb 一开始就重叠在一起");
        System.out.println("-------------");
        System.out.println("-------------");

        // 圆打obb 需要打射线才在一起在一起
        aabb1 = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        b2Circle1 = new B2Circle(new B2Vec2(20, 2), 2);
        b2Vec2 = new B2Vec2(1, 0); // 这里需要反向 因为a在 b的右边
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


    }

    @Test
    public void ccdTest() {
        // ccd obb打obb
        B2MyPolygon a = new B2MyPolygon(1f, 1f, new B2Vec2(2, 2), 40f);
        B2MyPolygon b = new B2MyPolygon(1f, 1f, new B2Vec2(2, 5), 50f);
        System.out.println(Arrays.toString(a.getM_vertices()));
        System.out.println(Arrays.toString(b.getM_vertices()));
       /* B2Vec2 dirA = new B2Vec2(3, 2).Normalize().multiply(10);
        B2Vec2 dirB = new B2Vec2(1, -3).Normalize().multiply(10);*/
        B2Vec2 dirA = new B2Vec2(3, 2).Normalize().multiply(10);
        System.out.println(dirA);
        B2Vec2 dirB = new B2Vec2(3, -2).Normalize().multiply(10);
        System.out.println(dirB);
        b2CCD(new b2ShapeProxy(a), new b2ShapeProxy(b), dirA, dirB, "CCD 测试");


        System.out.println("----------");
        System.out.println("----------");
        // 这个ccd 运行时间能长一点 用这个来测试 gjk是否有问题
        a = new B2MyPolygon(40f, 1f, 1f, new B2Vec2(2, 2));
        b = new B2MyPolygon(50f, 1f, 1f, new B2Vec2(2, 5));
        System.out.println(Arrays.toString(a.getM_vertices()));
        System.out.println(Arrays.toString(b.getM_vertices()));
       /* B2Vec2 dirA = new B2Vec2(3, 2).Normalize().multiply(10);
        B2Vec2 dirB = new B2Vec2(1, -3).Normalize().multiply(10);*/
        dirA = new B2Vec2(3, 2).Normalize().multiply(10);
        System.out.println(dirA);
        dirB = new B2Vec2(3, -2).Normalize().multiply(10);
        System.out.println(dirB);
        b2CCD(new b2ShapeProxy(a), new b2ShapeProxy(b), dirA, dirB, "CCD 测试");
    }


    private static void bsDistance(b2ShapeProxy aabb1, b2ShapeProxy aabb2, String param) {
        B2DistanceInput b2DistanceInput = new B2DistanceInput(aabb1, aabb2, true);
        B2DistanceOutput b2DistanceOutput = Distance.b2ShapeDistance(b2DistanceInput);


        String format = String.format("param %s  hit %s b2DistanceOutput pointA %s pointB %s distance %s iterations %s",
                "bsDistance " + param, b2DistanceOutput.distance < 0.0001f,
                b2DistanceOutput.pointA, b2DistanceOutput.pointB,
                b2DistanceOutput.distance, b2DistanceOutput.iterations);
        if (PRINT)
            System.out.println(format);


        // 测试zeroGC
        b2DistanceInputZeroGC.proxyA.reset(aabb1);
        b2DistanceInputZeroGC.proxyB.reset(aabb2);
        B2DistanceOutputZeroGC b2DistanceOutputZeroGC1 = DistanceZeroGC.b2ShapeDistance(b2DistanceInputZeroGC, b2DistanceOutputZeroGC);
        String format2 = String.format("param %s  hit %s b2DistanceOutput pointA %s pointB %s distance %s iterations %s",
                "bsDistance " + param, b2DistanceOutputZeroGC1.distance < 0.0001f,
                b2DistanceOutputZeroGC1.pointA, b2DistanceOutputZeroGC1.pointB,
                b2DistanceOutputZeroGC1.distance, b2DistanceOutputZeroGC1.iterations);
        if (PRINT)
            System.out.println(String.format("测试 zeroGC  %s   %s", format.equals(format2), format2));


        bsDistance2(aabb1, aabb2, param);
    }

    private static void b2ShapeCast(b2ShapeProxy aabb1, b2ShapeProxy aabb2, B2Vec2 b2Vec2, String param) {
        B2ShapeCastPairInput b2ShapeCastInput = new B2ShapeCastPairInput(aabb1, aabb2, b2Vec2);
        B2CastOutput b2CastOutput = Distance.b2ShapeCast(b2ShapeCastInput);

        String format = String.format("param %s hit %s dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s",
                "b2ShapeCast " + param, b2CastOutput.hit,
                b2Vec2, b2CastOutput.point,
                b2CastOutput.normal, b2CastOutput.fraction, b2CastOutput.iterations);
        if (PRINT)
            System.out.println(format);


        // 测试zeroGC
        b2ShapeCastPairInputZeroGC.proxyA.reset(aabb1);
        b2ShapeCastPairInputZeroGC.proxyB.reset(aabb2);
        b2ShapeCastPairInputZeroGC.translationB.setXY(b2Vec2);
        b2ShapeCastPairInputZeroGC.canEncroach = false;
        b2ShapeCastPairInputZeroGC.maxFraction = 1;
        B2CastOutputZeroGC b2CastOutputZeroGC1 = DistanceZeroGC.b2ShapeCast(b2ShapeCastPairInputZeroGC, b2CastOutputZeroGC);

        String format2 = String.format("param %s hit %s dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s",
                "b2ShapeCast " + param, b2CastOutputZeroGC1.hit,
                b2Vec2, b2CastOutputZeroGC1.point,
                b2CastOutputZeroGC1.normal, b2CastOutputZeroGC1.fraction, b2CastOutputZeroGC1.iterations);

        if (PRINT)
            System.out.println(String.format("测试 zeroGC  %s  %s ", format.equals(format2), format2));

        b2ShapeCast2(aabb1, aabb2, b2Vec2, param);
    }


    private static void bsDistance2(b2ShapeProxy aabb1, b2ShapeProxy aabb2, String param) {
        B2Distance.b2DistanceInput b2DistanceInput = new B2Distance.b2DistanceInput(aabb1, aabb2, true);
        B2Distance.b2DistanceOutput b2DistanceOutput = new B2Distance.b2DistanceOutput();
        new B2Distance().b2Distance(b2DistanceOutput, b2DistanceInput);

        if (PRINT)
            System.out.printf("param %s hit %s  b2DistanceOutput pointA %s pointB %s distance %s iterations %s%n", "bsDistance " + param, b2DistanceOutput.distance < 0.0001f,
                    b2DistanceOutput.pointA, b2DistanceOutput.pointB, b2DistanceOutput.distance, b2DistanceOutput.iterations);

        b2CCD(aabb1, aabb2, new B2Vec2(), new B2Vec2(), param);
    }


    private static void b2ShapeCast2(b2ShapeProxy aabb1, b2ShapeProxy aabb2, B2Vec2 b2Vec2, String param) {
        B2ShapeCastInput b2ShapeCastInput = new B2ShapeCastInput(aabb1, aabb2, b2Vec2);
        B2ShapeCastOutput b2ShapeCastOutput = new B2ShapeCastOutput();
        boolean b = new B2Distance().b2ShapeCast(b2ShapeCastOutput, b2ShapeCastInput);

        if (PRINT)
            System.out.printf("param %s hit %s  dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s %n", "b2ShapeCast " + param, b,
                    b2Vec2, b2ShapeCastOutput.point, b2ShapeCastOutput.normal, b2ShapeCastOutput.lambda, b2ShapeCastOutput.iterations);

        b2CCD(aabb1, aabb2, new B2Vec2(), b2Vec2, param);
        // b2ShapeCast3(aabb1, aabb2, b2Vec2, param);
    }

    /*private static void b2ShapeCast3(b2ShapeProxy aabb1, b2ShapeProxy aabb2, B2Vec2 b2Vec2, String param) {
        B2ShapeCastInput b2ShapeCastInput = new B2ShapeCastInput(aabb1, aabb2, b2Vec2);
        B2ShapeCastOutput b2ShapeCastOutput = new B2ShapeCastOutput();
        boolean b = new B2Distance().b2ShapeCast222(b2ShapeCastOutput, b2ShapeCastInput);

        if (PRINT)
            System.out.printf("param %s hit %s  dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s %n", param, b,
                    b2Vec2, b2ShapeCastOutput.point, b2ShapeCastOutput.normal, b2ShapeCastOutput.lambda, b2ShapeCastOutput.iterations);
    }*/

    private static void b2CCD(b2ShapeProxy aabb1, b2ShapeProxy aabb2, B2Vec2 dirA, B2Vec2 dirB, String param) {
        B2CCDInput b2CCDInput = new B2CCDInput(aabb1, aabb2, dirA, dirB);
        B2CCDOutput ccd = CCD.ccd(b2CCDInput);

        String format = String.format("CCD param %s hit %s  dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s ",
                param, ccd.hit,
                dirB, ccd.point, ccd.normal, ccd.fraction, ccd.iterations);
        if (PRINT)
            System.out.println(format);
        // 测试 zeroGC

        b2CCDInputZeroGC.proxyA.reset(aabb1);
        b2CCDInputZeroGC.proxyB.reset(aabb2);
        b2CCDInputZeroGC.proxyADir.setXY(dirA);
        b2CCDInputZeroGC.proxyBDir.setXY(dirB);

        b2CCDInputZeroGC.canEncroach = false;

        B2CCDOutputZeroGC b2CCDOutputZeroGC1 = CCDZeroGC.ccd(b2CCDInputZeroGC, b2CCDOutputZeroGC);
        String format2 = String.format("CCD param %s hit %s  dir %s b2ShapeCastOutput point %s normal %s lambda %s iterations %s ",
                param, b2CCDOutputZeroGC1.hit,
                dirB, b2CCDOutputZeroGC1.point, b2CCDOutputZeroGC1.normal, b2CCDOutputZeroGC1.fraction, b2CCDOutputZeroGC1.iterations);
        if (PRINT)
            System.out.println(String.format("CCD 测试 zeroGC  %s  %s ", format.equals(format2), format2));
    }


}
