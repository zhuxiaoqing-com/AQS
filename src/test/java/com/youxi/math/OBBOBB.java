package com.youxi.math;

import com.youxi.building.Misc;
import com.youxi.entity.*;
import org.junit.Test;

import java.util.Random;

public class OBBOBB{

	/**
	 * https://www.cnblogs.com/hont/p/9501169.html
	 * <p>
	 * 在两个凸多边形中找到一根轴，凸多边形所有在这根轴上的投影点不产生相交，则这两个凸多边形不相交。
	 * 这根轴一般取每个边的垂线，逐个投影进行测试。
	 *
	 * @param aaa
	 * @param bbb
	 * @return
	 */
	public boolean intersects(OBB2D aaa, OBB2D bbb) {
		Position axis1 = aaa.getP1().sub(aaa.getP0()).normalize();
		Position axis2 = aaa.getP3().sub(aaa.getP0()).normalize();

		Position axis3 = bbb.getP1().sub(aaa.getP0()).normalize();
		Position axis4 = bbb.getP3().sub(aaa.getP0()).normalize();


		// 只要有一个不相交就是不相交
		if (isIntersect(aaa, bbb, axis1)) return false;
		if (isIntersect(aaa, bbb, axis2)) return false;
		if (isIntersect(aaa, bbb, axis3)) return false;
		if (isIntersect(aaa, bbb, axis4)) return false;

		return true;
	}


	/**
	 * @return 相交:false  不相交：true
	 */
	private boolean isIntersect(OBB2D aaa, OBB2D bbb, Position axis) {
		long aaaP0 = CMath.Dot2D(aaa.getP0(), axis);
		long aaaP1 = CMath.Dot2D(aaa.getP1(), axis);
		long aaaP2 = CMath.Dot2D(aaa.getP2(), axis);
		long aaaP3 = CMath.Dot2D(aaa.getP3(), axis);

		long bbbP0 = CMath.Dot2D(bbb.getP0(), axis);
		long bbbP1 = CMath.Dot2D(bbb.getP1(), axis);
		long bbbP2 = CMath.Dot2D(bbb.getP2(), axis);
		long bbbP3 = CMath.Dot2D(bbb.getP3(), axis);

		long xMin = CMath.min(aaaP0, aaaP1, aaaP2, aaaP3);
		long xMax = CMath.max(aaaP0, aaaP1, aaaP2, aaaP3);
		long yMin = CMath.min(bbbP0, bbbP1, bbbP2, bbbP3);
		long yMax = CMath.max(bbbP0, bbbP1, bbbP2, bbbP3);


		if (yMin > xMin && yMin < xMax) return false;
		if (yMax > xMin && yMax < xMax) return false;
		if (xMin > yMin && xMin < yMax) return false;
		if (xMax > yMin && xMax < yMax) return false;

		return true;
	}


	////////////////////////第二种实现方法///////////////////////////
	////////////////////////第二种实现方法///////////////////////////
	////////////////////////第二种实现方法///////////////////////////

	/**
	 * https://www.jianshu.com/p/2c842362fb22
	 */
	public boolean isIntersect2(OBB2D aaa, OBB2D bbb) {
		Position t = aaa.getCenter().sub(bbb.getCenter());
		return true;

	}


	public static boolean in(OBB2D aaa, Position position) {
		Position rotateDir = position.sub(aaa.getCenter()).getRotateDir(-aaa.getDir());

		int x = aaa.getSizeDir().getX();
		int z = aaa.getSizeDir().getZ();

		return rotateDir.getX() > -x && rotateDir.getX() < x && rotateDir.getZ() > -z && rotateDir.getZ() < z;
	}

	@Test
	public void test01() {
		OBB2D obb2D = new OBB2D(new Position(2000, 2000), new Dir(1000, 1000), 0);

		int index = 12322;

		for (int i = 0; i < index; i++) {
			int dir = Misc.getRandomBetween(0, 360);
			obb2D.setDir(dir);
			Position position = obb2D.randomPosition2();
			if (!in(obb2D, position)) {
				System.out.println("position:  " + position + "  dir :" + dir + "   in ： " + in(obb2D, position));
			}
		}
	}


}
