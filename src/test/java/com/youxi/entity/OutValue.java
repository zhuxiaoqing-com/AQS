package com.youxi.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * a传出参数
 *
 * @author Eric.Ma
 */
public class OutValue {
	private List<OutValue> outValueList = new ArrayList<>();// ColliderList 使用

	private long t;
	/**
	 * {@link CMath.Direction}
	 */
	private int hitEdge; // 击中的边
	private int hitColliderIndex = -1; // 击中的碰撞盒的下标(ColliderList类型使用 用于翻墙)

	private ModifyPosition pos = new ModifyPosition(0, 0, 0);

	public ObbCache obbCache = new ObbCache();
	public CircleCache circleCache = new CircleCache();

	public List<OutValue> getOutValueList(int colliderCount) {
		if (outValueList.size() < colliderCount) {
			int needCreateNum = colliderCount - outValueList.size();
			for (int i = 0; i < needCreateNum; i++) {
				outValueList.add(new OutValue());
			}
		}
		return outValueList;
	}


	public long getT() {
		return t;
	}


	public ModifyPosition getModifyPos() {
		return pos;
	}

	public int getHitEdge() {
		return hitEdge;
	}

	public int getHitColliderIndex() {
		return hitColliderIndex;
	}

	public void setResult(long t, int hitEdge) {
		this.t = t;
		this.hitEdge = hitEdge;
	}

	public void setResult(OutValue outValue, int hitColliderIndex) {
		this.hitColliderIndex = hitColliderIndex;
		setResult(outValue.getT(), outValue.getHitEdge());
		getModifyPos().setX(outValue.getModifyPos().getX());
		getModifyPos().setZ(outValue.getModifyPos().getZ());
	}

	public void reset() {
		this.t = 0;
		this.hitEdge = 0;
		pos.setPos(Position.ZERO);
		dir.setDir(Dir.ZERO);

		obbCache.clear();
		circleCache.clear();
		for (OutValue outValue : this.outValueList) {
			outValue.reset();
		}
	}


	public ModifyDir dir = new ModifyDir();

	public class ObbCache {
		public ModifyDir fo = new ModifyDir();
		public ModifyDir fd = new ModifyDir();
		public ModifyPosition fo2p = new ModifyPosition(0, 0, 0);
		public ModifyDir sizeDirRightTopNegDir = new ModifyDir();

		public void clear() {
			fo.setDir(Dir.ZERO);
			fd.setDir(Dir.ZERO);
			fo2p.setPos(Position.ZERO);
			sizeDirRightTopNegDir.setDir(Dir.ZERO);
		}
	}

	public class CircleCache {
		ModifyPosition d = new ModifyPosition(0, 0, 0);
		ModifyPosition f = new ModifyPosition(0, 0, 0);

		public void clear() {
			d.setPos(Position.ZERO);
			f.setPos(Position.ZERO);
		}
	}

}
