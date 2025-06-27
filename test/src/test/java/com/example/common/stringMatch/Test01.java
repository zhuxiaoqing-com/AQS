package com.example.common.stringMatch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test01 {
    List initList = new ArrayList() {
        {
            add("马化腾");
            add("习近平");
        }
    };

    void init() {
    }

    @Test
    public void test01() {
        List list = new ArrayList();
        int count = 100_000;
        for (int x = 1; x <= count; x++) {
            StringBuilder builder = new StringBuilder();
            for (int y = 1; y <= 4; y++) {
                builder.append(getRandomChar());
            }
            list.add(builder.toString());
            builder.delete(0, 4);
        }

        for (int x = 1; x <= count; x++) {
            StringBuilder builder = new StringBuilder();
            for (int y = 1; y <= 4; y++) {
                builder.append(getRandomChar());
            }
            list.add(builder.toString());
            builder.delete(0, 4);
        }

        MatchIllegalCharacter.getInstance().init(list);
        int num = 0;
        long start = System.currentTimeMillis();
        for (int x = 0; x < list.size(); x++) {
            num++;
            boolean hah = new NODFA().getSensitiveWordLength(list.get(x).toString());
        }
        System.out.println(System.currentTimeMillis() - start);
        //MatchIllegalCharacter.getInstance().init();
    }

    public static char getRandomChar() {
        return (char) (0x4e00 + (int) (Math.random() * (0x9da5 - 0x4e00 + 1)));
    }

    @Test
    public void test02() {
        MatchIllegalCharacter.getInstance().init(initList);
        boolean hah = new NODFA().getSensitiveWordLength("哈哈马化腾d");
        System.out.println(hah);
    }
}
