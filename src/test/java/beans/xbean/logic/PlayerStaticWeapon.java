package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerStaticWeapon extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> createLimit;  // 每日制造限额 key:staticWeaponId, value:当天已经制造的数量
    private long lastDailyFreshTime;  // 上次刷新的时间

    PlayerStaticWeapon(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.createLimit = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerStaticWeapon() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerStaticWeapon(PlayerStaticWeapon _o_) {
		this(_o_, null);
	}

	PlayerStaticWeapon(PlayerStaticWeapon _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.createLimit = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.createLimit.forEach((_k_, _v_) -> this.createLimit.put(_k_, _v_));
		this.lastDailyFreshTime = _o_.lastDailyFreshTime;
	}

	public void copyFrom(PlayerStaticWeapon _o_) {
        this.createLimit = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.createLimit.forEach((_k_, _v_) -> this.createLimit.put(_k_, _v_));
        this.lastDailyFreshTime = _o_.lastDailyFreshTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCreateLimit(){
		return this.createLimit;
	}

	@JSONField(name = "2")
	public long getLastDailyFreshTime(){
		return this.lastDailyFreshTime;
	}

	@JSONField(name = "1")
	public void setCreateLimit(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.createLimit = _v_;
	}

	@JSONField(name = "2")
	public void setLastDailyFreshTime(long _v_){
		this.lastDailyFreshTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerStaticWeapon) {
			PlayerStaticWeapon _o_ = (PlayerStaticWeapon)_o1_;
			if (!this.createLimit.equals(_o_.createLimit)) return false;
			if (this.lastDailyFreshTime != _o_.lastDailyFreshTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.createLimit.hashCode();
		_h_ += _h_ * 31 + (int)(lastDailyFreshTime ^ (lastDailyFreshTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(createLimit).append(",");
		_sb_.append(lastDailyFreshTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
