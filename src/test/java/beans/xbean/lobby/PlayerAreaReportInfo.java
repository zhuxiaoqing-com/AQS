package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerAreaReportInfo extends warrior.mdb.XBean {
    private long playerId;  // 玩家唯一id
    private xbean.lobby.PlayerBaseInfo playerInfo;  // 角色基础数据
    private java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore> scoreMap;  // 战报积分 k->区域id v:分数数据
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> reward;  // 奖励
    private int rank;  // 排名
    private int totalScore;  // 总积分
    private long gensId;  // 公会id
    private int gensRank;  // 公会排名

    PlayerAreaReportInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerInfo = new PlayerBaseInfo(this);
		this.scoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore>();
		this.reward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerAreaReportInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerAreaReportInfo(PlayerAreaReportInfo _o_) {
		this(_o_, null);
	}

	PlayerAreaReportInfo(PlayerAreaReportInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
		this.scoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore>();
		_o_.scoreMap.forEach((_k_, _v_) -> this.scoreMap.put(_k_, new AreaReportScore(_v_)));
		this.reward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.reward.forEach((_k_, _v_) -> this.reward.put(_k_, _v_));
		this.rank = _o_.rank;
		this.totalScore = _o_.totalScore;
		this.gensId = _o_.gensId;
		this.gensRank = _o_.gensRank;
	}

	public void copyFrom(PlayerAreaReportInfo _o_) {
        this.playerId = _o_.playerId;
        this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
        this.scoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore>();
		_o_.scoreMap.forEach((_k_, _v_) -> this.scoreMap.put(_k_, new AreaReportScore(_v_)));
        this.reward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.reward.forEach((_k_, _v_) -> this.reward.put(_k_, _v_));
        this.rank = _o_.rank;
        this.totalScore = _o_.totalScore;
        this.gensId = _o_.gensId;
        this.gensRank = _o_.gensRank;
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "2")
	public xbean.lobby.PlayerBaseInfo getPlayerInfo(){
		return this.playerInfo;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore> getScoreMap(){
		return this.scoreMap;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getReward(){
		return this.reward;
	}

	@JSONField(name = "5")
	public int getRank(){
		return this.rank;
	}

	@JSONField(name = "6")
	public int getTotalScore(){
		return this.totalScore;
	}

	@JSONField(name = "7")
	public long getGensId(){
		return this.gensId;
	}

	@JSONField(name = "8")
	public int getGensRank(){
		return this.gensRank;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "2")
	public void setPlayerInfo(xbean.lobby.PlayerBaseInfo _v_){
		this.playerInfo = _v_;
	}

	@JSONField(name = "3")
	public void setScoreMap(java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore> _v_){
		this.scoreMap = _v_;
	}

	@JSONField(name = "4")
	public void setReward(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.reward = _v_;
	}

	@JSONField(name = "5")
	public void setRank(int _v_){
		this.rank = _v_;
	}

	@JSONField(name = "6")
	public void setTotalScore(int _v_){
		this.totalScore = _v_;
	}

	@JSONField(name = "7")
	public void setGensId(long _v_){
		this.gensId = _v_;
	}

	@JSONField(name = "8")
	public void setGensRank(int _v_){
		this.gensRank = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerAreaReportInfo) {
			PlayerAreaReportInfo _o_ = (PlayerAreaReportInfo)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (!this.playerInfo.equals(_o_.playerInfo)) return false;
			if (!this.scoreMap.equals(_o_.scoreMap)) return false;
			if (!this.reward.equals(_o_.reward)) return false;
			if (this.rank != _o_.rank) return false;
			if (this.totalScore != _o_.totalScore) return false;
			if (this.gensId != _o_.gensId) return false;
			if (this.gensRank != _o_.gensRank) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + this.playerInfo.hashCode();
		_h_ += _h_ * 31 + this.scoreMap.hashCode();
		_h_ += _h_ * 31 + this.reward.hashCode();
		_h_ += _h_ * 31 + (int)(this.rank);
		_h_ += _h_ * 31 + (int)(this.totalScore);
		_h_ += _h_ * 31 + (int)(gensId ^ (gensId >>> 32));
		_h_ += _h_ * 31 + (int)(this.gensRank);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append(playerInfo).append(",");
		_sb_.append(scoreMap).append(",");
		_sb_.append(reward).append(",");
		_sb_.append(rank).append(",");
		_sb_.append(totalScore).append(",");
		_sb_.append(gensId).append(",");
		_sb_.append(gensRank).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
