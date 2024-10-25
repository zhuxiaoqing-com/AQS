package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerSkilledExpInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo> skilled; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> skilledGainCount; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> bounsExp;  // 向上加成累积经验
    private java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo> subSkilled;  // 附属技能

    PlayerSkilledExpInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.skilled = new java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo>();
		this.skilledGainCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.bounsExp = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.subSkilled = new java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo>();
	}

	public PlayerSkilledExpInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerSkilledExpInfo(PlayerSkilledExpInfo _o_) {
		this(_o_, null);
	}

	PlayerSkilledExpInfo(PlayerSkilledExpInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.skilled = new java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo>();
		_o_.skilled.forEach((_k_, _v_) -> this.skilled.put(_k_, new SkilledExpInfo(_v_)));
		this.skilledGainCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.skilledGainCount.forEach((_k_, _v_) -> this.skilledGainCount.put(_k_, _v_));
		this.bounsExp = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.bounsExp.forEach((_k_, _v_) -> this.bounsExp.put(_k_, _v_));
		this.subSkilled = new java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo>();
		_o_.subSkilled.forEach((_k_, _v_) -> this.subSkilled.put(_k_, new SkilledExpInfo(_v_)));
	}

	public void copyFrom(PlayerSkilledExpInfo _o_) {
        this.skilled = new java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo>();
		_o_.skilled.forEach((_k_, _v_) -> this.skilled.put(_k_, new SkilledExpInfo(_v_)));
        this.skilledGainCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.skilledGainCount.forEach((_k_, _v_) -> this.skilledGainCount.put(_k_, _v_));
        this.bounsExp = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.bounsExp.forEach((_k_, _v_) -> this.bounsExp.put(_k_, _v_));
        this.subSkilled = new java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo>();
		_o_.subSkilled.forEach((_k_, _v_) -> this.subSkilled.put(_k_, new SkilledExpInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo> getSkilled(){
		return this.skilled;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getSkilledGainCount(){
		return this.skilledGainCount;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getBounsExp(){
		return this.bounsExp;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo> getSubSkilled(){
		return this.subSkilled;
	}

	@JSONField(name = "1")
	public void setSkilled(java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo> _v_){
		this.skilled = _v_;
	}

	@JSONField(name = "2")
	public void setSkilledGainCount(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.skilledGainCount = _v_;
	}

	@JSONField(name = "3")
	public void setBounsExp(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.bounsExp = _v_;
	}

	@JSONField(name = "4")
	public void setSubSkilled(java.util.concurrent.ConcurrentHashMap<Integer, SkilledExpInfo> _v_){
		this.subSkilled = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerSkilledExpInfo) {
			PlayerSkilledExpInfo _o_ = (PlayerSkilledExpInfo)_o1_;
			if (!this.skilled.equals(_o_.skilled)) return false;
			if (!this.skilledGainCount.equals(_o_.skilledGainCount)) return false;
			if (!this.bounsExp.equals(_o_.bounsExp)) return false;
			if (!this.subSkilled.equals(_o_.subSkilled)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.skilled.hashCode();
		_h_ += _h_ * 31 + this.skilledGainCount.hashCode();
		_h_ += _h_ * 31 + this.bounsExp.hashCode();
		_h_ += _h_ * 31 + this.subSkilled.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(skilled).append(",");
		_sb_.append(skilledGainCount).append(",");
		_sb_.append(bounsExp).append(",");
		_sb_.append(subSkilled).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
