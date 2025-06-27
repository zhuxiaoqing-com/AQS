package com.youxi.dataStructure.box2d3_2.collidePolygons.zeroGC;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class OutValueZeroGC {
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

    private B2Vec2 hitPoint = new B2Vec2();




    public OutValueZeroGC reset() {
        hitEdge = 0;
        t = 0;
        hitPoint.setXY(0, 0);

        cacheIndex = 0;
        for (B2Vec2 cacheB2Vec : cacheB2Vecs) {
            cacheB2Vec.setXY(0,0);
        }
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
        this.hitPoint.setXY(hitPoint);
    }


    private int cacheIndex = 0;
    private final B2Vec2[] cacheB2Vecs = new B2Vec2[10];
    {
        for (int i = 0; i < cacheB2Vecs.length; i++) {
            cacheB2Vecs[i] = new B2Vec2();
        }
    }
    public void resetCacheIndex() {
        cacheIndex = 0;
    }
    public B2Vec2 incrCacheVec() {
        return cacheB2Vecs[cacheIndex++];
    }

}
