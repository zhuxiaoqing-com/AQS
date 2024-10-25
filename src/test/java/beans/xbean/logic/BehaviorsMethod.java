package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class BehaviorsMethod extends warrior.mdb.XBean {
    private String methodName; 
    private java.util.concurrent.CopyOnWriteArrayList<String> mparams; 

    BehaviorsMethod(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.methodName = "";
		this.mparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
	}

	public BehaviorsMethod() {
		this((warrior.mdb.XBean)null);
	}

	public BehaviorsMethod(BehaviorsMethod _o_) {
		this(_o_, null);
	}

	BehaviorsMethod(BehaviorsMethod _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.methodName = _o_.methodName;
		this.mparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.mparams.forEach(_v_ -> this.mparams.add(_v_));
	}

	public void copyFrom(BehaviorsMethod _o_) {
        this.methodName = _o_.methodName;
        this.mparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.mparams.forEach(_v_ -> this.mparams.add(_v_));
	}


	@JSONField(name = "1")
	public String getMethodName(){
		return this.methodName;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<String> getMparams(){
		return this.mparams;
	}

	@JSONField(name = "1")
	public void setMethodName(String _v_){
		this.methodName = _v_;
	}

	@JSONField(name = "2")
	public void setMparams(java.util.concurrent.CopyOnWriteArrayList<String> _v_){
		this.mparams = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BehaviorsMethod) {
			BehaviorsMethod _o_ = (BehaviorsMethod)_o1_;
			if (!this.methodName.equals(_o_.methodName)) return false;
			if (!this.mparams.equals(_o_.mparams)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.methodName.hashCode();
		_h_ += _h_ * 31 + this.mparams.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append("T").append(methodName.length()).append(",");
		_sb_.append(mparams).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
