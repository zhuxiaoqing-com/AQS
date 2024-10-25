package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AreaReportInfo extends warrior.mdb.XBean {
    private int areaId;  // 区域id
    private java.util.concurrent.ConcurrentHashMap<Integer, ReportScore> scoreMap;  // 战报积分 k->阵营id v:分数数据
    private int winFaction;  // 胜利阵营
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> faction2ScoreMap;  // 城市争夺积分数据
    private int roleNum;  // 参战人数

    AreaReportInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.scoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, ReportScore>();
		this.faction2ScoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
	}

	public AreaReportInfo() {
		this((warrior.mdb.XBean)null);
	}

	public AreaReportInfo(AreaReportInfo _o_) {
		this(_o_, null);
	}

	AreaReportInfo(AreaReportInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaId = _o_.areaId;
		this.scoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, ReportScore>();
		_o_.scoreMap.forEach((_k_, _v_) -> this.scoreMap.put(_k_, new ReportScore(_v_)));
		this.winFaction = _o_.winFaction;
		this.faction2ScoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.faction2ScoreMap.forEach((_k_, _v_) -> this.faction2ScoreMap.put(_k_, _v_));
		this.roleNum = _o_.roleNum;
	}

	public void copyFrom(AreaReportInfo _o_) {
        this.areaId = _o_.areaId;
        this.scoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, ReportScore>();
		_o_.scoreMap.forEach((_k_, _v_) -> this.scoreMap.put(_k_, new ReportScore(_v_)));
        this.winFaction = _o_.winFaction;
        this.faction2ScoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.faction2ScoreMap.forEach((_k_, _v_) -> this.faction2ScoreMap.put(_k_, _v_));
        this.roleNum = _o_.roleNum;
	}


	@JSONField(name = "1")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, ReportScore> getScoreMap(){
		return this.scoreMap;
	}

	@JSONField(name = "3")
	public int getWinFaction(){
		return this.winFaction;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getFaction2ScoreMap(){
		return this.faction2ScoreMap;
	}

	@JSONField(name = "5")
	public int getRoleNum(){
		return this.roleNum;
	}

	@JSONField(name = "1")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "2")
	public void setScoreMap(java.util.concurrent.ConcurrentHashMap<Integer, ReportScore> _v_){
		this.scoreMap = _v_;
	}

	@JSONField(name = "3")
	public void setWinFaction(int _v_){
		this.winFaction = _v_;
	}

	@JSONField(name = "4")
	public void setFaction2ScoreMap(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.faction2ScoreMap = _v_;
	}

	@JSONField(name = "5")
	public void setRoleNum(int _v_){
		this.roleNum = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AreaReportInfo) {
			AreaReportInfo _o_ = (AreaReportInfo)_o1_;
			if (this.areaId != _o_.areaId) return false;
			if (!this.scoreMap.equals(_o_.scoreMap)) return false;
			if (this.winFaction != _o_.winFaction) return false;
			if (!this.faction2ScoreMap.equals(_o_.faction2ScoreMap)) return false;
			if (this.roleNum != _o_.roleNum) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + this.scoreMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.winFaction);
		_h_ += _h_ * 31 + this.faction2ScoreMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.roleNum);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaId).append(",");
		_sb_.append(scoreMap).append(",");
		_sb_.append(winFaction).append(",");
		_sb_.append(faction2ScoreMap).append(",");
		_sb_.append(roleNum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
