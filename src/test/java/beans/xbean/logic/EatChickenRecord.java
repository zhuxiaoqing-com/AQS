package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class EatChickenRecord extends warrior.mdb.XBean {
    private int dungeonId;  // 副本Id
    private long endMill;  // 结束时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> other;  // 其他参数

    EatChickenRecord(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.other = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
	}

	public EatChickenRecord() {
		this((warrior.mdb.XBean)null);
	}

	public EatChickenRecord(EatChickenRecord _o_) {
		this(_o_, null);
	}

	EatChickenRecord(EatChickenRecord _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.dungeonId = _o_.dungeonId;
		this.endMill = _o_.endMill;
		this.other = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.other.forEach((_k_, _v_) -> this.other.put(_k_, _v_));
	}

	public void copyFrom(EatChickenRecord _o_) {
        this.dungeonId = _o_.dungeonId;
        this.endMill = _o_.endMill;
        this.other = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.other.forEach((_k_, _v_) -> this.other.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "2")
	public long getEndMill(){
		return this.endMill;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getOther(){
		return this.other;
	}

	@JSONField(name = "1")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "2")
	public void setEndMill(long _v_){
		this.endMill = _v_;
	}

	@JSONField(name = "3")
	public void setOther(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.other = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EatChickenRecord) {
			EatChickenRecord _o_ = (EatChickenRecord)_o1_;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (this.endMill != _o_.endMill) return false;
			if (!this.other.equals(_o_.other)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + (int)(endMill ^ (endMill >>> 32));
		_h_ += _h_ * 31 + this.other.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(dungeonId).append(",");
		_sb_.append(endMill).append(",");
		_sb_.append(other).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
