package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionDepot extends warrior.mdb.XBean {
    private int fid;  // 阵营id
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionItemInfo> items;  // itemid->info
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> resources;  // resourceId->num
    private java.util.concurrent.ConcurrentHashMap<Integer, ItemDiscount> itemDiscountMap;  // itemId->折扣数据
    private long lastDailyRefresh; 
    private long lastDiscountRefresh;  // 上一次折扣刷新数据

    FactionDepot(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, FactionItemInfo>();
		this.resources = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.itemDiscountMap = new java.util.concurrent.ConcurrentHashMap<Integer, ItemDiscount>();
	}

	public FactionDepot() {
		this((warrior.mdb.XBean)null);
	}

	public FactionDepot(FactionDepot _o_) {
		this(_o_, null);
	}

	FactionDepot(FactionDepot _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.fid = _o_.fid;
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, FactionItemInfo>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, new FactionItemInfo(_v_)));
		this.resources = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.resources.forEach((_k_, _v_) -> this.resources.put(_k_, _v_));
		this.itemDiscountMap = new java.util.concurrent.ConcurrentHashMap<Integer, ItemDiscount>();
		_o_.itemDiscountMap.forEach((_k_, _v_) -> this.itemDiscountMap.put(_k_, new ItemDiscount(_v_)));
		this.lastDailyRefresh = _o_.lastDailyRefresh;
		this.lastDiscountRefresh = _o_.lastDiscountRefresh;
	}

	public void copyFrom(FactionDepot _o_) {
        this.fid = _o_.fid;
        this.items = new java.util.concurrent.ConcurrentHashMap<Integer, FactionItemInfo>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, new FactionItemInfo(_v_)));
        this.resources = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.resources.forEach((_k_, _v_) -> this.resources.put(_k_, _v_));
        this.itemDiscountMap = new java.util.concurrent.ConcurrentHashMap<Integer, ItemDiscount>();
		_o_.itemDiscountMap.forEach((_k_, _v_) -> this.itemDiscountMap.put(_k_, new ItemDiscount(_v_)));
        this.lastDailyRefresh = _o_.lastDailyRefresh;
        this.lastDiscountRefresh = _o_.lastDiscountRefresh;
	}


	@JSONField(name = "1")
	public int getFid(){
		return this.fid;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionItemInfo> getItems(){
		return this.items;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getResources(){
		return this.resources;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, ItemDiscount> getItemDiscountMap(){
		return this.itemDiscountMap;
	}

	@JSONField(name = "4")
	public long getLastDailyRefresh(){
		return this.lastDailyRefresh;
	}

	@JSONField(name = "5")
	public long getLastDiscountRefresh(){
		return this.lastDiscountRefresh;
	}

	@JSONField(name = "1")
	public void setFid(int _v_){
		this.fid = _v_;
	}

	@JSONField(name = "2")
	public void setItems(java.util.concurrent.ConcurrentHashMap<Integer, FactionItemInfo> _v_){
		this.items = _v_;
	}

	@JSONField(name = "3")
	public void setResources(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.resources = _v_;
	}

	@JSONField(name = "6")
	public void setItemDiscountMap(java.util.concurrent.ConcurrentHashMap<Integer, ItemDiscount> _v_){
		this.itemDiscountMap = _v_;
	}

	@JSONField(name = "4")
	public void setLastDailyRefresh(long _v_){
		this.lastDailyRefresh = _v_;
	}

	@JSONField(name = "5")
	public void setLastDiscountRefresh(long _v_){
		this.lastDiscountRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionDepot) {
			FactionDepot _o_ = (FactionDepot)_o1_;
			if (this.fid != _o_.fid) return false;
			if (!this.items.equals(_o_.items)) return false;
			if (!this.resources.equals(_o_.resources)) return false;
			if (!this.itemDiscountMap.equals(_o_.itemDiscountMap)) return false;
			if (this.lastDailyRefresh != _o_.lastDailyRefresh) return false;
			if (this.lastDiscountRefresh != _o_.lastDiscountRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.fid);
		_h_ += _h_ * 31 + this.items.hashCode();
		_h_ += _h_ * 31 + this.resources.hashCode();
		_h_ += _h_ * 31 + this.itemDiscountMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastDailyRefresh ^ (lastDailyRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(lastDiscountRefresh ^ (lastDiscountRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(fid).append(",");
		_sb_.append(items).append(",");
		_sb_.append(resources).append(",");
		_sb_.append(itemDiscountMap).append(",");
		_sb_.append(lastDailyRefresh).append(",");
		_sb_.append(lastDiscountRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
