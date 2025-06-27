package com.example.代码优化.设计模式之美.a_54享元模式.chessGame.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/9 11:15
 * @Description:
 */
public class ChessPieceUnitFactory {
	private static final Map<Integer, ChessPieceUnit> pieces = new HashMap<>();

	static {
		pieces.put(1, new ChessPieceUnit(1, "车",  ChessPieceUnit.Color.RED));
		// ...省略摆放其他棋子的代码...
	}

	public static ChessPieceUnit getChessPiece(int chessPieceId) {
		return pieces.get(chessPieceId);
	}
}
