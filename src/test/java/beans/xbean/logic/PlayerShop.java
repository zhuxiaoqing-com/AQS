package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerShop extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, PlayerSupplyBox> boxs;  // 补给信息  key:箱子ID（待删除）
    private xbean.logic.BuyItems dlm;  // 每日限购
    private xbean.logic.BuyItems wlm;  // 每周限购
    private xbean.logic.BuyItems flm;  // 永久限购
    private java.util.concurrent.ConcurrentHashMap<Integer, VipInfo> vipMap;  // vip月卡信息
    private java.util.concurrent.CopyOnWriteArraySet<Integer> rechargeIds;  // 充值过的充值档次Id
    private int dailyBuyCount;  // 今日商场购买次数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> shopItem;  // 当前商店内商品
    private int dailyFreshCount;  // 今日刷新次数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> buyHistory;  // 商城购买总次数

    PlayerShop(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.boxs = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerSupplyBox>();
		this.dlm = new BuyItems(this);
		this.wlm = new BuyItems(this);
		this.flm = new BuyItems(this);
		this.vipMap = new java.util.concurrent.ConcurrentHashMap<Integer, VipInfo>();
		this.rechargeIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.shopItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.buyHistory = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerShop() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerShop(PlayerShop _o_) {
		this(_o_, null);
	}

	PlayerShop(PlayerShop _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.boxs = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerSupplyBox>();
		_o_.boxs.forEach((_k_, _v_) -> this.boxs.put(_k_, new PlayerSupplyBox(_v_)));
		this.dlm = new BuyItems(_o_.dlm, this);
		this.wlm = new BuyItems(_o_.wlm, this);
		this.flm = new BuyItems(_o_.flm, this);
		this.vipMap = new java.util.concurrent.ConcurrentHashMap<Integer, VipInfo>();
		_o_.vipMap.forEach((_k_, _v_) -> this.vipMap.put(_k_, new VipInfo(_v_)));
		this.rechargeIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.rechargeIds.forEach(_v_ -> this.rechargeIds.add(_v_));
		this.dailyBuyCount = _o_.dailyBuyCount;
		this.shopItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.shopItem.forEach((_k_, _v_) -> this.shopItem.put(_k_, _v_));
		this.dailyFreshCount = _o_.dailyFreshCount;
		this.buyHistory = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.buyHistory.forEach((_k_, _v_) -> this.buyHistory.put(_k_, _v_));
	}

	public void copyFrom(PlayerShop _o_) {
        this.boxs = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerSupplyBox>();
		_o_.boxs.forEach((_k_, _v_) -> this.boxs.put(_k_, new PlayerSupplyBox(_v_)));
        this.dlm = new BuyItems(_o_.dlm, this);
        this.wlm = new BuyItems(_o_.wlm, this);
        this.flm = new BuyItems(_o_.flm, this);
        this.vipMap = new java.util.concurrent.ConcurrentHashMap<Integer, VipInfo>();
		_o_.vipMap.forEach((_k_, _v_) -> this.vipMap.put(_k_, new VipInfo(_v_)));
        this.rechargeIds = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.rechargeIds.forEach(_v_ -> this.rechargeIds.add(_v_));
        this.dailyBuyCount = _o_.dailyBuyCount;
        this.shopItem = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.shopItem.forEach((_k_, _v_) -> this.shopItem.put(_k_, _v_));
        this.dailyFreshCount = _o_.dailyFreshCount;
        this.buyHistory = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.buyHistory.forEach((_k_, _v_) -> this.buyHistory.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, PlayerSupplyBox> getBoxs(){
		return this.boxs;
	}

	@JSONField(name = "2")
	public xbean.logic.BuyItems getDlm(){
		return this.dlm;
	}

	@JSONField(name = "3")
	public xbean.logic.BuyItems getWlm(){
		return this.wlm;
	}

	@JSONField(name = "4")
	public xbean.logic.BuyItems getFlm(){
		return this.flm;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, VipInfo> getVipMap(){
		return this.vipMap;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getRechargeIds(){
		return this.rechargeIds;
	}

	@JSONField(name = "7")
	public int getDailyBuyCount(){
		return this.dailyBuyCount;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getShopItem(){
		return this.shopItem;
	}

	@JSONField(name = "9")
	public int getDailyFreshCount(){
		return this.dailyFreshCount;
	}

	@JSONField(name = "10")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getBuyHistory(){
		return this.buyHistory;
	}

	@JSONField(name = "1")
	public void setBoxs(java.util.concurrent.ConcurrentHashMap<Integer, PlayerSupplyBox> _v_){
		this.boxs = _v_;
	}

	@JSONField(name = "2")
	public void setDlm(xbean.logic.BuyItems _v_){
		this.dlm = _v_;
	}

	@JSONField(name = "3")
	public void setWlm(xbean.logic.BuyItems _v_){
		this.wlm = _v_;
	}

	@JSONField(name = "4")
	public void setFlm(xbean.logic.BuyItems _v_){
		this.flm = _v_;
	}

	@JSONField(name = "5")
	public void setVipMap(java.util.concurrent.ConcurrentHashMap<Integer, VipInfo> _v_){
		this.vipMap = _v_;
	}

	@JSONField(name = "6")
	public void setRechargeIds(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.rechargeIds = _v_;
	}

	@JSONField(name = "7")
	public void setDailyBuyCount(int _v_){
		this.dailyBuyCount = _v_;
	}

	@JSONField(name = "8")
	public void setShopItem(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.shopItem = _v_;
	}

	@JSONField(name = "9")
	public void setDailyFreshCount(int _v_){
		this.dailyFreshCount = _v_;
	}

	@JSONField(name = "10")
	public void setBuyHistory(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.buyHistory = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerShop) {
			PlayerShop _o_ = (PlayerShop)_o1_;
			if (!this.boxs.equals(_o_.boxs)) return false;
			if (!this.dlm.equals(_o_.dlm)) return false;
			if (!this.wlm.equals(_o_.wlm)) return false;
			if (!this.flm.equals(_o_.flm)) return false;
			if (!this.vipMap.equals(_o_.vipMap)) return false;
			if (!this.rechargeIds.equals(_o_.rechargeIds)) return false;
			if (this.dailyBuyCount != _o_.dailyBuyCount) return false;
			if (!this.shopItem.equals(_o_.shopItem)) return false;
			if (this.dailyFreshCount != _o_.dailyFreshCount) return false;
			if (!this.buyHistory.equals(_o_.buyHistory)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.boxs.hashCode();
		_h_ += _h_ * 31 + this.dlm.hashCode();
		_h_ += _h_ * 31 + this.wlm.hashCode();
		_h_ += _h_ * 31 + this.flm.hashCode();
		_h_ += _h_ * 31 + this.vipMap.hashCode();
		_h_ += _h_ * 31 + this.rechargeIds.hashCode();
		_h_ += _h_ * 31 + (int)(this.dailyBuyCount);
		_h_ += _h_ * 31 + this.shopItem.hashCode();
		_h_ += _h_ * 31 + (int)(this.dailyFreshCount);
		_h_ += _h_ * 31 + this.buyHistory.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(boxs).append(",");
		_sb_.append(dlm).append(",");
		_sb_.append(wlm).append(",");
		_sb_.append(flm).append(",");
		_sb_.append(vipMap).append(",");
		_sb_.append(rechargeIds).append(",");
		_sb_.append(dailyBuyCount).append(",");
		_sb_.append(shopItem).append(",");
		_sb_.append(dailyFreshCount).append(",");
		_sb_.append(buyHistory).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
