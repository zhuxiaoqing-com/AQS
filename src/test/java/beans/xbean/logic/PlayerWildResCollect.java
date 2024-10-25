package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerWildResCollect extends warrior.mdb.XBean {
    private xbean.logic.LGActLogicInfo actInfo;  // 活动数据
    private java.util.concurrent.ConcurrentHashMap<Integer, PlayerDungeonMirror> mirrorMap;  // 副本镜像map key:dungeonId value:mirror
    private long lastRefreshTime;  // 上一次刷新时间

    PlayerWildResCollect(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actInfo = new LGActLogicInfo(this);
		this.mirrorMap = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerDungeonMirror>();
	}

	public PlayerWildResCollect() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerWildResCollect(PlayerWildResCollect _o_) {
		this(_o_, null);
	}

	PlayerWildResCollect(PlayerWildResCollect _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
		this.mirrorMap = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerDungeonMirror>();
		_o_.mirrorMap.forEach((_k_, _v_) -> this.mirrorMap.put(_k_, new PlayerDungeonMirror(_v_)));
		this.lastRefreshTime = _o_.lastRefreshTime;
	}

	public void copyFrom(PlayerWildResCollect _o_) {
        this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
        this.mirrorMap = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerDungeonMirror>();
		_o_.mirrorMap.forEach((_k_, _v_) -> this.mirrorMap.put(_k_, new PlayerDungeonMirror(_v_)));
        this.lastRefreshTime = _o_.lastRefreshTime;
	}


	@JSONField(name = "1")
	public xbean.logic.LGActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, PlayerDungeonMirror> getMirrorMap(){
		return this.mirrorMap;
	}

	@JSONField(name = "3")
	public long getLastRefreshTime(){
		return this.lastRefreshTime;
	}

	@JSONField(name = "1")
	public void setActInfo(xbean.logic.LGActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@JSONField(name = "2")
	public void setMirrorMap(java.util.concurrent.ConcurrentHashMap<Integer, PlayerDungeonMirror> _v_){
		this.mirrorMap = _v_;
	}

	@JSONField(name = "3")
	public void setLastRefreshTime(long _v_){
		this.lastRefreshTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerWildResCollect) {
			PlayerWildResCollect _o_ = (PlayerWildResCollect)_o1_;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			if (!this.mirrorMap.equals(_o_.mirrorMap)) return false;
			if (this.lastRefreshTime != _o_.lastRefreshTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		_h_ += _h_ * 31 + this.mirrorMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefreshTime ^ (lastRefreshTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actInfo).append(",");
		_sb_.append(mirrorMap).append(",");
		_sb_.append(lastRefreshTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
