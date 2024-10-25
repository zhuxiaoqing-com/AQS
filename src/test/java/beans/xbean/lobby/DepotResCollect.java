package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class DepotResCollect extends warrior.mdb.XBean {
    private long playerId; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> collectInfo;  // resourceId->num

    DepotResCollect(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.collectInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
	}

	public DepotResCollect() {
		this((warrior.mdb.XBean)null);
	}

	public DepotResCollect(DepotResCollect _o_) {
		this(_o_, null);
	}

	DepotResCollect(DepotResCollect _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.collectInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.collectInfo.forEach((_k_, _v_) -> this.collectInfo.put(_k_, _v_));
	}

	public void copyFrom(DepotResCollect _o_) {
        this.playerId = _o_.playerId;
        this.collectInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.collectInfo.forEach((_k_, _v_) -> this.collectInfo.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getCollectInfo(){
		return this.collectInfo;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "2")
	public void setCollectInfo(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.collectInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof DepotResCollect) {
			DepotResCollect _o_ = (DepotResCollect)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (!this.collectInfo.equals(_o_.collectInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + this.collectInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append(collectInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
