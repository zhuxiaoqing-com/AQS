package com.example.代码优化.设计模式之美.a_54享元模式.textEditor;

import java.util.HashMap;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/9 11:24
 * @Description: 字体
 */
public class Font {
	private int a;
	@Override
	public int hashCode() {
		return a;
	}

	@Override
	public boolean equals(Object obj) {
		Font ohter = (Font) obj;
		return a == ohter.a;
	}
}
