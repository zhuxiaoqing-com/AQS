package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerMakeRankMap extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, PlayerMakeRank> recordMap;  // 领取日志数据 最多500个人

    PlayerMakeRankMap(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.recordMap = new java.util.concurrent.ConcurrentHashMap<Long, PlayerMakeRank>();
	}

	public PlayerMakeRankMap() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerMakeRankMap(PlayerMakeRankMap _o_) {
		this(_o_, null);
	}

	PlayerMakeRankMap(PlayerMakeRankMap _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.recordMap = new java.util.concurrent.ConcurrentHashMap<Long, PlayerMakeRank>();
		_o_.recordMap.forEach((_k_, _v_) -> this.recordMap.put(_k_, new PlayerMakeRank(_v_)));
	}

	public void copyFrom(PlayerMakeRankMap _o_) {
        this.recordMap = new java.util.concurrent.ConcurrentHashMap<Long, PlayerMakeRank>();
		_o_.recordMap.forEach((_k_, _v_) -> this.recordMap.put(_k_, new PlayerMakeRank(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, PlayerMakeRank> getRecordMap(){
		return this.recordMap;
	}

	@JSONField(name = "1")
	public void setRecordMap(java.util.concurrent.ConcurrentHashMap<Long, PlayerMakeRank> _v_){
		this.recordMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerMakeRankMap) {
			PlayerMakeRankMap _o_ = (PlayerMakeRankMap)_o1_;
			if (!this.recordMap.equals(_o_.recordMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.recordMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(recordMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
