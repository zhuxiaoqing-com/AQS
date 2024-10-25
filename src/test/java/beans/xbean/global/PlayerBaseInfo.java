package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerBaseInfo extends warrior.mdb.XBean {
    private String name; 
    private int hair;  // Gens
    private int body; 
    private int sex; 
    private int nation; 
    private int military;  // 本国军衔
    private int avatar;  // 头像
    private int title;  // 称号
    private int faction;  // 阵营
    private int talentActiveType;  // 天赋激活类型
    private long honor;  // 荣誉
    private String qq; 
    private boolean showFaction;  // 是否阵营可见
    private boolean showFriend;  // 是否好友可见
    private boolean showCompany;  // 是否连队可见
    private long playerId; 
    private boolean online;  // 当前是否在线
    private long offlineTime;  // 上次离线时间
    private int maxTroopLead; 
    private long troopId; 

    PlayerBaseInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.qq = "";
	}

	public PlayerBaseInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerBaseInfo(PlayerBaseInfo _o_) {
		this(_o_, null);
	}

	PlayerBaseInfo(PlayerBaseInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.name = _o_.name;
		this.hair = _o_.hair;
		this.body = _o_.body;
		this.sex = _o_.sex;
		this.nation = _o_.nation;
		this.military = _o_.military;
		this.avatar = _o_.avatar;
		this.title = _o_.title;
		this.faction = _o_.faction;
		this.talentActiveType = _o_.talentActiveType;
		this.honor = _o_.honor;
		this.qq = _o_.qq;
		this.showFaction = _o_.showFaction;
		this.showFriend = _o_.showFriend;
		this.showCompany = _o_.showCompany;
		this.playerId = _o_.playerId;
		this.online = _o_.online;
		this.offlineTime = _o_.offlineTime;
		this.maxTroopLead = _o_.maxTroopLead;
		this.troopId = _o_.troopId;
	}

	public void copyFrom(PlayerBaseInfo _o_) {
        this.name = _o_.name;
        this.hair = _o_.hair;
        this.body = _o_.body;
        this.sex = _o_.sex;
        this.nation = _o_.nation;
        this.military = _o_.military;
        this.avatar = _o_.avatar;
        this.title = _o_.title;
        this.faction = _o_.faction;
        this.talentActiveType = _o_.talentActiveType;
        this.honor = _o_.honor;
        this.qq = _o_.qq;
        this.showFaction = _o_.showFaction;
        this.showFriend = _o_.showFriend;
        this.showCompany = _o_.showCompany;
        this.playerId = _o_.playerId;
        this.online = _o_.online;
        this.offlineTime = _o_.offlineTime;
        this.maxTroopLead = _o_.maxTroopLead;
        this.troopId = _o_.troopId;
	}


	@JSONField(name = "1")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "2")
	public int getHair(){
		return this.hair;
	}

	@JSONField(name = "3")
	public int getBody(){
		return this.body;
	}

	@JSONField(name = "4")
	public int getSex(){
		return this.sex;
	}

	@JSONField(name = "5")
	public int getNation(){
		return this.nation;
	}

	@JSONField(name = "6")
	public int getMilitary(){
		return this.military;
	}

	@JSONField(name = "7")
	public int getAvatar(){
		return this.avatar;
	}

	@JSONField(name = "8")
	public int getTitle(){
		return this.title;
	}

	@JSONField(name = "9")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "10")
	public int getTalentActiveType(){
		return this.talentActiveType;
	}

	@JSONField(name = "11")
	public long getHonor(){
		return this.honor;
	}

	@JSONField(name = "12")
	public String getQq(){
		return this.qq;
	}

	@JSONField(name = "13")
	public boolean getShowFaction(){
		return this.showFaction;
	}

	@JSONField(name = "14")
	public boolean getShowFriend(){
		return this.showFriend;
	}

	@JSONField(name = "15")
	public boolean getShowCompany(){
		return this.showCompany;
	}

	@JSONField(name = "16")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "17")
	public boolean getOnline(){
		return this.online;
	}

	@JSONField(name = "18")
	public long getOfflineTime(){
		return this.offlineTime;
	}

	@JSONField(name = "19")
	public int getMaxTroopLead(){
		return this.maxTroopLead;
	}

	@JSONField(name = "20")
	public long getTroopId(){
		return this.troopId;
	}

	@JSONField(name = "1")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "2")
	public void setHair(int _v_){
		this.hair = _v_;
	}

	@JSONField(name = "3")
	public void setBody(int _v_){
		this.body = _v_;
	}

	@JSONField(name = "4")
	public void setSex(int _v_){
		this.sex = _v_;
	}

	@JSONField(name = "5")
	public void setNation(int _v_){
		this.nation = _v_;
	}

	@JSONField(name = "6")
	public void setMilitary(int _v_){
		this.military = _v_;
	}

	@JSONField(name = "7")
	public void setAvatar(int _v_){
		this.avatar = _v_;
	}

	@JSONField(name = "8")
	public void setTitle(int _v_){
		this.title = _v_;
	}

	@JSONField(name = "9")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "10")
	public void setTalentActiveType(int _v_){
		this.talentActiveType = _v_;
	}

	@JSONField(name = "11")
	public void setHonor(long _v_){
		this.honor = _v_;
	}

	@JSONField(name = "12")
	public void setQq(String _v_){
		this.qq = _v_;
	}

	@JSONField(name = "13")
	public void setShowFaction(boolean _v_){
		this.showFaction = _v_;
	}

	@JSONField(name = "14")
	public void setShowFriend(boolean _v_){
		this.showFriend = _v_;
	}

	@JSONField(name = "15")
	public void setShowCompany(boolean _v_){
		this.showCompany = _v_;
	}

	@JSONField(name = "16")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "17")
	public void setOnline(boolean _v_){
		this.online = _v_;
	}

	@JSONField(name = "18")
	public void setOfflineTime(long _v_){
		this.offlineTime = _v_;
	}

	@JSONField(name = "19")
	public void setMaxTroopLead(int _v_){
		this.maxTroopLead = _v_;
	}

	@JSONField(name = "20")
	public void setTroopId(long _v_){
		this.troopId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerBaseInfo) {
			PlayerBaseInfo _o_ = (PlayerBaseInfo)_o1_;
			if (!this.name.equals(_o_.name)) return false;
			if (this.hair != _o_.hair) return false;
			if (this.body != _o_.body) return false;
			if (this.sex != _o_.sex) return false;
			if (this.nation != _o_.nation) return false;
			if (this.military != _o_.military) return false;
			if (this.avatar != _o_.avatar) return false;
			if (this.title != _o_.title) return false;
			if (this.faction != _o_.faction) return false;
			if (this.talentActiveType != _o_.talentActiveType) return false;
			if (this.honor != _o_.honor) return false;
			if (!this.qq.equals(_o_.qq)) return false;
			if (this.showFaction != _o_.showFaction) return false;
			if (this.showFriend != _o_.showFriend) return false;
			if (this.showCompany != _o_.showCompany) return false;
			if (this.playerId != _o_.playerId) return false;
			if (this.online != _o_.online) return false;
			if (this.offlineTime != _o_.offlineTime) return false;
			if (this.maxTroopLead != _o_.maxTroopLead) return false;
			if (this.troopId != _o_.troopId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + (int)(this.hair);
		_h_ += _h_ * 31 + (int)(this.body);
		_h_ += _h_ * 31 + (int)(this.sex);
		_h_ += _h_ * 31 + (int)(this.nation);
		_h_ += _h_ * 31 + (int)(this.military);
		_h_ += _h_ * 31 + (int)(this.avatar);
		_h_ += _h_ * 31 + (int)(this.title);
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(this.talentActiveType);
		_h_ += _h_ * 31 + (int)(honor ^ (honor >>> 32));
		_h_ += _h_ * 31 + this.qq.hashCode();
		_h_ += _h_ * 31 + (this.showFaction ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.showFriend ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.showCompany ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + (this.online ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(offlineTime ^ (offlineTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.maxTroopLead);
		_h_ += _h_ * 31 + (int)(troopId ^ (troopId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(hair).append(",");
		_sb_.append(body).append(",");
		_sb_.append(sex).append(",");
		_sb_.append(nation).append(",");
		_sb_.append(military).append(",");
		_sb_.append(avatar).append(",");
		_sb_.append(title).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(talentActiveType).append(",");
		_sb_.append(honor).append(",");
		_sb_.append("T").append(qq.length()).append(",");
		_sb_.append(showFaction).append(",");
		_sb_.append(showFriend).append(",");
		_sb_.append(showCompany).append(",");
		_sb_.append(playerId).append(",");
		_sb_.append(online).append(",");
		_sb_.append(offlineTime).append(",");
		_sb_.append(maxTroopLead).append(",");
		_sb_.append(troopId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
