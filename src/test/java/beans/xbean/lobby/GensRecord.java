package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensRecord extends warrior.mdb.XBean {
    private long gid;  // 公会id
    private java.util.concurrent.ConcurrentHashMap<Integer, GensRecordInfoList> recordMap;  // 公会日志类型->公会日志

    GensRecord(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.recordMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensRecordInfoList>();
	}

	public GensRecord() {
		this((warrior.mdb.XBean)null);
	}

	public GensRecord(GensRecord _o_) {
		this(_o_, null);
	}

	GensRecord(GensRecord _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gid = _o_.gid;
		this.recordMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensRecordInfoList>();
		_o_.recordMap.forEach((_k_, _v_) -> this.recordMap.put(_k_, new GensRecordInfoList(_v_)));
	}

	public void copyFrom(GensRecord _o_) {
        this.gid = _o_.gid;
        this.recordMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensRecordInfoList>();
		_o_.recordMap.forEach((_k_, _v_) -> this.recordMap.put(_k_, new GensRecordInfoList(_v_)));
	}


	@JSONField(name = "1")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, GensRecordInfoList> getRecordMap(){
		return this.recordMap;
	}

	@JSONField(name = "1")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "2")
	public void setRecordMap(java.util.concurrent.ConcurrentHashMap<Integer, GensRecordInfoList> _v_){
		this.recordMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensRecord) {
			GensRecord _o_ = (GensRecord)_o1_;
			if (this.gid != _o_.gid) return false;
			if (!this.recordMap.equals(_o_.recordMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + this.recordMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gid).append(",");
		_sb_.append(recordMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
