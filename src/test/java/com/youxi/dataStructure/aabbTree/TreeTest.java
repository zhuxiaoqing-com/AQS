package com.youxi.dataStructure.aabbTree;

import com.example.demo4.TestUtil;
import com.example.demo4.testObj1.AA;
import com.youxi.building.Misc;
import com.youxi.dataStructure.aabbTree.bean.B2AABB;
import com.youxi.dataStructure.aabbTree.bean.B2RayCastInput;
import com.youxi.dataStructure.aabbTree.bean.RayCastCallback;
import com.youxi.dataStructure.aabbTree.original.B2DynamicTree;
import com.youxi.dataStructure.aabbTree.zeroGC.B2DynamicTreeZeroGC;
import com.youxi.dataStructure.collisionTree.AABB;
import com.youxi.db.test.KV;
import com.youxi.math.box2dGJK.shape.B2Vec2;
import com.youxi.xstream.A;
import org.checkerframework.checker.units.qual.K;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TreeTest {

    public static class UserData {
        public int id = 0;
        public int proxyId = 0;
        public B2AABB aabb;
        public B2AABB moveAAbb;

        public UserData(int id, B2AABB aabb) {
            this.id = id;
            this.aabb = aabb;
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
    static int index = 2000;

    static {
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
    }


    /**
     * 测试两颗树是否一样
     */
    @Test
    public void testTreeSame() {
        int index = 20;

        int errorCount = 0;
        for (int i = 0; i < index; i++) {
            String s1 = treeTest(new B2DynamicTree());
            String s = treeTest(new B2DynamicTreeZeroGC());

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

    public String treeTest(IB2DynamicTree b2DynamicTree) {
        for (UserData value : map.values()) {
            value.moveAAbb = new B2AABB(value.aabb.upperBound, value.aabb.lowerBound);
        }
        TestUtil.testTime(() -> {
            for (UserData value : map.values()) {
                int proxy = b2DynamicTree.createProxy(value.aabb, value);
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
                b2DynamicTree.moveProxy(k.proxyId, k.moveAAbb, sub);
               /* String x = "move id %s oldAABB %s  newAABB %s  moveV %s proxyAABB %s";
                String format = String.format(x, k.id, oldAABB, k.moveAAbb, v, b2DynamicTree.getFatAABB(k.proxyId));
                System.out.println(format);*/
            }
            b2DynamicTree.Validate();
            //b2DynamicTree.rebuildBottomUp();
        }, 1, b2DynamicTree.getClass().getSimpleName() + " moveProxy");


        String s = b2DynamicTree.printTreeToString();
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
        B2DynamicTree b2DynamicTree = new B2DynamicTree();

        for (UserData value : map.values()) {
            int proxy = b2DynamicTree.createProxy(value.aabb, value);
            value.proxyId = proxy;
        }
        //b2DynamicTree.rebuildBottomUp();
        testRayAndAABB(b2DynamicTree);
    }

    public void testRayAndAABB(IB2DynamicTree tree) {
        TestUtil.testTime(() -> {
            B2RayCastInput b2RayCastInput = new B2RayCastInput();
            b2RayCastInput.maxFraction = 1;
            B2AABB b2AABB = randomCreateAABB();
            b2RayCastInput.p1 = b2AABB.lowerBound;
            b2RayCastInput.p2 = b2AABB.upperBound;
            tree.rayCast((a, b) -> 1f, b2RayCastInput);

            b2AABB = randomCreateAABB();
            tree.query(a -> ThreadLocalRandom.current().nextInt(100) == 1, b2AABB);

        }, 1000, "rayAndQuery");


    }


}
