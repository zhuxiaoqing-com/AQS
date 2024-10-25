package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class ComposeOrder extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, ComposeOrderInfo> orderInfoMap;  // key: orderId
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> orderLimit;  // 每日订单限额 key:composeId, value:当天已经制造的数量
    private long lastDailyFreshTime;  // 上次刷新的时间

    ComposeOrder(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.orderInfoMap = new java.util.concurrent.ConcurrentHashMap<Long, ComposeOrderInfo>();
		this.orderLimit = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public ComposeOrder() {
		this((warrior.mdb.XBean)null);
	}

	public ComposeOrder(ComposeOrder _o_) {
		this(_o_, null);
	}

	ComposeOrder(ComposeOrder _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.orderInfoMap = new java.util.concurrent.ConcurrentHashMap<Long, ComposeOrderInfo>();
		_o_.orderInfoMap.forEach((_k_, _v_) -> this.orderInfoMap.put(_k_, new ComposeOrderInfo(_v_)));
		this.orderLimit = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.orderLimit.forEach((_k_, _v_) -> this.orderLimit.put(_k_, _v_));
		this.lastDailyFreshTime = _o_.lastDailyFreshTime;
	}

	public void copyFrom(ComposeOrder _o_) {
        this.orderInfoMap = new java.util.concurrent.ConcurrentHashMap<Long, ComposeOrderInfo>();
		_o_.orderInfoMap.forEach((_k_, _v_) -> this.orderInfoMap.put(_k_, new ComposeOrderInfo(_v_)));
        this.orderLimit = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.orderLimit.forEach((_k_, _v_) -> this.orderLimit.put(_k_, _v_));
        this.lastDailyFreshTime = _o_.lastDailyFreshTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, ComposeOrderInfo> getOrderInfoMap(){
		return this.orderInfoMap;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getOrderLimit(){
		return this.orderLimit;
	}

	@JSONField(name = "4")
	public long getLastDailyFreshTime(){
		return this.lastDailyFreshTime;
	}

	@JSONField(name = "1")
	public void setOrderInfoMap(java.util.concurrent.ConcurrentHashMap<Long, ComposeOrderInfo> _v_){
		this.orderInfoMap = _v_;
	}

	@JSONField(name = "3")
	public void setOrderLimit(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.orderLimit = _v_;
	}

	@JSONField(name = "4")
	public void setLastDailyFreshTime(long _v_){
		this.lastDailyFreshTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ComposeOrder) {
			ComposeOrder _o_ = (ComposeOrder)_o1_;
			if (!this.orderInfoMap.equals(_o_.orderInfoMap)) return false;
			if (!this.orderLimit.equals(_o_.orderLimit)) return false;
			if (this.lastDailyFreshTime != _o_.lastDailyFreshTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.orderInfoMap.hashCode();
		_h_ += _h_ * 31 + this.orderLimit.hashCode();
		_h_ += _h_ * 31 + (int)(lastDailyFreshTime ^ (lastDailyFreshTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(orderInfoMap).append(",");
		_sb_.append(orderLimit).append(",");
		_sb_.append(lastDailyFreshTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
