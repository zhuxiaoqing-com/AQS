package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class IntList extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<Integer> intList; 

    IntList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.intList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public IntList() {
		this((warrior.mdb.XBean)null);
	}

	public IntList(IntList _o_) {
		this(_o_, null);
	}

	IntList(IntList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.intList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.intList.forEach(_v_ -> this.intList.add(_v_));
	}

	public void copyFrom(IntList _o_) {
        this.intList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.intList.forEach(_v_ -> this.intList.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getIntList(){
		return this.intList;
	}

	@JSONField(name = "1")
	public void setIntList(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.intList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof IntList) {
			IntList _o_ = (IntList)_o1_;
			if (!this.intList.equals(_o_.intList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.intList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(intList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
