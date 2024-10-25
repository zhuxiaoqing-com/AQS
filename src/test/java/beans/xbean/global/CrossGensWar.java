package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CrossGensWar extends warrior.mdb.XBean {
    private boolean warEnd;  // 战场是否已经结束
    private int serverId1;  // 服务器id1
    private int serverId2;  // 服务器id2
    private xbean.global.GensBaseInfo gensInfo1;  // gens1
    private xbean.global.GensBaseInfo gensInfo2;  // gens2
    private long winGensId;  // 胜利的公会id

    CrossGensWar(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gensInfo1 = new GensBaseInfo(this);
		this.gensInfo2 = new GensBaseInfo(this);
	}

	public CrossGensWar() {
		this((warrior.mdb.XBean)null);
	}

	public CrossGensWar(CrossGensWar _o_) {
		this(_o_, null);
	}

	CrossGensWar(CrossGensWar _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.warEnd = _o_.warEnd;
		this.serverId1 = _o_.serverId1;
		this.serverId2 = _o_.serverId2;
		this.gensInfo1 = new GensBaseInfo(_o_.gensInfo1, this);
		this.gensInfo2 = new GensBaseInfo(_o_.gensInfo2, this);
		this.winGensId = _o_.winGensId;
	}

	public void copyFrom(CrossGensWar _o_) {
        this.warEnd = _o_.warEnd;
        this.serverId1 = _o_.serverId1;
        this.serverId2 = _o_.serverId2;
        this.gensInfo1 = new GensBaseInfo(_o_.gensInfo1, this);
        this.gensInfo2 = new GensBaseInfo(_o_.gensInfo2, this);
        this.winGensId = _o_.winGensId;
	}


	@JSONField(name = "1")
	public boolean getWarEnd(){
		return this.warEnd;
	}

	@JSONField(name = "2")
	public int getServerId1(){
		return this.serverId1;
	}

	@JSONField(name = "3")
	public int getServerId2(){
		return this.serverId2;
	}

	@JSONField(name = "4")
	public xbean.global.GensBaseInfo getGensInfo1(){
		return this.gensInfo1;
	}

	@JSONField(name = "5")
	public xbean.global.GensBaseInfo getGensInfo2(){
		return this.gensInfo2;
	}

	@JSONField(name = "6")
	public long getWinGensId(){
		return this.winGensId;
	}

	@JSONField(name = "1")
	public void setWarEnd(boolean _v_){
		this.warEnd = _v_;
	}

	@JSONField(name = "2")
	public void setServerId1(int _v_){
		this.serverId1 = _v_;
	}

	@JSONField(name = "3")
	public void setServerId2(int _v_){
		this.serverId2 = _v_;
	}

	@JSONField(name = "4")
	public void setGensInfo1(xbean.global.GensBaseInfo _v_){
		this.gensInfo1 = _v_;
	}

	@JSONField(name = "5")
	public void setGensInfo2(xbean.global.GensBaseInfo _v_){
		this.gensInfo2 = _v_;
	}

	@JSONField(name = "6")
	public void setWinGensId(long _v_){
		this.winGensId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CrossGensWar) {
			CrossGensWar _o_ = (CrossGensWar)_o1_;
			if (this.warEnd != _o_.warEnd) return false;
			if (this.serverId1 != _o_.serverId1) return false;
			if (this.serverId2 != _o_.serverId2) return false;
			if (!this.gensInfo1.equals(_o_.gensInfo1)) return false;
			if (!this.gensInfo2.equals(_o_.gensInfo2)) return false;
			if (this.winGensId != _o_.winGensId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (this.warEnd ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.serverId1);
		_h_ += _h_ * 31 + (int)(this.serverId2);
		_h_ += _h_ * 31 + this.gensInfo1.hashCode();
		_h_ += _h_ * 31 + this.gensInfo2.hashCode();
		_h_ += _h_ * 31 + (int)(winGensId ^ (winGensId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(warEnd).append(",");
		_sb_.append(serverId1).append(",");
		_sb_.append(serverId2).append(",");
		_sb_.append(gensInfo1).append(",");
		_sb_.append(gensInfo2).append(",");
		_sb_.append(winGensId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
