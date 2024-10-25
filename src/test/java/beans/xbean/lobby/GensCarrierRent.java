package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensCarrierRent extends warrior.mdb.XBean {
    private long id;  // id
    private long owner; 
    private long renter;  // 承租人
    private long applyer;  // 申请人
    private int rentHour;  // 共享小时数
    private xbean.lobby.LBRentCarrier carrier;  // 载具快照
    private long ownerSrl;  // 该载具在原主人的srl
    private long renterSrl;  // 载具在租借人的srl
    private int stage;  // 租借阶段
    private long rentStart;  // 租借开始时间
    private long rentEnd;  // 租借结束时间
    private boolean ownerBack;  // 主人是否返还
    private boolean renterBack;  // 租借人是否删除
    private boolean renterReceive;  // 租借人是否收到
    private boolean fromGens;  // 是否来自军团

    GensCarrierRent(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.carrier = new LBRentCarrier(this);
	}

	public GensCarrierRent() {
		this((warrior.mdb.XBean)null);
	}

	public GensCarrierRent(GensCarrierRent _o_) {
		this(_o_, null);
	}

	GensCarrierRent(GensCarrierRent _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.owner = _o_.owner;
		this.renter = _o_.renter;
		this.applyer = _o_.applyer;
		this.rentHour = _o_.rentHour;
		this.carrier = new LBRentCarrier(_o_.carrier, this);
		this.ownerSrl = _o_.ownerSrl;
		this.renterSrl = _o_.renterSrl;
		this.stage = _o_.stage;
		this.rentStart = _o_.rentStart;
		this.rentEnd = _o_.rentEnd;
		this.ownerBack = _o_.ownerBack;
		this.renterBack = _o_.renterBack;
		this.renterReceive = _o_.renterReceive;
		this.fromGens = _o_.fromGens;
	}

	public void copyFrom(GensCarrierRent _o_) {
        this.id = _o_.id;
        this.owner = _o_.owner;
        this.renter = _o_.renter;
        this.applyer = _o_.applyer;
        this.rentHour = _o_.rentHour;
        this.carrier = new LBRentCarrier(_o_.carrier, this);
        this.ownerSrl = _o_.ownerSrl;
        this.renterSrl = _o_.renterSrl;
        this.stage = _o_.stage;
        this.rentStart = _o_.rentStart;
        this.rentEnd = _o_.rentEnd;
        this.ownerBack = _o_.ownerBack;
        this.renterBack = _o_.renterBack;
        this.renterReceive = _o_.renterReceive;
        this.fromGens = _o_.fromGens;
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public long getOwner(){
		return this.owner;
	}

	@JSONField(name = "3")
	public long getRenter(){
		return this.renter;
	}

	@JSONField(name = "4")
	public long getApplyer(){
		return this.applyer;
	}

	@JSONField(name = "5")
	public int getRentHour(){
		return this.rentHour;
	}

	@JSONField(name = "8")
	public xbean.lobby.LBRentCarrier getCarrier(){
		return this.carrier;
	}

	@JSONField(name = "9")
	public long getOwnerSrl(){
		return this.ownerSrl;
	}

	@JSONField(name = "10")
	public long getRenterSrl(){
		return this.renterSrl;
	}

	@JSONField(name = "11")
	public int getStage(){
		return this.stage;
	}

	@JSONField(name = "12")
	public long getRentStart(){
		return this.rentStart;
	}

	@JSONField(name = "13")
	public long getRentEnd(){
		return this.rentEnd;
	}

	@JSONField(name = "14")
	public boolean getOwnerBack(){
		return this.ownerBack;
	}

	@JSONField(name = "15")
	public boolean getRenterBack(){
		return this.renterBack;
	}

	@JSONField(name = "16")
	public boolean getRenterReceive(){
		return this.renterReceive;
	}

	@JSONField(name = "17")
	public boolean getFromGens(){
		return this.fromGens;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setOwner(long _v_){
		this.owner = _v_;
	}

	@JSONField(name = "3")
	public void setRenter(long _v_){
		this.renter = _v_;
	}

	@JSONField(name = "4")
	public void setApplyer(long _v_){
		this.applyer = _v_;
	}

	@JSONField(name = "5")
	public void setRentHour(int _v_){
		this.rentHour = _v_;
	}

	@JSONField(name = "8")
	public void setCarrier(xbean.lobby.LBRentCarrier _v_){
		this.carrier = _v_;
	}

	@JSONField(name = "9")
	public void setOwnerSrl(long _v_){
		this.ownerSrl = _v_;
	}

	@JSONField(name = "10")
	public void setRenterSrl(long _v_){
		this.renterSrl = _v_;
	}

	@JSONField(name = "11")
	public void setStage(int _v_){
		this.stage = _v_;
	}

	@JSONField(name = "12")
	public void setRentStart(long _v_){
		this.rentStart = _v_;
	}

	@JSONField(name = "13")
	public void setRentEnd(long _v_){
		this.rentEnd = _v_;
	}

	@JSONField(name = "14")
	public void setOwnerBack(boolean _v_){
		this.ownerBack = _v_;
	}

	@JSONField(name = "15")
	public void setRenterBack(boolean _v_){
		this.renterBack = _v_;
	}

	@JSONField(name = "16")
	public void setRenterReceive(boolean _v_){
		this.renterReceive = _v_;
	}

	@JSONField(name = "17")
	public void setFromGens(boolean _v_){
		this.fromGens = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensCarrierRent) {
			GensCarrierRent _o_ = (GensCarrierRent)_o1_;
			if (this.id != _o_.id) return false;
			if (this.owner != _o_.owner) return false;
			if (this.renter != _o_.renter) return false;
			if (this.applyer != _o_.applyer) return false;
			if (this.rentHour != _o_.rentHour) return false;
			if (!this.carrier.equals(_o_.carrier)) return false;
			if (this.ownerSrl != _o_.ownerSrl) return false;
			if (this.renterSrl != _o_.renterSrl) return false;
			if (this.stage != _o_.stage) return false;
			if (this.rentStart != _o_.rentStart) return false;
			if (this.rentEnd != _o_.rentEnd) return false;
			if (this.ownerBack != _o_.ownerBack) return false;
			if (this.renterBack != _o_.renterBack) return false;
			if (this.renterReceive != _o_.renterReceive) return false;
			if (this.fromGens != _o_.fromGens) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + (int)(owner ^ (owner >>> 32));
		_h_ += _h_ * 31 + (int)(renter ^ (renter >>> 32));
		_h_ += _h_ * 31 + (int)(applyer ^ (applyer >>> 32));
		_h_ += _h_ * 31 + (int)(this.rentHour);
		_h_ += _h_ * 31 + this.carrier.hashCode();
		_h_ += _h_ * 31 + (int)(ownerSrl ^ (ownerSrl >>> 32));
		_h_ += _h_ * 31 + (int)(renterSrl ^ (renterSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.stage);
		_h_ += _h_ * 31 + (int)(rentStart ^ (rentStart >>> 32));
		_h_ += _h_ * 31 + (int)(rentEnd ^ (rentEnd >>> 32));
		_h_ += _h_ * 31 + (this.ownerBack ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.renterBack ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.renterReceive ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.fromGens ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(owner).append(",");
		_sb_.append(renter).append(",");
		_sb_.append(applyer).append(",");
		_sb_.append(rentHour).append(",");
		_sb_.append(carrier).append(",");
		_sb_.append(ownerSrl).append(",");
		_sb_.append(renterSrl).append(",");
		_sb_.append(stage).append(",");
		_sb_.append(rentStart).append(",");
		_sb_.append(rentEnd).append(",");
		_sb_.append(ownerBack).append(",");
		_sb_.append(renterBack).append(",");
		_sb_.append(renterReceive).append(",");
		_sb_.append(fromGens).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
