package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionItemMakeInfo extends warrior.mdb.XBean {
    private int itemId;  // 道具id
    private java.util.concurrent.ConcurrentHashMap<Long, MakingRoleInfo> roleMap;  // 当前正在制造的玩家数据
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionItemBuff> buffMap;  // buffMap

    FactionItemMakeInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.roleMap = new java.util.concurrent.ConcurrentHashMap<Long, MakingRoleInfo>();
		this.buffMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionItemBuff>();
	}

	public FactionItemMakeInfo() {
		this((warrior.mdb.XBean)null);
	}

	public FactionItemMakeInfo(FactionItemMakeInfo _o_) {
		this(_o_, null);
	}

	FactionItemMakeInfo(FactionItemMakeInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.itemId = _o_.itemId;
		this.roleMap = new java.util.concurrent.ConcurrentHashMap<Long, MakingRoleInfo>();
		_o_.roleMap.forEach((_k_, _v_) -> this.roleMap.put(_k_, new MakingRoleInfo(_v_)));
		this.buffMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionItemBuff>();
		_o_.buffMap.forEach((_k_, _v_) -> this.buffMap.put(_k_, new FactionItemBuff(_v_)));
	}

	public void copyFrom(FactionItemMakeInfo _o_) {
        this.itemId = _o_.itemId;
        this.roleMap = new java.util.concurrent.ConcurrentHashMap<Long, MakingRoleInfo>();
		_o_.roleMap.forEach((_k_, _v_) -> this.roleMap.put(_k_, new MakingRoleInfo(_v_)));
        this.buffMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionItemBuff>();
		_o_.buffMap.forEach((_k_, _v_) -> this.buffMap.put(_k_, new FactionItemBuff(_v_)));
	}


	@JSONField(name = "1")
	public int getItemId(){
		return this.itemId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, MakingRoleInfo> getRoleMap(){
		return this.roleMap;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionItemBuff> getBuffMap(){
		return this.buffMap;
	}

	@JSONField(name = "1")
	public void setItemId(int _v_){
		this.itemId = _v_;
	}

	@JSONField(name = "2")
	public void setRoleMap(java.util.concurrent.ConcurrentHashMap<Long, MakingRoleInfo> _v_){
		this.roleMap = _v_;
	}

	@JSONField(name = "3")
	public void setBuffMap(java.util.concurrent.ConcurrentHashMap<Integer, FactionItemBuff> _v_){
		this.buffMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionItemMakeInfo) {
			FactionItemMakeInfo _o_ = (FactionItemMakeInfo)_o1_;
			if (this.itemId != _o_.itemId) return false;
			if (!this.roleMap.equals(_o_.roleMap)) return false;
			if (!this.buffMap.equals(_o_.buffMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.itemId);
		_h_ += _h_ * 31 + this.roleMap.hashCode();
		_h_ += _h_ * 31 + this.buffMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(itemId).append(",");
		_sb_.append(roleMap).append(",");
		_sb_.append(buffMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
