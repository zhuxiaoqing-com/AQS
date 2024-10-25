package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensContest extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, GensContestBaseInfo> contestRank;  // 公会争霸排行榜
    private xbean.lobby.GensContestKnockout knockout1;  // 淘汰赛第1轮
    private xbean.lobby.GensContestKnockout knockout2;  // 淘汰赛第2轮
    private long lastRefreshTime;  // 上次公会争霸刷新时间
    private java.util.concurrent.ConcurrentHashMap<Long, RankObj> playerRank;  // 公会争霸个人分数数据
    private xbean.lobby.GensContestKnockout knockout3;  // 淘汰赛第3轮

    GensContest(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.contestRank = new java.util.concurrent.ConcurrentHashMap<Long, GensContestBaseInfo>();
		this.knockout1 = new GensContestKnockout(this);
		this.knockout2 = new GensContestKnockout(this);
		this.playerRank = new java.util.concurrent.ConcurrentHashMap<Long, RankObj>();
		this.knockout3 = new GensContestKnockout(this);
	}

	public GensContest() {
		this((warrior.mdb.XBean)null);
	}

	public GensContest(GensContest _o_) {
		this(_o_, null);
	}

	GensContest(GensContest _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.contestRank = new java.util.concurrent.ConcurrentHashMap<Long, GensContestBaseInfo>();
		_o_.contestRank.forEach((_k_, _v_) -> this.contestRank.put(_k_, new GensContestBaseInfo(_v_)));
		this.knockout1 = new GensContestKnockout(_o_.knockout1, this);
		this.knockout2 = new GensContestKnockout(_o_.knockout2, this);
		this.lastRefreshTime = _o_.lastRefreshTime;
		this.playerRank = new java.util.concurrent.ConcurrentHashMap<Long, RankObj>();
		_o_.playerRank.forEach((_k_, _v_) -> this.playerRank.put(_k_, new RankObj(_v_)));
		this.knockout3 = new GensContestKnockout(_o_.knockout3, this);
	}

	public void copyFrom(GensContest _o_) {
        this.contestRank = new java.util.concurrent.ConcurrentHashMap<Long, GensContestBaseInfo>();
		_o_.contestRank.forEach((_k_, _v_) -> this.contestRank.put(_k_, new GensContestBaseInfo(_v_)));
        this.knockout1 = new GensContestKnockout(_o_.knockout1, this);
        this.knockout2 = new GensContestKnockout(_o_.knockout2, this);
        this.lastRefreshTime = _o_.lastRefreshTime;
        this.playerRank = new java.util.concurrent.ConcurrentHashMap<Long, RankObj>();
		_o_.playerRank.forEach((_k_, _v_) -> this.playerRank.put(_k_, new RankObj(_v_)));
        this.knockout3 = new GensContestKnockout(_o_.knockout3, this);
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, GensContestBaseInfo> getContestRank(){
		return this.contestRank;
	}

	@JSONField(name = "2")
	public xbean.lobby.GensContestKnockout getKnockout1(){
		return this.knockout1;
	}

	@JSONField(name = "3")
	public xbean.lobby.GensContestKnockout getKnockout2(){
		return this.knockout2;
	}

	@JSONField(name = "4")
	public long getLastRefreshTime(){
		return this.lastRefreshTime;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Long, RankObj> getPlayerRank(){
		return this.playerRank;
	}

	@JSONField(name = "6")
	public xbean.lobby.GensContestKnockout getKnockout3(){
		return this.knockout3;
	}

	@JSONField(name = "1")
	public void setContestRank(java.util.concurrent.ConcurrentHashMap<Long, GensContestBaseInfo> _v_){
		this.contestRank = _v_;
	}

	@JSONField(name = "2")
	public void setKnockout1(xbean.lobby.GensContestKnockout _v_){
		this.knockout1 = _v_;
	}

	@JSONField(name = "3")
	public void setKnockout2(xbean.lobby.GensContestKnockout _v_){
		this.knockout2 = _v_;
	}

	@JSONField(name = "4")
	public void setLastRefreshTime(long _v_){
		this.lastRefreshTime = _v_;
	}

	@JSONField(name = "5")
	public void setPlayerRank(java.util.concurrent.ConcurrentHashMap<Long, RankObj> _v_){
		this.playerRank = _v_;
	}

	@JSONField(name = "6")
	public void setKnockout3(xbean.lobby.GensContestKnockout _v_){
		this.knockout3 = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensContest) {
			GensContest _o_ = (GensContest)_o1_;
			if (!this.contestRank.equals(_o_.contestRank)) return false;
			if (!this.knockout1.equals(_o_.knockout1)) return false;
			if (!this.knockout2.equals(_o_.knockout2)) return false;
			if (this.lastRefreshTime != _o_.lastRefreshTime) return false;
			if (!this.playerRank.equals(_o_.playerRank)) return false;
			if (!this.knockout3.equals(_o_.knockout3)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.contestRank.hashCode();
		_h_ += _h_ * 31 + this.knockout1.hashCode();
		_h_ += _h_ * 31 + this.knockout2.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefreshTime ^ (lastRefreshTime >>> 32));
		_h_ += _h_ * 31 + this.playerRank.hashCode();
		_h_ += _h_ * 31 + this.knockout3.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(contestRank).append(",");
		_sb_.append(knockout1).append(",");
		_sb_.append(knockout2).append(",");
		_sb_.append(lastRefreshTime).append(",");
		_sb_.append(playerRank).append(",");
		_sb_.append(knockout3).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
