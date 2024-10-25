package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerEquipWarehouse extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<Integer> unlockId; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> studyInfo;  // 学习经验

    PlayerEquipWarehouse(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.unlockId = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.studyInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerEquipWarehouse() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerEquipWarehouse(PlayerEquipWarehouse _o_) {
		this(_o_, null);
	}

	PlayerEquipWarehouse(PlayerEquipWarehouse _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.unlockId = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.unlockId.forEach(_v_ -> this.unlockId.add(_v_));
		this.studyInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.studyInfo.forEach((_k_, _v_) -> this.studyInfo.put(_k_, _v_));
	}

	public void copyFrom(PlayerEquipWarehouse _o_) {
        this.unlockId = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.unlockId.forEach(_v_ -> this.unlockId.add(_v_));
        this.studyInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.studyInfo.forEach((_k_, _v_) -> this.studyInfo.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getUnlockId(){
		return this.unlockId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getStudyInfo(){
		return this.studyInfo;
	}

	@JSONField(name = "1")
	public void setUnlockId(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.unlockId = _v_;
	}

	@JSONField(name = "2")
	public void setStudyInfo(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.studyInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerEquipWarehouse) {
			PlayerEquipWarehouse _o_ = (PlayerEquipWarehouse)_o1_;
			if (!this.unlockId.equals(_o_.unlockId)) return false;
			if (!this.studyInfo.equals(_o_.studyInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.unlockId.hashCode();
		_h_ += _h_ * 31 + this.studyInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(unlockId).append(",");
		_sb_.append(studyInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
