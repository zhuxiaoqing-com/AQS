package com.agentTest.AgentTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

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
            int port = 9528;
            // 与服务端建立连接
            Socket socket = new Socket(host, port);
            int index = 100;
            for (int i = 0; i < index; i++) {
                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(next.getBytes());
                outputStream.flush();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
