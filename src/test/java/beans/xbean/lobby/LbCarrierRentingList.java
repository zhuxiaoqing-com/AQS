package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LbCarrierRentingList extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, LbCarrierRenting> carrierRentingMap;  // 载具srl -> 载具租借数据

    LbCarrierRentingList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.carrierRentingMap = new java.util.concurrent.ConcurrentHashMap<Long, LbCarrierRenting>();
	}

	public LbCarrierRentingList() {
		this((warrior.mdb.XBean)null);
	}

	public LbCarrierRentingList(LbCarrierRentingList _o_) {
		this(_o_, null);
	}

	LbCarrierRentingList(LbCarrierRentingList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.carrierRentingMap = new java.util.concurrent.ConcurrentHashMap<Long, LbCarrierRenting>();
		_o_.carrierRentingMap.forEach((_k_, _v_) -> this.carrierRentingMap.put(_k_, new LbCarrierRenting(_v_)));
	}

	public void copyFrom(LbCarrierRentingList _o_) {
        this.carrierRentingMap = new java.util.concurrent.ConcurrentHashMap<Long, LbCarrierRenting>();
		_o_.carrierRentingMap.forEach((_k_, _v_) -> this.carrierRentingMap.put(_k_, new LbCarrierRenting(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, LbCarrierRenting> getCarrierRentingMap(){
		return this.carrierRentingMap;
	}

	@JSONField(name = "1")
	public void setCarrierRentingMap(java.util.concurrent.ConcurrentHashMap<Long, LbCarrierRenting> _v_){
		this.carrierRentingMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LbCarrierRentingList) {
			LbCarrierRentingList _o_ = (LbCarrierRentingList)_o1_;
			if (!this.carrierRentingMap.equals(_o_.carrierRentingMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.carrierRentingMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(carrierRentingMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
