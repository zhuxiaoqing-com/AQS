package com.example.demo4.qunyou.message;

import com.example.demo4.qunyou.MessageConstant;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/8/4 14:32
 * @Description:
 */
public class Connect extends AbsMessage {
	private static int frameType = MessageConstant.Type.CONNECT;
	private static int flags = MessageConstant.VERSION;
	private int keepalive;
	private String clientId;
	private String userName;
	private String password;


	public Connect(int keepalive, String clientId, String userName, String password) {
		this.keepalive = keepalive;
		this.clientId = clientId;
		this.userName = userName;
		this.password = password;
	}

	public byte[] array() {
		ArrayList<Byte> messageList = new ArrayList<>();
		messageList.add((byte) (frameType << 4 & flags));
		messageList.add((byte) keepalive);
		addStr(messageList, clientId);
		if (userName != null && !userName.isEmpty()) {
			addStr(messageList, userName);
		}
		if (password != null && !password.isEmpty()) {
			addStr(messageList, password);
		}
		messageList.add(1, (byte) ((messageList.size() - 1) >> 8));
		messageList.add(2, (byte) (messageList.size() - 1));

		return toByteArray(messageList);
	}
}
