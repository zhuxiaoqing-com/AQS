package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerReportInfo extends warrior.mdb.XBean {
    private xbean.global.ReportInfo reportInfo;  // 主动举报信息
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> beReportMap;  // 被举报信息
    private long beReportLastRefresh;  // 被举报信息上一次刷新时间

    PlayerReportInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.reportInfo = new ReportInfo(this);
		this.beReportMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
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
		this.beReportMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.beReportMap.forEach((_k_, _v_) -> this.beReportMap.put(_k_, _v_));
		this.beReportLastRefresh = _o_.beReportLastRefresh;
	}

	public void copyFrom(PlayerReportInfo _o_) {
        this.reportInfo = new ReportInfo(_o_.reportInfo, this);
        this.beReportMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.beReportMap.forEach((_k_, _v_) -> this.beReportMap.put(_k_, _v_));
        this.beReportLastRefresh = _o_.beReportLastRefresh;
	}


	@JSONField(name = "1")
	public xbean.global.ReportInfo getReportInfo(){
		return this.reportInfo;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getBeReportMap(){
		return this.beReportMap;
	}

	@JSONField(name = "3")
	public long getBeReportLastRefresh(){
		return this.beReportLastRefresh;
	}

	@JSONField(name = "1")
	public void setReportInfo(xbean.global.ReportInfo _v_){
		this.reportInfo = _v_;
	}

	@JSONField(name = "2")
	public void setBeReportMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.beReportMap = _v_;
	}

	@JSONField(name = "3")
	public void setBeReportLastRefresh(long _v_){
		this.beReportLastRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerReportInfo) {
			PlayerReportInfo _o_ = (PlayerReportInfo)_o1_;
			if (!this.reportInfo.equals(_o_.reportInfo)) return false;
			if (!this.beReportMap.equals(_o_.beReportMap)) return false;
			if (this.beReportLastRefresh != _o_.beReportLastRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.reportInfo.hashCode();
		_h_ += _h_ * 31 + this.beReportMap.hashCode();
		_h_ += _h_ * 31 + (int)(beReportLastRefresh ^ (beReportLastRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(reportInfo).append(",");
		_sb_.append(beReportMap).append(",");
		_sb_.append(beReportLastRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
