package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AllianceInvite extends warrior.mdb.XBean {
    private long allianceId; 
    private long inviteEndTime; 

    AllianceInvite(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public AllianceInvite() {
		this((warrior.mdb.XBean)null);
	}

	public AllianceInvite(AllianceInvite _o_) {
		this(_o_, null);
	}

	AllianceInvite(AllianceInvite _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.allianceId = _o_.allianceId;
		this.inviteEndTime = _o_.inviteEndTime;
	}

	public void copyFrom(AllianceInvite _o_) {
        this.allianceId = _o_.allianceId;
        this.inviteEndTime = _o_.inviteEndTime;
	}


	@JSONField(name = "1")
	public long getAllianceId(){
		return this.allianceId;
	}

	@JSONField(name = "2")
	public long getInviteEndTime(){
		return this.inviteEndTime;
	}

	@JSONField(name = "1")
	public void setAllianceId(long _v_){
		this.allianceId = _v_;
	}

	@JSONField(name = "2")
	public void setInviteEndTime(long _v_){
		this.inviteEndTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AllianceInvite) {
			AllianceInvite _o_ = (AllianceInvite)_o1_;
			if (this.allianceId != _o_.allianceId) return false;
			if (this.inviteEndTime != _o_.inviteEndTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(allianceId ^ (allianceId >>> 32));
		_h_ += _h_ * 31 + (int)(inviteEndTime ^ (inviteEndTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(allianceId).append(",");
		_sb_.append(inviteEndTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
