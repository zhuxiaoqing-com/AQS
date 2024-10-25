package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class GiftLogList extends warrior.mdb.XBean {
    private int giftItemId; 
    private java.util.concurrent.CopyOnWriteArrayList<GiftLog> giftLogList;  // 抽取记录

    GiftLogList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.giftLogList = new java.util.concurrent.CopyOnWriteArrayList<GiftLog>();
	}

	public GiftLogList() {
		this((warrior.mdb.XBean)null);
	}

	public GiftLogList(GiftLogList _o_) {
		this(_o_, null);
	}

	GiftLogList(GiftLogList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.giftItemId = _o_.giftItemId;
		this.giftLogList = new java.util.concurrent.CopyOnWriteArrayList<GiftLog>();
		_o_.giftLogList.forEach(_v_ -> this.giftLogList.add(new GiftLog(_v_)));
	}

	public void copyFrom(GiftLogList _o_) {
        this.giftItemId = _o_.giftItemId;
        this.giftLogList = new java.util.concurrent.CopyOnWriteArrayList<GiftLog>();
		_o_.giftLogList.forEach(_v_ -> this.giftLogList.add(new GiftLog(_v_)));
	}


	@JSONField(name = "1")
	public int getGiftItemId(){
		return this.giftItemId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<GiftLog> getGiftLogList(){
		return this.giftLogList;
	}

	@JSONField(name = "1")
	public void setGiftItemId(int _v_){
		this.giftItemId = _v_;
	}

	@JSONField(name = "2")
	public void setGiftLogList(java.util.concurrent.CopyOnWriteArrayList<GiftLog> _v_){
		this.giftLogList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GiftLogList) {
			GiftLogList _o_ = (GiftLogList)_o1_;
			if (this.giftItemId != _o_.giftItemId) return false;
			if (!this.giftLogList.equals(_o_.giftLogList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.giftItemId);
		_h_ += _h_ * 31 + this.giftLogList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(giftItemId).append(",");
		_sb_.append(giftLogList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
