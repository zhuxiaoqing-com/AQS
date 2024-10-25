package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMajorZombie extends warrior.mdb.XBean {
    private xbean.lobby.LBActLogicInfo actInfo;  // 活动数据
    private boolean received;  // 是否已经领取过奖励

    PlayerMajorZombie(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actInfo = new LBActLogicInfo(this);
	}

	public PlayerMajorZombie() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMajorZombie(PlayerMajorZombie _o_) {
		this(_o_, null);
	}

	PlayerMajorZombie(PlayerMajorZombie _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actInfo = new LBActLogicInfo(_o_.actInfo, this);
		this.received = _o_.received;
	}

	public void copyFrom(PlayerMajorZombie _o_) {
        this.actInfo = new LBActLogicInfo(_o_.actInfo, this);
        this.received = _o_.received;
	}


	@JSONField(name = "1")
	public xbean.lobby.LBActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "2")
	public boolean getReceived(){
		return this.received;
	}

	@JSONField(name = "1")
	public void setActInfo(xbean.lobby.LBActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@JSONField(name = "2")
	public void setReceived(boolean _v_){
		this.received = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMajorZombie) {
			PlayerMajorZombie _o_ = (PlayerMajorZombie)_o1_;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			if (this.received != _o_.received) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		_h_ += _h_ * 31 + (this.received ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actInfo).append(",");
		_sb_.append(received).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
