package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CFWPlayerLog extends warrior.mdb.XBean {
    private long playerId; 
    private int score; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dataMap; 
    private int serverId; 
    private int rank; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> reward;  // 军功奖励
    private boolean taken; 
    private xbean.global.PlayerBaseInfo baseInfo; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> rankReward;  // 排名奖励

    CFWPlayerLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.reward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.baseInfo = new PlayerBaseInfo(this);
		this.rankReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public CFWPlayerLog() {
		this((warrior.mdb.XBean)null);
	}

	public CFWPlayerLog(CFWPlayerLog _o_) {
		this(_o_, null);
	}

	CFWPlayerLog(CFWPlayerLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.score = _o_.score;
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
		this.serverId = _o_.serverId;
		this.rank = _o_.rank;
		this.reward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.reward.forEach((_k_, _v_) -> this.reward.put(_k_, _v_));
		this.taken = _o_.taken;
		this.baseInfo = new PlayerBaseInfo(_o_.baseInfo, this);
		this.rankReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.rankReward.forEach((_k_, _v_) -> this.rankReward.put(_k_, _v_));
	}

	public void copyFrom(CFWPlayerLog _o_) {
        this.playerId = _o_.playerId;
        this.score = _o_.score;
        this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
        this.serverId = _o_.serverId;
        this.rank = _o_.rank;
        this.reward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.reward.forEach((_k_, _v_) -> this.reward.put(_k_, _v_));
        this.taken = _o_.taken;
        this.baseInfo = new PlayerBaseInfo(_o_.baseInfo, this);
        this.rankReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.rankReward.forEach((_k_, _v_) -> this.rankReward.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "3")
	public int getScore(){
		return this.score;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDataMap(){
		return this.dataMap;
	}

	@JSONField(name = "5")
	public int getServerId(){
		return this.serverId;
	}

	@JSONField(name = "6")
	public int getRank(){
		return this.rank;
	}

	@JSONField(name = "7")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getReward(){
		return this.reward;
	}

	@JSONField(name = "8")
	public boolean getTaken(){
		return this.taken;
	}

	@JSONField(name = "9")
	public xbean.global.PlayerBaseInfo getBaseInfo(){
		return this.baseInfo;
	}

	@JSONField(name = "10")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getRankReward(){
		return this.rankReward;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "3")
	public void setScore(int _v_){
		this.score = _v_;
	}

	@JSONField(name = "4")
	public void setDataMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dataMap = _v_;
	}

	@JSONField(name = "5")
	public void setServerId(int _v_){
		this.serverId = _v_;
	}

	@JSONField(name = "6")
	public void setRank(int _v_){
		this.rank = _v_;
	}

	@JSONField(name = "7")
	public void setReward(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.reward = _v_;
	}

	@JSONField(name = "8")
	public void setTaken(boolean _v_){
		this.taken = _v_;
	}

	@JSONField(name = "9")
	public void setBaseInfo(xbean.global.PlayerBaseInfo _v_){
		this.baseInfo = _v_;
	}

	@JSONField(name = "10")
	public void setRankReward(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.rankReward = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFWPlayerLog) {
			CFWPlayerLog _o_ = (CFWPlayerLog)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (this.score != _o_.score) return false;
			if (!this.dataMap.equals(_o_.dataMap)) return false;
			if (this.serverId != _o_.serverId) return false;
			if (this.rank != _o_.rank) return false;
			if (!this.reward.equals(_o_.reward)) return false;
			if (this.taken != _o_.taken) return false;
			if (!this.baseInfo.equals(_o_.baseInfo)) return false;
			if (!this.rankReward.equals(_o_.rankReward)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + (int)(this.score);
		_h_ += _h_ * 31 + this.dataMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.serverId);
		_h_ += _h_ * 31 + (int)(this.rank);
		_h_ += _h_ * 31 + this.reward.hashCode();
		_h_ += _h_ * 31 + (this.taken ? 1231 : 1237);
		_h_ += _h_ * 31 + this.baseInfo.hashCode();
		_h_ += _h_ * 31 + this.rankReward.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append(score).append(",");
		_sb_.append(dataMap).append(",");
		_sb_.append(serverId).append(",");
		_sb_.append(rank).append(",");
		_sb_.append(reward).append(",");
		_sb_.append(taken).append(",");
		_sb_.append(baseInfo).append(",");
		_sb_.append(rankReward).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
