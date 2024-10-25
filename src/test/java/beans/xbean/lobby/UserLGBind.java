package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class UserLGBind extends warrior.mdb.XBean {
    private int lgId;  // 绑定的lgId

    UserLGBind(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public UserLGBind() {
		this((warrior.mdb.XBean)null);
	}

	public UserLGBind(UserLGBind _o_) {
		this(_o_, null);
	}

	UserLGBind(UserLGBind _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.lgId = _o_.lgId;
	}

	public void copyFrom(UserLGBind _o_) {
        this.lgId = _o_.lgId;
	}


	@JSONField(name = "1")
	public int getLgId(){
		return this.lgId;
	}

	@JSONField(name = "1")
	public void setLgId(int _v_){
		this.lgId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof UserLGBind) {
			UserLGBind _o_ = (UserLGBind)_o1_;
			if (this.lgId != _o_.lgId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.lgId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(lgId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
