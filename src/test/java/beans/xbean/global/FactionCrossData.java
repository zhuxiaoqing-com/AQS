package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionCrossData extends warrior.mdb.XBean {
    private int faction; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> data; 

    FactionCrossData(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.data = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public FactionCrossData() {
		this((warrior.mdb.XBean)null);
	}

	public FactionCrossData(FactionCrossData _o_) {
		this(_o_, null);
	}

	FactionCrossData(FactionCrossData _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.faction = _o_.faction;
		this.data = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.data.forEach((_k_, _v_) -> this.data.put(_k_, _v_));
	}

	public void copyFrom(FactionCrossData _o_) {
        this.faction = _o_.faction;
        this.data = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.data.forEach((_k_, _v_) -> this.data.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getData(){
		return this.data;
	}

	@JSONField(name = "1")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "2")
	public void setData(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.data = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionCrossData) {
			FactionCrossData _o_ = (FactionCrossData)_o1_;
			if (this.faction != _o_.faction) return false;
			if (!this.data.equals(_o_.data)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + this.data.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(faction).append(",");
		_sb_.append(data).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
