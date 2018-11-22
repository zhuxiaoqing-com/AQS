package com.example.datastruture.skipList;

import org.junit.Test;

public class Test01 {
    @Test
    public void test01() {
        SkipList2OneLink map = new SkipList2OneLink();
        for (int i = 0; i <= 100; i++) {
            map.insert02(i);
        }
        for (int i = 0; i <= 100; i++) {
            SkipList2OneLink.SkipListNode skipListNode = map.find(i);
           // System.out.println(skipListNode.getValue());
        }

        for (int i = 0; i <= 122; i++) {
            int remove = map.remove(i);
            System.out.println(remove+",......"+map.size());
        }
        System.out.println("测试完33成");
    }
}
