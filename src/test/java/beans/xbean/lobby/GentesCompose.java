package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GentesCompose extends warrior.mdb.XBean {
    private int dailyComposeNum;  // 当日发起制造次数
    private long lastRefreshTime;  // 上次刷新每日制造时间
    private java.util.concurrent.ConcurrentHashMap<Long, GentesComposeOrder> composeOrder;  // 订单

    GentesCompose(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.composeOrder = new java.util.concurrent.ConcurrentHashMap<Long, GentesComposeOrder>();
	}

	public GentesCompose() {
		this((warrior.mdb.XBean)null);
	}

	public GentesCompose(GentesCompose _o_) {
		this(_o_, null);
	}

	GentesCompose(GentesCompose _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.dailyComposeNum = _o_.dailyComposeNum;
		this.lastRefreshTime = _o_.lastRefreshTime;
		this.composeOrder = new java.util.concurrent.ConcurrentHashMap<Long, GentesComposeOrder>();
		_o_.composeOrder.forEach((_k_, _v_) -> this.composeOrder.put(_k_, new GentesComposeOrder(_v_)));
	}

	public void copyFrom(GentesCompose _o_) {
        this.dailyComposeNum = _o_.dailyComposeNum;
        this.lastRefreshTime = _o_.lastRefreshTime;
        this.composeOrder = new java.util.concurrent.ConcurrentHashMap<Long, GentesComposeOrder>();
		_o_.composeOrder.forEach((_k_, _v_) -> this.composeOrder.put(_k_, new GentesComposeOrder(_v_)));
	}


	@JSONField(name = "1")
	public int getDailyComposeNum(){
		return this.dailyComposeNum;
	}

	@JSONField(name = "2")
	public long getLastRefreshTime(){
		return this.lastRefreshTime;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Long, GentesComposeOrder> getComposeOrder(){
		return this.composeOrder;
	}

	@JSONField(name = "1")
	public void setDailyComposeNum(int _v_){
		this.dailyComposeNum = _v_;
	}

	@JSONField(name = "2")
	public void setLastRefreshTime(long _v_){
		this.lastRefreshTime = _v_;
	}

	@JSONField(name = "3")
	public void setComposeOrder(java.util.concurrent.ConcurrentHashMap<Long, GentesComposeOrder> _v_){
		this.composeOrder = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GentesCompose) {
			GentesCompose _o_ = (GentesCompose)_o1_;
			if (this.dailyComposeNum != _o_.dailyComposeNum) return false;
			if (this.lastRefreshTime != _o_.lastRefreshTime) return false;
			if (!this.composeOrder.equals(_o_.composeOrder)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.dailyComposeNum);
		_h_ += _h_ * 31 + (int)(lastRefreshTime ^ (lastRefreshTime >>> 32));
		_h_ += _h_ * 31 + this.composeOrder.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(dailyComposeNum).append(",");
		_sb_.append(lastRefreshTime).append(",");
		_sb_.append(composeOrder).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
