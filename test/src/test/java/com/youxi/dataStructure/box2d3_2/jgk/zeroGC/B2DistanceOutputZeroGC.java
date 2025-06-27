package com.youxi.dataStructure.box2d3_2.jgk.zeroGC;

import com.youxi.math.box2dGJK.B2SimplexVertex;
import com.youxi.math.box2dGJK.shape.B2Vec2;

// b2Distance的输出
public class B2DistanceOutputZeroGC {
    public B2Vec2 pointA = new B2Vec2(0, 0);        //shapeA上最近的点 如果碰撞了 pointA和pointB相等
    public B2Vec2 pointB = new B2Vec2(0, 0);        //shapeB上最近的点
    public B2Vec2 normal = new B2Vec2(0, 0);
    /// < Normal vector that points from A to B. Invalid if distance is zero.
    public float distance;
    /// < The final distance, zero if overlapped
    public int iterations;
    /// < Number of GJK iterations used
    //int simplexCount; ///< The number of simplexes stored in the simplex array

    B2SimplexZeroGC b2SimplexZeroGC = new B2SimplexZeroGC();
    B2SimplexVertexZeroGC[] vertices;

    int saveA[] = new int[3];
    int saveB[] = new int[3];

    {
        vertices = new B2SimplexVertexZeroGC[]{b2SimplexZeroGC.v1, b2SimplexZeroGC.v2, b2SimplexZeroGC.v3};
    }

    public void reset() {
        pointA.setXY(0, 0);
        pointB.setXY(0, 0);
        normal.setXY(0, 0);
        distance = 0;
        iterations = 0;
    }


    public B2DistanceOutputZeroGC getResult() {
        B2DistanceOutputZeroGC b2DistanceOutput = new B2DistanceOutputZeroGC();

        b2DistanceOutput.pointA.setXY(pointA.getX(), pointA.getY());
        b2DistanceOutput.pointB.setXY(pointB.getX(), pointB.getY());
        b2DistanceOutput.normal.setXY(normal.getX(), normal.getY());

        b2DistanceOutput.distance = distance;
        b2DistanceOutput.iterations = iterations;
        return b2DistanceOutput;
    }

}
