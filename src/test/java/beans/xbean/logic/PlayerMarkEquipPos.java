package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMarkEquipPos extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> markPosItemMap;  // key:装备槽位id value:对应的道具id

    PlayerMarkEquipPos(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.markPosItemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerMarkEquipPos() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMarkEquipPos(PlayerMarkEquipPos _o_) {
		this(_o_, null);
	}

	PlayerMarkEquipPos(PlayerMarkEquipPos _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.markPosItemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.markPosItemMap.forEach((_k_, _v_) -> this.markPosItemMap.put(_k_, _v_));
	}

	public void copyFrom(PlayerMarkEquipPos _o_) {
        this.markPosItemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.markPosItemMap.forEach((_k_, _v_) -> this.markPosItemMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getMarkPosItemMap(){
		return this.markPosItemMap;
	}

	@JSONField(name = "1")
	public void setMarkPosItemMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.markPosItemMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMarkEquipPos) {
			PlayerMarkEquipPos _o_ = (PlayerMarkEquipPos)_o1_;
			if (!this.markPosItemMap.equals(_o_.markPosItemMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.markPosItemMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(markPosItemMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
