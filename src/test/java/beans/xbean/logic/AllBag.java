package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class AllBag extends warrior.mdb.XBean {
    public static final int PLAYER_BAG_TYPE = 1;   // 普通背包
    public static final int EQUIP_BAG_TYPE = 2;   // 装备背包
    public static final int MAP_BAG_TYPE = 3;   // 副本箱子
    public static final int HOME_WEIGHT_BAG_TYPE = 4;   // 家园负重背包
    public static final int HOME_CELL_BAG_TYPE = 5;   // 家园格子背包
    public static final int CARRIER_WEIGHT_BAG_TYPE = 6;   // 载具负重背包
    public static final int CARRIER_EQUIP_BAG_TYPE = 7;   // 载具装备背包
    public static final int HOME_DEPOT_BAG_TYPE = 8;   // 家园仓库背包
    public static final int GENS_DEPOT_BAG_TYPE = 9;   // 军团仓库背包
    public static final int CITY_DEPOT_BAG_TYPE = 10;   // 城市仓库背包

    private java.util.concurrent.ConcurrentHashMap<Integer, Bag> bags;  // key背包类型

    AllBag(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.bags = new java.util.concurrent.ConcurrentHashMap<Integer, Bag>();
	}

	public AllBag() {
		this((warrior.mdb.XBean)null);
	}

	public AllBag(AllBag _o_) {
		this(_o_, null);
	}

	AllBag(AllBag _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.bags = new java.util.concurrent.ConcurrentHashMap<Integer, Bag>();
		_o_.bags.forEach((_k_, _v_) -> this.bags.put(_k_, new Bag(_v_)));
	}

	public void copyFrom(AllBag _o_) {
        this.bags = new java.util.concurrent.ConcurrentHashMap<Integer, Bag>();
		_o_.bags.forEach((_k_, _v_) -> this.bags.put(_k_, new Bag(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Bag> getBags(){
		return this.bags;
	}

	@JSONField(name = "1")
	public void setBags(java.util.concurrent.ConcurrentHashMap<Integer, Bag> _v_){
		this.bags = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AllBag) {
			AllBag _o_ = (AllBag)_o1_;
			if (!this.bags.equals(_o_.bags)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.bags.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(bags).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
