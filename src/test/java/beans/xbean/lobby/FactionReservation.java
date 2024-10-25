package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionReservation extends warrior.mdb.XBean {
    private int faction; 
    private java.util.concurrent.ConcurrentHashMap<Long, Integer> troopMap;  // troopId->预约人数
    private int num; 

    FactionReservation(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.troopMap = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
	}

	public FactionReservation() {
		this((warrior.mdb.XBean)null);
	}

	public FactionReservation(FactionReservation _o_) {
		this(_o_, null);
	}

	FactionReservation(FactionReservation _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.faction = _o_.faction;
		this.troopMap = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.troopMap.forEach((_k_, _v_) -> this.troopMap.put(_k_, _v_));
		this.num = _o_.num;
	}

	public void copyFrom(FactionReservation _o_) {
        this.faction = _o_.faction;
        this.troopMap = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.troopMap.forEach((_k_, _v_) -> this.troopMap.put(_k_, _v_));
        this.num = _o_.num;
	}


	@JSONField(name = "1")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, Integer> getTroopMap(){
		return this.troopMap;
	}

	@JSONField(name = "3")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "1")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "2")
	public void setTroopMap(java.util.concurrent.ConcurrentHashMap<Long, Integer> _v_){
		this.troopMap = _v_;
	}

	@JSONField(name = "3")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionReservation) {
			FactionReservation _o_ = (FactionReservation)_o1_;
			if (this.faction != _o_.faction) return false;
			if (!this.troopMap.equals(_o_.troopMap)) return false;
			if (this.num != _o_.num) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + this.troopMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.num);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(faction).append(",");
		_sb_.append(troopMap).append(",");
		_sb_.append(num).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
