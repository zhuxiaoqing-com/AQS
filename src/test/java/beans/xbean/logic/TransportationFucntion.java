package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class TransportationFucntion extends warrior.mdb.XBean {
    private xbean.logic.Item fuel;  // 燃料
    private int fvalue;  // 燃料值
    private int state;  // 状态   0空闲  1 乘骑
    private xbean.logic.Bag items;  // 载具道具

    TransportationFucntion(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.fuel = new Item(this);
		this.items = new Bag(this);
	}

	public TransportationFucntion() {
		this((warrior.mdb.XBean)null);
	}

	public TransportationFucntion(TransportationFucntion _o_) {
		this(_o_, null);
	}

	TransportationFucntion(TransportationFucntion _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.fuel = new Item(_o_.fuel, this);
		this.fvalue = _o_.fvalue;
		this.state = _o_.state;
		this.items = new Bag(_o_.items, this);
	}

	public void copyFrom(TransportationFucntion _o_) {
        this.fuel = new Item(_o_.fuel, this);
        this.fvalue = _o_.fvalue;
        this.state = _o_.state;
        this.items = new Bag(_o_.items, this);
	}


	@JSONField(name = "1")
	public xbean.logic.Item getFuel(){
		return this.fuel;
	}

	@JSONField(name = "2")
	public int getFvalue(){
		return this.fvalue;
	}

	@JSONField(name = "3")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "4")
	public xbean.logic.Bag getItems(){
		return this.items;
	}

	@JSONField(name = "1")
	public void setFuel(xbean.logic.Item _v_){
		this.fuel = _v_;
	}

	@JSONField(name = "2")
	public void setFvalue(int _v_){
		this.fvalue = _v_;
	}

	@JSONField(name = "3")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "4")
	public void setItems(xbean.logic.Bag _v_){
		this.items = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TransportationFucntion) {
			TransportationFucntion _o_ = (TransportationFucntion)_o1_;
			if (!this.fuel.equals(_o_.fuel)) return false;
			if (this.fvalue != _o_.fvalue) return false;
			if (this.state != _o_.state) return false;
			if (!this.items.equals(_o_.items)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.fuel.hashCode();
		_h_ += _h_ * 31 + (int)(this.fvalue);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + this.items.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(fuel).append(",");
		_sb_.append(fvalue).append(",");
		_sb_.append(state).append(",");
		_sb_.append(items).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
