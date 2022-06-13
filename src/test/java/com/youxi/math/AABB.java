package com.youxi.math;

import com.youxi.entity.CMath;
import com.youxi.entity.Dir;
import com.youxi.entity.Position;

import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/5/23 17:09
 * @Description:
 */
public class AABB {

	/**
	 * @param p0    射线起点
	 * @param p1    射线终点
	 * @param verts 顶点数组
	 */
	public boolean dtIntersectSegmentPoly2D(Position p0, Position p1, List<Position> verts) {
		double tmin = 0;
		double tmax = 1;
		int segMin = -1;
		int segMax = -1;

		// 射线方向
		Dir dir = p1.sub(p0).toDir();

		for (int i = 0, j = verts.size() - 1; i < verts.size(); j = i++) {
			// 多边形的一条边
			Position edge = verts.get(i).sub(verts.get(j));
			// 顶点和 j 顶点相差距离
			Position diff = p0.sub(verts.get(j));

			// diff x edge y  n>0说明 diff在edge的右边
			long n = CMath.Cross2D(diff, edge);
			long d = CMath.Cross2D(edge, dir.toPosition());

			// 如果 dir 和 edge 基本上平行
			if (d < 0.0001) {
				// diff在edge的左边 就永远不可能相交
				if (n < 0) {
					return false;
				} else {
					continue;
				}
			}

			// 两条线段之比
			double t = n * 1.0 / d;
			if (d < 0)
			{
				// segment S is entering across this edge
				if (t > tmin)
				{
					tmin = t;
					segMin = j;
					// S enters after leaving polygon
					if (tmin > tmax)
						return false;
				}
			}
			else
			{
				// segment S is leaving across this edge
				if (t < tmax)
				{
					tmax = t;
					segMax = j;
					// S leaves before entering polygon
					if (tmax < tmin)
						return false;
				}
			}
		}

		return true;
	}

}
