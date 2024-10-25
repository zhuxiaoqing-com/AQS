package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerChat extends warrior.mdb.XBean {
    private int fnum;  // 每天免费聊天次数
    private long ftime;  // 刷新时间

    PlayerChat(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public PlayerChat() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerChat(PlayerChat _o_) {
		this(_o_, null);
	}

	PlayerChat(PlayerChat _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.fnum = _o_.fnum;
		this.ftime = _o_.ftime;
	}

	public void copyFrom(PlayerChat _o_) {
        this.fnum = _o_.fnum;
        this.ftime = _o_.ftime;
	}


	@JSONField(name = "1")
	public int getFnum(){
		return this.fnum;
	}

	@JSONField(name = "2")
	public long getFtime(){
		return this.ftime;
	}

	@JSONField(name = "1")
	public void setFnum(int _v_){
		this.fnum = _v_;
	}

	@JSONField(name = "2")
	public void setFtime(long _v_){
		this.ftime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerChat) {
			PlayerChat _o_ = (PlayerChat)_o1_;
			if (this.fnum != _o_.fnum) return false;
			if (this.ftime != _o_.ftime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.fnum);
		_h_ += _h_ * 31 + (int)(ftime ^ (ftime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(fnum).append(",");
		_sb_.append(ftime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
