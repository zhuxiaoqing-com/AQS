package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Carrier extends warrior.mdb.XBean {
    private long srl;  // 载具唯一id
    private int cfgid;  // 载具配置表id
    private xbean.logic.Bag wbag;  // 负重背包(油，子弹等)
    private xbean.logic.Bag equipBag;  // 装备背包(涂装，防护，油量，维护材料等)
    private int hp;  // 当前血量
    private int state;  // 载具状态 详情请看 PBCarrierState
    private xbean.logic.MAttr attr;  // 载具加成属性
    private long repairEndTime;  // 大修结束时间
    private java.util.concurrent.ConcurrentHashMap<Long, Crew> crewMap;  // 载具乘员 乘员srl->乘员
    private int maxHp;  // 最大血量
    private int areaId;  // 记录载具所在区域地图id
    private int bulletNum;  // 子弹数量
    private long bindCarrierSrl;  // 绑定的载具Srl
    private int bindCarrierCfgId;  // 绑定的载具Id
    private int repairCunt;  // 大修次数
    private xbean.logic.CarrierPosRecord cityRecord;  // 载具位置记录(主城)
    private xbean.logic.Pos pos;  // 当前地图下车载具位置
    private int depotSupplyType;  // 仓库补给类型
    private int loadAdd;  // 载具额外负重
    private xbean.logic.WildRentInfo wildRentInfo;  // 野外租借信息
    private boolean lock1;  // 载具是否锁定 (lock提示是关键字生成不出来)
    private xbean.logic.CarrierLogList logList;  // logList

    Carrier(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.wbag = new Bag(this);
		this.equipBag = new Bag(this);
		this.attr = new MAttr(this);
		this.crewMap = new java.util.concurrent.ConcurrentHashMap<Long, Crew>();
		this.cityRecord = new CarrierPosRecord(this);
		this.pos = new Pos(this);
		this.wildRentInfo = new WildRentInfo(this);
		this.logList = new CarrierLogList(this);
	}

	public Carrier() {
		this((warrior.mdb.XBean)null);
	}

	public Carrier(Carrier _o_) {
		this(_o_, null);
	}

	Carrier(Carrier _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.cfgid = _o_.cfgid;
		this.wbag = new Bag(_o_.wbag, this);
		this.equipBag = new Bag(_o_.equipBag, this);
		this.hp = _o_.hp;
		this.state = _o_.state;
		this.attr = new MAttr(_o_.attr, this);
		this.repairEndTime = _o_.repairEndTime;
		this.crewMap = new java.util.concurrent.ConcurrentHashMap<Long, Crew>();
		_o_.crewMap.forEach((_k_, _v_) -> this.crewMap.put(_k_, new Crew(_v_)));
		this.maxHp = _o_.maxHp;
		this.areaId = _o_.areaId;
		this.bulletNum = _o_.bulletNum;
		this.bindCarrierSrl = _o_.bindCarrierSrl;
		this.bindCarrierCfgId = _o_.bindCarrierCfgId;
		this.repairCunt = _o_.repairCunt;
		this.cityRecord = new CarrierPosRecord(_o_.cityRecord, this);
		this.pos = new Pos(_o_.pos, this);
		this.depotSupplyType = _o_.depotSupplyType;
		this.loadAdd = _o_.loadAdd;
		this.wildRentInfo = new WildRentInfo(_o_.wildRentInfo, this);
		this.lock1 = _o_.lock1;
		this.logList = new CarrierLogList(_o_.logList, this);
	}

	public void copyFrom(Carrier _o_) {
        this.srl = _o_.srl;
        this.cfgid = _o_.cfgid;
        this.wbag = new Bag(_o_.wbag, this);
        this.equipBag = new Bag(_o_.equipBag, this);
        this.hp = _o_.hp;
        this.state = _o_.state;
        this.attr = new MAttr(_o_.attr, this);
        this.repairEndTime = _o_.repairEndTime;
        this.crewMap = new java.util.concurrent.ConcurrentHashMap<Long, Crew>();
		_o_.crewMap.forEach((_k_, _v_) -> this.crewMap.put(_k_, new Crew(_v_)));
        this.maxHp = _o_.maxHp;
        this.areaId = _o_.areaId;
        this.bulletNum = _o_.bulletNum;
        this.bindCarrierSrl = _o_.bindCarrierSrl;
        this.bindCarrierCfgId = _o_.bindCarrierCfgId;
        this.repairCunt = _o_.repairCunt;
        this.cityRecord = new CarrierPosRecord(_o_.cityRecord, this);
        this.pos = new Pos(_o_.pos, this);
        this.depotSupplyType = _o_.depotSupplyType;
        this.loadAdd = _o_.loadAdd;
        this.wildRentInfo = new WildRentInfo(_o_.wildRentInfo, this);
        this.lock1 = _o_.lock1;
        this.logList = new CarrierLogList(_o_.logList, this);
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public int getCfgid(){
		return this.cfgid;
	}

	@JSONField(name = "3")
	public xbean.logic.Bag getWbag(){
		return this.wbag;
	}

	@JSONField(name = "4")
	public xbean.logic.Bag getEquipBag(){
		return this.equipBag;
	}

	@JSONField(name = "5")
	public int getHp(){
		return this.hp;
	}

	@JSONField(name = "6")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "7")
	public xbean.logic.MAttr getAttr(){
		return this.attr;
	}

	@JSONField(name = "8")
	public long getRepairEndTime(){
		return this.repairEndTime;
	}

	@JSONField(name = "9")
	public java.util.concurrent.ConcurrentHashMap<Long, Crew> getCrewMap(){
		return this.crewMap;
	}

	@JSONField(name = "10")
	public int getMaxHp(){
		return this.maxHp;
	}

	@JSONField(name = "11")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "13")
	public int getBulletNum(){
		return this.bulletNum;
	}

	@JSONField(name = "14")
	public long getBindCarrierSrl(){
		return this.bindCarrierSrl;
	}

	@JSONField(name = "15")
	public int getBindCarrierCfgId(){
		return this.bindCarrierCfgId;
	}

	@JSONField(name = "18")
	public int getRepairCunt(){
		return this.repairCunt;
	}

	@JSONField(name = "20")
	public xbean.logic.CarrierPosRecord getCityRecord(){
		return this.cityRecord;
	}

	@JSONField(name = "21")
	public xbean.logic.Pos getPos(){
		return this.pos;
	}

	@JSONField(name = "22")
	public int getDepotSupplyType(){
		return this.depotSupplyType;
	}

	@JSONField(name = "23")
	public int getLoadAdd(){
		return this.loadAdd;
	}

	@JSONField(name = "24")
	public xbean.logic.WildRentInfo getWildRentInfo(){
		return this.wildRentInfo;
	}

	@JSONField(name = "25")
	public boolean getLock1(){
		return this.lock1;
	}

	@JSONField(name = "26")
	public xbean.logic.CarrierLogList getLogList(){
		return this.logList;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setCfgid(int _v_){
		this.cfgid = _v_;
	}

	@JSONField(name = "3")
	public void setWbag(xbean.logic.Bag _v_){
		this.wbag = _v_;
	}

	@JSONField(name = "4")
	public void setEquipBag(xbean.logic.Bag _v_){
		this.equipBag = _v_;
	}

	@JSONField(name = "5")
	public void setHp(int _v_){
		this.hp = _v_;
	}

	@JSONField(name = "6")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "7")
	public void setAttr(xbean.logic.MAttr _v_){
		this.attr = _v_;
	}

	@JSONField(name = "8")
	public void setRepairEndTime(long _v_){
		this.repairEndTime = _v_;
	}

	@JSONField(name = "9")
	public void setCrewMap(java.util.concurrent.ConcurrentHashMap<Long, Crew> _v_){
		this.crewMap = _v_;
	}

	@JSONField(name = "10")
	public void setMaxHp(int _v_){
		this.maxHp = _v_;
	}

	@JSONField(name = "11")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "13")
	public void setBulletNum(int _v_){
		this.bulletNum = _v_;
	}

	@JSONField(name = "14")
	public void setBindCarrierSrl(long _v_){
		this.bindCarrierSrl = _v_;
	}

	@JSONField(name = "15")
	public void setBindCarrierCfgId(int _v_){
		this.bindCarrierCfgId = _v_;
	}

	@JSONField(name = "18")
	public void setRepairCunt(int _v_){
		this.repairCunt = _v_;
	}

	@JSONField(name = "20")
	public void setCityRecord(xbean.logic.CarrierPosRecord _v_){
		this.cityRecord = _v_;
	}

	@JSONField(name = "21")
	public void setPos(xbean.logic.Pos _v_){
		this.pos = _v_;
	}

	@JSONField(name = "22")
	public void setDepotSupplyType(int _v_){
		this.depotSupplyType = _v_;
	}

	@JSONField(name = "23")
	public void setLoadAdd(int _v_){
		this.loadAdd = _v_;
	}

	@JSONField(name = "24")
	public void setWildRentInfo(xbean.logic.WildRentInfo _v_){
		this.wildRentInfo = _v_;
	}

	@JSONField(name = "25")
	public void setLock1(boolean _v_){
		this.lock1 = _v_;
	}

	@JSONField(name = "26")
	public void setLogList(xbean.logic.CarrierLogList _v_){
		this.logList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Carrier) {
			Carrier _o_ = (Carrier)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.cfgid != _o_.cfgid) return false;
			if (!this.wbag.equals(_o_.wbag)) return false;
			if (!this.equipBag.equals(_o_.equipBag)) return false;
			if (this.hp != _o_.hp) return false;
			if (this.state != _o_.state) return false;
			if (!this.attr.equals(_o_.attr)) return false;
			if (this.repairEndTime != _o_.repairEndTime) return false;
			if (!this.crewMap.equals(_o_.crewMap)) return false;
			if (this.maxHp != _o_.maxHp) return false;
			if (this.areaId != _o_.areaId) return false;
			if (this.bulletNum != _o_.bulletNum) return false;
			if (this.bindCarrierSrl != _o_.bindCarrierSrl) return false;
			if (this.bindCarrierCfgId != _o_.bindCarrierCfgId) return false;
			if (this.repairCunt != _o_.repairCunt) return false;
			if (!this.cityRecord.equals(_o_.cityRecord)) return false;
			if (!this.pos.equals(_o_.pos)) return false;
			if (this.depotSupplyType != _o_.depotSupplyType) return false;
			if (this.loadAdd != _o_.loadAdd) return false;
			if (!this.wildRentInfo.equals(_o_.wildRentInfo)) return false;
			if (this.lock1 != _o_.lock1) return false;
			if (!this.logList.equals(_o_.logList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(this.cfgid);
		_h_ += _h_ * 31 + this.wbag.hashCode();
		_h_ += _h_ * 31 + this.equipBag.hashCode();
		_h_ += _h_ * 31 + (int)(this.hp);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + this.attr.hashCode();
		_h_ += _h_ * 31 + (int)(repairEndTime ^ (repairEndTime >>> 32));
		_h_ += _h_ * 31 + this.crewMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.maxHp);
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + (int)(this.bulletNum);
		_h_ += _h_ * 31 + (int)(bindCarrierSrl ^ (bindCarrierSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.bindCarrierCfgId);
		_h_ += _h_ * 31 + (int)(this.repairCunt);
		_h_ += _h_ * 31 + this.cityRecord.hashCode();
		_h_ += _h_ * 31 + this.pos.hashCode();
		_h_ += _h_ * 31 + (int)(this.depotSupplyType);
		_h_ += _h_ * 31 + (int)(this.loadAdd);
		_h_ += _h_ * 31 + this.wildRentInfo.hashCode();
		_h_ += _h_ * 31 + (this.lock1 ? 1231 : 1237);
		_h_ += _h_ * 31 + this.logList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(cfgid).append(",");
		_sb_.append(wbag).append(",");
		_sb_.append(equipBag).append(",");
		_sb_.append(hp).append(",");
		_sb_.append(state).append(",");
		_sb_.append(attr).append(",");
		_sb_.append(repairEndTime).append(",");
		_sb_.append(crewMap).append(",");
		_sb_.append(maxHp).append(",");
		_sb_.append(areaId).append(",");
		_sb_.append(bulletNum).append(",");
		_sb_.append(bindCarrierSrl).append(",");
		_sb_.append(bindCarrierCfgId).append(",");
		_sb_.append(repairCunt).append(",");
		_sb_.append(cityRecord).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(depotSupplyType).append(",");
		_sb_.append(loadAdd).append(",");
		_sb_.append(wildRentInfo).append(",");
		_sb_.append(lock1).append(",");
		_sb_.append(logList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
