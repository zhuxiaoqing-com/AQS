package com.youxi.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * polygon
 *
 * @author Eric.Ma
 *
 */
public class ConvexPolygon extends Shape {

	private List<Position> vertexs = new ArrayList<Position>();

	public ConvexPolygon(List<Position> vertexs) {
		super(vertexs.get(0));
		if (vertexs == null || vertexs.size() < 3)
			throw new IllegalArgumentException("polygon need at least 3 points");

		this.vertexs = vertexs;
	}

	/**
	 *
	 * @param pos
	 * @return
	 */
	public boolean inRegion(Position pos) {
		long result = 0;
		int size = vertexs.size();
		for (int i = 0; i < size; i++) {
			Position A;
			Position B;
			A = vertexs.get(i);
			if (i == size - 1) {
				B = vertexs.get(0);
			} else {
				B = vertexs.get(i + 1);
			}

			long checkResult = getResultOfLine(A, B, pos);
			if(checkResult == 0) { // 平行点在多边形线上就直接跳过
				continue;
			}
			// 将结果缩小 防止相乘的时候结果溢出
			checkResult = checkResult > 0 ? 1 : -1;
			if (result == 0) { // 第一次计算就跳过
				result = checkResult;
				continue;
			}

			if (result * checkResult < 0)
				return false;
		}

		return true;
	}


	/**
	 * 根据向量AB(a,b)的法向量M(b,-a)与向量AP的内积正负来判断点P在AB的左边还是右边
	 *
	 * @param A
	 * @param B
	 * @param p
	 * @return
	 */
	public long getResultOfLine(Position A, Position B, Position p) {

		long x1 = A.getX() - p.getX();
		long y1 = A.getZ() - p.getZ();
		long x2 = B.getX() - p.getX();
		long y2 = B.getZ() - p.getZ();


		return x1 * y2 - x2 * y1;
	}

	private Position SubPoint(Position v1, Position v2) {
		Position vector = new Position(v1.getX()- v2.getX(), 0, v1.getZ() - v2.getZ());
		return vector;
	}

	private float CrossProduct(Position v1, Position v2) {
		return v1.getX() * v2.getZ() - v2.getX() * v1.getZ();
	}

	private boolean IsPointInConvexPolygon(List<Position> vectors, Position targetpos) {
		float nCurCrossProduct = 0, nLastValue = 0;
		for (int i = 0; i < vectors.size(); i++) {
			Position vU = SubPoint(targetpos, vectors.get(i));
			int nextindex = (i + 1) % vectors.size();
			Position vV = SubPoint(vectors.get(nextindex), vectors.get(i));
			nCurCrossProduct = CrossProduct(vU, vV);
			System.err.println(nCurCrossProduct);
			if (i > 0 && nCurCrossProduct * nLastValue <= 0) {
				return false;
			}
			nLastValue = nCurCrossProduct;
		}
		return true;
	}

	/**
	 * 获取三角形面积
	 * 叉乘的一半就是三角形面积
	 */
	private long getS(Position a, Position b, Position c) {
		return CMath.Cross2D(b.sub(a), c.sub(a)) / 2;
	}

	public List<Position> getVertexs() {
		return vertexs;
	}

	@Override
	public Position support(Dir dir) {
		long maxPosDot = Integer.MIN_VALUE;
		Position maxPos = null;
		for (Position position : getVertexs()) {
			long dot = position.dot(dir.toPosition());
			if (dot > maxPosDot) {
				maxPosDot = dot;
				maxPos = position;
			}
		}
		return maxPos;
	}
}
