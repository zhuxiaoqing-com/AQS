package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerBlackList extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<Long> blackList; 

    PlayerBlackList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.blackList = new java.util.concurrent.CopyOnWriteArrayList<Long>();
	}

	public PlayerBlackList() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerBlackList(PlayerBlackList _o_) {
		this(_o_, null);
	}

	PlayerBlackList(PlayerBlackList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.blackList = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.blackList.forEach(_v_ -> this.blackList.add(_v_));
	}

	public void copyFrom(PlayerBlackList _o_) {
        this.blackList = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.blackList.forEach(_v_ -> this.blackList.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getBlackList(){
		return this.blackList;
	}

	@JSONField(name = "1")
	public void setBlackList(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.blackList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerBlackList) {
			PlayerBlackList _o_ = (PlayerBlackList)_o1_;
			if (!this.blackList.equals(_o_.blackList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.blackList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(blackList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
