package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerFightRecord extends warrior.mdb.XBean {
    private int kill; 
    private int death; 
    private int assist; 
    private long damage; 

    PlayerFightRecord(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public PlayerFightRecord() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerFightRecord(PlayerFightRecord _o_) {
		this(_o_, null);
	}

	PlayerFightRecord(PlayerFightRecord _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.kill = _o_.kill;
		this.death = _o_.death;
		this.assist = _o_.assist;
		this.damage = _o_.damage;
	}

	public void copyFrom(PlayerFightRecord _o_) {
        this.kill = _o_.kill;
        this.death = _o_.death;
        this.assist = _o_.assist;
        this.damage = _o_.damage;
	}


	@JSONField(name = "1")
	public int getKill(){
		return this.kill;
	}

	@JSONField(name = "2")
	public int getDeath(){
		return this.death;
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
	public void setKill(int _v_){
		this.kill = _v_;
	}

	@JSONField(name = "2")
	public void setDeath(int _v_){
		this.death = _v_;
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
		if (_o1_ instanceof PlayerFightRecord) {
			PlayerFightRecord _o_ = (PlayerFightRecord)_o1_;
			if (this.kill != _o_.kill) return false;
			if (this.death != _o_.death) return false;
			if (this.assist != _o_.assist) return false;
			if (this.damage != _o_.damage) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.kill);
		_h_ += _h_ * 31 + (int)(this.death);
		_h_ += _h_ * 31 + (int)(this.assist);
		_h_ += _h_ * 31 + (int)(damage ^ (damage >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(kill).append(",");
		_sb_.append(death).append(",");
		_sb_.append(assist).append(",");
		_sb_.append(damage).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
