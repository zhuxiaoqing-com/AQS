package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensWarMapInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, GensWarLandInfo> landMap; 

    GensWarMapInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.landMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensWarLandInfo>();
	}

	public GensWarMapInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensWarMapInfo(GensWarMapInfo _o_) {
		this(_o_, null);
	}

	GensWarMapInfo(GensWarMapInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.landMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensWarLandInfo>();
		_o_.landMap.forEach((_k_, _v_) -> this.landMap.put(_k_, new GensWarLandInfo(_v_)));
	}

	public void copyFrom(GensWarMapInfo _o_) {
        this.landMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensWarLandInfo>();
		_o_.landMap.forEach((_k_, _v_) -> this.landMap.put(_k_, new GensWarLandInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, GensWarLandInfo> getLandMap(){
		return this.landMap;
	}

	@JSONField(name = "1")
	public void setLandMap(java.util.concurrent.ConcurrentHashMap<Integer, GensWarLandInfo> _v_){
		this.landMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensWarMapInfo) {
			GensWarMapInfo _o_ = (GensWarMapInfo)_o1_;
			if (!this.landMap.equals(_o_.landMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.landMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(landMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
