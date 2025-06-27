package com.youxi.dataStructure.box2d3_2.jgk.zeroGC;

import com.youxi.math.box2dGJK.shape.B2Vec2;

import static com.youxi.math.box2dGJK.B2Math.subZeroGC;

public class B2SimplexZeroGC {

    public B2SimplexVertexZeroGC v1 = new B2SimplexVertexZeroGC(); //m_v1, m_v2, m_v3;//单纯形的顶点变量
    public B2SimplexVertexZeroGC v2 = new B2SimplexVertexZeroGC();
    public B2SimplexVertexZeroGC v3 = new B2SimplexVertexZeroGC();
    public int count;//单纯形顶点个数


    public B2Vec2 nonUnitNormal = new B2Vec2();
    public B2Vec2 d = new B2Vec2();



    public B2SimplexZeroGC() {
    }

    public B2SimplexVertexZeroGC get(int index) {
        switch (index) {
            case 0:
                return v1;
            case 1:
                return v2;
            case 2:
                return v3;
        }
        return null;
    }

    //第一次调用将count置0
 /*   public class b2SimplexCache {
        float metric;        //长度或面积
        int count;       //顶点数
        int indexA[] = new int[3];    //shapeA上的顶点索引数组
        int indexB[] = new int[3];    //shapeB上的顶点索引数组
    }*/


    public void reset(b2ShapeProxyZeroGC proxyA,
                      b2ShapeProxyZeroGC proxyB) {
        B2SimplexVertexZeroGC v = v1;
        v.indexA = 0;
        v.indexB = 0;
        v.wA.setXY(proxyA.GetVertex(0));
        v.wB.setXY(proxyB.GetVertex(0));
        subZeroGC(v.wA, v.wB, v.w);
        v.a = 1.0f;
        count = 1;

        nonUnitNormal.setXY(0, 0);
        d.setXY(0, 0);

        cacheIndex = 0;
        for (B2Vec2 cacheB2Vec : cacheB2Vecs) {
            cacheB2Vec.setXY(0,0);
        }
    }


    private int cacheIndex = 0;
    private final B2Vec2[] cacheB2Vecs = new B2Vec2[6];
    {
        for (int i = 0; i < cacheB2Vecs.length; i++) {
            cacheB2Vecs[i] = new B2Vec2();
        }
    }
    public void resetCacheIndex() {
        cacheIndex = 0;
    }
    public B2Vec2 incrCacheVec() {
        return cacheB2Vecs[cacheIndex++];
    }


}
