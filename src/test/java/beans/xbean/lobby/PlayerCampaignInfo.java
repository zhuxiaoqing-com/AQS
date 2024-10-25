package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerCampaignInfo extends warrior.mdb.XBean {
    private long playerId; 
    private String declaration; 
    private int giftMoney; 
    private int remainMoney; 
    private int vote;  // 票数
    private java.util.concurrent.CopyOnWriteArraySet<Long> voter; 

    PlayerCampaignInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.declaration = "";
		this.voter = new java.util.concurrent.CopyOnWriteArraySet<Long>();
	}

	public PlayerCampaignInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerCampaignInfo(PlayerCampaignInfo _o_) {
		this(_o_, null);
	}

	PlayerCampaignInfo(PlayerCampaignInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.declaration = _o_.declaration;
		this.giftMoney = _o_.giftMoney;
		this.remainMoney = _o_.remainMoney;
		this.vote = _o_.vote;
		this.voter = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.voter.forEach(_v_ -> this.voter.add(_v_));
	}

	public void copyFrom(PlayerCampaignInfo _o_) {
        this.playerId = _o_.playerId;
        this.declaration = _o_.declaration;
        this.giftMoney = _o_.giftMoney;
        this.remainMoney = _o_.remainMoney;
        this.vote = _o_.vote;
        this.voter = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.voter.forEach(_v_ -> this.voter.add(_v_));
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "2")
	public String getDeclaration(){
		return this.declaration;
	}

	@JSONField(name = "3")
	public int getGiftMoney(){
		return this.giftMoney;
	}

	@JSONField(name = "4")
	public int getRemainMoney(){
		return this.remainMoney;
	}

	@JSONField(name = "5")
	public int getVote(){
		return this.vote;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getVoter(){
		return this.voter;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "2")
	public void setDeclaration(String _v_){
		this.declaration = _v_;
	}

	@JSONField(name = "3")
	public void setGiftMoney(int _v_){
		this.giftMoney = _v_;
	}

	@JSONField(name = "4")
	public void setRemainMoney(int _v_){
		this.remainMoney = _v_;
	}

	@JSONField(name = "5")
	public void setVote(int _v_){
		this.vote = _v_;
	}

	@JSONField(name = "6")
	public void setVoter(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.voter = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerCampaignInfo) {
			PlayerCampaignInfo _o_ = (PlayerCampaignInfo)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (!this.declaration.equals(_o_.declaration)) return false;
			if (this.giftMoney != _o_.giftMoney) return false;
			if (this.remainMoney != _o_.remainMoney) return false;
			if (this.vote != _o_.vote) return false;
			if (!this.voter.equals(_o_.voter)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + this.declaration.hashCode();
		_h_ += _h_ * 31 + (int)(this.giftMoney);
		_h_ += _h_ * 31 + (int)(this.remainMoney);
		_h_ += _h_ * 31 + (int)(this.vote);
		_h_ += _h_ * 31 + this.voter.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append("T").append(declaration.length()).append(",");
		_sb_.append(giftMoney).append(",");
		_sb_.append(remainMoney).append(",");
		_sb_.append(vote).append(",");
		_sb_.append(voter).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
