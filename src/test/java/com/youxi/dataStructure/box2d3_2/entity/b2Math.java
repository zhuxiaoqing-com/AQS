package com.youxi.dataStructure.box2d3_2.entity;

public class b2Math {

    /// Get the center of the AABB.
    public static b2Vec2 b2AABB_Center(b2AABB a) {
        return new b2Vec2(0.5f * (a.lowerBound.x + a.upperBound.x), 0.5f * (a.lowerBound.y + a.upperBound.y));
    }

    /**
     * 周长
     */
    public static float b2Perimeter(b2AABB a) {
        float wx = a.upperBound.x - a.lowerBound.x;
        float wy = a.upperBound.y - a.lowerBound.y;
        return 2.0f * (wx + wy);
    }

    /// Union of two AABBs
    public static b2AABB b2AABB_Union(b2AABB a, b2AABB b) {
        b2AABB c = new b2AABB();
        c.lowerBound.x = b2MinFloat(a.lowerBound.x, b.lowerBound.x);
        c.lowerBound.y = b2MinFloat(a.lowerBound.y, b.lowerBound.y);
        c.upperBound.x = b2MaxFloat(a.upperBound.x, b.upperBound.x);
        c.upperBound.y = b2MaxFloat(a.upperBound.y, b.upperBound.y);
        return c;
    }

    /**
     * 判断一个AABB包围盒是否完全包含另一个AABB包围盒。若检测结果为真，说明目标包围盒的所有边界均在被检测框内
     */
    public static boolean b2AABB_Contains(b2AABB a, b2AABB b) {
        boolean s = true;
        s = s && a.lowerBound.x <= b.lowerBound.x;
        s = s && a.lowerBound.y <= b.lowerBound.y;
        s = s && b.upperBound.x <= a.upperBound.x;
        s = s && b.upperBound.y <= a.upperBound.y;
        return s;
    }


    /**
     * 判断两个AABB包围盒是否存在任何区域重叠。只要存在部分交集即返回真，用于快速筛选可能发生碰撞的对象
     */
    /// Do a and b overlap
    public static boolean b2AABB_Overlaps(b2AABB a, b2AABB b) {
        return !(b.lowerBound.x > a.upperBound.x || b.lowerBound.y > a.upperBound.y || a.lowerBound.x > b.upperBound.x ||
                a.lowerBound.y > b.upperBound.y);
    }


    /// Enlarge a to contain b
/// @return true if the AABB grew
    public static boolean b2EnlargeAABB(b2AABB a, b2AABB b) {
        boolean changed = false;
        if (b.lowerBound.x < a.lowerBound.x) {
            a.lowerBound.x = b.lowerBound.x;
            changed = true;
        }

        if (b.lowerBound.y < a.lowerBound.y) {
            a.lowerBound.y = b.lowerBound.y;
            changed = true;
        }

        if (a.upperBound.x < b.upperBound.x) {
            a.upperBound.x = b.upperBound.x;
            changed = true;
        }

        if (a.upperBound.y < b.upperBound.y) {
            a.upperBound.y = b.upperBound.y;
            changed = true;
        }

        return changed;
    }


    public static boolean b2IsValidAABB(b2AABB a) {
        b2Vec2 d = b2Sub(a.upperBound, a.lowerBound);
        boolean valid = d.x >= 0.0f && d.y >= 0.0f;
        valid = valid && b2IsValidVec2(a.lowerBound) && b2IsValidVec2(a.upperBound);
        return valid;
    }

    public static boolean b2IsValidVec2(b2Vec2 v) {
        if (Float.isNaN(v.x) || Float.isNaN(v.y)) {
            return false;
        }

        if (Float.isFinite(v.x) || Float.isFinite(v.y)) {
            return false;
        }

        return true;
    }

    public static float b2MinFloat(float a, float b) {
        return a < b ? a : b;
    }

    /// @return the maximum of two floats
    public static float b2MaxFloat(float a, float b) {
        return a > b ? a : b;
    }

    public static b2Vec2 b2Add(b2Vec2 a, b2Vec2 b) {
        return new b2Vec2(a.x + b.x, a.y + b.y);
    }

    /// Vector subtraction
    public static b2Vec2 b2Sub(b2Vec2 a, b2Vec2 b) {
        return new b2Vec2(a.x - b.x, a.y - b.y);
    }

    /// Vector negation
    public static b2Vec2 b2Neg(b2Vec2 a) {
        return new b2Vec2(-a.x, -a.y);
    }

    public static float b2LengthSquared(b2Vec2 v) {
        return v.x * v.x + v.y * v.y;
    }


    public static b2Vec2 b2Normalize(b2Vec2 v) {
        double length = Math.sqrt(v.x * v.x + v.y * v.y);
        if (length < 0.00001) {
            return new b2Vec2();
        }

        double invLength = 1.0f / length;
        b2Vec2 n = new b2Vec2((float) (invLength * v.x), (float) (invLength * v.y));
        return n;
    }

    public static int b2MaxInt(int height1, int height2) {
        return Math.max(height1, height2);
    }

    public static b2Vec2 b2CrossSV(float s, b2Vec2 v) {
        return new b2Vec2(-s * v.y, s * v.x);
    }

    public static b2Vec2 b2Abs(b2Vec2 a) {
        b2Vec2 b = new b2Vec2();
        b.x = Math.abs(a.x);
        b.y = Math.abs(a.y);
        return b;
    }


    public static b2Vec2 b2AABB_Extents(b2AABB a) {
        b2Vec2 b = new b2Vec2(0.5f * (a.upperBound.x - a.lowerBound.x), 0.5f * (a.upperBound.y - a.lowerBound.y));
        return b;
    }

    public static float b2Dot(b2Vec2 a, b2Vec2 b) {
        return a.x * b.x + a.y * b.y;
    }

    /// Vector cross product. In 2D this yields a scalar.
    public static float b2Cross(b2Vec2 a, b2Vec2 b) {
        return a.x * b.y - a.y * b.x;
    }

    public static float b2AbsFloat(float a) {
        return a < 0 ? -a : a;
    }

    public static float b2DistanceSquared(b2Vec2 a, b2Vec2 b) {
        b2Vec2 c = new b2Vec2(b.x - a.x, b.y - a.y);
        return c.x * c.x + c.y * c.y;
    }


    public static b2Vec2 b2MulAdd(b2Vec2 a, float s, b2Vec2 b) {
        return new b2Vec2(a.x + s * b.x, a.y + s * b.y);
    }

    public static b2Vec2 b2Min(b2Vec2 a, b2Vec2 b) {
        b2Vec2 c = new b2Vec2();
        c.x = b2MinFloat(a.x, b.x);
        c.y = b2MinFloat(a.y, b.y);
        return c;
    }

    /// Component-wise maximum vector
    public static b2Vec2 b2Max(b2Vec2 a, b2Vec2 b) {
        b2Vec2 c = new b2Vec2();
        c.x = b2MaxFloat(a.x, b.x);
        c.y = b2MaxFloat(a.y, b.y);
        return c;
    }

    public static b2Vec2 b2MulSV(float s, b2Vec2 v) {
        return new b2Vec2(s * v.x, s * v.y);
    }
}
