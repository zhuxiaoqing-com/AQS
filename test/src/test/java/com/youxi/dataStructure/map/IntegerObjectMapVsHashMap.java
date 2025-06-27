package com.youxi.dataStructure.map;

import com.example.demo4.TestUtil;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.youxi.building.Misc;
import io.netty.util.collection.IntObjectHashMap;
import org.junit.Test;

import java.util.*;

public class IntegerObjectMapVsHashMap {
       static Map<Integer, Object> longMap = new IntObjectHashMap<>();
        static Map<Integer, Object> hashMap = new HashMap<>();
    @Test
    public void  test01() {
        int index1 = 100;
        int index2 = 1000;
        int index3 = 10000;

        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(index1);
        objects.add(index2);
        objects.add(index3);
        objects.add(100000);

        for (Integer object : objects) {
            longMap.clear();
            hashMap.clear();

            putTest(object, longMap);
            putTest(object, hashMap);

            System.out.println();
            System.out.println();
            System.out.println();

            getTest(object, longMap);
            getTest(object, hashMap);

            System.out.println();
            System.out.println();
            System.out.println();

            removeTest(object, longMap);
            removeTest(object, hashMap);

            System.out.println();
            System.out.println();
            System.out.println();

            randomPutRemoveTest(object, 100_000,3,longMap);
            randomPutRemoveTest(object, 100_000,3,hashMap);
        }

    }

    private void putTest(int index1, Map<Integer, Object> map) {
        TestUtil.testTime(() -> {
            for (int i = 0; i < index1; i++) {
                map.put(i, i);
            }
        }, 1, map.getClass().getName() + " putTest  index ：" + index1);
    }

    private void removeTest(int index1, Map<Integer, Object> map) {
        TestUtil.testTime(() -> {
            for (int i = 0; i < index1; i++) {
                map.remove(i, i);
            }
        }, 1, map.getClass().getName() + " removeTest  index ：" + index1);
    }

    private void getTest(int index1, Map<Integer, Object> map) {
        TestUtil.testTime(() -> {
            for (int i = 0; i < index1; i++) {
                map.get(i);
            }
        }, 1, map.getClass().getName() + " getTest  index ：" + index1);
    }


    private void randomPutRemoveTest(int index1,int count, int modulus, Map<Integer, Object> map) {
        List<Integer> objects = new ArrayList<>(map.keySet());
        TestUtil.testTime(() -> {
            for (int i = 0; i < count; i++) {
                if (i % modulus == 0) {
                    Object random = Misc.getRandom(objects);
                    if (random != null) {
                        objects.remove(random);
                        map.remove(random);
                    }
                } else {
                    map.put(i, i);
                    objects.add(i);
                }
            }
        }, 1, map.getClass().getName() + " randomPutRemoveTest  index ：" + index1);
    }

}
