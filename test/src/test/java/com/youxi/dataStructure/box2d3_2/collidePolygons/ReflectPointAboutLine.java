package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Vec2;

/**
 * @author zhuxiaoqing
 * @Description: ReflectPointAboutLine 反射
 * @Date 2025/10/14 17:12
 **/
public class ReflectPointAboutLine {

    /**
     * 原理  通过将方向向量分解计算反射角
     *
     * 利用的原理： 比如 我1,1的向量，其实可以分为 (1,0),(0,1)两个向量相加，
     * 我想取出反射其实就是把其中某个向量取反 再相加就行，
     * 对于这个物体在墙上反射来说的话就是，投影在墙上，和投影在法线上，
     * 我只需要把投影在法线上的向量取反再和投影在墙上的向量相加就是反射向量；
     *
     * 将方向向量投影在墙上，和投影在墙的法线上，得出 dir = 方向向量投影在墙上+投影在墙的法线上
     * 然后反射的话，其实就是将投影在法线上的向量取反，反射dir = 方向向量投影在墙上 -投影在墙的法线上
     *
     * 法向量无所谓正负，都行;
     *
     * @param dir 方向向量
     * @param wall 单位化的墙的向量，方向无所谓
     * @return
     */
    public B2Vec2 reflect(B2Vec2 dir, B2Vec2 wall){
        // dir投影在wall上的长度
        float wallProjectionLength = B2Math.b2Dot(dir, wall);
        // dir在墙的水平方向上的向量
        B2Vec2 wallProjection = B2Math.multiply(wall, wallProjectionLength);

        // 随便来一个墙的法向量
        B2Vec2 normal = new B2Vec2(-wall.y, wall.x);
        // dir投影在法向量上的长度
        float normalProjectionLength = B2Math.b2Dot(dir, normal);
        //dir在墙的垂直方向上的向量
        B2Vec2 normalProjection = B2Math.multiply(normal, normalProjectionLength);

        // 将垂直方向上的向量取反 + 水平方向上的向量 = 反射向量
        // 也就是 -垂直方向上的向量 + 水平方向上的向量 = 反射向量 ==? 水平方向上的向量 - 垂直方向上的向量 = 反射向量
        return B2Math.sub(wallProjection, normalProjection);
    }
}
