package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerTalentInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo> talentMap;  // 天赋Map
    private int activateType;  // 激活的天赋类型
    private java.util.concurrent.ConcurrentHashMap<Integer, ActionRecord> actionMap;  // 行为数据
    private long minLevelEndMill;  // 天赋最小等级结束毫秒值
    private java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo> tempSaveTalentMap;  // 临时保存的天赋Map 进入职业副本使用
    private int tempActivateType;  // 临时保存的天赋Map 进入职业副本使用
    private java.util.concurrent.CopyOnWriteArraySet<Integer> unlockTypeSet;  // 解锁的天赋类型
    private int changeTypeNum;  // 今日已经切换天赋次数
    private long lastRefresh;  // 上一次刷新时间
    private int resetNum;  // 今日已经重置天赋n次

    PlayerTalentInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.talentMap = new java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo>();
		this.actionMap = new java.util.concurrent.ConcurrentHashMap<Integer, ActionRecord>();
		this.tempSaveTalentMap = new java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo>();
		this.unlockTypeSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public PlayerTalentInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerTalentInfo(PlayerTalentInfo _o_) {
		this(_o_, null);
	}

	PlayerTalentInfo(PlayerTalentInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.talentMap = new java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo>();
		_o_.talentMap.forEach((_k_, _v_) -> this.talentMap.put(_k_, new TalentInfo(_v_)));
		this.activateType = _o_.activateType;
		this.actionMap = new java.util.concurrent.ConcurrentHashMap<Integer, ActionRecord>();
		_o_.actionMap.forEach((_k_, _v_) -> this.actionMap.put(_k_, new ActionRecord(_v_)));
		this.minLevelEndMill = _o_.minLevelEndMill;
		this.tempSaveTalentMap = new java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo>();
		_o_.tempSaveTalentMap.forEach((_k_, _v_) -> this.tempSaveTalentMap.put(_k_, new TalentInfo(_v_)));
		this.tempActivateType = _o_.tempActivateType;
		this.unlockTypeSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.unlockTypeSet.forEach(_v_ -> this.unlockTypeSet.add(_v_));
		this.changeTypeNum = _o_.changeTypeNum;
		this.lastRefresh = _o_.lastRefresh;
		this.resetNum = _o_.resetNum;
	}

	public void copyFrom(PlayerTalentInfo _o_) {
        this.talentMap = new java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo>();
		_o_.talentMap.forEach((_k_, _v_) -> this.talentMap.put(_k_, new TalentInfo(_v_)));
        this.activateType = _o_.activateType;
        this.actionMap = new java.util.concurrent.ConcurrentHashMap<Integer, ActionRecord>();
		_o_.actionMap.forEach((_k_, _v_) -> this.actionMap.put(_k_, new ActionRecord(_v_)));
        this.minLevelEndMill = _o_.minLevelEndMill;
        this.tempSaveTalentMap = new java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo>();
		_o_.tempSaveTalentMap.forEach((_k_, _v_) -> this.tempSaveTalentMap.put(_k_, new TalentInfo(_v_)));
        this.tempActivateType = _o_.tempActivateType;
        this.unlockTypeSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.unlockTypeSet.forEach(_v_ -> this.unlockTypeSet.add(_v_));
        this.changeTypeNum = _o_.changeTypeNum;
        this.lastRefresh = _o_.lastRefresh;
        this.resetNum = _o_.resetNum;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo> getTalentMap(){
		return this.talentMap;
	}

	@JSONField(name = "2")
	public int getActivateType(){
		return this.activateType;
	}

	@JSONField(name = "5")
	public java.util.concurrent.ConcurrentHashMap<Integer, ActionRecord> getActionMap(){
		return this.actionMap;
	}

	@JSONField(name = "6")
	public long getMinLevelEndMill(){
		return this.minLevelEndMill;
	}

	@JSONField(name = "7")
	public java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo> getTempSaveTalentMap(){
		return this.tempSaveTalentMap;
	}

	@JSONField(name = "8")
	public int getTempActivateType(){
		return this.tempActivateType;
	}

	@JSONField(name = "9")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getUnlockTypeSet(){
		return this.unlockTypeSet;
	}

	@JSONField(name = "10")
	public int getChangeTypeNum(){
		return this.changeTypeNum;
	}

	@JSONField(name = "11")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "12")
	public int getResetNum(){
		return this.resetNum;
	}

	@JSONField(name = "1")
	public void setTalentMap(java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo> _v_){
		this.talentMap = _v_;
	}

	@JSONField(name = "2")
	public void setActivateType(int _v_){
		this.activateType = _v_;
	}

	@JSONField(name = "5")
	public void setActionMap(java.util.concurrent.ConcurrentHashMap<Integer, ActionRecord> _v_){
		this.actionMap = _v_;
	}

	@JSONField(name = "6")
	public void setMinLevelEndMill(long _v_){
		this.minLevelEndMill = _v_;
	}

	@JSONField(name = "7")
	public void setTempSaveTalentMap(java.util.concurrent.ConcurrentHashMap<Integer, TalentInfo> _v_){
		this.tempSaveTalentMap = _v_;
	}

	@JSONField(name = "8")
	public void setTempActivateType(int _v_){
		this.tempActivateType = _v_;
	}

	@JSONField(name = "9")
	public void setUnlockTypeSet(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.unlockTypeSet = _v_;
	}

	@JSONField(name = "10")
	public void setChangeTypeNum(int _v_){
		this.changeTypeNum = _v_;
	}

	@JSONField(name = "11")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@JSONField(name = "12")
	public void setResetNum(int _v_){
		this.resetNum = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerTalentInfo) {
			PlayerTalentInfo _o_ = (PlayerTalentInfo)_o1_;
			if (!this.talentMap.equals(_o_.talentMap)) return false;
			if (this.activateType != _o_.activateType) return false;
			if (!this.actionMap.equals(_o_.actionMap)) return false;
			if (this.minLevelEndMill != _o_.minLevelEndMill) return false;
			if (!this.tempSaveTalentMap.equals(_o_.tempSaveTalentMap)) return false;
			if (this.tempActivateType != _o_.tempActivateType) return false;
			if (!this.unlockTypeSet.equals(_o_.unlockTypeSet)) return false;
			if (this.changeTypeNum != _o_.changeTypeNum) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			if (this.resetNum != _o_.resetNum) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.talentMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.activateType);
		_h_ += _h_ * 31 + this.actionMap.hashCode();
		_h_ += _h_ * 31 + (int)(minLevelEndMill ^ (minLevelEndMill >>> 32));
		_h_ += _h_ * 31 + this.tempSaveTalentMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.tempActivateType);
		_h_ += _h_ * 31 + this.unlockTypeSet.hashCode();
		_h_ += _h_ * 31 + (int)(this.changeTypeNum);
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.resetNum);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(talentMap).append(",");
		_sb_.append(activateType).append(",");
		_sb_.append(actionMap).append(",");
		_sb_.append(minLevelEndMill).append(",");
		_sb_.append(tempSaveTalentMap).append(",");
		_sb_.append(tempActivateType).append(",");
		_sb_.append(unlockTypeSet).append(",");
		_sb_.append(changeTypeNum).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(resetNum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
