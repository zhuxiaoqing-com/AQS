package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionStore extends warrior.mdb.XBean {
    private int fid;  // 阵营id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> items;  // itemid->num
    private long lastDailyRefresh; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> shopInfo; 

    FactionStore(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.shopInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public FactionStore() {
		this((warrior.mdb.XBean)null);
	}

	public FactionStore(FactionStore _o_) {
		this(_o_, null);
	}

	FactionStore(FactionStore _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.fid = _o_.fid;
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, _v_));
		this.lastDailyRefresh = _o_.lastDailyRefresh;
		this.shopInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.shopInfo.forEach((_k_, _v_) -> this.shopInfo.put(_k_, _v_));
	}

	public void copyFrom(FactionStore _o_) {
        this.fid = _o_.fid;
        this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, _v_));
        this.lastDailyRefresh = _o_.lastDailyRefresh;
        this.shopInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.shopInfo.forEach((_k_, _v_) -> this.shopInfo.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getFid(){
		return this.fid;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getItems(){
		return this.items;
	}

	@JSONField(name = "3")
	public long getLastDailyRefresh(){
		return this.lastDailyRefresh;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getShopInfo(){
		return this.shopInfo;
	}

	@JSONField(name = "1")
	public void setFid(int _v_){
		this.fid = _v_;
	}

	@JSONField(name = "2")
	public void setItems(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.items = _v_;
	}

	@JSONField(name = "3")
	public void setLastDailyRefresh(long _v_){
		this.lastDailyRefresh = _v_;
	}

	@JSONField(name = "4")
	public void setShopInfo(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.shopInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionStore) {
			FactionStore _o_ = (FactionStore)_o1_;
			if (this.fid != _o_.fid) return false;
			if (!this.items.equals(_o_.items)) return false;
			if (this.lastDailyRefresh != _o_.lastDailyRefresh) return false;
			if (!this.shopInfo.equals(_o_.shopInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.fid);
		_h_ += _h_ * 31 + this.items.hashCode();
		_h_ += _h_ * 31 + (int)(lastDailyRefresh ^ (lastDailyRefresh >>> 32));
		_h_ += _h_ * 31 + this.shopInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(fid).append(",");
		_sb_.append(items).append(",");
		_sb_.append(lastDailyRefresh).append(",");
		_sb_.append(shopInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
