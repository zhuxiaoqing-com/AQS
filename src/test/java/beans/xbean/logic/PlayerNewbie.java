package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerNewbie extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<Integer> receivedIds;  // 领取过奖励的id

    PlayerNewbie(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.receivedIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public PlayerNewbie() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerNewbie(PlayerNewbie _o_) {
		this(_o_, null);
	}

	PlayerNewbie(PlayerNewbie _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.receivedIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.receivedIds.forEach(_v_ -> this.receivedIds.add(_v_));
	}

	public void copyFrom(PlayerNewbie _o_) {
        this.receivedIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.receivedIds.forEach(_v_ -> this.receivedIds.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getReceivedIds(){
		return this.receivedIds;
	}

	@JSONField(name = "1")
	public void setReceivedIds(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.receivedIds = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerNewbie) {
			PlayerNewbie _o_ = (PlayerNewbie)_o1_;
			if (!this.receivedIds.equals(_o_.receivedIds)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.receivedIds.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(receivedIds).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
