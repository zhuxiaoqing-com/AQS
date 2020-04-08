package com.test;

import com.test.mainAgent.MainAgent;

import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 11:19
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("启动");
        Map<String, String> fullClassNameFilePathMap = MainAgent.getFullClassNameFilePathMap();
        System.out.println(fullClassNameFilePathMap);
    }
}
