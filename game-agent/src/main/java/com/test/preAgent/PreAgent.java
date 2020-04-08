package com.test.preAgent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 10:05
 * @Description:
 */
public class PreAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("AopAgentTest.premain() was called.");

        /* ClassFileTransformer : An agent provides an implementation of this interface in order to transform class files.*/
        ClassFileTransformer trans = new AopAgentTransformer();

        System.out.println("Adding a AopAgentTest instance to the JVM.");

        /*Registers the supplied transformer.*/
        inst.addTransformer(trans);

    }
}
