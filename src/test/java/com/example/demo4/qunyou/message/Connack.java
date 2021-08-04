package com.example.demo4.qunyou.message;

import com.example.demo4.qunyou.MessageConstant;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
		messageList.add((byte) (frameType << 4 & flags));
		addStr(messageList,messageStr);
		return toByteArray(messageList);
	}

}
