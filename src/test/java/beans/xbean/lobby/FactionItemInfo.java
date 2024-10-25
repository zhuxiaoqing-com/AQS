package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionItemInfo extends warrior.mdb.XBean {
    private int itemId;  // 道具id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> qualityNum;  // 品质数量表

    FactionItemInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.qualityNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public FactionItemInfo() {
		this((warrior.mdb.XBean)null);
	}

	public FactionItemInfo(FactionItemInfo _o_) {
		this(_o_, null);
	}

	FactionItemInfo(FactionItemInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.itemId = _o_.itemId;
		this.qualityNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.qualityNum.forEach((_k_, _v_) -> this.qualityNum.put(_k_, _v_));
	}

	public void copyFrom(FactionItemInfo _o_) {
        this.itemId = _o_.itemId;
        this.qualityNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.qualityNum.forEach((_k_, _v_) -> this.qualityNum.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getItemId(){
		return this.itemId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getQualityNum(){
		return this.qualityNum;
	}

	@JSONField(name = "1")
	public void setItemId(int _v_){
		this.itemId = _v_;
	}

	@JSONField(name = "2")
	public void setQualityNum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.qualityNum = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionItemInfo) {
			FactionItemInfo _o_ = (FactionItemInfo)_o1_;
			if (this.itemId != _o_.itemId) return false;
			if (!this.qualityNum.equals(_o_.qualityNum)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.itemId);
		_h_ += _h_ * 31 + this.qualityNum.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(itemId).append(",");
		_sb_.append(qualityNum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
