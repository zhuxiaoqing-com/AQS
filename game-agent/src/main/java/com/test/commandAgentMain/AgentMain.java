package com.test.commandAgentMain;

import java.lang.instrument.Instrumentation;

/**
 * @author zhuxiaoqing
 * @Description: AgentMain
 * @Date 2025/10/30 15:35
 **/
public class AgentMain {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[Agent] Starting...");
        inst.addTransformer(new AbstractCommandTransformer(), true);
    }
}
