package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LbLogicalServerInfo extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<Integer> lbIds;  // 物理服务器里面包含了哪些逻辑服务器

    LbLogicalServerInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.lbIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public LbLogicalServerInfo() {
		this((warrior.mdb.XBean)null);
	}

	public LbLogicalServerInfo(LbLogicalServerInfo _o_) {
		this(_o_, null);
	}

	LbLogicalServerInfo(LbLogicalServerInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.lbIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.lbIds.forEach(_v_ -> this.lbIds.add(_v_));
	}

	public void copyFrom(LbLogicalServerInfo _o_) {
        this.lbIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.lbIds.forEach(_v_ -> this.lbIds.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getLbIds(){
		return this.lbIds;
	}

	@JSONField(name = "1")
	public void setLbIds(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.lbIds = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LbLogicalServerInfo) {
			LbLogicalServerInfo _o_ = (LbLogicalServerInfo)_o1_;
			if (!this.lbIds.equals(_o_.lbIds)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.lbIds.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(lbIds).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
