package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Vec2;

/**
 * https://math.stackexchange.com/questions/311921/get-location-of-vector-circle-intersection
 * * https://stackoverflow.com/questions/1073336/circle-line-segment-collision-detection-algorithm#
 * * https://www.pianshen.com/article/2429198750/ 这个最好
 */
public class Ray2Circle {

    /**
     * 这里用图形法取解 好像有很多特殊情况 额
     */
    public static boolean testRayCircle(B2Vec2 cPos, float cR, B2Vec2 rayO, B2Vec2 rDir, OutValue ov) {
        if (Math.abs(rDir.getX()) < 0.0001 || Math.abs(rDir.getY()) < 0.0001) {
            return false;
        }

        // 求圆心和射线的距离的射线
        B2Vec2 voc = B2Math.b2Sub(cPos, rayO);
        float cR2 = cR * cR;
        // 先判断点是否在圆里面 不判断这个的话 后面的算法会有问题
        if (B2Math.b2LengthSquared(voc) <= cR2) {
            // 在圆里面
            ov.setHitPoint(rayO);
            ov.setT(0);
            return true;
        }

        B2Vec2 rDirNor = B2Math.b2Normalize(rDir);
        // 求出voc 投影到rDir上的长度
        float projectionLength = B2Math.b2Dot(voc, rDirNor);

        //dot<0 说明圆在射线的后面, dot==0 说明原点在圆上
        if (projectionLength <= 0) {
            return false;
        }

        // voc的长度平方 - v的长度平方= cPos和射线的最近距离平方;
        float distance2 = B2Math.b2Dot(voc, voc) - projectionLength * projectionLength;

        // 没有相交
        if (distance2 > cR2) {
            return false;
        }

        float t0;
        float t1;
        float length = rDir.length();

        float tNumSqr = cR2 - distance2;
        // 相切 所以projectionLength得长度就是射线起点到圆的长度
        if (tNumSqr < 0.001 * 0.001) {
            t0 = t1 = projectionLength / length;
        } else {
            // 相交 有两个点
            float sqrt = (float) Math.sqrt(tNumSqr);
            t0 = (projectionLength - sqrt) / length;
            t1 = (projectionLength + sqrt) / length;
        }
        if(t0>1) {
            return false;
        }
        ov.setT(t0);
        ov.setHitPoint(B2Math.b2MulAdd(rayO, t0, rDir));
        return true;
    }


    public static void main(String[] args) {
        B2Vec2 center = new B2Vec2(2, 2);
        float cr = 1;

        OutValue outValue = new OutValue();
        // 射线和圆不相交 射线在圆左边
        B2Vec2 rayO = new B2Vec2(0.5f, 5);
        B2Vec2 b2Vec2 = new B2Vec2(1, 0.1f);
        b2Vec2 = B2Math.multiply(b2Vec2, 10);
        boolean hit = testRayCircle(center, cr, rayO, b2Vec2, outValue.reset());
        System.out.printf("射线和圆不相交 射线在圆左边  hit %s t %s hitPos %s", hit, outValue.getT(), outValue.getHitPoint());
        System.out.println();

        // 射线和圆不相交 射线在圆右边
        rayO = new B2Vec2(5, 5);
        b2Vec2 = new B2Vec2(1, 0.1f);
        b2Vec2 = B2Math.multiply(b2Vec2, 10).multiply(-1);
        hit = testRayCircle(center, cr, rayO, b2Vec2, outValue.reset());
        System.out.printf("射线和圆不相交 射线在圆右边  hit %s t %s hitPos %s", hit, outValue.getT(), outValue.getHitPoint());
        System.out.println();

        // 射线在圆里面 在圆心的左边
        rayO = new B2Vec2(1.5f, 1.3f);
        b2Vec2 = new B2Vec2(1, 0.1f);
        b2Vec2 = B2Math.multiply(b2Vec2, 10);
        hit = testRayCircle(center, cr, rayO, b2Vec2, outValue.reset());
        System.out.printf("射线在圆里面 在圆心的左边  hit %s t %s hitPos %s", hit, outValue.getT(), outValue.getHitPoint());
        System.out.println();

        // 射线在圆里面 在圆心的右边
        rayO = new B2Vec2(2.3f, 2.5f);
        b2Vec2 = new B2Vec2(1, 0.1f);
        b2Vec2 = B2Math.multiply(b2Vec2, 10);
        hit = testRayCircle(center, cr, rayO, b2Vec2, outValue.reset());
        System.out.printf("射线在圆里面 在圆心的右边  hit %s t %s hitPos %s", hit, outValue.getT(), outValue.getHitPoint());
        System.out.println();
        // 射线在圆里面 在圆上
        rayO = new B2Vec2(2f, 3f);
        b2Vec2 = new B2Vec2(1, 0.1f);
        b2Vec2 = B2Math.multiply(b2Vec2, 10);
        hit = testRayCircle(center, cr, rayO, b2Vec2, outValue.reset());
        System.out.printf("射线在圆里面 在圆上  hit %s t %s hitPos %s", hit, outValue.getT(), outValue.getHitPoint());
        System.out.println();

        // 射线的延长线和圆相交
        rayO = new B2Vec2(0.3f, 2.1f);
        b2Vec2 = new B2Vec2(1, 0.1f);
        b2Vec2 = B2Math.b2Normalize(b2Vec2).multiply(0.1f);
        hit = testRayCircle(center, cr, rayO, b2Vec2, outValue.reset());
        System.out.printf("射线的延长线和圆相交  hit %s t %s hitPos %s", hit, outValue.getT(), outValue.getHitPoint());
        System.out.println();

        // 射线的反方向延长线和圆相交
        rayO = new B2Vec2(4f, 2.1f);
        b2Vec2 = new B2Vec2(1, 0.1f);
        b2Vec2 = B2Math.multiply(b2Vec2, 10);
        hit = testRayCircle(center, cr, rayO, b2Vec2, outValue.reset());
        System.out.printf("射线的反方向延长线和圆相交  hit %s t %s hitPos %s", hit, outValue.getT(), outValue.getHitPoint());
        System.out.println();

        // 射线起点在圆外面 射线没有穿透圆
        rayO = new B2Vec2(0.1f, 2.1f);
        b2Vec2 = new B2Vec2(1, 0.1f);
        b2Vec2 = B2Math.b2Normalize(b2Vec2).multiply(2f);
        hit = testRayCircle(center, cr, rayO, b2Vec2, outValue.reset());
        System.out.printf("射线起点在圆外面 射线没有穿透圆  hit %s t %s hitPos %s", hit, outValue.getT(), outValue.getHitPoint());
        System.out.println();

        // 射线和圆相交 射线在圆左边
         rayO = new B2Vec2(0.5f, 2.1f);
         b2Vec2 = new B2Vec2(1, 0.1f);
        b2Vec2 = B2Math.multiply(b2Vec2, 10);
         hit = testRayCircle(center, cr, rayO, b2Vec2, outValue.reset());
        System.out.printf("射线和圆相交 射线在圆左边  hit %s t %s hitPos %s", hit, outValue.getT(), outValue.getHitPoint());
        System.out.println();
    }
}
