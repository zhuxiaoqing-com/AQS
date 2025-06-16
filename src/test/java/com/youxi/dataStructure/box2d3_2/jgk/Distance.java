package com.youxi.dataStructure.box2d3_2.jgk;

import com.youxi.dataStructure.box2d3_2.aabbTree.b2ShapeProxy;
import com.youxi.math.box2dGJK.B2DistanceProxy;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class Distance {

    //  b2Distance的输入
//  在计算中你必须选择使用形状的半径
    public static class b2DistanceInput {
        B2DistanceProxy proxyA;    //距离代理A
        B2DistanceProxy proxyB;    //距离代理B
        /*B2Transform transformA;    //转换A
        B2Transform transformB;    //转换B*/
        boolean useRadii;             //是否使用半径

        public b2DistanceInput(B2DistanceProxy proxyA, B2DistanceProxy proxyB, boolean useRadii) {
            this.proxyA = proxyA;
            this.proxyB = proxyB;
            this.useRadii = useRadii;
        }
    }

    ;

    // b2Distance的输出
    public static class b2DistanceOutput {
        B2Vec2 pointA = new B2Vec2(0,0);        //shapeA上最近的点
        B2Vec2 pointB = new B2Vec2(0,0);        //shapeB上最近的点
        float distance;   //距离
        int iterations;    //GJK的迭代次数
    }

    b2DistanceOutput b2ShapeDistance(b2DistanceInput input, b2Simplex simplexes, int simplexCapacity )
    {
        B2_ASSERT( input.proxyA.count > 0 && input.proxyB.count > 0 );
        B2_ASSERT( input.proxyA.radius >= 0.0f );
        B2_ASSERT( input.proxyB.radius >= 0.0f );

        b2DistanceOutput output = new b2DistanceOutput();

        B2DistanceProxy proxyA = input.proxyA;

        // Get proxyB in frame A to avoid further transforms in the main loop.
        // This is still a performance gain at 8 points.
        b2ShapeProxy localProxyB;
        {
            b2Transform transform = b2InvMulTransforms( input.transformA, input.transformB );
            localProxyB.count = input.proxyB.count;
            localProxyB.radius = input.proxyB.radius;
            for ( int i = 0; i < localProxyB.count; ++i )
            {
                localProxyB.points[i] = b2TransformPoint( transform, input.proxyB.points[i] );
            }
        }

        // Initialize the simplex.
        b2Simplex simplex = b2MakeSimplexFromCache( cache, proxyA, &localProxyB );

        int simplexIndex = 0;
        if ( simplexes != NULL && simplexIndex < simplexCapacity )
        {
            simplexes[simplexIndex] = simplex;
            simplexIndex += 1;
        }

        // Get simplex vertices as an array.
        b2SimplexVertex* vertices[] = { &simplex.v1, &simplex.v2, &simplex.v3 };

        b2Vec2 nonUnitNormal = b2Vec2_zero;

        // These store the vertices of the last simplex so that we can check for duplicates and prevent cycling.
        int saveA[3], saveB[3];

        // Main iteration loop. All computations are done in frame A.
	const int maxIterations = 20;
        int iteration = 0;
        while ( iteration < maxIterations )
        {
            // Copy simplex so we can identify duplicates.
            int saveCount = simplex.count;
            for ( int i = 0; i < saveCount; ++i )
            {
                saveA[i] = vertices[i].indexA;
                saveB[i] = vertices[i].indexB;
            }

            b2Vec2 d = { 0 };
            switch ( simplex.count )
            {
                case 1:
                    d = b2Neg( simplex.v1.w );
                    break;

                case 2:
                    d = b2SolveSimplex2( &simplex );
                    break;

                case 3:
                    d = b2SolveSimplex3( &simplex );
                    break;

                default:
                    B2_ASSERT( false );
            }

            // If we have 3 points, then the origin is in the corresponding triangle.
            if ( simplex.count == 3 )
            {
                // Overlap
                b2Vec2 localPointA, localPointB;
                b2ComputeSimplexWitnessPoints( &localPointA, &localPointB, &simplex );
                output.pointA = b2TransformPoint( input.transformA, localPointA );
                output.pointB = b2TransformPoint( input.transformA, localPointB );
                return output;
            }

#ifndef NDEBUG
            if ( simplexes != NULL && simplexIndex < simplexCapacity )
            {
                simplexes[simplexIndex] = simplex;
                simplexIndex += 1;
            }
#endif

            // Ensure the search direction is numerically fit.
            if ( b2Dot( d, d ) < FLT_EPSILON * FLT_EPSILON )
            {
                // This is unlikely but could lead to bad cycling.
                // The branch predictor seems to make this check have low cost.

                // The origin is probably contained by a line segment
                // or triangle. Thus the shapes are overlapped.

                // Must return overlap due to invalid normal.
                b2Vec2 localPointA, localPointB;
                b2ComputeSimplexWitnessPoints( &localPointA, &localPointB, &simplex );
                output.pointA = b2TransformPoint( input.transformA, localPointA );
                output.pointB = b2TransformPoint( input.transformA, localPointB );
                return output;
            }

            // Save the normal
            nonUnitNormal = d;

            // Compute a tentative new simplex vertex using support points.
            // support = support(a, d) - support(b, -d)
            b2SimplexVertex* vertex = vertices[simplex.count];
            vertex.indexA = b2FindSupport( proxyA, d );
            vertex.wA = proxyA.points[vertex.indexA];
            vertex.indexB = b2FindSupport( &localProxyB, b2Neg( d ) );
            vertex.wB = localProxyB.points[vertex.indexB];
            vertex.w = b2Sub( vertex.wA, vertex.wB );

            // Iteration count is equated to the number of support point calls.
            ++iteration;

            // Check for duplicate support points. This is the main termination criteria.
            bool duplicate = false;
            for ( int i = 0; i < saveCount; ++i )
            {
                if ( vertex.indexA == saveA[i] && vertex.indexB == saveB[i] )
                {
                    duplicate = true;
                    break;
                }
            }

            // If we found a duplicate support point we must exit to avoid cycling.
            if ( duplicate )
            {
                break;
            }

            // New vertex is valid and needed.
            simplex.count += 1;
        }

#ifndef NDEBUG
        if ( simplexes != NULL && simplexIndex < simplexCapacity )
        {
            simplexes[simplexIndex] = simplex;
            simplexIndex += 1;
        }
#endif

        // Prepare output
        b2Vec2 normal = b2Normalize( nonUnitNormal );
        B2_ASSERT( b2IsNormalized( normal ) );
        normal = b2RotateVector( input.transformA.q, normal );

        b2Vec2 localPointA, localPointB;
        b2ComputeSimplexWitnessPoints( &localPointA, &localPointB, &simplex );
        output.normal = normal;
        output.distance = b2Distance( localPointA, localPointB );
        output.pointA = b2TransformPoint( input.transformA, localPointA );
        output.pointB = b2TransformPoint( input.transformA, localPointB );
        output.iterations = iteration;
        output.simplexCount = simplexIndex;

        // Cache the simplex
        b2MakeSimplexCache( cache, &simplex );

        // Apply radii if requested
        if ( input.useRadii && output.distance > 0.1f * B2_LINEAR_SLOP )
        {
            float radiusA = input.proxyA.radius;
            float radiusB = input.proxyB.radius;
            output.distance = b2MaxFloat( 0.0f, output.distance - radiusA - radiusB );

            // Keep closest points on perimeter even if overlapped, this way the points move smoothly.
            output.pointA = b2MulAdd( output.pointA, radiusA, normal );
            output.pointB = b2MulSub( output.pointB, radiusB, normal );
        }

        return output;
    }

    private void B2_ASSERT(boolean b) {
        if(!b) {
            throw new RuntimeException( "B2_ASSERT failed");
        }
    }
}
