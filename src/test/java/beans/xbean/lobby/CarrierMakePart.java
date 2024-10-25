package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CarrierMakePart extends warrior.mdb.XBean {
    private int id;  // partId
    private int progress;  // 百分比
    private java.util.concurrent.ConcurrentHashMap<Long, AssistPlayer> playerAssistMap;  // 协助玩家
    private boolean pause;  // 是否是暂停中
    private long passTime;  // 已经经过时间
    private long lastCalcTime;  // 上一次计算时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> donateItemMap;  // 捐献过来的资源
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> ownerDonateItemMap;  // 自己捐献过来的资源
    private long finishNeedTime;  // 完成需要时间
    private int donateProgress;  // 捐献百分比

    CarrierMakePart(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerAssistMap = new java.util.concurrent.ConcurrentHashMap<Long, AssistPlayer>();
		this.donateItemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.ownerDonateItemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public CarrierMakePart() {
		this((warrior.mdb.XBean)null);
	}

	public CarrierMakePart(CarrierMakePart _o_) {
		this(_o_, null);
	}

	CarrierMakePart(CarrierMakePart _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.progress = _o_.progress;
		this.playerAssistMap = new java.util.concurrent.ConcurrentHashMap<Long, AssistPlayer>();
		_o_.playerAssistMap.forEach((_k_, _v_) -> this.playerAssistMap.put(_k_, new AssistPlayer(_v_)));
		this.pause = _o_.pause;
		this.passTime = _o_.passTime;
		this.lastCalcTime = _o_.lastCalcTime;
		this.donateItemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.donateItemMap.forEach((_k_, _v_) -> this.donateItemMap.put(_k_, _v_));
		this.ownerDonateItemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.ownerDonateItemMap.forEach((_k_, _v_) -> this.ownerDonateItemMap.put(_k_, _v_));
		this.finishNeedTime = _o_.finishNeedTime;
		this.donateProgress = _o_.donateProgress;
	}

	public void copyFrom(CarrierMakePart _o_) {
        this.id = _o_.id;
        this.progress = _o_.progress;
        this.playerAssistMap = new java.util.concurrent.ConcurrentHashMap<Long, AssistPlayer>();
		_o_.playerAssistMap.forEach((_k_, _v_) -> this.playerAssistMap.put(_k_, new AssistPlayer(_v_)));
        this.pause = _o_.pause;
        this.passTime = _o_.passTime;
        this.lastCalcTime = _o_.lastCalcTime;
        this.donateItemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.donateItemMap.forEach((_k_, _v_) -> this.donateItemMap.put(_k_, _v_));
        this.ownerDonateItemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.ownerDonateItemMap.forEach((_k_, _v_) -> this.ownerDonateItemMap.put(_k_, _v_));
        this.finishNeedTime = _o_.finishNeedTime;
        this.donateProgress = _o_.donateProgress;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getProgress(){
		return this.progress;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Long, AssistPlayer> getPlayerAssistMap(){
		return this.playerAssistMap;
	}

	@JSONField(name = "4")
	public boolean getPause(){
		return this.pause;
	}

	@JSONField(name = "6")
	public long getPassTime(){
		return this.passTime;
	}

	@JSONField(name = "7")
	public long getLastCalcTime(){
		return this.lastCalcTime;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDonateItemMap(){
		return this.donateItemMap;
	}

	@JSONField(name = "9")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getOwnerDonateItemMap(){
		return this.ownerDonateItemMap;
	}

	@JSONField(name = "10")
	public long getFinishNeedTime(){
		return this.finishNeedTime;
	}

	@JSONField(name = "11")
	public int getDonateProgress(){
		return this.donateProgress;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setProgress(int _v_){
		this.progress = _v_;
	}

	@JSONField(name = "3")
	public void setPlayerAssistMap(java.util.concurrent.ConcurrentHashMap<Long, AssistPlayer> _v_){
		this.playerAssistMap = _v_;
	}

	@JSONField(name = "4")
	public void setPause(boolean _v_){
		this.pause = _v_;
	}

	@JSONField(name = "6")
	public void setPassTime(long _v_){
		this.passTime = _v_;
	}

	@JSONField(name = "7")
	public void setLastCalcTime(long _v_){
		this.lastCalcTime = _v_;
	}

	@JSONField(name = "8")
	public void setDonateItemMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.donateItemMap = _v_;
	}

	@JSONField(name = "9")
	public void setOwnerDonateItemMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.ownerDonateItemMap = _v_;
	}

	@JSONField(name = "10")
	public void setFinishNeedTime(long _v_){
		this.finishNeedTime = _v_;
	}

	@JSONField(name = "11")
	public void setDonateProgress(int _v_){
		this.donateProgress = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CarrierMakePart) {
			CarrierMakePart _o_ = (CarrierMakePart)_o1_;
			if (this.id != _o_.id) return false;
			if (this.progress != _o_.progress) return false;
			if (!this.playerAssistMap.equals(_o_.playerAssistMap)) return false;
			if (this.pause != _o_.pause) return false;
			if (this.passTime != _o_.passTime) return false;
			if (this.lastCalcTime != _o_.lastCalcTime) return false;
			if (!this.donateItemMap.equals(_o_.donateItemMap)) return false;
			if (!this.ownerDonateItemMap.equals(_o_.ownerDonateItemMap)) return false;
			if (this.finishNeedTime != _o_.finishNeedTime) return false;
			if (this.donateProgress != _o_.donateProgress) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.progress);
		_h_ += _h_ * 31 + this.playerAssistMap.hashCode();
		_h_ += _h_ * 31 + (this.pause ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(passTime ^ (passTime >>> 32));
		_h_ += _h_ * 31 + (int)(lastCalcTime ^ (lastCalcTime >>> 32));
		_h_ += _h_ * 31 + this.donateItemMap.hashCode();
		_h_ += _h_ * 31 + this.ownerDonateItemMap.hashCode();
		_h_ += _h_ * 31 + (int)(finishNeedTime ^ (finishNeedTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.donateProgress);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(progress).append(",");
		_sb_.append(playerAssistMap).append(",");
		_sb_.append(pause).append(",");
		_sb_.append(passTime).append(",");
		_sb_.append(lastCalcTime).append(",");
		_sb_.append(donateItemMap).append(",");
		_sb_.append(ownerDonateItemMap).append(",");
		_sb_.append(finishNeedTime).append(",");
		_sb_.append(donateProgress).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
