package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class WeaponLog extends warrior.mdb.XBean {
    private int weaponId;  // 武器id
    private int kill;  // 击杀玩家
    private int assist;  // 助攻
    private long damage;  // 伤害

    WeaponLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public WeaponLog() {
		this((warrior.mdb.XBean)null);
	}

	public WeaponLog(WeaponLog _o_) {
		this(_o_, null);
	}

	WeaponLog(WeaponLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.weaponId = _o_.weaponId;
		this.kill = _o_.kill;
		this.assist = _o_.assist;
		this.damage = _o_.damage;
	}

	public void copyFrom(WeaponLog _o_) {
        this.weaponId = _o_.weaponId;
        this.kill = _o_.kill;
        this.assist = _o_.assist;
        this.damage = _o_.damage;
	}


	@JSONField(name = "1")
	public int getWeaponId(){
		return this.weaponId;
	}

	@JSONField(name = "2")
	public int getKill(){
		return this.kill;
	}

	@JSONField(name = "3")
	public int getAssist(){
		return this.assist;
	}

	@JSONField(name = "4")
	public long getDamage(){
		return this.damage;
	}

	@JSONField(name = "1")
	public void setWeaponId(int _v_){
		this.weaponId = _v_;
	}

	@JSONField(name = "2")
	public void setKill(int _v_){
		this.kill = _v_;
	}

	@JSONField(name = "3")
	public void setAssist(int _v_){
		this.assist = _v_;
	}

	@JSONField(name = "4")
	public void setDamage(long _v_){
		this.damage = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WeaponLog) {
			WeaponLog _o_ = (WeaponLog)_o1_;
			if (this.weaponId != _o_.weaponId) return false;
			if (this.kill != _o_.kill) return false;
			if (this.assist != _o_.assist) return false;
			if (this.damage != _o_.damage) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.weaponId);
		_h_ += _h_ * 31 + (int)(this.kill);
		_h_ += _h_ * 31 + (int)(this.assist);
		_h_ += _h_ * 31 + (int)(damage ^ (damage >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(weaponId).append(",");
		_sb_.append(kill).append(",");
		_sb_.append(assist).append(",");
		_sb_.append(damage).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
