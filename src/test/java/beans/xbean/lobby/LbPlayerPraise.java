package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LbPlayerPraise extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, LbPraise> praiseMap;  // srl->任务数据
    private long lastRefreshTime;  // 刷新时间
    private java.util.concurrent.CopyOnWriteArrayList<Long> weekPraise;  // 7天的点赞数据
    private int praiseOtherNum;  // 点赞他人数量

    LbPlayerPraise(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.praiseMap = new java.util.concurrent.ConcurrentHashMap<Long, LbPraise>();
		this.weekPraise = new java.util.concurrent.CopyOnWriteArrayList<Long>();
	}

	public LbPlayerPraise() {
		this((warrior.mdb.XBean)null);
	}

	public LbPlayerPraise(LbPlayerPraise _o_) {
		this(_o_, null);
	}

	LbPlayerPraise(LbPlayerPraise _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.praiseMap = new java.util.concurrent.ConcurrentHashMap<Long, LbPraise>();
		_o_.praiseMap.forEach((_k_, _v_) -> this.praiseMap.put(_k_, new LbPraise(_v_)));
		this.lastRefreshTime = _o_.lastRefreshTime;
		this.weekPraise = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekPraise.forEach(_v_ -> this.weekPraise.add(_v_));
		this.praiseOtherNum = _o_.praiseOtherNum;
	}

	public void copyFrom(LbPlayerPraise _o_) {
        this.praiseMap = new java.util.concurrent.ConcurrentHashMap<Long, LbPraise>();
		_o_.praiseMap.forEach((_k_, _v_) -> this.praiseMap.put(_k_, new LbPraise(_v_)));
        this.lastRefreshTime = _o_.lastRefreshTime;
        this.weekPraise = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekPraise.forEach(_v_ -> this.weekPraise.add(_v_));
        this.praiseOtherNum = _o_.praiseOtherNum;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, LbPraise> getPraiseMap(){
		return this.praiseMap;
	}

	@JSONField(name = "2")
	public long getLastRefreshTime(){
		return this.lastRefreshTime;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getWeekPraise(){
		return this.weekPraise;
	}

	@JSONField(name = "4")
	public int getPraiseOtherNum(){
		return this.praiseOtherNum;
	}

	@JSONField(name = "1")
	public void setPraiseMap(java.util.concurrent.ConcurrentHashMap<Long, LbPraise> _v_){
		this.praiseMap = _v_;
	}

	@JSONField(name = "2")
	public void setLastRefreshTime(long _v_){
		this.lastRefreshTime = _v_;
	}

	@JSONField(name = "3")
	public void setWeekPraise(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.weekPraise = _v_;
	}

	@JSONField(name = "4")
	public void setPraiseOtherNum(int _v_){
		this.praiseOtherNum = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LbPlayerPraise) {
			LbPlayerPraise _o_ = (LbPlayerPraise)_o1_;
			if (!this.praiseMap.equals(_o_.praiseMap)) return false;
			if (this.lastRefreshTime != _o_.lastRefreshTime) return false;
			if (!this.weekPraise.equals(_o_.weekPraise)) return false;
			if (this.praiseOtherNum != _o_.praiseOtherNum) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.praiseMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefreshTime ^ (lastRefreshTime >>> 32));
		_h_ += _h_ * 31 + this.weekPraise.hashCode();
		_h_ += _h_ * 31 + (int)(this.praiseOtherNum);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(praiseMap).append(",");
		_sb_.append(lastRefreshTime).append(",");
		_sb_.append(weekPraise).append(",");
		_sb_.append(praiseOtherNum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
