package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerStatisticsList extends warrior.mdb.XBean {
    public static final int PVP = 1;   // pvp
    public static final int WILD = 2;   // wild

    private xbean.lobby.PlayerStatistics pvpStatistics;  // 战场战绩
    private xbean.lobby.PlayerStatistics wildStatistics;  // 野外战绩

    PlayerStatisticsList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.pvpStatistics = new PlayerStatistics(this);
		this.wildStatistics = new PlayerStatistics(this);
	}

	public PlayerStatisticsList() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerStatisticsList(PlayerStatisticsList _o_) {
		this(_o_, null);
	}

	PlayerStatisticsList(PlayerStatisticsList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.pvpStatistics = new PlayerStatistics(_o_.pvpStatistics, this);
		this.wildStatistics = new PlayerStatistics(_o_.wildStatistics, this);
	}

	public void copyFrom(PlayerStatisticsList _o_) {
        this.pvpStatistics = new PlayerStatistics(_o_.pvpStatistics, this);
        this.wildStatistics = new PlayerStatistics(_o_.wildStatistics, this);
	}


	@JSONField(name = "1")
	public xbean.lobby.PlayerStatistics getPvpStatistics(){
		return this.pvpStatistics;
	}

	@JSONField(name = "2")
	public xbean.lobby.PlayerStatistics getWildStatistics(){
		return this.wildStatistics;
	}

	@JSONField(name = "1")
	public void setPvpStatistics(xbean.lobby.PlayerStatistics _v_){
		this.pvpStatistics = _v_;
	}

	@JSONField(name = "2")
	public void setWildStatistics(xbean.lobby.PlayerStatistics _v_){
		this.wildStatistics = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerStatisticsList) {
			PlayerStatisticsList _o_ = (PlayerStatisticsList)_o1_;
			if (!this.pvpStatistics.equals(_o_.pvpStatistics)) return false;
			if (!this.wildStatistics.equals(_o_.wildStatistics)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.pvpStatistics.hashCode();
		_h_ += _h_ * 31 + this.wildStatistics.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(pvpStatistics).append(",");
		_sb_.append(wildStatistics).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
