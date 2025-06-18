package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.entity.Dir;
import com.youxi.entity.Position;
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
    public boolean textRayCircle(B2Vec2 cPos, float cR, B2Vec2 rayO, B2Vec2 rDir, OutValue ov) {
        // 先判断
        // 求圆心和射线的距离的射线
        B2Vec2 voc = B2Math.b2Sub(cPos, rayO);
        B2Vec2 rDirNor = B2Math.b2Normalize(rDir);
        // 求出voc 投影到rDir上的长度
        float projectionLength = B2Math.b2Dot(voc, rDirNor);

        // voc的长度平方 - v的长度平方= cPos和射线的最近距离平方;
        float distance2 = B2Math.b2Dot(voc, voc) - projectionLength * projectionLength;

        float cR2 = cR*cR;
        // 没有相交
        if(distance2 > cR2) {
            return false;
        }

        float t0;
        float t1;

        // 相切 所以projectionLength得长度就是射线起点到圆的长度
        if (distance2 - cR2 < 0.001) {
            t0 = t1 = projectionLength / rDir.length();
        }else {
            // 相交 有两个点

        }
    }
}
