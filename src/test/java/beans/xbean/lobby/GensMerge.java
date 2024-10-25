package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensMerge extends warrior.mdb.XBean {
    private long gid; 
    private long createTime;  // 合并创建时间

    GensMerge(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public GensMerge() {
		this((warrior.mdb.XBean)null);
	}

	public GensMerge(GensMerge _o_) {
		this(_o_, null);
	}

	GensMerge(GensMerge _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gid = _o_.gid;
		this.createTime = _o_.createTime;
	}

	public void copyFrom(GensMerge _o_) {
        this.gid = _o_.gid;
        this.createTime = _o_.createTime;
	}


	@JSONField(name = "1")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "2")
	public long getCreateTime(){
		return this.createTime;
	}

	@JSONField(name = "1")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "2")
	public void setCreateTime(long _v_){
		this.createTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensMerge) {
			GensMerge _o_ = (GensMerge)_o1_;
			if (this.gid != _o_.gid) return false;
			if (this.createTime != _o_.createTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + (int)(createTime ^ (createTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gid).append(",");
		_sb_.append(createTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
