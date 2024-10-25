package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CrossServerGensWarMap extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, GensWarMapInfo> landMap;  // 跨服工会战 k:serverGroupId v:工会战数据
    private long seasonAllyStartTime;  // 赛季联盟开启时间

    CrossServerGensWarMap(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.landMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensWarMapInfo>();
	}

	public CrossServerGensWarMap() {
		this((warrior.mdb.XBean)null);
	}

	public CrossServerGensWarMap(CrossServerGensWarMap _o_) {
		this(_o_, null);
	}

	CrossServerGensWarMap(CrossServerGensWarMap _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.landMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensWarMapInfo>();
		_o_.landMap.forEach((_k_, _v_) -> this.landMap.put(_k_, new GensWarMapInfo(_v_)));
		this.seasonAllyStartTime = _o_.seasonAllyStartTime;
	}

	public void copyFrom(CrossServerGensWarMap _o_) {
        this.landMap = new java.util.concurrent.ConcurrentHashMap<Integer, GensWarMapInfo>();
		_o_.landMap.forEach((_k_, _v_) -> this.landMap.put(_k_, new GensWarMapInfo(_v_)));
        this.seasonAllyStartTime = _o_.seasonAllyStartTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, GensWarMapInfo> getLandMap(){
		return this.landMap;
	}

	@JSONField(name = "2")
	public long getSeasonAllyStartTime(){
		return this.seasonAllyStartTime;
	}

	@JSONField(name = "1")
	public void setLandMap(java.util.concurrent.ConcurrentHashMap<Integer, GensWarMapInfo> _v_){
		this.landMap = _v_;
	}

	@JSONField(name = "2")
	public void setSeasonAllyStartTime(long _v_){
		this.seasonAllyStartTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CrossServerGensWarMap) {
			CrossServerGensWarMap _o_ = (CrossServerGensWarMap)_o1_;
			if (!this.landMap.equals(_o_.landMap)) return false;
			if (this.seasonAllyStartTime != _o_.seasonAllyStartTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.landMap.hashCode();
		_h_ += _h_ * 31 + (int)(seasonAllyStartTime ^ (seasonAllyStartTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(landMap).append(",");
		_sb_.append(seasonAllyStartTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
