package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CFWTotalLog extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<SingleCFWLog> logs; 

    CFWTotalLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.logs = new java.util.concurrent.CopyOnWriteArrayList<SingleCFWLog>();
	}

	public CFWTotalLog() {
		this((warrior.mdb.XBean)null);
	}

	public CFWTotalLog(CFWTotalLog _o_) {
		this(_o_, null);
	}

	CFWTotalLog(CFWTotalLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.logs = new java.util.concurrent.CopyOnWriteArrayList<SingleCFWLog>();
		_o_.logs.forEach(_v_ -> this.logs.add(new SingleCFWLog(_v_)));
	}

	public void copyFrom(CFWTotalLog _o_) {
        this.logs = new java.util.concurrent.CopyOnWriteArrayList<SingleCFWLog>();
		_o_.logs.forEach(_v_ -> this.logs.add(new SingleCFWLog(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<SingleCFWLog> getLogs(){
		return this.logs;
	}

	@JSONField(name = "1")
	public void setLogs(java.util.concurrent.CopyOnWriteArrayList<SingleCFWLog> _v_){
		this.logs = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFWTotalLog) {
			CFWTotalLog _o_ = (CFWTotalLog)_o1_;
			if (!this.logs.equals(_o_.logs)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.logs.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(logs).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
