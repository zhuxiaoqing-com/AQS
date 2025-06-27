package com.example.demo4.qunyou;

import com.example.demo4.qunyou.message.*;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/8/13 10:03
 * @Description:
 */
public class Test01 {

	@Test
	public void test01() {
		// connect
		Connect abcd = new Connect(60, "abcd", null, null);
		System.out.println(AbsMessage.printHex(abcd.array()));
		Connect abcd1 = new Connect(60, "abcd", "abcd", "abcd");
		System.out.println(Connect.printHex(abcd1.array()));

		// connack
		Connack connect_sucessfully = new Connack(0, "Connect Successfully");
		System.out.println(AbsMessage.printHex(connect_sucessfully.array()));
		Connack connack = new Connack(1, "");
		System.out.println(AbsMessage.printHex(connack.array()));

		// DATATRANS
		Datatrans datatrans = new Datatrans("abcd".getBytes(StandardCharsets.UTF_8));
		System.out.println(AbsMessage.printHex(datatrans.array()));

		// PING 帧
		Ping ping = new Ping();
		System.out.println(AbsMessage.printHex(ping.array()));

		// PONG 帧
		Pong pong = new Pong();
		System.out.println(AbsMessage.printHex(pong.array()));

		// DISCONNECT  帧
		DisConnect disConnect = new DisConnect();
		System.out.println(AbsMessage.printHex(disConnect.array()));
	}

	@Test
	public void test02() {
		String a= "43 6f 6e 6e 65 63 74 20 53 75 63 63 65 73 73 66 75 6c 6c 79";
		String[] s = a.split(" ");
		System.out.println(Arrays.toString(s));
		byte[] a111 = new byte[s.length];
		for (int i = 0; i < s.length; i++) {
			a111[i] = Byte.parseByte(s[i], 16);
		}

		String s1 = new String(a111);
		System.out.println(s1);
	}
}
