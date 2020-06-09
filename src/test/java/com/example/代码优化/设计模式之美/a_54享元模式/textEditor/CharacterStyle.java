package com.example.代码优化.设计模式之美.a_54享元模式.textEditor;

import java.util.Objects;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/9 11:23
 * @Description:
 */
public class CharacterStyle {
	private Font font;
	private int size;
	private int colorRGB;

	public CharacterStyle(Font font, int size, int colorRGB) {
		this.font = font;
		this.size = size;
		this.colorRGB = colorRGB;
	}

	@Override
	public boolean equals(Object o) {
		CharacterStyle other = (CharacterStyle) o;
		return font.equals(other.font)
				&& size == other.size
				&& colorRGB == other.colorRGB;
	}

	@Override
	public int hashCode() {
		return Objects.hash(font, size, colorRGB);
	}
}
