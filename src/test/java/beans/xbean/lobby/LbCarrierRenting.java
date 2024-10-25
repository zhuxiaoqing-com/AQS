package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LbCarrierRenting extends warrior.mdb.XBean {
    private long srl;  // 载具srl
    private int cfgId;  // 载具配置id
    private long rentDeleteTime;  // 载具租借删除时间
    private long ownerPlayerId;  // 主人玩家id
    private long rentPlayerId;  // 租借者玩家id
    private int idType; 

    LbCarrierRenting(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public LbCarrierRenting() {
		this((warrior.mdb.XBean)null);
	}

	public LbCarrierRenting(LbCarrierRenting _o_) {
		this(_o_, null);
	}

	LbCarrierRenting(LbCarrierRenting _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.cfgId = _o_.cfgId;
		this.rentDeleteTime = _o_.rentDeleteTime;
		this.ownerPlayerId = _o_.ownerPlayerId;
		this.rentPlayerId = _o_.rentPlayerId;
		this.idType = _o_.idType;
	}

	public void copyFrom(LbCarrierRenting _o_) {
        this.srl = _o_.srl;
        this.cfgId = _o_.cfgId;
        this.rentDeleteTime = _o_.rentDeleteTime;
        this.ownerPlayerId = _o_.ownerPlayerId;
        this.rentPlayerId = _o_.rentPlayerId;
        this.idType = _o_.idType;
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public int getCfgId(){
		return this.cfgId;
	}

	@JSONField(name = "3")
	public long getRentDeleteTime(){
		return this.rentDeleteTime;
	}

	@JSONField(name = "4")
	public long getOwnerPlayerId(){
		return this.ownerPlayerId;
	}

	@JSONField(name = "5")
	public long getRentPlayerId(){
		return this.rentPlayerId;
	}

	@JSONField(name = "6")
	public int getIdType(){
		return this.idType;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setCfgId(int _v_){
		this.cfgId = _v_;
	}

	@JSONField(name = "3")
	public void setRentDeleteTime(long _v_){
		this.rentDeleteTime = _v_;
	}

	@JSONField(name = "4")
	public void setOwnerPlayerId(long _v_){
		this.ownerPlayerId = _v_;
	}

	@JSONField(name = "5")
	public void setRentPlayerId(long _v_){
		this.rentPlayerId = _v_;
	}

	@JSONField(name = "6")
	public void setIdType(int _v_){
		this.idType = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LbCarrierRenting) {
			LbCarrierRenting _o_ = (LbCarrierRenting)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.cfgId != _o_.cfgId) return false;
			if (this.rentDeleteTime != _o_.rentDeleteTime) return false;
			if (this.ownerPlayerId != _o_.ownerPlayerId) return false;
			if (this.rentPlayerId != _o_.rentPlayerId) return false;
			if (this.idType != _o_.idType) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(this.cfgId);
		_h_ += _h_ * 31 + (int)(rentDeleteTime ^ (rentDeleteTime >>> 32));
		_h_ += _h_ * 31 + (int)(ownerPlayerId ^ (ownerPlayerId >>> 32));
		_h_ += _h_ * 31 + (int)(rentPlayerId ^ (rentPlayerId >>> 32));
		_h_ += _h_ * 31 + (int)(this.idType);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(cfgId).append(",");
		_sb_.append(rentDeleteTime).append(",");
		_sb_.append(ownerPlayerId).append(",");
		_sb_.append(rentPlayerId).append(",");
		_sb_.append(idType).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
