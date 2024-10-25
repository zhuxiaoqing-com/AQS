package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionWarLandInfo extends warrior.mdb.XBean {
    private int landId;  // 地块id
    private int occupyFaction; 
    private java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup> offenseBattleGroups; 
    private java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup> defenceBattleGroups; 
    private java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment> offenseAppoints; 
    private java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment> defenceAppoints; 
    private boolean isSummary; 
    private int lastOccupyFaction;  // 上一轮跨服战所属阵营
    private java.util.concurrent.CopyOnWriteArrayList<Integer> honorServerIds; 

    FactionWarLandInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.offenseBattleGroups = new java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup>();
		this.defenceBattleGroups = new java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup>();
		this.offenseAppoints = new java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment>();
		this.defenceAppoints = new java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment>();
		this.honorServerIds = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public FactionWarLandInfo() {
		this((warrior.mdb.XBean)null);
	}

	public FactionWarLandInfo(FactionWarLandInfo _o_) {
		this(_o_, null);
	}

	FactionWarLandInfo(FactionWarLandInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.landId = _o_.landId;
		this.occupyFaction = _o_.occupyFaction;
		this.offenseBattleGroups = new java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup>();
		_o_.offenseBattleGroups.forEach(_v_ -> this.offenseBattleGroups.add(new CrossFactionWarBattleGroup(_v_)));
		this.defenceBattleGroups = new java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup>();
		_o_.defenceBattleGroups.forEach(_v_ -> this.defenceBattleGroups.add(new CrossFactionWarBattleGroup(_v_)));
		this.offenseAppoints = new java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment>();
		_o_.offenseAppoints.forEach(_v_ -> this.offenseAppoints.add(new CrossBattleAppointment(_v_)));
		this.defenceAppoints = new java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment>();
		_o_.defenceAppoints.forEach(_v_ -> this.defenceAppoints.add(new CrossBattleAppointment(_v_)));
		this.isSummary = _o_.isSummary;
		this.lastOccupyFaction = _o_.lastOccupyFaction;
		this.honorServerIds = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.honorServerIds.forEach(_v_ -> this.honorServerIds.add(_v_));
	}

	public void copyFrom(FactionWarLandInfo _o_) {
        this.landId = _o_.landId;
        this.occupyFaction = _o_.occupyFaction;
        this.offenseBattleGroups = new java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup>();
		_o_.offenseBattleGroups.forEach(_v_ -> this.offenseBattleGroups.add(new CrossFactionWarBattleGroup(_v_)));
        this.defenceBattleGroups = new java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup>();
		_o_.defenceBattleGroups.forEach(_v_ -> this.defenceBattleGroups.add(new CrossFactionWarBattleGroup(_v_)));
        this.offenseAppoints = new java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment>();
		_o_.offenseAppoints.forEach(_v_ -> this.offenseAppoints.add(new CrossBattleAppointment(_v_)));
        this.defenceAppoints = new java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment>();
		_o_.defenceAppoints.forEach(_v_ -> this.defenceAppoints.add(new CrossBattleAppointment(_v_)));
        this.isSummary = _o_.isSummary;
        this.lastOccupyFaction = _o_.lastOccupyFaction;
        this.honorServerIds = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.honorServerIds.forEach(_v_ -> this.honorServerIds.add(_v_));
	}


	@JSONField(name = "1")
	public int getLandId(){
		return this.landId;
	}

	@JSONField(name = "2")
	public int getOccupyFaction(){
		return this.occupyFaction;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup> getOffenseBattleGroups(){
		return this.offenseBattleGroups;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup> getDefenceBattleGroups(){
		return this.defenceBattleGroups;
	}

	@JSONField(name = "5")
	public java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment> getOffenseAppoints(){
		return this.offenseAppoints;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment> getDefenceAppoints(){
		return this.defenceAppoints;
	}

	@JSONField(name = "7")
	public boolean getIsSummary(){
		return this.isSummary;
	}

	@JSONField(name = "8")
	public int getLastOccupyFaction(){
		return this.lastOccupyFaction;
	}

	@JSONField(name = "9")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getHonorServerIds(){
		return this.honorServerIds;
	}

	@JSONField(name = "1")
	public void setLandId(int _v_){
		this.landId = _v_;
	}

	@JSONField(name = "2")
	public void setOccupyFaction(int _v_){
		this.occupyFaction = _v_;
	}

	@JSONField(name = "3")
	public void setOffenseBattleGroups(java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup> _v_){
		this.offenseBattleGroups = _v_;
	}

	@JSONField(name = "4")
	public void setDefenceBattleGroups(java.util.concurrent.CopyOnWriteArrayList<CrossFactionWarBattleGroup> _v_){
		this.defenceBattleGroups = _v_;
	}

	@JSONField(name = "5")
	public void setOffenseAppoints(java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment> _v_){
		this.offenseAppoints = _v_;
	}

	@JSONField(name = "6")
	public void setDefenceAppoints(java.util.concurrent.CopyOnWriteArrayList<CrossBattleAppointment> _v_){
		this.defenceAppoints = _v_;
	}

	@JSONField(name = "7")
	public void setIsSummary(boolean _v_){
		this.isSummary = _v_;
	}

	@JSONField(name = "8")
	public void setLastOccupyFaction(int _v_){
		this.lastOccupyFaction = _v_;
	}

	@JSONField(name = "9")
	public void setHonorServerIds(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.honorServerIds = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionWarLandInfo) {
			FactionWarLandInfo _o_ = (FactionWarLandInfo)_o1_;
			if (this.landId != _o_.landId) return false;
			if (this.occupyFaction != _o_.occupyFaction) return false;
			if (!this.offenseBattleGroups.equals(_o_.offenseBattleGroups)) return false;
			if (!this.defenceBattleGroups.equals(_o_.defenceBattleGroups)) return false;
			if (!this.offenseAppoints.equals(_o_.offenseAppoints)) return false;
			if (!this.defenceAppoints.equals(_o_.defenceAppoints)) return false;
			if (this.isSummary != _o_.isSummary) return false;
			if (this.lastOccupyFaction != _o_.lastOccupyFaction) return false;
			if (!this.honorServerIds.equals(_o_.honorServerIds)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.landId);
		_h_ += _h_ * 31 + (int)(this.occupyFaction);
		_h_ += _h_ * 31 + this.offenseBattleGroups.hashCode();
		_h_ += _h_ * 31 + this.defenceBattleGroups.hashCode();
		_h_ += _h_ * 31 + this.offenseAppoints.hashCode();
		_h_ += _h_ * 31 + this.defenceAppoints.hashCode();
		_h_ += _h_ * 31 + (this.isSummary ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.lastOccupyFaction);
		_h_ += _h_ * 31 + this.honorServerIds.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(landId).append(",");
		_sb_.append(occupyFaction).append(",");
		_sb_.append(offenseBattleGroups).append(",");
		_sb_.append(defenceBattleGroups).append(",");
		_sb_.append(offenseAppoints).append(",");
		_sb_.append(defenceAppoints).append(",");
		_sb_.append(isSummary).append(",");
		_sb_.append(lastOccupyFaction).append(",");
		_sb_.append(honorServerIds).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
