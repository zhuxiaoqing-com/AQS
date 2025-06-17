package com.youxi.dataStructure.box2d3_2.jgk;

import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.B2Simplex;
import com.youxi.math.box2dGJK.B2SimplexVertex;
import com.youxi.math.box2dGJK.b2ShapeProxy;
import com.youxi.math.box2dGJK.shape.B2Vec2;

import java.util.List;

import static com.youxi.math.box2dGJK.B2Math.*;

public class Distance {

    //  b2Distance的输入
//  在计算中你必须选择使用形状的半径
    public static class b2DistanceInput {
        b2ShapeProxy proxyA;    //距离代理A
        b2ShapeProxy proxyB;    //距离代理B
        /*B2Transform transformA;    //转换A
        B2Transform transformB;    //转换B*/
        boolean useRadii;             //是否使用半径

        public b2DistanceInput(b2ShapeProxy proxyA, b2ShapeProxy proxyB, boolean useRadii) {
            this.proxyA = proxyA;
            this.proxyB = proxyB;
            this.useRadii = useRadii;
        }
    }

    ;

    // b2Distance的输出
    public static class b2DistanceOutput {
        B2Vec2 pointA = new B2Vec2(0, 0);        //shapeA上最近的点
        B2Vec2 pointB = new B2Vec2(0, 0);        //shapeB上最近的点
        float distance;   //距离
        int iterations;    //GJK的迭代次数
    }

    b2DistanceOutput b2ShapeDistance(b2DistanceInput input, List<B2Simplex> simplexes, int simplexCapacity) {
        B2_ASSERT(input.proxyA.count > 0 && input.proxyB.count > 0);
        B2_ASSERT(input.proxyA.radius >= 0.0f);
        B2_ASSERT(input.proxyB.radius >= 0.0f);

        b2DistanceOutput output = new b2DistanceOutput();

        b2ShapeProxy proxyA = input.proxyA;

        // Get proxyB in frame A to avoid further transforms in the main loop.
        // This is still a performance gain at 8 points.
        b2ShapeProxy localProxyB = input.proxyB;

        // Initialize the simplex.
        B2Simplex simplex = new B2Simplex();
        simplex.init(proxyA, localProxyB);

        int simplexIndex = 0;
        if (simplexes != null && simplexIndex < simplexCapacity) {
            simplexes.add(simplex);
            simplexIndex += 1;
        }

        // Get simplex vertices as an array.
        B2SimplexVertex[] vertices = new B2SimplexVertex[]{simplex.v1, simplex.v2, simplex.v3};

        B2Vec2 nonUnitNormal = new B2Vec2();

        // These store the vertices of the last simplex so that we can check for duplicates and prevent cycling.
        int saveA[] = new int[3];
        int saveB[] = new int[3];

        // Main iteration loop. All computations are done in frame A.
        int maxIterations = 20;
        int iteration = 0;
        while (iteration < maxIterations) {
            // Copy simplex so we can identify duplicates.
            int saveCount = simplex.count;
            for (int i = 0; i < saveCount; ++i) {
                saveA[i] = vertices[i].indexA;
                saveB[i] = vertices[i].indexB;
            }

            B2Vec2 d = new B2Vec2();
            switch (simplex.count) {
                case 1:
                    d = B2Math.b2Neg(simplex.v1.w);
                    break;

                case 2:
                    d = b2SolveSimplex2(simplex);
                    break;

                case 3:
                    d = b2SolveSimplex3(simplex);
                    break;

                default:
                    B2_ASSERT(false);
            }

            // If we have 3 points, then the origin is in the corresponding triangle.
            if (simplex.count == 3) {
                // Overlap
                B2Vec2 localPointA = new B2Vec2(), localPointB = new B2Vec2();
                b2ComputeSimplexWitnessPoints(localPointA, localPointB, simplex);
                output.pointA = localPointA;
                output.pointB = localPointB;
                return output;
            }


            if (simplexes != null && simplexIndex < simplexCapacity) {
                simplexes.add(simplex);
                simplexIndex += 1;
            }

            // 方向太小了
            // Ensure the search direction is numerically fit.
            if (b2Dot(d, d) < B2Math.b2_epsilon * B2Math.b2_epsilon) {
                // This is unlikely but could lead to bad cycling.
                // The branch predictor seems to make this check have low cost.

                // The origin is probably contained by a line segment
                // or triangle. Thus the shapes are overlapped.

                // Must return overlap due to invalid normal.
                //原点有可能包含在线段或三角形内，因此形状是重叠的
                //尽管这里有可能重叠，我们不能在这里返回0
                //如果单纯形是一个点、线、三角形，它很难判断原点是否包含在在其中或者是非常靠近
                B2Vec2 localPointA, localPointB;
                b2ComputeSimplexWitnessPoints( & localPointA, &localPointB, &simplex );
                output.pointA = b2TransformPoint(input.transformA, localPointA);
                output.pointB = b2TransformPoint(input.transformA, localPointB);
                return output;
            }

            // Save the normal
            nonUnitNormal = d;

            // Compute a tentative new simplex vertex using support points.
            // support = support(a, d) - support(b, -d)
            b2SimplexVertex * vertex = vertices[simplex.count];
            vertex.indexA = b2FindSupport(proxyA, d);
            vertex.wA = proxyA.points[vertex.indexA];
            vertex.indexB = b2FindSupport( & localProxyB, b2Neg(d) );
            vertex.wB = localProxyB.points[vertex.indexB];
            vertex.w = b2Sub(vertex.wA, vertex.wB);

            // Iteration count is equated to the number of support point calls.
            ++iteration;

            // Check for duplicate support points. This is the main termination criteria.
            bool duplicate = false;
            for (int i = 0; i < saveCount; ++i) {
                if (vertex.indexA == saveA[i] && vertex.indexB == saveB[i]) {
                    duplicate = true;
                    break;
                }
            }

            // If we found a duplicate support point we must exit to avoid cycling.
            if (duplicate) {
                break;
            }

            // New vertex is valid and needed.
            simplex.count += 1;
        }

#ifndef NDEBUG
        if (simplexes != NULL && simplexIndex < simplexCapacity) {
            simplexes[simplexIndex] = simplex;
            simplexIndex += 1;
        }
#endif

        // Prepare output
        B2Vec2 normal = b2Normalize(nonUnitNormal);
        B2_ASSERT(b2IsNormalized(normal));
        normal = b2RotateVector(input.transformA.q, normal);

        B2Vec2 localPointA, localPointB;
        b2ComputeSimplexWitnessPoints( & localPointA, &localPointB, &simplex );
        output.normal = normal;
        output.distance = b2Distance(localPointA, localPointB);
        output.pointA = b2TransformPoint(input.transformA, localPointA);
        output.pointB = b2TransformPoint(input.transformA, localPointB);
        output.iterations = iteration;
        output.simplexCount = simplexIndex;

        // Cache the simplex
        b2MakeSimplexCache(cache, & simplex );

        // Apply radii if requested
        if (input.useRadii && output.distance > 0.1f * B2_LINEAR_SLOP) {
            float radiusA = input.proxyA.radius;
            float radiusB = input.proxyB.radius;
            output.distance = b2MaxFloat(0.0f, output.distance - radiusA - radiusB);

            // Keep closest points on perimeter even if overlapped, this way the points move smoothly.
            output.pointA = b2MulAdd(output.pointA, radiusA, normal);
            output.pointB = b2MulSub(output.pointB, radiusB, normal);
        }

        return output;
    }

    private static void B2_ASSERT(boolean b) {
        if (!b) {
            throw new RuntimeException("B2_ASSERT failed");
        }
    }

    // Solve a line segment using barycentric coordinates.
//
// p = a1 * w1 + a2 * w2
// a1 + a2 = 1
//
// The vector from the origin to the closest point on the line is
// perpendicular to the line.
// e12 = w2 - w1
// dot(p, e) = 0
// a1 * dot(w1, e) + a2 * dot(w2, e) = 0
//
// 2-by-2 linear system
// [1      1     ][a1] = [1]
// [w1.e12 w2.e12][a2] = [0]
//
// Define
// d12_1 =  dot(w2, e12)
// d12_2 = -dot(w1, e12)
// d12 = d12_1 + d12_2
//
// Solution
// a1 = d12_1 / d12
// a2 = d12_2 / d12
//
// returns a vector that points towards the origin
    // 具体看 B2Distance 的 Solve2 方法
    static B2Vec2 b2SolveSimplex2(B2Simplex s) {
        B2Vec2 w1 = s.v1.w;
        B2Vec2 w2 = s.v2.w;
        B2Vec2 e12 = b2Sub(w2, w1);

        // w1 region
        float d12_2 = -B2Math.b2Dot(w1, e12);
        if (d12_2 <= 0.0f) {
            // a2 <= 0, so we clamp it to 0
            s.v1.a = 1.0f;
            s.count = 1;
            return B2Math.b2Neg(w1);
        }

        // w2 region
        float d12_1 = B2Math.b2Dot(w2, e12);
        if (d12_1 <= 0.0f) {
            // a1 <= 0, so we clamp it to 0
            s.v2.a = 1.0f;
            s.count = 1;
            s.v1 = s.v2;
            return B2Math.b2Neg(w2);
        }

        // Must be in e12 region.
        float inv_d12 = 1.0f / (d12_1 + d12_2);
        s.v1.a = d12_1 * inv_d12;
        s.v2.a = d12_2 * inv_d12;
        s.count = 2;
        // 返回一个指向原点的向量
        // 这里 B2Math.b2Cross(B2Math.b2Add(w1, w2), e12) e12是线段, w1+w2是从原点出来的向量, 两个叉乘可以获取和原点的方向(顺时针 还是逆时针);
        return B2Math.b2CrossSV(B2Math.b2Cross(B2Math.b2Add(w1, w2), e12), e12);
    }


    static B2Vec2 b2SolveSimplex3(B2Simplex s) {
        B2Vec2 w1 = s.v1.w;
        B2Vec2 w2 = s.v2.w;
        B2Vec2 w3 = s.v3.w;

        // Edge12
        // [1      1     ][a1] = [1]
        // [w1.e12 w2.e12][a2] = [0]
        // a3 = 0
        B2Vec2 e12 = b2Sub(w2, w1);
        float w1e12 = b2Dot(w1, e12);
        float w2e12 = b2Dot(w2, e12);
        float d12_1 = w2e12;
        float d12_2 = -w1e12;

        // Edge13
        // [1      1     ][a1] = [1]
        // [w1.e13 w3.e13][a3] = [0]
        // a2 = 0
        B2Vec2 e13 = b2Sub(w3, w1);
        float w1e13 = b2Dot(w1, e13);
        float w3e13 = b2Dot(w3, e13);
        float d13_1 = w3e13;
        float d13_2 = -w1e13;

        // Edge23
        // [1      1     ][a2] = [1]
        // [w2.e23 w3.e23][a3] = [0]
        // a1 = 0
        B2Vec2 e23 = b2Sub(w3, w2);
        float w2e23 = b2Dot(w2, e23);
        float w3e23 = b2Dot(w3, e23);
        float d23_1 = w3e23;
        float d23_2 = -w2e23;

        // Triangle123
        float n123 = b2Cross(e12, e13);

        float d123_1 = n123 * b2Cross(w2, w3);
        float d123_2 = n123 * b2Cross(w3, w1);
        float d123_3 = n123 * b2Cross(w1, w2);

        // w1 region
        if (d12_2 <= 0.0f && d13_2 <= 0.0f) {
            s.v1.a = 1.0f;
            s.count = 1;
            return b2Neg(w1);
        }

        // e12
        if (d12_1 > 0.0f && d12_2 > 0.0f && d123_3 <= 0.0f) {
            float inv_d12 = 1.0f / (d12_1 + d12_2);
            s.v1.a = d12_1 * inv_d12;
            s.v2.a = d12_2 * inv_d12;
            s.count = 2;
            return b2CrossSV(b2Cross(b2Add(w1, w2), e12), e12);
        }

        // e13
        if (d13_1 > 0.0f && d13_2 > 0.0f && d123_2 <= 0.0f) {
            float inv_d13 = 1.0f / (d13_1 + d13_2);
            s.v1.a = d13_1 * inv_d13;
            s.v3.a = d13_2 * inv_d13;
            s.count = 2;
            s.v2 = s.v3;
            return b2CrossSV(b2Cross(b2Add(w1, w3), e13), e13);
        }

        // w2 region
        if (d12_1 <= 0.0f && d23_2 <= 0.0f) {
            s.v2.a = 1.0f;
            s.count = 1;
            s.v1 = s.v2;
            return b2Neg(w2);
        }

        // w3 region
        if (d13_1 <= 0.0f && d23_1 <= 0.0f) {
            s.v3.a = 1.0f;
            s.count = 1;
            s.v1 = s.v3;
            return b2Neg(w3);
        }

        // e23
        if (d23_1 > 0.0f && d23_2 > 0.0f && d123_1 <= 0.0f) {
            float inv_d23 = 1.0f / (d23_1 + d23_2);
            s.v2.a = d23_1 * inv_d23;
            s.v3.a = d23_2 * inv_d23;
            s.count = 2;
            s.v1 = s.v3;
            return b2CrossSV(b2Cross(b2Add(w2, w3), e23), e23);
        }

        // Must be in triangle123
        float inv_d123 = 1.0f / (d123_1 + d123_2 + d123_3);
        s.v1.a = d123_1 * inv_d123;
        s.v2.a = d123_2 * inv_d123;
        s.v3.a = d123_3 * inv_d123;
        s.count = 3;

        // No search direction
        return new B2Vec2();
    }

    /**************************************************************************
     * 功能描述：获取见证点【形状上的参与求解的两个顶点】
     * 参数说明： pA：形状A上的点
     pB：形状B上的点
     * 返 回 值： (void)
     ***************************************************************************/
    static void b2ComputeSimplexWitnessPoints(B2Vec2 pA, B2Vec2 pB, B2Simplex s) {
        switch (s.count) {
            case 1:
                /**a = s.v1.wA;
                 *b = s.v1.wB;*/
                pA.Set(s.v1.wA);
                pB.Set(s.v1.wB);
                break;

            case 2://对于2个点，我们用重心坐标求的的，至于为什么要用重心坐标，我们在下面的讲解中将会看到
                // 见证点这里其实一般都会有一边是点 一边是边;所以这个重心坐标其实一般都是给边的那一边用;
                //通过权重a1和a2（由Solve2()计算）对顶点坐标线性插值，得到线段上的最近点3。
                /**a = b2Weight2(s.v1.a, s.v1.wA, s.v2.a, s.v2.wA);
                 *b = b2Weight2(s.v1.a, s.v1.wB, s.v2.a, s.v2.wB);*/
                pA.Set(add(multiply(s.v1.a, s.v1.wA), multiply(s.v2.a, s.v2.wA)));
                pB.Set(add(multiply(s.v1.a, s.v1.wB), multiply(s.v2.a, s.v2.wB)));
                break;

            case 3://对于3个点，我们可以判断此时两形状一碰撞了，见证点，就是两形状的碰撞点，故是一样的。
                /**a = b2Weight3(s.v1.a, s.v1.wA, s.v2.a, s.v2.wA, s.v3.a, s.v3.wA);
                 // todo why are these not equal?
                 //*b = b2Weight3(s.v1.a, s.v1.wB, s.v2.a, s.v2.wB, s.v3.a, s.v3.wB);
                 *b = * a;*/
                pA.Set(add(multiply(s.v1.a, s.v1.wA), multiply(s.v2.a, s.v2.wA), multiply(s.v3.a, s.v3.wA)));
                pB.Set(pA);
                break;

            default:
                /**a = B2Vec2_zero;
                 *b = B2Vec2_zero;*/
                a.setXY(0, 0);
                b.setXY(0, 0);
                B2_ASSERT(false);
                break;
        }
    }

}
