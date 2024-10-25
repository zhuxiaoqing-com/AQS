package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class SceneCommanderInfo extends warrior.mdb.XBean {
    private int areaId;  // 野外区域id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> supplyMap;  // 补给站数量map

    SceneCommanderInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.supplyMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public SceneCommanderInfo() {
		this((warrior.mdb.XBean)null);
	}

	public SceneCommanderInfo(SceneCommanderInfo _o_) {
		this(_o_, null);
	}

	SceneCommanderInfo(SceneCommanderInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaId = _o_.areaId;
		this.supplyMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.supplyMap.forEach((_k_, _v_) -> this.supplyMap.put(_k_, _v_));
	}

	public void copyFrom(SceneCommanderInfo _o_) {
        this.areaId = _o_.areaId;
        this.supplyMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.supplyMap.forEach((_k_, _v_) -> this.supplyMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getSupplyMap(){
		return this.supplyMap;
	}

	@JSONField(name = "1")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "2")
	public void setSupplyMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.supplyMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SceneCommanderInfo) {
			SceneCommanderInfo _o_ = (SceneCommanderInfo)_o1_;
			if (this.areaId != _o_.areaId) return false;
			if (!this.supplyMap.equals(_o_.supplyMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + this.supplyMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaId).append(",");
		_sb_.append(supplyMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
