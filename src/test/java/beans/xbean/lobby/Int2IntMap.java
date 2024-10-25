package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class Int2IntMap extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> map; 

    Int2IntMap(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.map = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public Int2IntMap() {
		this((warrior.mdb.XBean)null);
	}

	public Int2IntMap(Int2IntMap _o_) {
		this(_o_, null);
	}

	Int2IntMap(Int2IntMap _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.map = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.map.forEach((_k_, _v_) -> this.map.put(_k_, _v_));
	}

	public void copyFrom(Int2IntMap _o_) {
        this.map = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.map.forEach((_k_, _v_) -> this.map.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getMap(){
		return this.map;
	}

	@JSONField(name = "1")
	public void setMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.map = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Int2IntMap) {
			Int2IntMap _o_ = (Int2IntMap)_o1_;
			if (!this.map.equals(_o_.map)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.map.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(map).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
