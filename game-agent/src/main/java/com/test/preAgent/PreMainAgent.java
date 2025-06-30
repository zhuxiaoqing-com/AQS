package com.test.preAgent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 10:05
 * @Description:
 */
public class PreMainAgent {
    public static  Instrumentation inst;
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("AopAgentTest.premain() was called.");
        PreMainAgent.inst = inst;
        System.out.println("PreMainAgent 类加载器 "+ PreMainAgent.class.getClassLoader());
    }

    /**
     * 这里直接在主程序调用, 晚上回来试试看看行不行;
     * @return
     */
    public static Instrumentation getInst() {
        return inst;
    }
}
