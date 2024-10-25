package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBWildRentCarrier extends warrior.mdb.XBean {
    private long srl;  // 载具srl
    private int cfgId;  // 载具配置id
    private String carrierJson;  // 载具json数据
    private long carrierRentSrl;  // 载具租借唯一id
    private boolean factionCanRent;  // 阵营租借
    private boolean troopCanRent;  // 连队租借
    private int militaryCanRent;  // 到达这该军衔可以租借
    private int rentHour;  // 租借小时
    private int ownerType; 
    private long ownerId; 
    private String ownerName; 
    private long rentPlayerId;  // 租借玩家id
    private xbean.lobby.PlayerBaseInfo rentPlayerInfo;  // 租借玩家数据
    private long rentDeleteTime;  // 载具租借删除时间
    private int carrierState;  // 载具状态
    private long repairEndTime;  // 载具修理结束时间
    private int currHp;  // maxhp
    private int maxHp;  // currhp
    private int repairCunt;  // 已经大修过的次数
    private java.util.concurrent.CopyOnWriteArrayList<LBCarrierLog> repairHelpLog;  // 修理帮助日志 连队载具专用
    private boolean returning;  // 是否正在归还载具中
    private long returningSetTime;  // 归还载具中状态set时间
    private long returningPlayerId;  // 正在归还载具中的玩家playerId

    LBWildRentCarrier(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.carrierJson = "";
		this.ownerName = "";
		this.rentPlayerInfo = new PlayerBaseInfo(this);
		this.repairHelpLog = new java.util.concurrent.CopyOnWriteArrayList<LBCarrierLog>();
	}

	public LBWildRentCarrier() {
		this((warrior.mdb.XBean)null);
	}

	public LBWildRentCarrier(LBWildRentCarrier _o_) {
		this(_o_, null);
	}

	LBWildRentCarrier(LBWildRentCarrier _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.cfgId = _o_.cfgId;
		this.carrierJson = _o_.carrierJson;
		this.carrierRentSrl = _o_.carrierRentSrl;
		this.factionCanRent = _o_.factionCanRent;
		this.troopCanRent = _o_.troopCanRent;
		this.militaryCanRent = _o_.militaryCanRent;
		this.rentHour = _o_.rentHour;
		this.ownerType = _o_.ownerType;
		this.ownerId = _o_.ownerId;
		this.ownerName = _o_.ownerName;
		this.rentPlayerId = _o_.rentPlayerId;
		this.rentPlayerInfo = new PlayerBaseInfo(_o_.rentPlayerInfo, this);
		this.rentDeleteTime = _o_.rentDeleteTime;
		this.carrierState = _o_.carrierState;
		this.repairEndTime = _o_.repairEndTime;
		this.currHp = _o_.currHp;
		this.maxHp = _o_.maxHp;
		this.repairCunt = _o_.repairCunt;
		this.repairHelpLog = new java.util.concurrent.CopyOnWriteArrayList<LBCarrierLog>();
		_o_.repairHelpLog.forEach(_v_ -> this.repairHelpLog.add(new LBCarrierLog(_v_)));
		this.returning = _o_.returning;
		this.returningSetTime = _o_.returningSetTime;
		this.returningPlayerId = _o_.returningPlayerId;
	}

	public void copyFrom(LBWildRentCarrier _o_) {
        this.srl = _o_.srl;
        this.cfgId = _o_.cfgId;
        this.carrierJson = _o_.carrierJson;
        this.carrierRentSrl = _o_.carrierRentSrl;
        this.factionCanRent = _o_.factionCanRent;
        this.troopCanRent = _o_.troopCanRent;
        this.militaryCanRent = _o_.militaryCanRent;
        this.rentHour = _o_.rentHour;
        this.ownerType = _o_.ownerType;
        this.ownerId = _o_.ownerId;
        this.ownerName = _o_.ownerName;
        this.rentPlayerId = _o_.rentPlayerId;
        this.rentPlayerInfo = new PlayerBaseInfo(_o_.rentPlayerInfo, this);
        this.rentDeleteTime = _o_.rentDeleteTime;
        this.carrierState = _o_.carrierState;
        this.repairEndTime = _o_.repairEndTime;
        this.currHp = _o_.currHp;
        this.maxHp = _o_.maxHp;
        this.repairCunt = _o_.repairCunt;
        this.repairHelpLog = new java.util.concurrent.CopyOnWriteArrayList<LBCarrierLog>();
		_o_.repairHelpLog.forEach(_v_ -> this.repairHelpLog.add(new LBCarrierLog(_v_)));
        this.returning = _o_.returning;
        this.returningSetTime = _o_.returningSetTime;
        this.returningPlayerId = _o_.returningPlayerId;
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public int getCfgId(){
		return this.cfgId;
	}

	@JSONField(name = "3")
	public String getCarrierJson(){
		return this.carrierJson;
	}

	@JSONField(name = "4")
	public long getCarrierRentSrl(){
		return this.carrierRentSrl;
	}

	@JSONField(name = "5")
	public boolean getFactionCanRent(){
		return this.factionCanRent;
	}

	@JSONField(name = "6")
	public boolean getTroopCanRent(){
		return this.troopCanRent;
	}

	@JSONField(name = "7")
	public int getMilitaryCanRent(){
		return this.militaryCanRent;
	}

	@JSONField(name = "8")
	public int getRentHour(){
		return this.rentHour;
	}

	@JSONField(name = "9")
	public int getOwnerType(){
		return this.ownerType;
	}

	@JSONField(name = "10")
	public long getOwnerId(){
		return this.ownerId;
	}

	@JSONField(name = "11")
	public String getOwnerName(){
		return this.ownerName;
	}

	@JSONField(name = "12")
	public long getRentPlayerId(){
		return this.rentPlayerId;
	}

	@JSONField(name = "13")
	public xbean.lobby.PlayerBaseInfo getRentPlayerInfo(){
		return this.rentPlayerInfo;
	}

	@JSONField(name = "14")
	public long getRentDeleteTime(){
		return this.rentDeleteTime;
	}

	@JSONField(name = "15")
	public int getCarrierState(){
		return this.carrierState;
	}

	@JSONField(name = "16")
	public long getRepairEndTime(){
		return this.repairEndTime;
	}

	@JSONField(name = "17")
	public int getCurrHp(){
		return this.currHp;
	}

	@JSONField(name = "18")
	public int getMaxHp(){
		return this.maxHp;
	}

	@JSONField(name = "19")
	public int getRepairCunt(){
		return this.repairCunt;
	}

	@JSONField(name = "20")
	public java.util.concurrent.CopyOnWriteArrayList<LBCarrierLog> getRepairHelpLog(){
		return this.repairHelpLog;
	}

	@JSONField(name = "30")
	public boolean getReturning(){
		return this.returning;
	}

	@JSONField(name = "31")
	public long getReturningSetTime(){
		return this.returningSetTime;
	}

	@JSONField(name = "32")
	public long getReturningPlayerId(){
		return this.returningPlayerId;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setCfgId(int _v_){
		this.cfgId = _v_;
	}

	@JSONField(name = "3")
	public void setCarrierJson(String _v_){
		this.carrierJson = _v_;
	}

	@JSONField(name = "4")
	public void setCarrierRentSrl(long _v_){
		this.carrierRentSrl = _v_;
	}

	@JSONField(name = "5")
	public void setFactionCanRent(boolean _v_){
		this.factionCanRent = _v_;
	}

	@JSONField(name = "6")
	public void setTroopCanRent(boolean _v_){
		this.troopCanRent = _v_;
	}

	@JSONField(name = "7")
	public void setMilitaryCanRent(int _v_){
		this.militaryCanRent = _v_;
	}

	@JSONField(name = "8")
	public void setRentHour(int _v_){
		this.rentHour = _v_;
	}

	@JSONField(name = "9")
	public void setOwnerType(int _v_){
		this.ownerType = _v_;
	}

	@JSONField(name = "10")
	public void setOwnerId(long _v_){
		this.ownerId = _v_;
	}

	@JSONField(name = "11")
	public void setOwnerName(String _v_){
		this.ownerName = _v_;
	}

	@JSONField(name = "12")
	public void setRentPlayerId(long _v_){
		this.rentPlayerId = _v_;
	}

	@JSONField(name = "13")
	public void setRentPlayerInfo(xbean.lobby.PlayerBaseInfo _v_){
		this.rentPlayerInfo = _v_;
	}

	@JSONField(name = "14")
	public void setRentDeleteTime(long _v_){
		this.rentDeleteTime = _v_;
	}

	@JSONField(name = "15")
	public void setCarrierState(int _v_){
		this.carrierState = _v_;
	}

	@JSONField(name = "16")
	public void setRepairEndTime(long _v_){
		this.repairEndTime = _v_;
	}

	@JSONField(name = "17")
	public void setCurrHp(int _v_){
		this.currHp = _v_;
	}

	@JSONField(name = "18")
	public void setMaxHp(int _v_){
		this.maxHp = _v_;
	}

	@JSONField(name = "19")
	public void setRepairCunt(int _v_){
		this.repairCunt = _v_;
	}

	@JSONField(name = "20")
	public void setRepairHelpLog(java.util.concurrent.CopyOnWriteArrayList<LBCarrierLog> _v_){
		this.repairHelpLog = _v_;
	}

	@JSONField(name = "30")
	public void setReturning(boolean _v_){
		this.returning = _v_;
	}

	@JSONField(name = "31")
	public void setReturningSetTime(long _v_){
		this.returningSetTime = _v_;
	}

	@JSONField(name = "32")
	public void setReturningPlayerId(long _v_){
		this.returningPlayerId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBWildRentCarrier) {
			LBWildRentCarrier _o_ = (LBWildRentCarrier)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.cfgId != _o_.cfgId) return false;
			if (!this.carrierJson.equals(_o_.carrierJson)) return false;
			if (this.carrierRentSrl != _o_.carrierRentSrl) return false;
			if (this.factionCanRent != _o_.factionCanRent) return false;
			if (this.troopCanRent != _o_.troopCanRent) return false;
			if (this.militaryCanRent != _o_.militaryCanRent) return false;
			if (this.rentHour != _o_.rentHour) return false;
			if (this.ownerType != _o_.ownerType) return false;
			if (this.ownerId != _o_.ownerId) return false;
			if (!this.ownerName.equals(_o_.ownerName)) return false;
			if (this.rentPlayerId != _o_.rentPlayerId) return false;
			if (!this.rentPlayerInfo.equals(_o_.rentPlayerInfo)) return false;
			if (this.rentDeleteTime != _o_.rentDeleteTime) return false;
			if (this.carrierState != _o_.carrierState) return false;
			if (this.repairEndTime != _o_.repairEndTime) return false;
			if (this.currHp != _o_.currHp) return false;
			if (this.maxHp != _o_.maxHp) return false;
			if (this.repairCunt != _o_.repairCunt) return false;
			if (!this.repairHelpLog.equals(_o_.repairHelpLog)) return false;
			if (this.returning != _o_.returning) return false;
			if (this.returningSetTime != _o_.returningSetTime) return false;
			if (this.returningPlayerId != _o_.returningPlayerId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(this.cfgId);
		_h_ += _h_ * 31 + this.carrierJson.hashCode();
		_h_ += _h_ * 31 + (int)(carrierRentSrl ^ (carrierRentSrl >>> 32));
		_h_ += _h_ * 31 + (this.factionCanRent ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.troopCanRent ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.militaryCanRent);
		_h_ += _h_ * 31 + (int)(this.rentHour);
		_h_ += _h_ * 31 + (int)(this.ownerType);
		_h_ += _h_ * 31 + (int)(ownerId ^ (ownerId >>> 32));
		_h_ += _h_ * 31 + this.ownerName.hashCode();
		_h_ += _h_ * 31 + (int)(rentPlayerId ^ (rentPlayerId >>> 32));
		_h_ += _h_ * 31 + this.rentPlayerInfo.hashCode();
		_h_ += _h_ * 31 + (int)(rentDeleteTime ^ (rentDeleteTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.carrierState);
		_h_ += _h_ * 31 + (int)(repairEndTime ^ (repairEndTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.currHp);
		_h_ += _h_ * 31 + (int)(this.maxHp);
		_h_ += _h_ * 31 + (int)(this.repairCunt);
		_h_ += _h_ * 31 + this.repairHelpLog.hashCode();
		_h_ += _h_ * 31 + (this.returning ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(returningSetTime ^ (returningSetTime >>> 32));
		_h_ += _h_ * 31 + (int)(returningPlayerId ^ (returningPlayerId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(cfgId).append(",");
		_sb_.append("T").append(carrierJson.length()).append(",");
		_sb_.append(carrierRentSrl).append(",");
		_sb_.append(factionCanRent).append(",");
		_sb_.append(troopCanRent).append(",");
		_sb_.append(militaryCanRent).append(",");
		_sb_.append(rentHour).append(",");
		_sb_.append(ownerType).append(",");
		_sb_.append(ownerId).append(",");
		_sb_.append("T").append(ownerName.length()).append(",");
		_sb_.append(rentPlayerId).append(",");
		_sb_.append(rentPlayerInfo).append(",");
		_sb_.append(rentDeleteTime).append(",");
		_sb_.append(carrierState).append(",");
		_sb_.append(repairEndTime).append(",");
		_sb_.append(currHp).append(",");
		_sb_.append(maxHp).append(",");
		_sb_.append(repairCunt).append(",");
		_sb_.append(repairHelpLog).append(",");
		_sb_.append(returning).append(",");
		_sb_.append(returningSetTime).append(",");
		_sb_.append(returningPlayerId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
