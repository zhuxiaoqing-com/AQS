package com.example.demo1;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

public class Doc {

    @Test
    public void test01() {
        Son son = new Son();
        son.setA(1);
        String o = JSON.toJSONString(son);
        System.out.println(o);
        System.out.println(JSON.parseObject(o, Son.class).getAa());
    }
}
