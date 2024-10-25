package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class QuestCondition extends warrior.mdb.XBean {
    private int type; 
    private int count; 
    private java.util.concurrent.CopyOnWriteArrayList<Integer> conditionId; 
    private long time;  // 更新条件时的时间

    QuestCondition(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.conditionId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public QuestCondition() {
		this((warrior.mdb.XBean)null);
	}

	public QuestCondition(QuestCondition _o_) {
		this(_o_, null);
	}

	QuestCondition(QuestCondition _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.type = _o_.type;
		this.count = _o_.count;
		this.conditionId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.conditionId.forEach(_v_ -> this.conditionId.add(_v_));
		this.time = _o_.time;
	}

	public void copyFrom(QuestCondition _o_) {
        this.type = _o_.type;
        this.count = _o_.count;
        this.conditionId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.conditionId.forEach(_v_ -> this.conditionId.add(_v_));
        this.time = _o_.time;
	}


	@JSONField(name = "1")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "2")
	public int getCount(){
		return this.count;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getConditionId(){
		return this.conditionId;
	}

	@JSONField(name = "4")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "1")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "2")
	public void setCount(int _v_){
		this.count = _v_;
	}

	@JSONField(name = "3")
	public void setConditionId(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.conditionId = _v_;
	}

	@JSONField(name = "4")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof QuestCondition) {
			QuestCondition _o_ = (QuestCondition)_o1_;
			if (this.type != _o_.type) return false;
			if (this.count != _o_.count) return false;
			if (!this.conditionId.equals(_o_.conditionId)) return false;
			if (this.time != _o_.time) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(this.count);
		_h_ += _h_ * 31 + this.conditionId.hashCode();
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(type).append(",");
		_sb_.append(count).append(",");
		_sb_.append(conditionId).append(",");
		_sb_.append(time).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
