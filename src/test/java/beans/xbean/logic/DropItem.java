package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class DropItem extends warrior.mdb.XBean {
    private long uniqueId;  // 资源关卡序列号
    private xbean.logic.Item item;  // 掉落资源
    private java.util.concurrent.CopyOnWriteArrayList<Float> pos;  // 位置集合 x:0下标 z:1下标
    private java.util.concurrent.CopyOnWriteArrayList<Float> dir;  // 方向集合 x:0下标 y:1下标 z:2下标
    private long resId;  // 谁掉落的

    DropItem(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.item = new Item(this);
		this.pos = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		this.dir = new java.util.concurrent.CopyOnWriteArrayList<Float>();
	}

	public DropItem() {
		this((warrior.mdb.XBean)null);
	}

	public DropItem(DropItem _o_) {
		this(_o_, null);
	}

	DropItem(DropItem _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.uniqueId = _o_.uniqueId;
		this.item = new Item(_o_.item, this);
		this.pos = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		_o_.pos.forEach(_v_ -> this.pos.add(_v_));
		this.dir = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		_o_.dir.forEach(_v_ -> this.dir.add(_v_));
		this.resId = _o_.resId;
	}

	public void copyFrom(DropItem _o_) {
        this.uniqueId = _o_.uniqueId;
        this.item = new Item(_o_.item, this);
        this.pos = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		_o_.pos.forEach(_v_ -> this.pos.add(_v_));
        this.dir = new java.util.concurrent.CopyOnWriteArrayList<Float>();
		_o_.dir.forEach(_v_ -> this.dir.add(_v_));
        this.resId = _o_.resId;
	}


	@JSONField(name = "1")
	public long getUniqueId(){
		return this.uniqueId;
	}

	@JSONField(name = "2")
	public xbean.logic.Item getItem(){
		return this.item;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<Float> getPos(){
		return this.pos;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArrayList<Float> getDir(){
		return this.dir;
	}

	@JSONField(name = "5")
	public long getResId(){
		return this.resId;
	}

	@JSONField(name = "1")
	public void setUniqueId(long _v_){
		this.uniqueId = _v_;
	}

	@JSONField(name = "2")
	public void setItem(xbean.logic.Item _v_){
		this.item = _v_;
	}

	@JSONField(name = "3")
	public void setPos(java.util.concurrent.CopyOnWriteArrayList<Float> _v_){
		this.pos = _v_;
	}

	@JSONField(name = "4")
	public void setDir(java.util.concurrent.CopyOnWriteArrayList<Float> _v_){
		this.dir = _v_;
	}

	@JSONField(name = "5")
	public void setResId(long _v_){
		this.resId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof DropItem) {
			DropItem _o_ = (DropItem)_o1_;
			if (this.uniqueId != _o_.uniqueId) return false;
			if (!this.item.equals(_o_.item)) return false;
			if (!this.pos.equals(_o_.pos)) return false;
			if (!this.dir.equals(_o_.dir)) return false;
			if (this.resId != _o_.resId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(uniqueId ^ (uniqueId >>> 32));
		_h_ += _h_ * 31 + this.item.hashCode();
		_h_ += _h_ * 31 + this.pos.hashCode();
		_h_ += _h_ * 31 + this.dir.hashCode();
		_h_ += _h_ * 31 + (int)(resId ^ (resId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(uniqueId).append(",");
		_sb_.append(item).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(dir).append(",");
		_sb_.append(resId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
