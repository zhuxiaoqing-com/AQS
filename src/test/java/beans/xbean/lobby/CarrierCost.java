package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CarrierCost extends warrior.mdb.XBean {
    private long itemSrl; 
    private int num; 
    private int ammoNum; 
    private int oil; 

    CarrierCost(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public CarrierCost() {
		this((warrior.mdb.XBean)null);
	}

	public CarrierCost(CarrierCost _o_) {
		this(_o_, null);
	}

	CarrierCost(CarrierCost _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.itemSrl = _o_.itemSrl;
		this.num = _o_.num;
		this.ammoNum = _o_.ammoNum;
		this.oil = _o_.oil;
	}

	public void copyFrom(CarrierCost _o_) {
        this.itemSrl = _o_.itemSrl;
        this.num = _o_.num;
        this.ammoNum = _o_.ammoNum;
        this.oil = _o_.oil;
	}


	@JSONField(name = "1")
	public long getItemSrl(){
		return this.itemSrl;
	}

	@JSONField(name = "2")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "3")
	public int getAmmoNum(){
		return this.ammoNum;
	}

	@JSONField(name = "4")
	public int getOil(){
		return this.oil;
	}

	@JSONField(name = "1")
	public void setItemSrl(long _v_){
		this.itemSrl = _v_;
	}

	@JSONField(name = "2")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@JSONField(name = "3")
	public void setAmmoNum(int _v_){
		this.ammoNum = _v_;
	}

	@JSONField(name = "4")
	public void setOil(int _v_){
		this.oil = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CarrierCost) {
			CarrierCost _o_ = (CarrierCost)_o1_;
			if (this.itemSrl != _o_.itemSrl) return false;
			if (this.num != _o_.num) return false;
			if (this.ammoNum != _o_.ammoNum) return false;
			if (this.oil != _o_.oil) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(itemSrl ^ (itemSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.num);
		_h_ += _h_ * 31 + (int)(this.ammoNum);
		_h_ += _h_ * 31 + (int)(this.oil);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(itemSrl).append(",");
		_sb_.append(num).append(",");
		_sb_.append(ammoNum).append(",");
		_sb_.append(oil).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
