package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class ActionRecord extends warrior.mdb.XBean {
    private int actionType;  // 行为类型
    private java.util.concurrent.ConcurrentHashMap<ActionIdKey, Integer> dataMap;  // 行为数据

    ActionRecord(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<ActionIdKey, Integer>();
	}

	public ActionRecord() {
		this((warrior.mdb.XBean)null);
	}

	public ActionRecord(ActionRecord _o_) {
		this(_o_, null);
	}

	ActionRecord(ActionRecord _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actionType = _o_.actionType;
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<ActionIdKey, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
	}

	public void copyFrom(ActionRecord _o_) {
        this.actionType = _o_.actionType;
        this.dataMap = new java.util.concurrent.ConcurrentHashMap<ActionIdKey, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getActionType(){
		return this.actionType;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<ActionIdKey, Integer> getDataMap(){
		return this.dataMap;
	}

	@JSONField(name = "1")
	public void setActionType(int _v_){
		this.actionType = _v_;
	}

	@JSONField(name = "2")
	public void setDataMap(java.util.concurrent.ConcurrentHashMap<ActionIdKey, Integer> _v_){
		this.dataMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ActionRecord) {
			ActionRecord _o_ = (ActionRecord)_o1_;
			if (this.actionType != _o_.actionType) return false;
			if (!this.dataMap.equals(_o_.dataMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.actionType);
		_h_ += _h_ * 31 + this.dataMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actionType).append(",");
		_sb_.append(dataMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
