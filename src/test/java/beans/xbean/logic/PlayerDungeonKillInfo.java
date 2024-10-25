package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerDungeonKillInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, PlayerKillRoleTimeInfo> dungeonKillMap; 

    PlayerDungeonKillInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dungeonKillMap = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerKillRoleTimeInfo>();
	}

	public PlayerDungeonKillInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerDungeonKillInfo(PlayerDungeonKillInfo _o_) {
		this(_o_, null);
	}

	PlayerDungeonKillInfo(PlayerDungeonKillInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.dungeonKillMap = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerKillRoleTimeInfo>();
		_o_.dungeonKillMap.forEach((_k_, _v_) -> this.dungeonKillMap.put(_k_, new PlayerKillRoleTimeInfo(_v_)));
	}

	public void copyFrom(PlayerDungeonKillInfo _o_) {
        this.dungeonKillMap = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerKillRoleTimeInfo>();
		_o_.dungeonKillMap.forEach((_k_, _v_) -> this.dungeonKillMap.put(_k_, new PlayerKillRoleTimeInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, PlayerKillRoleTimeInfo> getDungeonKillMap(){
		return this.dungeonKillMap;
	}

	@JSONField(name = "1")
	public void setDungeonKillMap(java.util.concurrent.ConcurrentHashMap<Integer, PlayerKillRoleTimeInfo> _v_){
		this.dungeonKillMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerDungeonKillInfo) {
			PlayerDungeonKillInfo _o_ = (PlayerDungeonKillInfo)_o1_;
			if (!this.dungeonKillMap.equals(_o_.dungeonKillMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.dungeonKillMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(dungeonKillMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
