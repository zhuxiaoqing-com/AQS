package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMilitary extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> mlv;  // 军衔等级 key:国家id value:军衔等级
    private long lastTime;  // 上一次领取邮件奖励的时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> nationDonateBuffGain;  // 国家捐献buff领取 key:国家id value:当日领取次数
    private long lastGainBuffTime;  // 上一次领取国家捐献buff的时间

    PlayerMilitary(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.mlv = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.nationDonateBuffGain = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerMilitary() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMilitary(PlayerMilitary _o_) {
		this(_o_, null);
	}

	PlayerMilitary(PlayerMilitary _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.mlv = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.mlv.forEach((_k_, _v_) -> this.mlv.put(_k_, _v_));
		this.lastTime = _o_.lastTime;
		this.nationDonateBuffGain = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.nationDonateBuffGain.forEach((_k_, _v_) -> this.nationDonateBuffGain.put(_k_, _v_));
		this.lastGainBuffTime = _o_.lastGainBuffTime;
	}

	public void copyFrom(PlayerMilitary _o_) {
        this.mlv = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.mlv.forEach((_k_, _v_) -> this.mlv.put(_k_, _v_));
        this.lastTime = _o_.lastTime;
        this.nationDonateBuffGain = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.nationDonateBuffGain.forEach((_k_, _v_) -> this.nationDonateBuffGain.put(_k_, _v_));
        this.lastGainBuffTime = _o_.lastGainBuffTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getMlv(){
		return this.mlv;
	}

	@JSONField(name = "2")
	public long getLastTime(){
		return this.lastTime;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getNationDonateBuffGain(){
		return this.nationDonateBuffGain;
	}

	@JSONField(name = "4")
	public long getLastGainBuffTime(){
		return this.lastGainBuffTime;
	}

	@JSONField(name = "1")
	public void setMlv(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.mlv = _v_;
	}

	@JSONField(name = "2")
	public void setLastTime(long _v_){
		this.lastTime = _v_;
	}

	@JSONField(name = "3")
	public void setNationDonateBuffGain(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.nationDonateBuffGain = _v_;
	}

	@JSONField(name = "4")
	public void setLastGainBuffTime(long _v_){
		this.lastGainBuffTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMilitary) {
			PlayerMilitary _o_ = (PlayerMilitary)_o1_;
			if (!this.mlv.equals(_o_.mlv)) return false;
			if (this.lastTime != _o_.lastTime) return false;
			if (!this.nationDonateBuffGain.equals(_o_.nationDonateBuffGain)) return false;
			if (this.lastGainBuffTime != _o_.lastGainBuffTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.mlv.hashCode();
		_h_ += _h_ * 31 + (int)(lastTime ^ (lastTime >>> 32));
		_h_ += _h_ * 31 + this.nationDonateBuffGain.hashCode();
		_h_ += _h_ * 31 + (int)(lastGainBuffTime ^ (lastGainBuffTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(mlv).append(",");
		_sb_.append(lastTime).append(",");
		_sb_.append(nationDonateBuffGain).append(",");
		_sb_.append(lastGainBuffTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
