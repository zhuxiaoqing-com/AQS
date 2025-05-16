package com.youxi.dataStructure.box2d3_2.collidePolygons;

import com.youxi.dataStructure.box2d3_2.collidePolygons.entity.b2Manifold;
import com.youxi.dataStructure.box2d3_2.collidePolygons.entity.b2Polygon;
import com.youxi.dataStructure.box2d3_2.entity.b2Vec2;

import static com.youxi.dataStructure.box2d3_2.entity.b2Math.b2Add;

public class B2CollidePolygons {
    public static final float B2_LINEAR_SLOP = 0.005f;
    public static final float B2_SPECULATIVE_DISTANCE = 4.0f * B2_LINEAR_SLOP;

// Due to speculation, every polygon is rounded
// Algorithm:
//
// compute edge separation using the separating axis test (SAT)
// if (separation > speculation_distance)
//   return
// find reference and incident edge
// if separation >= 0.1f * B2_LINEAR_SLOP
//   compute closest points between reference and incident edge
//   if vertices are closest
//      single vertex-vertex contact
//   else
//      clip edges
//   end
// else
//   clip edges
// end

    b2Manifold b2CollidePolygons(b2Polygon polygonA, b2Polygon polygonB) {
        b2Vec2 origin = polygonA.vertices[0];
        float linearSlop = B2_LINEAR_SLOP;
        float speculativeDistance = B2_SPECULATIVE_DISTANCE;

        // Shift polyA to origin
        // pw = q * pb + p
        // pw = q * (pbs + origin) + p
        // pw = q * pbs + (p + q * origin)
        b2Transform sfA = {b2Add(xfA.p, b2RotateVector(xfA.q, origin)), xfA.q};
        b2Transform xf = b2InvMulTransforms(sfA, xfB);

        b2Polygon localPolyA;
        localPolyA.count = polygonA -> count;
        localPolyA.radius = polygonA -> radius;
        localPolyA.vertices[0] = b2Vec2_zero;
        localPolyA.normals[0] = polygonA -> normals[0];
        for (int i = 1; i < localPolyA.count; ++i) {
            localPolyA.vertices[i] = b2Sub(polygonA -> vertices[i], origin);
            localPolyA.normals[i] = polygonA -> normals[i];
        }

        // Put polyB in polyA's frame to reduce round-off error
        b2Polygon localPolyB;
        localPolyB.count = polygonB -> count;
        localPolyB.radius = polygonB -> radius;
        for (int i = 0; i < localPolyB.count; ++i) {
            localPolyB.vertices[i] = b2TransformPoint(xf, polygonB -> vertices[i]);
            localPolyB.normals[i] = b2RotateVector(xf.q, polygonB -> normals[i]);
        }

        int edgeA = 0;
        float separationA = b2FindMaxSeparation( & edgeA, &localPolyA, &localPolyB );

        int edgeB = 0;
        float separationB = b2FindMaxSeparation( & edgeB, &localPolyB, &localPolyA );

        float radius = localPolyA.radius + localPolyB.radius;

        if (separationA > speculativeDistance + radius || separationB > speculativeDistance + radius) {
            return (b2Manifold) {0};
        }

        // Find incident edge
        bool flip;
        if (separationA >= separationB) {
            flip = false;

            b2Vec2 searchDirection = localPolyA.normals[edgeA];

            // Find the incident edge on polyB
            int count = localPolyB.count;
		const b2Vec2 * normals = localPolyB.normals;
            edgeB = 0;
            float minDot = FLT_MAX;
            for (int i = 0; i < count; ++i) {
                float dot = b2Dot(searchDirection, normals[i]);
                if (dot < minDot) {
                    minDot = dot;
                    edgeB = i;
                }
            }
        } else {
            flip = true;

            b2Vec2 searchDirection = localPolyB.normals[edgeB];

            // Find the incident edge on polyA
            int count = localPolyA.count;
		const b2Vec2 * normals = localPolyA.normals;
            edgeA = 0;
            float minDot = FLT_MAX;
            for (int i = 0; i < count; ++i) {
                float dot = b2Dot(searchDirection, normals[i]);
                if (dot < minDot) {
                    minDot = dot;
                    edgeA = i;
                }
            }
        }

        b2Manifold manifold = {0};

        // Using slop here to ensure vertex-vertex normal vectors can be safely normalized
        // todo this means edge clipping needs to handle slightly non-overlapping edges.
        if (separationA > 0.1f * linearSlop || separationB > 0.1f * linearSlop) {

            // Edges are disjoint. Find closest points between reference edge and incident edge
            // Reference edge on polygon A
            int i11 = edgeA;
            int i12 = edgeA + 1 < localPolyA.count ? edgeA + 1 : 0;
            int i21 = edgeB;
            int i22 = edgeB + 1 < localPolyB.count ? edgeB + 1 : 0;

            b2Vec2 v11 = localPolyA.vertices[i11];
            b2Vec2 v12 = localPolyA.vertices[i12];
            b2Vec2 v21 = localPolyB.vertices[i21];
            b2Vec2 v22 = localPolyB.vertices[i22];

            b2SegmentDistanceResult result = b2SegmentDistance(v11, v12, v21, v22);
            B2_ASSERT(result.distanceSquared > 0.0f);
            float distance = sqrtf(result.distanceSquared);
            float separation = distance - radius;

            if (distance - radius > speculativeDistance) {
                // This can happen in the vertex-vertex case
                return manifold;
            }

            // Attempt to clip edges
            manifold = b2ClipPolygons( & localPolyA, &localPolyB, edgeA, edgeB, flip );

            float minSeparation = FLT_MAX;
            for (int i = 0; i < manifold.pointCount; ++i) {
                minSeparation = b2MinFloat(minSeparation, manifold.points[i].separation);
            }

            // Does vertex-vertex have substantially larger separation?
            if (separation + 0.1f * linearSlop < minSeparation) {
                if (result.fraction1 == 0.0f && result.fraction2 == 0.0f) {
                    // v11 - v21
                    b2Vec2 normal = b2Sub(v21, v11);
                    float invDistance = 1.0f / distance;
                    normal.x *= invDistance;
                    normal.y *= invDistance;

                    b2Vec2 c1 = b2MulAdd(v11, localPolyA.radius, normal);
                    b2Vec2 c2 = b2MulAdd(v21, -localPolyB.radius, normal);

                    manifold.normal = normal;
                    manifold.points[0].anchorA = b2Lerp(c1, c2, 0.5f);
                    manifold.points[0].separation = distance - radius;
                    manifold.points[0].id = B2_MAKE_ID(i11, i21);
                    manifold.pointCount = 1;
                } else if (result.fraction1 == 0.0f && result.fraction2 == 1.0f) {
                    // v11 - v22
                    b2Vec2 normal = b2Sub(v22, v11);
                    float invDistance = 1.0f / distance;
                    normal.x *= invDistance;
                    normal.y *= invDistance;

                    b2Vec2 c1 = b2MulAdd(v11, localPolyA.radius, normal);
                    b2Vec2 c2 = b2MulAdd(v22, -localPolyB.radius, normal);

                    manifold.normal = normal;
                    manifold.points[0].anchorA = b2Lerp(c1, c2, 0.5f);
                    manifold.points[0].separation = distance - radius;
                    manifold.points[0].id = B2_MAKE_ID(i11, i22);
                    manifold.pointCount = 1;
                } else if (result.fraction1 == 1.0f && result.fraction2 == 0.0f) {
                    // v12 - v21
                    b2Vec2 normal = b2Sub(v21, v12);
                    float invDistance = 1.0f / distance;
                    normal.x *= invDistance;
                    normal.y *= invDistance;

                    b2Vec2 c1 = b2MulAdd(v12, localPolyA.radius, normal);
                    b2Vec2 c2 = b2MulAdd(v21, -localPolyB.radius, normal);

                    manifold.normal = normal;
                    manifold.points[0].anchorA = b2Lerp(c1, c2, 0.5f);
                    manifold.points[0].separation = distance - radius;
                    manifold.points[0].id = B2_MAKE_ID(i12, i21);
                    manifold.pointCount = 1;
                } else if (result.fraction1 == 1.0f && result.fraction2 == 1.0f) {
                    // v12 - v22
                    b2Vec2 normal = b2Sub(v22, v12);
                    float invDistance = 1.0f / distance;
                    normal.x *= invDistance;
                    normal.y *= invDistance;

                    b2Vec2 c1 = b2MulAdd(v12, localPolyA.radius, normal);
                    b2Vec2 c2 = b2MulAdd(v22, -localPolyB.radius, normal);

                    manifold.normal = normal;
                    manifold.points[0].anchorA = b2Lerp(c1, c2, 0.5f);
                    manifold.points[0].separation = distance - radius;
                    manifold.points[0].id = B2_MAKE_ID(i12, i22);
                    manifold.pointCount = 1;
                }
            }

        } else {
            // Polygons overlap
            manifold = b2ClipPolygons( & localPolyA, &localPolyB, edgeA, edgeB, flip );
        }

        // Convert manifold to world space
        if (manifold.pointCount > 0) {
            manifold.normal = b2RotateVector(xfA.q, manifold.normal);
            for (int i = 0; i < manifold.pointCount; ++i) {
                b2ManifoldPoint * mp = manifold.points + i;

                // anchor points relative to shape origin in world space
                mp -> anchorA = b2RotateVector(xfA.q, b2Add(mp -> anchorA, origin));
                mp -> anchorB = b2Add(mp -> anchorA, b2Sub(xfA.p, xfB.p));
                mp -> point = b2Add(xfA.p, mp -> anchorA);
            }
        }

        return manifold;
    }
}
