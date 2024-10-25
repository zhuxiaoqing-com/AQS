package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class ActionIdKey extends warrior.mdb.XBean {
    private int id1;  // id1
    private int id2;  // id2

    ActionIdKey(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public ActionIdKey() {
		this((warrior.mdb.XBean)null);
	}

	public ActionIdKey(ActionIdKey _o_) {
		this(_o_, null);
	}

	ActionIdKey(ActionIdKey _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id1 = _o_.id1;
		this.id2 = _o_.id2;
	}

	public void copyFrom(ActionIdKey _o_) {
        this.id1 = _o_.id1;
        this.id2 = _o_.id2;
	}


	@JSONField(name = "1")
	public int getId1(){
		return this.id1;
	}

	@JSONField(name = "2")
	public int getId2(){
		return this.id2;
	}

	@JSONField(name = "1")
	public void setId1(int _v_){
		this.id1 = _v_;
	}

	@JSONField(name = "2")
	public void setId2(int _v_){
		this.id2 = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ActionIdKey) {
			ActionIdKey _o_ = (ActionIdKey)_o1_;
			if (this.id1 != _o_.id1) return false;
			if (this.id2 != _o_.id2) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id1);
		_h_ += _h_ * 31 + (int)(this.id2);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id1).append(",");
		_sb_.append(id2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
