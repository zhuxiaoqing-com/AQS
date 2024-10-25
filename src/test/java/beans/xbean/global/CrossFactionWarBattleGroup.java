package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CrossFactionWarBattleGroup extends warrior.mdb.XBean {
    private int serverId; 
    private int faction; 
    private java.util.concurrent.CopyOnWriteArraySet<Long> leaders; 
    private java.util.concurrent.CopyOnWriteArraySet<Long> members; 
    private boolean autoJoin; 
    private java.util.concurrent.CopyOnWriteArraySet<Long> applys; 
    private boolean offence; 
    private int landId; 

    CrossFactionWarBattleGroup(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.leaders = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.members = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.applys = new java.util.concurrent.CopyOnWriteArraySet<Long>();
	}

	public CrossFactionWarBattleGroup() {
		this((warrior.mdb.XBean)null);
	}

	public CrossFactionWarBattleGroup(CrossFactionWarBattleGroup _o_) {
		this(_o_, null);
	}

	CrossFactionWarBattleGroup(CrossFactionWarBattleGroup _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverId = _o_.serverId;
		this.faction = _o_.faction;
		this.leaders = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.leaders.forEach(_v_ -> this.leaders.add(_v_));
		this.members = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.members.forEach(_v_ -> this.members.add(_v_));
		this.autoJoin = _o_.autoJoin;
		this.applys = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.applys.forEach(_v_ -> this.applys.add(_v_));
		this.offence = _o_.offence;
		this.landId = _o_.landId;
	}

	public void copyFrom(CrossFactionWarBattleGroup _o_) {
        this.serverId = _o_.serverId;
        this.faction = _o_.faction;
        this.leaders = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.leaders.forEach(_v_ -> this.leaders.add(_v_));
        this.members = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.members.forEach(_v_ -> this.members.add(_v_));
        this.autoJoin = _o_.autoJoin;
        this.applys = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.applys.forEach(_v_ -> this.applys.add(_v_));
        this.offence = _o_.offence;
        this.landId = _o_.landId;
	}


	@JSONField(name = "1")
	public int getServerId(){
		return this.serverId;
	}

	@JSONField(name = "2")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getLeaders(){
		return this.leaders;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getMembers(){
		return this.members;
	}

	@JSONField(name = "5")
	public boolean getAutoJoin(){
		return this.autoJoin;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getApplys(){
		return this.applys;
	}

	@JSONField(name = "7")
	public boolean getOffence(){
		return this.offence;
	}

	@JSONField(name = "8")
	public int getLandId(){
		return this.landId;
	}

	@JSONField(name = "1")
	public void setServerId(int _v_){
		this.serverId = _v_;
	}

	@JSONField(name = "2")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "3")
	public void setLeaders(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.leaders = _v_;
	}

	@JSONField(name = "4")
	public void setMembers(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.members = _v_;
	}

	@JSONField(name = "5")
	public void setAutoJoin(boolean _v_){
		this.autoJoin = _v_;
	}

	@JSONField(name = "6")
	public void setApplys(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.applys = _v_;
	}

	@JSONField(name = "7")
	public void setOffence(boolean _v_){
		this.offence = _v_;
	}

	@JSONField(name = "8")
	public void setLandId(int _v_){
		this.landId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CrossFactionWarBattleGroup) {
			CrossFactionWarBattleGroup _o_ = (CrossFactionWarBattleGroup)_o1_;
			if (this.serverId != _o_.serverId) return false;
			if (this.faction != _o_.faction) return false;
			if (!this.leaders.equals(_o_.leaders)) return false;
			if (!this.members.equals(_o_.members)) return false;
			if (this.autoJoin != _o_.autoJoin) return false;
			if (!this.applys.equals(_o_.applys)) return false;
			if (this.offence != _o_.offence) return false;
			if (this.landId != _o_.landId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.serverId);
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + this.leaders.hashCode();
		_h_ += _h_ * 31 + this.members.hashCode();
		_h_ += _h_ * 31 + (this.autoJoin ? 1231 : 1237);
		_h_ += _h_ * 31 + this.applys.hashCode();
		_h_ += _h_ * 31 + (this.offence ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.landId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(serverId).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(leaders).append(",");
		_sb_.append(members).append(",");
		_sb_.append(autoJoin).append(",");
		_sb_.append(applys).append(",");
		_sb_.append(offence).append(",");
		_sb_.append(landId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
