package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ActCityFightEnroll extends warrior.mdb.XBean {
    private xbean.lobby.LBActLogicInfo actInfo;  // 活动数据
    private java.util.concurrent.ConcurrentHashMap<Integer, CityFightEnrollMap> enrollMap;  // k:本服区域Id v:报名的公会数据

    ActCityFightEnroll(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actInfo = new LBActLogicInfo(this);
		this.enrollMap = new java.util.concurrent.ConcurrentHashMap<Integer, CityFightEnrollMap>();
	}

	public ActCityFightEnroll() {
		this((warrior.mdb.XBean)null);
	}

	public ActCityFightEnroll(ActCityFightEnroll _o_) {
		this(_o_, null);
	}

	ActCityFightEnroll(ActCityFightEnroll _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actInfo = new LBActLogicInfo(_o_.actInfo, this);
		this.enrollMap = new java.util.concurrent.ConcurrentHashMap<Integer, CityFightEnrollMap>();
		_o_.enrollMap.forEach((_k_, _v_) -> this.enrollMap.put(_k_, new CityFightEnrollMap(_v_)));
	}

	public void copyFrom(ActCityFightEnroll _o_) {
        this.actInfo = new LBActLogicInfo(_o_.actInfo, this);
        this.enrollMap = new java.util.concurrent.ConcurrentHashMap<Integer, CityFightEnrollMap>();
		_o_.enrollMap.forEach((_k_, _v_) -> this.enrollMap.put(_k_, new CityFightEnrollMap(_v_)));
	}


	@JSONField(name = "1")
	public xbean.lobby.LBActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, CityFightEnrollMap> getEnrollMap(){
		return this.enrollMap;
	}

	@JSONField(name = "1")
	public void setActInfo(xbean.lobby.LBActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@JSONField(name = "2")
	public void setEnrollMap(java.util.concurrent.ConcurrentHashMap<Integer, CityFightEnrollMap> _v_){
		this.enrollMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ActCityFightEnroll) {
			ActCityFightEnroll _o_ = (ActCityFightEnroll)_o1_;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			if (!this.enrollMap.equals(_o_.enrollMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		_h_ += _h_ * 31 + this.enrollMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actInfo).append(",");
		_sb_.append(enrollMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
