package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class BTPlayerInfo extends warrior.mdb.XBean {
    private int battleServerId; 
    private long sceneId; 
    private int dungeonId; 
    private int mapAreaId; 
    private int line; 
    private int activityId; 
    private int serverGroupId; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> reviveMap;  // k:复活类型  v:复活次数
    private long deathTime;  // 玩家死亡时间
    private int wildAreaId;  // 所在野外区域id
    private String pbPBEnterMapInfoBytes;  // 进入地图数据Json

    BTPlayerInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.reviveMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.pbPBEnterMapInfoBytes = "";
	}

	public BTPlayerInfo() {
		this((warrior.mdb.XBean)null);
	}

	public BTPlayerInfo(BTPlayerInfo _o_) {
		this(_o_, null);
	}

	BTPlayerInfo(BTPlayerInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.battleServerId = _o_.battleServerId;
		this.sceneId = _o_.sceneId;
		this.dungeonId = _o_.dungeonId;
		this.mapAreaId = _o_.mapAreaId;
		this.line = _o_.line;
		this.activityId = _o_.activityId;
		this.serverGroupId = _o_.serverGroupId;
		this.reviveMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.reviveMap.forEach((_k_, _v_) -> this.reviveMap.put(_k_, _v_));
		this.deathTime = _o_.deathTime;
		this.wildAreaId = _o_.wildAreaId;
		this.pbPBEnterMapInfoBytes = _o_.pbPBEnterMapInfoBytes;
	}

	public void copyFrom(BTPlayerInfo _o_) {
        this.battleServerId = _o_.battleServerId;
        this.sceneId = _o_.sceneId;
        this.dungeonId = _o_.dungeonId;
        this.mapAreaId = _o_.mapAreaId;
        this.line = _o_.line;
        this.activityId = _o_.activityId;
        this.serverGroupId = _o_.serverGroupId;
        this.reviveMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.reviveMap.forEach((_k_, _v_) -> this.reviveMap.put(_k_, _v_));
        this.deathTime = _o_.deathTime;
        this.wildAreaId = _o_.wildAreaId;
        this.pbPBEnterMapInfoBytes = _o_.pbPBEnterMapInfoBytes;
	}


	@JSONField(name = "1")
	public int getBattleServerId(){
		return this.battleServerId;
	}

	@JSONField(name = "2")
	public long getSceneId(){
		return this.sceneId;
	}

	@JSONField(name = "3")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "4")
	public int getMapAreaId(){
		return this.mapAreaId;
	}

	@JSONField(name = "5")
	public int getLine(){
		return this.line;
	}

	@JSONField(name = "6")
	public int getActivityId(){
		return this.activityId;
	}

	@JSONField(name = "7")
	public int getServerGroupId(){
		return this.serverGroupId;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getReviveMap(){
		return this.reviveMap;
	}

	@JSONField(name = "9")
	public long getDeathTime(){
		return this.deathTime;
	}

	@JSONField(name = "10")
	public int getWildAreaId(){
		return this.wildAreaId;
	}

	@JSONField(name = "11")
	public String getPbPBEnterMapInfoBytes(){
		return this.pbPBEnterMapInfoBytes;
	}

	@JSONField(name = "1")
	public void setBattleServerId(int _v_){
		this.battleServerId = _v_;
	}

	@JSONField(name = "2")
	public void setSceneId(long _v_){
		this.sceneId = _v_;
	}

	@JSONField(name = "3")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "4")
	public void setMapAreaId(int _v_){
		this.mapAreaId = _v_;
	}

	@JSONField(name = "5")
	public void setLine(int _v_){
		this.line = _v_;
	}

	@JSONField(name = "6")
	public void setActivityId(int _v_){
		this.activityId = _v_;
	}

	@JSONField(name = "7")
	public void setServerGroupId(int _v_){
		this.serverGroupId = _v_;
	}

	@JSONField(name = "8")
	public void setReviveMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.reviveMap = _v_;
	}

	@JSONField(name = "9")
	public void setDeathTime(long _v_){
		this.deathTime = _v_;
	}

	@JSONField(name = "10")
	public void setWildAreaId(int _v_){
		this.wildAreaId = _v_;
	}

	@JSONField(name = "11")
	public void setPbPBEnterMapInfoBytes(String _v_){
		this.pbPBEnterMapInfoBytes = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BTPlayerInfo) {
			BTPlayerInfo _o_ = (BTPlayerInfo)_o1_;
			if (this.battleServerId != _o_.battleServerId) return false;
			if (this.sceneId != _o_.sceneId) return false;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (this.mapAreaId != _o_.mapAreaId) return false;
			if (this.line != _o_.line) return false;
			if (this.activityId != _o_.activityId) return false;
			if (this.serverGroupId != _o_.serverGroupId) return false;
			if (!this.reviveMap.equals(_o_.reviveMap)) return false;
			if (this.deathTime != _o_.deathTime) return false;
			if (this.wildAreaId != _o_.wildAreaId) return false;
			if (!this.pbPBEnterMapInfoBytes.equals(_o_.pbPBEnterMapInfoBytes)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.battleServerId);
		_h_ += _h_ * 31 + (int)(sceneId ^ (sceneId >>> 32));
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + (int)(this.mapAreaId);
		_h_ += _h_ * 31 + (int)(this.line);
		_h_ += _h_ * 31 + (int)(this.activityId);
		_h_ += _h_ * 31 + (int)(this.serverGroupId);
		_h_ += _h_ * 31 + this.reviveMap.hashCode();
		_h_ += _h_ * 31 + (int)(deathTime ^ (deathTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.wildAreaId);
		_h_ += _h_ * 31 + this.pbPBEnterMapInfoBytes.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(battleServerId).append(",");
		_sb_.append(sceneId).append(",");
		_sb_.append(dungeonId).append(",");
		_sb_.append(mapAreaId).append(",");
		_sb_.append(line).append(",");
		_sb_.append(activityId).append(",");
		_sb_.append(serverGroupId).append(",");
		_sb_.append(reviveMap).append(",");
		_sb_.append(deathTime).append(",");
		_sb_.append(wildAreaId).append(",");
		_sb_.append("T").append(pbPBEnterMapInfoBytes.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
