package com.youxi.math.box2dGJK;

import com.youxi.math.box2dGJK.shape.B2Vec2;

import static com.youxi.math.box2dGJK.B2Math.*;

/**
 * box2d  2.4.1
 */
public class B2Distance {
    int b2_gjkCalls;
    int b2_gjkIters;
    int b2_gjkMaxIters;


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
        public B2Vec2 pointA = new B2Vec2(0, 0);        //shapeA上最近的点
        public B2Vec2 pointB = new B2Vec2(0, 0);        //shapeB上最近的点
        public float distance;   //距离
        public int iterations;    //GJK的迭代次数
    }

    ;


    /**************************************************************************
     * 功能描述：在两个形状间计算最近点。支持下面的任何组合：
     圆形、多边形、边缘形状。单纯形缓存输入/输出
     第一次调用设置b2SimplexCache.count为0
     * 参数说明： output ：距离输出指针
     cache  ：单纯形缓存指针
     input  ：距离输入指针
     * 返 回 值： (void)
     ***************************************************************************/
    //在两个形状间计算最近点
    void ___b2Distance(b2DistanceOutput output,
                       b2DistanceInput input) {
        ++b2_gjkCalls;
        //初始化代理
        b2ShapeProxy proxyA = input.proxyA;
        b2ShapeProxy proxyB = input.proxyB;
        //获取变换
		/*B2Transform transformA = input.transformA;
		B2Transform transformB = input.transformB;*/
        //初始化单纯形
        B2Simplex simplex = new B2Simplex();
        simplex.init_2(proxyA, proxyB);
        //获取单纯形顶点
        int k_maxIters = 20;
        // 存储最近的单纯形顶点，以便我们检测是否重复和阻止循环
        int saveA[] = new int[3];
        int saveB[] = new int[3];
        int saveCount = 0;
        //获取单纯形最近的顶点，并获取该点与原点组成的向量的长度平方
        B2Vec2 closestPoint = simplex.GetClosestPoint();
        float distanceSqr1 = closestPoint.LengthSquared();
        float distanceSqr2 = distanceSqr1;
        //主迭代循环
        int iter = 0;
        while (iter < k_maxIters) {
            // 拷贝单纯形，我们可以识别重复
            saveCount = simplex.count;
            for (int i = 0; i < saveCount; ++i) {
                saveA[i] = simplex.get(i).indexA;
                saveB[i] = simplex.get(i).indexB;
            }
            //获取单纯形顶点,并判断
            switch (simplex.count) {
                case 1:
                    break;
                case 2:
                    simplex.Solve2();
                    break;
                case 3:
                    simplex.Solve3();
                    break;

                default:
                    throw new RuntimeException();
            }
            // 如果我们有3个点，原点在相应的三角形内。
            if (simplex.count == 3) {
                break;
            }
            //计算最近的点
			/*B2Vec2 p = simplex.GetClosestPoint();
			distanceSqr2 = p.LengthSquared();


			//确保前进
			if (distanceSqr2 >= distanceSqr1) {
				//break;
			}
			distanceSqr1 = distanceSqr2;*/
            //获取查找方向
            B2Vec2 d = simplex.GetSearchDirection();
            //确保查找的方向是有效的
            if (d.LengthSquared() < b2_epsilon * b2_epsilon) {
                //原点有可能包含在线段或三角形内，因此形状是重叠的
                //尽管这里有可能重叠，我们不能在这里返回0
                //如果单纯形是一个点、线、三角形，它很难判断原点是否包含在在其中或者是非常靠近
                break;
            }
            //用支撑点计算一个试探性的新的单纯形顶点
            B2SimplexVertex vertex = simplex.get(simplex.count);
            vertex.indexA = proxyA.GetSupport(d.multiply(-1));
            vertex.wA = proxyA.GetVertex(vertex.indexA);
            B2Vec2 wBLocal;
            vertex.indexB = proxyB.GetSupport(d);
            vertex.wB = proxyB.GetVertex(vertex.indexB);
            vertex.w = sub(vertex.wB, vertex.wA);

            //迭代计数等于支撑点的调用数量
            ++iter;
            ++b2_gjkIters;
            //检测重复的支撑点，这是主要的终止条件
            boolean duplicate = false;
            for (int i = 0; i < saveCount; ++i) {
                if (vertex.indexA == saveA[i] && vertex.indexB == saveB[i]) {
                    duplicate = true;
                    break;
                }
            }
            // 如果我们发现了一个重复的支撑点我们必须退出循环
            if (duplicate) {
                break;
            }
            // 新的顶点是好的且需要的
            ++simplex.count;
        }

        b2_gjkMaxIters = Math.max(b2_gjkMaxIters, iter);
        // 准备输出
        simplex.GetWitnessPoints(output.pointA, output.pointB);
        output.distance = B2Math.b2Distance(output.pointA, output.pointB);
        output.iterations = iter;
        //将单纯形写入缓存
        //simplex.WriteCache(cache);
        // 如果被要求，则提供半径 这里是处理圆
        if (input.useRadii) {
            float rA = proxyA.radius;
            float rB = proxyB.radius;

            if (output.distance > rA + rB && output.distance > b2_epsilon) {
                // 形状没有重叠,移动见证点到外面
                output.distance -= rA + rB;
                B2Vec2 normal = sub(output.pointB, output.pointA);
                normal = normal.Normalize();
			/*	output.pointA += rA * normal;
				output.pointB -= rB * normal;*/
                output.pointA.add(normal.multiply(rA));
                output.pointB.add(normal.multiply(rB));
            } else {
                // 当形状重叠，移动见证点到中间
                B2Vec2 p = multiply(0.5f, (add(output.pointA, output.pointB)));
                output.pointA = p;
                output.pointB = p;
                output.distance = 0.0f;
            }
        }
    }


    public void b2Distance(b2DistanceOutput output, b2DistanceInput input) {
        ++b2_gjkCalls;

        b2ShapeProxy proxyA = input.proxyA;
        b2ShapeProxy proxyB = input.proxyB;


        // Initialize the simplex.
        B2Simplex simplex = new B2Simplex();
        simplex.init_2(proxyA, proxyB);

        // Get simplex vertices as an array.
        int k_maxIters = 20;

        // These store the vertices of the last simplex so that we
        // can check for duplicates and prevent cycling.
        int saveA[] = new int[3];
        int saveB[] = new int[3];
        int saveCount = 0;

        // Main iteration loop.
        int iter = 0;
        while (iter < k_maxIters) {
            // Copy simplex so we can identify duplicates.
            saveCount = simplex.count;
            for (int i = 0; i < saveCount; ++i) {
                saveA[i] = simplex.get(i).indexA;
                saveB[i] = simplex.get(i).indexB;
            }

            switch (simplex.count) {
                case 1:
                    break;

                case 2:
                    simplex.Solve2();
                    break;

                case 3:
                    simplex.Solve3();
                    break;

                default:
                    return;
            }

            // If we have 3 points, then the origin is in the corresponding triangle.
            if (simplex.count == 3) {
                break;
            }

            // Get search direction.
            B2Vec2 d = simplex.GetSearchDirection();

            // Ensure the search direction is numerically fit.
            if (d.LengthSquared() < b2_epsilon * b2_epsilon) {
                // The origin is probably contained by a line segment
                // or triangle. Thus the shapes are overlapped.

                // We can't return zero here even though there may be overlap.
                // In case the simplex is a point, segment, or triangle it is difficult
                // to determine if the origin is contained in the CSO or very close to it.
                break;
            }

            // Compute a tentative new simplex vertex using support points.
            B2SimplexVertex vertex = simplex.get(simplex.count);
            vertex.indexA = proxyA.GetSupport(d.multiply(-1));
            vertex.wA = proxyA.GetVertex(vertex.indexA);
            vertex.indexB = proxyB.GetSupport(d);
            vertex.wB = proxyB.GetVertex(vertex.indexB);
            vertex.w = vertex.wB.sub(vertex.wA);

            // Iteration count is equated to the number of support point calls.
            ++iter;
            ++b2_gjkIters;

            // Check for duplicate support points. This is the main termination criteria.
            boolean duplicate = false;
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

            // New vertex is ok and needed.
            ++simplex.count;
        }

        b2_gjkMaxIters = Math.max(b2_gjkMaxIters, iter);

        // Prepare output.
        simplex.GetWitnessPoints(output.pointA, output.pointB);

        output.distance = B2Math.b2Distance(output.pointA, output.pointB);
        output.iterations = iter;

        // Apply radii if requested
        if (input.useRadii) {
            if (output.distance < b2_epsilon) {
                // Shapes are too close to safely compute normal
                B2Vec2 p = B2Math.add(output.pointA, output.pointB).multiply(0.5f);
                output.pointA = p;
                output.pointB = p;
                output.distance = 0.0f;
            } else {
                // Keep closest points on perimeter even if overlapped, this way
                // the points move smoothly.
                float rA = proxyA.radius;
                float rB = proxyB.radius;
                B2Vec2 normal = B2Math.sub(output.pointB, output.pointA);
                normal = normal.Normalize();
                output.distance = Math.max(0.0f, output.distance - rA - rB);
				/*output.pointA += rA * normal;
				output.pointB -= rB * normal;*/
                output.pointA = add(output.pointA, normal.multiply(rA));
                output.pointB = sub(output.pointB, normal.multiply(rB));
            }
        }
    }


    // GJK-raycast
// Algorithm by Gino van den Bergen.
// "Smooth Mesh Contacts with GJK" in Game Physics Pearls. 2010
    public boolean b2ShapeCast(B2ShapeCastOutput output, B2ShapeCastInput input) {
        output.iterations = 0;
        output.lambda = 1.0f;
        output.normal.SetZero();
        output.point.SetZero();

        b2ShapeProxy proxyA = input.proxyA;
        b2ShapeProxy proxyB = input.proxyB;

        float radiusA = Math.max(proxyA.radius, b2_polygonRadius);
        float radiusB = Math.max(proxyB.radius, b2_polygonRadius);
        float radius = radiusA + radiusB;


        B2Vec2 r = input.translationB;
        B2Vec2 n = new B2Vec2(0.0f, 0.0f);
        float lambda = 0.0f;

        // Initial simplex
        B2Simplex simplex = new B2Simplex();
        simplex.count = 0;

        // Get simplex vertices as an array.

        // Get support point in -r direction
        int indexA = proxyA.GetSupport(r.multiply(-1));
        B2Vec2 wA = proxyA.GetVertex(indexA);
        int indexB = proxyB.GetSupport(r);
        B2Vec2 wB = proxyB.GetVertex(indexB);
        B2Vec2 v = sub(wA, wB);

        // Sigma is the target distance between polygons
        float sigma = Math.max(b2_polygonRadius, radius - b2_polygonRadius); // 距离半径值
        float tolerance = 0.5f * b2_linearSlop;

        // Main iteration loop.
        int k_maxIters = 20;
        int iter = 0;
        while (iter < k_maxIters && v.Length() - sigma > tolerance) {
            output.iterations += 1;

            // Support in direction -v (A - B)
            indexA = proxyA.GetSupport(v.multiply(-1));
            wA = proxyA.GetVertex(indexA);
            indexB = proxyB.GetSupport(v);
            wB = proxyB.GetVertex(indexB);
            B2Vec2 p = sub(wA, wB);

            // -v is a normal at p
            v = v.Normalize();

            // p(当前两个点的距离投影到离原点最近的线段上)
            // Intersect ray with plane
            float vp = b2Dot(v, p);
            // r(移动方向投影到离原点最近的线段上)
            float vr = b2Dot(v, r);
            if (vp - sigma > lambda * vr) {
                // 已经往移动方向相反的地方去了
                if (vr <= 0.0f) {
                    return false;
                }
                lambda = (vp - sigma) / vr;
                if (lambda > 1.0f) // 射线延长的太多了 超过了100%
                {
                    return false;
                }

                n = v.multiply(-1);
                simplex.count = 0;
            }

            // Reverse simplex since it works with B - A.
            // Shift by lambda * r because we want the closest point to the current clip point.
            // Note that the support point p is not shifted because we want the plane equation
            // to be formed in unshifted space.
            B2SimplexVertex vertex = simplex.get(simplex.count);
            vertex.indexA = indexB;
            //vertex.wA = wB + lambda * r;
            vertex.wA = add(wB, r.multiply(lambda));
            vertex.indexB = indexA;
            vertex.wB = wA;
            //vertex.w = vertex.wB - vertex.wA;
            vertex.w = sub(vertex.wB, vertex.wA);
            vertex.a = 1.0f;
            simplex.count += 1;

            switch (simplex.count) {
                case 1:
                    break;

                case 2:
                    simplex.Solve2();
                    break;

                case 3:
                    simplex.Solve3();
                    break;

                default:
                    return false;
            }

            // If we have 3 points, then the origin is in the corresponding triangle.
            if (simplex.count == 3) {
                // Overlap
                //return false;
                // todo 为了和box2d 3.2一样 直接返回true
                return true;
            }

            // Get search direction.
            v = simplex.GetClosestPoint();

            // Iteration count is equated to the number of support point calls.
            ++iter;
        }

		/*if (iter == 0)
		{
			// Initial overlap
			//return false;
			// todo 为了和box2d 3.2一样 直接返回true
			return true;
		}*/

        // Prepare output.
        B2Vec2 pointA = new B2Vec2(), pointB = new B2Vec2();
        simplex.GetWitnessPoints(pointB, pointA);

        if (v.LengthSquared() > 0.0f) {
            n = v.multiply(-1);
            n = n.Normalize();
        }

        //output.point = pointA + radiusA * n;
        output.point = add(pointA, n.multiply(radiusA));
        output.normal = n;
        output.lambda = lambda;
        output.iterations = iter;
        return true;
    }

    public boolean intersect(b2DistanceInput input) {
        return false;
    }
}
