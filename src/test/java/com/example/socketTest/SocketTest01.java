package com.example.socketTest;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.HashSet;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/29 14:16
 * @Description:
 */
public class SocketTest01 {
	@Test
	public void test01() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(8888);
		int i = 0;
		HashSet<Integer> sets = new HashSet<>();
		while (i < 2000000) {
			Socket client = null;
			try {
				client = serverSocket.accept();
				//System.out.println("receive client connect, localPort=" + client.getPort());
				InputStream inputStream = client.getInputStream();
			/*	while (true) {
					int read = inputStream.read();
					if (read == -1) {
						break;
					}
					System.out.println(read);
				}*/
			/*	for (int a = 1; a <= 1000; a++) {
					Thread.sleep(500);
					outputStream.write((a + "").getBytes());
				}*/
				if(!sets.add(client.getPort())) {
					System.out.println("重复的port"+client.getPort());
				}
			} catch (Exception e) {
				System.out.println("client exception,e=" + e.getMessage());
			} finally {
				if (client != null) {
					client.close();
				}
				//Thread.sleep(1000);
				//serverSocket.close();
			}
			i++;
		}
	}
}