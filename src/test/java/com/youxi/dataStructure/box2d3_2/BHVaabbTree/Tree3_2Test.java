package com.youxi.dataStructure.box2d3_2.BHVaabbTree;

import com.example.demo4.TestUtil;
import com.youxi.building.Misc;
import com.youxi.dataStructure.box2d3_2.collidePolygons.OutValue;
import com.youxi.dataStructure.box2d3_2.collidePolygons.Ray2OBB;
import com.youxi.dataStructure.box2d3_2.jgk.B2CastOutput;
import com.youxi.dataStructure.box2d3_2.jgk.B2ShapeCastPairInput;
import com.youxi.dataStructure.box2d3_2.jgk.Distance;
import com.youxi.db.test.KV;
import com.youxi.db.test.ThreeKV;
import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.b2ShapeProxy;
import com.youxi.math.box2dGJK.shape.B2AABB;
import com.youxi.math.box2dGJK.shape.B2OBB;
import com.youxi.math.box2dGJK.shape.B2Vec2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Tree3_2Test {

    public static class UserData {
        public int id = 0;
        public int proxyId = 0;
        public B2AABB aabb;
        public B2AABB moveAAbb;

        public UserData(int id, B2AABB aabb) {
            this.id = id;
            this.aabb = aabb;
        }

        @Override
        public String toString() {
            return
                    "{" + id +
                            '}';
        }
    }

    private static B2AABB randomCreateAABB() {
        int minX = ThreadLocalRandom.current().nextInt(1000000) - 500000;
        int minY = ThreadLocalRandom.current().nextInt(1000000) - 500000;

        int maxX = ThreadLocalRandom.current().nextInt(1000000) - 500000;
        if (maxX == minX) {
            maxX += 1000;
        }
        if (maxX < minX) {
            int tempX = maxX;
            maxX = minX;
            minX = tempX;
        }
        int maxY = ThreadLocalRandom.current().nextInt(1000000) - 500000;
        if (maxY == minY) {
            maxY += 1000;
        }
        if (maxY < minY) {
            int tempY = maxY;
            maxY = minY;
            minY = tempY;
        }

        return new B2AABB(new B2Vec2(minX / 1000f, minY / 1000f), new B2Vec2(maxX / 1000f, maxY / 1000f));
    }

    static Map<Integer, UserData> map = new HashMap<>();
    static List<KV<UserData, B2Vec2>> moveList = new ArrayList<>();
    static List<ThreeKV<b2RayCastInput, B2AABB, b2ShapeCastInput>> rayCastList = new ArrayList<>();

    static int index = 20000;

    public void randomData() {
        for (int i = 0; i < index; i++) {
            map.put(i + 1, new UserData(i + 1, randomCreateAABB()));

        }
        // 随机移动

        for (int i = 0; i < index; i++) {
            int x = ThreadLocalRandom.current().nextInt(4000) - 2000;
            int y = ThreadLocalRandom.current().nextInt(4000) - 2000;
            B2Vec2 b2Vec2 = new B2Vec2(x / 1000f, y / 1000f);
            UserData random = Misc.getRandom(map.values());
            moveList.add(new KV<>(random, b2Vec2));
        }

        for (int i = 0; i < index; i++) {
            b2RayCastInput b2RayCastInput = new b2RayCastInput();
            b2RayCastInput.maxFraction = 1;
            B2AABB b2AABB = randomCreateAABB();
            b2RayCastInput.origin = b2AABB.lowerBound;
            b2RayCastInput.translation = b2AABB.upperBound.sub(b2AABB.lowerBound);

            B2AABB b2AABB1 = randomCreateAABB();

            // boxcast
            b2ShapeCastInput b2ShapeCastInput = new b2ShapeCastInput();
            B2AABB b2AABB3 = randomCreateAABB();
            B2OBB b2OBB = new B2OBB(b2AABB3.center, b2AABB3.halfB2Vec2, ThreadLocalRandom.current().nextInt(360));
            b2ShapeCastInput.proxy = new b2ShapeProxy(b2OBB);
            B2AABB b2AABB4 = randomCreateAABB();
            b2ShapeCastInput.translation = b2AABB4.upperBound.sub(b2AABB4.lowerBound);

            rayCastList.add(new ThreeKV<>(b2RayCastInput, b2AABB1, b2ShapeCastInput));
        }
    }


    public void customData() {
        // 1x 都打不中
        int index = 10;
        map.put(index++, new UserData(index, new B2AABB(new B2Vec2(9, 1), new B2Vec2(10, 2))));
        map.put(index++, new UserData(index, new B2AABB(new B2Vec2(1, 9), new B2Vec2(2, 10))));
        map.put(index++, new UserData(index, new B2AABB(new B2Vec2(1, -1), new B2Vec2(2f, 0))));
        // 2x 都打能中 不要
        index = 20;
        // 3x 射线能打中
        index = 30;
        map.put(index++, new UserData(index, new B2AABB(new B2Vec2(1, 0.5f), new B2Vec2(2, 2.5f))));
        map.put(index++, new UserData(index, new B2AABB(new B2Vec2(2, 2.5f), new B2Vec2(3.5f, 3))));
        map.put(index++, new UserData(index, new B2AABB(new B2Vec2(8, 8), new B2Vec2(10, 10))));
        // 4x 矩形能打中
        index = 40;
        map.put(index++, new UserData(index, new B2AABB(new B2Vec2(6, 3), new B2Vec2(8, 4))));
        map.put(index++, new UserData(index, new B2AABB(new B2Vec2(5, 2), new B2Vec2(9, 5))));

        // 打射线
        b2RayCastInput b2RayCastInput = new b2RayCastInput();
        b2RayCastInput.maxFraction = 1;
        b2RayCastInput.origin = new B2Vec2(-1, -1);
        b2RayCastInput.translation = new B2Vec2(10, 10);

        B2AABB b2AABB1 = new B2AABB(new B2Vec2(5, 2), new B2Vec2(8.6f, 5));

        // boxcast
        b2ShapeCastInput b2ShapeCastInput = new b2ShapeCastInput();
        B2OBB b2OBB = new B2OBB(b2RayCastInput.origin, new B2Vec2(1, 1), 30);
        b2ShapeCastInput.proxy = new b2ShapeProxy(b2OBB);
        b2ShapeCastInput.translation = b2RayCastInput.translation;


        rayCastList.add(new ThreeKV<>(b2RayCastInput, b2AABB1, b2ShapeCastInput));
    }


    /**
     * 测试两颗树是否一样
     */
    @Test
    public void testTreeSame() {
        int index = 20;

        randomData();
        int errorCount = 0;
        for (int i = 0; i < index; i++) {
            String s1 = treeTest(b2DynamicTree.b2DynamicTree_Create(), false);
            String s = treeTest(b2DynamicTreeZeroGC.b2DynamicTree_Create(), false);

            boolean equals = s.equals(s1);
            System.out.println(" 对比是否一样  ：" + equals);
            if (!equals) {
                System.out.println(s);
                System.out.println(s1);
                if (true) return;
                errorCount++;
                int a = 0;
            }

            System.out.println();
            System.out.println();
            System.out.println();
        }

        System.out.println("不一样次数！！！  " + errorCount);
    }


    @Test
    public void test01() {
        //randomData();
        customData();
        treeTest(b2DynamicTree.b2DynamicTree_Create(), true);
        System.out.println("-------------------");
        treeTest(b2DynamicTreeZeroGC.b2DynamicTree_Create(), true);
    }

    public String treeTest(IB3_2DynamicTree b2DynamicTree, boolean rebuild) {
        for (UserData value : map.values()) {
            value.moveAAbb = new B2AABB(value.aabb.lowerBound, value.aabb.upperBound);
        }
        TestUtil.testTime(() -> {
            for (UserData value : map.values()) {
                int proxy = b2DynamicTree.b2DynamicTree_CreateProxy(value.aabb, 1, value);
                value.proxyId = proxy;
            }
            //b2DynamicTree.rebuildBottomUp();
        }, 1, b2DynamicTree.getClass().getSimpleName() + " createProxy");

        TestUtil.testTime(() -> {
            for (KV<UserData, B2Vec2> integerB2Vec2KV : moveList) {
                UserData k = integerB2Vec2KV.getK();
                B2Vec2 v = integerB2Vec2KV.getV();

                B2AABB oldAABB = new B2AABB(k.moveAAbb.upperBound, k.moveAAbb.lowerBound);

                k.moveAAbb.lowerBound = k.moveAAbb.lowerBound.add(v);
                k.moveAAbb.upperBound = k.moveAAbb.upperBound.add(v);

                B2Vec2 sub = k.moveAAbb.GetCenter().sub(oldAABB.GetCenter());
                if (rebuild) {
                    b2DynamicTree.b2DynamicTree_EnlargeProxy(k.proxyId, k.moveAAbb);
                } else {
                    b2DynamicTree.b2DynamicTree_MoveProxy(k.proxyId, k.moveAAbb);
                }
               /* String x = "move id %s oldAABB %s  newAABB %s  moveV %s proxyAABB %s";
                String format = String.format(x, k.id, oldAABB, k.moveAAbb, v, b2DynamicTree.getFatAABB(k.proxyId));
                System.out.println(format);*/
            }
            if (rebuild) {
                b2DynamicTree.b2DynamicTree_Rebuild(false);
            }
            b2DynamicTree.b2DynamicTree_Validate();
            //b2DynamicTree.rebuildBottomUp();
        }, 1, b2DynamicTree.getClass().getSimpleName() + " moveProxy rebuild ：" + rebuild);

        String s = b2DynamicTree.printTreeToString();
        System.out.println(String.format("rebuild %s heigh %s minH %s", rebuild, b2DynamicTree.b2DynamicTree_GetHeight(), b2DynamicTree.calculateMinHeight()));
        //System.out.println(s);


        B2TreeRayCastCallbackFcn rayCastCallback = new B2TreeRayCastCallbackFcn() {
            @Override
            public float callback(b2RayCastInput input, int proxyId, Object userData, Object context) {
                UserData u = (UserData) userData;
                OutValue outValue = new OutValue();
                boolean b = Ray2OBB.TestRayOBB(input.origin, input.translation, u.aabb.center, u.aabb.halfB2Vec2, 0, outValue);
                if (b) {
                    System.out.println(String.format("RayCastCallback id %s aabb %s  hit %s", u.id, u.moveAAbb, b));
                }
                return 1;
            }
        };

        B2TreeQueryCallbackFcn queryCallback = new B2TreeQueryCallbackFcn() {

            @Override
            public boolean callback(B2AABB aabb, int proxyId, Object userData, Object context) {
                UserData u = (UserData) userData;
                boolean b = B2Math.B2AABB_Overlaps(aabb, u.aabb);
                if (b) {
                    System.out.println(String.format("QueryCallback id %s aabb %s  hit %s", u.id, u.moveAAbb, b));
                }
                return true;
            }
        };

        B2TreeShapeCastCallbackFcn boxCastback = new B2TreeShapeCastCallbackFcn() {

            @Override
            public float callback(b2ShapeCastInput input, int proxyId, Object userData, Object context) {
                UserData u = (UserData) userData;
                OutValue outValue = new OutValue();
                B2ShapeCastPairInput b2ShapeCastPairInput = new B2ShapeCastPairInput();
                b2ShapeCastPairInput.proxyA = input.proxy;
                b2ShapeCastPairInput.proxyB = new b2ShapeProxy(new B2OBB(u.aabb.GetCenter(), u.aabb.halfB2Vec2, 0));
                b2ShapeCastPairInput.translationB = input.translation;
                B2CastOutput b2CastOutput = Distance.b2ShapeCast(b2ShapeCastPairInput);
                if (b2CastOutput.hit) {
                    System.out.println(String.format("BoxCastCallback id %s aabb %s  hit %s", u.id, u.moveAAbb, b2CastOutput.hit));
                }
                return 1;
            }
        };


        TestUtil.testTime(() -> {
            for (ThreeKV<b2RayCastInput, B2AABB, b2ShapeCastInput> kv : rayCastList) {
                b2DynamicTree.b2DynamicTree_RayCast(kv.getK(), Long.MAX_VALUE, rayCastCallback, new Object());
                b2DynamicTree.b2DynamicTree_Query(kv.getV(), Long.MAX_VALUE, queryCallback, new Object());
                b2DynamicTree.b2DynamicTree_ShapeCast(kv.getP(), Long.MAX_VALUE, boxCastback, new Object());
            }

        }, 1, b2DynamicTree.getClass().getSimpleName() + " rayAndQuery");


        // b2DynamicTree.printTree();
   /*     TestUtil.testTime(() -> {
            for (UserData value : map.values()) {
                b2DynamicTree.destroyProxy(value.proxyId);
            }
        }, 1, b2DynamicTree.getClass().getSimpleName() + " destroyProxy");
*/
        return s;
    }


    @Test
    public void test02() {
        /*B2AABB b2AABB = new B2AABB(new B2Vec2(6, 3), new B2Vec2(8, 4));
        B2AABB b2AABB1 = new B2AABB(new B2Vec2(5, 2), new B2Vec2(9, 5));
        boolean b = B2Collision.b2TestOverlap(b2AABB, b2AABB1);
        boolean b1 = B2Collision.b2TestOverlap(b2AABB1, b2AABB);
        //IB2DynamicTree b2DynamicTree = new B2DynamicTreeZeroGC();
        IB2DynamicTree b2DynamicTree = new B2DynamicTree();
        customData();
        treeTest(b2DynamicTree);*/
    }


}
