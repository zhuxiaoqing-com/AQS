package com.example.代码优化.设计模式之美.a_54享元模式.textEditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/9 11:29
 * @Description:
 */
public class CharacterStyleFactory {
	private static final Map<CharacterStyle, CharacterStyle> styles = new HashMap<>();

	public static CharacterStyle getStyle(Font font, int size, int colorRGB) {
		CharacterStyle characterStyle = new CharacterStyle(font, size, colorRGB);
		return styles.computeIfAbsent(characterStyle, key -> characterStyle);
	}
}
