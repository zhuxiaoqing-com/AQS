package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionArmamentDonate extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, AreaArmamentDonate> areaDonate;  // 区域战备点捐献数据 flagId->AreaArmamentDonate
    private long lastCheckTime;  // 上次检测时间

    FactionArmamentDonate(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.areaDonate = new java.util.concurrent.ConcurrentHashMap<Integer, AreaArmamentDonate>();
	}

	public FactionArmamentDonate() {
		this((warrior.mdb.XBean)null);
	}

	public FactionArmamentDonate(FactionArmamentDonate _o_) {
		this(_o_, null);
	}

	FactionArmamentDonate(FactionArmamentDonate _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaDonate = new java.util.concurrent.ConcurrentHashMap<Integer, AreaArmamentDonate>();
		_o_.areaDonate.forEach((_k_, _v_) -> this.areaDonate.put(_k_, new AreaArmamentDonate(_v_)));
		this.lastCheckTime = _o_.lastCheckTime;
	}

	public void copyFrom(FactionArmamentDonate _o_) {
        this.areaDonate = new java.util.concurrent.ConcurrentHashMap<Integer, AreaArmamentDonate>();
		_o_.areaDonate.forEach((_k_, _v_) -> this.areaDonate.put(_k_, new AreaArmamentDonate(_v_)));
        this.lastCheckTime = _o_.lastCheckTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, AreaArmamentDonate> getAreaDonate(){
		return this.areaDonate;
	}

	@JSONField(name = "2")
	public long getLastCheckTime(){
		return this.lastCheckTime;
	}

	@JSONField(name = "1")
	public void setAreaDonate(java.util.concurrent.ConcurrentHashMap<Integer, AreaArmamentDonate> _v_){
		this.areaDonate = _v_;
	}

	@JSONField(name = "2")
	public void setLastCheckTime(long _v_){
		this.lastCheckTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionArmamentDonate) {
			FactionArmamentDonate _o_ = (FactionArmamentDonate)_o1_;
			if (!this.areaDonate.equals(_o_.areaDonate)) return false;
			if (this.lastCheckTime != _o_.lastCheckTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.areaDonate.hashCode();
		_h_ += _h_ * 31 + (int)(lastCheckTime ^ (lastCheckTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaDonate).append(",");
		_sb_.append(lastCheckTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
