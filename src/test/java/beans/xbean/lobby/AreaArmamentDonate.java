package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AreaArmamentDonate extends warrior.mdb.XBean {
    private int flagId;  // 旗帜Id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> donateType2NumMap;  // 阵营捐献type->次数
    private int donateRoleNum;  // 战前捐献人数
    private int donateTotalArmament;  // 战前捐献战备点累计
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> donateTotalResMap;  // 捐献产生资源量
    private int collectRoleNum;  // 战前采集人数
    private int collectTotalArmament;  // 战前采集战备点累计
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> collectTotalResMap;  // 采集产生资源量

    AreaArmamentDonate(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.donateType2NumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.donateTotalResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.collectTotalResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public AreaArmamentDonate() {
		this((warrior.mdb.XBean)null);
	}

	public AreaArmamentDonate(AreaArmamentDonate _o_) {
		this(_o_, null);
	}

	AreaArmamentDonate(AreaArmamentDonate _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.flagId = _o_.flagId;
		this.donateType2NumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.donateType2NumMap.forEach((_k_, _v_) -> this.donateType2NumMap.put(_k_, _v_));
		this.donateRoleNum = _o_.donateRoleNum;
		this.donateTotalArmament = _o_.donateTotalArmament;
		this.donateTotalResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.donateTotalResMap.forEach((_k_, _v_) -> this.donateTotalResMap.put(_k_, _v_));
		this.collectRoleNum = _o_.collectRoleNum;
		this.collectTotalArmament = _o_.collectTotalArmament;
		this.collectTotalResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectTotalResMap.forEach((_k_, _v_) -> this.collectTotalResMap.put(_k_, _v_));
	}

	public void copyFrom(AreaArmamentDonate _o_) {
        this.flagId = _o_.flagId;
        this.donateType2NumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.donateType2NumMap.forEach((_k_, _v_) -> this.donateType2NumMap.put(_k_, _v_));
        this.donateRoleNum = _o_.donateRoleNum;
        this.donateTotalArmament = _o_.donateTotalArmament;
        this.donateTotalResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.donateTotalResMap.forEach((_k_, _v_) -> this.donateTotalResMap.put(_k_, _v_));
        this.collectRoleNum = _o_.collectRoleNum;
        this.collectTotalArmament = _o_.collectTotalArmament;
        this.collectTotalResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectTotalResMap.forEach((_k_, _v_) -> this.collectTotalResMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getFlagId(){
		return this.flagId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDonateType2NumMap(){
		return this.donateType2NumMap;
	}

	@JSONField(name = "3")
	public int getDonateRoleNum(){
		return this.donateRoleNum;
	}

	@JSONField(name = "4")
	public int getDonateTotalArmament(){
		return this.donateTotalArmament;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDonateTotalResMap(){
		return this.donateTotalResMap;
	}

	@JSONField(name = "6")
	public int getCollectRoleNum(){
		return this.collectRoleNum;
	}

	@JSONField(name = "7")
	public int getCollectTotalArmament(){
		return this.collectTotalArmament;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCollectTotalResMap(){
		return this.collectTotalResMap;
	}

	@JSONField(name = "1")
	public void setFlagId(int _v_){
		this.flagId = _v_;
	}

	@JSONField(name = "2")
	public void setDonateType2NumMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.donateType2NumMap = _v_;
	}

	@JSONField(name = "3")
	public void setDonateRoleNum(int _v_){
		this.donateRoleNum = _v_;
	}

	@JSONField(name = "4")
	public void setDonateTotalArmament(int _v_){
		this.donateTotalArmament = _v_;
	}

	@JSONField(name = "5")
	public void setDonateTotalResMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.donateTotalResMap = _v_;
	}

	@JSONField(name = "6")
	public void setCollectRoleNum(int _v_){
		this.collectRoleNum = _v_;
	}

	@JSONField(name = "7")
	public void setCollectTotalArmament(int _v_){
		this.collectTotalArmament = _v_;
	}

	@JSONField(name = "8")
	public void setCollectTotalResMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.collectTotalResMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AreaArmamentDonate) {
			AreaArmamentDonate _o_ = (AreaArmamentDonate)_o1_;
			if (this.flagId != _o_.flagId) return false;
			if (!this.donateType2NumMap.equals(_o_.donateType2NumMap)) return false;
			if (this.donateRoleNum != _o_.donateRoleNum) return false;
			if (this.donateTotalArmament != _o_.donateTotalArmament) return false;
			if (!this.donateTotalResMap.equals(_o_.donateTotalResMap)) return false;
			if (this.collectRoleNum != _o_.collectRoleNum) return false;
			if (this.collectTotalArmament != _o_.collectTotalArmament) return false;
			if (!this.collectTotalResMap.equals(_o_.collectTotalResMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.flagId);
		_h_ += _h_ * 31 + this.donateType2NumMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.donateRoleNum);
		_h_ += _h_ * 31 + (int)(this.donateTotalArmament);
		_h_ += _h_ * 31 + this.donateTotalResMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.collectRoleNum);
		_h_ += _h_ * 31 + (int)(this.collectTotalArmament);
		_h_ += _h_ * 31 + this.collectTotalResMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(flagId).append(",");
		_sb_.append(donateType2NumMap).append(",");
		_sb_.append(donateRoleNum).append(",");
		_sb_.append(donateTotalArmament).append(",");
		_sb_.append(donateTotalResMap).append(",");
		_sb_.append(collectRoleNum).append(",");
		_sb_.append(collectTotalArmament).append(",");
		_sb_.append(collectTotalResMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
