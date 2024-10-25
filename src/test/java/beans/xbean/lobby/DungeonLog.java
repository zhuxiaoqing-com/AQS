package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class DungeonLog extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> dungeonLog;  // key:type,num
    private int dungeonId;  // dungeonId
    private long mills; 

    DungeonLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dungeonLog = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
	}

	public DungeonLog() {
		this((warrior.mdb.XBean)null);
	}

	public DungeonLog(DungeonLog _o_) {
		this(_o_, null);
	}

	DungeonLog(DungeonLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.dungeonLog = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.dungeonLog.forEach((_k_, _v_) -> this.dungeonLog.put(_k_, _v_));
		this.dungeonId = _o_.dungeonId;
		this.mills = _o_.mills;
	}

	public void copyFrom(DungeonLog _o_) {
        this.dungeonLog = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.dungeonLog.forEach((_k_, _v_) -> this.dungeonLog.put(_k_, _v_));
        this.dungeonId = _o_.dungeonId;
        this.mills = _o_.mills;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getDungeonLog(){
		return this.dungeonLog;
	}

	@JSONField(name = "2")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "3")
	public long getMills(){
		return this.mills;
	}

	@JSONField(name = "1")
	public void setDungeonLog(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.dungeonLog = _v_;
	}

	@JSONField(name = "2")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "3")
	public void setMills(long _v_){
		this.mills = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof DungeonLog) {
			DungeonLog _o_ = (DungeonLog)_o1_;
			if (!this.dungeonLog.equals(_o_.dungeonLog)) return false;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (this.mills != _o_.mills) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.dungeonLog.hashCode();
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + (int)(mills ^ (mills >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(dungeonLog).append(",");
		_sb_.append(dungeonId).append(",");
		_sb_.append(mills).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
