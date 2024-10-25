package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ResBaseExploreQueue extends warrior.mdb.XBean {
    private int type;  // 类型 0:free，1：付费
    private long startTime;  // 开始时间
    private int extId;  // 增幅id
    private long finishTime;  // 结束时间

    ResBaseExploreQueue(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public ResBaseExploreQueue() {
		this((warrior.mdb.XBean)null);
	}

	public ResBaseExploreQueue(ResBaseExploreQueue _o_) {
		this(_o_, null);
	}

	ResBaseExploreQueue(ResBaseExploreQueue _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.type = _o_.type;
		this.startTime = _o_.startTime;
		this.extId = _o_.extId;
		this.finishTime = _o_.finishTime;
	}

	public void copyFrom(ResBaseExploreQueue _o_) {
        this.type = _o_.type;
        this.startTime = _o_.startTime;
        this.extId = _o_.extId;
        this.finishTime = _o_.finishTime;
	}


	@JSONField(name = "1")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "2")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "3")
	public int getExtId(){
		return this.extId;
	}

	@JSONField(name = "4")
	public long getFinishTime(){
		return this.finishTime;
	}

	@JSONField(name = "1")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "2")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "3")
	public void setExtId(int _v_){
		this.extId = _v_;
	}

	@JSONField(name = "4")
	public void setFinishTime(long _v_){
		this.finishTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ResBaseExploreQueue) {
			ResBaseExploreQueue _o_ = (ResBaseExploreQueue)_o1_;
			if (this.type != _o_.type) return false;
			if (this.startTime != _o_.startTime) return false;
			if (this.extId != _o_.extId) return false;
			if (this.finishTime != _o_.finishTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.extId);
		_h_ += _h_ * 31 + (int)(finishTime ^ (finishTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(type).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(extId).append(",");
		_sb_.append(finishTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
