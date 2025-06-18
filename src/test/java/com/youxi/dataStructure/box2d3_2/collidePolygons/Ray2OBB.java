package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class Ray2OBB {
    public static boolean TestRayOBB(B2Vec2 src, B2Vec2 dir, B2Vec2 center, B2Vec2 sizeDirRightTop, float deg, OutValue ov) {
        // 计算从起点位置到中心点位置的朝向
        B2Vec2 fo = B2Math.b2Sub(src, center);
        B2Vec2 a = B2Math.unityRotate(fo, -deg);
        MapHelper.getDirBySrcAndDesPos(center, src, ov.obbCache.fo);
        CMath.Rotate(ov.obbCache.fo, deg, ov.obbCache.fo);
        CMath.Rotate(dir, deg, ov.obbCache.fd);
        ov.obbCache.fo.toPosition(ov.obbCache.fo2p);
        boolean r = TestRayAABB(ov.obbCache.fo2p, ov.obbCache.fd, sizeDirRightTop.negDir(ov.obbCache.sizeDirRightTopNegDir), sizeDirRightTop, ov, false);
        // 多个碰撞盒子有没有打中是通过 有没有击中点来判断的;这里没有打中也有击中点会导致多个碰撞盒子判断错误
        if (r && ov != null)
            nearest(ov, src, dir, ov.getT());
        return r;
    }
}
