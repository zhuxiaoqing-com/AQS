package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerBuffInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, RoleBuffBean> buffMap; 
    private long lastWeakRefresh; 
    private int weakTriggerCount; 
    private long lastDailyBuffRefreshTime;  // 每日buff刷新时间
    private boolean dailyBuffAttach;  // 每日buff是否已经添加上
    private int deathCount;  // 地图死亡次数

    PlayerBuffInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.buffMap = new java.util.concurrent.ConcurrentHashMap<Integer, RoleBuffBean>();
	}

	public PlayerBuffInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerBuffInfo(PlayerBuffInfo _o_) {
		this(_o_, null);
	}

	PlayerBuffInfo(PlayerBuffInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.buffMap = new java.util.concurrent.ConcurrentHashMap<Integer, RoleBuffBean>();
		_o_.buffMap.forEach((_k_, _v_) -> this.buffMap.put(_k_, new RoleBuffBean(_v_)));
		this.lastWeakRefresh = _o_.lastWeakRefresh;
		this.weakTriggerCount = _o_.weakTriggerCount;
		this.lastDailyBuffRefreshTime = _o_.lastDailyBuffRefreshTime;
		this.dailyBuffAttach = _o_.dailyBuffAttach;
		this.deathCount = _o_.deathCount;
	}

	public void copyFrom(PlayerBuffInfo _o_) {
        this.buffMap = new java.util.concurrent.ConcurrentHashMap<Integer, RoleBuffBean>();
		_o_.buffMap.forEach((_k_, _v_) -> this.buffMap.put(_k_, new RoleBuffBean(_v_)));
        this.lastWeakRefresh = _o_.lastWeakRefresh;
        this.weakTriggerCount = _o_.weakTriggerCount;
        this.lastDailyBuffRefreshTime = _o_.lastDailyBuffRefreshTime;
        this.dailyBuffAttach = _o_.dailyBuffAttach;
        this.deathCount = _o_.deathCount;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, RoleBuffBean> getBuffMap(){
		return this.buffMap;
	}

	@JSONField(name = "2")
	public long getLastWeakRefresh(){
		return this.lastWeakRefresh;
	}

	@JSONField(name = "3")
	public int getWeakTriggerCount(){
		return this.weakTriggerCount;
	}

	@JSONField(name = "4")
	public long getLastDailyBuffRefreshTime(){
		return this.lastDailyBuffRefreshTime;
	}

	@JSONField(name = "5")
	public boolean getDailyBuffAttach(){
		return this.dailyBuffAttach;
	}

	@JSONField(name = "6")
	public int getDeathCount(){
		return this.deathCount;
	}

	@JSONField(name = "1")
	public void setBuffMap(java.util.concurrent.ConcurrentHashMap<Integer, RoleBuffBean> _v_){
		this.buffMap = _v_;
	}

	@JSONField(name = "2")
	public void setLastWeakRefresh(long _v_){
		this.lastWeakRefresh = _v_;
	}

	@JSONField(name = "3")
	public void setWeakTriggerCount(int _v_){
		this.weakTriggerCount = _v_;
	}

	@JSONField(name = "4")
	public void setLastDailyBuffRefreshTime(long _v_){
		this.lastDailyBuffRefreshTime = _v_;
	}

	@JSONField(name = "5")
	public void setDailyBuffAttach(boolean _v_){
		this.dailyBuffAttach = _v_;
	}

	@JSONField(name = "6")
	public void setDeathCount(int _v_){
		this.deathCount = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerBuffInfo) {
			PlayerBuffInfo _o_ = (PlayerBuffInfo)_o1_;
			if (!this.buffMap.equals(_o_.buffMap)) return false;
			if (this.lastWeakRefresh != _o_.lastWeakRefresh) return false;
			if (this.weakTriggerCount != _o_.weakTriggerCount) return false;
			if (this.lastDailyBuffRefreshTime != _o_.lastDailyBuffRefreshTime) return false;
			if (this.dailyBuffAttach != _o_.dailyBuffAttach) return false;
			if (this.deathCount != _o_.deathCount) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.buffMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastWeakRefresh ^ (lastWeakRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.weakTriggerCount);
		_h_ += _h_ * 31 + (int)(lastDailyBuffRefreshTime ^ (lastDailyBuffRefreshTime >>> 32));
		_h_ += _h_ * 31 + (this.dailyBuffAttach ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.deathCount);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(buffMap).append(",");
		_sb_.append(lastWeakRefresh).append(",");
		_sb_.append(weakTriggerCount).append(",");
		_sb_.append(lastDailyBuffRefreshTime).append(",");
		_sb_.append(dailyBuffAttach).append(",");
		_sb_.append(deathCount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
