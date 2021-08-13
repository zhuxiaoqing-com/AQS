package com.example.demo4.qunyou.message;

import com.example.demo4.qunyou.MessageConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/8/4 14:32
 * @Description:
 */
public class Ping extends AbsMessage {

	private static int frameType = MessageConstant.Type.PING;


	public byte[] array() {
		ArrayList<Byte> messageList = new ArrayList<>();
		messageList.add((byte) (0x40));
		byte[] btyes = new byte[((List<Byte>) messageList).size()];
		for (int i = 0; i < ((List<Byte>) messageList).size(); i++) {
			btyes[i] = ((List<Byte>) messageList).get(i);
		}
		return btyes;
	}

}
