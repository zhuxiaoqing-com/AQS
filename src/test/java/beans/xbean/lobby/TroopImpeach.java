package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class TroopImpeach extends warrior.mdb.XBean {
    private long initiator; 
    private int yes; 
    private int yesLimit; 
    private int no; 
    private int noLimit; 
    private long endTime; 
    private long troopId; 
    private java.util.concurrent.CopyOnWriteArraySet<Long> votePlayers; 
    private String reason; 

    TroopImpeach(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.votePlayers = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.reason = "";
	}

	public TroopImpeach() {
		this((warrior.mdb.XBean)null);
	}

	public TroopImpeach(TroopImpeach _o_) {
		this(_o_, null);
	}

	TroopImpeach(TroopImpeach _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.initiator = _o_.initiator;
		this.yes = _o_.yes;
		this.yesLimit = _o_.yesLimit;
		this.no = _o_.no;
		this.noLimit = _o_.noLimit;
		this.endTime = _o_.endTime;
		this.troopId = _o_.troopId;
		this.votePlayers = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.votePlayers.forEach(_v_ -> this.votePlayers.add(_v_));
		this.reason = _o_.reason;
	}

	public void copyFrom(TroopImpeach _o_) {
        this.initiator = _o_.initiator;
        this.yes = _o_.yes;
        this.yesLimit = _o_.yesLimit;
        this.no = _o_.no;
        this.noLimit = _o_.noLimit;
        this.endTime = _o_.endTime;
        this.troopId = _o_.troopId;
        this.votePlayers = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.votePlayers.forEach(_v_ -> this.votePlayers.add(_v_));
        this.reason = _o_.reason;
	}


	@JSONField(name = "1")
	public long getInitiator(){
		return this.initiator;
	}

	@JSONField(name = "2")
	public int getYes(){
		return this.yes;
	}

	@JSONField(name = "3")
	public int getYesLimit(){
		return this.yesLimit;
	}

	@JSONField(name = "4")
	public int getNo(){
		return this.no;
	}

	@JSONField(name = "5")
	public int getNoLimit(){
		return this.noLimit;
	}

	@JSONField(name = "6")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "7")
	public long getTroopId(){
		return this.troopId;
	}

	@JSONField(name = "8")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getVotePlayers(){
		return this.votePlayers;
	}

	@JSONField(name = "9")
	public String getReason(){
		return this.reason;
	}

	@JSONField(name = "1")
	public void setInitiator(long _v_){
		this.initiator = _v_;
	}

	@JSONField(name = "2")
	public void setYes(int _v_){
		this.yes = _v_;
	}

	@JSONField(name = "3")
	public void setYesLimit(int _v_){
		this.yesLimit = _v_;
	}

	@JSONField(name = "4")
	public void setNo(int _v_){
		this.no = _v_;
	}

	@JSONField(name = "5")
	public void setNoLimit(int _v_){
		this.noLimit = _v_;
	}

	@JSONField(name = "6")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "7")
	public void setTroopId(long _v_){
		this.troopId = _v_;
	}

	@JSONField(name = "8")
	public void setVotePlayers(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.votePlayers = _v_;
	}

	@JSONField(name = "9")
	public void setReason(String _v_){
		this.reason = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TroopImpeach) {
			TroopImpeach _o_ = (TroopImpeach)_o1_;
			if (this.initiator != _o_.initiator) return false;
			if (this.yes != _o_.yes) return false;
			if (this.yesLimit != _o_.yesLimit) return false;
			if (this.no != _o_.no) return false;
			if (this.noLimit != _o_.noLimit) return false;
			if (this.endTime != _o_.endTime) return false;
			if (this.troopId != _o_.troopId) return false;
			if (!this.votePlayers.equals(_o_.votePlayers)) return false;
			if (!this.reason.equals(_o_.reason)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(initiator ^ (initiator >>> 32));
		_h_ += _h_ * 31 + (int)(this.yes);
		_h_ += _h_ * 31 + (int)(this.yesLimit);
		_h_ += _h_ * 31 + (int)(this.no);
		_h_ += _h_ * 31 + (int)(this.noLimit);
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + (int)(troopId ^ (troopId >>> 32));
		_h_ += _h_ * 31 + this.votePlayers.hashCode();
		_h_ += _h_ * 31 + this.reason.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(initiator).append(",");
		_sb_.append(yes).append(",");
		_sb_.append(yesLimit).append(",");
		_sb_.append(no).append(",");
		_sb_.append(noLimit).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(troopId).append(",");
		_sb_.append(votePlayers).append(",");
		_sb_.append("T").append(reason.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
