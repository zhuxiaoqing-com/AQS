package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.entity.CMath;
import com.youxi.entity.Dir;
import com.youxi.entity.Position;
import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Vec2;

/**
 * https://blog.csdn.net/qq_37043683/article/details/80375691
 */
public class OBB2Circle {
    public static boolean testCircleAABB(B2Vec2 circleCenter, float circleRadius,
                                         B2Vec2 aabbCenter, B2Vec2 aabbSizeB) {
        // 计算圆心到AABB中心的向量绝对值
        B2Vec2 abs = B2Math.b2Abs(aabbCenter.sub(circleCenter));
        // 计算圆心到AABB边界的最近点偏移 若圆心在AABB内部，结果为负值；否则为超出边界的距离。
        // 这一步 相当于吧aabb变成了一个点 然后比较点和半径的距离
        B2Vec2 u = B2Math.b2Max(abs.sub(aabbSizeB), new B2Vec2());
        // 距离平方比较 若该值小于半径平方，则相交。
        return B2Math.b2LengthSquared(u) <= circleRadius * circleRadius;
    }

    /**
     * 将圆旋转到aabb的坐标系 然后用aabb的算法
     */
    public static boolean testCircleOBB(B2Vec2 circleCenter, float circleRadius,
                                        B2Vec2 obbCenter, float obbDeg, B2Vec2 obbSizeB) {

        B2Vec2 p = obbCenter.sub(circleCenter);
        B2Vec2 pRotate = B2Math.unityRotate(p, -obbDeg);

        B2Vec2 abs = B2Math.b2Abs(pRotate);
        B2Vec2 u = B2Math.b2Max(abs.sub(obbSizeB), new B2Vec2());
        // 距离平方比较 若该值小于半径平方，则相交。
        return B2Math.b2LengthSquared(u) <= circleRadius * circleRadius;
    }

    public static void main(String[] args) {
        B2Vec2 center = new B2Vec2(2, 2);
        float cr = 1;
        // 圆和aabb相交
        B2Vec2 aabbCenter = new B2Vec2(0.7f, 0.7f);
        B2Vec2 aabbSize = new B2Vec2(1f, 1f);
        System.out.println("圆和aabb相交 "+ testCircleAABB(center, cr, aabbCenter, aabbSize));

        // 圆和aabb不相交
        aabbCenter = new B2Vec2(-0.7f, -0.7f);
        aabbSize = new B2Vec2(1f, 1f);
        System.out.println("圆和aabb不相交 " + testCircleAABB(center, cr, aabbCenter, aabbSize));


        // 圆和obb相交
        aabbCenter = new B2Vec2(0.7f, 0.7f);
        aabbSize = new B2Vec2(1f, 1f);
        System.out.println("圆和obb相交 " + testCircleOBB(center, cr, aabbCenter, 30, aabbSize));
        // 圆和obb不相交
        aabbCenter = new B2Vec2(-0.7f, -0.7f);
        aabbSize = new B2Vec2(1f, 1f);
        System.out.println("圆和obb不相交 " + testCircleOBB(center, cr, aabbCenter, 30, aabbSize));
    }
}

