package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerHonorFalloff extends warrior.mdb.XBean {
    private xbean.logic.PlayerDungeonKillInfo kill; 
    private xbean.logic.PlayerDungeonKillInfo assist; 
    private xbean.logic.PlayerDungeonKillInfo removeDying; 

    PlayerHonorFalloff(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.kill = new PlayerDungeonKillInfo(this);
		this.assist = new PlayerDungeonKillInfo(this);
		this.removeDying = new PlayerDungeonKillInfo(this);
	}

	public PlayerHonorFalloff() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerHonorFalloff(PlayerHonorFalloff _o_) {
		this(_o_, null);
	}

	PlayerHonorFalloff(PlayerHonorFalloff _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.kill = new PlayerDungeonKillInfo(_o_.kill, this);
		this.assist = new PlayerDungeonKillInfo(_o_.assist, this);
		this.removeDying = new PlayerDungeonKillInfo(_o_.removeDying, this);
	}

	public void copyFrom(PlayerHonorFalloff _o_) {
        this.kill = new PlayerDungeonKillInfo(_o_.kill, this);
        this.assist = new PlayerDungeonKillInfo(_o_.assist, this);
        this.removeDying = new PlayerDungeonKillInfo(_o_.removeDying, this);
	}


	@JSONField(name = "1")
	public xbean.logic.PlayerDungeonKillInfo getKill(){
		return this.kill;
	}

	@JSONField(name = "2")
	public xbean.logic.PlayerDungeonKillInfo getAssist(){
		return this.assist;
	}

	@JSONField(name = "3")
	public xbean.logic.PlayerDungeonKillInfo getRemoveDying(){
		return this.removeDying;
	}

	@JSONField(name = "1")
	public void setKill(xbean.logic.PlayerDungeonKillInfo _v_){
		this.kill = _v_;
	}

	@JSONField(name = "2")
	public void setAssist(xbean.logic.PlayerDungeonKillInfo _v_){
		this.assist = _v_;
	}

	@JSONField(name = "3")
	public void setRemoveDying(xbean.logic.PlayerDungeonKillInfo _v_){
		this.removeDying = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerHonorFalloff) {
			PlayerHonorFalloff _o_ = (PlayerHonorFalloff)_o1_;
			if (!this.kill.equals(_o_.kill)) return false;
			if (!this.assist.equals(_o_.assist)) return false;
			if (!this.removeDying.equals(_o_.removeDying)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.kill.hashCode();
		_h_ += _h_ * 31 + this.assist.hashCode();
		_h_ += _h_ * 31 + this.removeDying.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(kill).append(",");
		_sb_.append(assist).append(",");
		_sb_.append(removeDying).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
