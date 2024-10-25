package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class WildNodeRecord extends warrior.mdb.XBean {
    private int id;  // id
    private java.util.concurrent.CopyOnWriteArrayList<String> param;  // 参数
    private long time;  // 创建时间

    WildNodeRecord(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.param = new java.util.concurrent.CopyOnWriteArrayList<String>();
	}

	public WildNodeRecord() {
		this((warrior.mdb.XBean)null);
	}

	public WildNodeRecord(WildNodeRecord _o_) {
		this(_o_, null);
	}

	WildNodeRecord(WildNodeRecord _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.param = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.param.forEach(_v_ -> this.param.add(_v_));
		this.time = _o_.time;
	}

	public void copyFrom(WildNodeRecord _o_) {
        this.id = _o_.id;
        this.param = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.param.forEach(_v_ -> this.param.add(_v_));
        this.time = _o_.time;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<String> getParam(){
		return this.param;
	}

	@JSONField(name = "3")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setParam(java.util.concurrent.CopyOnWriteArrayList<String> _v_){
		this.param = _v_;
	}

	@JSONField(name = "3")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WildNodeRecord) {
			WildNodeRecord _o_ = (WildNodeRecord)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.param.equals(_o_.param)) return false;
			if (this.time != _o_.time) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + this.param.hashCode();
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(param).append(",");
		_sb_.append(time).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
