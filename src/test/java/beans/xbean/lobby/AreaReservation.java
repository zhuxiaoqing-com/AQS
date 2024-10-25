package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AreaReservation extends warrior.mdb.XBean {
    private int areaId; 
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionReservation> factionMap;  // factionId->数据

    AreaReservation(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.factionMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionReservation>();
	}

	public AreaReservation() {
		this((warrior.mdb.XBean)null);
	}

	public AreaReservation(AreaReservation _o_) {
		this(_o_, null);
	}

	AreaReservation(AreaReservation _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaId = _o_.areaId;
		this.factionMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionReservation>();
		_o_.factionMap.forEach((_k_, _v_) -> this.factionMap.put(_k_, new FactionReservation(_v_)));
	}

	public void copyFrom(AreaReservation _o_) {
        this.areaId = _o_.areaId;
        this.factionMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionReservation>();
		_o_.factionMap.forEach((_k_, _v_) -> this.factionMap.put(_k_, new FactionReservation(_v_)));
	}


	@JSONField(name = "1")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionReservation> getFactionMap(){
		return this.factionMap;
	}

	@JSONField(name = "1")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "2")
	public void setFactionMap(java.util.concurrent.ConcurrentHashMap<Integer, FactionReservation> _v_){
		this.factionMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AreaReservation) {
			AreaReservation _o_ = (AreaReservation)_o1_;
			if (this.areaId != _o_.areaId) return false;
			if (!this.factionMap.equals(_o_.factionMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + this.factionMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaId).append(",");
		_sb_.append(factionMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
