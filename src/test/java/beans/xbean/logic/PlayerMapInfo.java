package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMapInfo extends warrior.mdb.XBean {
    private xbean.logic.MapInfo staticMap;  // 静态地图(主城)信息
    private xbean.logic.MapInfo dynamicMap;  // 动态地图信息
    private int dungeonId;  // 所在副本id
    private int lastCityAreaId;  // 最新所在的主城的区域id
    private xbean.logic.EnteringMap enteringMap;  // 正在进入地图的地图数据

    PlayerMapInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.staticMap = new MapInfo(this);
		this.dynamicMap = new MapInfo(this);
		this.enteringMap = new EnteringMap(this);
	}

	public PlayerMapInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMapInfo(PlayerMapInfo _o_) {
		this(_o_, null);
	}

	PlayerMapInfo(PlayerMapInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.staticMap = new MapInfo(_o_.staticMap, this);
		this.dynamicMap = new MapInfo(_o_.dynamicMap, this);
		this.dungeonId = _o_.dungeonId;
		this.lastCityAreaId = _o_.lastCityAreaId;
		this.enteringMap = new EnteringMap(_o_.enteringMap, this);
	}

	public void copyFrom(PlayerMapInfo _o_) {
        this.staticMap = new MapInfo(_o_.staticMap, this);
        this.dynamicMap = new MapInfo(_o_.dynamicMap, this);
        this.dungeonId = _o_.dungeonId;
        this.lastCityAreaId = _o_.lastCityAreaId;
        this.enteringMap = new EnteringMap(_o_.enteringMap, this);
	}


	@JSONField(name = "1")
	public xbean.logic.MapInfo getStaticMap(){
		return this.staticMap;
	}

	@JSONField(name = "2")
	public xbean.logic.MapInfo getDynamicMap(){
		return this.dynamicMap;
	}

	@JSONField(name = "3")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "4")
	public int getLastCityAreaId(){
		return this.lastCityAreaId;
	}

	@JSONField(name = "5")
	public xbean.logic.EnteringMap getEnteringMap(){
		return this.enteringMap;
	}

	@JSONField(name = "1")
	public void setStaticMap(xbean.logic.MapInfo _v_){
		this.staticMap = _v_;
	}

	@JSONField(name = "2")
	public void setDynamicMap(xbean.logic.MapInfo _v_){
		this.dynamicMap = _v_;
	}

	@JSONField(name = "3")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "4")
	public void setLastCityAreaId(int _v_){
		this.lastCityAreaId = _v_;
	}

	@JSONField(name = "5")
	public void setEnteringMap(xbean.logic.EnteringMap _v_){
		this.enteringMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMapInfo) {
			PlayerMapInfo _o_ = (PlayerMapInfo)_o1_;
			if (!this.staticMap.equals(_o_.staticMap)) return false;
			if (!this.dynamicMap.equals(_o_.dynamicMap)) return false;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (this.lastCityAreaId != _o_.lastCityAreaId) return false;
			if (!this.enteringMap.equals(_o_.enteringMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.staticMap.hashCode();
		_h_ += _h_ * 31 + this.dynamicMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + (int)(this.lastCityAreaId);
		_h_ += _h_ * 31 + this.enteringMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(staticMap).append(",");
		_sb_.append(dynamicMap).append(",");
		_sb_.append(dungeonId).append(",");
		_sb_.append(lastCityAreaId).append(",");
		_sb_.append(enteringMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
