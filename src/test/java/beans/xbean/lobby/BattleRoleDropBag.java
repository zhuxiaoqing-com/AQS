package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class BattleRoleDropBag extends warrior.mdb.XBean {
    private int bagType; 
    private java.util.concurrent.CopyOnWriteArrayList<Long> srl; 

    BattleRoleDropBag(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.srl = new java.util.concurrent.CopyOnWriteArrayList<Long>();
	}

	public BattleRoleDropBag() {
		this((warrior.mdb.XBean)null);
	}

	public BattleRoleDropBag(BattleRoleDropBag _o_) {
		this(_o_, null);
	}

	BattleRoleDropBag(BattleRoleDropBag _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.bagType = _o_.bagType;
		this.srl = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.srl.forEach(_v_ -> this.srl.add(_v_));
	}

	public void copyFrom(BattleRoleDropBag _o_) {
        this.bagType = _o_.bagType;
        this.srl = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.srl.forEach(_v_ -> this.srl.add(_v_));
	}


	@JSONField(name = "1")
	public int getBagType(){
		return this.bagType;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getSrl(){
		return this.srl;
	}

	@JSONField(name = "1")
	public void setBagType(int _v_){
		this.bagType = _v_;
	}

	@JSONField(name = "2")
	public void setSrl(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.srl = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BattleRoleDropBag) {
			BattleRoleDropBag _o_ = (BattleRoleDropBag)_o1_;
			if (this.bagType != _o_.bagType) return false;
			if (!this.srl.equals(_o_.srl)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.bagType);
		_h_ += _h_ * 31 + this.srl.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(bagType).append(",");
		_sb_.append(srl).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
