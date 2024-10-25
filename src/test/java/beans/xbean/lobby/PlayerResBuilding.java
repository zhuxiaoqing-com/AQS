package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerResBuilding extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> equipments;  // 装备列表
    private int level;  // 等级
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> resMap;  // 当前资源数量
    private long lastSummaryTime;  // 上次结算时间

    PlayerResBuilding(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.equipments = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.resMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerResBuilding() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerResBuilding(PlayerResBuilding _o_) {
		this(_o_, null);
	}

	PlayerResBuilding(PlayerResBuilding _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.equipments = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.equipments.forEach((_k_, _v_) -> this.equipments.put(_k_, _v_));
		this.level = _o_.level;
		this.resMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resMap.forEach((_k_, _v_) -> this.resMap.put(_k_, _v_));
		this.lastSummaryTime = _o_.lastSummaryTime;
	}

	public void copyFrom(PlayerResBuilding _o_) {
        this.equipments = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.equipments.forEach((_k_, _v_) -> this.equipments.put(_k_, _v_));
        this.level = _o_.level;
        this.resMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resMap.forEach((_k_, _v_) -> this.resMap.put(_k_, _v_));
        this.lastSummaryTime = _o_.lastSummaryTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getEquipments(){
		return this.equipments;
	}

	@JSONField(name = "2")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getResMap(){
		return this.resMap;
	}

	@JSONField(name = "4")
	public long getLastSummaryTime(){
		return this.lastSummaryTime;
	}

	@JSONField(name = "1")
	public void setEquipments(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.equipments = _v_;
	}

	@JSONField(name = "2")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "3")
	public void setResMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.resMap = _v_;
	}

	@JSONField(name = "4")
	public void setLastSummaryTime(long _v_){
		this.lastSummaryTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerResBuilding) {
			PlayerResBuilding _o_ = (PlayerResBuilding)_o1_;
			if (!this.equipments.equals(_o_.equipments)) return false;
			if (this.level != _o_.level) return false;
			if (!this.resMap.equals(_o_.resMap)) return false;
			if (this.lastSummaryTime != _o_.lastSummaryTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.equipments.hashCode();
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + this.resMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastSummaryTime ^ (lastSummaryTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(equipments).append(",");
		_sb_.append(level).append(",");
		_sb_.append(resMap).append(",");
		_sb_.append(lastSummaryTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
