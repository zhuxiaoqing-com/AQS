package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class TroopReservation extends warrior.mdb.XBean {
    private long troopSrl; 
    private xbean.lobby.LBGensBaseInfo gensInfo; 
    private int faction; 
    private int maxArea;  // 预约数量最多的那个区域

    TroopReservation(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gensInfo = new LBGensBaseInfo(this);
	}

	public TroopReservation() {
		this((warrior.mdb.XBean)null);
	}

	public TroopReservation(TroopReservation _o_) {
		this(_o_, null);
	}

	TroopReservation(TroopReservation _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.troopSrl = _o_.troopSrl;
		this.gensInfo = new LBGensBaseInfo(_o_.gensInfo, this);
		this.faction = _o_.faction;
		this.maxArea = _o_.maxArea;
	}

	public void copyFrom(TroopReservation _o_) {
        this.troopSrl = _o_.troopSrl;
        this.gensInfo = new LBGensBaseInfo(_o_.gensInfo, this);
        this.faction = _o_.faction;
        this.maxArea = _o_.maxArea;
	}


	@JSONField(name = "1")
	public long getTroopSrl(){
		return this.troopSrl;
	}

	@JSONField(name = "2")
	public xbean.lobby.LBGensBaseInfo getGensInfo(){
		return this.gensInfo;
	}

	@JSONField(name = "4")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "5")
	public int getMaxArea(){
		return this.maxArea;
	}

	@JSONField(name = "1")
	public void setTroopSrl(long _v_){
		this.troopSrl = _v_;
	}

	@JSONField(name = "2")
	public void setGensInfo(xbean.lobby.LBGensBaseInfo _v_){
		this.gensInfo = _v_;
	}

	@JSONField(name = "4")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "5")
	public void setMaxArea(int _v_){
		this.maxArea = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TroopReservation) {
			TroopReservation _o_ = (TroopReservation)_o1_;
			if (this.troopSrl != _o_.troopSrl) return false;
			if (!this.gensInfo.equals(_o_.gensInfo)) return false;
			if (this.faction != _o_.faction) return false;
			if (this.maxArea != _o_.maxArea) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(troopSrl ^ (troopSrl >>> 32));
		_h_ += _h_ * 31 + this.gensInfo.hashCode();
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(this.maxArea);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(troopSrl).append(",");
		_sb_.append(gensInfo).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(maxArea).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
