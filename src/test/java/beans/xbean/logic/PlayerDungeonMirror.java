package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerDungeonMirror extends warrior.mdb.XBean {
    private int dungeonId;  // 副本Id
    private long offlineTime;  // 玩家离线时间
    private long posIndex;  // 副本所在位置 战区->区域->x坐标->y坐标
    private long endTime;  // 该副本销毁时间
    private java.util.concurrent.ConcurrentHashMap<Integer, EventMirror> eventMap;  // 事件数据
    private java.util.concurrent.ConcurrentHashMap<Long, Obstacles> hurtObstacles;  // 受伤的的静态阻挡物
    private String dungeonDataJson;  // dungeonData类json串
    private java.util.concurrent.ConcurrentHashMap<Long, DungeonResource> resources;  // 全部资源
    private java.util.concurrent.ConcurrentHashMap<Long, DropItem> dropItems;  // 掉落数据

    PlayerDungeonMirror(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.eventMap = new java.util.concurrent.ConcurrentHashMap<Integer, EventMirror>();
		this.hurtObstacles = new java.util.concurrent.ConcurrentHashMap<Long, Obstacles>();
		this.dungeonDataJson = "";
		this.resources = new java.util.concurrent.ConcurrentHashMap<Long, DungeonResource>();
		this.dropItems = new java.util.concurrent.ConcurrentHashMap<Long, DropItem>();
	}

	public PlayerDungeonMirror() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerDungeonMirror(PlayerDungeonMirror _o_) {
		this(_o_, null);
	}

	PlayerDungeonMirror(PlayerDungeonMirror _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.dungeonId = _o_.dungeonId;
		this.offlineTime = _o_.offlineTime;
		this.posIndex = _o_.posIndex;
		this.endTime = _o_.endTime;
		this.eventMap = new java.util.concurrent.ConcurrentHashMap<Integer, EventMirror>();
		_o_.eventMap.forEach((_k_, _v_) -> this.eventMap.put(_k_, new EventMirror(_v_)));
		this.hurtObstacles = new java.util.concurrent.ConcurrentHashMap<Long, Obstacles>();
		_o_.hurtObstacles.forEach((_k_, _v_) -> this.hurtObstacles.put(_k_, new Obstacles(_v_)));
		this.dungeonDataJson = _o_.dungeonDataJson;
		this.resources = new java.util.concurrent.ConcurrentHashMap<Long, DungeonResource>();
		_o_.resources.forEach((_k_, _v_) -> this.resources.put(_k_, new DungeonResource(_v_)));
		this.dropItems = new java.util.concurrent.ConcurrentHashMap<Long, DropItem>();
		_o_.dropItems.forEach((_k_, _v_) -> this.dropItems.put(_k_, new DropItem(_v_)));
	}

	public void copyFrom(PlayerDungeonMirror _o_) {
        this.dungeonId = _o_.dungeonId;
        this.offlineTime = _o_.offlineTime;
        this.posIndex = _o_.posIndex;
        this.endTime = _o_.endTime;
        this.eventMap = new java.util.concurrent.ConcurrentHashMap<Integer, EventMirror>();
		_o_.eventMap.forEach((_k_, _v_) -> this.eventMap.put(_k_, new EventMirror(_v_)));
        this.hurtObstacles = new java.util.concurrent.ConcurrentHashMap<Long, Obstacles>();
		_o_.hurtObstacles.forEach((_k_, _v_) -> this.hurtObstacles.put(_k_, new Obstacles(_v_)));
        this.dungeonDataJson = _o_.dungeonDataJson;
        this.resources = new java.util.concurrent.ConcurrentHashMap<Long, DungeonResource>();
		_o_.resources.forEach((_k_, _v_) -> this.resources.put(_k_, new DungeonResource(_v_)));
        this.dropItems = new java.util.concurrent.ConcurrentHashMap<Long, DropItem>();
		_o_.dropItems.forEach((_k_, _v_) -> this.dropItems.put(_k_, new DropItem(_v_)));
	}


	@JSONField(name = "1")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "2")
	public long getOfflineTime(){
		return this.offlineTime;
	}

	@JSONField(name = "3")
	public long getPosIndex(){
		return this.posIndex;
	}

	@JSONField(name = "4")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, EventMirror> getEventMap(){
		return this.eventMap;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Long, Obstacles> getHurtObstacles(){
		return this.hurtObstacles;
	}

	@JSONField(name = "10")
	public String getDungeonDataJson(){
		return this.dungeonDataJson;
	}

	@JSONField(name = "11")
	public java.util.concurrent.ConcurrentHashMap<Long, DungeonResource> getResources(){
		return this.resources;
	}

	@JSONField(name = "12")
	public java.util.concurrent.ConcurrentHashMap<Long, DropItem> getDropItems(){
		return this.dropItems;
	}

	@JSONField(name = "1")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "2")
	public void setOfflineTime(long _v_){
		this.offlineTime = _v_;
	}

	@JSONField(name = "3")
	public void setPosIndex(long _v_){
		this.posIndex = _v_;
	}

	@JSONField(name = "4")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "5")
	public void setEventMap(java.util.concurrent.ConcurrentHashMap<Integer, EventMirror> _v_){
		this.eventMap = _v_;
	}

	@JSONField(name = "6")
	public void setHurtObstacles(java.util.concurrent.ConcurrentHashMap<Long, Obstacles> _v_){
		this.hurtObstacles = _v_;
	}

	@JSONField(name = "10")
	public void setDungeonDataJson(String _v_){
		this.dungeonDataJson = _v_;
	}

	@JSONField(name = "11")
	public void setResources(java.util.concurrent.ConcurrentHashMap<Long, DungeonResource> _v_){
		this.resources = _v_;
	}

	@JSONField(name = "12")
	public void setDropItems(java.util.concurrent.ConcurrentHashMap<Long, DropItem> _v_){
		this.dropItems = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerDungeonMirror) {
			PlayerDungeonMirror _o_ = (PlayerDungeonMirror)_o1_;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (this.offlineTime != _o_.offlineTime) return false;
			if (this.posIndex != _o_.posIndex) return false;
			if (this.endTime != _o_.endTime) return false;
			if (!this.eventMap.equals(_o_.eventMap)) return false;
			if (!this.hurtObstacles.equals(_o_.hurtObstacles)) return false;
			if (!this.dungeonDataJson.equals(_o_.dungeonDataJson)) return false;
			if (!this.resources.equals(_o_.resources)) return false;
			if (!this.dropItems.equals(_o_.dropItems)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + (int)(offlineTime ^ (offlineTime >>> 32));
		_h_ += _h_ * 31 + (int)(posIndex ^ (posIndex >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + this.eventMap.hashCode();
		_h_ += _h_ * 31 + this.hurtObstacles.hashCode();
		_h_ += _h_ * 31 + this.dungeonDataJson.hashCode();
		_h_ += _h_ * 31 + this.resources.hashCode();
		_h_ += _h_ * 31 + this.dropItems.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(dungeonId).append(",");
		_sb_.append(offlineTime).append(",");
		_sb_.append(posIndex).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(eventMap).append(",");
		_sb_.append(hurtObstacles).append(",");
		_sb_.append("T").append(dungeonDataJson.length()).append(",");
		_sb_.append(resources).append(",");
		_sb_.append(dropItems).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
