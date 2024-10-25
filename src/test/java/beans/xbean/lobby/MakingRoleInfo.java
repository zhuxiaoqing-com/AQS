package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class MakingRoleInfo extends warrior.mdb.XBean {
    private long startMill; 
    private xbean.lobby.PlayerBaseInfo playerInfo; 

    MakingRoleInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerInfo = new PlayerBaseInfo(this);
	}

	public MakingRoleInfo() {
		this((warrior.mdb.XBean)null);
	}

	public MakingRoleInfo(MakingRoleInfo _o_) {
		this(_o_, null);
	}

	MakingRoleInfo(MakingRoleInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.startMill = _o_.startMill;
		this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
	}

	public void copyFrom(MakingRoleInfo _o_) {
        this.startMill = _o_.startMill;
        this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
	}


	@JSONField(name = "1")
	public long getStartMill(){
		return this.startMill;
	}

	@JSONField(name = "2")
	public xbean.lobby.PlayerBaseInfo getPlayerInfo(){
		return this.playerInfo;
	}

	@JSONField(name = "1")
	public void setStartMill(long _v_){
		this.startMill = _v_;
	}

	@JSONField(name = "2")
	public void setPlayerInfo(xbean.lobby.PlayerBaseInfo _v_){
		this.playerInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MakingRoleInfo) {
			MakingRoleInfo _o_ = (MakingRoleInfo)_o1_;
			if (this.startMill != _o_.startMill) return false;
			if (!this.playerInfo.equals(_o_.playerInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(startMill ^ (startMill >>> 32));
		_h_ += _h_ * 31 + this.playerInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(startMill).append(",");
		_sb_.append(playerInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
