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
		messageList.add((byte) ((frameType << 4) | flags));
		messageList.add((byte) keepalive);
		byte[] bytes11 = clientId.getBytes(StandardCharsets.US_ASCII);
		((List<Byte>) messageList).add((byte) (bytes11.length >> 8));
		((List<Byte>) messageList).add((byte) (bytes11.length));
		for (byte b1 : bytes11) {
			((List<Byte>) messageList).add(b1);
		}
		if (userName != null && !userName.isEmpty()) {
			byte[] bytes1 = userName.getBytes(StandardCharsets.US_ASCII);
			((List<Byte>) messageList).add((byte) (bytes1.length >> 8));
			((List<Byte>) messageList).add((byte) (bytes1.length));
			for (byte b : bytes1) {
				((List<Byte>) messageList).add(b);
			}
		}
		if (password != null && !password.isEmpty()) {
			byte[] bytes1 = password.getBytes(StandardCharsets.US_ASCII);
			((List<Byte>) messageList).add((byte) (bytes1.length >> 8));
			((List<Byte>) messageList).add((byte) (bytes1.length));
			for (byte b : bytes1) {
				((List<Byte>) messageList).add(b);
			}
		}
		int bodyLength = messageList.size() - 1;
		messageList.add(1, (byte) (bodyLength >> 8));
		messageList.add(2, (byte) bodyLength);

		byte[] btyes = new byte[((List<Byte>) messageList).size()];
		for (int i = 0; i < ((List<Byte>) messageList).size(); i++) {
			btyes[i] = ((List<Byte>) messageList).get(i);
		}
		return btyes;
	}
}
