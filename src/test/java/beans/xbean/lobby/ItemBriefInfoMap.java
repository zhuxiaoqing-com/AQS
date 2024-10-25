package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ItemBriefInfoMap extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, ItemBriefInfo> itemBriefInfoMap;  // 阵营仓库道具简略map

    ItemBriefInfoMap(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.itemBriefInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, ItemBriefInfo>();
	}

	public ItemBriefInfoMap() {
		this((warrior.mdb.XBean)null);
	}

	public ItemBriefInfoMap(ItemBriefInfoMap _o_) {
		this(_o_, null);
	}

	ItemBriefInfoMap(ItemBriefInfoMap _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.itemBriefInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, ItemBriefInfo>();
		_o_.itemBriefInfoMap.forEach((_k_, _v_) -> this.itemBriefInfoMap.put(_k_, new ItemBriefInfo(_v_)));
	}

	public void copyFrom(ItemBriefInfoMap _o_) {
        this.itemBriefInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, ItemBriefInfo>();
		_o_.itemBriefInfoMap.forEach((_k_, _v_) -> this.itemBriefInfoMap.put(_k_, new ItemBriefInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, ItemBriefInfo> getItemBriefInfoMap(){
		return this.itemBriefInfoMap;
	}

	@JSONField(name = "1")
	public void setItemBriefInfoMap(java.util.concurrent.ConcurrentHashMap<Integer, ItemBriefInfo> _v_){
		this.itemBriefInfoMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ItemBriefInfoMap) {
			ItemBriefInfoMap _o_ = (ItemBriefInfoMap)_o1_;
			if (!this.itemBriefInfoMap.equals(_o_.itemBriefInfoMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.itemBriefInfoMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(itemBriefInfoMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
