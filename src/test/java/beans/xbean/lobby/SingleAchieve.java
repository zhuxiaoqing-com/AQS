package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class SingleAchieve extends warrior.mdb.XBean {
    private int type;  // 类型
    private long pid;  // 完成的用户
    private long time;  // 完成时间

    SingleAchieve(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public SingleAchieve() {
		this((warrior.mdb.XBean)null);
	}

	public SingleAchieve(SingleAchieve _o_) {
		this(_o_, null);
	}

	SingleAchieve(SingleAchieve _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.type = _o_.type;
		this.pid = _o_.pid;
		this.time = _o_.time;
	}

	public void copyFrom(SingleAchieve _o_) {
        this.type = _o_.type;
        this.pid = _o_.pid;
        this.time = _o_.time;
	}


	@JSONField(name = "1")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "2")
	public long getPid(){
		return this.pid;
	}

	@JSONField(name = "3")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "1")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "2")
	public void setPid(long _v_){
		this.pid = _v_;
	}

	@JSONField(name = "3")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SingleAchieve) {
			SingleAchieve _o_ = (SingleAchieve)_o1_;
			if (this.type != _o_.type) return false;
			if (this.pid != _o_.pid) return false;
			if (this.time != _o_.time) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(pid ^ (pid >>> 32));
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(type).append(",");
		_sb_.append(pid).append(",");
		_sb_.append(time).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
