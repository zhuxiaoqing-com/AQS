package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class CityDepot extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Bag> cityDepots;  // key:城市id value:背包
    private java.util.concurrent.CopyOnWriteArrayList<TransferItem> transferItems;  // 转运信息

    CityDepot(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.cityDepots = new java.util.concurrent.ConcurrentHashMap<Integer, Bag>();
		this.transferItems = new java.util.concurrent.CopyOnWriteArrayList<TransferItem>();
	}

	public CityDepot() {
		this((warrior.mdb.XBean)null);
	}

	public CityDepot(CityDepot _o_) {
		this(_o_, null);
	}

	CityDepot(CityDepot _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.cityDepots = new java.util.concurrent.ConcurrentHashMap<Integer, Bag>();
		_o_.cityDepots.forEach((_k_, _v_) -> this.cityDepots.put(_k_, new Bag(_v_)));
		this.transferItems = new java.util.concurrent.CopyOnWriteArrayList<TransferItem>();
		_o_.transferItems.forEach(_v_ -> this.transferItems.add(new TransferItem(_v_)));
	}

	public void copyFrom(CityDepot _o_) {
        this.cityDepots = new java.util.concurrent.ConcurrentHashMap<Integer, Bag>();
		_o_.cityDepots.forEach((_k_, _v_) -> this.cityDepots.put(_k_, new Bag(_v_)));
        this.transferItems = new java.util.concurrent.CopyOnWriteArrayList<TransferItem>();
		_o_.transferItems.forEach(_v_ -> this.transferItems.add(new TransferItem(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Bag> getCityDepots(){
		return this.cityDepots;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<TransferItem> getTransferItems(){
		return this.transferItems;
	}

	@JSONField(name = "1")
	public void setCityDepots(java.util.concurrent.ConcurrentHashMap<Integer, Bag> _v_){
		this.cityDepots = _v_;
	}

	@JSONField(name = "2")
	public void setTransferItems(java.util.concurrent.CopyOnWriteArrayList<TransferItem> _v_){
		this.transferItems = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CityDepot) {
			CityDepot _o_ = (CityDepot)_o1_;
			if (!this.cityDepots.equals(_o_.cityDepots)) return false;
			if (!this.transferItems.equals(_o_.transferItems)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.cityDepots.hashCode();
		_h_ += _h_ * 31 + this.transferItems.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(cityDepots).append(",");
		_sb_.append(transferItems).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
