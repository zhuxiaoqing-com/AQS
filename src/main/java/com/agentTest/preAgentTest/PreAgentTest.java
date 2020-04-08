package com.agentTest.preAgentTest;

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 11:20
 * @Description:
 */
public class PreAgentTest {
    // preAgent
    // vm options -javaagent:F:\mh\game_server-develop\AQS\game-agent\target\game-agent-0.0.1-SNAPSHOT.jar
    public static void main(String[] args) {
        Test1 agentTest = new Test1();
        agentTest.normalFun1();
        Test1.staticFun1();
    }
}
