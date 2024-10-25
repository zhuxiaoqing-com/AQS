package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerDungeonRecord extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> skilledRecord; 

    PlayerDungeonRecord(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.skilledRecord = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerDungeonRecord() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerDungeonRecord(PlayerDungeonRecord _o_) {
		this(_o_, null);
	}

	PlayerDungeonRecord(PlayerDungeonRecord _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.skilledRecord = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.skilledRecord.forEach((_k_, _v_) -> this.skilledRecord.put(_k_, _v_));
	}

	public void copyFrom(PlayerDungeonRecord _o_) {
        this.skilledRecord = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.skilledRecord.forEach((_k_, _v_) -> this.skilledRecord.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getSkilledRecord(){
		return this.skilledRecord;
	}

	@JSONField(name = "1")
	public void setSkilledRecord(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.skilledRecord = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerDungeonRecord) {
			PlayerDungeonRecord _o_ = (PlayerDungeonRecord)_o1_;
			if (!this.skilledRecord.equals(_o_.skilledRecord)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.skilledRecord.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(skilledRecord).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
