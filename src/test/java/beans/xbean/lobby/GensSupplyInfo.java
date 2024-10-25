package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensSupplyInfo extends warrior.mdb.XBean {
    private int talentType; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> supply; 

    GensSupplyInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.supply = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GensSupplyInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensSupplyInfo(GensSupplyInfo _o_) {
		this(_o_, null);
	}

	GensSupplyInfo(GensSupplyInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.talentType = _o_.talentType;
		this.supply = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.supply.forEach((_k_, _v_) -> this.supply.put(_k_, _v_));
	}

	public void copyFrom(GensSupplyInfo _o_) {
        this.talentType = _o_.talentType;
        this.supply = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.supply.forEach((_k_, _v_) -> this.supply.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getTalentType(){
		return this.talentType;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getSupply(){
		return this.supply;
	}

	@JSONField(name = "1")
	public void setTalentType(int _v_){
		this.talentType = _v_;
	}

	@JSONField(name = "2")
	public void setSupply(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.supply = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensSupplyInfo) {
			GensSupplyInfo _o_ = (GensSupplyInfo)_o1_;
			if (this.talentType != _o_.talentType) return false;
			if (!this.supply.equals(_o_.supply)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.talentType);
		_h_ += _h_ * 31 + this.supply.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(talentType).append(",");
		_sb_.append(supply).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
