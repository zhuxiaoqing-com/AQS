package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class RankInfo extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<Long> rank;  // 排行
    private java.util.concurrent.ConcurrentHashMap<Long, RankObj> rankObjs;  // 排行分数; key：id value：RankObj
    private long lastFresh;  // 上次刷新
    private int freshType;  // 刷新类型

    RankInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.rank = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.rankObjs = new java.util.concurrent.ConcurrentHashMap<Long, RankObj>();
	}

	public RankInfo() {
		this((warrior.mdb.XBean)null);
	}

	public RankInfo(RankInfo _o_) {
		this(_o_, null);
	}

	RankInfo(RankInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.rank = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.rank.forEach(_v_ -> this.rank.add(_v_));
		this.rankObjs = new java.util.concurrent.ConcurrentHashMap<Long, RankObj>();
		_o_.rankObjs.forEach((_k_, _v_) -> this.rankObjs.put(_k_, new RankObj(_v_)));
		this.lastFresh = _o_.lastFresh;
		this.freshType = _o_.freshType;
	}

	public void copyFrom(RankInfo _o_) {
        this.rank = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.rank.forEach(_v_ -> this.rank.add(_v_));
        this.rankObjs = new java.util.concurrent.ConcurrentHashMap<Long, RankObj>();
		_o_.rankObjs.forEach((_k_, _v_) -> this.rankObjs.put(_k_, new RankObj(_v_)));
        this.lastFresh = _o_.lastFresh;
        this.freshType = _o_.freshType;
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getRank(){
		return this.rank;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, RankObj> getRankObjs(){
		return this.rankObjs;
	}

	@JSONField(name = "3")
	public long getLastFresh(){
		return this.lastFresh;
	}

	@JSONField(name = "4")
	public int getFreshType(){
		return this.freshType;
	}

	@JSONField(name = "1")
	public void setRank(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.rank = _v_;
	}

	@JSONField(name = "2")
	public void setRankObjs(java.util.concurrent.ConcurrentHashMap<Long, RankObj> _v_){
		this.rankObjs = _v_;
	}

	@JSONField(name = "3")
	public void setLastFresh(long _v_){
		this.lastFresh = _v_;
	}

	@JSONField(name = "4")
	public void setFreshType(int _v_){
		this.freshType = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RankInfo) {
			RankInfo _o_ = (RankInfo)_o1_;
			if (!this.rank.equals(_o_.rank)) return false;
			if (!this.rankObjs.equals(_o_.rankObjs)) return false;
			if (this.lastFresh != _o_.lastFresh) return false;
			if (this.freshType != _o_.freshType) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.rank.hashCode();
		_h_ += _h_ * 31 + this.rankObjs.hashCode();
		_h_ += _h_ * 31 + (int)(lastFresh ^ (lastFresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.freshType);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(rank).append(",");
		_sb_.append(rankObjs).append(",");
		_sb_.append(lastFresh).append(",");
		_sb_.append(freshType).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
