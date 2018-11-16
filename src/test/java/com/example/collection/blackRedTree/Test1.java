package com.example.collection.blackRedTree;

import org.junit.Test;

public class Test1 {

    @Test
    public void test1() {
        RedBlackBST4<Integer, String> map = new RedBlackBST4<>();
        for (int i = 0; i <= 200; i++) {
            map.put(i, i + "");

        }

        System.out.println("size" + map.size());
        for (int i = 0; i <= 200; i++) {
            String s = map.get(i);
            //System.out.println(s);
        }
        map.deleteMax();
        for (int i = 0; i <= 200; i++) {
            map.delete(i);
        }
        System.out.println("size" + map.size());
    }

    @Test
    public void test2() {
        RedBlack<Integer, String> map = new RedBlack<>();
        for (int i = 0; i <= 200; i++) {
            map.put(i, i + "");

        }

        System.out.println("size" + map.size());
        for (int i = 0; i <= 200; i++) {
            String s = map.get(i);
            //System.out.println(s);
        }

        for (int i = 0; i <= 200; i++) {
            map.delete(i);
        }
        System.out.println("size" + map.size());
    }
}
