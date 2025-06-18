package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.entity.CMath;
import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class Ray2OBB {
    public static boolean TestRayOBB(B2Vec2 src, B2Vec2 dir, B2Vec2 center, B2Vec2 sizeDirRightTop, float deg, OutValue ov) {
        // 没有方向向量
        if(dir.length()<=0.0001){
            return false;
        }
        // 以center当做新的原点
        B2Vec2 raySrc = B2Math.b2Sub(src, center);
        // 这里将obb的坐标系 变换成aabb的坐标系 也就是说需要反向旋转下 obb变换成aabb其实只要忽略deg就好了
        // 然后将射线也旋转下 先旋转射线原点 因为是要把obb变成aabb 就是要把obb的旋转反向旋转回来
        raySrc = B2Math.unityRotate(raySrc, -deg);
        // 然后将射线也旋转下 旋转射线方向
        B2Vec2 rayDir = B2Math.unityRotate(dir, -deg);


        boolean r = TestRayAABB(raySrc, rayDir, B2Math.b2Neg(sizeDirRightTop), sizeDirRightTop, ov, false);
        if (r && ov != null) {
            //nearest(ov, src, dir, ov.getT());
            B2Vec2 b2Vec2 = B2Math.b2MulAdd(src, ov.getT(), dir);
            ov.setHitPoint(b2Vec2);
        }

        return r;
    }


    /**
     * 光线的参数方程为R(t) = O + t * Dir
     * <p>
     * 一般平面方程为aX+bY+cZ+d=0，因为AABB的六个面分别平行于XY、XZ、YZ平面，所以平面的方程为X=d，Y=d，Z=d
     * <p>
     * d=aabb的min(x,y)和max(x,y)
     * 光线与垂直于x轴的两个面相交时，t = (d - o.x) / Dir.x
     * 光线与垂直于y轴的两个面相交时，t = (d - o.y) / Dir.y
     * 光线与垂直于z轴的两个面相交时，t = (d - o.z) / Dir.z
     *
     * <a href="https://blog.csdn.net/ring0hx/article/details/7677418">...</a>
     * <a href="https://blog.csdn.net/u012325397/article/details/50807880">...</a>
     * <a href="https://blog.csdn.net/i_dovelemon/article/details/38342739">...</a>
     *
     * @param o
     * @param dir
     * @param min
     * @param max
     * @param ov
     * @param calcNearest
     * @return
     */
    public static boolean TestRayAABB(B2Vec2 o, B2Vec2 dir, B2Vec2 min, B2Vec2 max, OutValue ov, boolean calcNearest) {
        float tmin = Float.MIN_VALUE; // set to -FLT_MAX to get first hit on line 进入平面的T值最大
        float tmax = Float.MAX_VALUE; // set to max distance ray can travel (for segment) 离开平面的T值最小


        boolean xSwap = false;
        boolean zSwap = false;
        boolean minSwap = false;

        // 基本和x轴平行
        if (Math.abs(dir.getX()) < 0.001) {
            // Ray is parallel to slab. No hit if origin not within slab
            // 如果dir往x轴方向 基本和x轴平行 但是 射线的原点 不在 min.x和 max.x 之间 说明他们永远平行且不会相交
            if (o.getX() < min.getX() || o.getX() > max.getX())
                return false;
        } else {
            // t = (d - o.x) / Dir.x
            float dirX = 1 / dir.getX();
            float t1 = (min.getX() - dir.getX()) * dirX;
            float t2 = (max.getX() - dir.getX()) * dirX;

            if (t1 > t2) {
                // 令 t1 为与近平面的交点，t2 为与远平面的交点
                xSwap = true;
                float temp = t1;
                t1 = t2;
                t2 = temp;
            }

            // Compute the intersection of slab intersections intervals
            tmin = Math.max(tmin, t1);
            tmax = Math.min(tmax, t2);

            // 这里不会进来 因为tmin和tmax在上面已经调换过位置了
            // Exit with no collision as soon as slab intersection becomes empty
            if (tmin > tmax)
                return false;
        }

        // 基本和y轴平行
        if (Math.abs(dir.getY()) < 0.001) {
            // Ray is parallel to slab. No hit if origin not within slab
            // 如果dir往y轴方向 基本和y轴平行 但是 射线的原点 不在 min.y和 max.y 之间 说明他们永远平行且不会相交
            if (o.getY() < min.getY() || o.getY() > max.getY())
                return false;
        } else {
            // t = (d - o.y) / Dir.y
            float dirY = 1 / dir.getY();
            float t1 = (min.getY() - dir.getY()) * dirY;
            float t2 = (max.getY() - dir.getY()) * dirY;

            if (t1 > t2) {
                // 令 t1 为与近平面的交点，t2 为与远平面的交点
                zSwap = true;
                float temp = t1;
                t1 = t2;
                t2 = temp;
            }

            if (t1 > tmin) minSwap = true;

            // Compute the intersection of slab intersections intervals
            tmin = Math.max(tmin, t1);
            tmax = Math.min(tmax, t2);

            // 不满足条件 应该满足 进入平面的T值最大 < 离开平面的T值最小
            // Exit with no collision as soon as slab intersection becomes empty
            if (tmin > tmax)
                return false;
        }

        // tmin > 1 代表射线够不到盒子
        if (tmin > 1) {
            return false;
        }
        int direction = 0; //1234 前后左右

        if (tmin < 0) {
            if (tmax < 0) {
                // 射线在盒子的上方并且往反方向打
                return false;
            }
            // 射线在盒子内部够不到盒子的出去的面的时候 tmax > CMath.PRECISION
            // 射线在盒子内部够得到盒子的出去的面的时候 tmax <= CMath.PRECISION
            // 如果tmax<1的话 那tmax就是击中的点(也就是子弹在盒子内飞的最远距离)  tmax>=1的话 那击中的点就默认为子弹飞出去的点
            tmin = Math.min(tmax, 1);
        } else { // tim大于等于是说明射线是从盒子外面进来的
            if (minSwap) { //min交换过说明是从上或者下进入的;
                //zSwap 如果没有交换过默认是从下面射过来的;交换过就是从上面射过来的
                direction = zSwap ? CMath.Direction.UP : CMath.Direction.DOWN;
            } else {
                //xSwap 如果没有交换过默认是从左面射过来的;交换过就是从右面射过来的
                direction = xSwap ? CMath.Direction.RIGHT : CMath.Direction.LEFT;
            }
        }

        if (ov != null) {
            ov.setT(tmin);
            ov.setHitEdge(direction);
            if (calcNearest) {
                B2Vec2 b2Vec2 = B2Math.b2MulAdd(o, tmin, dir);
                ov.setHitPoint(b2Vec2);
            }
        }

        return true;
    }
}

