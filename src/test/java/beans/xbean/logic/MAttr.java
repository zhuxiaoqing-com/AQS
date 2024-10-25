package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class MAttr extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Float> initAttrMap;  // 这里的属性都是属于初始属性 (属性id个位数为0的属性)
    private java.util.concurrent.ConcurrentHashMap<Integer, Float> effectMap;  // 这里的属性都是属于加成属性 (属性id个位数为1~2的属性)

    MAttr(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.initAttrMap = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		this.effectMap = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
	}

	public MAttr() {
		this((warrior.mdb.XBean)null);
	}

	public MAttr(MAttr _o_) {
		this(_o_, null);
	}

	MAttr(MAttr _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.initAttrMap = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.initAttrMap.forEach((_k_, _v_) -> this.initAttrMap.put(_k_, _v_));
		this.effectMap = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.effectMap.forEach((_k_, _v_) -> this.effectMap.put(_k_, _v_));
	}

	public void copyFrom(MAttr _o_) {
        this.initAttrMap = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.initAttrMap.forEach((_k_, _v_) -> this.initAttrMap.put(_k_, _v_));
        this.effectMap = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.effectMap.forEach((_k_, _v_) -> this.effectMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Float> getInitAttrMap(){
		return this.initAttrMap;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Float> getEffectMap(){
		return this.effectMap;
	}

	@JSONField(name = "1")
	public void setInitAttrMap(java.util.concurrent.ConcurrentHashMap<Integer, Float> _v_){
		this.initAttrMap = _v_;
	}

	@JSONField(name = "2")
	public void setEffectMap(java.util.concurrent.ConcurrentHashMap<Integer, Float> _v_){
		this.effectMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MAttr) {
			MAttr _o_ = (MAttr)_o1_;
			if (!this.initAttrMap.equals(_o_.initAttrMap)) return false;
			if (!this.effectMap.equals(_o_.effectMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.initAttrMap.hashCode();
		_h_ += _h_ * 31 + this.effectMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(initAttrMap).append(",");
		_sb_.append(effectMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
