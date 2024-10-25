package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AllianceIds extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<Long> ids; 

    AllianceIds(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.ids = new java.util.concurrent.CopyOnWriteArrayList<Long>();
	}

	public AllianceIds() {
		this((warrior.mdb.XBean)null);
	}

	public AllianceIds(AllianceIds _o_) {
		this(_o_, null);
	}

	AllianceIds(AllianceIds _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.ids = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.ids.forEach(_v_ -> this.ids.add(_v_));
	}

	public void copyFrom(AllianceIds _o_) {
        this.ids = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.ids.forEach(_v_ -> this.ids.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getIds(){
		return this.ids;
	}

	@JSONField(name = "1")
	public void setIds(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.ids = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AllianceIds) {
			AllianceIds _o_ = (AllianceIds)_o1_;
			if (!this.ids.equals(_o_.ids)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.ids.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(ids).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
