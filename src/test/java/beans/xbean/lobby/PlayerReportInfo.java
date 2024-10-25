package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerReportInfo extends warrior.mdb.XBean {
    private xbean.lobby.ReportInfo reportInfo;  // 主动举报信息
    private java.util.concurrent.ConcurrentHashMap<Integer, BeReportedInfo> beReportInfo;  // k->cfgId

    PlayerReportInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.reportInfo = new ReportInfo(this);
		this.beReportInfo = new java.util.concurrent.ConcurrentHashMap<Integer, BeReportedInfo>();
	}

	public PlayerReportInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerReportInfo(PlayerReportInfo _o_) {
		this(_o_, null);
	}

	PlayerReportInfo(PlayerReportInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.reportInfo = new ReportInfo(_o_.reportInfo, this);
		this.beReportInfo = new java.util.concurrent.ConcurrentHashMap<Integer, BeReportedInfo>();
		_o_.beReportInfo.forEach((_k_, _v_) -> this.beReportInfo.put(_k_, new BeReportedInfo(_v_)));
	}

	public void copyFrom(PlayerReportInfo _o_) {
        this.reportInfo = new ReportInfo(_o_.reportInfo, this);
        this.beReportInfo = new java.util.concurrent.ConcurrentHashMap<Integer, BeReportedInfo>();
		_o_.beReportInfo.forEach((_k_, _v_) -> this.beReportInfo.put(_k_, new BeReportedInfo(_v_)));
	}


	@JSONField(name = "1")
	public xbean.lobby.ReportInfo getReportInfo(){
		return this.reportInfo;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, BeReportedInfo> getBeReportInfo(){
		return this.beReportInfo;
	}

	@JSONField(name = "1")
	public void setReportInfo(xbean.lobby.ReportInfo _v_){
		this.reportInfo = _v_;
	}

	@JSONField(name = "2")
	public void setBeReportInfo(java.util.concurrent.ConcurrentHashMap<Integer, BeReportedInfo> _v_){
		this.beReportInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerReportInfo) {
			PlayerReportInfo _o_ = (PlayerReportInfo)_o1_;
			if (!this.reportInfo.equals(_o_.reportInfo)) return false;
			if (!this.beReportInfo.equals(_o_.beReportInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.reportInfo.hashCode();
		_h_ += _h_ * 31 + this.beReportInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(reportInfo).append(",");
		_sb_.append(beReportInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
