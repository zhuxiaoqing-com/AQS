package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerOrder extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<String, OrderInfo> orders;  // 订单列表

    PlayerOrder(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.orders = new java.util.concurrent.ConcurrentHashMap<String, OrderInfo>();
	}

	public PlayerOrder() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerOrder(PlayerOrder _o_) {
		this(_o_, null);
	}

	PlayerOrder(PlayerOrder _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.orders = new java.util.concurrent.ConcurrentHashMap<String, OrderInfo>();
		_o_.orders.forEach((_k_, _v_) -> this.orders.put(_k_, new OrderInfo(_v_)));
	}

	public void copyFrom(PlayerOrder _o_) {
        this.orders = new java.util.concurrent.ConcurrentHashMap<String, OrderInfo>();
		_o_.orders.forEach((_k_, _v_) -> this.orders.put(_k_, new OrderInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<String, OrderInfo> getOrders(){
		return this.orders;
	}

	@JSONField(name = "1")
	public void setOrders(java.util.concurrent.ConcurrentHashMap<String, OrderInfo> _v_){
		this.orders = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerOrder) {
			PlayerOrder _o_ = (PlayerOrder)_o1_;
			if (!this.orders.equals(_o_.orders)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.orders.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(orders).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
