package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class ItemInfoBean extends warrior.mdb.XBean {
    private int id;  // 道具id
    private int num;  // 道具数量
    private int randomAttrCfgId;  // 随机属性表id

    ItemInfoBean(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public ItemInfoBean() {
		this((warrior.mdb.XBean)null);
	}

	public ItemInfoBean(ItemInfoBean _o_) {
		this(_o_, null);
	}

	ItemInfoBean(ItemInfoBean _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.num = _o_.num;
		this.randomAttrCfgId = _o_.randomAttrCfgId;
	}

	public void copyFrom(ItemInfoBean _o_) {
        this.id = _o_.id;
        this.num = _o_.num;
        this.randomAttrCfgId = _o_.randomAttrCfgId;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "3")
	public int getRandomAttrCfgId(){
		return this.randomAttrCfgId;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@JSONField(name = "3")
	public void setRandomAttrCfgId(int _v_){
		this.randomAttrCfgId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ItemInfoBean) {
			ItemInfoBean _o_ = (ItemInfoBean)_o1_;
			if (this.id != _o_.id) return false;
			if (this.num != _o_.num) return false;
			if (this.randomAttrCfgId != _o_.randomAttrCfgId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.num);
		_h_ += _h_ * 31 + (int)(this.randomAttrCfgId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(num).append(",");
		_sb_.append(randomAttrCfgId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
