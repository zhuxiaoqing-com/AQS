package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class ComposeOrderInfo extends warrior.mdb.XBean {
    private long orderId;  // 订单唯一di
    private int composeId;  // 制造id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> items;  // key:道具id value:数量
    private int state;  // 制造状态
    private long finishTime;  // 已完成制造的时间
    private long startComposeTime;  // 开始制造的时间戳
    private long allTime;  // 本订单总共需要的时间
    private long finishWaitLbMill;  // 完成以后请求lb的时间
    private int composeNum;  // 订单一次性制造数量
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> commandReward;  // key:道具id value:数量

    ComposeOrderInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.commandReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public ComposeOrderInfo() {
		this((warrior.mdb.XBean)null);
	}

	public ComposeOrderInfo(ComposeOrderInfo _o_) {
		this(_o_, null);
	}

	ComposeOrderInfo(ComposeOrderInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.orderId = _o_.orderId;
		this.composeId = _o_.composeId;
		this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, _v_));
		this.state = _o_.state;
		this.finishTime = _o_.finishTime;
		this.startComposeTime = _o_.startComposeTime;
		this.allTime = _o_.allTime;
		this.finishWaitLbMill = _o_.finishWaitLbMill;
		this.composeNum = _o_.composeNum;
		this.commandReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.commandReward.forEach((_k_, _v_) -> this.commandReward.put(_k_, _v_));
	}

	public void copyFrom(ComposeOrderInfo _o_) {
        this.orderId = _o_.orderId;
        this.composeId = _o_.composeId;
        this.items = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.items.forEach((_k_, _v_) -> this.items.put(_k_, _v_));
        this.state = _o_.state;
        this.finishTime = _o_.finishTime;
        this.startComposeTime = _o_.startComposeTime;
        this.allTime = _o_.allTime;
        this.finishWaitLbMill = _o_.finishWaitLbMill;
        this.composeNum = _o_.composeNum;
        this.commandReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.commandReward.forEach((_k_, _v_) -> this.commandReward.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getOrderId(){
		return this.orderId;
	}

	@JSONField(name = "2")
	public int getComposeId(){
		return this.composeId;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getItems(){
		return this.items;
	}

	@JSONField(name = "4")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "5")
	public long getFinishTime(){
		return this.finishTime;
	}

	@JSONField(name = "6")
	public long getStartComposeTime(){
		return this.startComposeTime;
	}

	@JSONField(name = "7")
	public long getAllTime(){
		return this.allTime;
	}

	@JSONField(name = "8")
	public long getFinishWaitLbMill(){
		return this.finishWaitLbMill;
	}

	@JSONField(name = "9")
	public int getComposeNum(){
		return this.composeNum;
	}

	@JSONField(name = "10")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCommandReward(){
		return this.commandReward;
	}

	@JSONField(name = "1")
	public void setOrderId(long _v_){
		this.orderId = _v_;
	}

	@JSONField(name = "2")
	public void setComposeId(int _v_){
		this.composeId = _v_;
	}

	@JSONField(name = "3")
	public void setItems(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.items = _v_;
	}

	@JSONField(name = "4")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "5")
	public void setFinishTime(long _v_){
		this.finishTime = _v_;
	}

	@JSONField(name = "6")
	public void setStartComposeTime(long _v_){
		this.startComposeTime = _v_;
	}

	@JSONField(name = "7")
	public void setAllTime(long _v_){
		this.allTime = _v_;
	}

	@JSONField(name = "8")
	public void setFinishWaitLbMill(long _v_){
		this.finishWaitLbMill = _v_;
	}

	@JSONField(name = "9")
	public void setComposeNum(int _v_){
		this.composeNum = _v_;
	}

	@JSONField(name = "10")
	public void setCommandReward(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.commandReward = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ComposeOrderInfo) {
			ComposeOrderInfo _o_ = (ComposeOrderInfo)_o1_;
			if (this.orderId != _o_.orderId) return false;
			if (this.composeId != _o_.composeId) return false;
			if (!this.items.equals(_o_.items)) return false;
			if (this.state != _o_.state) return false;
			if (this.finishTime != _o_.finishTime) return false;
			if (this.startComposeTime != _o_.startComposeTime) return false;
			if (this.allTime != _o_.allTime) return false;
			if (this.finishWaitLbMill != _o_.finishWaitLbMill) return false;
			if (this.composeNum != _o_.composeNum) return false;
			if (!this.commandReward.equals(_o_.commandReward)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(orderId ^ (orderId >>> 32));
		_h_ += _h_ * 31 + (int)(this.composeId);
		_h_ += _h_ * 31 + this.items.hashCode();
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(finishTime ^ (finishTime >>> 32));
		_h_ += _h_ * 31 + (int)(startComposeTime ^ (startComposeTime >>> 32));
		_h_ += _h_ * 31 + (int)(allTime ^ (allTime >>> 32));
		_h_ += _h_ * 31 + (int)(finishWaitLbMill ^ (finishWaitLbMill >>> 32));
		_h_ += _h_ * 31 + (int)(this.composeNum);
		_h_ += _h_ * 31 + this.commandReward.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(orderId).append(",");
		_sb_.append(composeId).append(",");
		_sb_.append(items).append(",");
		_sb_.append(state).append(",");
		_sb_.append(finishTime).append(",");
		_sb_.append(startComposeTime).append(",");
		_sb_.append(allTime).append(",");
		_sb_.append(finishWaitLbMill).append(",");
		_sb_.append(composeNum).append(",");
		_sb_.append(commandReward).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
