package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class ReportInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, Long> reportMap; 
    private long lastRefresh; 

    ReportInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.reportMap = new java.util.concurrent.ConcurrentHashMap<Long, Long>();
	}

	public ReportInfo() {
		this((warrior.mdb.XBean)null);
	}

	public ReportInfo(ReportInfo _o_) {
		this(_o_, null);
	}

	ReportInfo(ReportInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.reportMap = new java.util.concurrent.ConcurrentHashMap<Long, Long>();
		_o_.reportMap.forEach((_k_, _v_) -> this.reportMap.put(_k_, _v_));
		this.lastRefresh = _o_.lastRefresh;
	}

	public void copyFrom(ReportInfo _o_) {
        this.reportMap = new java.util.concurrent.ConcurrentHashMap<Long, Long>();
		_o_.reportMap.forEach((_k_, _v_) -> this.reportMap.put(_k_, _v_));
        this.lastRefresh = _o_.lastRefresh;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, Long> getReportMap(){
		return this.reportMap;
	}

	@JSONField(name = "2")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "1")
	public void setReportMap(java.util.concurrent.ConcurrentHashMap<Long, Long> _v_){
		this.reportMap = _v_;
	}

	@JSONField(name = "2")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ReportInfo) {
			ReportInfo _o_ = (ReportInfo)_o1_;
			if (!this.reportMap.equals(_o_.reportMap)) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.reportMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(reportMap).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
