package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class WildInfo extends warrior.mdb.XBean {
    public static final int FIGHTING = 1;   // 交战中
    public static final int OCCUPY = 2;   // 占领中

    private int areaId;  // 区域id
    private xbean.global.GensBaseInfo occupyGens;  // 占领的公会
    private long flagNextStartTime;  // 夺旗下一次的开始时间
    private int state;  // 1：开战中 2：占领中
    private int resNum;  // 资源数量
    private java.util.concurrent.ConcurrentHashMap<Integer, WildFlag> wildFlagMap;  // 野外夺旗数据

    WildInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.occupyGens = new GensBaseInfo(this);
		this.wildFlagMap = new java.util.concurrent.ConcurrentHashMap<Integer, WildFlag>();
	}

	public WildInfo() {
		this((warrior.mdb.XBean)null);
	}

	public WildInfo(WildInfo _o_) {
		this(_o_, null);
	}

	WildInfo(WildInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaId = _o_.areaId;
		this.occupyGens = new GensBaseInfo(_o_.occupyGens, this);
		this.flagNextStartTime = _o_.flagNextStartTime;
		this.state = _o_.state;
		this.resNum = _o_.resNum;
		this.wildFlagMap = new java.util.concurrent.ConcurrentHashMap<Integer, WildFlag>();
		_o_.wildFlagMap.forEach((_k_, _v_) -> this.wildFlagMap.put(_k_, new WildFlag(_v_)));
	}

	public void copyFrom(WildInfo _o_) {
        this.areaId = _o_.areaId;
        this.occupyGens = new GensBaseInfo(_o_.occupyGens, this);
        this.flagNextStartTime = _o_.flagNextStartTime;
        this.state = _o_.state;
        this.resNum = _o_.resNum;
        this.wildFlagMap = new java.util.concurrent.ConcurrentHashMap<Integer, WildFlag>();
		_o_.wildFlagMap.forEach((_k_, _v_) -> this.wildFlagMap.put(_k_, new WildFlag(_v_)));
	}


	@JSONField(name = "1")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "2")
	public xbean.global.GensBaseInfo getOccupyGens(){
		return this.occupyGens;
	}

	@JSONField(name = "3")
	public long getFlagNextStartTime(){
		return this.flagNextStartTime;
	}

	@JSONField(name = "5")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "6")
	public int getResNum(){
		return this.resNum;
	}

	@JSONField(name = "7")
	public java.util.concurrent.ConcurrentHashMap<Integer, WildFlag> getWildFlagMap(){
		return this.wildFlagMap;
	}

	@JSONField(name = "1")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "2")
	public void setOccupyGens(xbean.global.GensBaseInfo _v_){
		this.occupyGens = _v_;
	}

	@JSONField(name = "3")
	public void setFlagNextStartTime(long _v_){
		this.flagNextStartTime = _v_;
	}

	@JSONField(name = "5")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "6")
	public void setResNum(int _v_){
		this.resNum = _v_;
	}

	@JSONField(name = "7")
	public void setWildFlagMap(java.util.concurrent.ConcurrentHashMap<Integer, WildFlag> _v_){
		this.wildFlagMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WildInfo) {
			WildInfo _o_ = (WildInfo)_o1_;
			if (this.areaId != _o_.areaId) return false;
			if (!this.occupyGens.equals(_o_.occupyGens)) return false;
			if (this.flagNextStartTime != _o_.flagNextStartTime) return false;
			if (this.state != _o_.state) return false;
			if (this.resNum != _o_.resNum) return false;
			if (!this.wildFlagMap.equals(_o_.wildFlagMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + this.occupyGens.hashCode();
		_h_ += _h_ * 31 + (int)(flagNextStartTime ^ (flagNextStartTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(this.resNum);
		_h_ += _h_ * 31 + this.wildFlagMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaId).append(",");
		_sb_.append(occupyGens).append(",");
		_sb_.append(flagNextStartTime).append(",");
		_sb_.append(state).append(",");
		_sb_.append(resNum).append(",");
		_sb_.append(wildFlagMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
