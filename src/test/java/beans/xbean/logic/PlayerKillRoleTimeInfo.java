package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerKillRoleTimeInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, KillRoleTimeInfo> roleKillMap; 

    PlayerKillRoleTimeInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.roleKillMap = new java.util.concurrent.ConcurrentHashMap<Long, KillRoleTimeInfo>();
	}

	public PlayerKillRoleTimeInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerKillRoleTimeInfo(PlayerKillRoleTimeInfo _o_) {
		this(_o_, null);
	}

	PlayerKillRoleTimeInfo(PlayerKillRoleTimeInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.roleKillMap = new java.util.concurrent.ConcurrentHashMap<Long, KillRoleTimeInfo>();
		_o_.roleKillMap.forEach((_k_, _v_) -> this.roleKillMap.put(_k_, new KillRoleTimeInfo(_v_)));
	}

	public void copyFrom(PlayerKillRoleTimeInfo _o_) {
        this.roleKillMap = new java.util.concurrent.ConcurrentHashMap<Long, KillRoleTimeInfo>();
		_o_.roleKillMap.forEach((_k_, _v_) -> this.roleKillMap.put(_k_, new KillRoleTimeInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, KillRoleTimeInfo> getRoleKillMap(){
		return this.roleKillMap;
	}

	@JSONField(name = "1")
	public void setRoleKillMap(java.util.concurrent.ConcurrentHashMap<Long, KillRoleTimeInfo> _v_){
		this.roleKillMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerKillRoleTimeInfo) {
			PlayerKillRoleTimeInfo _o_ = (PlayerKillRoleTimeInfo)_o1_;
			if (!this.roleKillMap.equals(_o_.roleKillMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.roleKillMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(roleKillMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
