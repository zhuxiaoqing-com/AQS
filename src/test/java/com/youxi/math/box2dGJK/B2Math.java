package com.youxi.math.box2dGJK;


import com.youxi.math.box2dGJK.shape.B2AABB;
import com.youxi.math.box2dGJK.shape.B2Rot;
import com.youxi.math.box2dGJK.shape.B2Transform;
import com.youxi.math.box2dGJK.shape.B2Vec2;
import org.apache.regexp.RE;

public class B2Math {
    public static float b2_epsilon = 0.0000001f;
    public static float b2_polygonRadius = 2.0f * 0.005f;
    public static float b2_linearSlop = 0.005f;
    public static float B2_LINEAR_SLOP = 0.005f;


    public static B2Vec2 b2Mul(B2Transform T, B2Vec2 v) {
        float x = (T.q.c * v.x - T.q.s * v.y) + T.p.x;
        float y = (T.q.s * v.x + T.q.c * v.y) + T.p.y;
        return new B2Vec2(x, y);
    }


    public static B2Vec2 b2Mul(B2Rot q, B2Vec2 v) {
        return new B2Vec2(q.c * v.x - q.s * v.y, q.s * v.x + q.c * v.y);
    }

    /// Inverse rotate a vector
    public static B2Vec2 b2MulT(B2Rot q, B2Vec2 v) {
        return new B2Vec2(q.c * v.x + q.s * v.y, -q.s * v.x + q.c * v.y);
    }

    public static float b2Distance(B2Vec2 a, B2Vec2 b) {
        B2Vec2 c = a.sub(b);
        return c.length();
    }

    /**
     * 设置1我们将返回的向量视为逆时针旋转了90°，或者将 s 设置为-1时视为顺时针旋转了90°
     */
    public static B2Vec2 b2Cross(B2Vec2 a, float s) {
        return new B2Vec2(s * a.y, -s * a.x);
    }

    public static float b2Cross(B2Vec2 a, B2Vec2 b) {
        return a.cross(b);
    }

    /**
     * 设置1我们将返回的向量视为顺时针旋转了90°，或者将 s 设置为-1时视为逆时针旋转了90°
     */
    public static B2Vec2 b2Cross(float s, B2Vec2 a) {
        return new B2Vec2(-s * a.y, s * a.x);
    }

    public static float b2Dot(B2Vec2 a, B2Vec2 b) {
        return a.dot(b);
    }

    public static B2Vec2 add(B2Vec2 a, B2Vec2 B2Vec2) {
        return new B2Vec2(a.x + B2Vec2.x, a.y + B2Vec2.y);
    }
    public static B2Vec2 addZeroGC(B2Vec2 a, B2Vec2 B2Vec2, B2Vec2 ret) {
        ret.setXY(a.x + B2Vec2.x, a.y + B2Vec2.y);
        return ret;
    }
    public static B2Vec2 add(B2Vec2 a, B2Vec2 B2Vec2, B2Vec2 c) {
        return new B2Vec2(a.x + B2Vec2.x + c.x, a.y + B2Vec2.y + c.y);
    }
    public static B2Vec2 addZeroGC(B2Vec2 a, B2Vec2 B2Vec2, B2Vec2 c, B2Vec2 ret) {
        ret.setXY(a.x + B2Vec2.x + c.x, a.y + B2Vec2.y + c.y);
        return ret;
        //return new B2Vec2(a.x + B2Vec2.x + c.x, a.y + B2Vec2.y + c.y);
    }


    public static B2Vec2 add(B2Vec2 a, B2Vec2 B2Vec2, float t) {
        return new B2Vec2((a.x + B2Vec2.x) * t, (a.y + B2Vec2.y) * t);
    }

    public static B2Vec2 addZeroGC(B2Vec2 a, B2Vec2 B2Vec2, float t, B2Vec2 ret) {
        ret.setXY((a.x + B2Vec2.x) * t, (a.y + B2Vec2.y) * t);
        return ret;
    }




    public static B2Vec2 sub(B2Vec2 a, B2Vec2 B2Vec2) {
        return new B2Vec2(a.x - B2Vec2.x, a.y - B2Vec2.y);
    }

    public static B2Vec2 subZeroGC(B2Vec2 a, B2Vec2 B2Vec2, B2Vec2 ret) {
        ret.setXY(a.x - B2Vec2.x, a.y - B2Vec2.y);
        return ret;
    }

    public static B2Vec2 sub(B2Vec2 a, B2Vec2 B2Vec2, float t) {
        return new B2Vec2((a.x - B2Vec2.x) * t, (a.y - B2Vec2.y) * t);
    }
    public static B2Vec2 subZeroGC(B2Vec2 a, B2Vec2 B2Vec2, float t, B2Vec2 ret) {
        ret.setXY((a.x - B2Vec2.x) * t, (a.y - B2Vec2.y) * t);
        return ret;
    }

    public static B2Vec2 multiply(B2Vec2 a, float v) {
        return new B2Vec2(a.x * v, a.y * v);
    }

    public static B2Vec2 multiply(float v, B2Vec2 a) {
        return new B2Vec2(a.x * v, a.y * v);
    }

    public static B2Vec2 multiplyZeroGC(float v, B2Vec2 a, B2Vec2 ret) {
        ret.setXY(a.x * v, a.y * v);
        return ret;
        //return new B2Vec2(a.x * v, a.y * v);
    }

    public static B2Vec2 b2Min(B2Vec2 a, B2Vec2 b) {
        return new B2Vec2(Math.min(a.x, b.x), Math.min(a.y, b.y));
    }
    public static B2Vec2 b2MinZeroGC(B2Vec2 a, B2Vec2 b, B2Vec2 ret) {
        ret.setXY(Math.min(a.x, b.x), Math.min(a.y, b.y));
        return ret;
        //return new B2Vec2(Math.min(a.x, b.x), Math.min(a.y, b.y));
    }


    public static B2Vec2 b2Max(B2Vec2 a, B2Vec2 b) {
        return new B2Vec2(Math.max(a.x, b.x), Math.max(a.y, b.y));
    }
    public static B2Vec2 b2MaxZeroGC(B2Vec2 a, B2Vec2 b, B2Vec2 ret) {
        ret.setXY(Math.max(a.x, b.x), Math.max(a.y, b.y));
        return ret;
    }

    public static B2Vec2 b2Abs(B2Vec2 a) {
        return new B2Vec2(Math.abs(a.x), Math.abs(a.y));
    }
    public static B2Vec2 b2AbsZeroGC(B2Vec2 a, B2Vec2 ret) {
        ret.setXY(Math.abs(a.x), Math.abs(a.y));
        return ret;
    }


    /// Get the center of the AABB.
    public static B2Vec2 B2AABB_Center(B2AABB a) {
        //return new B2Vec2(0.5f * (a.lowerBound.x + a.upperBound.x), 0.5f * (a.lowerBound.y + a.upperBound.y));
        return a.GetCenter();
    }

    public static float b2Perimeter(B2AABB a, B2AABB b) {
        float lowerX = b2MinFloat(a.lowerBound.x, b.lowerBound.x);
        float lowerY = b2MinFloat(a.lowerBound.y, b.lowerBound.y);
        float upperX = b2MaxFloat(a.upperBound.x, b.upperBound.x);
        float upperY = b2MaxFloat(a.upperBound.y, b.upperBound.y);

        float wx = upperX - lowerX;
        float wy = upperY - lowerY;
        return 2.0f * (wx + wy);
    }


    /**
     * 周长
     */
    public static float b2Perimeter(B2AABB a) {
        float wx = a.upperBound.x - a.lowerBound.x;
        float wy = a.upperBound.y - a.lowerBound.y;
        return 2.0f * (wx + wy);
    }

    /// Union of two AABBs
    public static B2AABB B2AABB_Union(B2AABB a, B2AABB b) {
        B2AABB c = new B2AABB();
        c.lowerBound.x = b2MinFloat(a.lowerBound.x, b.lowerBound.x);
        c.lowerBound.y = b2MinFloat(a.lowerBound.y, b.lowerBound.y);
        c.upperBound.x = b2MaxFloat(a.upperBound.x, b.upperBound.x);
        c.upperBound.y = b2MaxFloat(a.upperBound.y, b.upperBound.y);
        c.calcCenterAndHalfB2Vec2();
        return c;
    }

    public static B2AABB B2AABB_Union(B2Vec2 a, B2Vec2 b) {
        B2AABB c = new B2AABB();
        c.lowerBound.x = b2MinFloat(a.x, b.x);
        c.lowerBound.y = b2MinFloat(a.y, b.y);
        c.upperBound.x = b2MaxFloat(a.x, b.x);
        c.upperBound.y = b2MaxFloat(a.y, b.y);
        c.calcCenterAndHalfB2Vec2();
        return c;
    }


    public static B2AABB B2AABB_UnionZeroGC(B2Vec2 a, B2Vec2 b, B2AABB ret) {
        ret.lowerBound.x = b2MinFloat(a.x, b.x);
        ret.lowerBound.y = b2MinFloat(a.y, b.y);
        ret.upperBound.x = b2MaxFloat(a.x, b.x);
        ret.upperBound.y = b2MaxFloat(a.y, b.y);
        ret.calcCenterAndHalfB2Vec2();
        return ret;
    }



    /**
     * 判断一个AABB包围盒是否完全包含另一个AABB包围盒。若检测结果为真，说明目标包围盒的所有边界均在被检测框内
     */
    public static boolean B2AABB_Contains(B2AABB a, B2AABB b) {
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
    public static boolean B2AABB_Overlaps(B2AABB a, B2AABB b) {
        return !(b.lowerBound.x > a.upperBound.x || b.lowerBound.y > a.upperBound.y || a.lowerBound.x > b.upperBound.x ||
                a.lowerBound.y > b.upperBound.y);
    }


    /// Enlarge a to contain b
    ///
    /// @return true if the AABB grew
    public static boolean b2EnlargeAABB(B2AABB a, B2AABB b) {
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


    public static boolean b2IsValidAABB(B2AABB a) {
        B2Vec2 d = b2Sub(a.upperBound, a.lowerBound);
        boolean valid = d.x >= 0.0f && d.y >= 0.0f;
        valid = valid && b2IsValidVec2(a.lowerBound) && b2IsValidVec2(a.upperBound);
        return valid;
    }

    public static boolean b2IsValidVec2(B2Vec2 v) {
        if (Float.isNaN(v.x) || Float.isNaN(v.y)) {
            return false;
        }

        if (!Float.isFinite(v.x) || !Float.isFinite(v.y)) {
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

    public static B2Vec2 b2Add(B2Vec2 a, B2Vec2 b) {
        return new B2Vec2(a.x + b.x, a.y + b.y);
    }

    public static B2Vec2 b2AddZeroGC(B2Vec2 a, B2Vec2 b, B2Vec2 ret) {
        ret.setXY(a.x + b.x, a.y + b.y);
        return ret;
    }

    /// Vector subtraction
    public static B2Vec2 b2Sub(B2Vec2 a, B2Vec2 b) {
        return new B2Vec2(a.x - b.x, a.y - b.y);
    }
    public static B2Vec2 b2SubZeroGC(B2Vec2 a, B2Vec2 b, B2Vec2 ret) {
        ret.setXY(a.x - b.x, a.y - b.y);
        return ret;
    }

    /// Vector negation
    public static B2Vec2 b2Neg(B2Vec2 a) {
        return new B2Vec2(-a.x, -a.y);
    }

    public static B2Vec2 b2NegZeroGC(B2Vec2 a, B2Vec2 ret) {
        ret.setXY(-a.x, -a.y);
        return ret;
    }

    public static float b2LengthSquared(B2Vec2 v) {
        return v.x * v.x + v.y * v.y;
    }


    public static float b2LengthSquared(B2Vec2 a, B2Vec2 b) {
        float x = a.getX() - b.getX();
        float y = a.getY() - b.getY();
        return x*x + y*y;
    }


    public static B2Vec2 b2Normalize(B2Vec2 v) {
        double length = Math.sqrt(v.x * v.x + v.y * v.y);
        if (length < 0.00001) {
            return new B2Vec2();
        }

        double invLength = 1.0f / length;
        B2Vec2 n = new B2Vec2((float) (invLength * v.x), (float) (invLength * v.y));
        return n;
    }

    public static B2Vec2 b2NormalizeZeroGC(B2Vec2 v, B2Vec2 ret) {
        double length = Math.sqrt(v.x * v.x + v.y * v.y);
        if (length < 0.00001) {
            ret.setXY(0,0);
            return ret;
        }

        double invLength = 1.0f / length;
        ret.setXY((float) (invLength * v.x), (float) (invLength * v.y));
        return ret;
    }

    public static int b2MaxInt(int height1, int height2) {
        return Math.max(height1, height2);
    }

    /**
     * 设置1我们将返回的向量视为逆时针旋转了90°，或者将 s 设置为-1时视为顺时针旋转了90°
     */
    public static B2Vec2 b2CrossSV(float s, B2Vec2 v) {
        return new B2Vec2(-s * v.y, s * v.x);
    }

    public static B2Vec2 b2CrossSVZeroGC(float s, B2Vec2 v, B2Vec2 ret) {
        ret.setXY(-s * v.y, s * v.x);
        return ret;
    }


    public static B2Vec2 B2AABB_Extents(B2AABB a) {
        //B2Vec2 b = new B2Vec2(0.5f * (a.upperBound.x - a.lowerBound.x), 0.5f * (a.upperBound.y - a.lowerBound.y));
        return a.GetExtents();
    }


    public static float b2AbsFloat(float a) {
        return a < 0 ? -a : a;
    }

    public static float b2DistanceSquared(B2Vec2 a, B2Vec2 b) {
        B2Vec2 c = new B2Vec2(b.x - a.x, b.y - a.y);
        return c.x * c.x + c.y * c.y;
    }


    public static B2Vec2 b2MulAdd(B2Vec2 a, float s, B2Vec2 b) {
        return new B2Vec2(a.x + s * b.x, a.y + s * b.y);
    }
    public static B2Vec2 b2MulAddZeroGC(B2Vec2 a, float s, B2Vec2 b, B2Vec2 returnVec2) {
        returnVec2.setXY(a.x + s * b.x, a.y + s * b.y);
        return returnVec2;
    }

    public static B2Vec2 b2MulSub(B2Vec2 a, float s, B2Vec2 b) {
        return new B2Vec2(a.x - s * b.x, a.y - s * b.y);
    }

    public static B2Vec2 b2MulSubZeroGC(B2Vec2 a, float s, B2Vec2 b, B2Vec2 ret) {
        ret.setXY(a.x - s * b.x, a.y - s * b.y);
        return ret;
    }


    public static B2Vec2 b2MulSV(float s, B2Vec2 v) {
        return new B2Vec2(s * v.x, s * v.y);
    }
    public static B2Vec2 b2MulSVZeroGC(float s, B2Vec2 v, B2Vec2 ret) {
        ret.setXY(s * v.x, s * v.y);
        return ret;
        //return new B2Vec2(s * v.x, s * v.y);
    }

    public static B2Vec2 b2Lerp(B2Vec2 a, B2Vec2 b, float t) {
        return new B2Vec2((1.0f - t) * a.x + t * b.x, (1.0f - t) * a.y + t * b.y);
    }
    public static B2Vec2 b2LerpZeroGC(B2Vec2 a, B2Vec2 b, float t, B2Vec2 returnVec2) {
        returnVec2.setXY((1.0f - t) * a.x + t * b.x, (1.0f - t) * a.y + t * b.y);
        return returnVec2;
    }

    public static B2Vec2 rotate(B2Vec2 b, float deg) {
        float radians = (float) Math.toRadians(deg);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        float x = (float) (b.getX() * cos - b.getY() * sin);
        float y = (float) (b.getY() * cos + b.getX() * sin);
        return new B2Vec2(x, y);
    }

    /**
     * unity 左手坐标系 应用右手坐标系的旋转需要 -deg
     */
    public static B2Vec2 unityRotate(B2Vec2 b, float deg) {
        float radians = (float) Math.toRadians(-deg);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        float x = (float) (b.getX() * cos - b.getY() * sin);
        float y = (float) (b.getY() * cos + b.getX() * sin);
        return new B2Vec2(x, y);
    }
    public static B2Vec2 unityRotateZeroGC(B2Vec2 b, float deg, B2Vec2 ret) {
        float radians = (float) Math.toRadians(-deg);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        float x = (float) (b.getX() * cos - b.getY() * sin);
        float y = (float) (b.getY() * cos + b.getX() * sin);

        ret.setXY(x, y);
        return ret;
    }

    /**
     * unity 左手坐标系 角度转向量
     */
    public static B2Vec2 unityDegreeToDir(float deg) {
        B2Vec2 b2Vec2 = new B2Vec2(0, 1);
        return unityRotate(b2Vec2, deg);
    }


    /**
     * a在b的顺时针方向 是负角度
     * a在b的逆时针方向 是正角度
     *
     * 在DirectX里用左手系，程序员调用cross(a,b)函数时，引擎内部其实偷偷把Z结果取反了，这样公式形式上就能统一用右手定则。
     */
    public static double getDegree(B2Vec2 a, B2Vec2 b) {
        double n = b2Dot(a, b);
        double cc = b2Cross(a, b);

        //(x1*x1 + z1*z1 ) *(x2*x2 + z2*z2 )
		/*double m = CMath.fixedMultiply(CMath.sqrt(CMath.square(x1) + CMath.square(z1)),
				CMath.sqrt(CMath.square(x2) + CMath.square(z2)));*/

        double m = a.length() * b.length();
        if (m == 0)
            return 0; // 正常情况不会出现这样，除非朝向向量为空。

        // 因为改为定点数以后有误差，导致cos值可能超过1，所以做矫正在-1，1范围内
        double ac = Math.min(Math.max(-1, n / m), 1);
        return Math.acos(ac) * (180 / Math.PI) * (cc > 0 ? -1 : 1);
    }


    public static float min(float a1, float a2, float a3, float a4) {
        float min = Math.min(a1, a2);
        min = Math.min(min, a3);
        min = Math.min(min, a4);
        return min;
    }

    public static float max(float a1, float a2, float a3, float a4) {
        float max = Math.max(a1, a2);
        max = Math.max(max, a3);
        max = Math.max(max, a4);
        return max;
    }
}
