package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class GsFactionInfo extends warrior.mdb.XBean {
    private int serverId; 
    private int faction; 
    private int winOffence; 
    private int winDefence; 
    private int totalCFWScore; 
    private int playerNum; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> carrierTypeNum; 
    private long leader; 

    GsFactionInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.carrierTypeNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GsFactionInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GsFactionInfo(GsFactionInfo _o_) {
		this(_o_, null);
	}

	GsFactionInfo(GsFactionInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverId = _o_.serverId;
		this.faction = _o_.faction;
		this.winOffence = _o_.winOffence;
		this.winDefence = _o_.winDefence;
		this.totalCFWScore = _o_.totalCFWScore;
		this.playerNum = _o_.playerNum;
		this.carrierTypeNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.carrierTypeNum.forEach((_k_, _v_) -> this.carrierTypeNum.put(_k_, _v_));
		this.leader = _o_.leader;
	}

	public void copyFrom(GsFactionInfo _o_) {
        this.serverId = _o_.serverId;
        this.faction = _o_.faction;
        this.winOffence = _o_.winOffence;
        this.winDefence = _o_.winDefence;
        this.totalCFWScore = _o_.totalCFWScore;
        this.playerNum = _o_.playerNum;
        this.carrierTypeNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.carrierTypeNum.forEach((_k_, _v_) -> this.carrierTypeNum.put(_k_, _v_));
        this.leader = _o_.leader;
	}


	@JSONField(name = "1")
	public int getServerId(){
		return this.serverId;
	}

	@JSONField(name = "2")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "3")
	public int getWinOffence(){
		return this.winOffence;
	}

	@JSONField(name = "4")
	public int getWinDefence(){
		return this.winDefence;
	}

	@JSONField(name = "5")
	public int getTotalCFWScore(){
		return this.totalCFWScore;
	}

	@JSONField(name = "6")
	public int getPlayerNum(){
		return this.playerNum;
	}

	@JSONField(name = "7")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCarrierTypeNum(){
		return this.carrierTypeNum;
	}

	@JSONField(name = "8")
	public long getLeader(){
		return this.leader;
	}

	@JSONField(name = "1")
	public void setServerId(int _v_){
		this.serverId = _v_;
	}

	@JSONField(name = "2")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "3")
	public void setWinOffence(int _v_){
		this.winOffence = _v_;
	}

	@JSONField(name = "4")
	public void setWinDefence(int _v_){
		this.winDefence = _v_;
	}

	@JSONField(name = "5")
	public void setTotalCFWScore(int _v_){
		this.totalCFWScore = _v_;
	}

	@JSONField(name = "6")
	public void setPlayerNum(int _v_){
		this.playerNum = _v_;
	}

	@JSONField(name = "7")
	public void setCarrierTypeNum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.carrierTypeNum = _v_;
	}

	@JSONField(name = "8")
	public void setLeader(long _v_){
		this.leader = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GsFactionInfo) {
			GsFactionInfo _o_ = (GsFactionInfo)_o1_;
			if (this.serverId != _o_.serverId) return false;
			if (this.faction != _o_.faction) return false;
			if (this.winOffence != _o_.winOffence) return false;
			if (this.winDefence != _o_.winDefence) return false;
			if (this.totalCFWScore != _o_.totalCFWScore) return false;
			if (this.playerNum != _o_.playerNum) return false;
			if (!this.carrierTypeNum.equals(_o_.carrierTypeNum)) return false;
			if (this.leader != _o_.leader) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.serverId);
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(this.winOffence);
		_h_ += _h_ * 31 + (int)(this.winDefence);
		_h_ += _h_ * 31 + (int)(this.totalCFWScore);
		_h_ += _h_ * 31 + (int)(this.playerNum);
		_h_ += _h_ * 31 + this.carrierTypeNum.hashCode();
		_h_ += _h_ * 31 + (int)(leader ^ (leader >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(serverId).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(winOffence).append(",");
		_sb_.append(winDefence).append(",");
		_sb_.append(totalCFWScore).append(",");
		_sb_.append(playerNum).append(",");
		_sb_.append(carrierTypeNum).append(",");
		_sb_.append(leader).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
