package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMakeRank extends warrior.mdb.XBean {
    private long playerId;  // 玩家id
    private String playerName;  // 玩家名字
    private int itemNum;  // 道具num

    PlayerMakeRank(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerName = "";
	}

	public PlayerMakeRank() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMakeRank(PlayerMakeRank _o_) {
		this(_o_, null);
	}

	PlayerMakeRank(PlayerMakeRank _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.playerName = _o_.playerName;
		this.itemNum = _o_.itemNum;
	}

	public void copyFrom(PlayerMakeRank _o_) {
        this.playerId = _o_.playerId;
        this.playerName = _o_.playerName;
        this.itemNum = _o_.itemNum;
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "2")
	public String getPlayerName(){
		return this.playerName;
	}

	@JSONField(name = "3")
	public int getItemNum(){
		return this.itemNum;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "2")
	public void setPlayerName(String _v_){
		this.playerName = _v_;
	}

	@JSONField(name = "3")
	public void setItemNum(int _v_){
		this.itemNum = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMakeRank) {
			PlayerMakeRank _o_ = (PlayerMakeRank)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (!this.playerName.equals(_o_.playerName)) return false;
			if (this.itemNum != _o_.itemNum) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + this.playerName.hashCode();
		_h_ += _h_ * 31 + (int)(this.itemNum);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append("T").append(playerName.length()).append(",");
		_sb_.append(itemNum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
