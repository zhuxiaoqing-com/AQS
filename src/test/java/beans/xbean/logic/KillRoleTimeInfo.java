package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class KillRoleTimeInfo extends warrior.mdb.XBean {
    private long killId; 
    private long lastRefreshTime; 
    private int killCount; 

    KillRoleTimeInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public KillRoleTimeInfo() {
		this((warrior.mdb.XBean)null);
	}

	public KillRoleTimeInfo(KillRoleTimeInfo _o_) {
		this(_o_, null);
	}

	KillRoleTimeInfo(KillRoleTimeInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.killId = _o_.killId;
		this.lastRefreshTime = _o_.lastRefreshTime;
		this.killCount = _o_.killCount;
	}

	public void copyFrom(KillRoleTimeInfo _o_) {
        this.killId = _o_.killId;
        this.lastRefreshTime = _o_.lastRefreshTime;
        this.killCount = _o_.killCount;
	}


	@JSONField(name = "1")
	public long getKillId(){
		return this.killId;
	}

	@JSONField(name = "2")
	public long getLastRefreshTime(){
		return this.lastRefreshTime;
	}

	@JSONField(name = "3")
	public int getKillCount(){
		return this.killCount;
	}

	@JSONField(name = "1")
	public void setKillId(long _v_){
		this.killId = _v_;
	}

	@JSONField(name = "2")
	public void setLastRefreshTime(long _v_){
		this.lastRefreshTime = _v_;
	}

	@JSONField(name = "3")
	public void setKillCount(int _v_){
		this.killCount = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof KillRoleTimeInfo) {
			KillRoleTimeInfo _o_ = (KillRoleTimeInfo)_o1_;
			if (this.killId != _o_.killId) return false;
			if (this.lastRefreshTime != _o_.lastRefreshTime) return false;
			if (this.killCount != _o_.killCount) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(killId ^ (killId >>> 32));
		_h_ += _h_ * 31 + (int)(lastRefreshTime ^ (lastRefreshTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.killCount);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(killId).append(",");
		_sb_.append(lastRefreshTime).append(",");
		_sb_.append(killCount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
