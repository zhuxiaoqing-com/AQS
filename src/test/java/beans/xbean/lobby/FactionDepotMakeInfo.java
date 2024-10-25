package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionDepotMakeInfo extends warrior.mdb.XBean {
    private int fid;  // 阵营id
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionItemMakeInfo> itemMakeInfoMap;  // itemId->makeInfo

    FactionDepotMakeInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.itemMakeInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionItemMakeInfo>();
	}

	public FactionDepotMakeInfo() {
		this((warrior.mdb.XBean)null);
	}

	public FactionDepotMakeInfo(FactionDepotMakeInfo _o_) {
		this(_o_, null);
	}

	FactionDepotMakeInfo(FactionDepotMakeInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.fid = _o_.fid;
		this.itemMakeInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionItemMakeInfo>();
		_o_.itemMakeInfoMap.forEach((_k_, _v_) -> this.itemMakeInfoMap.put(_k_, new FactionItemMakeInfo(_v_)));
	}

	public void copyFrom(FactionDepotMakeInfo _o_) {
        this.fid = _o_.fid;
        this.itemMakeInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionItemMakeInfo>();
		_o_.itemMakeInfoMap.forEach((_k_, _v_) -> this.itemMakeInfoMap.put(_k_, new FactionItemMakeInfo(_v_)));
	}


	@JSONField(name = "1")
	public int getFid(){
		return this.fid;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionItemMakeInfo> getItemMakeInfoMap(){
		return this.itemMakeInfoMap;
	}

	@JSONField(name = "1")
	public void setFid(int _v_){
		this.fid = _v_;
	}

	@JSONField(name = "2")
	public void setItemMakeInfoMap(java.util.concurrent.ConcurrentHashMap<Integer, FactionItemMakeInfo> _v_){
		this.itemMakeInfoMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionDepotMakeInfo) {
			FactionDepotMakeInfo _o_ = (FactionDepotMakeInfo)_o1_;
			if (this.fid != _o_.fid) return false;
			if (!this.itemMakeInfoMap.equals(_o_.itemMakeInfoMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.fid);
		_h_ += _h_ * 31 + this.itemMakeInfoMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(fid).append(",");
		_sb_.append(itemMakeInfoMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
