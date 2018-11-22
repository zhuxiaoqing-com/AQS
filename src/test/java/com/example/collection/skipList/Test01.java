package com.example.collection.skipList;

import org.junit.Test;

public class Test01 {
    @Test
    public void test01() {
        SkipListMy2<String> map = new SkipListMy2<>();
        for (int i = 0; i <= 100; i++) {
            map.put2(i, i + "");
        }
        for (int i = 0; i <= 100; i++) {
            SkipListMy2<String>.SkipListNode<String> search = map.search(i);
            //System.out.println(search.value);
        }

        for (int i = 0; i <= 122; i++) {
            String remove = map.remove(i);
            System.out.println(remove+",......"+map.size());
        }
        System.out.println("测试完成");
    }
}
