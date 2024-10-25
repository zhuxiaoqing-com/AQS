package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CarrierMakePlayerList extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, Long> playerList;  // 玩家id

    CarrierMakePlayerList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerList = new java.util.concurrent.ConcurrentHashMap<Long, Long>();
	}

	public CarrierMakePlayerList() {
		this((warrior.mdb.XBean)null);
	}

	public CarrierMakePlayerList(CarrierMakePlayerList _o_) {
		this(_o_, null);
	}

	CarrierMakePlayerList(CarrierMakePlayerList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerList = new java.util.concurrent.ConcurrentHashMap<Long, Long>();
		_o_.playerList.forEach((_k_, _v_) -> this.playerList.put(_k_, _v_));
	}

	public void copyFrom(CarrierMakePlayerList _o_) {
        this.playerList = new java.util.concurrent.ConcurrentHashMap<Long, Long>();
		_o_.playerList.forEach((_k_, _v_) -> this.playerList.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, Long> getPlayerList(){
		return this.playerList;
	}

	@JSONField(name = "1")
	public void setPlayerList(java.util.concurrent.ConcurrentHashMap<Long, Long> _v_){
		this.playerList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CarrierMakePlayerList) {
			CarrierMakePlayerList _o_ = (CarrierMakePlayerList)_o1_;
			if (!this.playerList.equals(_o_.playerList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.playerList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
