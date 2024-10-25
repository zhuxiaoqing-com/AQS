package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerPvpBattle extends warrior.mdb.XBean {
    private int currScore;  // 当前积分
    private int realTimeCurrScore;  // 实时当前积分
    private long dayRefresh;  // 日期更新时间
    private java.util.concurrent.CopyOnWriteArraySet<Integer> received;  // 领取过的奖励
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> historyGradeMap;  // 历史领取过的奖励档位
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> talentScoreHistory;  // 各类职业获取的历史演习积分

    PlayerPvpBattle(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.historyGradeMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.talentScoreHistory = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerPvpBattle() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerPvpBattle(PlayerPvpBattle _o_) {
		this(_o_, null);
	}

	PlayerPvpBattle(PlayerPvpBattle _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.currScore = _o_.currScore;
		this.realTimeCurrScore = _o_.realTimeCurrScore;
		this.dayRefresh = _o_.dayRefresh;
		this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.received.forEach(_v_ -> this.received.add(_v_));
		this.historyGradeMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.historyGradeMap.forEach((_k_, _v_) -> this.historyGradeMap.put(_k_, _v_));
		this.talentScoreHistory = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.talentScoreHistory.forEach((_k_, _v_) -> this.talentScoreHistory.put(_k_, _v_));
	}

	public void copyFrom(PlayerPvpBattle _o_) {
        this.currScore = _o_.currScore;
        this.realTimeCurrScore = _o_.realTimeCurrScore;
        this.dayRefresh = _o_.dayRefresh;
        this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.received.forEach(_v_ -> this.received.add(_v_));
        this.historyGradeMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.historyGradeMap.forEach((_k_, _v_) -> this.historyGradeMap.put(_k_, _v_));
        this.talentScoreHistory = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.talentScoreHistory.forEach((_k_, _v_) -> this.talentScoreHistory.put(_k_, _v_));
	}


	@JSONField(name = "2")
	public int getCurrScore(){
		return this.currScore;
	}

	@JSONField(name = "7")
	public int getRealTimeCurrScore(){
		return this.realTimeCurrScore;
	}

	@JSONField(name = "3")
	public long getDayRefresh(){
		return this.dayRefresh;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getReceived(){
		return this.received;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getHistoryGradeMap(){
		return this.historyGradeMap;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTalentScoreHistory(){
		return this.talentScoreHistory;
	}

	@JSONField(name = "2")
	public void setCurrScore(int _v_){
		this.currScore = _v_;
	}

	@JSONField(name = "7")
	public void setRealTimeCurrScore(int _v_){
		this.realTimeCurrScore = _v_;
	}

	@JSONField(name = "3")
	public void setDayRefresh(long _v_){
		this.dayRefresh = _v_;
	}

	@JSONField(name = "4")
	public void setReceived(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.received = _v_;
	}

	@JSONField(name = "5")
	public void setHistoryGradeMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.historyGradeMap = _v_;
	}

	@JSONField(name = "6")
	public void setTalentScoreHistory(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.talentScoreHistory = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerPvpBattle) {
			PlayerPvpBattle _o_ = (PlayerPvpBattle)_o1_;
			if (this.currScore != _o_.currScore) return false;
			if (this.realTimeCurrScore != _o_.realTimeCurrScore) return false;
			if (this.dayRefresh != _o_.dayRefresh) return false;
			if (!this.received.equals(_o_.received)) return false;
			if (!this.historyGradeMap.equals(_o_.historyGradeMap)) return false;
			if (!this.talentScoreHistory.equals(_o_.talentScoreHistory)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.currScore);
		_h_ += _h_ * 31 + (int)(this.realTimeCurrScore);
		_h_ += _h_ * 31 + (int)(dayRefresh ^ (dayRefresh >>> 32));
		_h_ += _h_ * 31 + this.received.hashCode();
		_h_ += _h_ * 31 + this.historyGradeMap.hashCode();
		_h_ += _h_ * 31 + this.talentScoreHistory.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(currScore).append(",");
		_sb_.append(realTimeCurrScore).append(",");
		_sb_.append(dayRefresh).append(",");
		_sb_.append(received).append(",");
		_sb_.append(historyGradeMap).append(",");
		_sb_.append(talentScoreHistory).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
