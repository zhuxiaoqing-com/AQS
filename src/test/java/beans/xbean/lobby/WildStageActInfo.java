package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class WildStageActInfo extends warrior.mdb.XBean {
    private int stage;  // 当前阶段
    private java.util.concurrent.ConcurrentHashMap<Integer, WildActInfo> wildActInfoMap;  // factionId->wildActInfoByFaction
    private xbean.lobby.WildActInfo wildActInfo;  // 第二阶段 统一 开始结束数据
    private long lastRealEndTime;  // 上一次真实的结束时间
    private long lastCheckTime;  // 上一次检测时间
    private xbean.lobby.CrossSeatInfo crossSeatInfo;  // 跨服席位数据

    WildStageActInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.wildActInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, WildActInfo>();
		this.wildActInfo = new WildActInfo(this);
		this.crossSeatInfo = new CrossSeatInfo(this);
	}

	public WildStageActInfo() {
		this((warrior.mdb.XBean)null);
	}

	public WildStageActInfo(WildStageActInfo _o_) {
		this(_o_, null);
	}

	WildStageActInfo(WildStageActInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.stage = _o_.stage;
		this.wildActInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, WildActInfo>();
		_o_.wildActInfoMap.forEach((_k_, _v_) -> this.wildActInfoMap.put(_k_, new WildActInfo(_v_)));
		this.wildActInfo = new WildActInfo(_o_.wildActInfo, this);
		this.lastRealEndTime = _o_.lastRealEndTime;
		this.lastCheckTime = _o_.lastCheckTime;
		this.crossSeatInfo = new CrossSeatInfo(_o_.crossSeatInfo, this);
	}

	public void copyFrom(WildStageActInfo _o_) {
        this.stage = _o_.stage;
        this.wildActInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, WildActInfo>();
		_o_.wildActInfoMap.forEach((_k_, _v_) -> this.wildActInfoMap.put(_k_, new WildActInfo(_v_)));
        this.wildActInfo = new WildActInfo(_o_.wildActInfo, this);
        this.lastRealEndTime = _o_.lastRealEndTime;
        this.lastCheckTime = _o_.lastCheckTime;
        this.crossSeatInfo = new CrossSeatInfo(_o_.crossSeatInfo, this);
	}


	@JSONField(name = "1")
	public int getStage(){
		return this.stage;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, WildActInfo> getWildActInfoMap(){
		return this.wildActInfoMap;
	}

	@JSONField(name = "3")
	public xbean.lobby.WildActInfo getWildActInfo(){
		return this.wildActInfo;
	}

	@JSONField(name = "4")
	public long getLastRealEndTime(){
		return this.lastRealEndTime;
	}

	@JSONField(name = "5")
	public long getLastCheckTime(){
		return this.lastCheckTime;
	}

	@JSONField(name = "6")
	public xbean.lobby.CrossSeatInfo getCrossSeatInfo(){
		return this.crossSeatInfo;
	}

	@JSONField(name = "1")
	public void setStage(int _v_){
		this.stage = _v_;
	}

	@JSONField(name = "2")
	public void setWildActInfoMap(java.util.concurrent.ConcurrentHashMap<Integer, WildActInfo> _v_){
		this.wildActInfoMap = _v_;
	}

	@JSONField(name = "3")
	public void setWildActInfo(xbean.lobby.WildActInfo _v_){
		this.wildActInfo = _v_;
	}

	@JSONField(name = "4")
	public void setLastRealEndTime(long _v_){
		this.lastRealEndTime = _v_;
	}

	@JSONField(name = "5")
	public void setLastCheckTime(long _v_){
		this.lastCheckTime = _v_;
	}

	@JSONField(name = "6")
	public void setCrossSeatInfo(xbean.lobby.CrossSeatInfo _v_){
		this.crossSeatInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WildStageActInfo) {
			WildStageActInfo _o_ = (WildStageActInfo)_o1_;
			if (this.stage != _o_.stage) return false;
			if (!this.wildActInfoMap.equals(_o_.wildActInfoMap)) return false;
			if (!this.wildActInfo.equals(_o_.wildActInfo)) return false;
			if (this.lastRealEndTime != _o_.lastRealEndTime) return false;
			if (this.lastCheckTime != _o_.lastCheckTime) return false;
			if (!this.crossSeatInfo.equals(_o_.crossSeatInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.stage);
		_h_ += _h_ * 31 + this.wildActInfoMap.hashCode();
		_h_ += _h_ * 31 + this.wildActInfo.hashCode();
		_h_ += _h_ * 31 + (int)(lastRealEndTime ^ (lastRealEndTime >>> 32));
		_h_ += _h_ * 31 + (int)(lastCheckTime ^ (lastCheckTime >>> 32));
		_h_ += _h_ * 31 + this.crossSeatInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(stage).append(",");
		_sb_.append(wildActInfoMap).append(",");
		_sb_.append(wildActInfo).append(",");
		_sb_.append(lastRealEndTime).append(",");
		_sb_.append(lastCheckTime).append(",");
		_sb_.append(crossSeatInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
