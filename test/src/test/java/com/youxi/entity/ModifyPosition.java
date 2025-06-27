package com.youxi.entity;



/**
 * 逻辑坐标 (当前角色坐标就是逻辑坐标) 服务器与客户端通信的坐标均是该类型
 * 
 */
public final class ModifyPosition extends Position {


	public ModifyPosition(int x, int y, int z) {
		super(x, y, z);
	}

	public ModifyPosition(int x, int y) {
		super(x, 0, y);
	}



	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}


	public void setX(float x) {
		this.x = (int) (x * CMath.PRECISION);
	}

	public void setY(float y) {
		this.y = (int) (y * CMath.PRECISION);
	}
	public void setZ(float z) {
		this.z = (int) (z * CMath.PRECISION);
	}

	public void setPos(Position pos) {
		setX(pos.getX());
		setY(pos.getY());
		setZ(pos.getZ());
	}

	public Position toPosition() {
		return copy();
	}

	public void clear() {
		setX(0);
		setY(0);
		setZ(0);
	}
}
