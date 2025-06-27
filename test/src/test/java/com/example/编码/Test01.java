package com.example.编码;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/5/8 17:50
 * @Description:
 */
public class Test01 {
	@Test
	public void test01() throws Exception {
		System.out.println("---------gbk 转 utf-8");
		a("GBK", "UTF-8");
		System.out.println();
		System.out.println();
		System.out.println("---------utf-8 转 gbk");
		a("UTF-8", "GBK");
	}

	private void a(String charsetName1, String charsetName2) throws Exception {
		String a = "我是你";
		System.out.println(charsetName1 + "......." + printHex(a.getBytes(charsetName1)));

		String s = new String(a.getBytes(charsetName1), charsetName2);
		System.out.println(charsetName2 + "......." + printHex(s.getBytes(charsetName2)));
		System.out.println(s);


		String s2 = new String(s.getBytes(charsetName2), charsetName1);
		System.out.println(charsetName1 + "......." + printHex(s2.getBytes(charsetName1)));
		System.out.println(s2);

	}

	public static String printHex(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (byte b : byteArray) {
			sb.append(Integer.toHexString((b >> 4) & 0xF));
			sb.append(Integer.toHexString(b & 0xF));
			sb.append(" ");
		}
		return sb.toString();
	}


	@Test
	public void test02() throws Exception {
		String aa = "我是你";
		System.out.println(Integer.toHexString(aa.codePointAt(0)));

		System.out.println("\u6211\u0300");
		System.out.println("\u0300\u6211");
		System.out.println("\u0300\u0033");
	}


	@Test
	public void test03() throws Exception {
		String aa = "我";
		byte[] gbks = aa.getBytes("GBK");
		System.out.println(printHex(gbks));

		 aa = "我";
		gbks = aa.getBytes(StandardCharsets.UTF_8);
		System.out.println(printHex(gbks));
	}
}
