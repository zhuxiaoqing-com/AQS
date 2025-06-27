package com.example.com.agentTest.AgentTest;

import com.example.com.agentTest.preAgentTest.Test1;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.test.preAgent.PreMainAgent;

import java.io.*;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 13:46
 * @Description:
 */
public class MainAgentTestServer {
    private static final String agentPath = "G:\\mh\\game_server-develop\\AQS\\game-agent\\target\\game-agent-0.0.1-SNAPSHOT.jar";

    public static void openServer() {
        int port = 12333;
        try {
            ServerSocket socket = new ServerSocket(port);
            runLogicMethod();
            while (true) {
                Socket accept = socket.accept();
                reloadAgent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runLogicMethod() {
        new Thread(() -> {
            Test1 test1 = Test1.test1;
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Test1.staticFun1();
                Test1.test1.singleFun1();
                new Test1().normalFun1();
                test1.saveMapFun1();
            }
        }).start();
        new Thread(() -> {
            new Test1().endlessWhileFun1();
        }).start();
    }

    private static void reloadAgent() throws IOException, UnmodifiableClassException, ClassNotFoundException {
        String path = "F:\\gittttt\\AQS\\game-agent-test\\target\\classes\\com\\example\\com\\agentTest\\preAgentTest\\Test1.class";
        String className = "com.example.com.agentTest.preAgentTest.Test1";
        Instrumentation inst = PreMainAgent.getInst();

        System.out.println("reload start agent class: " + className);
        for (Class allLoadedClass : inst.getAllLoadedClasses()) {
            //System.out.println(allLoadedClass.getName());
            if (allLoadedClass.getName().equals(className)) {
                byte[] bytes = fileToBytes(new File(path));
                inst.redefineClasses(new ClassDefinition(allLoadedClass, bytes));
            }
        }
        System.out.println("reload end agent class: " + className);
    }
  private static void reloadAgent1() throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        VirtualMachine attach = VirtualMachine.attach(getPID());
        attach.loadAgent(agentPath);
        attach.detach();
    }

    private static String getPID() {
        String pidAtHost = ManagementFactory.getRuntimeMXBean().getName();
        String pid = pidAtHost.split("@", -1)[0];
        return pid;
    }

    private static byte[] fileToBytes(File file) throws IOException {

        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();
        return bytes;
    }
}
