package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBCityFlag extends warrior.mdb.XBean {
    private int flagMarkId;  // 旗帜markId
    private int flagCfgId;  // 旗帜配置Id
    private long flagOccupyTime;  // 旗帜占领时间
    private long areaOccupyTime;  // 区域占领时间
    private int state;  // 旗帜状态
    private int faction; 
    private int flagFaction;  // 旗帜派系
    private long createTime;  // 旗帜初始化时间
    private xbean.lobby.LBCityFlagSave saveInfo;  // 战备点

    LBCityFlag(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.saveInfo = new LBCityFlagSave(this);
	}

	public LBCityFlag() {
		this((warrior.mdb.XBean)null);
	}

	public LBCityFlag(LBCityFlag _o_) {
		this(_o_, null);
	}

	LBCityFlag(LBCityFlag _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.flagMarkId = _o_.flagMarkId;
		this.flagCfgId = _o_.flagCfgId;
		this.flagOccupyTime = _o_.flagOccupyTime;
		this.areaOccupyTime = _o_.areaOccupyTime;
		this.state = _o_.state;
		this.faction = _o_.faction;
		this.flagFaction = _o_.flagFaction;
		this.createTime = _o_.createTime;
		this.saveInfo = new LBCityFlagSave(_o_.saveInfo, this);
	}

	public void copyFrom(LBCityFlag _o_) {
        this.flagMarkId = _o_.flagMarkId;
        this.flagCfgId = _o_.flagCfgId;
        this.flagOccupyTime = _o_.flagOccupyTime;
        this.areaOccupyTime = _o_.areaOccupyTime;
        this.state = _o_.state;
        this.faction = _o_.faction;
        this.flagFaction = _o_.flagFaction;
        this.createTime = _o_.createTime;
        this.saveInfo = new LBCityFlagSave(_o_.saveInfo, this);
	}


	@JSONField(name = "1")
	public int getFlagMarkId(){
		return this.flagMarkId;
	}

	@JSONField(name = "4")
	public int getFlagCfgId(){
		return this.flagCfgId;
	}

	@JSONField(name = "5")
	public long getFlagOccupyTime(){
		return this.flagOccupyTime;
	}

	@JSONField(name = "6")
	public long getAreaOccupyTime(){
		return this.areaOccupyTime;
	}

	@JSONField(name = "7")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "8")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "9")
	public int getFlagFaction(){
		return this.flagFaction;
	}

	@JSONField(name = "11")
	public long getCreateTime(){
		return this.createTime;
	}

	@JSONField(name = "12")
	public xbean.lobby.LBCityFlagSave getSaveInfo(){
		return this.saveInfo;
	}

	@JSONField(name = "1")
	public void setFlagMarkId(int _v_){
		this.flagMarkId = _v_;
	}

	@JSONField(name = "4")
	public void setFlagCfgId(int _v_){
		this.flagCfgId = _v_;
	}

	@JSONField(name = "5")
	public void setFlagOccupyTime(long _v_){
		this.flagOccupyTime = _v_;
	}

	@JSONField(name = "6")
	public void setAreaOccupyTime(long _v_){
		this.areaOccupyTime = _v_;
	}

	@JSONField(name = "7")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "8")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "9")
	public void setFlagFaction(int _v_){
		this.flagFaction = _v_;
	}

	@JSONField(name = "11")
	public void setCreateTime(long _v_){
		this.createTime = _v_;
	}

	@JSONField(name = "12")
	public void setSaveInfo(xbean.lobby.LBCityFlagSave _v_){
		this.saveInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBCityFlag) {
			LBCityFlag _o_ = (LBCityFlag)_o1_;
			if (this.flagMarkId != _o_.flagMarkId) return false;
			if (this.flagCfgId != _o_.flagCfgId) return false;
			if (this.flagOccupyTime != _o_.flagOccupyTime) return false;
			if (this.areaOccupyTime != _o_.areaOccupyTime) return false;
			if (this.state != _o_.state) return false;
			if (this.faction != _o_.faction) return false;
			if (this.flagFaction != _o_.flagFaction) return false;
			if (this.createTime != _o_.createTime) return false;
			if (!this.saveInfo.equals(_o_.saveInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.flagMarkId);
		_h_ += _h_ * 31 + (int)(this.flagCfgId);
		_h_ += _h_ * 31 + (int)(flagOccupyTime ^ (flagOccupyTime >>> 32));
		_h_ += _h_ * 31 + (int)(areaOccupyTime ^ (areaOccupyTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(this.flagFaction);
		_h_ += _h_ * 31 + (int)(createTime ^ (createTime >>> 32));
		_h_ += _h_ * 31 + this.saveInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(flagMarkId).append(",");
		_sb_.append(flagCfgId).append(",");
		_sb_.append(flagOccupyTime).append(",");
		_sb_.append(areaOccupyTime).append(",");
		_sb_.append(state).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(flagFaction).append(",");
		_sb_.append(createTime).append(",");
		_sb_.append(saveInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
