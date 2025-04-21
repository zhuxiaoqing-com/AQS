package com.youxi.entity;



/**
 * 场景屏的数据处理
 * 
 * @author Eric.Ma
 *
 */
public final class MapHelper {


	public static Dir getDirBySrcAndDesPos(Position src,
										   Position des) {
		int _x = des.getX() - src.getX();
		int _z = des.getZ() - src.getZ();
		/*
		 * if (Math.abs(_x) <= CMath.HALF && Math.abs(_z) <= CMath.HALF) { _x *=
		 * CMath.PRECISION; _z *= CMath.PRECISION; }
		 */
		return new Dir(_x, _z);
	}

	public static Dir getDirBySrcAndDesPos(Position src, Position des, ModifyDir returnDir) {
		int _x = des.getX() - src.getX();
		int _y = src.getY();
		int _z = des.getZ() - src.getZ();
		/*
		 * if (Math.abs(_x) <= CMath.HALF && Math.abs(_z) <= CMath.HALF) { _x *=
		 * CMath.PRECISION; _z *= CMath.PRECISION; }
		 */
		returnDir.setX(_x);
		returnDir.setY(_y);
		returnDir.setZ(_z);
		return returnDir;
	}

}
