package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class OrderInfo extends warrior.mdb.XBean {
    public static final int PAY_SUCCESS = 1;   // 回调支付成功
    public static final int REWARD_SUCCESS = 2;   // 奖励发放成功

    private long createT;  // 创建时间
    private long payT;  // 支付时间
    private String gOrderId;  // 游戏生成的订单id
    private String pOrderId;  // 平台订单id
    private String userId;  // 账号
    private int cfgId;  // 购买的策划表id
    private int money;  // 金额(分)
    private int payMoney;  // 实际金额(分)
    private int state;  // 状态
    private long playerId;  // 玩家id

    OrderInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gOrderId = "";
		this.pOrderId = "";
		this.userId = "";
	}

	public OrderInfo() {
		this((warrior.mdb.XBean)null);
	}

	public OrderInfo(OrderInfo _o_) {
		this(_o_, null);
	}

	OrderInfo(OrderInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.createT = _o_.createT;
		this.payT = _o_.payT;
		this.gOrderId = _o_.gOrderId;
		this.pOrderId = _o_.pOrderId;
		this.userId = _o_.userId;
		this.cfgId = _o_.cfgId;
		this.money = _o_.money;
		this.payMoney = _o_.payMoney;
		this.state = _o_.state;
		this.playerId = _o_.playerId;
	}

	public void copyFrom(OrderInfo _o_) {
        this.createT = _o_.createT;
        this.payT = _o_.payT;
        this.gOrderId = _o_.gOrderId;
        this.pOrderId = _o_.pOrderId;
        this.userId = _o_.userId;
        this.cfgId = _o_.cfgId;
        this.money = _o_.money;
        this.payMoney = _o_.payMoney;
        this.state = _o_.state;
        this.playerId = _o_.playerId;
	}


	@JSONField(name = "1")
	public long getCreateT(){
		return this.createT;
	}

	@JSONField(name = "2")
	public long getPayT(){
		return this.payT;
	}

	@JSONField(name = "3")
	public String getGOrderId(){
		return this.gOrderId;
	}

	@JSONField(name = "4")
	public String getPOrderId(){
		return this.pOrderId;
	}

	@JSONField(name = "5")
	public String getUserId(){
		return this.userId;
	}

	@JSONField(name = "6")
	public int getCfgId(){
		return this.cfgId;
	}

	@JSONField(name = "7")
	public int getMoney(){
		return this.money;
	}

	@JSONField(name = "8")
	public int getPayMoney(){
		return this.payMoney;
	}

	@JSONField(name = "9")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "10")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "1")
	public void setCreateT(long _v_){
		this.createT = _v_;
	}

	@JSONField(name = "2")
	public void setPayT(long _v_){
		this.payT = _v_;
	}

	@JSONField(name = "3")
	public void setGOrderId(String _v_){
		this.gOrderId = _v_;
	}

	@JSONField(name = "4")
	public void setPOrderId(String _v_){
		this.pOrderId = _v_;
	}

	@JSONField(name = "5")
	public void setUserId(String _v_){
		this.userId = _v_;
	}

	@JSONField(name = "6")
	public void setCfgId(int _v_){
		this.cfgId = _v_;
	}

	@JSONField(name = "7")
	public void setMoney(int _v_){
		this.money = _v_;
	}

	@JSONField(name = "8")
	public void setPayMoney(int _v_){
		this.payMoney = _v_;
	}

	@JSONField(name = "9")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "10")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof OrderInfo) {
			OrderInfo _o_ = (OrderInfo)_o1_;
			if (this.createT != _o_.createT) return false;
			if (this.payT != _o_.payT) return false;
			if (!this.gOrderId.equals(_o_.gOrderId)) return false;
			if (!this.pOrderId.equals(_o_.pOrderId)) return false;
			if (!this.userId.equals(_o_.userId)) return false;
			if (this.cfgId != _o_.cfgId) return false;
			if (this.money != _o_.money) return false;
			if (this.payMoney != _o_.payMoney) return false;
			if (this.state != _o_.state) return false;
			if (this.playerId != _o_.playerId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(createT ^ (createT >>> 32));
		_h_ += _h_ * 31 + (int)(payT ^ (payT >>> 32));
		_h_ += _h_ * 31 + this.gOrderId.hashCode();
		_h_ += _h_ * 31 + this.pOrderId.hashCode();
		_h_ += _h_ * 31 + this.userId.hashCode();
		_h_ += _h_ * 31 + (int)(this.cfgId);
		_h_ += _h_ * 31 + (int)(this.money);
		_h_ += _h_ * 31 + (int)(this.payMoney);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(createT).append(",");
		_sb_.append(payT).append(",");
		_sb_.append("T").append(gOrderId.length()).append(",");
		_sb_.append("T").append(pOrderId.length()).append(",");
		_sb_.append("T").append(userId.length()).append(",");
		_sb_.append(cfgId).append(",");
		_sb_.append(money).append(",");
		_sb_.append(payMoney).append(",");
		_sb_.append(state).append(",");
		_sb_.append(playerId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
