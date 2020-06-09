package com.example.代码优化.设计模式之美.a_54享元模式.chessGame.flyweight;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/9 11:18
 * @Description:
 */
public class ChessPiece {
	private ChessPieceUnit chessPieceUnit;
	private int positionX;
	private int positionY;

	public ChessPiece(ChessPieceUnit chessPieceUnit, int positionX, int positionY) {
		this.chessPieceUnit = chessPieceUnit;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public ChessPieceUnit getChessPieceUnit() {
		return chessPieceUnit;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}
}
