package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerGuard extends warrior.mdb.XBean {
    private int talentPoint; 
    private java.util.concurrent.ConcurrentHashMap<Integer, PlayerGuardInfo> guards;  // 已获得的警卫
    private java.util.concurrent.ConcurrentHashMap<Integer, FormationInfo> formationList; 
    private int curFormation;  // ,1,2,3，4为4套阵容
    private boolean inited;  // 是否给过默认警卫
    private long lastRefresh;  // 上次的刷新时间
    private boolean summonState;  // 召唤状态
    private int instruct;  // 护卫指令

    PlayerGuard(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.guards = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerGuardInfo>();
		this.formationList = new java.util.concurrent.ConcurrentHashMap<Integer, FormationInfo>();
	}

	public PlayerGuard() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerGuard(PlayerGuard _o_) {
		this(_o_, null);
	}

	PlayerGuard(PlayerGuard _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.talentPoint = _o_.talentPoint;
		this.guards = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerGuardInfo>();
		_o_.guards.forEach((_k_, _v_) -> this.guards.put(_k_, new PlayerGuardInfo(_v_)));
		this.formationList = new java.util.concurrent.ConcurrentHashMap<Integer, FormationInfo>();
		_o_.formationList.forEach((_k_, _v_) -> this.formationList.put(_k_, new FormationInfo(_v_)));
		this.curFormation = _o_.curFormation;
		this.inited = _o_.inited;
		this.lastRefresh = _o_.lastRefresh;
		this.summonState = _o_.summonState;
		this.instruct = _o_.instruct;
	}

	public void copyFrom(PlayerGuard _o_) {
        this.talentPoint = _o_.talentPoint;
        this.guards = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerGuardInfo>();
		_o_.guards.forEach((_k_, _v_) -> this.guards.put(_k_, new PlayerGuardInfo(_v_)));
        this.formationList = new java.util.concurrent.ConcurrentHashMap<Integer, FormationInfo>();
		_o_.formationList.forEach((_k_, _v_) -> this.formationList.put(_k_, new FormationInfo(_v_)));
        this.curFormation = _o_.curFormation;
        this.inited = _o_.inited;
        this.lastRefresh = _o_.lastRefresh;
        this.summonState = _o_.summonState;
        this.instruct = _o_.instruct;
	}


	@JSONField(name = "1")
	public int getTalentPoint(){
		return this.talentPoint;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, PlayerGuardInfo> getGuards(){
		return this.guards;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, FormationInfo> getFormationList(){
		return this.formationList;
	}

	@JSONField(name = "4")
	public int getCurFormation(){
		return this.curFormation;
	}

	@JSONField(name = "5")
	public boolean getInited(){
		return this.inited;
	}

	@JSONField(name = "6")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "7")
	public boolean getSummonState(){
		return this.summonState;
	}

	@JSONField(name = "8")
	public int getInstruct(){
		return this.instruct;
	}

	@JSONField(name = "1")
	public void setTalentPoint(int _v_){
		this.talentPoint = _v_;
	}

	@JSONField(name = "2")
	public void setGuards(java.util.concurrent.ConcurrentHashMap<Integer, PlayerGuardInfo> _v_){
		this.guards = _v_;
	}

	@JSONField(name = "3")
	public void setFormationList(java.util.concurrent.ConcurrentHashMap<Integer, FormationInfo> _v_){
		this.formationList = _v_;
	}

	@JSONField(name = "4")
	public void setCurFormation(int _v_){
		this.curFormation = _v_;
	}

	@JSONField(name = "5")
	public void setInited(boolean _v_){
		this.inited = _v_;
	}

	@JSONField(name = "6")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@JSONField(name = "7")
	public void setSummonState(boolean _v_){
		this.summonState = _v_;
	}

	@JSONField(name = "8")
	public void setInstruct(int _v_){
		this.instruct = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerGuard) {
			PlayerGuard _o_ = (PlayerGuard)_o1_;
			if (this.talentPoint != _o_.talentPoint) return false;
			if (!this.guards.equals(_o_.guards)) return false;
			if (!this.formationList.equals(_o_.formationList)) return false;
			if (this.curFormation != _o_.curFormation) return false;
			if (this.inited != _o_.inited) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			if (this.summonState != _o_.summonState) return false;
			if (this.instruct != _o_.instruct) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.talentPoint);
		_h_ += _h_ * 31 + this.guards.hashCode();
		_h_ += _h_ * 31 + this.formationList.hashCode();
		_h_ += _h_ * 31 + (int)(this.curFormation);
		_h_ += _h_ * 31 + (this.inited ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		_h_ += _h_ * 31 + (this.summonState ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.instruct);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(talentPoint).append(",");
		_sb_.append(guards).append(",");
		_sb_.append(formationList).append(",");
		_sb_.append(curFormation).append(",");
		_sb_.append(inited).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(summonState).append(",");
		_sb_.append(instruct).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
