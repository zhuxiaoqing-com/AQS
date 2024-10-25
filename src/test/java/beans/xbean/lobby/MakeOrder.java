package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class MakeOrder extends warrior.mdb.XBean {
    private long id;  // 订单id
    private int composeId;  // 制造配置id
    private long startTime;  // 开始时间
    private long finishTime;  // 结束时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> items;  // 道具 k:id,v:num

    MakeOrder(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public MakeOrder() {
		this((warrior.mdb.XBean)null);
	}

	public MakeOrder(MakeOrder _o_) {
		this(_o_, null);
	}

	MakeOrder(MakeOrder _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.composeId = _o_.composeId;
		this.startTime = _o_.startTime;
		this.finishTime = _o_.finishTime;
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, _v_));
	}

	public void copyFrom(MakeOrder _o_) {
        this.id = _o_.id;
        this.composeId = _o_.composeId;
        this.startTime = _o_.startTime;
        this.finishTime = _o_.finishTime;
        this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getComposeId(){
		return this.composeId;
	}

	@JSONField(name = "3")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "4")
	public long getFinishTime(){
		return this.finishTime;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getItems(){
		return this.items;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setComposeId(int _v_){
		this.composeId = _v_;
	}

	@JSONField(name = "3")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "4")
	public void setFinishTime(long _v_){
		this.finishTime = _v_;
	}

	@JSONField(name = "5")
	public void setItems(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.items = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MakeOrder) {
			MakeOrder _o_ = (MakeOrder)_o1_;
			if (this.id != _o_.id) return false;
			if (this.composeId != _o_.composeId) return false;
			if (this.startTime != _o_.startTime) return false;
			if (this.finishTime != _o_.finishTime) return false;
			if (!this.items.equals(_o_.items)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + (int)(this.composeId);
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(finishTime ^ (finishTime >>> 32));
		_h_ += _h_ * 31 + this.items.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(composeId).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(finishTime).append(",");
		_sb_.append(items).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
