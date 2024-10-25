package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class OfflineQuest extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<QuestCondition> condition; 

    OfflineQuest(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.condition = new java.util.concurrent.CopyOnWriteArrayList<QuestCondition>();
	}

	public OfflineQuest() {
		this((warrior.mdb.XBean)null);
	}

	public OfflineQuest(OfflineQuest _o_) {
		this(_o_, null);
	}

	OfflineQuest(OfflineQuest _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.condition = new java.util.concurrent.CopyOnWriteArrayList<QuestCondition>();
		_o_.condition.forEach(_v_ -> this.condition.add(new QuestCondition(_v_)));
	}

	public void copyFrom(OfflineQuest _o_) {
        this.condition = new java.util.concurrent.CopyOnWriteArrayList<QuestCondition>();
		_o_.condition.forEach(_v_ -> this.condition.add(new QuestCondition(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<QuestCondition> getCondition(){
		return this.condition;
	}

	@JSONField(name = "1")
	public void setCondition(java.util.concurrent.CopyOnWriteArrayList<QuestCondition> _v_){
		this.condition = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof OfflineQuest) {
			OfflineQuest _o_ = (OfflineQuest)_o1_;
			if (!this.condition.equals(_o_.condition)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.condition.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(condition).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
