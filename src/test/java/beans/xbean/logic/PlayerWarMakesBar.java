package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerWarMakesBar extends warrior.mdb.XBean {
    private int exp;  // 经验
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> receiveMap;  // 已经领取的进度条奖励 id->次数
    private long lastRefresh;  // 上一次刷新时间

    PlayerWarMakesBar(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.receiveMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerWarMakesBar() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerWarMakesBar(PlayerWarMakesBar _o_) {
		this(_o_, null);
	}

	PlayerWarMakesBar(PlayerWarMakesBar _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.exp = _o_.exp;
		this.receiveMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.receiveMap.forEach((_k_, _v_) -> this.receiveMap.put(_k_, _v_));
		this.lastRefresh = _o_.lastRefresh;
	}

	public void copyFrom(PlayerWarMakesBar _o_) {
        this.exp = _o_.exp;
        this.receiveMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.receiveMap.forEach((_k_, _v_) -> this.receiveMap.put(_k_, _v_));
        this.lastRefresh = _o_.lastRefresh;
	}


	@JSONField(name = "1")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getReceiveMap(){
		return this.receiveMap;
	}

	@JSONField(name = "3")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "1")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@JSONField(name = "2")
	public void setReceiveMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.receiveMap = _v_;
	}

	@JSONField(name = "3")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerWarMakesBar) {
			PlayerWarMakesBar _o_ = (PlayerWarMakesBar)_o1_;
			if (this.exp != _o_.exp) return false;
			if (!this.receiveMap.equals(_o_.receiveMap)) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.exp);
		_h_ += _h_ * 31 + this.receiveMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(exp).append(",");
		_sb_.append(receiveMap).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
