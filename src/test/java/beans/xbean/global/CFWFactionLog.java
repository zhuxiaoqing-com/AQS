package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CFWFactionLog extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<Integer> loseLands; 
    private java.util.concurrent.CopyOnWriteArraySet<Integer> occupyNewLands; 
    private int faction; 

    CFWFactionLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.loseLands = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.occupyNewLands = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public CFWFactionLog() {
		this((warrior.mdb.XBean)null);
	}

	public CFWFactionLog(CFWFactionLog _o_) {
		this(_o_, null);
	}

	CFWFactionLog(CFWFactionLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.loseLands = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.loseLands.forEach(_v_ -> this.loseLands.add(_v_));
		this.occupyNewLands = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.occupyNewLands.forEach(_v_ -> this.occupyNewLands.add(_v_));
		this.faction = _o_.faction;
	}

	public void copyFrom(CFWFactionLog _o_) {
        this.loseLands = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.loseLands.forEach(_v_ -> this.loseLands.add(_v_));
        this.occupyNewLands = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.occupyNewLands.forEach(_v_ -> this.occupyNewLands.add(_v_));
        this.faction = _o_.faction;
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getLoseLands(){
		return this.loseLands;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getOccupyNewLands(){
		return this.occupyNewLands;
	}

	@JSONField(name = "3")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "1")
	public void setLoseLands(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.loseLands = _v_;
	}

	@JSONField(name = "2")
	public void setOccupyNewLands(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.occupyNewLands = _v_;
	}

	@JSONField(name = "3")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFWFactionLog) {
			CFWFactionLog _o_ = (CFWFactionLog)_o1_;
			if (!this.loseLands.equals(_o_.loseLands)) return false;
			if (!this.occupyNewLands.equals(_o_.occupyNewLands)) return false;
			if (this.faction != _o_.faction) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.loseLands.hashCode();
		_h_ += _h_ * 31 + this.occupyNewLands.hashCode();
		_h_ += _h_ * 31 + (int)(this.faction);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(loseLands).append(",");
		_sb_.append(occupyNewLands).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
