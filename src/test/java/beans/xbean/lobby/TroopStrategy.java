package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class TroopStrategy extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, ConditionDonateTech> strategies; 
    private java.util.concurrent.CopyOnWriteArraySet<Integer> activeSet; 

    TroopStrategy(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.strategies = new java.util.concurrent.ConcurrentHashMap<Integer, ConditionDonateTech>();
		this.activeSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public TroopStrategy() {
		this((warrior.mdb.XBean)null);
	}

	public TroopStrategy(TroopStrategy _o_) {
		this(_o_, null);
	}

	TroopStrategy(TroopStrategy _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.strategies = new java.util.concurrent.ConcurrentHashMap<Integer, ConditionDonateTech>();
		_o_.strategies.forEach((_k_, _v_) -> this.strategies.put(_k_, new ConditionDonateTech(_v_)));
		this.activeSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.activeSet.forEach(_v_ -> this.activeSet.add(_v_));
	}

	public void copyFrom(TroopStrategy _o_) {
        this.strategies = new java.util.concurrent.ConcurrentHashMap<Integer, ConditionDonateTech>();
		_o_.strategies.forEach((_k_, _v_) -> this.strategies.put(_k_, new ConditionDonateTech(_v_)));
        this.activeSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.activeSet.forEach(_v_ -> this.activeSet.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, ConditionDonateTech> getStrategies(){
		return this.strategies;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getActiveSet(){
		return this.activeSet;
	}

	@JSONField(name = "1")
	public void setStrategies(java.util.concurrent.ConcurrentHashMap<Integer, ConditionDonateTech> _v_){
		this.strategies = _v_;
	}

	@JSONField(name = "2")
	public void setActiveSet(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.activeSet = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TroopStrategy) {
			TroopStrategy _o_ = (TroopStrategy)_o1_;
			if (!this.strategies.equals(_o_.strategies)) return false;
			if (!this.activeSet.equals(_o_.activeSet)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.strategies.hashCode();
		_h_ += _h_ * 31 + this.activeSet.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(strategies).append(",");
		_sb_.append(activeSet).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
