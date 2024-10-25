package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ConditionDonateTech extends warrior.mdb.XBean {
    private int id; 
    private java.util.concurrent.CopyOnWriteArrayList<Integer> conditionCV;  // 条件进度
    private boolean canUpdate;  // 条件进度可更新

    ConditionDonateTech(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.conditionCV = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public ConditionDonateTech() {
		this((warrior.mdb.XBean)null);
	}

	public ConditionDonateTech(ConditionDonateTech _o_) {
		this(_o_, null);
	}

	ConditionDonateTech(ConditionDonateTech _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.conditionCV = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.conditionCV.forEach(_v_ -> this.conditionCV.add(_v_));
		this.canUpdate = _o_.canUpdate;
	}

	public void copyFrom(ConditionDonateTech _o_) {
        this.id = _o_.id;
        this.conditionCV = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.conditionCV.forEach(_v_ -> this.conditionCV.add(_v_));
        this.canUpdate = _o_.canUpdate;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getConditionCV(){
		return this.conditionCV;
	}

	@JSONField(name = "4")
	public boolean getCanUpdate(){
		return this.canUpdate;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "3")
	public void setConditionCV(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.conditionCV = _v_;
	}

	@JSONField(name = "4")
	public void setCanUpdate(boolean _v_){
		this.canUpdate = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ConditionDonateTech) {
			ConditionDonateTech _o_ = (ConditionDonateTech)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.conditionCV.equals(_o_.conditionCV)) return false;
			if (this.canUpdate != _o_.canUpdate) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + this.conditionCV.hashCode();
		_h_ += _h_ * 31 + (this.canUpdate ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(conditionCV).append(",");
		_sb_.append(canUpdate).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
