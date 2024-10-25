package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AreaDeclareGens extends warrior.mdb.XBean {
    private int areaId; 
    private java.util.concurrent.ConcurrentHashMap<Long, WildDeclareGens> declareGensMap;  // 公会唯一id

    AreaDeclareGens(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.declareGensMap = new java.util.concurrent.ConcurrentHashMap<Long, WildDeclareGens>();
	}

	public AreaDeclareGens() {
		this((warrior.mdb.XBean)null);
	}

	public AreaDeclareGens(AreaDeclareGens _o_) {
		this(_o_, null);
	}

	AreaDeclareGens(AreaDeclareGens _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaId = _o_.areaId;
		this.declareGensMap = new java.util.concurrent.ConcurrentHashMap<Long, WildDeclareGens>();
		_o_.declareGensMap.forEach((_k_, _v_) -> this.declareGensMap.put(_k_, new WildDeclareGens(_v_)));
	}

	public void copyFrom(AreaDeclareGens _o_) {
        this.areaId = _o_.areaId;
        this.declareGensMap = new java.util.concurrent.ConcurrentHashMap<Long, WildDeclareGens>();
		_o_.declareGensMap.forEach((_k_, _v_) -> this.declareGensMap.put(_k_, new WildDeclareGens(_v_)));
	}


	@JSONField(name = "1")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, WildDeclareGens> getDeclareGensMap(){
		return this.declareGensMap;
	}

	@JSONField(name = "1")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "2")
	public void setDeclareGensMap(java.util.concurrent.ConcurrentHashMap<Long, WildDeclareGens> _v_){
		this.declareGensMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AreaDeclareGens) {
			AreaDeclareGens _o_ = (AreaDeclareGens)_o1_;
			if (this.areaId != _o_.areaId) return false;
			if (!this.declareGensMap.equals(_o_.declareGensMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + this.declareGensMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaId).append(",");
		_sb_.append(declareGensMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
