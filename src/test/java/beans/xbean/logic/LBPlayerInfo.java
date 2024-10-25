package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBPlayerInfo extends warrior.mdb.XBean {
    private long gentesId; 
    private String gentesName; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> gentesIcon; 
    private long teamSrl; 
    private long pvpLimitEndTime; 
    private long armPoint; 
    private int gentesPosition; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> gensTech; 
    private long allianceId; 
    private String allianceShortCall; 
    private long assistCarrierMakeSrl; 
    private int assistCarrierMakeCfgId; 
    private long playerId; 
    private int troopPosition; 
    private int constructScore; 
    private boolean isInstructor; 
    private boolean todayTroopCheckIn;  // 今天有没有部队签到
    private long troopImmureEndTime;  // 部队监禁结束时间
    private int troopLv;  // 部队等级
    private java.util.concurrent.CopyOnWriteArrayList<Integer> activeTroopStrategy; 

    LBPlayerInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gentesName = "";
		this.gentesIcon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.gensTech = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.allianceShortCall = "";
		this.activeTroopStrategy = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public LBPlayerInfo() {
		this((warrior.mdb.XBean)null);
	}

	public LBPlayerInfo(LBPlayerInfo _o_) {
		this(_o_, null);
	}

	LBPlayerInfo(LBPlayerInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gentesId = _o_.gentesId;
		this.gentesName = _o_.gentesName;
		this.gentesIcon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.gentesIcon.forEach((_k_, _v_) -> this.gentesIcon.put(_k_, _v_));
		this.teamSrl = _o_.teamSrl;
		this.pvpLimitEndTime = _o_.pvpLimitEndTime;
		this.armPoint = _o_.armPoint;
		this.gentesPosition = _o_.gentesPosition;
		this.gensTech = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.gensTech.forEach((_k_, _v_) -> this.gensTech.put(_k_, _v_));
		this.allianceId = _o_.allianceId;
		this.allianceShortCall = _o_.allianceShortCall;
		this.assistCarrierMakeSrl = _o_.assistCarrierMakeSrl;
		this.assistCarrierMakeCfgId = _o_.assistCarrierMakeCfgId;
		this.playerId = _o_.playerId;
		this.troopPosition = _o_.troopPosition;
		this.constructScore = _o_.constructScore;
		this.isInstructor = _o_.isInstructor;
		this.todayTroopCheckIn = _o_.todayTroopCheckIn;
		this.troopImmureEndTime = _o_.troopImmureEndTime;
		this.troopLv = _o_.troopLv;
		this.activeTroopStrategy = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.activeTroopStrategy.forEach(_v_ -> this.activeTroopStrategy.add(_v_));
	}

	public void copyFrom(LBPlayerInfo _o_) {
        this.gentesId = _o_.gentesId;
        this.gentesName = _o_.gentesName;
        this.gentesIcon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.gentesIcon.forEach((_k_, _v_) -> this.gentesIcon.put(_k_, _v_));
        this.teamSrl = _o_.teamSrl;
        this.pvpLimitEndTime = _o_.pvpLimitEndTime;
        this.armPoint = _o_.armPoint;
        this.gentesPosition = _o_.gentesPosition;
        this.gensTech = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.gensTech.forEach((_k_, _v_) -> this.gensTech.put(_k_, _v_));
        this.allianceId = _o_.allianceId;
        this.allianceShortCall = _o_.allianceShortCall;
        this.assistCarrierMakeSrl = _o_.assistCarrierMakeSrl;
        this.assistCarrierMakeCfgId = _o_.assistCarrierMakeCfgId;
        this.playerId = _o_.playerId;
        this.troopPosition = _o_.troopPosition;
        this.constructScore = _o_.constructScore;
        this.isInstructor = _o_.isInstructor;
        this.todayTroopCheckIn = _o_.todayTroopCheckIn;
        this.troopImmureEndTime = _o_.troopImmureEndTime;
        this.troopLv = _o_.troopLv;
        this.activeTroopStrategy = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.activeTroopStrategy.forEach(_v_ -> this.activeTroopStrategy.add(_v_));
	}


	@JSONField(name = "1")
	public long getGentesId(){
		return this.gentesId;
	}

	@JSONField(name = "2")
	public String getGentesName(){
		return this.gentesName;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getGentesIcon(){
		return this.gentesIcon;
	}

	@JSONField(name = "4")
	public long getTeamSrl(){
		return this.teamSrl;
	}

	@JSONField(name = "5")
	public long getPvpLimitEndTime(){
		return this.pvpLimitEndTime;
	}

	@JSONField(name = "6")
	public long getArmPoint(){
		return this.armPoint;
	}

	@JSONField(name = "7")
	public int getGentesPosition(){
		return this.gentesPosition;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getGensTech(){
		return this.gensTech;
	}

	@JSONField(name = "9")
	public long getAllianceId(){
		return this.allianceId;
	}

	@JSONField(name = "10")
	public String getAllianceShortCall(){
		return this.allianceShortCall;
	}

	@JSONField(name = "11")
	public long getAssistCarrierMakeSrl(){
		return this.assistCarrierMakeSrl;
	}

	@JSONField(name = "12")
	public int getAssistCarrierMakeCfgId(){
		return this.assistCarrierMakeCfgId;
	}

	@JSONField(name = "13")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "14")
	public int getTroopPosition(){
		return this.troopPosition;
	}

	@JSONField(name = "15")
	public int getConstructScore(){
		return this.constructScore;
	}

	@JSONField(name = "16")
	public boolean getIsInstructor(){
		return this.isInstructor;
	}

	@JSONField(name = "18")
	public boolean getTodayTroopCheckIn(){
		return this.todayTroopCheckIn;
	}

	@JSONField(name = "19")
	public long getTroopImmureEndTime(){
		return this.troopImmureEndTime;
	}

	@JSONField(name = "20")
	public int getTroopLv(){
		return this.troopLv;
	}

	@JSONField(name = "21")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getActiveTroopStrategy(){
		return this.activeTroopStrategy;
	}

	@JSONField(name = "1")
	public void setGentesId(long _v_){
		this.gentesId = _v_;
	}

	@JSONField(name = "2")
	public void setGentesName(String _v_){
		this.gentesName = _v_;
	}

	@JSONField(name = "3")
	public void setGentesIcon(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.gentesIcon = _v_;
	}

	@JSONField(name = "4")
	public void setTeamSrl(long _v_){
		this.teamSrl = _v_;
	}

	@JSONField(name = "5")
	public void setPvpLimitEndTime(long _v_){
		this.pvpLimitEndTime = _v_;
	}

	@JSONField(name = "6")
	public void setArmPoint(long _v_){
		this.armPoint = _v_;
	}

	@JSONField(name = "7")
	public void setGentesPosition(int _v_){
		this.gentesPosition = _v_;
	}

	@JSONField(name = "8")
	public void setGensTech(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.gensTech = _v_;
	}

	@JSONField(name = "9")
	public void setAllianceId(long _v_){
		this.allianceId = _v_;
	}

	@JSONField(name = "10")
	public void setAllianceShortCall(String _v_){
		this.allianceShortCall = _v_;
	}

	@JSONField(name = "11")
	public void setAssistCarrierMakeSrl(long _v_){
		this.assistCarrierMakeSrl = _v_;
	}

	@JSONField(name = "12")
	public void setAssistCarrierMakeCfgId(int _v_){
		this.assistCarrierMakeCfgId = _v_;
	}

	@JSONField(name = "13")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "14")
	public void setTroopPosition(int _v_){
		this.troopPosition = _v_;
	}

	@JSONField(name = "15")
	public void setConstructScore(int _v_){
		this.constructScore = _v_;
	}

	@JSONField(name = "16")
	public void setIsInstructor(boolean _v_){
		this.isInstructor = _v_;
	}

	@JSONField(name = "18")
	public void setTodayTroopCheckIn(boolean _v_){
		this.todayTroopCheckIn = _v_;
	}

	@JSONField(name = "19")
	public void setTroopImmureEndTime(long _v_){
		this.troopImmureEndTime = _v_;
	}

	@JSONField(name = "20")
	public void setTroopLv(int _v_){
		this.troopLv = _v_;
	}

	@JSONField(name = "21")
	public void setActiveTroopStrategy(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.activeTroopStrategy = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBPlayerInfo) {
			LBPlayerInfo _o_ = (LBPlayerInfo)_o1_;
			if (this.gentesId != _o_.gentesId) return false;
			if (!this.gentesName.equals(_o_.gentesName)) return false;
			if (!this.gentesIcon.equals(_o_.gentesIcon)) return false;
			if (this.teamSrl != _o_.teamSrl) return false;
			if (this.pvpLimitEndTime != _o_.pvpLimitEndTime) return false;
			if (this.armPoint != _o_.armPoint) return false;
			if (this.gentesPosition != _o_.gentesPosition) return false;
			if (!this.gensTech.equals(_o_.gensTech)) return false;
			if (this.allianceId != _o_.allianceId) return false;
			if (!this.allianceShortCall.equals(_o_.allianceShortCall)) return false;
			if (this.assistCarrierMakeSrl != _o_.assistCarrierMakeSrl) return false;
			if (this.assistCarrierMakeCfgId != _o_.assistCarrierMakeCfgId) return false;
			if (this.playerId != _o_.playerId) return false;
			if (this.troopPosition != _o_.troopPosition) return false;
			if (this.constructScore != _o_.constructScore) return false;
			if (this.isInstructor != _o_.isInstructor) return false;
			if (this.todayTroopCheckIn != _o_.todayTroopCheckIn) return false;
			if (this.troopImmureEndTime != _o_.troopImmureEndTime) return false;
			if (this.troopLv != _o_.troopLv) return false;
			if (!this.activeTroopStrategy.equals(_o_.activeTroopStrategy)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gentesId ^ (gentesId >>> 32));
		_h_ += _h_ * 31 + this.gentesName.hashCode();
		_h_ += _h_ * 31 + this.gentesIcon.hashCode();
		_h_ += _h_ * 31 + (int)(teamSrl ^ (teamSrl >>> 32));
		_h_ += _h_ * 31 + (int)(pvpLimitEndTime ^ (pvpLimitEndTime >>> 32));
		_h_ += _h_ * 31 + (int)(armPoint ^ (armPoint >>> 32));
		_h_ += _h_ * 31 + (int)(this.gentesPosition);
		_h_ += _h_ * 31 + this.gensTech.hashCode();
		_h_ += _h_ * 31 + (int)(allianceId ^ (allianceId >>> 32));
		_h_ += _h_ * 31 + this.allianceShortCall.hashCode();
		_h_ += _h_ * 31 + (int)(assistCarrierMakeSrl ^ (assistCarrierMakeSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.assistCarrierMakeCfgId);
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + (int)(this.troopPosition);
		_h_ += _h_ * 31 + (int)(this.constructScore);
		_h_ += _h_ * 31 + (this.isInstructor ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.todayTroopCheckIn ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(troopImmureEndTime ^ (troopImmureEndTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.troopLv);
		_h_ += _h_ * 31 + this.activeTroopStrategy.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gentesId).append(",");
		_sb_.append("T").append(gentesName.length()).append(",");
		_sb_.append(gentesIcon).append(",");
		_sb_.append(teamSrl).append(",");
		_sb_.append(pvpLimitEndTime).append(",");
		_sb_.append(armPoint).append(",");
		_sb_.append(gentesPosition).append(",");
		_sb_.append(gensTech).append(",");
		_sb_.append(allianceId).append(",");
		_sb_.append("T").append(allianceShortCall.length()).append(",");
		_sb_.append(assistCarrierMakeSrl).append(",");
		_sb_.append(assistCarrierMakeCfgId).append(",");
		_sb_.append(playerId).append(",");
		_sb_.append(troopPosition).append(",");
		_sb_.append(constructScore).append(",");
		_sb_.append(isInstructor).append(",");
		_sb_.append(todayTroopCheckIn).append(",");
		_sb_.append(troopImmureEndTime).append(",");
		_sb_.append(troopLv).append(",");
		_sb_.append(activeTroopStrategy).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
