package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CarrierAttr extends warrior.mdb.XBean {
    private boolean init;  // 载具属性类的数据是否有效；有效：true ; 无效：false
    private int hp; 

    CarrierAttr(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public CarrierAttr() {
		this((warrior.mdb.XBean)null);
	}

	public CarrierAttr(CarrierAttr _o_) {
		this(_o_, null);
	}

	CarrierAttr(CarrierAttr _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.init = _o_.init;
		this.hp = _o_.hp;
	}

	public void copyFrom(CarrierAttr _o_) {
        this.init = _o_.init;
        this.hp = _o_.hp;
	}


	@JSONField(name = "1")
	public boolean getInit(){
		return this.init;
	}

	@JSONField(name = "2")
	public int getHp(){
		return this.hp;
	}

	@JSONField(name = "1")
	public void setInit(boolean _v_){
		this.init = _v_;
	}

	@JSONField(name = "2")
	public void setHp(int _v_){
		this.hp = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CarrierAttr) {
			CarrierAttr _o_ = (CarrierAttr)_o1_;
			if (this.init != _o_.init) return false;
			if (this.hp != _o_.hp) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (this.init ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.hp);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(init).append(",");
		_sb_.append(hp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
