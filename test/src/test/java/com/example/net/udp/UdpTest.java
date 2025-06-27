package com.example.net.udp;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/6/22 15:42
 * @Description:
 */
public class UdpTest {

	/**
	 * 服务器
	 */
	@Test
	public void server() throws Exception {
		DatagramSocket socket = new DatagramSocket(8800);
		byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
		DatagramPacket packet = new DatagramPacket(data, data.length);
		//3.接收客户端发送的数据
		System.out.println("****服务器端已经启动，等待客户端发送数据");
		int num = 0;
		while (num++ <= 100) {
			socket.receive(packet);//此方法在接收到数据报之前会一直阻塞
			//4.读取数据
			String info = new String(data, 0, packet.getLength());
			System.out.println("客户端ip 端口" + packet.getAddress() + "..." + packet.getPort());
			System.out.println("我是服务器，客户端说：" + info);

			/*
			 * 向客户端响应数据
			 */
			//1.定义客户端的地址、端口号、数据
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			byte[] data2 = "欢迎您!".getBytes();
			//2.创建数据报，包含响应的数据信息
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
			//3.响应客户端
			socket.send(packet2);
		}
		//4.关闭资源
		socket.close();
	}

	/**
	 * 客户端01
	 */
	@Test
	public void client01() throws Exception {
		client();
		client();
		client();
	}

	private void client() throws IOException {
		/*
		 * 向服务器端发送数据
		 */
		//1.定义服务器的地址、端口号、数据
		InetAddress address=InetAddress.getByName("localhost");
		int port = 8800;
		byte[] data="用户名：jinbin;密码：1997".getBytes();
		//2.创建数据报，包含发送的数据信息
		DatagramPacket packet=new DatagramPacket(data, data.length, address, port);
		//3.创建DatagramSocket对象
		DatagramSocket socket=new DatagramSocket();
		//4.向服务器端发送数据报
		socket.send(packet);

		/*
		 * 接收服务器端响应的数据
		 */
		//1.创建数据报，用于接收服务器端响应的数据
		byte[] data2=new byte[1024];
		DatagramPacket packet2=new DatagramPacket(data2, data2.length);
		//2.接收服务器响应的数据
		socket.receive(packet2);
		//3.读取数据
		String reply=new String(data2, 0, packet2.getLength());
		System.out.println("我是客户端，服务器说："+reply);
		//4.关闭资源
		socket.close();
	}


}
