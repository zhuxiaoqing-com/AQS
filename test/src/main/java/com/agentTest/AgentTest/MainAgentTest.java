package com.agentTest.AgentTest;

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 16:49
 * @Description:
 */
public class MainAgentTest {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> MainAgentTestServer.openServer()).start();
        Thread.sleep(4000);
        new Thread(() -> MainAgentTestClient.clientConnect()).start();
    }
}
