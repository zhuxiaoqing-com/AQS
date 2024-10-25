package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class TroopCarrier extends warrior.mdb.XBean {
    private xbean.lobby.LBCarrierList lBCarrierList;  // 载具list

    TroopCarrier(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.lBCarrierList = new LBCarrierList(this);
	}

	public TroopCarrier() {
		this((warrior.mdb.XBean)null);
	}

	public TroopCarrier(TroopCarrier _o_) {
		this(_o_, null);
	}

	TroopCarrier(TroopCarrier _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.lBCarrierList = new LBCarrierList(_o_.lBCarrierList, this);
	}

	public void copyFrom(TroopCarrier _o_) {
        this.lBCarrierList = new LBCarrierList(_o_.lBCarrierList, this);
	}


	@JSONField(name = "1")
	public xbean.lobby.LBCarrierList getLBCarrierList(){
		return this.lBCarrierList;
	}

	@JSONField(name = "1")
	public void setLBCarrierList(xbean.lobby.LBCarrierList _v_){
		this.lBCarrierList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TroopCarrier) {
			TroopCarrier _o_ = (TroopCarrier)_o1_;
			if (!this.lBCarrierList.equals(_o_.lBCarrierList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.lBCarrierList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(lBCarrierList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
