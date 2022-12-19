package com.youxi.recastNav.meadowMapping;

import com.example.demo1.util.vo.Vector2f;

/**
 * <a href="https://www.bilibili.com/video/BV19G4y187Fz/?spm_id_from=pageDriver&vd_source=45efa9402ce6ca32c998c7a15ba39467">...</a>
 * 视频时间戳：21:56
 *
 * 是否在锥形里面
 */
public class Cone {

    /**
     * 点是逆时针传过来的
     * 多边形的边是逆时针方向的
     *
     * @return 连接的线段 true: 在多边形里面 false： 在多边形外面
     */
    public boolean cone(Vector2f p1, Vector2f p2, Vector2f p3, Vector2f checkP) {
        Vector2f coneLeft = p1.sub(p2);
        Vector2f checkCenter = checkP.sub(p2);
        Vector2f coneRight = p3.sub(p2);

        // > 0 说明是凸的 锐角， <0 说明是凹的 钝角
        float cross = Vector2f.cross(p2.sub(p1), p3.sub(p1));

        // 锐角
        if (cross > 0) {
            float cross1 = Vector2f.cross(p2.sub(p1), checkP.sub(p1));
            float cross2 = Vector2f.cross(p3.sub(p2), checkCenter.sub(p2));
            return cross1 > 0 && cross2 > 0;
        }


        // 钝角
        if (cross < 0) {
            float cross1 = Vector2f.cross(p2.sub(p1), checkCenter.sub(p1));
            float cross2 = Vector2f.cross(p3.sub(p2), checkCenter.sub(p2));
            return cross1 < 0 && cross2 < 0;
        }

        // 两条边平行
        return false;
    }
}
