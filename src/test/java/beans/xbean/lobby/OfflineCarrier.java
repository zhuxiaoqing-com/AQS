package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class OfflineCarrier extends warrior.mdb.XBean {
    private xbean.lobby.LBCarrierList lBCarrierList;  // 载具list
    private java.util.concurrent.CopyOnWriteArrayList<BTCarrierSyncInfo> btCarrierSyncList;  // 同步数据
    private int carrierHelpRepair;  // 载具帮助他人修理次数
    private long lastRefresh;  // 上次刷新时间

    OfflineCarrier(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.lBCarrierList = new LBCarrierList(this);
		this.btCarrierSyncList = new java.util.concurrent.CopyOnWriteArrayList<BTCarrierSyncInfo>();
	}

	public OfflineCarrier() {
		this((warrior.mdb.XBean)null);
	}

	public OfflineCarrier(OfflineCarrier _o_) {
		this(_o_, null);
	}

	OfflineCarrier(OfflineCarrier _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.lBCarrierList = new LBCarrierList(_o_.lBCarrierList, this);
		this.btCarrierSyncList = new java.util.concurrent.CopyOnWriteArrayList<BTCarrierSyncInfo>();
		_o_.btCarrierSyncList.forEach(_v_ -> this.btCarrierSyncList.add(new BTCarrierSyncInfo(_v_)));
		this.carrierHelpRepair = _o_.carrierHelpRepair;
		this.lastRefresh = _o_.lastRefresh;
	}

	public void copyFrom(OfflineCarrier _o_) {
        this.lBCarrierList = new LBCarrierList(_o_.lBCarrierList, this);
        this.btCarrierSyncList = new java.util.concurrent.CopyOnWriteArrayList<BTCarrierSyncInfo>();
		_o_.btCarrierSyncList.forEach(_v_ -> this.btCarrierSyncList.add(new BTCarrierSyncInfo(_v_)));
        this.carrierHelpRepair = _o_.carrierHelpRepair;
        this.lastRefresh = _o_.lastRefresh;
	}


	@JSONField(name = "1")
	public xbean.lobby.LBCarrierList getLBCarrierList(){
		return this.lBCarrierList;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<BTCarrierSyncInfo> getBtCarrierSyncList(){
		return this.btCarrierSyncList;
	}

	@JSONField(name = "4")
	public int getCarrierHelpRepair(){
		return this.carrierHelpRepair;
	}

	@JSONField(name = "5")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "1")
	public void setLBCarrierList(xbean.lobby.LBCarrierList _v_){
		this.lBCarrierList = _v_;
	}

	@JSONField(name = "3")
	public void setBtCarrierSyncList(java.util.concurrent.CopyOnWriteArrayList<BTCarrierSyncInfo> _v_){
		this.btCarrierSyncList = _v_;
	}

	@JSONField(name = "4")
	public void setCarrierHelpRepair(int _v_){
		this.carrierHelpRepair = _v_;
	}

	@JSONField(name = "5")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof OfflineCarrier) {
			OfflineCarrier _o_ = (OfflineCarrier)_o1_;
			if (!this.lBCarrierList.equals(_o_.lBCarrierList)) return false;
			if (!this.btCarrierSyncList.equals(_o_.btCarrierSyncList)) return false;
			if (this.carrierHelpRepair != _o_.carrierHelpRepair) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.lBCarrierList.hashCode();
		_h_ += _h_ * 31 + this.btCarrierSyncList.hashCode();
		_h_ += _h_ * 31 + (int)(this.carrierHelpRepair);
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(lBCarrierList).append(",");
		_sb_.append(btCarrierSyncList).append(",");
		_sb_.append(carrierHelpRepair).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
