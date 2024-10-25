package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class TransportationProducer extends warrior.mdb.XBean {
    private xbean.logic.Bag bag;  // 背包

    TransportationProducer(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.bag = new Bag(this);
	}

	public TransportationProducer() {
		this((warrior.mdb.XBean)null);
	}

	public TransportationProducer(TransportationProducer _o_) {
		this(_o_, null);
	}

	TransportationProducer(TransportationProducer _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.bag = new Bag(_o_.bag, this);
	}

	public void copyFrom(TransportationProducer _o_) {
        this.bag = new Bag(_o_.bag, this);
	}


	@JSONField(name = "1")
	public xbean.logic.Bag getBag(){
		return this.bag;
	}

	@JSONField(name = "1")
	public void setBag(xbean.logic.Bag _v_){
		this.bag = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TransportationProducer) {
			TransportationProducer _o_ = (TransportationProducer)_o1_;
			if (!this.bag.equals(_o_.bag)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.bag.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(bag).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
