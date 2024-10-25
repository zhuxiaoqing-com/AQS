package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GentesTech extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> techMap;  // id->level

    GentesTech(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.techMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GentesTech() {
		this((warrior.mdb.XBean)null);
	}

	public GentesTech(GentesTech _o_) {
		this(_o_, null);
	}

	GentesTech(GentesTech _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.techMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.techMap.forEach((_k_, _v_) -> this.techMap.put(_k_, _v_));
	}

	public void copyFrom(GentesTech _o_) {
        this.techMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.techMap.forEach((_k_, _v_) -> this.techMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTechMap(){
		return this.techMap;
	}

	@JSONField(name = "1")
	public void setTechMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.techMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GentesTech) {
			GentesTech _o_ = (GentesTech)_o1_;
			if (!this.techMap.equals(_o_.techMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.techMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(techMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
