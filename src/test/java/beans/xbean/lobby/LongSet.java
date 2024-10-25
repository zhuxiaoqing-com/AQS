package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LongSet extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<Long> longSet;  // longset

    LongSet(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.longSet = new java.util.concurrent.CopyOnWriteArraySet<Long>();
	}

	public LongSet() {
		this((warrior.mdb.XBean)null);
	}

	public LongSet(LongSet _o_) {
		this(_o_, null);
	}

	LongSet(LongSet _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.longSet = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.longSet.forEach(_v_ -> this.longSet.add(_v_));
	}

	public void copyFrom(LongSet _o_) {
        this.longSet = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.longSet.forEach(_v_ -> this.longSet.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getLongSet(){
		return this.longSet;
	}

	@JSONField(name = "1")
	public void setLongSet(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.longSet = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LongSet) {
			LongSet _o_ = (LongSet)_o1_;
			if (!this.longSet.equals(_o_.longSet)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.longSet.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(longSet).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
