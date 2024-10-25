package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensMake extends warrior.mdb.XBean {
    private long gid;  // 公会id
    private java.util.concurrent.ConcurrentHashMap<Long, MakeOrder> orders;  // 订单信息
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> itemRestMap;  // 受军团科技加成的剩余制造道具数量 k:itemId v:剩余个数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> carrierRestMap;  // 受军团科技加成的剩余制造道具数量 k(载具类型) v:剩余个数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> weaponRestMap;  // 受军团科技加成的剩余制造道具数量 k(武器类型) v:剩余个数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> equipRestMap;  // 受军团科技加成的剩余制造道具数量 k(装备类型) v:剩余个数
    private int total;  // 累计制造军团装备数量

    GensMake(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.orders = new java.util.concurrent.ConcurrentHashMap<Long, MakeOrder>();
		this.itemRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.carrierRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.weaponRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.equipRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GensMake() {
		this((warrior.mdb.XBean)null);
	}

	public GensMake(GensMake _o_) {
		this(_o_, null);
	}

	GensMake(GensMake _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gid = _o_.gid;
		this.orders = new java.util.concurrent.ConcurrentHashMap<Long, MakeOrder>();
		_o_.orders.forEach((_k_, _v_) -> this.orders.put(_k_, new MakeOrder(_v_)));
		this.itemRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.itemRestMap.forEach((_k_, _v_) -> this.itemRestMap.put(_k_, _v_));
		this.carrierRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.carrierRestMap.forEach((_k_, _v_) -> this.carrierRestMap.put(_k_, _v_));
		this.weaponRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.weaponRestMap.forEach((_k_, _v_) -> this.weaponRestMap.put(_k_, _v_));
		this.equipRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.equipRestMap.forEach((_k_, _v_) -> this.equipRestMap.put(_k_, _v_));
		this.total = _o_.total;
	}

	public void copyFrom(GensMake _o_) {
        this.gid = _o_.gid;
        this.orders = new java.util.concurrent.ConcurrentHashMap<Long, MakeOrder>();
		_o_.orders.forEach((_k_, _v_) -> this.orders.put(_k_, new MakeOrder(_v_)));
        this.itemRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.itemRestMap.forEach((_k_, _v_) -> this.itemRestMap.put(_k_, _v_));
        this.carrierRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.carrierRestMap.forEach((_k_, _v_) -> this.carrierRestMap.put(_k_, _v_));
        this.weaponRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.weaponRestMap.forEach((_k_, _v_) -> this.weaponRestMap.put(_k_, _v_));
        this.equipRestMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.equipRestMap.forEach((_k_, _v_) -> this.equipRestMap.put(_k_, _v_));
        this.total = _o_.total;
	}


	@JSONField(name = "1")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, MakeOrder> getOrders(){
		return this.orders;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getItemRestMap(){
		return this.itemRestMap;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCarrierRestMap(){
		return this.carrierRestMap;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getWeaponRestMap(){
		return this.weaponRestMap;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getEquipRestMap(){
		return this.equipRestMap;
	}

	@JSONField(name = "7")
	public int getTotal(){
		return this.total;
	}

	@JSONField(name = "1")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "2")
	public void setOrders(java.util.concurrent.ConcurrentHashMap<Long, MakeOrder> _v_){
		this.orders = _v_;
	}

	@JSONField(name = "3")
	public void setItemRestMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.itemRestMap = _v_;
	}

	@JSONField(name = "4")
	public void setCarrierRestMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.carrierRestMap = _v_;
	}

	@JSONField(name = "5")
	public void setWeaponRestMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.weaponRestMap = _v_;
	}

	@JSONField(name = "6")
	public void setEquipRestMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.equipRestMap = _v_;
	}

	@JSONField(name = "7")
	public void setTotal(int _v_){
		this.total = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensMake) {
			GensMake _o_ = (GensMake)_o1_;
			if (this.gid != _o_.gid) return false;
			if (!this.orders.equals(_o_.orders)) return false;
			if (!this.itemRestMap.equals(_o_.itemRestMap)) return false;
			if (!this.carrierRestMap.equals(_o_.carrierRestMap)) return false;
			if (!this.weaponRestMap.equals(_o_.weaponRestMap)) return false;
			if (!this.equipRestMap.equals(_o_.equipRestMap)) return false;
			if (this.total != _o_.total) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + this.orders.hashCode();
		_h_ += _h_ * 31 + this.itemRestMap.hashCode();
		_h_ += _h_ * 31 + this.carrierRestMap.hashCode();
		_h_ += _h_ * 31 + this.weaponRestMap.hashCode();
		_h_ += _h_ * 31 + this.equipRestMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.total);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gid).append(",");
		_sb_.append(orders).append(",");
		_sb_.append(itemRestMap).append(",");
		_sb_.append(carrierRestMap).append(",");
		_sb_.append(weaponRestMap).append(",");
		_sb_.append(equipRestMap).append(",");
		_sb_.append(total).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
