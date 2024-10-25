package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBGensBaseInfo extends warrior.mdb.XBean {
    private long id;  // 公会id
    private String name;  // 公会名字
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> icon;  // 公会icon
    private long allianceId;  // 联盟id
    private String allianceName;  // 联盟名称
    private int faction;  // 派系
    private boolean system;  // 是否是系统公会
    private int lv;  // 等级

    LBGensBaseInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.allianceName = "";
	}

	public LBGensBaseInfo() {
		this((warrior.mdb.XBean)null);
	}

	public LBGensBaseInfo(LBGensBaseInfo _o_) {
		this(_o_, null);
	}

	LBGensBaseInfo(LBGensBaseInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.name = _o_.name;
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
		this.allianceId = _o_.allianceId;
		this.allianceName = _o_.allianceName;
		this.faction = _o_.faction;
		this.system = _o_.system;
		this.lv = _o_.lv;
	}

	public void copyFrom(LBGensBaseInfo _o_) {
        this.id = _o_.id;
        this.name = _o_.name;
        this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
        this.allianceId = _o_.allianceId;
        this.allianceName = _o_.allianceName;
        this.faction = _o_.faction;
        this.system = _o_.system;
        this.lv = _o_.lv;
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getIcon(){
		return this.icon;
	}

	@JSONField(name = "4")
	public long getAllianceId(){
		return this.allianceId;
	}

	@JSONField(name = "5")
	public String getAllianceName(){
		return this.allianceName;
	}

	@JSONField(name = "6")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "7")
	public boolean getSystem(){
		return this.system;
	}

	@JSONField(name = "8")
	public int getLv(){
		return this.lv;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "3")
	public void setIcon(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.icon = _v_;
	}

	@JSONField(name = "4")
	public void setAllianceId(long _v_){
		this.allianceId = _v_;
	}

	@JSONField(name = "5")
	public void setAllianceName(String _v_){
		this.allianceName = _v_;
	}

	@JSONField(name = "6")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "7")
	public void setSystem(boolean _v_){
		this.system = _v_;
	}

	@JSONField(name = "8")
	public void setLv(int _v_){
		this.lv = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBGensBaseInfo) {
			LBGensBaseInfo _o_ = (LBGensBaseInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (!this.icon.equals(_o_.icon)) return false;
			if (this.allianceId != _o_.allianceId) return false;
			if (!this.allianceName.equals(_o_.allianceName)) return false;
			if (this.faction != _o_.faction) return false;
			if (this.system != _o_.system) return false;
			if (this.lv != _o_.lv) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + this.icon.hashCode();
		_h_ += _h_ * 31 + (int)(allianceId ^ (allianceId >>> 32));
		_h_ += _h_ * 31 + this.allianceName.hashCode();
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (this.system ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.lv);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(icon).append(",");
		_sb_.append(allianceId).append(",");
		_sb_.append("T").append(allianceName.length()).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(system).append(",");
		_sb_.append(lv).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
