package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Bag extends warrior.mdb.XBean {
    private int size;  // 背包长度
    private int wlv = 1;  // 背包负重等级
    private int expand;  // 当前已扩充次数
    private java.util.concurrent.ConcurrentHashMap<Long, Item> items;  // 道具集合 key道具唯一id

    Bag(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.ConcurrentHashMap<Long, Item>();
	}

	public Bag() {
		this((warrior.mdb.XBean)null);
	}

	public Bag(Bag _o_) {
		this(_o_, null);
	}

	Bag(Bag _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.size = _o_.size;
		this.wlv = _o_.wlv;
		this.expand = _o_.expand;
		this.items = new java.util.concurrent.ConcurrentHashMap<Long, Item>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, new Item(_v_)));
	}

	public void copyFrom(Bag _o_) {
        this.size = _o_.size;
        this.wlv = _o_.wlv;
        this.expand = _o_.expand;
        this.items = new java.util.concurrent.ConcurrentHashMap<Long, Item>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, new Item(_v_)));
	}


	@JSONField(name = "1")
	public int getSize(){
		return this.size;
	}

	@JSONField(name = "2")
	public int getWlv(){
		return this.wlv;
	}

	@JSONField(name = "3")
	public int getExpand(){
		return this.expand;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Long, Item> getItems(){
		return this.items;
	}

	@JSONField(name = "1")
	public void setSize(int _v_){
		this.size = _v_;
	}

	@JSONField(name = "2")
	public void setWlv(int _v_){
		this.wlv = _v_;
	}

	@JSONField(name = "3")
	public void setExpand(int _v_){
		this.expand = _v_;
	}

	@JSONField(name = "4")
	public void setItems(java.util.concurrent.ConcurrentHashMap<Long, Item> _v_){
		this.items = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Bag) {
			Bag _o_ = (Bag)_o1_;
			if (this.size != _o_.size) return false;
			if (this.wlv != _o_.wlv) return false;
			if (this.expand != _o_.expand) return false;
			if (!this.items.equals(_o_.items)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.size);
		_h_ += _h_ * 31 + (int)(this.wlv);
		_h_ += _h_ * 31 + (int)(this.expand);
		_h_ += _h_ * 31 + this.items.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(size).append(",");
		_sb_.append(wlv).append(",");
		_sb_.append(expand).append(",");
		_sb_.append(items).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
