package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBAllAreaReportDetail extends warrior.mdb.XBean {
    private long srl;  // 赛季开始时间
    private long startTime;  // 赛季开始时间
    private long endTime;  // 赛季结束时间
    private java.util.concurrent.ConcurrentHashMap<Long, PlayerAreaReportInfo> playerReportInfo;  // 成员排名
    private java.util.concurrent.ConcurrentHashMap<Long, GensReportInfo> gensReportInfo;  // 公会排名
    private java.util.concurrent.ConcurrentHashMap<Integer, AreaReportInfo> areaReportInfo;  // 区域数据

    LBAllAreaReportDetail(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerReportInfo = new java.util.concurrent.ConcurrentHashMap<Long, PlayerAreaReportInfo>();
		this.gensReportInfo = new java.util.concurrent.ConcurrentHashMap<Long, GensReportInfo>();
		this.areaReportInfo = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReportInfo>();
	}

	public LBAllAreaReportDetail() {
		this((warrior.mdb.XBean)null);
	}

	public LBAllAreaReportDetail(LBAllAreaReportDetail _o_) {
		this(_o_, null);
	}

	LBAllAreaReportDetail(LBAllAreaReportDetail _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.startTime = _o_.startTime;
		this.endTime = _o_.endTime;
		this.playerReportInfo = new java.util.concurrent.ConcurrentHashMap<Long, PlayerAreaReportInfo>();
		_o_.playerReportInfo.forEach((_k_, _v_) -> this.playerReportInfo.put(_k_, new PlayerAreaReportInfo(_v_)));
		this.gensReportInfo = new java.util.concurrent.ConcurrentHashMap<Long, GensReportInfo>();
		_o_.gensReportInfo.forEach((_k_, _v_) -> this.gensReportInfo.put(_k_, new GensReportInfo(_v_)));
		this.areaReportInfo = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReportInfo>();
		_o_.areaReportInfo.forEach((_k_, _v_) -> this.areaReportInfo.put(_k_, new AreaReportInfo(_v_)));
	}

	public void copyFrom(LBAllAreaReportDetail _o_) {
        this.srl = _o_.srl;
        this.startTime = _o_.startTime;
        this.endTime = _o_.endTime;
        this.playerReportInfo = new java.util.concurrent.ConcurrentHashMap<Long, PlayerAreaReportInfo>();
		_o_.playerReportInfo.forEach((_k_, _v_) -> this.playerReportInfo.put(_k_, new PlayerAreaReportInfo(_v_)));
        this.gensReportInfo = new java.util.concurrent.ConcurrentHashMap<Long, GensReportInfo>();
		_o_.gensReportInfo.forEach((_k_, _v_) -> this.gensReportInfo.put(_k_, new GensReportInfo(_v_)));
        this.areaReportInfo = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReportInfo>();
		_o_.areaReportInfo.forEach((_k_, _v_) -> this.areaReportInfo.put(_k_, new AreaReportInfo(_v_)));
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "3")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Long, PlayerAreaReportInfo> getPlayerReportInfo(){
		return this.playerReportInfo;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Long, GensReportInfo> getGensReportInfo(){
		return this.gensReportInfo;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, AreaReportInfo> getAreaReportInfo(){
		return this.areaReportInfo;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "3")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "4")
	public void setPlayerReportInfo(java.util.concurrent.ConcurrentHashMap<Long, PlayerAreaReportInfo> _v_){
		this.playerReportInfo = _v_;
	}

	@JSONField(name = "5")
	public void setGensReportInfo(java.util.concurrent.ConcurrentHashMap<Long, GensReportInfo> _v_){
		this.gensReportInfo = _v_;
	}

	@JSONField(name = "6")
	public void setAreaReportInfo(java.util.concurrent.ConcurrentHashMap<Integer, AreaReportInfo> _v_){
		this.areaReportInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBAllAreaReportDetail) {
			LBAllAreaReportDetail _o_ = (LBAllAreaReportDetail)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.startTime != _o_.startTime) return false;
			if (this.endTime != _o_.endTime) return false;
			if (!this.playerReportInfo.equals(_o_.playerReportInfo)) return false;
			if (!this.gensReportInfo.equals(_o_.gensReportInfo)) return false;
			if (!this.areaReportInfo.equals(_o_.areaReportInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + this.playerReportInfo.hashCode();
		_h_ += _h_ * 31 + this.gensReportInfo.hashCode();
		_h_ += _h_ * 31 + this.areaReportInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(playerReportInfo).append(",");
		_sb_.append(gensReportInfo).append(",");
		_sb_.append(areaReportInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
