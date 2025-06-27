package com.youxi.entity;



/**
 * 朝向
 *
 * @author Eric.Ma
 *
 */
public class ModifyDir extends Dir {
	public ModifyDir() {
	}

	public ModifyDir(int x, int z) {
		super();
		this.x = x;
		this.z = z;
	}



	public ModifyDir(int x, int y, int z) {
		this.x = x;
		this.z = z;
		this.y = y;
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

	public void setDir(Dir dir) {
		this.setX(dir.getX());
		this.setY(dir.getY());
		this.setZ(dir.getZ());
	}
}