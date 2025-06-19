package com.youxi.dataStructure.box2d3_2.jgk;

import com.youxi.math.box2dGJK.b2ShapeProxy;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class b2ShapeCastPairInput {
    b2ShapeProxy proxyA;
    /// < The proxy for shape A
    b2ShapeProxy proxyB;
    /// < The proxy for shape B
    //b2Transform transformA; ///< The world transform for shape A
    //b2Transform transformB; ///< The world transform for shape B
    // 形状B的平移向量，表示其移动方向和距离  根据AI解释默认A是静止的  B往A撞
    B2Vec2 translationB;
    /// < The translation of shape B
    //考虑平移的分数比例，通常为1（即完整平移）
    float maxFraction;
    /// < The fraction of the translation to consider, typically 1
    /**
     * canEncroach 的作用‌
     * ‌允许轻微穿透‌
     * 当设置为 true 时，允许带有半径（如圆形或胶囊体）的形状在已经接触的情况下略微靠近对方，避免因严格碰撞检测导致的运动卡顿
     *
     * 适用于需要平滑移动的场景，如角色控制器或动态物体的连续运动。
     * ‌严格碰撞检测‌
     * 当设置为 false 时，强制保持形状间的严格距离，即使已接触也不会进一步靠近。
     *
     * 适用于需要精确物理模拟的场景，如刚体堆叠或机械结构。
     * Box2D 通过 b2ShapeCastPairInput 结构体传递该参数，影响形状投射计算中的接触点判定逻辑。
     * 若形状已接触且 canEncroach=true，则允许忽略半径的微小重叠，否则强制分离
     * 所以如果设置了就运行获取法向量进行分离
     */
    // 布尔值，若为真，允许已接触的形状（带半径）略微靠近
    boolean canEncroach;

    public b2ShapeCastPairInput() {
    }

    ///< Allows shapes with a radius to move slightly closer if already touching


    public b2ShapeCastPairInput(b2ShapeProxy proxyA, b2ShapeProxy proxyB, B2Vec2 translationB) {
        this.proxyA = proxyA;
        this.proxyB = proxyB;
        this.translationB = translationB;
        this.maxFraction = 1;
        this.canEncroach = false;
    }
}
