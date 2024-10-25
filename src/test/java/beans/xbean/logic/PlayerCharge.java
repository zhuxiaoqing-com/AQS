package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerCharge extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<String> rewardedOrders;  // 已发奖完成的充值订单

    PlayerCharge(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.rewardedOrders = new java.util.concurrent.CopyOnWriteArraySet<String>();
	}

	public PlayerCharge() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerCharge(PlayerCharge _o_) {
		this(_o_, null);
	}

	PlayerCharge(PlayerCharge _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.rewardedOrders = new java.util.concurrent.CopyOnWriteArraySet<String>();
		this.rewardedOrders.forEach(_v_ -> this.rewardedOrders.add(_v_));
	}

	public void copyFrom(PlayerCharge _o_) {
        this.rewardedOrders = new java.util.concurrent.CopyOnWriteArraySet<String>();
		this.rewardedOrders.forEach(_v_ -> this.rewardedOrders.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArraySet<String> getRewardedOrders(){
		return this.rewardedOrders;
	}

	@JSONField(name = "1")
	public void setRewardedOrders(java.util.concurrent.CopyOnWriteArraySet<String> _v_){
		this.rewardedOrders = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerCharge) {
			PlayerCharge _o_ = (PlayerCharge)_o1_;
			if (!this.rewardedOrders.equals(_o_.rewardedOrders)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.rewardedOrders.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(rewardedOrders).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
