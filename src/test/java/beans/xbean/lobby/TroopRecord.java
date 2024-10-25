package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class TroopRecord extends warrior.mdb.XBean {
    private long time; 
    private int cfgId; 
    private java.util.concurrent.CopyOnWriteArrayList<String> contentParams; 

    TroopRecord(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.contentParams = new java.util.concurrent.CopyOnWriteArrayList<String>();
	}

	public TroopRecord() {
		this((warrior.mdb.XBean)null);
	}

	public TroopRecord(TroopRecord _o_) {
		this(_o_, null);
	}

	TroopRecord(TroopRecord _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.time = _o_.time;
		this.cfgId = _o_.cfgId;
		this.contentParams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.contentParams.forEach(_v_ -> this.contentParams.add(_v_));
	}

	public void copyFrom(TroopRecord _o_) {
        this.time = _o_.time;
        this.cfgId = _o_.cfgId;
        this.contentParams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.contentParams.forEach(_v_ -> this.contentParams.add(_v_));
	}


	@JSONField(name = "1")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "2")
	public int getCfgId(){
		return this.cfgId;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<String> getContentParams(){
		return this.contentParams;
	}

	@JSONField(name = "1")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@JSONField(name = "2")
	public void setCfgId(int _v_){
		this.cfgId = _v_;
	}

	@JSONField(name = "3")
	public void setContentParams(java.util.concurrent.CopyOnWriteArrayList<String> _v_){
		this.contentParams = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TroopRecord) {
			TroopRecord _o_ = (TroopRecord)_o1_;
			if (this.time != _o_.time) return false;
			if (this.cfgId != _o_.cfgId) return false;
			if (!this.contentParams.equals(_o_.contentParams)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		_h_ += _h_ * 31 + (int)(this.cfgId);
		_h_ += _h_ * 31 + this.contentParams.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(time).append(",");
		_sb_.append(cfgId).append(",");
		_sb_.append(contentParams).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
