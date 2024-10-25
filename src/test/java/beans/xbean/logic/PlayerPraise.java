package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerPraise extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Praise> praiseMap;  // 任务id->任务数据
    private long lastRefreshTime;  // 刷新时间

    PlayerPraise(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.praiseMap = new java.util.concurrent.ConcurrentHashMap<Integer, Praise>();
	}

	public PlayerPraise() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerPraise(PlayerPraise _o_) {
		this(_o_, null);
	}

	PlayerPraise(PlayerPraise _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.praiseMap = new java.util.concurrent.ConcurrentHashMap<Integer, Praise>();
		_o_.praiseMap.forEach((_k_, _v_) -> this.praiseMap.put(_k_, new Praise(_v_)));
		this.lastRefreshTime = _o_.lastRefreshTime;
	}

	public void copyFrom(PlayerPraise _o_) {
        this.praiseMap = new java.util.concurrent.ConcurrentHashMap<Integer, Praise>();
		_o_.praiseMap.forEach((_k_, _v_) -> this.praiseMap.put(_k_, new Praise(_v_)));
        this.lastRefreshTime = _o_.lastRefreshTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Praise> getPraiseMap(){
		return this.praiseMap;
	}

	@JSONField(name = "2")
	public long getLastRefreshTime(){
		return this.lastRefreshTime;
	}

	@JSONField(name = "1")
	public void setPraiseMap(java.util.concurrent.ConcurrentHashMap<Integer, Praise> _v_){
		this.praiseMap = _v_;
	}

	@JSONField(name = "2")
	public void setLastRefreshTime(long _v_){
		this.lastRefreshTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerPraise) {
			PlayerPraise _o_ = (PlayerPraise)_o1_;
			if (!this.praiseMap.equals(_o_.praiseMap)) return false;
			if (this.lastRefreshTime != _o_.lastRefreshTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.praiseMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefreshTime ^ (lastRefreshTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(praiseMap).append(",");
		_sb_.append(lastRefreshTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
