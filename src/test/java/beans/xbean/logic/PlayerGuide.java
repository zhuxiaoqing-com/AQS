package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerGuide extends warrior.mdb.XBean {
    private int gid;  // 当前引导id
    private String json;  // 引导信息

    PlayerGuide(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.json = "";
	}

	public PlayerGuide() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerGuide(PlayerGuide _o_) {
		this(_o_, null);
	}

	PlayerGuide(PlayerGuide _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gid = _o_.gid;
		this.json = _o_.json;
	}

	public void copyFrom(PlayerGuide _o_) {
        this.gid = _o_.gid;
        this.json = _o_.json;
	}


	@JSONField(name = "1")
	public int getGid(){
		return this.gid;
	}

	@JSONField(name = "2")
	public String getJson(){
		return this.json;
	}

	@JSONField(name = "1")
	public void setGid(int _v_){
		this.gid = _v_;
	}

	@JSONField(name = "2")
	public void setJson(String _v_){
		this.json = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerGuide) {
			PlayerGuide _o_ = (PlayerGuide)_o1_;
			if (this.gid != _o_.gid) return false;
			if (!this.json.equals(_o_.json)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.gid);
		_h_ += _h_ * 31 + this.json.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gid).append(",");
		_sb_.append("T").append(json.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
