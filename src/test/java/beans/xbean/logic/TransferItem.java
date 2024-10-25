package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class TransferItem extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, Item> items;  // 道具集合
    private long endTime;  // 结束时间
    private int originMapId;  // 源城市mapId
    private int targetMapId;  // 目的城市mapId

    TransferItem(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.ConcurrentHashMap<Long, Item>();
	}

	public TransferItem() {
		this((warrior.mdb.XBean)null);
	}

	public TransferItem(TransferItem _o_) {
		this(_o_, null);
	}

	TransferItem(TransferItem _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.items = new java.util.concurrent.ConcurrentHashMap<Long, Item>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, new Item(_v_)));
		this.endTime = _o_.endTime;
		this.originMapId = _o_.originMapId;
		this.targetMapId = _o_.targetMapId;
	}

	public void copyFrom(TransferItem _o_) {
        this.items = new java.util.concurrent.ConcurrentHashMap<Long, Item>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, new Item(_v_)));
        this.endTime = _o_.endTime;
        this.originMapId = _o_.originMapId;
        this.targetMapId = _o_.targetMapId;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, Item> getItems(){
		return this.items;
	}

	@JSONField(name = "2")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "3")
	public int getOriginMapId(){
		return this.originMapId;
	}

	@JSONField(name = "4")
	public int getTargetMapId(){
		return this.targetMapId;
	}

	@JSONField(name = "1")
	public void setItems(java.util.concurrent.ConcurrentHashMap<Long, Item> _v_){
		this.items = _v_;
	}

	@JSONField(name = "2")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "3")
	public void setOriginMapId(int _v_){
		this.originMapId = _v_;
	}

	@JSONField(name = "4")
	public void setTargetMapId(int _v_){
		this.targetMapId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TransferItem) {
			TransferItem _o_ = (TransferItem)_o1_;
			if (!this.items.equals(_o_.items)) return false;
			if (this.endTime != _o_.endTime) return false;
			if (this.originMapId != _o_.originMapId) return false;
			if (this.targetMapId != _o_.targetMapId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.items.hashCode();
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.originMapId);
		_h_ += _h_ * 31 + (int)(this.targetMapId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(items).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(originMapId).append(",");
		_sb_.append(targetMapId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
