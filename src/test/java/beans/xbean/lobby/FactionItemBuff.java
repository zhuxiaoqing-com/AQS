package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionItemBuff extends warrior.mdb.XBean {
    private int buffId;  // buffid
    private long startMill;  // 创建时间
    private String playerName;  // 创建玩家名字

    FactionItemBuff(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerName = "";
	}

	public FactionItemBuff() {
		this((warrior.mdb.XBean)null);
	}

	public FactionItemBuff(FactionItemBuff _o_) {
		this(_o_, null);
	}

	FactionItemBuff(FactionItemBuff _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.buffId = _o_.buffId;
		this.startMill = _o_.startMill;
		this.playerName = _o_.playerName;
	}

	public void copyFrom(FactionItemBuff _o_) {
        this.buffId = _o_.buffId;
        this.startMill = _o_.startMill;
        this.playerName = _o_.playerName;
	}


	@JSONField(name = "1")
	public int getBuffId(){
		return this.buffId;
	}

	@JSONField(name = "2")
	public long getStartMill(){
		return this.startMill;
	}

	@JSONField(name = "3")
	public String getPlayerName(){
		return this.playerName;
	}

	@JSONField(name = "1")
	public void setBuffId(int _v_){
		this.buffId = _v_;
	}

	@JSONField(name = "2")
	public void setStartMill(long _v_){
		this.startMill = _v_;
	}

	@JSONField(name = "3")
	public void setPlayerName(String _v_){
		this.playerName = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionItemBuff) {
			FactionItemBuff _o_ = (FactionItemBuff)_o1_;
			if (this.buffId != _o_.buffId) return false;
			if (this.startMill != _o_.startMill) return false;
			if (!this.playerName.equals(_o_.playerName)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.buffId);
		_h_ += _h_ * 31 + (int)(startMill ^ (startMill >>> 32));
		_h_ += _h_ * 31 + this.playerName.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(buffId).append(",");
		_sb_.append(startMill).append(",");
		_sb_.append("T").append(playerName.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
