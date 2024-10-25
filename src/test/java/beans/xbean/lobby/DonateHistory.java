package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class DonateHistory extends warrior.mdb.XBean {
    private long time;  // 时间
    private long playerId;  // 捐献者
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> items;  // 捐献的道具
    private String playerName;  // 捐献者名字

    DonateHistory(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.playerName = "";
	}

	public DonateHistory() {
		this((warrior.mdb.XBean)null);
	}

	public DonateHistory(DonateHistory _o_) {
		this(_o_, null);
	}

	DonateHistory(DonateHistory _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.time = _o_.time;
		this.playerId = _o_.playerId;
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, _v_));
		this.playerName = _o_.playerName;
	}

	public void copyFrom(DonateHistory _o_) {
        this.time = _o_.time;
        this.playerId = _o_.playerId;
        this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, _v_));
        this.playerName = _o_.playerName;
	}


	@JSONField(name = "1")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "2")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getItems(){
		return this.items;
	}

	@JSONField(name = "4")
	public String getPlayerName(){
		return this.playerName;
	}

	@JSONField(name = "1")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@JSONField(name = "2")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "3")
	public void setItems(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.items = _v_;
	}

	@JSONField(name = "4")
	public void setPlayerName(String _v_){
		this.playerName = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof DonateHistory) {
			DonateHistory _o_ = (DonateHistory)_o1_;
			if (this.time != _o_.time) return false;
			if (this.playerId != _o_.playerId) return false;
			if (!this.items.equals(_o_.items)) return false;
			if (!this.playerName.equals(_o_.playerName)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + this.items.hashCode();
		_h_ += _h_ * 31 + this.playerName.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(time).append(",");
		_sb_.append(playerId).append(",");
		_sb_.append(items).append(",");
		_sb_.append("T").append(playerName.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
