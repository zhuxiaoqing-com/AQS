package com.example.代码优化.设计模式之美.a_54享元模式.chessGame.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/9 10:48
 * @Description: 棋局
 */
public class ChessBoard {
	private Map<Integer, ChessPiece> chessPieces = new HashMap<>();

	public ChessBoard() {
		init();
	}

	private void init() {
		chessPieces.put(1, new ChessPiece(ChessPieceUnitFactory.getChessPiece(1),0,1));
		// ...省略摆放其他棋子的代码...
	}

	public void move(int chessPieceId, int toPositionX, int toPositionY) {
		// ... 省略 ...
	}
}
