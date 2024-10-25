package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensRecordInfoList extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<GensRecordInfo> recordList; 

    GensRecordInfoList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.recordList = new java.util.concurrent.CopyOnWriteArrayList<GensRecordInfo>();
	}

	public GensRecordInfoList() {
		this((warrior.mdb.XBean)null);
	}

	public GensRecordInfoList(GensRecordInfoList _o_) {
		this(_o_, null);
	}

	GensRecordInfoList(GensRecordInfoList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.recordList = new java.util.concurrent.CopyOnWriteArrayList<GensRecordInfo>();
		_o_.recordList.forEach(_v_ -> this.recordList.add(new GensRecordInfo(_v_)));
	}

	public void copyFrom(GensRecordInfoList _o_) {
        this.recordList = new java.util.concurrent.CopyOnWriteArrayList<GensRecordInfo>();
		_o_.recordList.forEach(_v_ -> this.recordList.add(new GensRecordInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<GensRecordInfo> getRecordList(){
		return this.recordList;
	}

	@JSONField(name = "1")
	public void setRecordList(java.util.concurrent.CopyOnWriteArrayList<GensRecordInfo> _v_){
		this.recordList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensRecordInfoList) {
			GensRecordInfoList _o_ = (GensRecordInfoList)_o1_;
			if (!this.recordList.equals(_o_.recordList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.recordList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(recordList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
