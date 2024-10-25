package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBWildFrontLine extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag> flagMap;  // k:野外区域id v:野外区域数据
    private long lastCheckTime;  // 上次检测时间

    LBWildFrontLine(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.flagMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag>();
	}

	public LBWildFrontLine() {
		this((warrior.mdb.XBean)null);
	}

	public LBWildFrontLine(LBWildFrontLine _o_) {
		this(_o_, null);
	}

	LBWildFrontLine(LBWildFrontLine _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.flagMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag>();
		_o_.flagMap.forEach((_k_, _v_) -> this.flagMap.put(_k_, new LBWildFlag(_v_)));
		this.lastCheckTime = _o_.lastCheckTime;
	}

	public void copyFrom(LBWildFrontLine _o_) {
        this.flagMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag>();
		_o_.flagMap.forEach((_k_, _v_) -> this.flagMap.put(_k_, new LBWildFlag(_v_)));
        this.lastCheckTime = _o_.lastCheckTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag> getFlagMap(){
		return this.flagMap;
	}

	@JSONField(name = "2")
	public long getLastCheckTime(){
		return this.lastCheckTime;
	}

	@JSONField(name = "1")
	public void setFlagMap(java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag> _v_){
		this.flagMap = _v_;
	}

	@JSONField(name = "2")
	public void setLastCheckTime(long _v_){
		this.lastCheckTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBWildFrontLine) {
			LBWildFrontLine _o_ = (LBWildFrontLine)_o1_;
			if (!this.flagMap.equals(_o_.flagMap)) return false;
			if (this.lastCheckTime != _o_.lastCheckTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.flagMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastCheckTime ^ (lastCheckTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(flagMap).append(",");
		_sb_.append(lastCheckTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
