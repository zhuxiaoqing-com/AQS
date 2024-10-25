package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensCheckInInfo extends warrior.mdb.XBean {
    private int dailySignCount;  // 每天签到人数
    private float weekTotalSign;  // 周签到率每日总和
    private java.util.concurrent.ConcurrentHashMap<Long, CheckInDepositReward> deposit;  // 临时寄存奖励

    GensCheckInInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.deposit = new java.util.concurrent.ConcurrentHashMap<Long, CheckInDepositReward>();
	}

	public GensCheckInInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensCheckInInfo(GensCheckInInfo _o_) {
		this(_o_, null);
	}

	GensCheckInInfo(GensCheckInInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.dailySignCount = _o_.dailySignCount;
		this.weekTotalSign = _o_.weekTotalSign;
		this.deposit = new java.util.concurrent.ConcurrentHashMap<Long, CheckInDepositReward>();
		_o_.deposit.forEach((_k_, _v_) -> this.deposit.put(_k_, new CheckInDepositReward(_v_)));
	}

	public void copyFrom(GensCheckInInfo _o_) {
        this.dailySignCount = _o_.dailySignCount;
        this.weekTotalSign = _o_.weekTotalSign;
        this.deposit = new java.util.concurrent.ConcurrentHashMap<Long, CheckInDepositReward>();
		_o_.deposit.forEach((_k_, _v_) -> this.deposit.put(_k_, new CheckInDepositReward(_v_)));
	}


	@JSONField(name = "1")
	public int getDailySignCount(){
		return this.dailySignCount;
	}

	@JSONField(name = "2")
	public float getWeekTotalSign(){
		return this.weekTotalSign;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Long, CheckInDepositReward> getDeposit(){
		return this.deposit;
	}

	@JSONField(name = "1")
	public void setDailySignCount(int _v_){
		this.dailySignCount = _v_;
	}

	@JSONField(name = "2")
	public void setWeekTotalSign(float _v_){
		this.weekTotalSign = _v_;
	}

	@JSONField(name = "3")
	public void setDeposit(java.util.concurrent.ConcurrentHashMap<Long, CheckInDepositReward> _v_){
		this.deposit = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensCheckInInfo) {
			GensCheckInInfo _o_ = (GensCheckInInfo)_o1_;
			if (this.dailySignCount != _o_.dailySignCount) return false;
			if (this.weekTotalSign != _o_.weekTotalSign) return false;
			if (!this.deposit.equals(_o_.deposit)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.dailySignCount);
		_h_ += _h_ * 31 + Float.floatToIntBits(this.weekTotalSign);
		_h_ += _h_ * 31 + this.deposit.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(dailySignCount).append(",");
		_sb_.append(weekTotalSign).append(",");
		_sb_.append(deposit).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
