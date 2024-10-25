package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LGOfflineSave extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, CarrierData> carrierData;  // key:载具srl value:载具数据

    LGOfflineSave(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.carrierData = new java.util.concurrent.ConcurrentHashMap<Long, CarrierData>();
	}

	public LGOfflineSave() {
		this((warrior.mdb.XBean)null);
	}

	public LGOfflineSave(LGOfflineSave _o_) {
		this(_o_, null);
	}

	LGOfflineSave(LGOfflineSave _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.carrierData = new java.util.concurrent.ConcurrentHashMap<Long, CarrierData>();
		_o_.carrierData.forEach((_k_, _v_) -> this.carrierData.put(_k_, new CarrierData(_v_)));
	}

	public void copyFrom(LGOfflineSave _o_) {
        this.carrierData = new java.util.concurrent.ConcurrentHashMap<Long, CarrierData>();
		_o_.carrierData.forEach((_k_, _v_) -> this.carrierData.put(_k_, new CarrierData(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, CarrierData> getCarrierData(){
		return this.carrierData;
	}

	@JSONField(name = "1")
	public void setCarrierData(java.util.concurrent.ConcurrentHashMap<Long, CarrierData> _v_){
		this.carrierData = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LGOfflineSave) {
			LGOfflineSave _o_ = (LGOfflineSave)_o1_;
			if (!this.carrierData.equals(_o_.carrierData)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.carrierData.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(carrierData).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
