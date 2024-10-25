package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerFriend extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, Friend> friends;  // 好友列表
    private java.util.concurrent.ConcurrentHashMap<Long, Friend> applys;  // 申请列表
    private int comnum;  // 今日剩余点赞数量
    private int comRef;  // 点赞数量最后刷新时间

    PlayerFriend(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.friends = new java.util.concurrent.ConcurrentHashMap<Long, Friend>();
		this.applys = new java.util.concurrent.ConcurrentHashMap<Long, Friend>();
	}

	public PlayerFriend() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerFriend(PlayerFriend _o_) {
		this(_o_, null);
	}

	PlayerFriend(PlayerFriend _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.friends = new java.util.concurrent.ConcurrentHashMap<Long, Friend>();
		_o_.friends.forEach((_k_, _v_) -> this.friends.put(_k_, new Friend(_v_)));
		this.applys = new java.util.concurrent.ConcurrentHashMap<Long, Friend>();
		_o_.applys.forEach((_k_, _v_) -> this.applys.put(_k_, new Friend(_v_)));
		this.comnum = _o_.comnum;
		this.comRef = _o_.comRef;
	}

	public void copyFrom(PlayerFriend _o_) {
        this.friends = new java.util.concurrent.ConcurrentHashMap<Long, Friend>();
		_o_.friends.forEach((_k_, _v_) -> this.friends.put(_k_, new Friend(_v_)));
        this.applys = new java.util.concurrent.ConcurrentHashMap<Long, Friend>();
		_o_.applys.forEach((_k_, _v_) -> this.applys.put(_k_, new Friend(_v_)));
        this.comnum = _o_.comnum;
        this.comRef = _o_.comRef;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, Friend> getFriends(){
		return this.friends;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, Friend> getApplys(){
		return this.applys;
	}

	@JSONField(name = "3")
	public int getComnum(){
		return this.comnum;
	}

	@JSONField(name = "4")
	public int getComRef(){
		return this.comRef;
	}

	@JSONField(name = "1")
	public void setFriends(java.util.concurrent.ConcurrentHashMap<Long, Friend> _v_){
		this.friends = _v_;
	}

	@JSONField(name = "2")
	public void setApplys(java.util.concurrent.ConcurrentHashMap<Long, Friend> _v_){
		this.applys = _v_;
	}

	@JSONField(name = "3")
	public void setComnum(int _v_){
		this.comnum = _v_;
	}

	@JSONField(name = "4")
	public void setComRef(int _v_){
		this.comRef = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerFriend) {
			PlayerFriend _o_ = (PlayerFriend)_o1_;
			if (!this.friends.equals(_o_.friends)) return false;
			if (!this.applys.equals(_o_.applys)) return false;
			if (this.comnum != _o_.comnum) return false;
			if (this.comRef != _o_.comRef) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.friends.hashCode();
		_h_ += _h_ * 31 + this.applys.hashCode();
		_h_ += _h_ * 31 + (int)(this.comnum);
		_h_ += _h_ * 31 + (int)(this.comRef);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(friends).append(",");
		_sb_.append(applys).append(",");
		_sb_.append(comnum).append(",");
		_sb_.append(comRef).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
