package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerHome extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, HomeBuilding> build;  // 家园建筑
    private java.util.concurrent.ConcurrentHashMap<Integer, HomeUnlockRegion> unlock;  // 已解锁家园区域信息
    private xbean.logic.Dungeon dungeon;  // 家园地图
    private java.util.concurrent.ConcurrentHashMap<Long, Worker> workerMap;  // key:工人srl  value:工人类
    private int homeBuildId;  // 家园所绑定的城市的区域地图Id
    private java.util.concurrent.ConcurrentHashMap<Integer, CollectionInfo> collectionsInfo;  // 采集物等级信息

    PlayerHome(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.build = new java.util.concurrent.ConcurrentHashMap<Long, HomeBuilding>();
		this.unlock = new java.util.concurrent.ConcurrentHashMap<Integer, HomeUnlockRegion>();
		this.dungeon = new Dungeon(this);
		this.workerMap = new java.util.concurrent.ConcurrentHashMap<Long, Worker>();
		this.collectionsInfo = new java.util.concurrent.ConcurrentHashMap<Integer, CollectionInfo>();
	}

	public PlayerHome() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerHome(PlayerHome _o_) {
		this(_o_, null);
	}

	PlayerHome(PlayerHome _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.build = new java.util.concurrent.ConcurrentHashMap<Long, HomeBuilding>();
		_o_.build.forEach((_k_, _v_) -> this.build.put(_k_, new HomeBuilding(_v_)));
		this.unlock = new java.util.concurrent.ConcurrentHashMap<Integer, HomeUnlockRegion>();
		_o_.unlock.forEach((_k_, _v_) -> this.unlock.put(_k_, new HomeUnlockRegion(_v_)));
		this.dungeon = new Dungeon(_o_.dungeon, this);
		this.workerMap = new java.util.concurrent.ConcurrentHashMap<Long, Worker>();
		_o_.workerMap.forEach((_k_, _v_) -> this.workerMap.put(_k_, new Worker(_v_)));
		this.homeBuildId = _o_.homeBuildId;
		this.collectionsInfo = new java.util.concurrent.ConcurrentHashMap<Integer, CollectionInfo>();
		_o_.collectionsInfo.forEach((_k_, _v_) -> this.collectionsInfo.put(_k_, new CollectionInfo(_v_)));
	}

	public void copyFrom(PlayerHome _o_) {
        this.build = new java.util.concurrent.ConcurrentHashMap<Long, HomeBuilding>();
		_o_.build.forEach((_k_, _v_) -> this.build.put(_k_, new HomeBuilding(_v_)));
        this.unlock = new java.util.concurrent.ConcurrentHashMap<Integer, HomeUnlockRegion>();
		_o_.unlock.forEach((_k_, _v_) -> this.unlock.put(_k_, new HomeUnlockRegion(_v_)));
        this.dungeon = new Dungeon(_o_.dungeon, this);
        this.workerMap = new java.util.concurrent.ConcurrentHashMap<Long, Worker>();
		_o_.workerMap.forEach((_k_, _v_) -> this.workerMap.put(_k_, new Worker(_v_)));
        this.homeBuildId = _o_.homeBuildId;
        this.collectionsInfo = new java.util.concurrent.ConcurrentHashMap<Integer, CollectionInfo>();
		_o_.collectionsInfo.forEach((_k_, _v_) -> this.collectionsInfo.put(_k_, new CollectionInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, HomeBuilding> getBuild(){
		return this.build;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, HomeUnlockRegion> getUnlock(){
		return this.unlock;
	}

	@JSONField(name = "3")
	public xbean.logic.Dungeon getDungeon(){
		return this.dungeon;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Long, Worker> getWorkerMap(){
		return this.workerMap;
	}

	@JSONField(name = "6")
	public int getHomeBuildId(){
		return this.homeBuildId;
	}

	@JSONField(name = "7")
	public java.util.concurrent.ConcurrentHashMap<Integer, CollectionInfo> getCollectionsInfo(){
		return this.collectionsInfo;
	}

	@JSONField(name = "1")
	public void setBuild(java.util.concurrent.ConcurrentHashMap<Long, HomeBuilding> _v_){
		this.build = _v_;
	}

	@JSONField(name = "2")
	public void setUnlock(java.util.concurrent.ConcurrentHashMap<Integer, HomeUnlockRegion> _v_){
		this.unlock = _v_;
	}

	@JSONField(name = "3")
	public void setDungeon(xbean.logic.Dungeon _v_){
		this.dungeon = _v_;
	}

	@JSONField(name = "4")
	public void setWorkerMap(java.util.concurrent.ConcurrentHashMap<Long, Worker> _v_){
		this.workerMap = _v_;
	}

	@JSONField(name = "6")
	public void setHomeBuildId(int _v_){
		this.homeBuildId = _v_;
	}

	@JSONField(name = "7")
	public void setCollectionsInfo(java.util.concurrent.ConcurrentHashMap<Integer, CollectionInfo> _v_){
		this.collectionsInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerHome) {
			PlayerHome _o_ = (PlayerHome)_o1_;
			if (!this.build.equals(_o_.build)) return false;
			if (!this.unlock.equals(_o_.unlock)) return false;
			if (!this.dungeon.equals(_o_.dungeon)) return false;
			if (!this.workerMap.equals(_o_.workerMap)) return false;
			if (this.homeBuildId != _o_.homeBuildId) return false;
			if (!this.collectionsInfo.equals(_o_.collectionsInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.build.hashCode();
		_h_ += _h_ * 31 + this.unlock.hashCode();
		_h_ += _h_ * 31 + this.dungeon.hashCode();
		_h_ += _h_ * 31 + this.workerMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.homeBuildId);
		_h_ += _h_ * 31 + this.collectionsInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(build).append(",");
		_sb_.append(unlock).append(",");
		_sb_.append(dungeon).append(",");
		_sb_.append(workerMap).append(",");
		_sb_.append(homeBuildId).append(",");
		_sb_.append(collectionsInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
