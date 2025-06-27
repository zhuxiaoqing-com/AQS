package com.example.demo4.qunyou.message;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/8/4 15:44
 * @Description:
 */
public abstract class AbsMessage {

	/**
	 * 打印16进制
	 * @param byteArray byteArray
	 */
	public static String printHex(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (byte b : byteArray) {
			sb.append(Integer.toHexString((b >> 4) & 0xF));
			sb.append(Integer.toHexString(b & 0xF));
			sb.append(" ");
		}
		return sb.toString();
	}
}
