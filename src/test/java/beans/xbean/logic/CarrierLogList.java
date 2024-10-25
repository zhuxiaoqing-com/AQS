package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class CarrierLogList extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<CarrierLog> logList; 
    private java.util.concurrent.CopyOnWriteArrayList<CarrierLog> repairHelpLogList;  // 修理帮助日志
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> killCarrierMap;  // 击杀载具map type->num
    private int killRole;  // 击杀玩家数量
    private int killGuard;  // 击杀护卫数量

    CarrierLogList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.logList = new java.util.concurrent.CopyOnWriteArrayList<CarrierLog>();
		this.repairHelpLogList = new java.util.concurrent.CopyOnWriteArrayList<CarrierLog>();
		this.killCarrierMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public CarrierLogList() {
		this((warrior.mdb.XBean)null);
	}

	public CarrierLogList(CarrierLogList _o_) {
		this(_o_, null);
	}

	CarrierLogList(CarrierLogList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.logList = new java.util.concurrent.CopyOnWriteArrayList<CarrierLog>();
		_o_.logList.forEach(_v_ -> this.logList.add(new CarrierLog(_v_)));
		this.repairHelpLogList = new java.util.concurrent.CopyOnWriteArrayList<CarrierLog>();
		_o_.repairHelpLogList.forEach(_v_ -> this.repairHelpLogList.add(new CarrierLog(_v_)));
		this.killCarrierMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.killCarrierMap.forEach((_k_, _v_) -> this.killCarrierMap.put(_k_, _v_));
		this.killRole = _o_.killRole;
		this.killGuard = _o_.killGuard;
	}

	public void copyFrom(CarrierLogList _o_) {
        this.logList = new java.util.concurrent.CopyOnWriteArrayList<CarrierLog>();
		_o_.logList.forEach(_v_ -> this.logList.add(new CarrierLog(_v_)));
        this.repairHelpLogList = new java.util.concurrent.CopyOnWriteArrayList<CarrierLog>();
		_o_.repairHelpLogList.forEach(_v_ -> this.repairHelpLogList.add(new CarrierLog(_v_)));
        this.killCarrierMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.killCarrierMap.forEach((_k_, _v_) -> this.killCarrierMap.put(_k_, _v_));
        this.killRole = _o_.killRole;
        this.killGuard = _o_.killGuard;
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<CarrierLog> getLogList(){
		return this.logList;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<CarrierLog> getRepairHelpLogList(){
		return this.repairHelpLogList;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getKillCarrierMap(){
		return this.killCarrierMap;
	}

	@JSONField(name = "5")
	public int getKillRole(){
		return this.killRole;
	}

	@JSONField(name = "6")
	public int getKillGuard(){
		return this.killGuard;
	}

	@JSONField(name = "1")
	public void setLogList(java.util.concurrent.CopyOnWriteArrayList<CarrierLog> _v_){
		this.logList = _v_;
	}

	@JSONField(name = "3")
	public void setRepairHelpLogList(java.util.concurrent.CopyOnWriteArrayList<CarrierLog> _v_){
		this.repairHelpLogList = _v_;
	}

	@JSONField(name = "4")
	public void setKillCarrierMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.killCarrierMap = _v_;
	}

	@JSONField(name = "5")
	public void setKillRole(int _v_){
		this.killRole = _v_;
	}

	@JSONField(name = "6")
	public void setKillGuard(int _v_){
		this.killGuard = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CarrierLogList) {
			CarrierLogList _o_ = (CarrierLogList)_o1_;
			if (!this.logList.equals(_o_.logList)) return false;
			if (!this.repairHelpLogList.equals(_o_.repairHelpLogList)) return false;
			if (!this.killCarrierMap.equals(_o_.killCarrierMap)) return false;
			if (this.killRole != _o_.killRole) return false;
			if (this.killGuard != _o_.killGuard) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.logList.hashCode();
		_h_ += _h_ * 31 + this.repairHelpLogList.hashCode();
		_h_ += _h_ * 31 + this.killCarrierMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.killRole);
		_h_ += _h_ * 31 + (int)(this.killGuard);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(logList).append(",");
		_sb_.append(repairHelpLogList).append(",");
		_sb_.append(killCarrierMap).append(",");
		_sb_.append(killRole).append(",");
		_sb_.append(killGuard).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
