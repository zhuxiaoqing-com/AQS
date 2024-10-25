package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBWildInfo extends warrior.mdb.XBean {
    private int areaId;  // 区域id
    private java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag> wildFlagMap;  // 野外夺旗数据

    LBWildInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.wildFlagMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag>();
	}

	public LBWildInfo() {
		this((warrior.mdb.XBean)null);
	}

	public LBWildInfo(LBWildInfo _o_) {
		this(_o_, null);
	}

	LBWildInfo(LBWildInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaId = _o_.areaId;
		this.wildFlagMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag>();
		_o_.wildFlagMap.forEach((_k_, _v_) -> this.wildFlagMap.put(_k_, new LBWildFlag(_v_)));
	}

	public void copyFrom(LBWildInfo _o_) {
        this.areaId = _o_.areaId;
        this.wildFlagMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag>();
		_o_.wildFlagMap.forEach((_k_, _v_) -> this.wildFlagMap.put(_k_, new LBWildFlag(_v_)));
	}


	@JSONField(name = "1")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag> getWildFlagMap(){
		return this.wildFlagMap;
	}

	@JSONField(name = "1")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "2")
	public void setWildFlagMap(java.util.concurrent.ConcurrentHashMap<Integer, LBWildFlag> _v_){
		this.wildFlagMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBWildInfo) {
			LBWildInfo _o_ = (LBWildInfo)_o1_;
			if (this.areaId != _o_.areaId) return false;
			if (!this.wildFlagMap.equals(_o_.wildFlagMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + this.wildFlagMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaId).append(",");
		_sb_.append(wildFlagMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
