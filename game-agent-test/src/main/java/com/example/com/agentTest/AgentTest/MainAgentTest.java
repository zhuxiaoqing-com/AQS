package com.example.com.agentTest.AgentTest;


import com.test.preAgent.PreMainAgent;

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 16:49
 * @Description:
 */
public class MainAgentTest {
    // preAgent
    // vm options -javaagent:F:\gittttt\AQS\game-agent\target\game-agent-0.0.1-SNAPSHOT.jar
    public static void main(String[] args) throws InterruptedException {
        System.out.println(PreMainAgent.getInst());
        new Thread(() -> MainAgentTestServer.openServer()).start();
        Thread.sleep(4000);
        new Thread(() -> MainAgentTestClient.clientConnect()).start();
    }
}
