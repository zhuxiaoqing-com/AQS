package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class HomeBuilding extends warrior.mdb.XBean {
    private long bsrl;  // uuid
    private int bid;  // 建筑id
    private int pos;  // 位置
    private int dir;  // 朝向
    private int type;  // 类型
    private int blv;  // 建筑等级
    private xbean.logic.TransportationProducer infantile;  // 雏形建筑功能
    private xbean.logic.TransportationFucntion traffic;  // 交通运输功能
    private xbean.logic.MakingTable making;  // 生产制造功能
    private xbean.logic.HomeDepot depot;  // 仓库功能
    private xbean.logic.EquipmentMakingTable equipmentMaking;  // 装备制作台
    private xbean.logic.CarrierTable carrier;  // 载具工厂

    HomeBuilding(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.infantile = new TransportationProducer(this);
		this.traffic = new TransportationFucntion(this);
		this.making = new MakingTable(this);
		this.depot = new HomeDepot(this);
		this.equipmentMaking = new EquipmentMakingTable(this);
		this.carrier = new CarrierTable(this);
	}

	public HomeBuilding() {
		this((warrior.mdb.XBean)null);
	}

	public HomeBuilding(HomeBuilding _o_) {
		this(_o_, null);
	}

	HomeBuilding(HomeBuilding _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.bsrl = _o_.bsrl;
		this.bid = _o_.bid;
		this.pos = _o_.pos;
		this.dir = _o_.dir;
		this.type = _o_.type;
		this.blv = _o_.blv;
		this.infantile = new TransportationProducer(_o_.infantile, this);
		this.traffic = new TransportationFucntion(_o_.traffic, this);
		this.making = new MakingTable(_o_.making, this);
		this.depot = new HomeDepot(_o_.depot, this);
		this.equipmentMaking = new EquipmentMakingTable(_o_.equipmentMaking, this);
		this.carrier = new CarrierTable(_o_.carrier, this);
	}

	public void copyFrom(HomeBuilding _o_) {
        this.bsrl = _o_.bsrl;
        this.bid = _o_.bid;
        this.pos = _o_.pos;
        this.dir = _o_.dir;
        this.type = _o_.type;
        this.blv = _o_.blv;
        this.infantile = new TransportationProducer(_o_.infantile, this);
        this.traffic = new TransportationFucntion(_o_.traffic, this);
        this.making = new MakingTable(_o_.making, this);
        this.depot = new HomeDepot(_o_.depot, this);
        this.equipmentMaking = new EquipmentMakingTable(_o_.equipmentMaking, this);
        this.carrier = new CarrierTable(_o_.carrier, this);
	}


	@JSONField(name = "1")
	public long getBsrl(){
		return this.bsrl;
	}

	@JSONField(name = "2")
	public int getBid(){
		return this.bid;
	}

	@JSONField(name = "3")
	public int getPos(){
		return this.pos;
	}

	@JSONField(name = "4")
	public int getDir(){
		return this.dir;
	}

	@JSONField(name = "5")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "6")
	public int getBlv(){
		return this.blv;
	}

	@JSONField(name = "7")
	public xbean.logic.TransportationProducer getInfantile(){
		return this.infantile;
	}

	@JSONField(name = "8")
	public xbean.logic.TransportationFucntion getTraffic(){
		return this.traffic;
	}

	@JSONField(name = "9")
	public xbean.logic.MakingTable getMaking(){
		return this.making;
	}

	@JSONField(name = "10")
	public xbean.logic.HomeDepot getDepot(){
		return this.depot;
	}

	@JSONField(name = "11")
	public xbean.logic.EquipmentMakingTable getEquipmentMaking(){
		return this.equipmentMaking;
	}

	@JSONField(name = "12")
	public xbean.logic.CarrierTable getCarrier(){
		return this.carrier;
	}

	@JSONField(name = "1")
	public void setBsrl(long _v_){
		this.bsrl = _v_;
	}

	@JSONField(name = "2")
	public void setBid(int _v_){
		this.bid = _v_;
	}

	@JSONField(name = "3")
	public void setPos(int _v_){
		this.pos = _v_;
	}

	@JSONField(name = "4")
	public void setDir(int _v_){
		this.dir = _v_;
	}

	@JSONField(name = "5")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "6")
	public void setBlv(int _v_){
		this.blv = _v_;
	}

	@JSONField(name = "7")
	public void setInfantile(xbean.logic.TransportationProducer _v_){
		this.infantile = _v_;
	}

	@JSONField(name = "8")
	public void setTraffic(xbean.logic.TransportationFucntion _v_){
		this.traffic = _v_;
	}

	@JSONField(name = "9")
	public void setMaking(xbean.logic.MakingTable _v_){
		this.making = _v_;
	}

	@JSONField(name = "10")
	public void setDepot(xbean.logic.HomeDepot _v_){
		this.depot = _v_;
	}

	@JSONField(name = "11")
	public void setEquipmentMaking(xbean.logic.EquipmentMakingTable _v_){
		this.equipmentMaking = _v_;
	}

	@JSONField(name = "12")
	public void setCarrier(xbean.logic.CarrierTable _v_){
		this.carrier = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof HomeBuilding) {
			HomeBuilding _o_ = (HomeBuilding)_o1_;
			if (this.bsrl != _o_.bsrl) return false;
			if (this.bid != _o_.bid) return false;
			if (this.pos != _o_.pos) return false;
			if (this.dir != _o_.dir) return false;
			if (this.type != _o_.type) return false;
			if (this.blv != _o_.blv) return false;
			if (!this.infantile.equals(_o_.infantile)) return false;
			if (!this.traffic.equals(_o_.traffic)) return false;
			if (!this.making.equals(_o_.making)) return false;
			if (!this.depot.equals(_o_.depot)) return false;
			if (!this.equipmentMaking.equals(_o_.equipmentMaking)) return false;
			if (!this.carrier.equals(_o_.carrier)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(bsrl ^ (bsrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.bid);
		_h_ += _h_ * 31 + (int)(this.pos);
		_h_ += _h_ * 31 + (int)(this.dir);
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(this.blv);
		_h_ += _h_ * 31 + this.infantile.hashCode();
		_h_ += _h_ * 31 + this.traffic.hashCode();
		_h_ += _h_ * 31 + this.making.hashCode();
		_h_ += _h_ * 31 + this.depot.hashCode();
		_h_ += _h_ * 31 + this.equipmentMaking.hashCode();
		_h_ += _h_ * 31 + this.carrier.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(bsrl).append(",");
		_sb_.append(bid).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(dir).append(",");
		_sb_.append(type).append(",");
		_sb_.append(blv).append(",");
		_sb_.append(infantile).append(",");
		_sb_.append(traffic).append(",");
		_sb_.append(making).append(",");
		_sb_.append(depot).append(",");
		_sb_.append(equipmentMaking).append(",");
		_sb_.append(carrier).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
