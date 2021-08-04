package com.example.demo4.qunyou.message;

import com.example.demo4.qunyou.MessageConstant;

import java.util.ArrayList;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/8/4 14:32
 * @Description:
 */
public class DisConnect extends AbsMessage {

	private static int frameType = MessageConstant.Type.DISSCONNECT;

	public byte[] array() {
		ArrayList<Byte> messageList = new ArrayList<>();
		messageList.add((byte) (0x60));
		return toByteArray(messageList);
	}

}
