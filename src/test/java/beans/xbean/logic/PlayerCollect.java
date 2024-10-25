package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerCollect extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> collectLimit;  // 每日采集限额 key:采集物id, value:当天已经采集的数量
    private long lastDailyFreshTime;  // 上次刷新的时间

    PlayerCollect(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.collectLimit = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerCollect() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerCollect(PlayerCollect _o_) {
		this(_o_, null);
	}

	PlayerCollect(PlayerCollect _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.collectLimit = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectLimit.forEach((_k_, _v_) -> this.collectLimit.put(_k_, _v_));
		this.lastDailyFreshTime = _o_.lastDailyFreshTime;
	}

	public void copyFrom(PlayerCollect _o_) {
        this.collectLimit = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectLimit.forEach((_k_, _v_) -> this.collectLimit.put(_k_, _v_));
        this.lastDailyFreshTime = _o_.lastDailyFreshTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCollectLimit(){
		return this.collectLimit;
	}

	@JSONField(name = "2")
	public long getLastDailyFreshTime(){
		return this.lastDailyFreshTime;
	}

	@JSONField(name = "1")
	public void setCollectLimit(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.collectLimit = _v_;
	}

	@JSONField(name = "2")
	public void setLastDailyFreshTime(long _v_){
		this.lastDailyFreshTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerCollect) {
			PlayerCollect _o_ = (PlayerCollect)_o1_;
			if (!this.collectLimit.equals(_o_.collectLimit)) return false;
			if (this.lastDailyFreshTime != _o_.lastDailyFreshTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.collectLimit.hashCode();
		_h_ += _h_ * 31 + (int)(lastDailyFreshTime ^ (lastDailyFreshTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(collectLimit).append(",");
		_sb_.append(lastDailyFreshTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
