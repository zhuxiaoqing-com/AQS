package com.example.demo4.qunyou.message;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/8/4 15:44
 * @Description:
 */
public abstract class AbsMessage {

	public void addStr(List<Byte> messageList, String a) {
		byte[] bytes1 = a.getBytes(StandardCharsets.US_ASCII);
		messageList.add((byte) (bytes1.length >> 8));
		messageList.add((byte) (bytes1.length));
		for (byte b : bytes1) {
			messageList.add(b);
		}
	}

	public byte[] toByteArray(List<Byte> messageList) {
		byte[] btyes = new byte[messageList.size()];
		for (int i = 0; i < messageList.size(); i++) {
			btyes[i] = messageList.get(i);
		}
		return btyes;
	}

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
