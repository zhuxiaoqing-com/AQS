package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class DungeonResource extends warrior.mdb.XBean {
    private long msrl;  // 资源关卡序列号
    private int type;  // 资源类型(怪物还是采集物)
    private int dataId;  // 怪物配表id或者资源配表id
    private int hp;  // 当前血量
    private boolean done;  // 状态 0初始状态 默认为false  怪物、采集物死亡时标记为true; 箱子被打开时标记为true;
    private java.util.concurrent.CopyOnWriteArrayList<Float> pos;  // 位置集合 x:0下标 z:1下标
    private java.util.concurrent.CopyOnWriteArrayList<Float> dir;  // 方向集合 x:0下标 y:1下标 z:2下标
    private xbean.logic.Bag bag;  // bag
    private long lastcollectd;  // 上次采集时间
    private long deathd;  // 资源死亡时间
    private int mapMarkId;  // mapMarkId 地图怪物用来索引坐标和寻路点 还有策划通过这个来细化操纵怪物
    private int specialId;  // 特殊id 载具怪是下车载具配置Id 炮台类工事是公式cfgId
    private long specialSrl;  // 特殊srl 工人怪物是家园工人的srl
    private long expireTime;  // 到这个时间该怪物过期(删除该怪物); 小于等于0 说明永远不过期
    private long lastRefresh;  // 上一次刷新时间;这个字段是给 配置了固定刷新时间的采集物 使用的
    private int bulletId;  // 炮台类工事是子弹id
    private int bulletNum;  // 炮台类工事是子弹数量
    private int armorPoint;  // 当前护甲值
    private int masterType;  // 主人type（只有召唤物有）
    private long masterSrl;  // 主人srl（只有召唤物有）
    private int currLoc;  // 当前持有槽位(只有护卫有用)
    private java.util.concurrent.ConcurrentHashMap<Integer, Float> specialAttr;  // 怪物的特殊属性记录 如毒气弹的固定增伤属性

    DungeonResource(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.pos = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		this.dir = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		this.bag = new Bag(this);
		this.specialAttr = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
	}

	public DungeonResource() {
		this((warrior.mdb.XBean)null);
	}

	public DungeonResource(DungeonResource _o_) {
		this(_o_, null);
	}

	DungeonResource(DungeonResource _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.msrl = _o_.msrl;
		this.type = _o_.type;
		this.dataId = _o_.dataId;
		this.hp = _o_.hp;
		this.done = _o_.done;
		this.pos = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		_o_.pos.forEach(_v_ -> this.pos.add(_v_));
		this.dir = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		_o_.dir.forEach(_v_ -> this.dir.add(_v_));
		this.bag = new Bag(_o_.bag, this);
		this.lastcollectd = _o_.lastcollectd;
		this.deathd = _o_.deathd;
		this.mapMarkId = _o_.mapMarkId;
		this.specialId = _o_.specialId;
		this.specialSrl = _o_.specialSrl;
		this.expireTime = _o_.expireTime;
		this.lastRefresh = _o_.lastRefresh;
		this.bulletId = _o_.bulletId;
		this.bulletNum = _o_.bulletNum;
		this.armorPoint = _o_.armorPoint;
		this.masterType = _o_.masterType;
		this.masterSrl = _o_.masterSrl;
		this.currLoc = _o_.currLoc;
		this.specialAttr = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.specialAttr.forEach((_k_, _v_) -> this.specialAttr.put(_k_, _v_));
	}

	public void copyFrom(DungeonResource _o_) {
        this.msrl = _o_.msrl;
        this.type = _o_.type;
        this.dataId = _o_.dataId;
        this.hp = _o_.hp;
        this.done = _o_.done;
        this.pos = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		_o_.pos.forEach(_v_ -> this.pos.add(_v_));
        this.dir = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		_o_.dir.forEach(_v_ -> this.dir.add(_v_));
        this.bag = new Bag(_o_.bag, this);
        this.lastcollectd = _o_.lastcollectd;
        this.deathd = _o_.deathd;
        this.mapMarkId = _o_.mapMarkId;
        this.specialId = _o_.specialId;
        this.specialSrl = _o_.specialSrl;
        this.expireTime = _o_.expireTime;
        this.lastRefresh = _o_.lastRefresh;
        this.bulletId = _o_.bulletId;
        this.bulletNum = _o_.bulletNum;
        this.armorPoint = _o_.armorPoint;
        this.masterType = _o_.masterType;
        this.masterSrl = _o_.masterSrl;
        this.currLoc = _o_.currLoc;
        this.specialAttr = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.specialAttr.forEach((_k_, _v_) -> this.specialAttr.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getMsrl(){
		return this.msrl;
	}

	@JSONField(name = "2")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "3")
	public int getDataId(){
		return this.dataId;
	}

	@JSONField(name = "4")
	public int getHp(){
		return this.hp;
	}

	@JSONField(name = "5")
	public boolean getDone(){
		return this.done;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArrayList<Float> getPos(){
		return this.pos;
	}

	@JSONField(name = "7")
	public java.util.concurrent.CopyOnWriteArrayList<Float> getDir(){
		return this.dir;
	}

	@JSONField(name = "8")
	public xbean.logic.Bag getBag(){
		return this.bag;
	}

	@JSONField(name = "9")
	public long getLastcollectd(){
		return this.lastcollectd;
	}

	@JSONField(name = "10")
	public long getDeathd(){
		return this.deathd;
	}

	@JSONField(name = "11")
	public int getMapMarkId(){
		return this.mapMarkId;
	}

	@JSONField(name = "12")
	public int getSpecialId(){
		return this.specialId;
	}

	@JSONField(name = "13")
	public long getSpecialSrl(){
		return this.specialSrl;
	}

	@JSONField(name = "14")
	public long getExpireTime(){
		return this.expireTime;
	}

	@JSONField(name = "15")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "16")
	public int getBulletId(){
		return this.bulletId;
	}

	@JSONField(name = "17")
	public int getBulletNum(){
		return this.bulletNum;
	}

	@JSONField(name = "18")
	public int getArmorPoint(){
		return this.armorPoint;
	}

	@JSONField(name = "19")
	public int getMasterType(){
		return this.masterType;
	}

	@JSONField(name = "20")
	public long getMasterSrl(){
		return this.masterSrl;
	}

	@JSONField(name = "21")
	public int getCurrLoc(){
		return this.currLoc;
	}

	@JSONField(name = "22")
	public java.util.concurrent.ConcurrentHashMap<Integer, Float> getSpecialAttr(){
		return this.specialAttr;
	}

	@JSONField(name = "1")
	public void setMsrl(long _v_){
		this.msrl = _v_;
	}

	@JSONField(name = "2")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "3")
	public void setDataId(int _v_){
		this.dataId = _v_;
	}

	@JSONField(name = "4")
	public void setHp(int _v_){
		this.hp = _v_;
	}

	@JSONField(name = "5")
	public void setDone(boolean _v_){
		this.done = _v_;
	}

	@JSONField(name = "6")
	public void setPos(java.util.concurrent.CopyOnWriteArrayList<Float> _v_){
		this.pos = _v_;
	}

	@JSONField(name = "7")
	public void setDir(java.util.concurrent.CopyOnWriteArrayList<Float> _v_){
		this.dir = _v_;
	}

	@JSONField(name = "8")
	public void setBag(xbean.logic.Bag _v_){
		this.bag = _v_;
	}

	@JSONField(name = "9")
	public void setLastcollectd(long _v_){
		this.lastcollectd = _v_;
	}

	@JSONField(name = "10")
	public void setDeathd(long _v_){
		this.deathd = _v_;
	}

	@JSONField(name = "11")
	public void setMapMarkId(int _v_){
		this.mapMarkId = _v_;
	}

	@JSONField(name = "12")
	public void setSpecialId(int _v_){
		this.specialId = _v_;
	}

	@JSONField(name = "13")
	public void setSpecialSrl(long _v_){
		this.specialSrl = _v_;
	}

	@JSONField(name = "14")
	public void setExpireTime(long _v_){
		this.expireTime = _v_;
	}

	@JSONField(name = "15")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@JSONField(name = "16")
	public void setBulletId(int _v_){
		this.bulletId = _v_;
	}

	@JSONField(name = "17")
	public void setBulletNum(int _v_){
		this.bulletNum = _v_;
	}

	@JSONField(name = "18")
	public void setArmorPoint(int _v_){
		this.armorPoint = _v_;
	}

	@JSONField(name = "19")
	public void setMasterType(int _v_){
		this.masterType = _v_;
	}

	@JSONField(name = "20")
	public void setMasterSrl(long _v_){
		this.masterSrl = _v_;
	}

	@JSONField(name = "21")
	public void setCurrLoc(int _v_){
		this.currLoc = _v_;
	}

	@JSONField(name = "22")
	public void setSpecialAttr(java.util.concurrent.ConcurrentHashMap<Integer, Float> _v_){
		this.specialAttr = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof DungeonResource) {
			DungeonResource _o_ = (DungeonResource)_o1_;
			if (this.msrl != _o_.msrl) return false;
			if (this.type != _o_.type) return false;
			if (this.dataId != _o_.dataId) return false;
			if (this.hp != _o_.hp) return false;
			if (this.done != _o_.done) return false;
			if (!this.pos.equals(_o_.pos)) return false;
			if (!this.dir.equals(_o_.dir)) return false;
			if (!this.bag.equals(_o_.bag)) return false;
			if (this.lastcollectd != _o_.lastcollectd) return false;
			if (this.deathd != _o_.deathd) return false;
			if (this.mapMarkId != _o_.mapMarkId) return false;
			if (this.specialId != _o_.specialId) return false;
			if (this.specialSrl != _o_.specialSrl) return false;
			if (this.expireTime != _o_.expireTime) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			if (this.bulletId != _o_.bulletId) return false;
			if (this.bulletNum != _o_.bulletNum) return false;
			if (this.armorPoint != _o_.armorPoint) return false;
			if (this.masterType != _o_.masterType) return false;
			if (this.masterSrl != _o_.masterSrl) return false;
			if (this.currLoc != _o_.currLoc) return false;
			if (!this.specialAttr.equals(_o_.specialAttr)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(msrl ^ (msrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(this.dataId);
		_h_ += _h_ * 31 + (int)(this.hp);
		_h_ += _h_ * 31 + (this.done ? 1231 : 1237);
		_h_ += _h_ * 31 + this.pos.hashCode();
		_h_ += _h_ * 31 + this.dir.hashCode();
		_h_ += _h_ * 31 + this.bag.hashCode();
		_h_ += _h_ * 31 + (int)(lastcollectd ^ (lastcollectd >>> 32));
		_h_ += _h_ * 31 + (int)(deathd ^ (deathd >>> 32));
		_h_ += _h_ * 31 + (int)(this.mapMarkId);
		_h_ += _h_ * 31 + (int)(this.specialId);
		_h_ += _h_ * 31 + (int)(specialSrl ^ (specialSrl >>> 32));
		_h_ += _h_ * 31 + (int)(expireTime ^ (expireTime >>> 32));
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.bulletId);
		_h_ += _h_ * 31 + (int)(this.bulletNum);
		_h_ += _h_ * 31 + (int)(this.armorPoint);
		_h_ += _h_ * 31 + (int)(this.masterType);
		_h_ += _h_ * 31 + (int)(masterSrl ^ (masterSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.currLoc);
		_h_ += _h_ * 31 + this.specialAttr.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(msrl).append(",");
		_sb_.append(type).append(",");
		_sb_.append(dataId).append(",");
		_sb_.append(hp).append(",");
		_sb_.append(done).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(dir).append(",");
		_sb_.append(bag).append(",");
		_sb_.append(lastcollectd).append(",");
		_sb_.append(deathd).append(",");
		_sb_.append(mapMarkId).append(",");
		_sb_.append(specialId).append(",");
		_sb_.append(specialSrl).append(",");
		_sb_.append(expireTime).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(bulletId).append(",");
		_sb_.append(bulletNum).append(",");
		_sb_.append(armorPoint).append(",");
		_sb_.append(masterType).append(",");
		_sb_.append(masterSrl).append(",");
		_sb_.append(currLoc).append(",");
		_sb_.append(specialAttr).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
