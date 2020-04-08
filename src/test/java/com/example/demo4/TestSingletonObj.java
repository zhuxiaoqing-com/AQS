package com.example.demo4;

/**
 * @Auther: Administrator
 * @Date: 2020/4/7 20:25
 * @Description:
 */
public class TestSingletonObj {
    public static TestSingletonObj instance  = new TestSingletonObj();
    public void fun1(){
        System.out.println("热更新前");
    }
}
