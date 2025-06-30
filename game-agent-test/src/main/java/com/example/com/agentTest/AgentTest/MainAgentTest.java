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

    /**
     * lib 里面应用了 agent.jar  -javaagent也引用了agent.jar 会就优先加载-jvavagent里面的Jar;
     * -javaagent里面的jar也是用的 AppClassLoader;
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println(PreMainAgent.getInst());
        System.out.println("PreMainAgent 类加载器 "+ PreMainAgent.class.getClassLoader());
        System.out.println("MainAgentTest 类加载器 "+ MainAgentTest.class.getClassLoader());
        new Thread(() -> MainAgentTestServer.openServer()).start();
        Thread.sleep(4000);
        new Thread(() -> MainAgentTestClient.clientConnect()).start();
    }
}
