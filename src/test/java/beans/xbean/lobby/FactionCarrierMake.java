package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionCarrierMake extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, CarrierMakeInfo> makeMap;  // 载具制造列表

    FactionCarrierMake(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.makeMap = new java.util.concurrent.ConcurrentHashMap<Long, CarrierMakeInfo>();
	}

	public FactionCarrierMake() {
		this((warrior.mdb.XBean)null);
	}

	public FactionCarrierMake(FactionCarrierMake _o_) {
		this(_o_, null);
	}

	FactionCarrierMake(FactionCarrierMake _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.makeMap = new java.util.concurrent.ConcurrentHashMap<Long, CarrierMakeInfo>();
		_o_.makeMap.forEach((_k_, _v_) -> this.makeMap.put(_k_, new CarrierMakeInfo(_v_)));
	}

	public void copyFrom(FactionCarrierMake _o_) {
        this.makeMap = new java.util.concurrent.ConcurrentHashMap<Long, CarrierMakeInfo>();
		_o_.makeMap.forEach((_k_, _v_) -> this.makeMap.put(_k_, new CarrierMakeInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, CarrierMakeInfo> getMakeMap(){
		return this.makeMap;
	}

	@JSONField(name = "1")
	public void setMakeMap(java.util.concurrent.ConcurrentHashMap<Long, CarrierMakeInfo> _v_){
		this.makeMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionCarrierMake) {
			FactionCarrierMake _o_ = (FactionCarrierMake)_o1_;
			if (!this.makeMap.equals(_o_.makeMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.makeMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(makeMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
