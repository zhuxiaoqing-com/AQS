package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensRentInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, GensCarrierRent> carrierRent;  // 军团成员租借

    GensRentInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.carrierRent = new java.util.concurrent.ConcurrentHashMap<Long, GensCarrierRent>();
	}

	public GensRentInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensRentInfo(GensRentInfo _o_) {
		this(_o_, null);
	}

	GensRentInfo(GensRentInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.carrierRent = new java.util.concurrent.ConcurrentHashMap<Long, GensCarrierRent>();
		_o_.carrierRent.forEach((_k_, _v_) -> this.carrierRent.put(_k_, new GensCarrierRent(_v_)));
	}

	public void copyFrom(GensRentInfo _o_) {
        this.carrierRent = new java.util.concurrent.ConcurrentHashMap<Long, GensCarrierRent>();
		_o_.carrierRent.forEach((_k_, _v_) -> this.carrierRent.put(_k_, new GensCarrierRent(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, GensCarrierRent> getCarrierRent(){
		return this.carrierRent;
	}

	@JSONField(name = "1")
	public void setCarrierRent(java.util.concurrent.ConcurrentHashMap<Long, GensCarrierRent> _v_){
		this.carrierRent = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensRentInfo) {
			GensRentInfo _o_ = (GensRentInfo)_o1_;
			if (!this.carrierRent.equals(_o_.carrierRent)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.carrierRent.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(carrierRent).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
