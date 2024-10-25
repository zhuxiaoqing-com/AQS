package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PassiveTech extends warrior.mdb.XBean {
    private int id; 
    private int level; 
    private java.util.concurrent.CopyOnWriteArrayList<Integer> conditionCurV;  // 某一类需要累计条件值的判断类型

    PassiveTech(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.conditionCurV = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public PassiveTech() {
		this((warrior.mdb.XBean)null);
	}

	public PassiveTech(PassiveTech _o_) {
		this(_o_, null);
	}

	PassiveTech(PassiveTech _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.level = _o_.level;
		this.conditionCurV = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.conditionCurV.forEach(_v_ -> this.conditionCurV.add(_v_));
	}

	public void copyFrom(PassiveTech _o_) {
        this.id = _o_.id;
        this.level = _o_.level;
        this.conditionCurV = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.conditionCurV.forEach(_v_ -> this.conditionCurV.add(_v_));
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getConditionCurV(){
		return this.conditionCurV;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "3")
	public void setConditionCurV(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.conditionCurV = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PassiveTech) {
			PassiveTech _o_ = (PassiveTech)_o1_;
			if (this.id != _o_.id) return false;
			if (this.level != _o_.level) return false;
			if (!this.conditionCurV.equals(_o_.conditionCurV)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + this.conditionCurV.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(level).append(",");
		_sb_.append(conditionCurV).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
