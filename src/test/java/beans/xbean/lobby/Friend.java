package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class Friend extends warrior.mdb.XBean {
    private int type;  // 好友类型: 0-好友，1-黑名单
    private int state;  // 1 在线  0  离线
    private xbean.lobby.PlayerBaseInfo playerInfo;  // 角色基础数据
    private long apptime;  // 申请时间 申请时用
    private long offtime;  // 离线时间
    private boolean deepen;  // 是否已点赞
    private int intimacy;  // 羁绊值
    private long gid;  // 工会Id
    private String gname;  // 工会名
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> icons;  // 工会图标
    private int dungeonId;  // 所在副本id
    private long teamSrl;  // 所在队伍

    Friend(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerInfo = new PlayerBaseInfo(this);
		this.gname = "";
		this.icons = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public Friend() {
		this((warrior.mdb.XBean)null);
	}

	public Friend(Friend _o_) {
		this(_o_, null);
	}

	Friend(Friend _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.type = _o_.type;
		this.state = _o_.state;
		this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
		this.apptime = _o_.apptime;
		this.offtime = _o_.offtime;
		this.deepen = _o_.deepen;
		this.intimacy = _o_.intimacy;
		this.gid = _o_.gid;
		this.gname = _o_.gname;
		this.icons = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icons.forEach((_k_, _v_) -> this.icons.put(_k_, _v_));
		this.dungeonId = _o_.dungeonId;
		this.teamSrl = _o_.teamSrl;
	}

	public void copyFrom(Friend _o_) {
        this.type = _o_.type;
        this.state = _o_.state;
        this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
        this.apptime = _o_.apptime;
        this.offtime = _o_.offtime;
        this.deepen = _o_.deepen;
        this.intimacy = _o_.intimacy;
        this.gid = _o_.gid;
        this.gname = _o_.gname;
        this.icons = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icons.forEach((_k_, _v_) -> this.icons.put(_k_, _v_));
        this.dungeonId = _o_.dungeonId;
        this.teamSrl = _o_.teamSrl;
	}


	@JSONField(name = "1")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "2")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "3")
	public xbean.lobby.PlayerBaseInfo getPlayerInfo(){
		return this.playerInfo;
	}

	@JSONField(name = "4")
	public long getApptime(){
		return this.apptime;
	}

	@JSONField(name = "5")
	public long getOfftime(){
		return this.offtime;
	}

	@JSONField(name = "6")
	public boolean getDeepen(){
		return this.deepen;
	}

	@JSONField(name = "7")
	public int getIntimacy(){
		return this.intimacy;
	}

	@JSONField(name = "8")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "9")
	public String getGname(){
		return this.gname;
	}

	@JSONField(name = "10")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getIcons(){
		return this.icons;
	}

	@JSONField(name = "11")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "12")
	public long getTeamSrl(){
		return this.teamSrl;
	}

	@JSONField(name = "1")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "2")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "3")
	public void setPlayerInfo(xbean.lobby.PlayerBaseInfo _v_){
		this.playerInfo = _v_;
	}

	@JSONField(name = "4")
	public void setApptime(long _v_){
		this.apptime = _v_;
	}

	@JSONField(name = "5")
	public void setOfftime(long _v_){
		this.offtime = _v_;
	}

	@JSONField(name = "6")
	public void setDeepen(boolean _v_){
		this.deepen = _v_;
	}

	@JSONField(name = "7")
	public void setIntimacy(int _v_){
		this.intimacy = _v_;
	}

	@JSONField(name = "8")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "9")
	public void setGname(String _v_){
		this.gname = _v_;
	}

	@JSONField(name = "10")
	public void setIcons(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.icons = _v_;
	}

	@JSONField(name = "11")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "12")
	public void setTeamSrl(long _v_){
		this.teamSrl = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Friend) {
			Friend _o_ = (Friend)_o1_;
			if (this.type != _o_.type) return false;
			if (this.state != _o_.state) return false;
			if (!this.playerInfo.equals(_o_.playerInfo)) return false;
			if (this.apptime != _o_.apptime) return false;
			if (this.offtime != _o_.offtime) return false;
			if (this.deepen != _o_.deepen) return false;
			if (this.intimacy != _o_.intimacy) return false;
			if (this.gid != _o_.gid) return false;
			if (!this.gname.equals(_o_.gname)) return false;
			if (!this.icons.equals(_o_.icons)) return false;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (this.teamSrl != _o_.teamSrl) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + this.playerInfo.hashCode();
		_h_ += _h_ * 31 + (int)(apptime ^ (apptime >>> 32));
		_h_ += _h_ * 31 + (int)(offtime ^ (offtime >>> 32));
		_h_ += _h_ * 31 + (this.deepen ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.intimacy);
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + this.gname.hashCode();
		_h_ += _h_ * 31 + this.icons.hashCode();
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + (int)(teamSrl ^ (teamSrl >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(type).append(",");
		_sb_.append(state).append(",");
		_sb_.append(playerInfo).append(",");
		_sb_.append(apptime).append(",");
		_sb_.append(offtime).append(",");
		_sb_.append(deepen).append(",");
		_sb_.append(intimacy).append(",");
		_sb_.append(gid).append(",");
		_sb_.append("T").append(gname.length()).append(",");
		_sb_.append(icons).append(",");
		_sb_.append(dungeonId).append(",");
		_sb_.append(teamSrl).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
