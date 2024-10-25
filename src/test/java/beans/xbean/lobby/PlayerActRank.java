package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerActRank extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, ActRankTarget> actMap; 

    PlayerActRank(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actMap = new java.util.concurrent.ConcurrentHashMap<Integer, ActRankTarget>();
	}

	public PlayerActRank() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerActRank(PlayerActRank _o_) {
		this(_o_, null);
	}

	PlayerActRank(PlayerActRank _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actMap = new java.util.concurrent.ConcurrentHashMap<Integer, ActRankTarget>();
		_o_.actMap.forEach((_k_, _v_) -> this.actMap.put(_k_, new ActRankTarget(_v_)));
	}

	public void copyFrom(PlayerActRank _o_) {
        this.actMap = new java.util.concurrent.ConcurrentHashMap<Integer, ActRankTarget>();
		_o_.actMap.forEach((_k_, _v_) -> this.actMap.put(_k_, new ActRankTarget(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, ActRankTarget> getActMap(){
		return this.actMap;
	}

	@JSONField(name = "1")
	public void setActMap(java.util.concurrent.ConcurrentHashMap<Integer, ActRankTarget> _v_){
		this.actMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerActRank) {
			PlayerActRank _o_ = (PlayerActRank)_o1_;
			if (!this.actMap.equals(_o_.actMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.actMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
