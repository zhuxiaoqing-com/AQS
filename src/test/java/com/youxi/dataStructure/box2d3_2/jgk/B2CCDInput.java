package com.youxi.dataStructure.box2d3_2.jgk;

import com.youxi.math.box2dGJK.b2ShapeProxy;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class B2CCDInput {
   public b2ShapeProxy proxyA; // 上一帧的位置
    /// < The proxy for shape A
   public b2ShapeProxy proxyB; //上一帧的位置
   public B2Vec2 proxyADir; // proxyA上一帧到该帧的移动dir  包含移动速度
   public B2Vec2 proxyBDir; // proxyB上一帧到该帧的移动dir
    // 布尔值，若为真，允许已接触的形状（带半径）略微靠近
   public boolean canEncroach;

    public B2CCDInput() {
    }

    ///< Allows shapes with a radius to move slightly closer if already touching


    public B2CCDInput(b2ShapeProxy proxyA, b2ShapeProxy proxyB, B2Vec2 proxyADir, B2Vec2 proxyBDir) {
        this.proxyA = proxyA;
        this.proxyB = proxyB;
        this.proxyADir = proxyADir;
        this.proxyBDir = proxyBDir;
        this.canEncroach = false;
    }
}
