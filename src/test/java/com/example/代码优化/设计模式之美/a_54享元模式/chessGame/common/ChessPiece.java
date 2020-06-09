package com.example.代码优化.设计模式之美.a_54享元模式.chessGame.common;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/9 10:46
 * @Description: 棋子
 */
public class ChessPiece {
	private int id;
	private String text;
	private Color color;
	private int positionX;
	private int positionY;

	public ChessPiece(int id, String text, Color color, int positionX, int positionY) {
		this.id = id;
		this.text = text;
		this.color = color;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}


	public enum Color {
		RED, BLACK
	}

}
