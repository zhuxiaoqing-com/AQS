package com.agentTest.AgentTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 14:04
 * @Description:
 */
public class MainAgentTestClient {
    public static void main(String[] args) {
        clientConnect();
    }
    public static void clientConnect() {
        try {
            // 要连接的服务端IP地址和端口
            String host = "127.0.0.1";
            int port = 12333;
            // 与服务端建立连接
            Socket socket = new Socket(host, port);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
