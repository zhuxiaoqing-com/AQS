package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensWildInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, WildInfo> wildMap;  // k:野外区域id v:野外区域数据

    GensWildInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.wildMap = new java.util.concurrent.ConcurrentHashMap<Integer, WildInfo>();
	}

	public GensWildInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensWildInfo(GensWildInfo _o_) {
		this(_o_, null);
	}

	GensWildInfo(GensWildInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.wildMap = new java.util.concurrent.ConcurrentHashMap<Integer, WildInfo>();
		_o_.wildMap.forEach((_k_, _v_) -> this.wildMap.put(_k_, new WildInfo(_v_)));
	}

	public void copyFrom(GensWildInfo _o_) {
        this.wildMap = new java.util.concurrent.ConcurrentHashMap<Integer, WildInfo>();
		_o_.wildMap.forEach((_k_, _v_) -> this.wildMap.put(_k_, new WildInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, WildInfo> getWildMap(){
		return this.wildMap;
	}

	@JSONField(name = "1")
	public void setWildMap(java.util.concurrent.ConcurrentHashMap<Integer, WildInfo> _v_){
		this.wildMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensWildInfo) {
			GensWildInfo _o_ = (GensWildInfo)_o1_;
			if (!this.wildMap.equals(_o_.wildMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.wildMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(wildMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
