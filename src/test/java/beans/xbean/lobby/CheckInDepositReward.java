package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CheckInDepositReward extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> reward; 
    private long deleteTime; 
    private long id; 

    CheckInDepositReward(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.reward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public CheckInDepositReward() {
		this((warrior.mdb.XBean)null);
	}

	public CheckInDepositReward(CheckInDepositReward _o_) {
		this(_o_, null);
	}

	CheckInDepositReward(CheckInDepositReward _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.reward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.reward.forEach((_k_, _v_) -> this.reward.put(_k_, _v_));
		this.deleteTime = _o_.deleteTime;
		this.id = _o_.id;
	}

	public void copyFrom(CheckInDepositReward _o_) {
        this.reward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.reward.forEach((_k_, _v_) -> this.reward.put(_k_, _v_));
        this.deleteTime = _o_.deleteTime;
        this.id = _o_.id;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getReward(){
		return this.reward;
	}

	@JSONField(name = "2")
	public long getDeleteTime(){
		return this.deleteTime;
	}

	@JSONField(name = "3")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "1")
	public void setReward(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.reward = _v_;
	}

	@JSONField(name = "2")
	public void setDeleteTime(long _v_){
		this.deleteTime = _v_;
	}

	@JSONField(name = "3")
	public void setId(long _v_){
		this.id = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CheckInDepositReward) {
			CheckInDepositReward _o_ = (CheckInDepositReward)_o1_;
			if (!this.reward.equals(_o_.reward)) return false;
			if (this.deleteTime != _o_.deleteTime) return false;
			if (this.id != _o_.id) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.reward.hashCode();
		_h_ += _h_ * 31 + (int)(deleteTime ^ (deleteTime >>> 32));
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(reward).append(",");
		_sb_.append(deleteTime).append(",");
		_sb_.append(id).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
