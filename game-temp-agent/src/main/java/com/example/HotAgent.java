package com.example;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhuxiaoqing
 * @Description: HotAgent
 * @Date 2026/3/26 20:41
 **/
public class HotAgent {
    public static void agentmain(String args, Instrumentation inst) throws Exception {
        System.out.println("Agent attached!");

        // 读取新的 class 文件
        byte[] bytes = Files.readAllBytes(Paths.get(args));

        // 找到要替换的类
        Class<?> targetClass = Class.forName("TargetApp");

        // 替换
        inst.redefineClasses(new ClassDefinition(targetClass, bytes));

        System.out.println("Hot update done!");
    }
}
