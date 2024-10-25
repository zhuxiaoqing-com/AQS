package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FlagSpecialInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> teleportMap;  // 阵营id->已经消耗的传送次数

    FlagSpecialInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.teleportMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public FlagSpecialInfo() {
		this((warrior.mdb.XBean)null);
	}

	public FlagSpecialInfo(FlagSpecialInfo _o_) {
		this(_o_, null);
	}

	FlagSpecialInfo(FlagSpecialInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.teleportMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.teleportMap.forEach((_k_, _v_) -> this.teleportMap.put(_k_, _v_));
	}

	public void copyFrom(FlagSpecialInfo _o_) {
        this.teleportMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.teleportMap.forEach((_k_, _v_) -> this.teleportMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTeleportMap(){
		return this.teleportMap;
	}

	@JSONField(name = "1")
	public void setTeleportMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.teleportMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FlagSpecialInfo) {
			FlagSpecialInfo _o_ = (FlagSpecialInfo)_o1_;
			if (!this.teleportMap.equals(_o_.teleportMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.teleportMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(teleportMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
