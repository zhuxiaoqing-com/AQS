package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerWarfare extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, WarfareInfo> warfareInfoMap;  // 单个战役详细信息 key:战役id value:该战役数据
    private long lastResetTime;  // 最后刷新时间
    private int saveWarfare;  // 保存的关卡

    PlayerWarfare(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.warfareInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, WarfareInfo>();
	}

	public PlayerWarfare() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerWarfare(PlayerWarfare _o_) {
		this(_o_, null);
	}

	PlayerWarfare(PlayerWarfare _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.warfareInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, WarfareInfo>();
		_o_.warfareInfoMap.forEach((_k_, _v_) -> this.warfareInfoMap.put(_k_, new WarfareInfo(_v_)));
		this.lastResetTime = _o_.lastResetTime;
		this.saveWarfare = _o_.saveWarfare;
	}

	public void copyFrom(PlayerWarfare _o_) {
        this.warfareInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, WarfareInfo>();
		_o_.warfareInfoMap.forEach((_k_, _v_) -> this.warfareInfoMap.put(_k_, new WarfareInfo(_v_)));
        this.lastResetTime = _o_.lastResetTime;
        this.saveWarfare = _o_.saveWarfare;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, WarfareInfo> getWarfareInfoMap(){
		return this.warfareInfoMap;
	}

	@JSONField(name = "2")
	public long getLastResetTime(){
		return this.lastResetTime;
	}

	@JSONField(name = "3")
	public int getSaveWarfare(){
		return this.saveWarfare;
	}

	@JSONField(name = "1")
	public void setWarfareInfoMap(java.util.concurrent.ConcurrentHashMap<Integer, WarfareInfo> _v_){
		this.warfareInfoMap = _v_;
	}

	@JSONField(name = "2")
	public void setLastResetTime(long _v_){
		this.lastResetTime = _v_;
	}

	@JSONField(name = "3")
	public void setSaveWarfare(int _v_){
		this.saveWarfare = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerWarfare) {
			PlayerWarfare _o_ = (PlayerWarfare)_o1_;
			if (!this.warfareInfoMap.equals(_o_.warfareInfoMap)) return false;
			if (this.lastResetTime != _o_.lastResetTime) return false;
			if (this.saveWarfare != _o_.saveWarfare) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.warfareInfoMap.hashCode();
		_h_ += _h_ * 31 + (int)(lastResetTime ^ (lastResetTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.saveWarfare);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(warfareInfoMap).append(",");
		_sb_.append(lastResetTime).append(",");
		_sb_.append(saveWarfare).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
