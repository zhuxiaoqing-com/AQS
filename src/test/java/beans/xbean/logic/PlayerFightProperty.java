package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerFightProperty extends warrior.mdb.XBean {
    private int hp;  // 血量
    private int ep;  // 耐力
    private java.util.concurrent.ConcurrentHashMap<Integer, Buff> buffs;  // buffId->buff
    private float armorPoint;  // 当前护甲值
    private long lastAttackedTime;  // 上次受击时间
    private long lastRecoverArmorPointTime;  // 上次恢复护甲时间

    PlayerFightProperty(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.buffs = new java.util.concurrent.ConcurrentHashMap<Integer, Buff>();
	}

	public PlayerFightProperty() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerFightProperty(PlayerFightProperty _o_) {
		this(_o_, null);
	}

	PlayerFightProperty(PlayerFightProperty _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.hp = _o_.hp;
		this.ep = _o_.ep;
		this.buffs = new java.util.concurrent.ConcurrentHashMap<Integer, Buff>();
		_o_.buffs.forEach((_k_, _v_) -> this.buffs.put(_k_, new Buff(_v_)));
		this.armorPoint = _o_.armorPoint;
		this.lastAttackedTime = _o_.lastAttackedTime;
		this.lastRecoverArmorPointTime = _o_.lastRecoverArmorPointTime;
	}

	public void copyFrom(PlayerFightProperty _o_) {
        this.hp = _o_.hp;
        this.ep = _o_.ep;
        this.buffs = new java.util.concurrent.ConcurrentHashMap<Integer, Buff>();
		_o_.buffs.forEach((_k_, _v_) -> this.buffs.put(_k_, new Buff(_v_)));
        this.armorPoint = _o_.armorPoint;
        this.lastAttackedTime = _o_.lastAttackedTime;
        this.lastRecoverArmorPointTime = _o_.lastRecoverArmorPointTime;
	}


	@JSONField(name = "1")
	public int getHp(){
		return this.hp;
	}

	@JSONField(name = "2")
	public int getEp(){
		return this.ep;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Buff> getBuffs(){
		return this.buffs;
	}

	@JSONField(name = "4")
	public float getArmorPoint(){
		return this.armorPoint;
	}

	@JSONField(name = "5")
	public long getLastAttackedTime(){
		return this.lastAttackedTime;
	}

	@JSONField(name = "6")
	public long getLastRecoverArmorPointTime(){
		return this.lastRecoverArmorPointTime;
	}

	@JSONField(name = "1")
	public void setHp(int _v_){
		this.hp = _v_;
	}

	@JSONField(name = "2")
	public void setEp(int _v_){
		this.ep = _v_;
	}

	@JSONField(name = "3")
	public void setBuffs(java.util.concurrent.ConcurrentHashMap<Integer, Buff> _v_){
		this.buffs = _v_;
	}

	@JSONField(name = "4")
	public void setArmorPoint(float _v_){
		this.armorPoint = _v_;
	}

	@JSONField(name = "5")
	public void setLastAttackedTime(long _v_){
		this.lastAttackedTime = _v_;
	}

	@JSONField(name = "6")
	public void setLastRecoverArmorPointTime(long _v_){
		this.lastRecoverArmorPointTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerFightProperty) {
			PlayerFightProperty _o_ = (PlayerFightProperty)_o1_;
			if (this.hp != _o_.hp) return false;
			if (this.ep != _o_.ep) return false;
			if (!this.buffs.equals(_o_.buffs)) return false;
			if (this.armorPoint != _o_.armorPoint) return false;
			if (this.lastAttackedTime != _o_.lastAttackedTime) return false;
			if (this.lastRecoverArmorPointTime != _o_.lastRecoverArmorPointTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.hp);
		_h_ += _h_ * 31 + (int)(this.ep);
		_h_ += _h_ * 31 + this.buffs.hashCode();
		_h_ += _h_ * 31 + Float.floatToIntBits(this.armorPoint);
		_h_ += _h_ * 31 + (int)(lastAttackedTime ^ (lastAttackedTime >>> 32));
		_h_ += _h_ * 31 + (int)(lastRecoverArmorPointTime ^ (lastRecoverArmorPointTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(hp).append(",");
		_sb_.append(ep).append(",");
		_sb_.append(buffs).append(",");
		_sb_.append(armorPoint).append(",");
		_sb_.append(lastAttackedTime).append(",");
		_sb_.append(lastRecoverArmorPointTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
