package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GentesComposeOrder extends warrior.mdb.XBean {
    private long orderId;  // 订单id
    private int composeId;  // 制造id
    private int state;  // 订单状态，待收集，暂停中，制造中
    private java.util.concurrent.CopyOnWriteArrayList<DonateHistory> donateHistory;  // 捐献历史记录
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> needCollectItem;  // 需要收集的道具
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> collectItem;  // 已经收集的道具
    private long finishTime;  // 已经完成的时间
    private long startTime;  // 开始时间
    private long allTime;  // 总共需要的时间
    private java.util.concurrent.CopyOnWriteArraySet<Long> composingPlayer;  // 正在制造或等待中的成员
    private long promoter;  // 订单发起人
    private String promoterName;  // 订单发起人名称
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> originNeedCollectItem;  // 初始需要收集的道具
    private int orderLoc;  // 订单位置

    GentesComposeOrder(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.donateHistory = new java.util.concurrent.CopyOnWriteArrayList<DonateHistory>();
		this.needCollectItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.collectItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.composingPlayer = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.promoterName = "";
		this.originNeedCollectItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GentesComposeOrder() {
		this((warrior.mdb.XBean)null);
	}

	public GentesComposeOrder(GentesComposeOrder _o_) {
		this(_o_, null);
	}

	GentesComposeOrder(GentesComposeOrder _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.orderId = _o_.orderId;
		this.composeId = _o_.composeId;
		this.state = _o_.state;
		this.donateHistory = new java.util.concurrent.CopyOnWriteArrayList<DonateHistory>();
		_o_.donateHistory.forEach(_v_ -> this.donateHistory.add(new DonateHistory(_v_)));
		this.needCollectItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.needCollectItem.forEach((_k_, _v_) -> this.needCollectItem.put(_k_, _v_));
		this.collectItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectItem.forEach((_k_, _v_) -> this.collectItem.put(_k_, _v_));
		this.finishTime = _o_.finishTime;
		this.startTime = _o_.startTime;
		this.allTime = _o_.allTime;
		this.composingPlayer = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.composingPlayer.forEach(_v_ -> this.composingPlayer.add(_v_));
		this.promoter = _o_.promoter;
		this.promoterName = _o_.promoterName;
		this.originNeedCollectItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.originNeedCollectItem.forEach((_k_, _v_) -> this.originNeedCollectItem.put(_k_, _v_));
		this.orderLoc = _o_.orderLoc;
	}

	public void copyFrom(GentesComposeOrder _o_) {
        this.orderId = _o_.orderId;
        this.composeId = _o_.composeId;
        this.state = _o_.state;
        this.donateHistory = new java.util.concurrent.CopyOnWriteArrayList<DonateHistory>();
		_o_.donateHistory.forEach(_v_ -> this.donateHistory.add(new DonateHistory(_v_)));
        this.needCollectItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.needCollectItem.forEach((_k_, _v_) -> this.needCollectItem.put(_k_, _v_));
        this.collectItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectItem.forEach((_k_, _v_) -> this.collectItem.put(_k_, _v_));
        this.finishTime = _o_.finishTime;
        this.startTime = _o_.startTime;
        this.allTime = _o_.allTime;
        this.composingPlayer = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.composingPlayer.forEach(_v_ -> this.composingPlayer.add(_v_));
        this.promoter = _o_.promoter;
        this.promoterName = _o_.promoterName;
        this.originNeedCollectItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.originNeedCollectItem.forEach((_k_, _v_) -> this.originNeedCollectItem.put(_k_, _v_));
        this.orderLoc = _o_.orderLoc;
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
	public int getState(){
		return this.state;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArrayList<DonateHistory> getDonateHistory(){
		return this.donateHistory;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getNeedCollectItem(){
		return this.needCollectItem;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCollectItem(){
		return this.collectItem;
	}

	@JSONField(name = "7")
	public long getFinishTime(){
		return this.finishTime;
	}

	@JSONField(name = "8")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "9")
	public long getAllTime(){
		return this.allTime;
	}

	@JSONField(name = "10")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getComposingPlayer(){
		return this.composingPlayer;
	}

	@JSONField(name = "11")
	public long getPromoter(){
		return this.promoter;
	}

	@JSONField(name = "12")
	public String getPromoterName(){
		return this.promoterName;
	}

	@JSONField(name = "13")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getOriginNeedCollectItem(){
		return this.originNeedCollectItem;
	}

	@JSONField(name = "14")
	public int getOrderLoc(){
		return this.orderLoc;
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
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "4")
	public void setDonateHistory(java.util.concurrent.CopyOnWriteArrayList<DonateHistory> _v_){
		this.donateHistory = _v_;
	}

	@JSONField(name = "5")
	public void setNeedCollectItem(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.needCollectItem = _v_;
	}

	@JSONField(name = "6")
	public void setCollectItem(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.collectItem = _v_;
	}

	@JSONField(name = "7")
	public void setFinishTime(long _v_){
		this.finishTime = _v_;
	}

	@JSONField(name = "8")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "9")
	public void setAllTime(long _v_){
		this.allTime = _v_;
	}

	@JSONField(name = "10")
	public void setComposingPlayer(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.composingPlayer = _v_;
	}

	@JSONField(name = "11")
	public void setPromoter(long _v_){
		this.promoter = _v_;
	}

	@JSONField(name = "12")
	public void setPromoterName(String _v_){
		this.promoterName = _v_;
	}

	@JSONField(name = "13")
	public void setOriginNeedCollectItem(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.originNeedCollectItem = _v_;
	}

	@JSONField(name = "14")
	public void setOrderLoc(int _v_){
		this.orderLoc = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GentesComposeOrder) {
			GentesComposeOrder _o_ = (GentesComposeOrder)_o1_;
			if (this.orderId != _o_.orderId) return false;
			if (this.composeId != _o_.composeId) return false;
			if (this.state != _o_.state) return false;
			if (!this.donateHistory.equals(_o_.donateHistory)) return false;
			if (!this.needCollectItem.equals(_o_.needCollectItem)) return false;
			if (!this.collectItem.equals(_o_.collectItem)) return false;
			if (this.finishTime != _o_.finishTime) return false;
			if (this.startTime != _o_.startTime) return false;
			if (this.allTime != _o_.allTime) return false;
			if (!this.composingPlayer.equals(_o_.composingPlayer)) return false;
			if (this.promoter != _o_.promoter) return false;
			if (!this.promoterName.equals(_o_.promoterName)) return false;
			if (!this.originNeedCollectItem.equals(_o_.originNeedCollectItem)) return false;
			if (this.orderLoc != _o_.orderLoc) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(orderId ^ (orderId >>> 32));
		_h_ += _h_ * 31 + (int)(this.composeId);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + this.donateHistory.hashCode();
		_h_ += _h_ * 31 + this.needCollectItem.hashCode();
		_h_ += _h_ * 31 + this.collectItem.hashCode();
		_h_ += _h_ * 31 + (int)(finishTime ^ (finishTime >>> 32));
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(allTime ^ (allTime >>> 32));
		_h_ += _h_ * 31 + this.composingPlayer.hashCode();
		_h_ += _h_ * 31 + (int)(promoter ^ (promoter >>> 32));
		_h_ += _h_ * 31 + this.promoterName.hashCode();
		_h_ += _h_ * 31 + this.originNeedCollectItem.hashCode();
		_h_ += _h_ * 31 + (int)(this.orderLoc);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(orderId).append(",");
		_sb_.append(composeId).append(",");
		_sb_.append(state).append(",");
		_sb_.append(donateHistory).append(",");
		_sb_.append(needCollectItem).append(",");
		_sb_.append(collectItem).append(",");
		_sb_.append(finishTime).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(allTime).append(",");
		_sb_.append(composingPlayer).append(",");
		_sb_.append(promoter).append(",");
		_sb_.append("T").append(promoterName.length()).append(",");
		_sb_.append(originNeedCollectItem).append(",");
		_sb_.append(orderLoc).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
