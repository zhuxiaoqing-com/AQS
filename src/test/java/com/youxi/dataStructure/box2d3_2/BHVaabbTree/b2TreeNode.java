package com.youxi.dataStructure.box2d3_2.BHVaabbTree;

import com.youxi.math.box2dGJK.shape.B2AABB;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class b2TreeNode {
    public static final int B2_DEFAULT_CATEGORY_BITS = 1;
    B2AABB aabb;
    // 碰撞过滤的分类位
    long categoryBits;
    int child1, child2;
    long userData;
    /// The node parent index (allocated node)
    int parent;
    /// The node freelist next index (free node)
    int next;

    int height; // 2
    int flags;     // 2


    public void init() {
        aabb = new B2AABB(new B2Vec2(0, 0), new B2Vec2(0, 0));
        categoryBits = B2_DEFAULT_CATEGORY_BITS;
        child1 = b2DynamicTree.B2_NULL_INDEX;
        child2 = b2DynamicTree.B2_NULL_INDEX;
        parent = b2DynamicTree.B2_NULL_INDEX;
        height = 0;
        flags = b2DynamicTree.b2_allocatedNode;

        next = 0;
    }


}
