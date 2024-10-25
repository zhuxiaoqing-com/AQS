package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CrossServerGensWildInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, GensWildInfo> gensWildInfoMap;  // 跨服野外 k:serverGroupId v:野外数据
    private long seasonAllyStartTime;  // 赛季联盟开启时间

    CrossServerGensWildInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gensWildInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensWildInfo>();
	}

	public CrossServerGensWildInfo() {
		this((warrior.mdb.XBean)null);
	}

	public CrossServerGensWildInfo(CrossServerGensWildInfo _o_) {
		this(_o_, null);
	}

	CrossServerGensWildInfo(CrossServerGensWildInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gensWildInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensWildInfo>();
		_o_.gensWildInfoMap.forEach((_k_, _v_) -> this.gensWildInfoMap.put(_k_, new GensWildInfo(_v_)));
		this.seasonAllyStartTime = _o_.seasonAllyStartTime;
	}

	public void copyFrom(CrossServerGensWildInfo _o_) {
        this.gensWildInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensWildInfo>();
		_o_.gensWildInfoMap.forEach((_k_, _v_) -> this.gensWildInfoMap.put(_k_, new GensWildInfo(_v_)));
        this.seasonAllyStartTime = _o_.seasonAllyStartTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, GensWildInfo> getGensWildInfoMap(){
		return this.gensWildInfoMap;
	}

	@JSONField(name = "2")
	public long getSeasonAllyStartTime(){
		return this.seasonAllyStartTime;
	}

	@JSONField(name = "1")
	public void setGensWildInfoMap(java.util.concurrent.ConcurrentHashMap<Integer, GensWildInfo> _v_){
		this.gensWildInfoMap = _v_;
	}

	@JSONField(name = "2")
	public void setSeasonAllyStartTime(long _v_){
		this.seasonAllyStartTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CrossServerGensWildInfo) {
			CrossServerGensWildInfo _o_ = (CrossServerGensWildInfo)_o1_;
			if (!this.gensWildInfoMap.equals(_o_.gensWildInfoMap)) return false;
			if (this.seasonAllyStartTime != _o_.seasonAllyStartTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.gensWildInfoMap.hashCode();
		_h_ += _h_ * 31 + (int)(seasonAllyStartTime ^ (seasonAllyStartTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gensWildInfoMap).append(",");
		_sb_.append(seasonAllyStartTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
