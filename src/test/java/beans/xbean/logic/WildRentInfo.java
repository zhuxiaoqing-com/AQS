package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class WildRentInfo extends warrior.mdb.XBean {
    private boolean rentOpen; 
    private int ownerType;  // 租借主人类型 连队还是玩家
    private long owner;  // 租借主人玩家id
    private String ownerName;  // 租借主人玩家名称
    private boolean friendCanRent;  // 好友能否租借
    private boolean companyCanRent;  // 连队能否租借
    private boolean factionCanRent;  // 阵营能否租借
    private int militaryCanRent;  // 租借军衔要求
    private int rentHour;  // 租借小时
    private long rentSrl;  // 租借唯一id
    private long rentPlayerId;  // 租借玩家id
    private String rentPBPlayerBaseInfoBytes;  // 租借玩家基础数据
    private long destroyTime;  // 销毁时间

    WildRentInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.ownerName = "";
		this.rentPBPlayerBaseInfoBytes = "";
	}

	public WildRentInfo() {
		this((warrior.mdb.XBean)null);
	}

	public WildRentInfo(WildRentInfo _o_) {
		this(_o_, null);
	}

	WildRentInfo(WildRentInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.rentOpen = _o_.rentOpen;
		this.ownerType = _o_.ownerType;
		this.owner = _o_.owner;
		this.ownerName = _o_.ownerName;
		this.friendCanRent = _o_.friendCanRent;
		this.companyCanRent = _o_.companyCanRent;
		this.factionCanRent = _o_.factionCanRent;
		this.militaryCanRent = _o_.militaryCanRent;
		this.rentHour = _o_.rentHour;
		this.rentSrl = _o_.rentSrl;
		this.rentPlayerId = _o_.rentPlayerId;
		this.rentPBPlayerBaseInfoBytes = _o_.rentPBPlayerBaseInfoBytes;
		this.destroyTime = _o_.destroyTime;
	}

	public void copyFrom(WildRentInfo _o_) {
        this.rentOpen = _o_.rentOpen;
        this.ownerType = _o_.ownerType;
        this.owner = _o_.owner;
        this.ownerName = _o_.ownerName;
        this.friendCanRent = _o_.friendCanRent;
        this.companyCanRent = _o_.companyCanRent;
        this.factionCanRent = _o_.factionCanRent;
        this.militaryCanRent = _o_.militaryCanRent;
        this.rentHour = _o_.rentHour;
        this.rentSrl = _o_.rentSrl;
        this.rentPlayerId = _o_.rentPlayerId;
        this.rentPBPlayerBaseInfoBytes = _o_.rentPBPlayerBaseInfoBytes;
        this.destroyTime = _o_.destroyTime;
	}


	@JSONField(name = "1")
	public boolean getRentOpen(){
		return this.rentOpen;
	}

	@JSONField(name = "2")
	public int getOwnerType(){
		return this.ownerType;
	}

	@JSONField(name = "3")
	public long getOwner(){
		return this.owner;
	}

	@JSONField(name = "4")
	public String getOwnerName(){
		return this.ownerName;
	}

	@JSONField(name = "5")
	public boolean getFriendCanRent(){
		return this.friendCanRent;
	}

	@JSONField(name = "6")
	public boolean getCompanyCanRent(){
		return this.companyCanRent;
	}

	@JSONField(name = "7")
	public boolean getFactionCanRent(){
		return this.factionCanRent;
	}

	@JSONField(name = "8")
	public int getMilitaryCanRent(){
		return this.militaryCanRent;
	}

	@JSONField(name = "9")
	public int getRentHour(){
		return this.rentHour;
	}

	@JSONField(name = "10")
	public long getRentSrl(){
		return this.rentSrl;
	}

	@JSONField(name = "11")
	public long getRentPlayerId(){
		return this.rentPlayerId;
	}

	@JSONField(name = "12")
	public String getRentPBPlayerBaseInfoBytes(){
		return this.rentPBPlayerBaseInfoBytes;
	}

	@JSONField(name = "13")
	public long getDestroyTime(){
		return this.destroyTime;
	}

	@JSONField(name = "1")
	public void setRentOpen(boolean _v_){
		this.rentOpen = _v_;
	}

	@JSONField(name = "2")
	public void setOwnerType(int _v_){
		this.ownerType = _v_;
	}

	@JSONField(name = "3")
	public void setOwner(long _v_){
		this.owner = _v_;
	}

	@JSONField(name = "4")
	public void setOwnerName(String _v_){
		this.ownerName = _v_;
	}

	@JSONField(name = "5")
	public void setFriendCanRent(boolean _v_){
		this.friendCanRent = _v_;
	}

	@JSONField(name = "6")
	public void setCompanyCanRent(boolean _v_){
		this.companyCanRent = _v_;
	}

	@JSONField(name = "7")
	public void setFactionCanRent(boolean _v_){
		this.factionCanRent = _v_;
	}

	@JSONField(name = "8")
	public void setMilitaryCanRent(int _v_){
		this.militaryCanRent = _v_;
	}

	@JSONField(name = "9")
	public void setRentHour(int _v_){
		this.rentHour = _v_;
	}

	@JSONField(name = "10")
	public void setRentSrl(long _v_){
		this.rentSrl = _v_;
	}

	@JSONField(name = "11")
	public void setRentPlayerId(long _v_){
		this.rentPlayerId = _v_;
	}

	@JSONField(name = "12")
	public void setRentPBPlayerBaseInfoBytes(String _v_){
		this.rentPBPlayerBaseInfoBytes = _v_;
	}

	@JSONField(name = "13")
	public void setDestroyTime(long _v_){
		this.destroyTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WildRentInfo) {
			WildRentInfo _o_ = (WildRentInfo)_o1_;
			if (this.rentOpen != _o_.rentOpen) return false;
			if (this.ownerType != _o_.ownerType) return false;
			if (this.owner != _o_.owner) return false;
			if (!this.ownerName.equals(_o_.ownerName)) return false;
			if (this.friendCanRent != _o_.friendCanRent) return false;
			if (this.companyCanRent != _o_.companyCanRent) return false;
			if (this.factionCanRent != _o_.factionCanRent) return false;
			if (this.militaryCanRent != _o_.militaryCanRent) return false;
			if (this.rentHour != _o_.rentHour) return false;
			if (this.rentSrl != _o_.rentSrl) return false;
			if (this.rentPlayerId != _o_.rentPlayerId) return false;
			if (!this.rentPBPlayerBaseInfoBytes.equals(_o_.rentPBPlayerBaseInfoBytes)) return false;
			if (this.destroyTime != _o_.destroyTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (this.rentOpen ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.ownerType);
		_h_ += _h_ * 31 + (int)(owner ^ (owner >>> 32));
		_h_ += _h_ * 31 + this.ownerName.hashCode();
		_h_ += _h_ * 31 + (this.friendCanRent ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.companyCanRent ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.factionCanRent ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.militaryCanRent);
		_h_ += _h_ * 31 + (int)(this.rentHour);
		_h_ += _h_ * 31 + (int)(rentSrl ^ (rentSrl >>> 32));
		_h_ += _h_ * 31 + (int)(rentPlayerId ^ (rentPlayerId >>> 32));
		_h_ += _h_ * 31 + this.rentPBPlayerBaseInfoBytes.hashCode();
		_h_ += _h_ * 31 + (int)(destroyTime ^ (destroyTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(rentOpen).append(",");
		_sb_.append(ownerType).append(",");
		_sb_.append(owner).append(",");
		_sb_.append("T").append(ownerName.length()).append(",");
		_sb_.append(friendCanRent).append(",");
		_sb_.append(companyCanRent).append(",");
		_sb_.append(factionCanRent).append(",");
		_sb_.append(militaryCanRent).append(",");
		_sb_.append(rentHour).append(",");
		_sb_.append(rentSrl).append(",");
		_sb_.append(rentPlayerId).append(",");
		_sb_.append("T").append(rentPBPlayerBaseInfoBytes.length()).append(",");
		_sb_.append(destroyTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
