package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AreaDeathInfo extends warrior.mdb.XBean {
    private int areaId;  // 区域id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> totalDeath;  // 阵营id->死亡数量
    private java.util.concurrent.ConcurrentHashMap<Integer, IntList> minute10Death;  // 阵营id->死亡数量
    private java.util.concurrent.CopyOnWriteArraySet<Integer> defenseSet;  // 当前场景工事类型
    private long lastSyncMill;  // 上一次同步时间

    AreaDeathInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.totalDeath = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.minute10Death = new java.util.concurrent.ConcurrentHashMap<Integer, IntList>();
		this.defenseSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public AreaDeathInfo() {
		this((warrior.mdb.XBean)null);
	}

	public AreaDeathInfo(AreaDeathInfo _o_) {
		this(_o_, null);
	}

	AreaDeathInfo(AreaDeathInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaId = _o_.areaId;
		this.totalDeath = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.totalDeath.forEach((_k_, _v_) -> this.totalDeath.put(_k_, _v_));
		this.minute10Death = new java.util.concurrent.ConcurrentHashMap<Integer, IntList>();
		_o_.minute10Death.forEach((_k_, _v_) -> this.minute10Death.put(_k_, new IntList(_v_)));
		this.defenseSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.defenseSet.forEach(_v_ -> this.defenseSet.add(_v_));
		this.lastSyncMill = _o_.lastSyncMill;
	}

	public void copyFrom(AreaDeathInfo _o_) {
        this.areaId = _o_.areaId;
        this.totalDeath = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.totalDeath.forEach((_k_, _v_) -> this.totalDeath.put(_k_, _v_));
        this.minute10Death = new java.util.concurrent.ConcurrentHashMap<Integer, IntList>();
		_o_.minute10Death.forEach((_k_, _v_) -> this.minute10Death.put(_k_, new IntList(_v_)));
        this.defenseSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.defenseSet.forEach(_v_ -> this.defenseSet.add(_v_));
        this.lastSyncMill = _o_.lastSyncMill;
	}


	@JSONField(name = "1")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTotalDeath(){
		return this.totalDeath;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, IntList> getMinute10Death(){
		return this.minute10Death;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getDefenseSet(){
		return this.defenseSet;
	}

	@JSONField(name = "5")
	public long getLastSyncMill(){
		return this.lastSyncMill;
	}

	@JSONField(name = "1")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "2")
	public void setTotalDeath(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.totalDeath = _v_;
	}

	@JSONField(name = "3")
	public void setMinute10Death(java.util.concurrent.ConcurrentHashMap<Integer, IntList> _v_){
		this.minute10Death = _v_;
	}

	@JSONField(name = "4")
	public void setDefenseSet(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.defenseSet = _v_;
	}

	@JSONField(name = "5")
	public void setLastSyncMill(long _v_){
		this.lastSyncMill = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AreaDeathInfo) {
			AreaDeathInfo _o_ = (AreaDeathInfo)_o1_;
			if (this.areaId != _o_.areaId) return false;
			if (!this.totalDeath.equals(_o_.totalDeath)) return false;
			if (!this.minute10Death.equals(_o_.minute10Death)) return false;
			if (!this.defenseSet.equals(_o_.defenseSet)) return false;
			if (this.lastSyncMill != _o_.lastSyncMill) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + this.totalDeath.hashCode();
		_h_ += _h_ * 31 + this.minute10Death.hashCode();
		_h_ += _h_ * 31 + this.defenseSet.hashCode();
		_h_ += _h_ * 31 + (int)(lastSyncMill ^ (lastSyncMill >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaId).append(",");
		_sb_.append(totalDeath).append(",");
		_sb_.append(minute10Death).append(",");
		_sb_.append(defenseSet).append(",");
		_sb_.append(lastSyncMill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
