package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerGuardSupplyHistory extends warrior.mdb.XBean {
    private long time;  // 补给时间
    private int cost;  // 花费
    private java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyEntry> entris;  // 道具列表

    PlayerGuardSupplyHistory(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.entris = new java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyEntry>();
	}

	public PlayerGuardSupplyHistory() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerGuardSupplyHistory(PlayerGuardSupplyHistory _o_) {
		this(_o_, null);
	}

	PlayerGuardSupplyHistory(PlayerGuardSupplyHistory _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.time = _o_.time;
		this.cost = _o_.cost;
		this.entris = new java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyEntry>();
		_o_.entris.forEach(_v_ -> this.entris.add(new PlayerGuardSupplyEntry(_v_)));
	}

	public void copyFrom(PlayerGuardSupplyHistory _o_) {
        this.time = _o_.time;
        this.cost = _o_.cost;
        this.entris = new java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyEntry>();
		_o_.entris.forEach(_v_ -> this.entris.add(new PlayerGuardSupplyEntry(_v_)));
	}


	@JSONField(name = "1")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "2")
	public int getCost(){
		return this.cost;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyEntry> getEntris(){
		return this.entris;
	}

	@JSONField(name = "1")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@JSONField(name = "2")
	public void setCost(int _v_){
		this.cost = _v_;
	}

	@JSONField(name = "3")
	public void setEntris(java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyEntry> _v_){
		this.entris = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerGuardSupplyHistory) {
			PlayerGuardSupplyHistory _o_ = (PlayerGuardSupplyHistory)_o1_;
			if (this.time != _o_.time) return false;
			if (this.cost != _o_.cost) return false;
			if (!this.entris.equals(_o_.entris)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		_h_ += _h_ * 31 + (int)(this.cost);
		_h_ += _h_ * 31 + this.entris.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(time).append(",");
		_sb_.append(cost).append(",");
		_sb_.append(entris).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
