package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class BeReportedInfo extends warrior.mdb.XBean {
    private int cfgId; 
    private int autoCount;  // 自动处理被举报次数
    private long autoRefresh;  // 自动处理上次刷新
    private int artificialCount;  // 人工处理被举报次数
    private long artificialRefresh;  // 人工处理上次刷新

    BeReportedInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public BeReportedInfo() {
		this((warrior.mdb.XBean)null);
	}

	public BeReportedInfo(BeReportedInfo _o_) {
		this(_o_, null);
	}

	BeReportedInfo(BeReportedInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.cfgId = _o_.cfgId;
		this.autoCount = _o_.autoCount;
		this.autoRefresh = _o_.autoRefresh;
		this.artificialCount = _o_.artificialCount;
		this.artificialRefresh = _o_.artificialRefresh;
	}

	public void copyFrom(BeReportedInfo _o_) {
        this.cfgId = _o_.cfgId;
        this.autoCount = _o_.autoCount;
        this.autoRefresh = _o_.autoRefresh;
        this.artificialCount = _o_.artificialCount;
        this.artificialRefresh = _o_.artificialRefresh;
	}


	@JSONField(name = "1")
	public int getCfgId(){
		return this.cfgId;
	}

	@JSONField(name = "2")
	public int getAutoCount(){
		return this.autoCount;
	}

	@JSONField(name = "3")
	public long getAutoRefresh(){
		return this.autoRefresh;
	}

	@JSONField(name = "4")
	public int getArtificialCount(){
		return this.artificialCount;
	}

	@JSONField(name = "5")
	public long getArtificialRefresh(){
		return this.artificialRefresh;
	}

	@JSONField(name = "1")
	public void setCfgId(int _v_){
		this.cfgId = _v_;
	}

	@JSONField(name = "2")
	public void setAutoCount(int _v_){
		this.autoCount = _v_;
	}

	@JSONField(name = "3")
	public void setAutoRefresh(long _v_){
		this.autoRefresh = _v_;
	}

	@JSONField(name = "4")
	public void setArtificialCount(int _v_){
		this.artificialCount = _v_;
	}

	@JSONField(name = "5")
	public void setArtificialRefresh(long _v_){
		this.artificialRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BeReportedInfo) {
			BeReportedInfo _o_ = (BeReportedInfo)_o1_;
			if (this.cfgId != _o_.cfgId) return false;
			if (this.autoCount != _o_.autoCount) return false;
			if (this.autoRefresh != _o_.autoRefresh) return false;
			if (this.artificialCount != _o_.artificialCount) return false;
			if (this.artificialRefresh != _o_.artificialRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.cfgId);
		_h_ += _h_ * 31 + (int)(this.autoCount);
		_h_ += _h_ * 31 + (int)(autoRefresh ^ (autoRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.artificialCount);
		_h_ += _h_ * 31 + (int)(artificialRefresh ^ (artificialRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(cfgId).append(",");
		_sb_.append(autoCount).append(",");
		_sb_.append(autoRefresh).append(",");
		_sb_.append(artificialCount).append(",");
		_sb_.append(artificialRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
