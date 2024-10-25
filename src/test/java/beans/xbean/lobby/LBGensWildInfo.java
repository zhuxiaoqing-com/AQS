package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBGensWildInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, LBWildInfo> wildMap;  // k:野外区域id v:野外区域数据
    private long lastCheckTime;  // 检测上次发奖励时间
    private java.util.concurrent.ConcurrentHashMap<Integer, AreaDeathInfo> areaDeathMap;  // 区域id->AreaDeathInfo
    private java.util.concurrent.CopyOnWriteArrayList<ALLAreaReport> allAreaReportList;  // 战报列表
    private java.util.concurrent.CopyOnWriteArrayList<AreaOccupyRecord> occupyRecord;  // 占领日志
    private xbean.lobby.WildStageActInfo wildStage;  // 野外结算数据
    private xbean.lobby.WildNodeRecordList nodeRecordList;  // 野外结算数据
    private java.util.concurrent.ConcurrentHashMap<Integer, AreaDeclareGens> areaDeclareGensMap;  // 公会宣战数据

    LBGensWildInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.wildMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBWildInfo>();
		this.areaDeathMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaDeathInfo>();
		this.allAreaReportList = new java.util.concurrent.CopyOnWriteArrayList<ALLAreaReport>();
		this.occupyRecord = new java.util.concurrent.CopyOnWriteArrayList<AreaOccupyRecord>();
		this.wildStage = new WildStageActInfo(this);
		this.nodeRecordList = new WildNodeRecordList(this);
		this.areaDeclareGensMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaDeclareGens>();
	}

	public LBGensWildInfo() {
		this((warrior.mdb.XBean)null);
	}

	public LBGensWildInfo(LBGensWildInfo _o_) {
		this(_o_, null);
	}

	LBGensWildInfo(LBGensWildInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.wildMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBWildInfo>();
		_o_.wildMap.forEach((_k_, _v_) -> this.wildMap.put(_k_, new LBWildInfo(_v_)));
		this.lastCheckTime = _o_.lastCheckTime;
		this.areaDeathMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaDeathInfo>();
		_o_.areaDeathMap.forEach((_k_, _v_) -> this.areaDeathMap.put(_k_, new AreaDeathInfo(_v_)));
		this.allAreaReportList = new java.util.concurrent.CopyOnWriteArrayList<ALLAreaReport>();
		_o_.allAreaReportList.forEach(_v_ -> this.allAreaReportList.add(new ALLAreaReport(_v_)));
		this.occupyRecord = new java.util.concurrent.CopyOnWriteArrayList<AreaOccupyRecord>();
		_o_.occupyRecord.forEach(_v_ -> this.occupyRecord.add(new AreaOccupyRecord(_v_)));
		this.wildStage = new WildStageActInfo(_o_.wildStage, this);
		this.nodeRecordList = new WildNodeRecordList(_o_.nodeRecordList, this);
		this.areaDeclareGensMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaDeclareGens>();
		_o_.areaDeclareGensMap.forEach((_k_, _v_) -> this.areaDeclareGensMap.put(_k_, new AreaDeclareGens(_v_)));
	}

	public void copyFrom(LBGensWildInfo _o_) {
        this.wildMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBWildInfo>();
		_o_.wildMap.forEach((_k_, _v_) -> this.wildMap.put(_k_, new LBWildInfo(_v_)));
        this.lastCheckTime = _o_.lastCheckTime;
        this.areaDeathMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaDeathInfo>();
		_o_.areaDeathMap.forEach((_k_, _v_) -> this.areaDeathMap.put(_k_, new AreaDeathInfo(_v_)));
        this.allAreaReportList = new java.util.concurrent.CopyOnWriteArrayList<ALLAreaReport>();
		_o_.allAreaReportList.forEach(_v_ -> this.allAreaReportList.add(new ALLAreaReport(_v_)));
        this.occupyRecord = new java.util.concurrent.CopyOnWriteArrayList<AreaOccupyRecord>();
		_o_.occupyRecord.forEach(_v_ -> this.occupyRecord.add(new AreaOccupyRecord(_v_)));
        this.wildStage = new WildStageActInfo(_o_.wildStage, this);
        this.nodeRecordList = new WildNodeRecordList(_o_.nodeRecordList, this);
        this.areaDeclareGensMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaDeclareGens>();
		_o_.areaDeclareGensMap.forEach((_k_, _v_) -> this.areaDeclareGensMap.put(_k_, new AreaDeclareGens(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, LBWildInfo> getWildMap(){
		return this.wildMap;
	}

	@JSONField(name = "2")
	public long getLastCheckTime(){
		return this.lastCheckTime;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, AreaDeathInfo> getAreaDeathMap(){
		return this.areaDeathMap;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArrayList<ALLAreaReport> getAllAreaReportList(){
		return this.allAreaReportList;
	}

	@JSONField(name = "10")
	public java.util.concurrent.CopyOnWriteArrayList<AreaOccupyRecord> getOccupyRecord(){
		return this.occupyRecord;
	}

	@JSONField(name = "11")
	public xbean.lobby.WildStageActInfo getWildStage(){
		return this.wildStage;
	}

	@JSONField(name = "12")
	public xbean.lobby.WildNodeRecordList getNodeRecordList(){
		return this.nodeRecordList;
	}

	@JSONField(name = "13")
	public java.util.concurrent.ConcurrentHashMap<Integer, AreaDeclareGens> getAreaDeclareGensMap(){
		return this.areaDeclareGensMap;
	}

	@JSONField(name = "1")
	public void setWildMap(java.util.concurrent.ConcurrentHashMap<Integer, LBWildInfo> _v_){
		this.wildMap = _v_;
	}

	@JSONField(name = "2")
	public void setLastCheckTime(long _v_){
		this.lastCheckTime = _v_;
	}

	@JSONField(name = "3")
	public void setAreaDeathMap(java.util.concurrent.ConcurrentHashMap<Integer, AreaDeathInfo> _v_){
		this.areaDeathMap = _v_;
	}

	@JSONField(name = "4")
	public void setAllAreaReportList(java.util.concurrent.CopyOnWriteArrayList<ALLAreaReport> _v_){
		this.allAreaReportList = _v_;
	}

	@JSONField(name = "10")
	public void setOccupyRecord(java.util.concurrent.CopyOnWriteArrayList<AreaOccupyRecord> _v_){
		this.occupyRecord = _v_;
	}

	@JSONField(name = "11")
	public void setWildStage(xbean.lobby.WildStageActInfo _v_){
		this.wildStage = _v_;
	}

	@JSONField(name = "12")
	public void setNodeRecordList(xbean.lobby.WildNodeRecordList _v_){
		this.nodeRecordList = _v_;
	}

	@JSONField(name = "13")
	public void setAreaDeclareGensMap(java.util.concurrent.ConcurrentHashMap<Integer, AreaDeclareGens> _v_){
		this.areaDeclareGensMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBGensWildInfo) {
			LBGensWildInfo _o_ = (LBGensWildInfo)_o1_;
			if (!this.wildMap.equals(_o_.wildMap)) return false;
			if (this.lastCheckTime != _o_.lastCheckTime) return false;
			if (!this.areaDeathMap.equals(_o_.areaDeathMap)) return false;
			if (!this.allAreaReportList.equals(_o_.allAreaReportList)) return false;
			if (!this.occupyRecord.equals(_o_.occupyRecord)) return false;
			if (!this.wildStage.equals(_o_.wildStage)) return false;
			if (!this.nodeRecordList.equals(_o_.nodeRecordList)) return false;
			if (!this.areaDeclareGensMap.equals(_o_.areaDeclareGensMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.wildMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastCheckTime ^ (lastCheckTime >>> 32));
		_h_ += _h_ * 31 + this.areaDeathMap.hashCode();
		_h_ += _h_ * 31 + this.allAreaReportList.hashCode();
		_h_ += _h_ * 31 + this.occupyRecord.hashCode();
		_h_ += _h_ * 31 + this.wildStage.hashCode();
		_h_ += _h_ * 31 + this.nodeRecordList.hashCode();
		_h_ += _h_ * 31 + this.areaDeclareGensMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(wildMap).append(",");
		_sb_.append(lastCheckTime).append(",");
		_sb_.append(areaDeathMap).append(",");
		_sb_.append(allAreaReportList).append(",");
		_sb_.append(occupyRecord).append(",");
		_sb_.append(wildStage).append(",");
		_sb_.append(nodeRecordList).append(",");
		_sb_.append(areaDeclareGensMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
