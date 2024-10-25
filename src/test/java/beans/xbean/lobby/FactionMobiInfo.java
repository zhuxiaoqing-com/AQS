package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionMobiInfo extends warrior.mdb.XBean {
    private int techId;  // 提供的科技id
    private java.util.concurrent.ConcurrentHashMap<Integer, Float> effects;  // 属性
    private long endTime;  // 结束时间
    private long startTime;  // 开始时间

    FactionMobiInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.effects = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
	}

	public FactionMobiInfo() {
		this((warrior.mdb.XBean)null);
	}

	public FactionMobiInfo(FactionMobiInfo _o_) {
		this(_o_, null);
	}

	FactionMobiInfo(FactionMobiInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.techId = _o_.techId;
		this.effects = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.effects.forEach((_k_, _v_) -> this.effects.put(_k_, _v_));
		this.endTime = _o_.endTime;
		this.startTime = _o_.startTime;
	}

	public void copyFrom(FactionMobiInfo _o_) {
        this.techId = _o_.techId;
        this.effects = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.effects.forEach((_k_, _v_) -> this.effects.put(_k_, _v_));
        this.endTime = _o_.endTime;
        this.startTime = _o_.startTime;
	}


	@JSONField(name = "1")
	public int getTechId(){
		return this.techId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Float> getEffects(){
		return this.effects;
	}

	@JSONField(name = "3")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "4")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "1")
	public void setTechId(int _v_){
		this.techId = _v_;
	}

	@JSONField(name = "2")
	public void setEffects(java.util.concurrent.ConcurrentHashMap<Integer, Float> _v_){
		this.effects = _v_;
	}

	@JSONField(name = "3")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "4")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionMobiInfo) {
			FactionMobiInfo _o_ = (FactionMobiInfo)_o1_;
			if (this.techId != _o_.techId) return false;
			if (!this.effects.equals(_o_.effects)) return false;
			if (this.endTime != _o_.endTime) return false;
			if (this.startTime != _o_.startTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.techId);
		_h_ += _h_ * 31 + this.effects.hashCode();
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(techId).append(",");
		_sb_.append(effects).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
