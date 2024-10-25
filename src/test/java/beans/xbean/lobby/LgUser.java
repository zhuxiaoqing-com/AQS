package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LgUser extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<Long> ids;  // 用户的角色列表 value是roleid
    private long create;  // 帐号第一次进入游戏的时间
    private int flag;  // 基于账号的状态（首次分享微博=2，微信=4，...）
    private String inviteCode;  // 缓存一下当前账号是被谁邀请的

    LgUser(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.ids = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.inviteCode = "";
	}

	public LgUser() {
		this((warrior.mdb.XBean)null);
	}

	public LgUser(LgUser _o_) {
		this(_o_, null);
	}

	LgUser(LgUser _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.ids = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.ids.forEach(_v_ -> this.ids.add(_v_));
		this.create = _o_.create;
		this.flag = _o_.flag;
		this.inviteCode = _o_.inviteCode;
	}

	public void copyFrom(LgUser _o_) {
        this.ids = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.ids.forEach(_v_ -> this.ids.add(_v_));
        this.create = _o_.create;
        this.flag = _o_.flag;
        this.inviteCode = _o_.inviteCode;
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getIds(){
		return this.ids;
	}

	@JSONField(name = "2")
	public long getCreate(){
		return this.create;
	}

	@JSONField(name = "3")
	public int getFlag(){
		return this.flag;
	}

	@JSONField(name = "4")
	public String getInviteCode(){
		return this.inviteCode;
	}

	@JSONField(name = "1")
	public void setIds(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.ids = _v_;
	}

	@JSONField(name = "2")
	public void setCreate(long _v_){
		this.create = _v_;
	}

	@JSONField(name = "3")
	public void setFlag(int _v_){
		this.flag = _v_;
	}

	@JSONField(name = "4")
	public void setInviteCode(String _v_){
		this.inviteCode = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LgUser) {
			LgUser _o_ = (LgUser)_o1_;
			if (!this.ids.equals(_o_.ids)) return false;
			if (this.create != _o_.create) return false;
			if (this.flag != _o_.flag) return false;
			if (!this.inviteCode.equals(_o_.inviteCode)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.ids.hashCode();
		_h_ += _h_ * 31 + (int)(create ^ (create >>> 32));
		_h_ += _h_ * 31 + (int)(this.flag);
		_h_ += _h_ * 31 + this.inviteCode.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(ids).append(",");
		_sb_.append(create).append(",");
		_sb_.append(flag).append(",");
		_sb_.append("T").append(inviteCode.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
