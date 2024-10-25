package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBCityFlagSave extends warrior.mdb.XBean {
    private int armament;  // 战备点
    private java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo> lvMap;  // 建筑id->建筑等级

    LBCityFlagSave(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.lvMap = new java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo>();
	}

	public LBCityFlagSave() {
		this((warrior.mdb.XBean)null);
	}

	public LBCityFlagSave(LBCityFlagSave _o_) {
		this(_o_, null);
	}

	LBCityFlagSave(LBCityFlagSave _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.armament = _o_.armament;
		this.lvMap = new java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo>();
		_o_.lvMap.forEach((_k_, _v_) -> this.lvMap.put(_k_, new FlagLvInfo(_v_)));
	}

	public void copyFrom(LBCityFlagSave _o_) {
        this.armament = _o_.armament;
        this.lvMap = new java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo>();
		_o_.lvMap.forEach((_k_, _v_) -> this.lvMap.put(_k_, new FlagLvInfo(_v_)));
	}


	@JSONField(name = "1")
	public int getArmament(){
		return this.armament;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo> getLvMap(){
		return this.lvMap;
	}

	@JSONField(name = "1")
	public void setArmament(int _v_){
		this.armament = _v_;
	}

	@JSONField(name = "2")
	public void setLvMap(java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo> _v_){
		this.lvMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBCityFlagSave) {
			LBCityFlagSave _o_ = (LBCityFlagSave)_o1_;
			if (this.armament != _o_.armament) return false;
			if (!this.lvMap.equals(_o_.lvMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.armament);
		_h_ += _h_ * 31 + this.lvMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(armament).append(",");
		_sb_.append(lvMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
