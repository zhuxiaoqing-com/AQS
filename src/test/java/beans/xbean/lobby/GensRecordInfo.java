package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensRecordInfo extends warrior.mdb.XBean {
    private int recordId;  // 公会日志id
    private long time;  // 公会日志时间
    private java.util.concurrent.CopyOnWriteArrayList<String> param;  // 公会日志参数

    GensRecordInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.param = new java.util.concurrent.CopyOnWriteArrayList<String>();
	}

	public GensRecordInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensRecordInfo(GensRecordInfo _o_) {
		this(_o_, null);
	}

	GensRecordInfo(GensRecordInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.recordId = _o_.recordId;
		this.time = _o_.time;
		this.param = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.param.forEach(_v_ -> this.param.add(_v_));
	}

	public void copyFrom(GensRecordInfo _o_) {
        this.recordId = _o_.recordId;
        this.time = _o_.time;
        this.param = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.param.forEach(_v_ -> this.param.add(_v_));
	}


	@JSONField(name = "1")
	public int getRecordId(){
		return this.recordId;
	}

	@JSONField(name = "2")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<String> getParam(){
		return this.param;
	}

	@JSONField(name = "1")
	public void setRecordId(int _v_){
		this.recordId = _v_;
	}

	@JSONField(name = "2")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@JSONField(name = "3")
	public void setParam(java.util.concurrent.CopyOnWriteArrayList<String> _v_){
		this.param = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensRecordInfo) {
			GensRecordInfo _o_ = (GensRecordInfo)_o1_;
			if (this.recordId != _o_.recordId) return false;
			if (this.time != _o_.time) return false;
			if (!this.param.equals(_o_.param)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.recordId);
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		_h_ += _h_ * 31 + this.param.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(recordId).append(",");
		_sb_.append(time).append(",");
		_sb_.append(param).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
