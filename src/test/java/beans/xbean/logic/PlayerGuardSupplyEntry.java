package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerGuardSupplyEntry extends warrior.mdb.XBean {
    private int itemId; 
    private int count; 

    PlayerGuardSupplyEntry(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public PlayerGuardSupplyEntry() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerGuardSupplyEntry(PlayerGuardSupplyEntry _o_) {
		this(_o_, null);
	}

	PlayerGuardSupplyEntry(PlayerGuardSupplyEntry _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.itemId = _o_.itemId;
		this.count = _o_.count;
	}

	public void copyFrom(PlayerGuardSupplyEntry _o_) {
        this.itemId = _o_.itemId;
        this.count = _o_.count;
	}


	@JSONField(name = "1")
	public int getItemId(){
		return this.itemId;
	}

	@JSONField(name = "2")
	public int getCount(){
		return this.count;
	}

	@JSONField(name = "1")
	public void setItemId(int _v_){
		this.itemId = _v_;
	}

	@JSONField(name = "2")
	public void setCount(int _v_){
		this.count = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerGuardSupplyEntry) {
			PlayerGuardSupplyEntry _o_ = (PlayerGuardSupplyEntry)_o1_;
			if (this.itemId != _o_.itemId) return false;
			if (this.count != _o_.count) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.itemId);
		_h_ += _h_ * 31 + (int)(this.count);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(itemId).append(",");
		_sb_.append(count).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
