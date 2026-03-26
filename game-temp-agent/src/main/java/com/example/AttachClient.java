package com.example;


import com.sun.tools.attach.VirtualMachine;

/**
 * @author zhuxiaoqing
 * @Description: AttachClient
 * @Date 2026/3/26 20:41
 **/
public class AttachClient {
    public static void main(String[] args) throws Exception {
        String pid = args[0];
        String agentPath = args[1];
        String classFile = args[2];

        VirtualMachine vm = VirtualMachine.attach(pid);

        // 把 class 文件路径传给 agent
        vm.loadAgent(agentPath, classFile);

        vm.detach();

        System.out.println("Attach done");
    }
}
