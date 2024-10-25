package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class EquipmentMakingTable extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, EquipmentMakingInfo> makingMap; 

    EquipmentMakingTable(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.makingMap = new java.util.concurrent.ConcurrentHashMap<Long, EquipmentMakingInfo>();
	}

	public EquipmentMakingTable() {
		this((warrior.mdb.XBean)null);
	}

	public EquipmentMakingTable(EquipmentMakingTable _o_) {
		this(_o_, null);
	}

	EquipmentMakingTable(EquipmentMakingTable _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.makingMap = new java.util.concurrent.ConcurrentHashMap<Long, EquipmentMakingInfo>();
		_o_.makingMap.forEach((_k_, _v_) -> this.makingMap.put(_k_, new EquipmentMakingInfo(_v_)));
	}

	public void copyFrom(EquipmentMakingTable _o_) {
        this.makingMap = new java.util.concurrent.ConcurrentHashMap<Long, EquipmentMakingInfo>();
		_o_.makingMap.forEach((_k_, _v_) -> this.makingMap.put(_k_, new EquipmentMakingInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, EquipmentMakingInfo> getMakingMap(){
		return this.makingMap;
	}

	@JSONField(name = "1")
	public void setMakingMap(java.util.concurrent.ConcurrentHashMap<Long, EquipmentMakingInfo> _v_){
		this.makingMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EquipmentMakingTable) {
			EquipmentMakingTable _o_ = (EquipmentMakingTable)_o1_;
			if (!this.makingMap.equals(_o_.makingMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.makingMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(makingMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
