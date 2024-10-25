package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class Alliance extends warrior.mdb.XBean {
    private long id; 
    private String name;  // 联盟名称
    private int icon;  // 联盟图标
    private String notice;  // 公告
    private long ruleGens;  // 执政军团id
    private java.util.concurrent.ConcurrentHashMap<Long, Integer> allys;  // 联盟所有军团id->?
    private long endTime;  // 联盟结束时间
    private int maxNum;  // 当前最大军团数量
    private String shortCall;  // 简称
    private java.util.concurrent.ConcurrentHashMap<Long, AllianceJoinApply> apply;  // 申请列表
    private int margin;  // 保证金
    private xbean.lobby.AllianceRenew allianceRenew;  // 当前的续期
    private long createTime;  // 创建时间

    Alliance(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.notice = "";
		this.allys = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		this.shortCall = "";
		this.apply = new java.util.concurrent.ConcurrentHashMap<Long, AllianceJoinApply>();
		this.allianceRenew = new AllianceRenew(this);
	}

	public Alliance() {
		this((warrior.mdb.XBean)null);
	}

	public Alliance(Alliance _o_) {
		this(_o_, null);
	}

	Alliance(Alliance _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.name = _o_.name;
		this.icon = _o_.icon;
		this.notice = _o_.notice;
		this.ruleGens = _o_.ruleGens;
		this.allys = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.allys.forEach((_k_, _v_) -> this.allys.put(_k_, _v_));
		this.endTime = _o_.endTime;
		this.maxNum = _o_.maxNum;
		this.shortCall = _o_.shortCall;
		this.apply = new java.util.concurrent.ConcurrentHashMap<Long, AllianceJoinApply>();
		_o_.apply.forEach((_k_, _v_) -> this.apply.put(_k_, new AllianceJoinApply(_v_)));
		this.margin = _o_.margin;
		this.allianceRenew = new AllianceRenew(_o_.allianceRenew, this);
		this.createTime = _o_.createTime;
	}

	public void copyFrom(Alliance _o_) {
        this.id = _o_.id;
        this.name = _o_.name;
        this.icon = _o_.icon;
        this.notice = _o_.notice;
        this.ruleGens = _o_.ruleGens;
        this.allys = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.allys.forEach((_k_, _v_) -> this.allys.put(_k_, _v_));
        this.endTime = _o_.endTime;
        this.maxNum = _o_.maxNum;
        this.shortCall = _o_.shortCall;
        this.apply = new java.util.concurrent.ConcurrentHashMap<Long, AllianceJoinApply>();
		_o_.apply.forEach((_k_, _v_) -> this.apply.put(_k_, new AllianceJoinApply(_v_)));
        this.margin = _o_.margin;
        this.allianceRenew = new AllianceRenew(_o_.allianceRenew, this);
        this.createTime = _o_.createTime;
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "3")
	public int getIcon(){
		return this.icon;
	}

	@JSONField(name = "4")
	public String getNotice(){
		return this.notice;
	}

	@JSONField(name = "5")
	public long getRuleGens(){
		return this.ruleGens;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Long, Integer> getAllys(){
		return this.allys;
	}

	@JSONField(name = "7")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "9")
	public int getMaxNum(){
		return this.maxNum;
	}

	@JSONField(name = "10")
	public String getShortCall(){
		return this.shortCall;
	}

	@JSONField(name = "11")
	public java.util.concurrent.ConcurrentHashMap<Long, AllianceJoinApply> getApply(){
		return this.apply;
	}

	@JSONField(name = "12")
	public int getMargin(){
		return this.margin;
	}

	@JSONField(name = "13")
	public xbean.lobby.AllianceRenew getAllianceRenew(){
		return this.allianceRenew;
	}

	@JSONField(name = "14")
	public long getCreateTime(){
		return this.createTime;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "3")
	public void setIcon(int _v_){
		this.icon = _v_;
	}

	@JSONField(name = "4")
	public void setNotice(String _v_){
		this.notice = _v_;
	}

	@JSONField(name = "5")
	public void setRuleGens(long _v_){
		this.ruleGens = _v_;
	}

	@JSONField(name = "6")
	public void setAllys(java.util.concurrent.ConcurrentHashMap<Long, Integer> _v_){
		this.allys = _v_;
	}

	@JSONField(name = "7")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "9")
	public void setMaxNum(int _v_){
		this.maxNum = _v_;
	}

	@JSONField(name = "10")
	public void setShortCall(String _v_){
		this.shortCall = _v_;
	}

	@JSONField(name = "11")
	public void setApply(java.util.concurrent.ConcurrentHashMap<Long, AllianceJoinApply> _v_){
		this.apply = _v_;
	}

	@JSONField(name = "12")
	public void setMargin(int _v_){
		this.margin = _v_;
	}

	@JSONField(name = "13")
	public void setAllianceRenew(xbean.lobby.AllianceRenew _v_){
		this.allianceRenew = _v_;
	}

	@JSONField(name = "14")
	public void setCreateTime(long _v_){
		this.createTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Alliance) {
			Alliance _o_ = (Alliance)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (this.icon != _o_.icon) return false;
			if (!this.notice.equals(_o_.notice)) return false;
			if (this.ruleGens != _o_.ruleGens) return false;
			if (!this.allys.equals(_o_.allys)) return false;
			if (this.endTime != _o_.endTime) return false;
			if (this.maxNum != _o_.maxNum) return false;
			if (!this.shortCall.equals(_o_.shortCall)) return false;
			if (!this.apply.equals(_o_.apply)) return false;
			if (this.margin != _o_.margin) return false;
			if (!this.allianceRenew.equals(_o_.allianceRenew)) return false;
			if (this.createTime != _o_.createTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + (int)(this.icon);
		_h_ += _h_ * 31 + this.notice.hashCode();
		_h_ += _h_ * 31 + (int)(ruleGens ^ (ruleGens >>> 32));
		_h_ += _h_ * 31 + this.allys.hashCode();
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.maxNum);
		_h_ += _h_ * 31 + this.shortCall.hashCode();
		_h_ += _h_ * 31 + this.apply.hashCode();
		_h_ += _h_ * 31 + (int)(this.margin);
		_h_ += _h_ * 31 + this.allianceRenew.hashCode();
		_h_ += _h_ * 31 + (int)(createTime ^ (createTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(icon).append(",");
		_sb_.append("T").append(notice.length()).append(",");
		_sb_.append(ruleGens).append(",");
		_sb_.append(allys).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(maxNum).append(",");
		_sb_.append("T").append(shortCall.length()).append(",");
		_sb_.append(apply).append(",");
		_sb_.append(margin).append(",");
		_sb_.append(allianceRenew).append(",");
		_sb_.append(createTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
