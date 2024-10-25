package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class BuyItems extends warrior.mdb.XBean {
    private int time;  // 时间：周的时候存的周数，天的时候存的天数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> buyInfo;  // key:商品ID value:已购买次数

    BuyItems(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.buyInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public BuyItems() {
		this((warrior.mdb.XBean)null);
	}

	public BuyItems(BuyItems _o_) {
		this(_o_, null);
	}

	BuyItems(BuyItems _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.time = _o_.time;
		this.buyInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.buyInfo.forEach((_k_, _v_) -> this.buyInfo.put(_k_, _v_));
	}

	public void copyFrom(BuyItems _o_) {
        this.time = _o_.time;
        this.buyInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.buyInfo.forEach((_k_, _v_) -> this.buyInfo.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getTime(){
		return this.time;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getBuyInfo(){
		return this.buyInfo;
	}

	@JSONField(name = "1")
	public void setTime(int _v_){
		this.time = _v_;
	}

	@JSONField(name = "2")
	public void setBuyInfo(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.buyInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BuyItems) {
			BuyItems _o_ = (BuyItems)_o1_;
			if (this.time != _o_.time) return false;
			if (!this.buyInfo.equals(_o_.buyInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.time);
		_h_ += _h_ * 31 + this.buyInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(time).append(",");
		_sb_.append(buyInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
