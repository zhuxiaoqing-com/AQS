package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CrossFactionWarMapInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionWarLandInfo> landInfo; 
    private boolean isSummary; 
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionCrossData> factionAllData; 
    private java.util.concurrent.CopyOnWriteArrayList<ServerFactionQualifyInfo> qualifyList; 

    CrossFactionWarMapInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.landInfo = new java.util.concurrent.ConcurrentHashMap<Integer, FactionWarLandInfo>();
		this.factionAllData = new java.util.concurrent.ConcurrentHashMap<Integer, FactionCrossData>();
		this.qualifyList = new java.util.concurrent.CopyOnWriteArrayList<ServerFactionQualifyInfo>();
	}

	public CrossFactionWarMapInfo() {
		this((warrior.mdb.XBean)null);
	}

	public CrossFactionWarMapInfo(CrossFactionWarMapInfo _o_) {
		this(_o_, null);
	}

	CrossFactionWarMapInfo(CrossFactionWarMapInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.landInfo = new java.util.concurrent.ConcurrentHashMap<Integer, FactionWarLandInfo>();
		_o_.landInfo.forEach((_k_, _v_) -> this.landInfo.put(_k_, new FactionWarLandInfo(_v_)));
		this.isSummary = _o_.isSummary;
		this.factionAllData = new java.util.concurrent.ConcurrentHashMap<Integer, FactionCrossData>();
		_o_.factionAllData.forEach((_k_, _v_) -> this.factionAllData.put(_k_, new FactionCrossData(_v_)));
		this.qualifyList = new java.util.concurrent.CopyOnWriteArrayList<ServerFactionQualifyInfo>();
		_o_.qualifyList.forEach(_v_ -> this.qualifyList.add(new ServerFactionQualifyInfo(_v_)));
	}

	public void copyFrom(CrossFactionWarMapInfo _o_) {
        this.landInfo = new java.util.concurrent.ConcurrentHashMap<Integer, FactionWarLandInfo>();
		_o_.landInfo.forEach((_k_, _v_) -> this.landInfo.put(_k_, new FactionWarLandInfo(_v_)));
        this.isSummary = _o_.isSummary;
        this.factionAllData = new java.util.concurrent.ConcurrentHashMap<Integer, FactionCrossData>();
		_o_.factionAllData.forEach((_k_, _v_) -> this.factionAllData.put(_k_, new FactionCrossData(_v_)));
        this.qualifyList = new java.util.concurrent.CopyOnWriteArrayList<ServerFactionQualifyInfo>();
		_o_.qualifyList.forEach(_v_ -> this.qualifyList.add(new ServerFactionQualifyInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionWarLandInfo> getLandInfo(){
		return this.landInfo;
	}

	@JSONField(name = "2")
	public boolean getIsSummary(){
		return this.isSummary;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionCrossData> getFactionAllData(){
		return this.factionAllData;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArrayList<ServerFactionQualifyInfo> getQualifyList(){
		return this.qualifyList;
	}

	@JSONField(name = "1")
	public void setLandInfo(java.util.concurrent.ConcurrentHashMap<Integer, FactionWarLandInfo> _v_){
		this.landInfo = _v_;
	}

	@JSONField(name = "2")
	public void setIsSummary(boolean _v_){
		this.isSummary = _v_;
	}

	@JSONField(name = "3")
	public void setFactionAllData(java.util.concurrent.ConcurrentHashMap<Integer, FactionCrossData> _v_){
		this.factionAllData = _v_;
	}

	@JSONField(name = "4")
	public void setQualifyList(java.util.concurrent.CopyOnWriteArrayList<ServerFactionQualifyInfo> _v_){
		this.qualifyList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CrossFactionWarMapInfo) {
			CrossFactionWarMapInfo _o_ = (CrossFactionWarMapInfo)_o1_;
			if (!this.landInfo.equals(_o_.landInfo)) return false;
			if (this.isSummary != _o_.isSummary) return false;
			if (!this.factionAllData.equals(_o_.factionAllData)) return false;
			if (!this.qualifyList.equals(_o_.qualifyList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.landInfo.hashCode();
		_h_ += _h_ * 31 + (this.isSummary ? 1231 : 1237);
		_h_ += _h_ * 31 + this.factionAllData.hashCode();
		_h_ += _h_ * 31 + this.qualifyList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(landInfo).append(",");
		_sb_.append(isSummary).append(",");
		_sb_.append(factionAllData).append(",");
		_sb_.append(qualifyList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
