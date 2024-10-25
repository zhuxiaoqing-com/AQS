package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class WildFlag extends warrior.mdb.XBean {
    private int flagMarkId;  // 旗帜Id
    private xbean.global.GensBaseInfo occupyGens;  // 占领的公会
    private int flagCfgId;  // 旗帜配置id

    WildFlag(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.occupyGens = new GensBaseInfo(this);
	}

	public WildFlag() {
		this((warrior.mdb.XBean)null);
	}

	public WildFlag(WildFlag _o_) {
		this(_o_, null);
	}

	WildFlag(WildFlag _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.flagMarkId = _o_.flagMarkId;
		this.occupyGens = new GensBaseInfo(_o_.occupyGens, this);
		this.flagCfgId = _o_.flagCfgId;
	}

	public void copyFrom(WildFlag _o_) {
        this.flagMarkId = _o_.flagMarkId;
        this.occupyGens = new GensBaseInfo(_o_.occupyGens, this);
        this.flagCfgId = _o_.flagCfgId;
	}


	@JSONField(name = "1")
	public int getFlagMarkId(){
		return this.flagMarkId;
	}

	@JSONField(name = "2")
	public xbean.global.GensBaseInfo getOccupyGens(){
		return this.occupyGens;
	}

	@JSONField(name = "3")
	public int getFlagCfgId(){
		return this.flagCfgId;
	}

	@JSONField(name = "1")
	public void setFlagMarkId(int _v_){
		this.flagMarkId = _v_;
	}

	@JSONField(name = "2")
	public void setOccupyGens(xbean.global.GensBaseInfo _v_){
		this.occupyGens = _v_;
	}

	@JSONField(name = "3")
	public void setFlagCfgId(int _v_){
		this.flagCfgId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WildFlag) {
			WildFlag _o_ = (WildFlag)_o1_;
			if (this.flagMarkId != _o_.flagMarkId) return false;
			if (!this.occupyGens.equals(_o_.occupyGens)) return false;
			if (this.flagCfgId != _o_.flagCfgId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.flagMarkId);
		_h_ += _h_ * 31 + this.occupyGens.hashCode();
		_h_ += _h_ * 31 + (int)(this.flagCfgId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(flagMarkId).append(",");
		_sb_.append(occupyGens).append(",");
		_sb_.append(flagCfgId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
