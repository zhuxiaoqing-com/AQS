package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerCrewDepot extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, Crew> crew;  // key:乘员唯一id  value:乘员

    PlayerCrewDepot(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.crew = new java.util.concurrent.ConcurrentHashMap<Long, Crew>();
	}

	public PlayerCrewDepot() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerCrewDepot(PlayerCrewDepot _o_) {
		this(_o_, null);
	}

	PlayerCrewDepot(PlayerCrewDepot _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.crew = new java.util.concurrent.ConcurrentHashMap<Long, Crew>();
		_o_.crew.forEach((_k_, _v_) -> this.crew.put(_k_, new Crew(_v_)));
	}

	public void copyFrom(PlayerCrewDepot _o_) {
        this.crew = new java.util.concurrent.ConcurrentHashMap<Long, Crew>();
		_o_.crew.forEach((_k_, _v_) -> this.crew.put(_k_, new Crew(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, Crew> getCrew(){
		return this.crew;
	}

	@JSONField(name = "1")
	public void setCrew(java.util.concurrent.ConcurrentHashMap<Long, Crew> _v_){
		this.crew = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerCrewDepot) {
			PlayerCrewDepot _o_ = (PlayerCrewDepot)_o1_;
			if (!this.crew.equals(_o_.crew)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.crew.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(crew).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
