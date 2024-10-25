package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CarrierData extends warrior.mdb.XBean {
    private int srl; 
    private xbean.lobby.CarrierAttr attr; 
    private java.util.concurrent.ConcurrentHashMap<Long, CarrierCost> cost;  // key:道具srl value:道具消耗

    CarrierData(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.attr = new CarrierAttr(this);
		this.cost = new java.util.concurrent.ConcurrentHashMap<Long, CarrierCost>();
	}

	public CarrierData() {
		this((warrior.mdb.XBean)null);
	}

	public CarrierData(CarrierData _o_) {
		this(_o_, null);
	}

	CarrierData(CarrierData _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.attr = new CarrierAttr(_o_.attr, this);
		this.cost = new java.util.concurrent.ConcurrentHashMap<Long, CarrierCost>();
		_o_.cost.forEach((_k_, _v_) -> this.cost.put(_k_, new CarrierCost(_v_)));
	}

	public void copyFrom(CarrierData _o_) {
        this.srl = _o_.srl;
        this.attr = new CarrierAttr(_o_.attr, this);
        this.cost = new java.util.concurrent.ConcurrentHashMap<Long, CarrierCost>();
		_o_.cost.forEach((_k_, _v_) -> this.cost.put(_k_, new CarrierCost(_v_)));
	}


	@JSONField(name = "1")
	public int getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public xbean.lobby.CarrierAttr getAttr(){
		return this.attr;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Long, CarrierCost> getCost(){
		return this.cost;
	}

	@JSONField(name = "1")
	public void setSrl(int _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setAttr(xbean.lobby.CarrierAttr _v_){
		this.attr = _v_;
	}

	@JSONField(name = "3")
	public void setCost(java.util.concurrent.ConcurrentHashMap<Long, CarrierCost> _v_){
		this.cost = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CarrierData) {
			CarrierData _o_ = (CarrierData)_o1_;
			if (this.srl != _o_.srl) return false;
			if (!this.attr.equals(_o_.attr)) return false;
			if (!this.cost.equals(_o_.cost)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.srl);
		_h_ += _h_ * 31 + this.attr.hashCode();
		_h_ += _h_ * 31 + this.cost.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(attr).append(",");
		_sb_.append(cost).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
