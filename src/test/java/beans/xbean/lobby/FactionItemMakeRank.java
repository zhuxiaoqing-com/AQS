package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionItemMakeRank extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRankMap> recordList;  // 领取日志数据 最多7条 每天一条
    private java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank> rank3;  // 三天排行榜
    private java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank> rank7;  // 7天排行榜
    private long lastRefresh;  // 上次刷新时间
    private java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank> rank1;  // 1天排行榜

    FactionItemMakeRank(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.recordList = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRankMap>();
		this.rank3 = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank>();
		this.rank7 = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank>();
		this.rank1 = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank>();
	}

	public FactionItemMakeRank() {
		this((warrior.mdb.XBean)null);
	}

	public FactionItemMakeRank(FactionItemMakeRank _o_) {
		this(_o_, null);
	}

	FactionItemMakeRank(FactionItemMakeRank _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.recordList = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRankMap>();
		_o_.recordList.forEach(_v_ -> this.recordList.add(new PlayerMakeRankMap(_v_)));
		this.rank3 = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank>();
		_o_.rank3.forEach(_v_ -> this.rank3.add(new PlayerMakeRank(_v_)));
		this.rank7 = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank>();
		_o_.rank7.forEach(_v_ -> this.rank7.add(new PlayerMakeRank(_v_)));
		this.lastRefresh = _o_.lastRefresh;
		this.rank1 = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank>();
		_o_.rank1.forEach(_v_ -> this.rank1.add(new PlayerMakeRank(_v_)));
	}

	public void copyFrom(FactionItemMakeRank _o_) {
        this.recordList = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRankMap>();
		_o_.recordList.forEach(_v_ -> this.recordList.add(new PlayerMakeRankMap(_v_)));
        this.rank3 = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank>();
		_o_.rank3.forEach(_v_ -> this.rank3.add(new PlayerMakeRank(_v_)));
        this.rank7 = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank>();
		_o_.rank7.forEach(_v_ -> this.rank7.add(new PlayerMakeRank(_v_)));
        this.lastRefresh = _o_.lastRefresh;
        this.rank1 = new java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank>();
		_o_.rank1.forEach(_v_ -> this.rank1.add(new PlayerMakeRank(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRankMap> getRecordList(){
		return this.recordList;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank> getRank3(){
		return this.rank3;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank> getRank7(){
		return this.rank7;
	}

	@JSONField(name = "4")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "5")
	public java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank> getRank1(){
		return this.rank1;
	}

	@JSONField(name = "1")
	public void setRecordList(java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRankMap> _v_){
		this.recordList = _v_;
	}

	@JSONField(name = "2")
	public void setRank3(java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank> _v_){
		this.rank3 = _v_;
	}

	@JSONField(name = "3")
	public void setRank7(java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank> _v_){
		this.rank7 = _v_;
	}

	@JSONField(name = "4")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@JSONField(name = "5")
	public void setRank1(java.util.concurrent.CopyOnWriteArrayList<PlayerMakeRank> _v_){
		this.rank1 = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionItemMakeRank) {
			FactionItemMakeRank _o_ = (FactionItemMakeRank)_o1_;
			if (!this.recordList.equals(_o_.recordList)) return false;
			if (!this.rank3.equals(_o_.rank3)) return false;
			if (!this.rank7.equals(_o_.rank7)) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			if (!this.rank1.equals(_o_.rank1)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.recordList.hashCode();
		_h_ += _h_ * 31 + this.rank3.hashCode();
		_h_ += _h_ * 31 + this.rank7.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		_h_ += _h_ * 31 + this.rank1.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(recordList).append(",");
		_sb_.append(rank3).append(",");
		_sb_.append(rank7).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(rank1).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
