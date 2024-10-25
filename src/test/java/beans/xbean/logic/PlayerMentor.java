package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMentor extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<Integer> gotIds; 

    PlayerMentor(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gotIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public PlayerMentor() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMentor(PlayerMentor _o_) {
		this(_o_, null);
	}

	PlayerMentor(PlayerMentor _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gotIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.gotIds.forEach(_v_ -> this.gotIds.add(_v_));
	}

	public void copyFrom(PlayerMentor _o_) {
        this.gotIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.gotIds.forEach(_v_ -> this.gotIds.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getGotIds(){
		return this.gotIds;
	}

	@JSONField(name = "1")
	public void setGotIds(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.gotIds = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMentor) {
			PlayerMentor _o_ = (PlayerMentor)_o1_;
			if (!this.gotIds.equals(_o_.gotIds)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.gotIds.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gotIds).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
