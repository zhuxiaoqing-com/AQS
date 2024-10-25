package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class EnterMapInfo extends warrior.mdb.XBean {
    private long sceneId; 
    private int mapAreaId; 
    private int dungeonId; 
    private int mapType; 
    private int line; 
    private long groupId; 
    private int serverGroupId; 
    private xbean.lobby.DungeonParam dungeonParam; 

    EnterMapInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dungeonParam = new DungeonParam(this);
	}

	public EnterMapInfo() {
		this((warrior.mdb.XBean)null);
	}

	public EnterMapInfo(EnterMapInfo _o_) {
		this(_o_, null);
	}

	EnterMapInfo(EnterMapInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.sceneId = _o_.sceneId;
		this.mapAreaId = _o_.mapAreaId;
		this.dungeonId = _o_.dungeonId;
		this.mapType = _o_.mapType;
		this.line = _o_.line;
		this.groupId = _o_.groupId;
		this.serverGroupId = _o_.serverGroupId;
		this.dungeonParam = new DungeonParam(_o_.dungeonParam, this);
	}

	public void copyFrom(EnterMapInfo _o_) {
        this.sceneId = _o_.sceneId;
        this.mapAreaId = _o_.mapAreaId;
        this.dungeonId = _o_.dungeonId;
        this.mapType = _o_.mapType;
        this.line = _o_.line;
        this.groupId = _o_.groupId;
        this.serverGroupId = _o_.serverGroupId;
        this.dungeonParam = new DungeonParam(_o_.dungeonParam, this);
	}


	@JSONField(name = "1")
	public long getSceneId(){
		return this.sceneId;
	}

	@JSONField(name = "2")
	public int getMapAreaId(){
		return this.mapAreaId;
	}

	@JSONField(name = "3")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "4")
	public int getMapType(){
		return this.mapType;
	}

	@JSONField(name = "5")
	public int getLine(){
		return this.line;
	}

	@JSONField(name = "6")
	public long getGroupId(){
		return this.groupId;
	}

	@JSONField(name = "7")
	public int getServerGroupId(){
		return this.serverGroupId;
	}

	@JSONField(name = "8")
	public xbean.lobby.DungeonParam getDungeonParam(){
		return this.dungeonParam;
	}

	@JSONField(name = "1")
	public void setSceneId(long _v_){
		this.sceneId = _v_;
	}

	@JSONField(name = "2")
	public void setMapAreaId(int _v_){
		this.mapAreaId = _v_;
	}

	@JSONField(name = "3")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "4")
	public void setMapType(int _v_){
		this.mapType = _v_;
	}

	@JSONField(name = "5")
	public void setLine(int _v_){
		this.line = _v_;
	}

	@JSONField(name = "6")
	public void setGroupId(long _v_){
		this.groupId = _v_;
	}

	@JSONField(name = "7")
	public void setServerGroupId(int _v_){
		this.serverGroupId = _v_;
	}

	@JSONField(name = "8")
	public void setDungeonParam(xbean.lobby.DungeonParam _v_){
		this.dungeonParam = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EnterMapInfo) {
			EnterMapInfo _o_ = (EnterMapInfo)_o1_;
			if (this.sceneId != _o_.sceneId) return false;
			if (this.mapAreaId != _o_.mapAreaId) return false;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (this.mapType != _o_.mapType) return false;
			if (this.line != _o_.line) return false;
			if (this.groupId != _o_.groupId) return false;
			if (this.serverGroupId != _o_.serverGroupId) return false;
			if (!this.dungeonParam.equals(_o_.dungeonParam)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(sceneId ^ (sceneId >>> 32));
		_h_ += _h_ * 31 + (int)(this.mapAreaId);
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + (int)(this.mapType);
		_h_ += _h_ * 31 + (int)(this.line);
		_h_ += _h_ * 31 + (int)(groupId ^ (groupId >>> 32));
		_h_ += _h_ * 31 + (int)(this.serverGroupId);
		_h_ += _h_ * 31 + this.dungeonParam.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(sceneId).append(",");
		_sb_.append(mapAreaId).append(",");
		_sb_.append(dungeonId).append(",");
		_sb_.append(mapType).append(",");
		_sb_.append(line).append(",");
		_sb_.append(groupId).append(",");
		_sb_.append(serverGroupId).append(",");
		_sb_.append(dungeonParam).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
