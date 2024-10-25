package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CityFightEnrollMap extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, LBGensBaseInfo> enrollMap;  // k:公会唯一id v:公会基础数据

    CityFightEnrollMap(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.enrollMap = new java.util.concurrent.ConcurrentHashMap<Long, LBGensBaseInfo>();
	}

	public CityFightEnrollMap() {
		this((warrior.mdb.XBean)null);
	}

	public CityFightEnrollMap(CityFightEnrollMap _o_) {
		this(_o_, null);
	}

	CityFightEnrollMap(CityFightEnrollMap _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.enrollMap = new java.util.concurrent.ConcurrentHashMap<Long, LBGensBaseInfo>();
		_o_.enrollMap.forEach((_k_, _v_) -> this.enrollMap.put(_k_, new LBGensBaseInfo(_v_)));
	}

	public void copyFrom(CityFightEnrollMap _o_) {
        this.enrollMap = new java.util.concurrent.ConcurrentHashMap<Long, LBGensBaseInfo>();
		_o_.enrollMap.forEach((_k_, _v_) -> this.enrollMap.put(_k_, new LBGensBaseInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, LBGensBaseInfo> getEnrollMap(){
		return this.enrollMap;
	}

	@JSONField(name = "1")
	public void setEnrollMap(java.util.concurrent.ConcurrentHashMap<Long, LBGensBaseInfo> _v_){
		this.enrollMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CityFightEnrollMap) {
			CityFightEnrollMap _o_ = (CityFightEnrollMap)_o1_;
			if (!this.enrollMap.equals(_o_.enrollMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.enrollMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(enrollMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
