package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerRandomGift extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> historyNum;  // 历史抽取次数(发送完奖励以后累加) k:drawType*100w + itemId v:历史总抽取次数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> drop2NumMap;  // 触发奖励次数 k:抽取Id v:触发奖励次数
    private xbean.logic.GiftProbBean giftProbBean; 
    private xbean.logic.GiftWrightBean giftWrightBean; 
    private java.util.concurrent.ConcurrentHashMap<Integer, GiftLogList> giftLogItemList;  // 抽取记录
    private xbean.logic.PrizeWheelInfo prizeWheelInfo; 

    PlayerRandomGift(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.historyNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.drop2NumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.giftProbBean = new GiftProbBean(this);
		this.giftWrightBean = new GiftWrightBean(this);
		this.giftLogItemList = new java.util.concurrent.ConcurrentHashMap<Integer, GiftLogList>();
		this.prizeWheelInfo = new PrizeWheelInfo(this);
	}

	public PlayerRandomGift() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerRandomGift(PlayerRandomGift _o_) {
		this(_o_, null);
	}

	PlayerRandomGift(PlayerRandomGift _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.historyNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.historyNum.forEach((_k_, _v_) -> this.historyNum.put(_k_, _v_));
		this.drop2NumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.drop2NumMap.forEach((_k_, _v_) -> this.drop2NumMap.put(_k_, _v_));
		this.giftProbBean = new GiftProbBean(_o_.giftProbBean, this);
		this.giftWrightBean = new GiftWrightBean(_o_.giftWrightBean, this);
		this.giftLogItemList = new java.util.concurrent.ConcurrentHashMap<Integer, GiftLogList>();
		_o_.giftLogItemList.forEach((_k_, _v_) -> this.giftLogItemList.put(_k_, new GiftLogList(_v_)));
		this.prizeWheelInfo = new PrizeWheelInfo(_o_.prizeWheelInfo, this);
	}

	public void copyFrom(PlayerRandomGift _o_) {
        this.historyNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.historyNum.forEach((_k_, _v_) -> this.historyNum.put(_k_, _v_));
        this.drop2NumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.drop2NumMap.forEach((_k_, _v_) -> this.drop2NumMap.put(_k_, _v_));
        this.giftProbBean = new GiftProbBean(_o_.giftProbBean, this);
        this.giftWrightBean = new GiftWrightBean(_o_.giftWrightBean, this);
        this.giftLogItemList = new java.util.concurrent.ConcurrentHashMap<Integer, GiftLogList>();
		_o_.giftLogItemList.forEach((_k_, _v_) -> this.giftLogItemList.put(_k_, new GiftLogList(_v_)));
        this.prizeWheelInfo = new PrizeWheelInfo(_o_.prizeWheelInfo, this);
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getHistoryNum(){
		return this.historyNum;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDrop2NumMap(){
		return this.drop2NumMap;
	}

	@JSONField(name = "3")
	public xbean.logic.GiftProbBean getGiftProbBean(){
		return this.giftProbBean;
	}

	@JSONField(name = "4")
	public xbean.logic.GiftWrightBean getGiftWrightBean(){
		return this.giftWrightBean;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, GiftLogList> getGiftLogItemList(){
		return this.giftLogItemList;
	}

	@JSONField(name = "6")
	public xbean.logic.PrizeWheelInfo getPrizeWheelInfo(){
		return this.prizeWheelInfo;
	}

	@JSONField(name = "1")
	public void setHistoryNum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.historyNum = _v_;
	}

	@JSONField(name = "2")
	public void setDrop2NumMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.drop2NumMap = _v_;
	}

	@JSONField(name = "3")
	public void setGiftProbBean(xbean.logic.GiftProbBean _v_){
		this.giftProbBean = _v_;
	}

	@JSONField(name = "4")
	public void setGiftWrightBean(xbean.logic.GiftWrightBean _v_){
		this.giftWrightBean = _v_;
	}

	@JSONField(name = "5")
	public void setGiftLogItemList(java.util.concurrent.ConcurrentHashMap<Integer, GiftLogList> _v_){
		this.giftLogItemList = _v_;
	}

	@JSONField(name = "6")
	public void setPrizeWheelInfo(xbean.logic.PrizeWheelInfo _v_){
		this.prizeWheelInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerRandomGift) {
			PlayerRandomGift _o_ = (PlayerRandomGift)_o1_;
			if (!this.historyNum.equals(_o_.historyNum)) return false;
			if (!this.drop2NumMap.equals(_o_.drop2NumMap)) return false;
			if (!this.giftProbBean.equals(_o_.giftProbBean)) return false;
			if (!this.giftWrightBean.equals(_o_.giftWrightBean)) return false;
			if (!this.giftLogItemList.equals(_o_.giftLogItemList)) return false;
			if (!this.prizeWheelInfo.equals(_o_.prizeWheelInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.historyNum.hashCode();
		_h_ += _h_ * 31 + this.drop2NumMap.hashCode();
		_h_ += _h_ * 31 + this.giftProbBean.hashCode();
		_h_ += _h_ * 31 + this.giftWrightBean.hashCode();
		_h_ += _h_ * 31 + this.giftLogItemList.hashCode();
		_h_ += _h_ * 31 + this.prizeWheelInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(historyNum).append(",");
		_sb_.append(drop2NumMap).append(",");
		_sb_.append(giftProbBean).append(",");
		_sb_.append(giftWrightBean).append(",");
		_sb_.append(giftLogItemList).append(",");
		_sb_.append(prizeWheelInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
