package com.youxi.dataStructure.box2d3_2.jgk.zeroGC;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class B2CCDInputZeroGC {
   public b2ShapeProxyZeroGC proxyA = new b2ShapeProxyZeroGC(); // 上一帧的位置
    /// < The proxy for shape A
   public b2ShapeProxyZeroGC proxyB = new b2ShapeProxyZeroGC(); //上一帧的位置
   public B2Vec2 proxyADir = new B2Vec2(); // proxyA上一帧到该帧的移动dir  包含移动速度
   public B2Vec2 proxyBDir = new B2Vec2(); // proxyB上一帧到该帧的移动dir
    // 布尔值，若为真，允许已接触的形状（带半径）略微靠近
   public boolean canEncroach;

    public B2CCDInputZeroGC() {
    }

    ///< Allows shapes with a radius to move slightly closer if already touching


    public B2CCDInputZeroGC(b2ShapeProxyZeroGC proxyA, b2ShapeProxyZeroGC proxyB, B2Vec2 proxyADir, B2Vec2 proxyBDir) {
        reset(proxyA, proxyB, proxyADir, proxyBDir);
    }

    public void reset(b2ShapeProxyZeroGC proxyA, b2ShapeProxyZeroGC proxyB, B2Vec2 proxyADir, B2Vec2 proxyBDir) {
        this.proxyA = proxyA;
        this.proxyB = proxyB;
        this.proxyADir.setXY(proxyADir);
        this.proxyBDir.setXY(proxyBDir);
        this.canEncroach = false;
    }
}
