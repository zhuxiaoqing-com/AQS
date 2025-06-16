package com.youxi.dataStructure.box2d3_2.collidePolygons.entity;

public class b2ManifoldPoint {
    /// Location of the contact point in world space. Subject to precision loss at large coordinates.
    /// @note Should only be used for debugging.
    // 世界空间中接触点的位置。在大坐标下会失去精度。
    b2Vec2 point;

    /// Location of the contact point relative to shapeA's origin in world space
    /// @note When used internally to the Box2D solver, this is relative to the body center of mass.
    // 相对于碰撞体ShapeA/B原点的局部坐标，求解器内部使用时转换为质心坐标系
    b2Vec2 anchorA;

    /// Location of the contact point relative to shapeB's origin in world space
    /// @note When used internally to the Box2D solver, this is relative to the body center of mass.
    // 相对于碰撞体ShapeA/B原点的局部坐标，求解器内部使用时转换为质心坐标系
    b2Vec2 anchorB;

    /// The separation of the contact point, negative if penetrating
    // 接触点分离距离（负值表示穿透深度），用于判断碰撞严重程度
    float separation;

    /// The impulse along the manifold normal vector.
    // 碰撞前相对法向速度，负值表示物体接近，用于触发碰撞事件
    float normalImpulse;

    /// The friction impulse
    // 沿接触法线方向的冲量，用于防止物体穿透
    float tangentImpulse;

    /// The total normal impulse applied across sub-stepping and restitution. This is important
    /// to identify speculative contact points that had an interaction in the time step.
    // 切向摩擦冲量，模拟滑动摩擦效应
    float totalNormalImpulse;

    /// Relative normal velocity pre-solve. Used for hit events. If the normal impulse is
    /// zero then there was no hit. Negative means shapes are approaching.
    // 累计法向冲量（含子步长和恢复系数计算），识别推测性接触
    float normalVelocity;

    /// Uniquely identifies a contact point between two shapes
    // 唯一标识符，用于跟踪同一接触点在多帧中的连续性
    int id;

    /// Did this contact point exist the previous step?
    // 标记该接触点是否在前一帧已存在，优化连续碰撞处理
    boolean persisted;
}
