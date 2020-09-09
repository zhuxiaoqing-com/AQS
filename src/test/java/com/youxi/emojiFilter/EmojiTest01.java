package com.youxi.emojiFilter;

import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/9/9 19:26
 * @Description:
 */
public class EmojiTest01 {

	@Test
	public void test01() {

	}

	/**
	 * UTF-8使用1~4字节为每个字符编码
	 *
	 * Emoji 是utf-8 4字节的所以只要判断该字是不是4字节的
	 * 下面是 1字节~4字节 utf-8 表示
	 * 1： 0xxx xxxx
	 * 2  110x xxxx 10xx xxxx
	 * 3  1110 xxxx 10xx xxxx 10xx xxxx
	 * 4  1111 0xxx 10xx xxxx 10xx xxxx  10xx xxxx
	 */
}
