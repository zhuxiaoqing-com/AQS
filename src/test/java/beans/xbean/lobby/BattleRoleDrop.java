package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class BattleRoleDrop extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, BattleRoleDropBag> dropBags;  // k:bagType  v：BattleRoleDropBag

    BattleRoleDrop(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dropBags = new java.util.concurrent.ConcurrentHashMap<Integer, BattleRoleDropBag>();
	}

	public BattleRoleDrop() {
		this((warrior.mdb.XBean)null);
	}

	public BattleRoleDrop(BattleRoleDrop _o_) {
		this(_o_, null);
	}

	BattleRoleDrop(BattleRoleDrop _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.dropBags = new java.util.concurrent.ConcurrentHashMap<Integer, BattleRoleDropBag>();
		_o_.dropBags.forEach((_k_, _v_) -> this.dropBags.put(_k_, new BattleRoleDropBag(_v_)));
	}

	public void copyFrom(BattleRoleDrop _o_) {
        this.dropBags = new java.util.concurrent.ConcurrentHashMap<Integer, BattleRoleDropBag>();
		_o_.dropBags.forEach((_k_, _v_) -> this.dropBags.put(_k_, new BattleRoleDropBag(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, BattleRoleDropBag> getDropBags(){
		return this.dropBags;
	}

	@JSONField(name = "1")
	public void setDropBags(java.util.concurrent.ConcurrentHashMap<Integer, BattleRoleDropBag> _v_){
		this.dropBags = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BattleRoleDrop) {
			BattleRoleDrop _o_ = (BattleRoleDrop)_o1_;
			if (!this.dropBags.equals(_o_.dropBags)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.dropBags.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(dropBags).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
