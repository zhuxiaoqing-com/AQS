package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class BTCarrierSyncInfo extends warrior.mdb.XBean {
    private String pbBT2LBCarrierStateSyncBytes;  // 同步数据
    private long carrierSrl;  // 载具唯一id
    private int type;  // 同步类型

    BTCarrierSyncInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.pbBT2LBCarrierStateSyncBytes = "";
	}

	public BTCarrierSyncInfo() {
		this((warrior.mdb.XBean)null);
	}

	public BTCarrierSyncInfo(BTCarrierSyncInfo _o_) {
		this(_o_, null);
	}

	BTCarrierSyncInfo(BTCarrierSyncInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.pbBT2LBCarrierStateSyncBytes = _o_.pbBT2LBCarrierStateSyncBytes;
		this.carrierSrl = _o_.carrierSrl;
		this.type = _o_.type;
	}

	public void copyFrom(BTCarrierSyncInfo _o_) {
        this.pbBT2LBCarrierStateSyncBytes = _o_.pbBT2LBCarrierStateSyncBytes;
        this.carrierSrl = _o_.carrierSrl;
        this.type = _o_.type;
	}


	@JSONField(name = "1")
	public String getPbBT2LBCarrierStateSyncBytes(){
		return this.pbBT2LBCarrierStateSyncBytes;
	}

	@JSONField(name = "2")
	public long getCarrierSrl(){
		return this.carrierSrl;
	}

	@JSONField(name = "3")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "1")
	public void setPbBT2LBCarrierStateSyncBytes(String _v_){
		this.pbBT2LBCarrierStateSyncBytes = _v_;
	}

	@JSONField(name = "2")
	public void setCarrierSrl(long _v_){
		this.carrierSrl = _v_;
	}

	@JSONField(name = "3")
	public void setType(int _v_){
		this.type = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BTCarrierSyncInfo) {
			BTCarrierSyncInfo _o_ = (BTCarrierSyncInfo)_o1_;
			if (!this.pbBT2LBCarrierStateSyncBytes.equals(_o_.pbBT2LBCarrierStateSyncBytes)) return false;
			if (this.carrierSrl != _o_.carrierSrl) return false;
			if (this.type != _o_.type) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.pbBT2LBCarrierStateSyncBytes.hashCode();
		_h_ += _h_ * 31 + (int)(carrierSrl ^ (carrierSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.type);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append("T").append(pbBT2LBCarrierStateSyncBytes.length()).append(",");
		_sb_.append(carrierSrl).append(",");
		_sb_.append(type).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
