package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionTroopMerge extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, TroopMerge> merges; 
    private int faction; 

    FactionTroopMerge(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.merges = new java.util.concurrent.ConcurrentHashMap<Long, TroopMerge>();
	}

	public FactionTroopMerge() {
		this((warrior.mdb.XBean)null);
	}

	public FactionTroopMerge(FactionTroopMerge _o_) {
		this(_o_, null);
	}

	FactionTroopMerge(FactionTroopMerge _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.merges = new java.util.concurrent.ConcurrentHashMap<Long, TroopMerge>();
		_o_.merges.forEach((_k_, _v_) -> this.merges.put(_k_, new TroopMerge(_v_)));
		this.faction = _o_.faction;
	}

	public void copyFrom(FactionTroopMerge _o_) {
        this.merges = new java.util.concurrent.ConcurrentHashMap<Long, TroopMerge>();
		_o_.merges.forEach((_k_, _v_) -> this.merges.put(_k_, new TroopMerge(_v_)));
        this.faction = _o_.faction;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, TroopMerge> getMerges(){
		return this.merges;
	}

	@JSONField(name = "2")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "1")
	public void setMerges(java.util.concurrent.ConcurrentHashMap<Long, TroopMerge> _v_){
		this.merges = _v_;
	}

	@JSONField(name = "2")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionTroopMerge) {
			FactionTroopMerge _o_ = (FactionTroopMerge)_o1_;
			if (!this.merges.equals(_o_.merges)) return false;
			if (this.faction != _o_.faction) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.merges.hashCode();
		_h_ += _h_ * 31 + (int)(this.faction);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(merges).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
