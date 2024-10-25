package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerReservation extends warrior.mdb.XBean {
    private long timeId;  // 标记id
    private int count;  // 预约次数
    private int areaId;  // 当前已经预约的区域id

    PlayerReservation(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public PlayerReservation() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerReservation(PlayerReservation _o_) {
		this(_o_, null);
	}

	PlayerReservation(PlayerReservation _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.timeId = _o_.timeId;
		this.count = _o_.count;
		this.areaId = _o_.areaId;
	}

	public void copyFrom(PlayerReservation _o_) {
        this.timeId = _o_.timeId;
        this.count = _o_.count;
        this.areaId = _o_.areaId;
	}


	@JSONField(name = "1")
	public long getTimeId(){
		return this.timeId;
	}

	@JSONField(name = "2")
	public int getCount(){
		return this.count;
	}

	@JSONField(name = "3")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "1")
	public void setTimeId(long _v_){
		this.timeId = _v_;
	}

	@JSONField(name = "2")
	public void setCount(int _v_){
		this.count = _v_;
	}

	@JSONField(name = "3")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerReservation) {
			PlayerReservation _o_ = (PlayerReservation)_o1_;
			if (this.timeId != _o_.timeId) return false;
			if (this.count != _o_.count) return false;
			if (this.areaId != _o_.areaId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(timeId ^ (timeId >>> 32));
		_h_ += _h_ * 31 + (int)(this.count);
		_h_ += _h_ * 31 + (int)(this.areaId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(timeId).append(",");
		_sb_.append(count).append(",");
		_sb_.append(areaId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
