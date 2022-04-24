package com.example.demo1;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.net.URL;

public class Doc {

    @Test
    public void test01() {
     /*   Son son = new Son();
        son.setA(1);
        String o = JSON.toJSONString(son);
        System.out.println(o);
        System.out.println(JSON.parseObject(o, Son.class).getAa());*/
		URL resource = Doc.class.getClassLoader().getResource("words.test");
		System.out.println(resource);
		System.out.println(Doc.class.getClassLoader().getResource("/"));
		System.out.println(Doc.class.getClassLoader().getResource(""));
    }
}
