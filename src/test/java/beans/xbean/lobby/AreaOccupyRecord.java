package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AreaOccupyRecord extends warrior.mdb.XBean {
    private String name;  // 占领者名称
    private long recordMill;  // 记录时间
    private int flagCfgId;  // 占领旗帜配置id
    private boolean troop;  // 是否是连队占领
    private int faction;  // 阵营

    AreaOccupyRecord(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
	}

	public AreaOccupyRecord() {
		this((warrior.mdb.XBean)null);
	}

	public AreaOccupyRecord(AreaOccupyRecord _o_) {
		this(_o_, null);
	}

	AreaOccupyRecord(AreaOccupyRecord _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.name = _o_.name;
		this.recordMill = _o_.recordMill;
		this.flagCfgId = _o_.flagCfgId;
		this.troop = _o_.troop;
		this.faction = _o_.faction;
	}

	public void copyFrom(AreaOccupyRecord _o_) {
        this.name = _o_.name;
        this.recordMill = _o_.recordMill;
        this.flagCfgId = _o_.flagCfgId;
        this.troop = _o_.troop;
        this.faction = _o_.faction;
	}


	@JSONField(name = "1")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "2")
	public long getRecordMill(){
		return this.recordMill;
	}

	@JSONField(name = "3")
	public int getFlagCfgId(){
		return this.flagCfgId;
	}

	@JSONField(name = "4")
	public boolean getTroop(){
		return this.troop;
	}

	@JSONField(name = "5")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "1")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "2")
	public void setRecordMill(long _v_){
		this.recordMill = _v_;
	}

	@JSONField(name = "3")
	public void setFlagCfgId(int _v_){
		this.flagCfgId = _v_;
	}

	@JSONField(name = "4")
	public void setTroop(boolean _v_){
		this.troop = _v_;
	}

	@JSONField(name = "5")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AreaOccupyRecord) {
			AreaOccupyRecord _o_ = (AreaOccupyRecord)_o1_;
			if (!this.name.equals(_o_.name)) return false;
			if (this.recordMill != _o_.recordMill) return false;
			if (this.flagCfgId != _o_.flagCfgId) return false;
			if (this.troop != _o_.troop) return false;
			if (this.faction != _o_.faction) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + (int)(recordMill ^ (recordMill >>> 32));
		_h_ += _h_ * 31 + (int)(this.flagCfgId);
		_h_ += _h_ * 31 + (this.troop ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.faction);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(recordMill).append(",");
		_sb_.append(flagCfgId).append(",");
		_sb_.append(troop).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
