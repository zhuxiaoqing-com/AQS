package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class OutValue {
    public static class Direction {
        public static final int UP = 1; // 上
        public static final int DOWN = 2; // 下
        public static final int LEFT = 3; // 左
        public static final int RIGHT = 4; // 右
    }

    private float t;

    /**
     * {@link Direction}
     */
    private int hitEdge; // 击中的边

    private B2Vec2 hitPoint;


    public OutValue reset() {
        hitEdge = 0;
        hitPoint = null;
        t = 0;
        return this;
    }


    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public int getHitEdge() {
        return hitEdge;
    }

    public void setHitEdge(int hitEdge) {
        this.hitEdge = hitEdge;
    }

    public B2Vec2 getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(B2Vec2 hitPoint) {
        this.hitPoint = hitPoint;
    }
}
