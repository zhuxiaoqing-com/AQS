package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ItemBriefInfo extends warrior.mdb.XBean {
    private int itemId;  // 道具id
    private int addItemNum;  // 添加道具num
    private int removeItemNum;  // 删除道具num

    ItemBriefInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public ItemBriefInfo() {
		this((warrior.mdb.XBean)null);
	}

	public ItemBriefInfo(ItemBriefInfo _o_) {
		this(_o_, null);
	}

	ItemBriefInfo(ItemBriefInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.itemId = _o_.itemId;
		this.addItemNum = _o_.addItemNum;
		this.removeItemNum = _o_.removeItemNum;
	}

	public void copyFrom(ItemBriefInfo _o_) {
        this.itemId = _o_.itemId;
        this.addItemNum = _o_.addItemNum;
        this.removeItemNum = _o_.removeItemNum;
	}


	@JSONField(name = "2")
	public int getItemId(){
		return this.itemId;
	}

	@JSONField(name = "3")
	public int getAddItemNum(){
		return this.addItemNum;
	}

	@JSONField(name = "4")
	public int getRemoveItemNum(){
		return this.removeItemNum;
	}

	@JSONField(name = "2")
	public void setItemId(int _v_){
		this.itemId = _v_;
	}

	@JSONField(name = "3")
	public void setAddItemNum(int _v_){
		this.addItemNum = _v_;
	}

	@JSONField(name = "4")
	public void setRemoveItemNum(int _v_){
		this.removeItemNum = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ItemBriefInfo) {
			ItemBriefInfo _o_ = (ItemBriefInfo)_o1_;
			if (this.itemId != _o_.itemId) return false;
			if (this.addItemNum != _o_.addItemNum) return false;
			if (this.removeItemNum != _o_.removeItemNum) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.itemId);
		_h_ += _h_ * 31 + (int)(this.addItemNum);
		_h_ += _h_ * 31 + (int)(this.removeItemNum);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(itemId).append(",");
		_sb_.append(addItemNum).append(",");
		_sb_.append(removeItemNum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
