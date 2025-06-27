package com.youxi.recastNav.meadowMapping;


import com.example.demo1.util.vo.Vector2f;

/**
 *
 */
public class LineSegmentIntersection {

    /**
     * <a href="https://zhuanlan.zhihu.com/p/414115475">网址</a>
     * r = pe1 - ps1
     * s = pe2 - ps2
     * <p>
     * <p>
     * x = 叉乘
     * t =
     * ps1+tr = ps2+us
     * 两边叉乘s
     * (ps1+tr)xs = (ps2+us)xs
     * ps1xs+ t(rxs) = ps2xs +u(sxs)
     * sxs=0;两条平行的先叉乘=0
     * ps1xs+ t(rxs) = ps2xs
     * t(rxs) = ps2xs - ps1xs
     * t(rxs) = (ps2-ps1)xs
     * t = (ps2-ps1)xs/rxs
     * <p>
     * <p>
     * u =
     * ps1+tr = ps2+us
     * 两边叉乘r
     * (ps1+tr)xr = (ps2+us)xr
     * ps1xr+ t(rxr) = ps2xr +u(sxr)
     * rxr=0;两条平行的先叉乘=0
     * ps1xr = ps2xr +u(sxr)
     * ps2xr +u(sxr) = ps1xr
     * u(sxr) = ps1xr - ps2xr
     * u(sxr) = (ps1 - ps2)xr
     * u = (ps1 - ps2)xr/sxr
     */
    public Vector2f a(Vector2f ps1, Vector2f pe1, Vector2f ps2, Vector2f pe2) {
        Vector2f r = pe1.sub(ps1);
        Vector2f s = pe2.sub(ps2);

        float cross1 = Vector2f.cross(r, s);
        float cross2 = Vector2f.cross(ps2.sub(ps1), r);

        // 两条线共线(重叠在一起)
        if (cross1 == 0 && cross2 == 0) {
            return null;
        }

        // 两条线并行不相交
        if (cross1 == 0 && cross2 != 0) {
            return null;
        }

        float t = Vector2f.cross(ps2.sub(ps1), s) / Vector2f.cross(r, s);
        float u = Vector2f.cross(ps1.sub(ps2), r) / Vector2f.cross(s, r);

        // 这里如果t == 1 || t == 0 | u == 1 || u == 0 说明没有相交 而是刚好碰上
        if (t > 0 && t < 1 && u > 0 && u < 1) {
            //ps1+tr
            return ps1.add(r.multiply(t));
        }
        // 直线相交 线段不相交
        return null;
    }
}
