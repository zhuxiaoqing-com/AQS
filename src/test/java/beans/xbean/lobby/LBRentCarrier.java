package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBRentCarrier extends warrior.mdb.XBean {
    private int cfgid;  // 载具配置表id
    private xbean.lobby.LBBag wbag;  // 负重背包(油，子弹等)
    private int hp;  // 当前血量
    private int bulletNum;  // 子弹数量
    private int repairCount;  // 大修次数
    private int state;  // 状态
    private long repairEndTime;  // 大修结束时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Float> attr;  // 属性
    private int loadAdd; 

    LBRentCarrier(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.wbag = new LBBag(this);
		this.attr = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
	}

	public LBRentCarrier() {
		this((warrior.mdb.XBean)null);
	}

	public LBRentCarrier(LBRentCarrier _o_) {
		this(_o_, null);
	}

	LBRentCarrier(LBRentCarrier _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.cfgid = _o_.cfgid;
		this.wbag = new LBBag(_o_.wbag, this);
		this.hp = _o_.hp;
		this.bulletNum = _o_.bulletNum;
		this.repairCount = _o_.repairCount;
		this.state = _o_.state;
		this.repairEndTime = _o_.repairEndTime;
		this.attr = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.attr.forEach((_k_, _v_) -> this.attr.put(_k_, _v_));
		this.loadAdd = _o_.loadAdd;
	}

	public void copyFrom(LBRentCarrier _o_) {
        this.cfgid = _o_.cfgid;
        this.wbag = new LBBag(_o_.wbag, this);
        this.hp = _o_.hp;
        this.bulletNum = _o_.bulletNum;
        this.repairCount = _o_.repairCount;
        this.state = _o_.state;
        this.repairEndTime = _o_.repairEndTime;
        this.attr = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.attr.forEach((_k_, _v_) -> this.attr.put(_k_, _v_));
        this.loadAdd = _o_.loadAdd;
	}


	@JSONField(name = "1")
	public int getCfgid(){
		return this.cfgid;
	}

	@JSONField(name = "2")
	public xbean.lobby.LBBag getWbag(){
		return this.wbag;
	}

	@JSONField(name = "3")
	public int getHp(){
		return this.hp;
	}

	@JSONField(name = "4")
	public int getBulletNum(){
		return this.bulletNum;
	}

	@JSONField(name = "5")
	public int getRepairCount(){
		return this.repairCount;
	}

	@JSONField(name = "6")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "7")
	public long getRepairEndTime(){
		return this.repairEndTime;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Integer, Float> getAttr(){
		return this.attr;
	}

	@JSONField(name = "9")
	public int getLoadAdd(){
		return this.loadAdd;
	}

	@JSONField(name = "1")
	public void setCfgid(int _v_){
		this.cfgid = _v_;
	}

	@JSONField(name = "2")
	public void setWbag(xbean.lobby.LBBag _v_){
		this.wbag = _v_;
	}

	@JSONField(name = "3")
	public void setHp(int _v_){
		this.hp = _v_;
	}

	@JSONField(name = "4")
	public void setBulletNum(int _v_){
		this.bulletNum = _v_;
	}

	@JSONField(name = "5")
	public void setRepairCount(int _v_){
		this.repairCount = _v_;
	}

	@JSONField(name = "6")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "7")
	public void setRepairEndTime(long _v_){
		this.repairEndTime = _v_;
	}

	@JSONField(name = "8")
	public void setAttr(java.util.concurrent.ConcurrentHashMap<Integer, Float> _v_){
		this.attr = _v_;
	}

	@JSONField(name = "9")
	public void setLoadAdd(int _v_){
		this.loadAdd = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBRentCarrier) {
			LBRentCarrier _o_ = (LBRentCarrier)_o1_;
			if (this.cfgid != _o_.cfgid) return false;
			if (!this.wbag.equals(_o_.wbag)) return false;
			if (this.hp != _o_.hp) return false;
			if (this.bulletNum != _o_.bulletNum) return false;
			if (this.repairCount != _o_.repairCount) return false;
			if (this.state != _o_.state) return false;
			if (this.repairEndTime != _o_.repairEndTime) return false;
			if (!this.attr.equals(_o_.attr)) return false;
			if (this.loadAdd != _o_.loadAdd) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.cfgid);
		_h_ += _h_ * 31 + this.wbag.hashCode();
		_h_ += _h_ * 31 + (int)(this.hp);
		_h_ += _h_ * 31 + (int)(this.bulletNum);
		_h_ += _h_ * 31 + (int)(this.repairCount);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(repairEndTime ^ (repairEndTime >>> 32));
		_h_ += _h_ * 31 + this.attr.hashCode();
		_h_ += _h_ * 31 + (int)(this.loadAdd);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(cfgid).append(",");
		_sb_.append(wbag).append(",");
		_sb_.append(hp).append(",");
		_sb_.append(bulletNum).append(",");
		_sb_.append(repairCount).append(",");
		_sb_.append(state).append(",");
		_sb_.append(repairEndTime).append(",");
		_sb_.append(attr).append(",");
		_sb_.append(loadAdd).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
