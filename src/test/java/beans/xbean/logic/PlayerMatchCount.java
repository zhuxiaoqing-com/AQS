package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMatchCount extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, MatchCount> count;  // k:pvp活动界面表id v：MatchCount
    private long lastRefresh;  // 上一次刷新时间

    PlayerMatchCount(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.count = new java.util.concurrent.ConcurrentHashMap<Integer, MatchCount>();
	}

	public PlayerMatchCount() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMatchCount(PlayerMatchCount _o_) {
		this(_o_, null);
	}

	PlayerMatchCount(PlayerMatchCount _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.count = new java.util.concurrent.ConcurrentHashMap<Integer, MatchCount>();
		_o_.count.forEach((_k_, _v_) -> this.count.put(_k_, new MatchCount(_v_)));
		this.lastRefresh = _o_.lastRefresh;
	}

	public void copyFrom(PlayerMatchCount _o_) {
        this.count = new java.util.concurrent.ConcurrentHashMap<Integer, MatchCount>();
		_o_.count.forEach((_k_, _v_) -> this.count.put(_k_, new MatchCount(_v_)));
        this.lastRefresh = _o_.lastRefresh;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, MatchCount> getCount(){
		return this.count;
	}

	@JSONField(name = "2")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "1")
	public void setCount(java.util.concurrent.ConcurrentHashMap<Integer, MatchCount> _v_){
		this.count = _v_;
	}

	@JSONField(name = "2")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMatchCount) {
			PlayerMatchCount _o_ = (PlayerMatchCount)_o1_;
			if (!this.count.equals(_o_.count)) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.count.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(count).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
