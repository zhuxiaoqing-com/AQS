package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ServerReservation extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, AreaReservation> areaReservation;  // 区域预约数量
    private java.util.concurrent.ConcurrentHashMap<Long, TroopReservation> troopReservation;  // 阵营连队数据
    private long timeId;  // 标记id

    ServerReservation(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.areaReservation = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReservation>();
		this.troopReservation = new java.util.concurrent.ConcurrentHashMap<Long, TroopReservation>();
	}

	public ServerReservation() {
		this((warrior.mdb.XBean)null);
	}

	public ServerReservation(ServerReservation _o_) {
		this(_o_, null);
	}

	ServerReservation(ServerReservation _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaReservation = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReservation>();
		_o_.areaReservation.forEach((_k_, _v_) -> this.areaReservation.put(_k_, new AreaReservation(_v_)));
		this.troopReservation = new java.util.concurrent.ConcurrentHashMap<Long, TroopReservation>();
		_o_.troopReservation.forEach((_k_, _v_) -> this.troopReservation.put(_k_, new TroopReservation(_v_)));
		this.timeId = _o_.timeId;
	}

	public void copyFrom(ServerReservation _o_) {
        this.areaReservation = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReservation>();
		_o_.areaReservation.forEach((_k_, _v_) -> this.areaReservation.put(_k_, new AreaReservation(_v_)));
        this.troopReservation = new java.util.concurrent.ConcurrentHashMap<Long, TroopReservation>();
		_o_.troopReservation.forEach((_k_, _v_) -> this.troopReservation.put(_k_, new TroopReservation(_v_)));
        this.timeId = _o_.timeId;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, AreaReservation> getAreaReservation(){
		return this.areaReservation;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, TroopReservation> getTroopReservation(){
		return this.troopReservation;
	}

	@JSONField(name = "3")
	public long getTimeId(){
		return this.timeId;
	}

	@JSONField(name = "1")
	public void setAreaReservation(java.util.concurrent.ConcurrentHashMap<Integer, AreaReservation> _v_){
		this.areaReservation = _v_;
	}

	@JSONField(name = "2")
	public void setTroopReservation(java.util.concurrent.ConcurrentHashMap<Long, TroopReservation> _v_){
		this.troopReservation = _v_;
	}

	@JSONField(name = "3")
	public void setTimeId(long _v_){
		this.timeId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ServerReservation) {
			ServerReservation _o_ = (ServerReservation)_o1_;
			if (!this.areaReservation.equals(_o_.areaReservation)) return false;
			if (!this.troopReservation.equals(_o_.troopReservation)) return false;
			if (this.timeId != _o_.timeId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.areaReservation.hashCode();
		_h_ += _h_ * 31 + this.troopReservation.hashCode();
		_h_ += _h_ * 31 + (int)(timeId ^ (timeId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaReservation).append(",");
		_sb_.append(troopReservation).append(",");
		_sb_.append(timeId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
