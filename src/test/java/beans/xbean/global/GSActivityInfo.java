package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class GSActivityInfo extends warrior.mdb.XBean {
    private int id;  // 活动的id
    private int baseId;  // 活动的baseid
    private long startTime;  // 活动预计的开始的时间(填在表里的时间)
    private long endTime;  // 活动预计的结束的时间(填在表里的时间)

    GSActivityInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public GSActivityInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GSActivityInfo(GSActivityInfo _o_) {
		this(_o_, null);
	}

	GSActivityInfo(GSActivityInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.baseId = _o_.baseId;
		this.startTime = _o_.startTime;
		this.endTime = _o_.endTime;
	}

	public void copyFrom(GSActivityInfo _o_) {
        this.id = _o_.id;
        this.baseId = _o_.baseId;
        this.startTime = _o_.startTime;
        this.endTime = _o_.endTime;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getBaseId(){
		return this.baseId;
	}

	@JSONField(name = "3")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "4")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setBaseId(int _v_){
		this.baseId = _v_;
	}

	@JSONField(name = "3")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "4")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GSActivityInfo) {
			GSActivityInfo _o_ = (GSActivityInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (this.baseId != _o_.baseId) return false;
			if (this.startTime != _o_.startTime) return false;
			if (this.endTime != _o_.endTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.baseId);
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(baseId).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
