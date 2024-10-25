package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionDonateRankBean extends warrior.mdb.XBean {
    private long playerId;  // idpo
    private int donateNum;  // 捐献数量

    FactionDonateRankBean(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public FactionDonateRankBean() {
		this((warrior.mdb.XBean)null);
	}

	public FactionDonateRankBean(FactionDonateRankBean _o_) {
		this(_o_, null);
	}

	FactionDonateRankBean(FactionDonateRankBean _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.donateNum = _o_.donateNum;
	}

	public void copyFrom(FactionDonateRankBean _o_) {
        this.playerId = _o_.playerId;
        this.donateNum = _o_.donateNum;
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "2")
	public int getDonateNum(){
		return this.donateNum;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "2")
	public void setDonateNum(int _v_){
		this.donateNum = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionDonateRankBean) {
			FactionDonateRankBean _o_ = (FactionDonateRankBean)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (this.donateNum != _o_.donateNum) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + (int)(this.donateNum);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append(donateNum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
