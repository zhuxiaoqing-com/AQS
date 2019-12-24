package com.example.java8;

import org.junit.Test;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class Compare {
    @Test
    public void test01(){
        Collator instance = Collator.getInstance(Locale.CHINA);
        Collator instance1 = Collator.getInstance(Locale.CHINESE);
        ArrayList<Object> list = new ArrayList<>();
        list.add("我");
        list.add("爱");
        list.add("你");
        list.sort(instance);
        System.out.println(list);
    }
}
