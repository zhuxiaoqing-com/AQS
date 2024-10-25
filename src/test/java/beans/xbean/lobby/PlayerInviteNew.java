package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerInviteNew extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, InvitePlayerInfo> linkPlayers;  // 拉去新人的playerId列表
    private xbean.lobby.InvitePlayerInfo fromPlayer;  // 谁拉了我
    private String code;  // 绑定码
    private java.util.concurrent.ConcurrentHashMap<Integer, InviteTask> inviteTask;  // 邀请任务

    PlayerInviteNew(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.linkPlayers = new java.util.concurrent.ConcurrentHashMap<Long, InvitePlayerInfo>();
		this.fromPlayer = new InvitePlayerInfo(this);
		this.code = "";
		this.inviteTask = new java.util.concurrent.ConcurrentHashMap<Integer, InviteTask>();
	}

	public PlayerInviteNew() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerInviteNew(PlayerInviteNew _o_) {
		this(_o_, null);
	}

	PlayerInviteNew(PlayerInviteNew _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.linkPlayers = new java.util.concurrent.ConcurrentHashMap<Long, InvitePlayerInfo>();
		_o_.linkPlayers.forEach((_k_, _v_) -> this.linkPlayers.put(_k_, new InvitePlayerInfo(_v_)));
		this.fromPlayer = new InvitePlayerInfo(_o_.fromPlayer, this);
		this.code = _o_.code;
		this.inviteTask = new java.util.concurrent.ConcurrentHashMap<Integer, InviteTask>();
		_o_.inviteTask.forEach((_k_, _v_) -> this.inviteTask.put(_k_, new InviteTask(_v_)));
	}

	public void copyFrom(PlayerInviteNew _o_) {
        this.linkPlayers = new java.util.concurrent.ConcurrentHashMap<Long, InvitePlayerInfo>();
		_o_.linkPlayers.forEach((_k_, _v_) -> this.linkPlayers.put(_k_, new InvitePlayerInfo(_v_)));
        this.fromPlayer = new InvitePlayerInfo(_o_.fromPlayer, this);
        this.code = _o_.code;
        this.inviteTask = new java.util.concurrent.ConcurrentHashMap<Integer, InviteTask>();
		_o_.inviteTask.forEach((_k_, _v_) -> this.inviteTask.put(_k_, new InviteTask(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, InvitePlayerInfo> getLinkPlayers(){
		return this.linkPlayers;
	}

	@JSONField(name = "2")
	public xbean.lobby.InvitePlayerInfo getFromPlayer(){
		return this.fromPlayer;
	}

	@JSONField(name = "3")
	public String getCode(){
		return this.code;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, InviteTask> getInviteTask(){
		return this.inviteTask;
	}

	@JSONField(name = "1")
	public void setLinkPlayers(java.util.concurrent.ConcurrentHashMap<Long, InvitePlayerInfo> _v_){
		this.linkPlayers = _v_;
	}

	@JSONField(name = "2")
	public void setFromPlayer(xbean.lobby.InvitePlayerInfo _v_){
		this.fromPlayer = _v_;
	}

	@JSONField(name = "3")
	public void setCode(String _v_){
		this.code = _v_;
	}

	@JSONField(name = "4")
	public void setInviteTask(java.util.concurrent.ConcurrentHashMap<Integer, InviteTask> _v_){
		this.inviteTask = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerInviteNew) {
			PlayerInviteNew _o_ = (PlayerInviteNew)_o1_;
			if (!this.linkPlayers.equals(_o_.linkPlayers)) return false;
			if (!this.fromPlayer.equals(_o_.fromPlayer)) return false;
			if (!this.code.equals(_o_.code)) return false;
			if (!this.inviteTask.equals(_o_.inviteTask)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.linkPlayers.hashCode();
		_h_ += _h_ * 31 + this.fromPlayer.hashCode();
		_h_ += _h_ * 31 + this.code.hashCode();
		_h_ += _h_ * 31 + this.inviteTask.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(linkPlayers).append(",");
		_sb_.append(fromPlayer).append(",");
		_sb_.append("T").append(code.length()).append(",");
		_sb_.append(inviteTask).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
