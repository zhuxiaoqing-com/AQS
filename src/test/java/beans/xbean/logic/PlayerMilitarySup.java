package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMilitarySup extends warrior.mdb.XBean {
    private long lastRefreshTime;  // 刷新时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> buyTimes; 

    PlayerMilitarySup(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.buyTimes = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerMilitarySup() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMilitarySup(PlayerMilitarySup _o_) {
		this(_o_, null);
	}

	PlayerMilitarySup(PlayerMilitarySup _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.lastRefreshTime = _o_.lastRefreshTime;
		this.buyTimes = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.buyTimes.forEach((_k_, _v_) -> this.buyTimes.put(_k_, _v_));
	}

	public void copyFrom(PlayerMilitarySup _o_) {
        this.lastRefreshTime = _o_.lastRefreshTime;
        this.buyTimes = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.buyTimes.forEach((_k_, _v_) -> this.buyTimes.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getLastRefreshTime(){
		return this.lastRefreshTime;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getBuyTimes(){
		return this.buyTimes;
	}

	@JSONField(name = "1")
	public void setLastRefreshTime(long _v_){
		this.lastRefreshTime = _v_;
	}

	@JSONField(name = "2")
	public void setBuyTimes(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.buyTimes = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMilitarySup) {
			PlayerMilitarySup _o_ = (PlayerMilitarySup)_o1_;
			if (this.lastRefreshTime != _o_.lastRefreshTime) return false;
			if (!this.buyTimes.equals(_o_.buyTimes)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(lastRefreshTime ^ (lastRefreshTime >>> 32));
		_h_ += _h_ * 31 + this.buyTimes.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(lastRefreshTime).append(",");
		_sb_.append(buyTimes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
