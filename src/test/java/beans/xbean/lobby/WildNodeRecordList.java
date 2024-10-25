package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class WildNodeRecordList extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<WildNodeRecord> recordList;  // 节点日志列表
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> occupyMoreOtherSet;  // factionId->占领地块多于对方的时间

    WildNodeRecordList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.recordList = new java.util.concurrent.CopyOnWriteArrayList<WildNodeRecord>();
		this.occupyMoreOtherSet = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
	}

	public WildNodeRecordList() {
		this((warrior.mdb.XBean)null);
	}

	public WildNodeRecordList(WildNodeRecordList _o_) {
		this(_o_, null);
	}

	WildNodeRecordList(WildNodeRecordList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.recordList = new java.util.concurrent.CopyOnWriteArrayList<WildNodeRecord>();
		_o_.recordList.forEach(_v_ -> this.recordList.add(new WildNodeRecord(_v_)));
		this.occupyMoreOtherSet = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.occupyMoreOtherSet.forEach((_k_, _v_) -> this.occupyMoreOtherSet.put(_k_, _v_));
	}

	public void copyFrom(WildNodeRecordList _o_) {
        this.recordList = new java.util.concurrent.CopyOnWriteArrayList<WildNodeRecord>();
		_o_.recordList.forEach(_v_ -> this.recordList.add(new WildNodeRecord(_v_)));
        this.occupyMoreOtherSet = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.occupyMoreOtherSet.forEach((_k_, _v_) -> this.occupyMoreOtherSet.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<WildNodeRecord> getRecordList(){
		return this.recordList;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getOccupyMoreOtherSet(){
		return this.occupyMoreOtherSet;
	}

	@JSONField(name = "1")
	public void setRecordList(java.util.concurrent.CopyOnWriteArrayList<WildNodeRecord> _v_){
		this.recordList = _v_;
	}

	@JSONField(name = "2")
	public void setOccupyMoreOtherSet(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.occupyMoreOtherSet = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WildNodeRecordList) {
			WildNodeRecordList _o_ = (WildNodeRecordList)_o1_;
			if (!this.recordList.equals(_o_.recordList)) return false;
			if (!this.occupyMoreOtherSet.equals(_o_.occupyMoreOtherSet)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.recordList.hashCode();
		_h_ += _h_ * 31 + this.occupyMoreOtherSet.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(recordList).append(",");
		_sb_.append(occupyMoreOtherSet).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
