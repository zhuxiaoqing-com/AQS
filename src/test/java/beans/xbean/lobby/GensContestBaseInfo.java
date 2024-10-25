package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensContestBaseInfo extends warrior.mdb.XBean {
    private xbean.lobby.LBGensBaseInfo gensBaseInfo;  // 公会基础数据
    private long point;  // 排行榜分数

    GensContestBaseInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gensBaseInfo = new LBGensBaseInfo(this);
	}

	public GensContestBaseInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensContestBaseInfo(GensContestBaseInfo _o_) {
		this(_o_, null);
	}

	GensContestBaseInfo(GensContestBaseInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gensBaseInfo = new LBGensBaseInfo(_o_.gensBaseInfo, this);
		this.point = _o_.point;
	}

	public void copyFrom(GensContestBaseInfo _o_) {
        this.gensBaseInfo = new LBGensBaseInfo(_o_.gensBaseInfo, this);
        this.point = _o_.point;
	}


	@JSONField(name = "1")
	public xbean.lobby.LBGensBaseInfo getGensBaseInfo(){
		return this.gensBaseInfo;
	}

	@JSONField(name = "2")
	public long getPoint(){
		return this.point;
	}

	@JSONField(name = "1")
	public void setGensBaseInfo(xbean.lobby.LBGensBaseInfo _v_){
		this.gensBaseInfo = _v_;
	}

	@JSONField(name = "2")
	public void setPoint(long _v_){
		this.point = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensContestBaseInfo) {
			GensContestBaseInfo _o_ = (GensContestBaseInfo)_o1_;
			if (!this.gensBaseInfo.equals(_o_.gensBaseInfo)) return false;
			if (this.point != _o_.point) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.gensBaseInfo.hashCode();
		_h_ += _h_ * 31 + (int)(point ^ (point >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gensBaseInfo).append(",");
		_sb_.append(point).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
