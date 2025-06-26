package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.entity.CMath;
import com.youxi.entity.OBB2D;
import com.youxi.entity.Position;
import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2OBB;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class OBB2OBB {

    /**
     * https://www.cnblogs.com/hont/p/9501169.html
     * https://www.cnblogs.com/programmer-kaima/p/5195781.html
     * <p>
     * 在两个凸多边形中找到一根轴，凸多边形所有在这根轴上的投影点不产生相交，则这两个凸多边形不相交。
     * 这根轴一般取每个边的垂线，逐个投影进行测试。
     * <p>
     * 轴分离算法 STA
     *
     * @param aaa
     * @param bbb
     * @return
     */
    public static boolean intersects(B2OBB aaa, B2OBB bbb) {
     /*   Position axis1 = aaa.getP1().sub(aaa.getP0()).normalize();
        Position axis2 = aaa.getP3().sub(aaa.getP0()).normalize();

        Position axis3 = bbb.getP1().sub(aaa.getP0()).normalize();
        Position axis4 = bbb.getP3().sub(aaa.getP0()).normalize();*/

        B2Vec2 axis1 = B2Math.sub(aaa.getPoints().get(1), aaa.getPoints().get(0));
        axis1 = B2Math.b2Normalize(axis1);

        B2Vec2 axis2 = B2Math.sub(aaa.getPoints().get(3), aaa.getPoints().get(0));
        axis2 = B2Math.b2Normalize(axis2);

        B2Vec2 axis3 = B2Math.sub(bbb.getPoints().get(1), bbb.getPoints().get(0));
        axis3 = B2Math.b2Normalize(axis3);

        B2Vec2 axis4 = B2Math.sub(bbb.getPoints().get(3), bbb.getPoints().get(0));
        axis4 = B2Math.b2Normalize(axis4);


        // 只要有一个不相交就是不相交
        if (isIntersect(aaa, bbb, axis1)) return false;
        if (isIntersect(aaa, bbb, axis2)) return false;
        if (isIntersect(aaa, bbb, axis3)) return false;
        if (isIntersect(aaa, bbb, axis4)) return false;

        return true;
    }


    /**
     * @return 相交:false  不相交：true
     */
    private static boolean isIntersect(B2OBB aaa, B2OBB bbb, B2Vec2 axis) {
        float aaaP0 = B2Math.b2Dot(aaa.getPoints().get(0), axis);
        float aaaP1 = B2Math.b2Dot(aaa.getPoints().get(1), axis);
        float aaaP2 = B2Math.b2Dot(aaa.getPoints().get(2), axis);
        float aaaP3 = B2Math.b2Dot(aaa.getPoints().get(3), axis);

        float bbbP0 = B2Math.b2Dot(bbb.getPoints().get(0), axis);
        float bbbP1 = B2Math.b2Dot(bbb.getPoints().get(1), axis);
        float bbbP2 = B2Math.b2Dot(bbb.getPoints().get(2), axis);
        float bbbP3 = B2Math.b2Dot(bbb.getPoints().get(3), axis);


        float xMin = B2Math.min(aaaP0, aaaP1, aaaP2, aaaP3);
        float xMax = B2Math.max(aaaP0, aaaP1, aaaP2, aaaP3);
        float yMin = B2Math.min(bbbP0, bbbP1, bbbP2, bbbP3);
        float yMax = B2Math.max(bbbP0, bbbP1, bbbP2, bbbP3);


        if (yMin > xMin && yMin < xMax) return false;
        if (yMax > xMin && yMax < xMax) return false;
        if (xMin > yMin && xMin < yMax) return false;
        if (xMax > yMin && xMax < yMax) return false;

        return true;
    }

    public static void main(String[] args) {
        B2OBB a = null;
        B2OBB b = null;
        // obb 左右 有碰撞
        a = new B2OBB(new B2Vec2(1, 1), new B2Vec2(1, 2), 30);
        b = new B2OBB(new B2Vec2(1.3f, 2), new B2Vec2(2, 2), 60);
        System.out.println(" obb 左右 有碰撞 hit: " + intersects(a, b));
        // obb 左右 没碰撞
        a = new B2OBB(new B2Vec2(1, 1), new B2Vec2(1, 2), 30);
        b = new B2OBB(new B2Vec2(6.1f, 2.2f), new B2Vec2(2.1f, 2.3f), 60);
        System.out.println(" obb 左右 没碰撞 hit: " + intersects(a, b));
        // obb 上下 有碰撞
        a = new B2OBB(new B2Vec2(1, 1), new B2Vec2(1, 2), 30);
        b = new B2OBB(new B2Vec2(2, 1.3f), new B2Vec2(2, 2), 60);
        System.out.println("obb 上下 有碰撞 hit: " + intersects(a, b));
        // obb 上下 没碰撞
        a = new B2OBB(new B2Vec2(1, 1), new B2Vec2(1, 2), 30);
        b = new B2OBB(new B2Vec2(2.2f, 6.1f), new B2Vec2(2.1f, 2.3f), 60);
        System.out.println("obb 上下 没碰撞 hit: " + intersects(a, b));
        // obb 斜着 有碰撞
        a = new B2OBB(new B2Vec2(1, 1), new B2Vec2(1, 2), 30);
        b = new B2OBB(new B2Vec2(2.1f, 2.1f), new B2Vec2(2, 2), 60);
        System.out.println("obb 斜着 有碰撞 hit: " + intersects(a, b));
        // obb 斜着 没碰撞
        a = new B2OBB(new B2Vec2(1, 1), new B2Vec2(1, 2), 30);
        b = new B2OBB(new B2Vec2(9.1f, 9.3f), new B2Vec2(2, 2), 60);
        System.out.println("obb 斜着 没碰撞 hit: " + intersects(a, b));
        // obb 重叠在一起 有碰撞
        a = new B2OBB(new B2Vec2(1, 1), new B2Vec2(1, 2), 30);
        b = new B2OBB(new B2Vec2(1f, 1f), new B2Vec2(2, 2), 60);
        System.out.println("obb 重叠在一起 有碰撞 hit: " + intersects(a, b));
    }

}
