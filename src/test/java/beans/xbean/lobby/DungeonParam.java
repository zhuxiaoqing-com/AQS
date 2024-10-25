package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class DungeonParam extends warrior.mdb.XBean {
    private int matchId; 
    private int cfgId; 

    DungeonParam(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public DungeonParam() {
		this((warrior.mdb.XBean)null);
	}

	public DungeonParam(DungeonParam _o_) {
		this(_o_, null);
	}

	DungeonParam(DungeonParam _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.matchId = _o_.matchId;
		this.cfgId = _o_.cfgId;
	}

	public void copyFrom(DungeonParam _o_) {
        this.matchId = _o_.matchId;
        this.cfgId = _o_.cfgId;
	}


	@JSONField(name = "1")
	public int getMatchId(){
		return this.matchId;
	}

	@JSONField(name = "2")
	public int getCfgId(){
		return this.cfgId;
	}

	@JSONField(name = "1")
	public void setMatchId(int _v_){
		this.matchId = _v_;
	}

	@JSONField(name = "2")
	public void setCfgId(int _v_){
		this.cfgId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof DungeonParam) {
			DungeonParam _o_ = (DungeonParam)_o1_;
			if (this.matchId != _o_.matchId) return false;
			if (this.cfgId != _o_.cfgId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.matchId);
		_h_ += _h_ * 31 + (int)(this.cfgId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(matchId).append(",");
		_sb_.append(cfgId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
