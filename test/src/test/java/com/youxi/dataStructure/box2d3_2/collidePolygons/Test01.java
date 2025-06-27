package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.dataStructure.box2d3_2.collidePolygons.zeroGC.*;
import com.youxi.math.box2dGJK.shape.B2Circle;
import com.youxi.math.box2dGJK.shape.B2OBB;
import com.youxi.math.box2dGJK.shape.B2Vec2;
import org.junit.Test;

public class Test01 {

    public static final OutValueZeroGC outValueZeroGC = new OutValueZeroGC();

    @Test
    public void test01() {
        // 圆和圆碰撞了
        B2Circle c1 = new B2Circle(new B2Vec2(1, 1), 5);
        B2Circle c2 = new B2Circle(new B2Vec2(3, 3), 5);
        boolean ret = Circle2Circle.circle2Circle(c1, c2);
        boolean zeroRet = Circle2CircleZero.circle2Circle(c1, c2, outValueZeroGC);
        System.out.println("圆和圆碰撞了 " + (ret == zeroRet));

        // 圆和圆没碰撞
        c1 = new B2Circle(new B2Vec2(1, 1), 5);
        c2 = new B2Circle(new B2Vec2(11, 11), 5);
        ret = Circle2Circle.circle2Circle(c1, c2);
        zeroRet = Circle2CircleZero.circle2Circle(c1, c2, outValueZeroGC);
        System.out.println("圆和圆没碰撞 " + (ret == zeroRet));

        // obb和圆碰撞
        B2OBB b1 = new B2OBB(new B2Vec2(1, 1), new B2Vec2(2, 2), 30);
        c2 = new B2Circle(new B2Vec2(3, 3), 5);
        ret = OBB2Circle.testCircleOBB(c2.getCenter(), c2.getRegionRadius(), b1.getCenter(), b1.getDeg(), b1.getHalfSize());
        zeroRet = OBB2CircleZero.testCircleOBB(c2.getCenter(), c2.getRegionRadius(), b1.getCenter(), b1.getDeg(), b1.getHalfSize(), outValueZeroGC);
        System.out.println("obb和圆碰撞 " + (ret == zeroRet));

        // obb和圆不碰撞
        b1 = new B2OBB(new B2Vec2(1, 1), new B2Vec2(2, 2), 30);
        c2 = new B2Circle(new B2Vec2(10, 10), 5);
        ret = OBB2Circle.testCircleOBB(c2.getCenter(), c2.getRegionRadius(), b1.getCenter(), b1.getDeg(), b1.getHalfSize());
        zeroRet = OBB2CircleZero.testCircleOBB(c2.getCenter(), c2.getRegionRadius(), b1.getCenter(), b1.getDeg(), b1.getHalfSize(), outValueZeroGC);
        System.out.println("obb和圆不碰撞 " + (ret == zeroRet));

        // obb和obb碰撞
        B2OBB a = new B2OBB(new B2Vec2(1, 1), new B2Vec2(1, 2), 30);
        B2OBB b = new B2OBB(new B2Vec2(1.3f, 2), new B2Vec2(2, 2), 60);
        ret = OBB2OBB.intersects(a, b);
        zeroRet = OBB2OBBZero.intersects(a, b, outValueZeroGC);
        System.out.println("obb和obb碰撞 " + (ret == zeroRet));

        // obb和obb不碰撞
        a = new B2OBB(new B2Vec2(1, 1), new B2Vec2(1, 2), 30);
        b = new B2OBB(new B2Vec2(6.1f, 2.2f), new B2Vec2(2.1f, 2.3f), 60);
        ret = OBB2OBB.intersects(a, b);
        zeroRet = OBB2OBBZero.intersects(a, b, outValueZeroGC);
        System.out.println("obb和obb不碰撞 " + (ret == zeroRet));

        // ray2circle 打中
        OutValue outValue = new OutValue();
        B2Circle c = new B2Circle(new B2Vec2(10, 10), 5);
        B2Vec2 rayO = new B2Vec2(1, 1);
        B2Vec2 rDir = new B2Vec2(1, 1).multiply(20);
        ret = Ray2Circle.testRayCircle(c.getCenter(), c.getRegionRadius(), rayO, rDir, outValue.reset());
        zeroRet = Ray2CircleZeroGC.testRayCircle(c.getCenter(), c.getRegionRadius(), rayO, rDir, outValueZeroGC);
        System.out.printf("ray2circle 打中 hit %s t %s point %s", ret, outValue.getT(), outValue.getHitPoint());
        System.out.println();
        System.out.printf("zeroGC ray2circle 打中 hit %s t %s point %s", zeroRet, outValueZeroGC.getT(), outValueZeroGC.getHitPoint());
        System.out.println();

        // ray2circle 打不中
        c = new B2Circle(new B2Vec2(10, 10), 5);
        rayO = new B2Vec2(1, 1);
        rDir = new B2Vec2(1, 1).multiply(4);
        ret = Ray2Circle.testRayCircle(c.getCenter(), c.getRegionRadius(), rayO, rDir, outValue.reset());
        zeroRet = Ray2CircleZeroGC.testRayCircle(c.getCenter(), c.getRegionRadius(), rayO, rDir, outValueZeroGC);
        System.out.printf("ray2circle 打不中 hit %s t %s point %s", ret, outValue.getT(), outValue.getHitPoint());
        System.out.println();
        System.out.printf("zeroGC ray2circle 打不中 hit %s t %s point %s", zeroRet, outValueZeroGC.getT(), outValueZeroGC.getHitPoint());
        System.out.println();

        // ray2obb 打中
        b = new B2OBB(new B2Vec2(10, 10), new B2Vec2(4, 4), 40);
        rayO = new B2Vec2(1, 1);
        rDir = new B2Vec2(1, 1).multiply(20);
        ret = Ray2OBB.TestRayOBB(rayO, rDir, b.getCenter(), b.getHalfSize(), b.getDeg(), outValue.reset());
        zeroRet = Ray2OBBZeroGC.TestRayOBB(rayO, rDir, b.getCenter(), b.getHalfSize(), b.getDeg(), outValueZeroGC);
        System.out.printf("ray2obb 打中 hit %s t %s point %s hitEdge %s", ret, outValue.getT(), outValue.getHitPoint(), outValue.getHitEdge());
        System.out.println();
        System.out.printf("zeroGC ray2obb 打中 hit %s t %s point %s hitEdge %s", zeroRet, outValue.getT(), outValue.getHitPoint(), outValue.getHitEdge());
        System.out.println();

        // ray2obb 打不中
        b = new B2OBB(new B2Vec2(10, 10), new B2Vec2(4, 4), 40);
        rayO = new B2Vec2(1, 1);
        rDir = new B2Vec2(1, 1).Normalize().multiply(2.9f);
        ret = Ray2OBB.TestRayOBB(rayO, rDir, b.getCenter(), b.getHalfSize(), b.getDeg(), outValue.reset());
        zeroRet = Ray2OBBZeroGC.TestRayOBB(rayO, rDir, b.getCenter(), b.getHalfSize(), b.getDeg(), outValueZeroGC);
        System.out.printf("ray2obb 打不中 hit %s t %s point %s hitEdge %s", ret, outValue.getT(), outValue.getHitPoint(), outValue.getHitEdge());
        System.out.println();
        System.out.printf("zeroGC ray2obb 打不中 hit %s t %s point %s hitEdge %s", zeroRet, outValue.getT(), outValue.getHitPoint(), outValue.getHitEdge());
        System.out.println();
    }
}
