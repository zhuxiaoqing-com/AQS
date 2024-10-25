package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Worker extends warrior.mdb.XBean {
    private long srl;  // 工人srl
    private int cfgId;  // 工人配表Id
    private long bornTime;  // 出生时间
    private int energy;  // 工人剩余精力
    private int buildResId;  // 工人当前分配的工作(要采集哪个采集物id)
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> resMap;  // 工人采集的奖励; key:资源Id; value:数量
    private long lastOfflineCalc;  // 上一次离线计算资源时间
    private long collectNum;  // 采集次数,工人将采集物送到仓库后清零
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> allResMap;  // 单个工人的总收益，包括离线的（不包括额外的）
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> extraResMap;  // 工人采集的额外奖励
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> extraAllResMap;  // 单个工人的额外总收益
    private int maxEnergy;  // 工人的最大精力

    Worker(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.resMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.allResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.extraResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.extraAllResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public Worker() {
		this((warrior.mdb.XBean)null);
	}

	public Worker(Worker _o_) {
		this(_o_, null);
	}

	Worker(Worker _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.cfgId = _o_.cfgId;
		this.bornTime = _o_.bornTime;
		this.energy = _o_.energy;
		this.buildResId = _o_.buildResId;
		this.resMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resMap.forEach((_k_, _v_) -> this.resMap.put(_k_, _v_));
		this.lastOfflineCalc = _o_.lastOfflineCalc;
		this.collectNum = _o_.collectNum;
		this.allResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.allResMap.forEach((_k_, _v_) -> this.allResMap.put(_k_, _v_));
		this.extraResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.extraResMap.forEach((_k_, _v_) -> this.extraResMap.put(_k_, _v_));
		this.extraAllResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.extraAllResMap.forEach((_k_, _v_) -> this.extraAllResMap.put(_k_, _v_));
		this.maxEnergy = _o_.maxEnergy;
	}

	public void copyFrom(Worker _o_) {
        this.srl = _o_.srl;
        this.cfgId = _o_.cfgId;
        this.bornTime = _o_.bornTime;
        this.energy = _o_.energy;
        this.buildResId = _o_.buildResId;
        this.resMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resMap.forEach((_k_, _v_) -> this.resMap.put(_k_, _v_));
        this.lastOfflineCalc = _o_.lastOfflineCalc;
        this.collectNum = _o_.collectNum;
        this.allResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.allResMap.forEach((_k_, _v_) -> this.allResMap.put(_k_, _v_));
        this.extraResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.extraResMap.forEach((_k_, _v_) -> this.extraResMap.put(_k_, _v_));
        this.extraAllResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.extraAllResMap.forEach((_k_, _v_) -> this.extraAllResMap.put(_k_, _v_));
        this.maxEnergy = _o_.maxEnergy;
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public int getCfgId(){
		return this.cfgId;
	}

	@JSONField(name = "3")
	public long getBornTime(){
		return this.bornTime;
	}

	@JSONField(name = "4")
	public int getEnergy(){
		return this.energy;
	}

	@JSONField(name = "5")
	public int getBuildResId(){
		return this.buildResId;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getResMap(){
		return this.resMap;
	}

	@JSONField(name = "7")
	public long getLastOfflineCalc(){
		return this.lastOfflineCalc;
	}

	@JSONField(name = "8")
	public long getCollectNum(){
		return this.collectNum;
	}

	@JSONField(name = "9")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getAllResMap(){
		return this.allResMap;
	}

	@JSONField(name = "10")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getExtraResMap(){
		return this.extraResMap;
	}

	@JSONField(name = "11")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getExtraAllResMap(){
		return this.extraAllResMap;
	}

	@JSONField(name = "12")
	public int getMaxEnergy(){
		return this.maxEnergy;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setCfgId(int _v_){
		this.cfgId = _v_;
	}

	@JSONField(name = "3")
	public void setBornTime(long _v_){
		this.bornTime = _v_;
	}

	@JSONField(name = "4")
	public void setEnergy(int _v_){
		this.energy = _v_;
	}

	@JSONField(name = "5")
	public void setBuildResId(int _v_){
		this.buildResId = _v_;
	}

	@JSONField(name = "6")
	public void setResMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.resMap = _v_;
	}

	@JSONField(name = "7")
	public void setLastOfflineCalc(long _v_){
		this.lastOfflineCalc = _v_;
	}

	@JSONField(name = "8")
	public void setCollectNum(long _v_){
		this.collectNum = _v_;
	}

	@JSONField(name = "9")
	public void setAllResMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.allResMap = _v_;
	}

	@JSONField(name = "10")
	public void setExtraResMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.extraResMap = _v_;
	}

	@JSONField(name = "11")
	public void setExtraAllResMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.extraAllResMap = _v_;
	}

	@JSONField(name = "12")
	public void setMaxEnergy(int _v_){
		this.maxEnergy = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Worker) {
			Worker _o_ = (Worker)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.cfgId != _o_.cfgId) return false;
			if (this.bornTime != _o_.bornTime) return false;
			if (this.energy != _o_.energy) return false;
			if (this.buildResId != _o_.buildResId) return false;
			if (!this.resMap.equals(_o_.resMap)) return false;
			if (this.lastOfflineCalc != _o_.lastOfflineCalc) return false;
			if (this.collectNum != _o_.collectNum) return false;
			if (!this.allResMap.equals(_o_.allResMap)) return false;
			if (!this.extraResMap.equals(_o_.extraResMap)) return false;
			if (!this.extraAllResMap.equals(_o_.extraAllResMap)) return false;
			if (this.maxEnergy != _o_.maxEnergy) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(this.cfgId);
		_h_ += _h_ * 31 + (int)(bornTime ^ (bornTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.energy);
		_h_ += _h_ * 31 + (int)(this.buildResId);
		_h_ += _h_ * 31 + this.resMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastOfflineCalc ^ (lastOfflineCalc >>> 32));
		_h_ += _h_ * 31 + (int)(collectNum ^ (collectNum >>> 32));
		_h_ += _h_ * 31 + this.allResMap.hashCode();
		_h_ += _h_ * 31 + this.extraResMap.hashCode();
		_h_ += _h_ * 31 + this.extraAllResMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.maxEnergy);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(cfgId).append(",");
		_sb_.append(bornTime).append(",");
		_sb_.append(energy).append(",");
		_sb_.append(buildResId).append(",");
		_sb_.append(resMap).append(",");
		_sb_.append(lastOfflineCalc).append(",");
		_sb_.append(collectNum).append(",");
		_sb_.append(allResMap).append(",");
		_sb_.append(extraResMap).append(",");
		_sb_.append(extraAllResMap).append(",");
		_sb_.append(maxEnergy).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
