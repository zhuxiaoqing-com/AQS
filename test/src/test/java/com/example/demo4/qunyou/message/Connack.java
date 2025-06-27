package com.example.demo4.qunyou.message;

import com.example.demo4.qunyou.MessageConstant;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/8/4 14:32
 * @Description:
 */
public class Connack  extends AbsMessage {


	private static int frameType = MessageConstant.Type.CONNACK;
	private int flags;
	private String messageStr;

	public Connack(int flags, String messageStr) {
		this.flags = flags;
		this.messageStr = messageStr;
	}

	public byte[] array() {
		ArrayList<Byte> messageList = new ArrayList<>();
		messageList.add((byte) ((frameType << 4) | flags));
		byte[] bytes1 = messageStr.getBytes(StandardCharsets.US_ASCII);
		((List<Byte>) messageList).add((byte) (bytes1.length >> 8));
		((List<Byte>) messageList).add((byte) (bytes1.length));
		for (byte b : bytes1) {
			((List<Byte>) messageList).add(b);
		}
		byte[] btyes = new byte[((List<Byte>) messageList).size()];
		for (int i = 0; i < ((List<Byte>) messageList).size(); i++) {
			btyes[i] = ((List<Byte>) messageList).get(i);
		}
		return btyes;
	}
}
