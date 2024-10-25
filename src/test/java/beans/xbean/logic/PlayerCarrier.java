package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerCarrier extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, Carrier> carrier;  // 载具集合
    private long drive;  // 驾驶的载具的srl
    private int inpartId;  // 所在部位
    private long driveStartTime;  // 驾驶载具开始时间
    private long lastCBCarrier;  // 上一次召回载具时间
    private long lastDailyRefresh;  // 上次每天刷新
    private java.util.concurrent.CopyOnWriteArraySet<Long> useSrlSet;  // 当前正在使用的载具列表
    private long rentSrlNextId;  // 租借载具下一个唯一id
    private long lastCheckMill;  // 上次checkTime

    PlayerCarrier(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.carrier = new java.util.concurrent.ConcurrentHashMap<Long, Carrier>();
		this.useSrlSet = new java.util.concurrent.CopyOnWriteArraySet<Long>();
	}

	public PlayerCarrier() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerCarrier(PlayerCarrier _o_) {
		this(_o_, null);
	}

	PlayerCarrier(PlayerCarrier _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.carrier = new java.util.concurrent.ConcurrentHashMap<Long, Carrier>();
		_o_.carrier.forEach((_k_, _v_) -> this.carrier.put(_k_, new Carrier(_v_)));
		this.drive = _o_.drive;
		this.inpartId = _o_.inpartId;
		this.driveStartTime = _o_.driveStartTime;
		this.lastCBCarrier = _o_.lastCBCarrier;
		this.lastDailyRefresh = _o_.lastDailyRefresh;
		this.useSrlSet = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.useSrlSet.forEach(_v_ -> this.useSrlSet.add(_v_));
		this.rentSrlNextId = _o_.rentSrlNextId;
		this.lastCheckMill = _o_.lastCheckMill;
	}

	public void copyFrom(PlayerCarrier _o_) {
        this.carrier = new java.util.concurrent.ConcurrentHashMap<Long, Carrier>();
		_o_.carrier.forEach((_k_, _v_) -> this.carrier.put(_k_, new Carrier(_v_)));
        this.drive = _o_.drive;
        this.inpartId = _o_.inpartId;
        this.driveStartTime = _o_.driveStartTime;
        this.lastCBCarrier = _o_.lastCBCarrier;
        this.lastDailyRefresh = _o_.lastDailyRefresh;
        this.useSrlSet = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.useSrlSet.forEach(_v_ -> this.useSrlSet.add(_v_));
        this.rentSrlNextId = _o_.rentSrlNextId;
        this.lastCheckMill = _o_.lastCheckMill;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, Carrier> getCarrier(){
		return this.carrier;
	}

	@JSONField(name = "3")
	public long getDrive(){
		return this.drive;
	}

	@JSONField(name = "2")
	public int getInpartId(){
		return this.inpartId;
	}

	@JSONField(name = "4")
	public long getDriveStartTime(){
		return this.driveStartTime;
	}

	@JSONField(name = "5")
	public long getLastCBCarrier(){
		return this.lastCBCarrier;
	}

	@JSONField(name = "10")
	public long getLastDailyRefresh(){
		return this.lastDailyRefresh;
	}

	@JSONField(name = "12")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getUseSrlSet(){
		return this.useSrlSet;
	}

	@JSONField(name = "13")
	public long getRentSrlNextId(){
		return this.rentSrlNextId;
	}

	@JSONField(name = "14")
	public long getLastCheckMill(){
		return this.lastCheckMill;
	}

	@JSONField(name = "1")
	public void setCarrier(java.util.concurrent.ConcurrentHashMap<Long, Carrier> _v_){
		this.carrier = _v_;
	}

	@JSONField(name = "3")
	public void setDrive(long _v_){
		this.drive = _v_;
	}

	@JSONField(name = "2")
	public void setInpartId(int _v_){
		this.inpartId = _v_;
	}

	@JSONField(name = "4")
	public void setDriveStartTime(long _v_){
		this.driveStartTime = _v_;
	}

	@JSONField(name = "5")
	public void setLastCBCarrier(long _v_){
		this.lastCBCarrier = _v_;
	}

	@JSONField(name = "10")
	public void setLastDailyRefresh(long _v_){
		this.lastDailyRefresh = _v_;
	}

	@JSONField(name = "12")
	public void setUseSrlSet(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.useSrlSet = _v_;
	}

	@JSONField(name = "13")
	public void setRentSrlNextId(long _v_){
		this.rentSrlNextId = _v_;
	}

	@JSONField(name = "14")
	public void setLastCheckMill(long _v_){
		this.lastCheckMill = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerCarrier) {
			PlayerCarrier _o_ = (PlayerCarrier)_o1_;
			if (!this.carrier.equals(_o_.carrier)) return false;
			if (this.drive != _o_.drive) return false;
			if (this.inpartId != _o_.inpartId) return false;
			if (this.driveStartTime != _o_.driveStartTime) return false;
			if (this.lastCBCarrier != _o_.lastCBCarrier) return false;
			if (this.lastDailyRefresh != _o_.lastDailyRefresh) return false;
			if (!this.useSrlSet.equals(_o_.useSrlSet)) return false;
			if (this.rentSrlNextId != _o_.rentSrlNextId) return false;
			if (this.lastCheckMill != _o_.lastCheckMill) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.carrier.hashCode();
		_h_ += _h_ * 31 + (int)(drive ^ (drive >>> 32));
		_h_ += _h_ * 31 + (int)(this.inpartId);
		_h_ += _h_ * 31 + (int)(driveStartTime ^ (driveStartTime >>> 32));
		_h_ += _h_ * 31 + (int)(lastCBCarrier ^ (lastCBCarrier >>> 32));
		_h_ += _h_ * 31 + (int)(lastDailyRefresh ^ (lastDailyRefresh >>> 32));
		_h_ += _h_ * 31 + this.useSrlSet.hashCode();
		_h_ += _h_ * 31 + (int)(rentSrlNextId ^ (rentSrlNextId >>> 32));
		_h_ += _h_ * 31 + (int)(lastCheckMill ^ (lastCheckMill >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(carrier).append(",");
		_sb_.append(drive).append(",");
		_sb_.append(inpartId).append(",");
		_sb_.append(driveStartTime).append(",");
		_sb_.append(lastCBCarrier).append(",");
		_sb_.append(lastDailyRefresh).append(",");
		_sb_.append(useSrlSet).append(",");
		_sb_.append(rentSrlNextId).append(",");
		_sb_.append(lastCheckMill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
