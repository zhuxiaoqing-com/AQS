package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ActCityFight extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, LBCityInfo> cityInfo;  // k:本服区域Id v:区域数据

    ActCityFight(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.cityInfo = new java.util.concurrent.ConcurrentHashMap<Integer, LBCityInfo>();
	}

	public ActCityFight() {
		this((warrior.mdb.XBean)null);
	}

	public ActCityFight(ActCityFight _o_) {
		this(_o_, null);
	}

	ActCityFight(ActCityFight _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.cityInfo = new java.util.concurrent.ConcurrentHashMap<Integer, LBCityInfo>();
		_o_.cityInfo.forEach((_k_, _v_) -> this.cityInfo.put(_k_, new LBCityInfo(_v_)));
	}

	public void copyFrom(ActCityFight _o_) {
        this.cityInfo = new java.util.concurrent.ConcurrentHashMap<Integer, LBCityInfo>();
		_o_.cityInfo.forEach((_k_, _v_) -> this.cityInfo.put(_k_, new LBCityInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, LBCityInfo> getCityInfo(){
		return this.cityInfo;
	}

	@JSONField(name = "1")
	public void setCityInfo(java.util.concurrent.ConcurrentHashMap<Integer, LBCityInfo> _v_){
		this.cityInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ActCityFight) {
			ActCityFight _o_ = (ActCityFight)_o1_;
			if (!this.cityInfo.equals(_o_.cityInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.cityInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(cityInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
