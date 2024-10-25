package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensJoinApply extends warrior.mdb.XBean {
    private long pid;  // 玩家id
    private xbean.lobby.PlayerBaseInfo playerInfo;  // 玩家基础数据
    private long apply;  // 申请时间

    GensJoinApply(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerInfo = new PlayerBaseInfo(this);
	}

	public GensJoinApply() {
		this((warrior.mdb.XBean)null);
	}

	public GensJoinApply(GensJoinApply _o_) {
		this(_o_, null);
	}

	GensJoinApply(GensJoinApply _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.pid = _o_.pid;
		this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
		this.apply = _o_.apply;
	}

	public void copyFrom(GensJoinApply _o_) {
        this.pid = _o_.pid;
        this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
        this.apply = _o_.apply;
	}


	@JSONField(name = "1")
	public long getPid(){
		return this.pid;
	}

	@JSONField(name = "2")
	public xbean.lobby.PlayerBaseInfo getPlayerInfo(){
		return this.playerInfo;
	}

	@JSONField(name = "3")
	public long getApply(){
		return this.apply;
	}

	@JSONField(name = "1")
	public void setPid(long _v_){
		this.pid = _v_;
	}

	@JSONField(name = "2")
	public void setPlayerInfo(xbean.lobby.PlayerBaseInfo _v_){
		this.playerInfo = _v_;
	}

	@JSONField(name = "3")
	public void setApply(long _v_){
		this.apply = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensJoinApply) {
			GensJoinApply _o_ = (GensJoinApply)_o1_;
			if (this.pid != _o_.pid) return false;
			if (!this.playerInfo.equals(_o_.playerInfo)) return false;
			if (this.apply != _o_.apply) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(pid ^ (pid >>> 32));
		_h_ += _h_ * 31 + this.playerInfo.hashCode();
		_h_ += _h_ * 31 + (int)(apply ^ (apply >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(pid).append(",");
		_sb_.append(playerInfo).append(",");
		_sb_.append(apply).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
