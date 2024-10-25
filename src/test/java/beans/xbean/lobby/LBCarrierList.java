package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBCarrierList extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, LBWildRentCarrier> lbRentCarrierMap;  // srl->租借载具数据
    private long lastCheckMill;  // 上次checkTime

    LBCarrierList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.lbRentCarrierMap = new java.util.concurrent.ConcurrentHashMap<Long, LBWildRentCarrier>();
	}

	public LBCarrierList() {
		this((warrior.mdb.XBean)null);
	}

	public LBCarrierList(LBCarrierList _o_) {
		this(_o_, null);
	}

	LBCarrierList(LBCarrierList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.lbRentCarrierMap = new java.util.concurrent.ConcurrentHashMap<Long, LBWildRentCarrier>();
		_o_.lbRentCarrierMap.forEach((_k_, _v_) -> this.lbRentCarrierMap.put(_k_, new LBWildRentCarrier(_v_)));
		this.lastCheckMill = _o_.lastCheckMill;
	}

	public void copyFrom(LBCarrierList _o_) {
        this.lbRentCarrierMap = new java.util.concurrent.ConcurrentHashMap<Long, LBWildRentCarrier>();
		_o_.lbRentCarrierMap.forEach((_k_, _v_) -> this.lbRentCarrierMap.put(_k_, new LBWildRentCarrier(_v_)));
        this.lastCheckMill = _o_.lastCheckMill;
	}


	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, LBWildRentCarrier> getLbRentCarrierMap(){
		return this.lbRentCarrierMap;
	}

	@JSONField(name = "6")
	public long getLastCheckMill(){
		return this.lastCheckMill;
	}

	@JSONField(name = "2")
	public void setLbRentCarrierMap(java.util.concurrent.ConcurrentHashMap<Long, LBWildRentCarrier> _v_){
		this.lbRentCarrierMap = _v_;
	}

	@JSONField(name = "6")
	public void setLastCheckMill(long _v_){
		this.lastCheckMill = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBCarrierList) {
			LBCarrierList _o_ = (LBCarrierList)_o1_;
			if (!this.lbRentCarrierMap.equals(_o_.lbRentCarrierMap)) return false;
			if (this.lastCheckMill != _o_.lastCheckMill) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.lbRentCarrierMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastCheckMill ^ (lastCheckMill >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(lbRentCarrierMap).append(",");
		_sb_.append(lastCheckMill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
