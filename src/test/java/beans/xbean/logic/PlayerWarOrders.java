package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerWarOrders extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, WarOrder> orders;  // 所有通行证
    private long lastRefresh;  // 上一次刷新时间

    PlayerWarOrders(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.orders = new java.util.concurrent.ConcurrentHashMap<Integer, WarOrder>();
	}

	public PlayerWarOrders() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerWarOrders(PlayerWarOrders _o_) {
		this(_o_, null);
	}

	PlayerWarOrders(PlayerWarOrders _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.orders = new java.util.concurrent.ConcurrentHashMap<Integer, WarOrder>();
		_o_.orders.forEach((_k_, _v_) -> this.orders.put(_k_, new WarOrder(_v_)));
		this.lastRefresh = _o_.lastRefresh;
	}

	public void copyFrom(PlayerWarOrders _o_) {
        this.orders = new java.util.concurrent.ConcurrentHashMap<Integer, WarOrder>();
		_o_.orders.forEach((_k_, _v_) -> this.orders.put(_k_, new WarOrder(_v_)));
        this.lastRefresh = _o_.lastRefresh;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, WarOrder> getOrders(){
		return this.orders;
	}

	@JSONField(name = "2")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "1")
	public void setOrders(java.util.concurrent.ConcurrentHashMap<Integer, WarOrder> _v_){
		this.orders = _v_;
	}

	@JSONField(name = "2")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerWarOrders) {
			PlayerWarOrders _o_ = (PlayerWarOrders)_o1_;
			if (!this.orders.equals(_o_.orders)) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.orders.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(orders).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
