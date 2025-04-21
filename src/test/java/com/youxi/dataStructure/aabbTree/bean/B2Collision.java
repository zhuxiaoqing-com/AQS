package com.youxi.dataStructure.aabbTree.bean;

import com.youxi.dataStructure.aabbTree.B2AABB;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class B2Collision {

    public static boolean b2TestOverlap(B2AABB a, B2AABB b) {
        B2Vec2 d1, d2;
        d1 = b.lowerBound.sub(a.upperBound);
        d2 = a.lowerBound.sub(b.upperBound);

        if (d1.x > 0.0f || d1.y > 0.0f)
            return false;

        if (d2.x > 0.0f || d2.y > 0.0f)
            return false;

        return true;
    }
}
