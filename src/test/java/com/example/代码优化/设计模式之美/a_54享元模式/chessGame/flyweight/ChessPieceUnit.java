package com.example.代码优化.设计模式之美.a_54享元模式.chessGame.flyweight;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/9 11:12
 * @Description: 享元类
 */
public class ChessPieceUnit {
	private int id;
	private String text;
	private Color color;

	public ChessPieceUnit(int id, String text, Color color) {
		this.id = id;
		this.text = text;
		this.color = color;
	}

	public static enum Color {
		RED, BLACK;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public Color getColor() {
		return color;
	}
}
