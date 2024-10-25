package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensDepot extends warrior.mdb.XBean {
    private long gid;  // 公会id
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> resources;  // resourceId->num
    private java.util.concurrent.ConcurrentHashMap<Long, LBItem> items;  // itemSrl->lbItem
    private java.util.concurrent.ConcurrentHashMap<Long, DepotResCollect> resCollectInfo;  // playerId->DepotResCollect
    private long lastFreshTime;  // 上次采集信息日志时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> itemMap;  // itemId->num

    GensDepot(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.resources = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.items = new java.util.concurrent.ConcurrentHashMap<Long, LBItem>();
		this.resCollectInfo = new java.util.concurrent.ConcurrentHashMap<Long, DepotResCollect>();
		this.itemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GensDepot() {
		this((warrior.mdb.XBean)null);
	}

	public GensDepot(GensDepot _o_) {
		this(_o_, null);
	}

	GensDepot(GensDepot _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gid = _o_.gid;
		this.resources = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.resources.forEach((_k_, _v_) -> this.resources.put(_k_, _v_));
		this.items = new java.util.concurrent.ConcurrentHashMap<Long, LBItem>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, new LBItem(_v_)));
		this.resCollectInfo = new java.util.concurrent.ConcurrentHashMap<Long, DepotResCollect>();
		_o_.resCollectInfo.forEach((_k_, _v_) -> this.resCollectInfo.put(_k_, new DepotResCollect(_v_)));
		this.lastFreshTime = _o_.lastFreshTime;
		this.itemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.itemMap.forEach((_k_, _v_) -> this.itemMap.put(_k_, _v_));
	}

	public void copyFrom(GensDepot _o_) {
        this.gid = _o_.gid;
        this.resources = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.resources.forEach((_k_, _v_) -> this.resources.put(_k_, _v_));
        this.items = new java.util.concurrent.ConcurrentHashMap<Long, LBItem>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, new LBItem(_v_)));
        this.resCollectInfo = new java.util.concurrent.ConcurrentHashMap<Long, DepotResCollect>();
		_o_.resCollectInfo.forEach((_k_, _v_) -> this.resCollectInfo.put(_k_, new DepotResCollect(_v_)));
        this.lastFreshTime = _o_.lastFreshTime;
        this.itemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.itemMap.forEach((_k_, _v_) -> this.itemMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getResources(){
		return this.resources;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Long, LBItem> getItems(){
		return this.items;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Long, DepotResCollect> getResCollectInfo(){
		return this.resCollectInfo;
	}

	@JSONField(name = "5")
	public long getLastFreshTime(){
		return this.lastFreshTime;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getItemMap(){
		return this.itemMap;
	}

	@JSONField(name = "1")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "2")
	public void setResources(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.resources = _v_;
	}

	@JSONField(name = "3")
	public void setItems(java.util.concurrent.ConcurrentHashMap<Long, LBItem> _v_){
		this.items = _v_;
	}

	@JSONField(name = "4")
	public void setResCollectInfo(java.util.concurrent.ConcurrentHashMap<Long, DepotResCollect> _v_){
		this.resCollectInfo = _v_;
	}

	@JSONField(name = "5")
	public void setLastFreshTime(long _v_){
		this.lastFreshTime = _v_;
	}

	@JSONField(name = "6")
	public void setItemMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.itemMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensDepot) {
			GensDepot _o_ = (GensDepot)_o1_;
			if (this.gid != _o_.gid) return false;
			if (!this.resources.equals(_o_.resources)) return false;
			if (!this.items.equals(_o_.items)) return false;
			if (!this.resCollectInfo.equals(_o_.resCollectInfo)) return false;
			if (this.lastFreshTime != _o_.lastFreshTime) return false;
			if (!this.itemMap.equals(_o_.itemMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + this.resources.hashCode();
		_h_ += _h_ * 31 + this.items.hashCode();
		_h_ += _h_ * 31 + this.resCollectInfo.hashCode();
		_h_ += _h_ * 31 + (int)(lastFreshTime ^ (lastFreshTime >>> 32));
		_h_ += _h_ * 31 + this.itemMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gid).append(",");
		_sb_.append(resources).append(",");
		_sb_.append(items).append(",");
		_sb_.append(resCollectInfo).append(",");
		_sb_.append(lastFreshTime).append(",");
		_sb_.append(itemMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
