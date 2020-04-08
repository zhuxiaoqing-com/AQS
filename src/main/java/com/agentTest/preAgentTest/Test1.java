package com.agentTest.preAgentTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 其实分析下就知道 只有方法块才会改变，像 字段啥的 应该一开始就加载到对象里面或者常量表里面 不会再变了。
 *
 * @Auther: Administrator
 * @Date: 2020/4/8 11:21
 * @Description:
 */
public class Test1 {
    public static Test1 test1 = new Test1();
    private int aa = 1;
    Map<Integer, String> map = new HashMap<>();

    {
        // agent 热更新不会修改 map 字段
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
    }

    // agent 热更新不会触发 静态方法
    static {
        System.out.println("Test1   static..................");
    }

    public static void staticFun1() {
        System.out.println("staticFun1");
        // System.out.println("staticFun1 mainAgent!!!!!");
    }

    public void normalFun1() {
        System.out.println("normalFun1");
        // System.out.println("normalFun1 mainAgent!!!!!");
    }

    public void singleFun1() {
        System.out.println("singleFun1");
        System.out.println(map);
        System.out.println(aa);
        // System.out.println("singleFun1 mainAgent!!!!!");
    }

    public void saveMapFun1() {
        System.out.println("saveMapFun1");
        // System.out.println("saveMapFun1 mainAgent!!!!!");
    }

    public void endlessWhileFun1() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("endlessWhileFun1");
            // System.out.println("endlessWhileFun1 mainAgent!!!!!");
        }
    }
}
