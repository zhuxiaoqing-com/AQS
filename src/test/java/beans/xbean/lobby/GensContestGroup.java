package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensContestGroup extends warrior.mdb.XBean {
    private long gens1;  // 对战方1
    private long gens2;  // 对战方2
    private int win;  // 0:还在战斗中 1:对战方1胜利 2:对战方2胜利
    private int zoneNum;  // 属于哪个半区的
    private java.util.concurrent.CopyOnWriteArraySet<Long> support1;  // 对战方1支持数
    private java.util.concurrent.CopyOnWriteArraySet<Long> support2; 

    GensContestGroup(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.support1 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.support2 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
	}

	public GensContestGroup() {
		this((warrior.mdb.XBean)null);
	}

	public GensContestGroup(GensContestGroup _o_) {
		this(_o_, null);
	}

	GensContestGroup(GensContestGroup _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gens1 = _o_.gens1;
		this.gens2 = _o_.gens2;
		this.win = _o_.win;
		this.zoneNum = _o_.zoneNum;
		this.support1 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.support1.forEach(_v_ -> this.support1.add(_v_));
		this.support2 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.support2.forEach(_v_ -> this.support2.add(_v_));
	}

	public void copyFrom(GensContestGroup _o_) {
        this.gens1 = _o_.gens1;
        this.gens2 = _o_.gens2;
        this.win = _o_.win;
        this.zoneNum = _o_.zoneNum;
        this.support1 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.support1.forEach(_v_ -> this.support1.add(_v_));
        this.support2 = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.support2.forEach(_v_ -> this.support2.add(_v_));
	}


	@JSONField(name = "1")
	public long getGens1(){
		return this.gens1;
	}

	@JSONField(name = "2")
	public long getGens2(){
		return this.gens2;
	}

	@JSONField(name = "3")
	public int getWin(){
		return this.win;
	}

	@JSONField(name = "4")
	public int getZoneNum(){
		return this.zoneNum;
	}

	@JSONField(name = "5")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getSupport1(){
		return this.support1;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getSupport2(){
		return this.support2;
	}

	@JSONField(name = "1")
	public void setGens1(long _v_){
		this.gens1 = _v_;
	}

	@JSONField(name = "2")
	public void setGens2(long _v_){
		this.gens2 = _v_;
	}

	@JSONField(name = "3")
	public void setWin(int _v_){
		this.win = _v_;
	}

	@JSONField(name = "4")
	public void setZoneNum(int _v_){
		this.zoneNum = _v_;
	}

	@JSONField(name = "5")
	public void setSupport1(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.support1 = _v_;
	}

	@JSONField(name = "6")
	public void setSupport2(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.support2 = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensContestGroup) {
			GensContestGroup _o_ = (GensContestGroup)_o1_;
			if (this.gens1 != _o_.gens1) return false;
			if (this.gens2 != _o_.gens2) return false;
			if (this.win != _o_.win) return false;
			if (this.zoneNum != _o_.zoneNum) return false;
			if (!this.support1.equals(_o_.support1)) return false;
			if (!this.support2.equals(_o_.support2)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gens1 ^ (gens1 >>> 32));
		_h_ += _h_ * 31 + (int)(gens2 ^ (gens2 >>> 32));
		_h_ += _h_ * 31 + (int)(this.win);
		_h_ += _h_ * 31 + (int)(this.zoneNum);
		_h_ += _h_ * 31 + this.support1.hashCode();
		_h_ += _h_ * 31 + this.support2.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gens1).append(",");
		_sb_.append(gens2).append(",");
		_sb_.append(win).append(",");
		_sb_.append(zoneNum).append(",");
		_sb_.append(support1).append(",");
		_sb_.append(support2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
