package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CampaignInfo extends warrior.mdb.XBean {
    private int state; 
    private java.util.concurrent.ConcurrentHashMap<Long, PlayerCampaignInfo> candidates; 
    private long winner; 
    private long openTime;  // 本轮开启时间
    private long endTime;  // 本轮结束时间

    CampaignInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.candidates = new java.util.concurrent.ConcurrentHashMap<Long, PlayerCampaignInfo>();
	}

	public CampaignInfo() {
		this((warrior.mdb.XBean)null);
	}

	public CampaignInfo(CampaignInfo _o_) {
		this(_o_, null);
	}

	CampaignInfo(CampaignInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.state = _o_.state;
		this.candidates = new java.util.concurrent.ConcurrentHashMap<Long, PlayerCampaignInfo>();
		_o_.candidates.forEach((_k_, _v_) -> this.candidates.put(_k_, new PlayerCampaignInfo(_v_)));
		this.winner = _o_.winner;
		this.openTime = _o_.openTime;
		this.endTime = _o_.endTime;
	}

	public void copyFrom(CampaignInfo _o_) {
        this.state = _o_.state;
        this.candidates = new java.util.concurrent.ConcurrentHashMap<Long, PlayerCampaignInfo>();
		_o_.candidates.forEach((_k_, _v_) -> this.candidates.put(_k_, new PlayerCampaignInfo(_v_)));
        this.winner = _o_.winner;
        this.openTime = _o_.openTime;
        this.endTime = _o_.endTime;
	}


	@JSONField(name = "1")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, PlayerCampaignInfo> getCandidates(){
		return this.candidates;
	}

	@JSONField(name = "3")
	public long getWinner(){
		return this.winner;
	}

	@JSONField(name = "4")
	public long getOpenTime(){
		return this.openTime;
	}

	@JSONField(name = "5")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "1")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "2")
	public void setCandidates(java.util.concurrent.ConcurrentHashMap<Long, PlayerCampaignInfo> _v_){
		this.candidates = _v_;
	}

	@JSONField(name = "3")
	public void setWinner(long _v_){
		this.winner = _v_;
	}

	@JSONField(name = "4")
	public void setOpenTime(long _v_){
		this.openTime = _v_;
	}

	@JSONField(name = "5")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CampaignInfo) {
			CampaignInfo _o_ = (CampaignInfo)_o1_;
			if (this.state != _o_.state) return false;
			if (!this.candidates.equals(_o_.candidates)) return false;
			if (this.winner != _o_.winner) return false;
			if (this.openTime != _o_.openTime) return false;
			if (this.endTime != _o_.endTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + this.candidates.hashCode();
		_h_ += _h_ * 31 + (int)(winner ^ (winner >>> 32));
		_h_ += _h_ * 31 + (int)(openTime ^ (openTime >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(state).append(",");
		_sb_.append(candidates).append(",");
		_sb_.append(winner).append(",");
		_sb_.append(openTime).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
