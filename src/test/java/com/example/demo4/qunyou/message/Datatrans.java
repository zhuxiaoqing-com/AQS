package com.example.demo4.qunyou.message;

import com.example.demo4.qunyou.MessageConstant;

import java.util.ArrayList;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/8/4 14:32
 * @Description:
 */
public class Datatrans extends AbsMessage {

	private static int frameType = MessageConstant.Type.DATATRANS;
	private int flags;
	private byte[] byesMess;

	public Datatrans(byte[] byesMess) {
		this.byesMess = byesMess;
	}

	public byte[] array() {
		ArrayList<Byte> messageList = new ArrayList<>();
		messageList.add((byte) (0x30));
		messageList.add((byte) (byesMess.length>>8));
		messageList.add((byte) byesMess.length);
		for (byte mess : byesMess) {
			messageList.add(mess);
		}

		return toByteArray(messageList);
	}

}
