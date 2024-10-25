package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerUserInfo extends warrior.mdb.XBean {
    private String userId;  // userId
    private long pId;  // 玩家唯一ID

    PlayerUserInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.userId = "";
	}

	public PlayerUserInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerUserInfo(PlayerUserInfo _o_) {
		this(_o_, null);
	}

	PlayerUserInfo(PlayerUserInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.userId = _o_.userId;
		this.pId = _o_.pId;
	}

	public void copyFrom(PlayerUserInfo _o_) {
        this.userId = _o_.userId;
        this.pId = _o_.pId;
	}


	@JSONField(name = "1")
	public String getUserId(){
		return this.userId;
	}

	@JSONField(name = "2")
	public long getPId(){
		return this.pId;
	}

	@JSONField(name = "1")
	public void setUserId(String _v_){
		this.userId = _v_;
	}

	@JSONField(name = "2")
	public void setPId(long _v_){
		this.pId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerUserInfo) {
			PlayerUserInfo _o_ = (PlayerUserInfo)_o1_;
			if (!this.userId.equals(_o_.userId)) return false;
			if (this.pId != _o_.pId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.userId.hashCode();
		_h_ += _h_ * 31 + (int)(pId ^ (pId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append("T").append(userId.length()).append(",");
		_sb_.append(pId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
