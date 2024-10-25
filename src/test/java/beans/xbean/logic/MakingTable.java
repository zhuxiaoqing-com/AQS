package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class MakingTable extends warrior.mdb.XBean {
    public static final int MATERIAL1 = 1;   // 原料1~8
    public static final int FUEL = 9;   // 燃料 9
    public static final int PRODUCTION = 10;   // 产物10

    private int mid;  // 制作Id
    private xbean.logic.Bag bag;  // 背包，loc=1原料  loc=2产物
    private long mtime;  // 制作时间
    private long smtime;  // 制作开始时间
    private long bcd;  // 燃烧时间
    private long lbtime;  // 剩余燃烧的时间
    private long sptime;  // 开始时间
    private boolean fuel; 
    private java.util.concurrent.CopyOnWriteArrayList<MakingLog> logs;  // 制作日志

    MakingTable(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.bag = new Bag(this);
		this.logs = new java.util.concurrent.CopyOnWriteArrayList<MakingLog>();
	}

	public MakingTable() {
		this((warrior.mdb.XBean)null);
	}

	public MakingTable(MakingTable _o_) {
		this(_o_, null);
	}

	MakingTable(MakingTable _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.mid = _o_.mid;
		this.bag = new Bag(_o_.bag, this);
		this.mtime = _o_.mtime;
		this.smtime = _o_.smtime;
		this.bcd = _o_.bcd;
		this.lbtime = _o_.lbtime;
		this.sptime = _o_.sptime;
		this.fuel = _o_.fuel;
		this.logs = new java.util.concurrent.CopyOnWriteArrayList<MakingLog>();
		_o_.logs.forEach(_v_ -> this.logs.add(new MakingLog(_v_)));
	}

	public void copyFrom(MakingTable _o_) {
        this.mid = _o_.mid;
        this.bag = new Bag(_o_.bag, this);
        this.mtime = _o_.mtime;
        this.smtime = _o_.smtime;
        this.bcd = _o_.bcd;
        this.lbtime = _o_.lbtime;
        this.sptime = _o_.sptime;
        this.fuel = _o_.fuel;
        this.logs = new java.util.concurrent.CopyOnWriteArrayList<MakingLog>();
		_o_.logs.forEach(_v_ -> this.logs.add(new MakingLog(_v_)));
	}


	@JSONField(name = "1")
	public int getMid(){
		return this.mid;
	}

	@JSONField(name = "2")
	public xbean.logic.Bag getBag(){
		return this.bag;
	}

	@JSONField(name = "3")
	public long getMtime(){
		return this.mtime;
	}

	@JSONField(name = "4")
	public long getSmtime(){
		return this.smtime;
	}

	@JSONField(name = "5")
	public long getBcd(){
		return this.bcd;
	}

	@JSONField(name = "6")
	public long getLbtime(){
		return this.lbtime;
	}

	@JSONField(name = "7")
	public long getSptime(){
		return this.sptime;
	}

	@JSONField(name = "8")
	public boolean getFuel(){
		return this.fuel;
	}

	@JSONField(name = "9")
	public java.util.concurrent.CopyOnWriteArrayList<MakingLog> getLogs(){
		return this.logs;
	}

	@JSONField(name = "1")
	public void setMid(int _v_){
		this.mid = _v_;
	}

	@JSONField(name = "2")
	public void setBag(xbean.logic.Bag _v_){
		this.bag = _v_;
	}

	@JSONField(name = "3")
	public void setMtime(long _v_){
		this.mtime = _v_;
	}

	@JSONField(name = "4")
	public void setSmtime(long _v_){
		this.smtime = _v_;
	}

	@JSONField(name = "5")
	public void setBcd(long _v_){
		this.bcd = _v_;
	}

	@JSONField(name = "6")
	public void setLbtime(long _v_){
		this.lbtime = _v_;
	}

	@JSONField(name = "7")
	public void setSptime(long _v_){
		this.sptime = _v_;
	}

	@JSONField(name = "8")
	public void setFuel(boolean _v_){
		this.fuel = _v_;
	}

	@JSONField(name = "9")
	public void setLogs(java.util.concurrent.CopyOnWriteArrayList<MakingLog> _v_){
		this.logs = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MakingTable) {
			MakingTable _o_ = (MakingTable)_o1_;
			if (this.mid != _o_.mid) return false;
			if (!this.bag.equals(_o_.bag)) return false;
			if (this.mtime != _o_.mtime) return false;
			if (this.smtime != _o_.smtime) return false;
			if (this.bcd != _o_.bcd) return false;
			if (this.lbtime != _o_.lbtime) return false;
			if (this.sptime != _o_.sptime) return false;
			if (this.fuel != _o_.fuel) return false;
			if (!this.logs.equals(_o_.logs)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.mid);
		_h_ += _h_ * 31 + this.bag.hashCode();
		_h_ += _h_ * 31 + (int)(mtime ^ (mtime >>> 32));
		_h_ += _h_ * 31 + (int)(smtime ^ (smtime >>> 32));
		_h_ += _h_ * 31 + (int)(bcd ^ (bcd >>> 32));
		_h_ += _h_ * 31 + (int)(lbtime ^ (lbtime >>> 32));
		_h_ += _h_ * 31 + (int)(sptime ^ (sptime >>> 32));
		_h_ += _h_ * 31 + (this.fuel ? 1231 : 1237);
		_h_ += _h_ * 31 + this.logs.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(mid).append(",");
		_sb_.append(bag).append(",");
		_sb_.append(mtime).append(",");
		_sb_.append(smtime).append(",");
		_sb_.append(bcd).append(",");
		_sb_.append(lbtime).append(",");
		_sb_.append(sptime).append(",");
		_sb_.append(fuel).append(",");
		_sb_.append(logs).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
