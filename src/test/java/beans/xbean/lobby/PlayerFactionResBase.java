package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerFactionResBase extends warrior.mdb.XBean {
    private int collectTotalCount; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyReward; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> collectCountMap;  // 用于实时积累奖励的采集物采集次数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> collectRewardCountMap;  // 每种采集物已累计奖励的次数
    private long dailyRefreshTime; 

    PlayerFactionResBase(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dailyReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.collectCountMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.collectRewardCountMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerFactionResBase() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerFactionResBase(PlayerFactionResBase _o_) {
		this(_o_, null);
	}

	PlayerFactionResBase(PlayerFactionResBase _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.collectTotalCount = _o_.collectTotalCount;
		this.dailyReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyReward.forEach((_k_, _v_) -> this.dailyReward.put(_k_, _v_));
		this.collectCountMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectCountMap.forEach((_k_, _v_) -> this.collectCountMap.put(_k_, _v_));
		this.collectRewardCountMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectRewardCountMap.forEach((_k_, _v_) -> this.collectRewardCountMap.put(_k_, _v_));
		this.dailyRefreshTime = _o_.dailyRefreshTime;
	}

	public void copyFrom(PlayerFactionResBase _o_) {
        this.collectTotalCount = _o_.collectTotalCount;
        this.dailyReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyReward.forEach((_k_, _v_) -> this.dailyReward.put(_k_, _v_));
        this.collectCountMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectCountMap.forEach((_k_, _v_) -> this.collectCountMap.put(_k_, _v_));
        this.collectRewardCountMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectRewardCountMap.forEach((_k_, _v_) -> this.collectRewardCountMap.put(_k_, _v_));
        this.dailyRefreshTime = _o_.dailyRefreshTime;
	}


	@JSONField(name = "1")
	public int getCollectTotalCount(){
		return this.collectTotalCount;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyReward(){
		return this.dailyReward;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCollectCountMap(){
		return this.collectCountMap;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCollectRewardCountMap(){
		return this.collectRewardCountMap;
	}

	@JSONField(name = "5")
	public long getDailyRefreshTime(){
		return this.dailyRefreshTime;
	}

	@JSONField(name = "1")
	public void setCollectTotalCount(int _v_){
		this.collectTotalCount = _v_;
	}

	@JSONField(name = "2")
	public void setDailyReward(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyReward = _v_;
	}

	@JSONField(name = "3")
	public void setCollectCountMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.collectCountMap = _v_;
	}

	@JSONField(name = "4")
	public void setCollectRewardCountMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.collectRewardCountMap = _v_;
	}

	@JSONField(name = "5")
	public void setDailyRefreshTime(long _v_){
		this.dailyRefreshTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerFactionResBase) {
			PlayerFactionResBase _o_ = (PlayerFactionResBase)_o1_;
			if (this.collectTotalCount != _o_.collectTotalCount) return false;
			if (!this.dailyReward.equals(_o_.dailyReward)) return false;
			if (!this.collectCountMap.equals(_o_.collectCountMap)) return false;
			if (!this.collectRewardCountMap.equals(_o_.collectRewardCountMap)) return false;
			if (this.dailyRefreshTime != _o_.dailyRefreshTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.collectTotalCount);
		_h_ += _h_ * 31 + this.dailyReward.hashCode();
		_h_ += _h_ * 31 + this.collectCountMap.hashCode();
		_h_ += _h_ * 31 + this.collectRewardCountMap.hashCode();
		_h_ += _h_ * 31 + (int)(dailyRefreshTime ^ (dailyRefreshTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(collectTotalCount).append(",");
		_sb_.append(dailyReward).append(",");
		_sb_.append(collectCountMap).append(",");
		_sb_.append(collectRewardCountMap).append(",");
		_sb_.append(dailyRefreshTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
