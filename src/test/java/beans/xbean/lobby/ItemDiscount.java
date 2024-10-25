package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ItemDiscount extends warrior.mdb.XBean {
    private int itemId;  // 道具id
    private int sellNum;  // 玩家卖的数量
    private int buyNum;  // 玩家买的数量
    private int buySellDiscount;  // 买入卖出折扣
    private java.util.concurrent.CopyOnWriteArrayList<Integer> historyDiscount;  // 历史折扣

    ItemDiscount(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.historyDiscount = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public ItemDiscount() {
		this((warrior.mdb.XBean)null);
	}

	public ItemDiscount(ItemDiscount _o_) {
		this(_o_, null);
	}

	ItemDiscount(ItemDiscount _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.itemId = _o_.itemId;
		this.sellNum = _o_.sellNum;
		this.buyNum = _o_.buyNum;
		this.buySellDiscount = _o_.buySellDiscount;
		this.historyDiscount = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.historyDiscount.forEach(_v_ -> this.historyDiscount.add(_v_));
	}

	public void copyFrom(ItemDiscount _o_) {
        this.itemId = _o_.itemId;
        this.sellNum = _o_.sellNum;
        this.buyNum = _o_.buyNum;
        this.buySellDiscount = _o_.buySellDiscount;
        this.historyDiscount = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.historyDiscount.forEach(_v_ -> this.historyDiscount.add(_v_));
	}


	@JSONField(name = "1")
	public int getItemId(){
		return this.itemId;
	}

	@JSONField(name = "2")
	public int getSellNum(){
		return this.sellNum;
	}

	@JSONField(name = "3")
	public int getBuyNum(){
		return this.buyNum;
	}

	@JSONField(name = "4")
	public int getBuySellDiscount(){
		return this.buySellDiscount;
	}

	@JSONField(name = "5")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getHistoryDiscount(){
		return this.historyDiscount;
	}

	@JSONField(name = "1")
	public void setItemId(int _v_){
		this.itemId = _v_;
	}

	@JSONField(name = "2")
	public void setSellNum(int _v_){
		this.sellNum = _v_;
	}

	@JSONField(name = "3")
	public void setBuyNum(int _v_){
		this.buyNum = _v_;
	}

	@JSONField(name = "4")
	public void setBuySellDiscount(int _v_){
		this.buySellDiscount = _v_;
	}

	@JSONField(name = "5")
	public void setHistoryDiscount(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.historyDiscount = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ItemDiscount) {
			ItemDiscount _o_ = (ItemDiscount)_o1_;
			if (this.itemId != _o_.itemId) return false;
			if (this.sellNum != _o_.sellNum) return false;
			if (this.buyNum != _o_.buyNum) return false;
			if (this.buySellDiscount != _o_.buySellDiscount) return false;
			if (!this.historyDiscount.equals(_o_.historyDiscount)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.itemId);
		_h_ += _h_ * 31 + (int)(this.sellNum);
		_h_ += _h_ * 31 + (int)(this.buyNum);
		_h_ += _h_ * 31 + (int)(this.buySellDiscount);
		_h_ += _h_ * 31 + this.historyDiscount.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(itemId).append(",");
		_sb_.append(sellNum).append(",");
		_sb_.append(buyNum).append(",");
		_sb_.append(buySellDiscount).append(",");
		_sb_.append(historyDiscount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
