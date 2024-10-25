package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AllianceForm extends warrior.mdb.XBean {
    private long initiatorGensId; 
    private int margin;  // 保证金
    private int keepDay;  // 盟约有效期(天数)
    private String aName;  // 名字
    private String shortCall;  // 简称
    private String notice;  // 公告
    private long endTime;  // 失效时间
    private int icon; 
    private long targetGensId; 

    AllianceForm(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.aName = "";
		this.shortCall = "";
		this.notice = "";
	}

	public AllianceForm() {
		this((warrior.mdb.XBean)null);
	}

	public AllianceForm(AllianceForm _o_) {
		this(_o_, null);
	}

	AllianceForm(AllianceForm _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.initiatorGensId = _o_.initiatorGensId;
		this.margin = _o_.margin;
		this.keepDay = _o_.keepDay;
		this.aName = _o_.aName;
		this.shortCall = _o_.shortCall;
		this.notice = _o_.notice;
		this.endTime = _o_.endTime;
		this.icon = _o_.icon;
		this.targetGensId = _o_.targetGensId;
	}

	public void copyFrom(AllianceForm _o_) {
        this.initiatorGensId = _o_.initiatorGensId;
        this.margin = _o_.margin;
        this.keepDay = _o_.keepDay;
        this.aName = _o_.aName;
        this.shortCall = _o_.shortCall;
        this.notice = _o_.notice;
        this.endTime = _o_.endTime;
        this.icon = _o_.icon;
        this.targetGensId = _o_.targetGensId;
	}


	@JSONField(name = "1")
	public long getInitiatorGensId(){
		return this.initiatorGensId;
	}

	@JSONField(name = "2")
	public int getMargin(){
		return this.margin;
	}

	@JSONField(name = "3")
	public int getKeepDay(){
		return this.keepDay;
	}

	@JSONField(name = "4")
	public String getAName(){
		return this.aName;
	}

	@JSONField(name = "5")
	public String getShortCall(){
		return this.shortCall;
	}

	@JSONField(name = "6")
	public String getNotice(){
		return this.notice;
	}

	@JSONField(name = "7")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "8")
	public int getIcon(){
		return this.icon;
	}

	@JSONField(name = "9")
	public long getTargetGensId(){
		return this.targetGensId;
	}

	@JSONField(name = "1")
	public void setInitiatorGensId(long _v_){
		this.initiatorGensId = _v_;
	}

	@JSONField(name = "2")
	public void setMargin(int _v_){
		this.margin = _v_;
	}

	@JSONField(name = "3")
	public void setKeepDay(int _v_){
		this.keepDay = _v_;
	}

	@JSONField(name = "4")
	public void setAName(String _v_){
		this.aName = _v_;
	}

	@JSONField(name = "5")
	public void setShortCall(String _v_){
		this.shortCall = _v_;
	}

	@JSONField(name = "6")
	public void setNotice(String _v_){
		this.notice = _v_;
	}

	@JSONField(name = "7")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "8")
	public void setIcon(int _v_){
		this.icon = _v_;
	}

	@JSONField(name = "9")
	public void setTargetGensId(long _v_){
		this.targetGensId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AllianceForm) {
			AllianceForm _o_ = (AllianceForm)_o1_;
			if (this.initiatorGensId != _o_.initiatorGensId) return false;
			if (this.margin != _o_.margin) return false;
			if (this.keepDay != _o_.keepDay) return false;
			if (!this.aName.equals(_o_.aName)) return false;
			if (!this.shortCall.equals(_o_.shortCall)) return false;
			if (!this.notice.equals(_o_.notice)) return false;
			if (this.endTime != _o_.endTime) return false;
			if (this.icon != _o_.icon) return false;
			if (this.targetGensId != _o_.targetGensId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(initiatorGensId ^ (initiatorGensId >>> 32));
		_h_ += _h_ * 31 + (int)(this.margin);
		_h_ += _h_ * 31 + (int)(this.keepDay);
		_h_ += _h_ * 31 + this.aName.hashCode();
		_h_ += _h_ * 31 + this.shortCall.hashCode();
		_h_ += _h_ * 31 + this.notice.hashCode();
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.icon);
		_h_ += _h_ * 31 + (int)(targetGensId ^ (targetGensId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(initiatorGensId).append(",");
		_sb_.append(margin).append(",");
		_sb_.append(keepDay).append(",");
		_sb_.append("T").append(aName.length()).append(",");
		_sb_.append("T").append(shortCall.length()).append(",");
		_sb_.append("T").append(notice.length()).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(icon).append(",");
		_sb_.append(targetGensId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
