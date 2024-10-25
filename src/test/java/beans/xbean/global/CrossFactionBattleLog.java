package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CrossFactionBattleLog extends warrior.mdb.XBean {
    private int crossGroupId; 
    private int winFaction; 
    private int landId; 

    CrossFactionBattleLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public CrossFactionBattleLog() {
		this((warrior.mdb.XBean)null);
	}

	public CrossFactionBattleLog(CrossFactionBattleLog _o_) {
		this(_o_, null);
	}

	CrossFactionBattleLog(CrossFactionBattleLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.crossGroupId = _o_.crossGroupId;
		this.winFaction = _o_.winFaction;
		this.landId = _o_.landId;
	}

	public void copyFrom(CrossFactionBattleLog _o_) {
        this.crossGroupId = _o_.crossGroupId;
        this.winFaction = _o_.winFaction;
        this.landId = _o_.landId;
	}


	@JSONField(name = "1")
	public int getCrossGroupId(){
		return this.crossGroupId;
	}

	@JSONField(name = "2")
	public int getWinFaction(){
		return this.winFaction;
	}

	@JSONField(name = "3")
	public int getLandId(){
		return this.landId;
	}

	@JSONField(name = "1")
	public void setCrossGroupId(int _v_){
		this.crossGroupId = _v_;
	}

	@JSONField(name = "2")
	public void setWinFaction(int _v_){
		this.winFaction = _v_;
	}

	@JSONField(name = "3")
	public void setLandId(int _v_){
		this.landId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CrossFactionBattleLog) {
			CrossFactionBattleLog _o_ = (CrossFactionBattleLog)_o1_;
			if (this.crossGroupId != _o_.crossGroupId) return false;
			if (this.winFaction != _o_.winFaction) return false;
			if (this.landId != _o_.landId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.crossGroupId);
		_h_ += _h_ * 31 + (int)(this.winFaction);
		_h_ += _h_ * 31 + (int)(this.landId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(crossGroupId).append(",");
		_sb_.append(winFaction).append(",");
		_sb_.append(landId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
