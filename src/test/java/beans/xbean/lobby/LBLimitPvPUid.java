package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBLimitPvPUid extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<String, Long> PvPUidMap;  // key:玩家uid value:禁止PvP结束时间

    LBLimitPvPUid(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.PvPUidMap = new java.util.concurrent.ConcurrentHashMap<String, Long>();
	}

	public LBLimitPvPUid() {
		this((warrior.mdb.XBean)null);
	}

	public LBLimitPvPUid(LBLimitPvPUid _o_) {
		this(_o_, null);
	}

	LBLimitPvPUid(LBLimitPvPUid _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.PvPUidMap = new java.util.concurrent.ConcurrentHashMap<String, Long>();
		_o_.PvPUidMap.forEach((_k_, _v_) -> this.PvPUidMap.put(_k_, _v_));
	}

	public void copyFrom(LBLimitPvPUid _o_) {
        this.PvPUidMap = new java.util.concurrent.ConcurrentHashMap<String, Long>();
		_o_.PvPUidMap.forEach((_k_, _v_) -> this.PvPUidMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<String, Long> getPvPUidMap(){
		return this.PvPUidMap;
	}

	@JSONField(name = "1")
	public void setPvPUidMap(java.util.concurrent.ConcurrentHashMap<String, Long> _v_){
		this.PvPUidMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBLimitPvPUid) {
			LBLimitPvPUid _o_ = (LBLimitPvPUid)_o1_;
			if (!this.PvPUidMap.equals(_o_.PvPUidMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.PvPUidMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(PvPUidMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
