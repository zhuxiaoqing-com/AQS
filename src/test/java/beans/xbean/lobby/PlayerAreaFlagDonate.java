package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerAreaFlagDonate extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> areaDonateMap;  // areaId->捐献的游戏货币值

    PlayerAreaFlagDonate(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.areaDonateMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
	}

	public PlayerAreaFlagDonate() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerAreaFlagDonate(PlayerAreaFlagDonate _o_) {
		this(_o_, null);
	}

	PlayerAreaFlagDonate(PlayerAreaFlagDonate _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaDonateMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.areaDonateMap.forEach((_k_, _v_) -> this.areaDonateMap.put(_k_, _v_));
	}

	public void copyFrom(PlayerAreaFlagDonate _o_) {
        this.areaDonateMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.areaDonateMap.forEach((_k_, _v_) -> this.areaDonateMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getAreaDonateMap(){
		return this.areaDonateMap;
	}

	@JSONField(name = "1")
	public void setAreaDonateMap(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.areaDonateMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerAreaFlagDonate) {
			PlayerAreaFlagDonate _o_ = (PlayerAreaFlagDonate)_o1_;
			if (!this.areaDonateMap.equals(_o_.areaDonateMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.areaDonateMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaDonateMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
