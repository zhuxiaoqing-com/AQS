package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ServerEmailInfo extends warrior.mdb.XBean {
    private long serverEmailId;  // 全服邮件id
    private xbean.lobby.OfflineEmailInfo emailInfo;  // 邮件Info
    private long sendTime;  // 发送时间
    private long delTime;  // 删除时间
    private long createRoleSTime;  // 创角时间段内开头时间
    private long createRoleETime;  // 创角时间段内结尾时间
    private long gmId;  // gm发过来时的id
    private int minLv;  // 最小军衔等级
    private int maxLv;  // 最大军衔等级
    private int faction;  // 阵营限制,0不限

    ServerEmailInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.emailInfo = new OfflineEmailInfo(this);
	}

	public ServerEmailInfo() {
		this((warrior.mdb.XBean)null);
	}

	public ServerEmailInfo(ServerEmailInfo _o_) {
		this(_o_, null);
	}

	ServerEmailInfo(ServerEmailInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverEmailId = _o_.serverEmailId;
		this.emailInfo = new OfflineEmailInfo(_o_.emailInfo, this);
		this.sendTime = _o_.sendTime;
		this.delTime = _o_.delTime;
		this.createRoleSTime = _o_.createRoleSTime;
		this.createRoleETime = _o_.createRoleETime;
		this.gmId = _o_.gmId;
		this.minLv = _o_.minLv;
		this.maxLv = _o_.maxLv;
		this.faction = _o_.faction;
	}

	public void copyFrom(ServerEmailInfo _o_) {
        this.serverEmailId = _o_.serverEmailId;
        this.emailInfo = new OfflineEmailInfo(_o_.emailInfo, this);
        this.sendTime = _o_.sendTime;
        this.delTime = _o_.delTime;
        this.createRoleSTime = _o_.createRoleSTime;
        this.createRoleETime = _o_.createRoleETime;
        this.gmId = _o_.gmId;
        this.minLv = _o_.minLv;
        this.maxLv = _o_.maxLv;
        this.faction = _o_.faction;
	}


	@JSONField(name = "1")
	public long getServerEmailId(){
		return this.serverEmailId;
	}

	@JSONField(name = "2")
	public xbean.lobby.OfflineEmailInfo getEmailInfo(){
		return this.emailInfo;
	}

	@JSONField(name = "3")
	public long getSendTime(){
		return this.sendTime;
	}

	@JSONField(name = "4")
	public long getDelTime(){
		return this.delTime;
	}

	@JSONField(name = "5")
	public long getCreateRoleSTime(){
		return this.createRoleSTime;
	}

	@JSONField(name = "6")
	public long getCreateRoleETime(){
		return this.createRoleETime;
	}

	@JSONField(name = "7")
	public long getGmId(){
		return this.gmId;
	}

	@JSONField(name = "8")
	public int getMinLv(){
		return this.minLv;
	}

	@JSONField(name = "9")
	public int getMaxLv(){
		return this.maxLv;
	}

	@JSONField(name = "10")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "1")
	public void setServerEmailId(long _v_){
		this.serverEmailId = _v_;
	}

	@JSONField(name = "2")
	public void setEmailInfo(xbean.lobby.OfflineEmailInfo _v_){
		this.emailInfo = _v_;
	}

	@JSONField(name = "3")
	public void setSendTime(long _v_){
		this.sendTime = _v_;
	}

	@JSONField(name = "4")
	public void setDelTime(long _v_){
		this.delTime = _v_;
	}

	@JSONField(name = "5")
	public void setCreateRoleSTime(long _v_){
		this.createRoleSTime = _v_;
	}

	@JSONField(name = "6")
	public void setCreateRoleETime(long _v_){
		this.createRoleETime = _v_;
	}

	@JSONField(name = "7")
	public void setGmId(long _v_){
		this.gmId = _v_;
	}

	@JSONField(name = "8")
	public void setMinLv(int _v_){
		this.minLv = _v_;
	}

	@JSONField(name = "9")
	public void setMaxLv(int _v_){
		this.maxLv = _v_;
	}

	@JSONField(name = "10")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ServerEmailInfo) {
			ServerEmailInfo _o_ = (ServerEmailInfo)_o1_;
			if (this.serverEmailId != _o_.serverEmailId) return false;
			if (!this.emailInfo.equals(_o_.emailInfo)) return false;
			if (this.sendTime != _o_.sendTime) return false;
			if (this.delTime != _o_.delTime) return false;
			if (this.createRoleSTime != _o_.createRoleSTime) return false;
			if (this.createRoleETime != _o_.createRoleETime) return false;
			if (this.gmId != _o_.gmId) return false;
			if (this.minLv != _o_.minLv) return false;
			if (this.maxLv != _o_.maxLv) return false;
			if (this.faction != _o_.faction) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(serverEmailId ^ (serverEmailId >>> 32));
		_h_ += _h_ * 31 + this.emailInfo.hashCode();
		_h_ += _h_ * 31 + (int)(sendTime ^ (sendTime >>> 32));
		_h_ += _h_ * 31 + (int)(delTime ^ (delTime >>> 32));
		_h_ += _h_ * 31 + (int)(createRoleSTime ^ (createRoleSTime >>> 32));
		_h_ += _h_ * 31 + (int)(createRoleETime ^ (createRoleETime >>> 32));
		_h_ += _h_ * 31 + (int)(gmId ^ (gmId >>> 32));
		_h_ += _h_ * 31 + (int)(this.minLv);
		_h_ += _h_ * 31 + (int)(this.maxLv);
		_h_ += _h_ * 31 + (int)(this.faction);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(serverEmailId).append(",");
		_sb_.append(emailInfo).append(",");
		_sb_.append(sendTime).append(",");
		_sb_.append(delTime).append(",");
		_sb_.append(createRoleSTime).append(",");
		_sb_.append(createRoleETime).append(",");
		_sb_.append(gmId).append(",");
		_sb_.append(minLv).append(",");
		_sb_.append(maxLv).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
