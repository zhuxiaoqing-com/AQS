package com.example.demo4.qunyou;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/8/4 14:34
 * @Description:
 */
public interface MessageConstant {
	byte VERSION = 1;
	byte KEEP_ALIVE = 6;

	interface Type {
		byte CONNECT = 1;
		byte CONNACK = 2;
		byte DATATRANS = 3;
		byte PING = 4;
		byte PONG = 5;
		byte DISSCONNECT = 6;
	}

}
