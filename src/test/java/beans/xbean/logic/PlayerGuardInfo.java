package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerGuardInfo extends warrior.mdb.XBean {
    private int id; 
    private xbean.logic.Bag bag; 
    private xbean.logic.PlayerSkilledExpInfo skilledInfo; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> talentMap; 
    private int level;  // 军衔等级
    private int curSuitBag;  // 当前使用的套装
    private int hp;  // 当前血量
    private int curHonor;  // 当前荣誉
    private int wpos;  // 当前持有槽位
    private int dailyHonor;  // 当日获取的荣誉值
    private int deathCount;  // 死亡次数
    private long deathCd;  // 死亡满了以后的CD时间
    private java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyHistory> history;  // 历史记录
    private String name;  // 护卫名字

    PlayerGuardInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.bag = new Bag(this);
		this.skilledInfo = new PlayerSkilledExpInfo(this);
		this.talentMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.history = new java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyHistory>();
		this.name = "";
	}

	public PlayerGuardInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerGuardInfo(PlayerGuardInfo _o_) {
		this(_o_, null);
	}

	PlayerGuardInfo(PlayerGuardInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.bag = new Bag(_o_.bag, this);
		this.skilledInfo = new PlayerSkilledExpInfo(_o_.skilledInfo, this);
		this.talentMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.talentMap.forEach((_k_, _v_) -> this.talentMap.put(_k_, _v_));
		this.level = _o_.level;
		this.curSuitBag = _o_.curSuitBag;
		this.hp = _o_.hp;
		this.curHonor = _o_.curHonor;
		this.wpos = _o_.wpos;
		this.dailyHonor = _o_.dailyHonor;
		this.deathCount = _o_.deathCount;
		this.deathCd = _o_.deathCd;
		this.history = new java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyHistory>();
		_o_.history.forEach(_v_ -> this.history.add(new PlayerGuardSupplyHistory(_v_)));
		this.name = _o_.name;
	}

	public void copyFrom(PlayerGuardInfo _o_) {
        this.id = _o_.id;
        this.bag = new Bag(_o_.bag, this);
        this.skilledInfo = new PlayerSkilledExpInfo(_o_.skilledInfo, this);
        this.talentMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.talentMap.forEach((_k_, _v_) -> this.talentMap.put(_k_, _v_));
        this.level = _o_.level;
        this.curSuitBag = _o_.curSuitBag;
        this.hp = _o_.hp;
        this.curHonor = _o_.curHonor;
        this.wpos = _o_.wpos;
        this.dailyHonor = _o_.dailyHonor;
        this.deathCount = _o_.deathCount;
        this.deathCd = _o_.deathCd;
        this.history = new java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyHistory>();
		_o_.history.forEach(_v_ -> this.history.add(new PlayerGuardSupplyHistory(_v_)));
        this.name = _o_.name;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public xbean.logic.Bag getBag(){
		return this.bag;
	}

	@JSONField(name = "3")
	public xbean.logic.PlayerSkilledExpInfo getSkilledInfo(){
		return this.skilledInfo;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTalentMap(){
		return this.talentMap;
	}

	@JSONField(name = "5")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "6")
	public int getCurSuitBag(){
		return this.curSuitBag;
	}

	@JSONField(name = "7")
	public int getHp(){
		return this.hp;
	}

	@JSONField(name = "8")
	public int getCurHonor(){
		return this.curHonor;
	}

	@JSONField(name = "9")
	public int getWpos(){
		return this.wpos;
	}

	@JSONField(name = "10")
	public int getDailyHonor(){
		return this.dailyHonor;
	}

	@JSONField(name = "11")
	public int getDeathCount(){
		return this.deathCount;
	}

	@JSONField(name = "12")
	public long getDeathCd(){
		return this.deathCd;
	}

	@JSONField(name = "13")
	public java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyHistory> getHistory(){
		return this.history;
	}

	@JSONField(name = "14")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setBag(xbean.logic.Bag _v_){
		this.bag = _v_;
	}

	@JSONField(name = "3")
	public void setSkilledInfo(xbean.logic.PlayerSkilledExpInfo _v_){
		this.skilledInfo = _v_;
	}

	@JSONField(name = "4")
	public void setTalentMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.talentMap = _v_;
	}

	@JSONField(name = "5")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "6")
	public void setCurSuitBag(int _v_){
		this.curSuitBag = _v_;
	}

	@JSONField(name = "7")
	public void setHp(int _v_){
		this.hp = _v_;
	}

	@JSONField(name = "8")
	public void setCurHonor(int _v_){
		this.curHonor = _v_;
	}

	@JSONField(name = "9")
	public void setWpos(int _v_){
		this.wpos = _v_;
	}

	@JSONField(name = "10")
	public void setDailyHonor(int _v_){
		this.dailyHonor = _v_;
	}

	@JSONField(name = "11")
	public void setDeathCount(int _v_){
		this.deathCount = _v_;
	}

	@JSONField(name = "12")
	public void setDeathCd(long _v_){
		this.deathCd = _v_;
	}

	@JSONField(name = "13")
	public void setHistory(java.util.concurrent.CopyOnWriteArrayList<PlayerGuardSupplyHistory> _v_){
		this.history = _v_;
	}

	@JSONField(name = "14")
	public void setName(String _v_){
		this.name = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerGuardInfo) {
			PlayerGuardInfo _o_ = (PlayerGuardInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.bag.equals(_o_.bag)) return false;
			if (!this.skilledInfo.equals(_o_.skilledInfo)) return false;
			if (!this.talentMap.equals(_o_.talentMap)) return false;
			if (this.level != _o_.level) return false;
			if (this.curSuitBag != _o_.curSuitBag) return false;
			if (this.hp != _o_.hp) return false;
			if (this.curHonor != _o_.curHonor) return false;
			if (this.wpos != _o_.wpos) return false;
			if (this.dailyHonor != _o_.dailyHonor) return false;
			if (this.deathCount != _o_.deathCount) return false;
			if (this.deathCd != _o_.deathCd) return false;
			if (!this.history.equals(_o_.history)) return false;
			if (!this.name.equals(_o_.name)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + this.bag.hashCode();
		_h_ += _h_ * 31 + this.skilledInfo.hashCode();
		_h_ += _h_ * 31 + this.talentMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + (int)(this.curSuitBag);
		_h_ += _h_ * 31 + (int)(this.hp);
		_h_ += _h_ * 31 + (int)(this.curHonor);
		_h_ += _h_ * 31 + (int)(this.wpos);
		_h_ += _h_ * 31 + (int)(this.dailyHonor);
		_h_ += _h_ * 31 + (int)(this.deathCount);
		_h_ += _h_ * 31 + (int)(deathCd ^ (deathCd >>> 32));
		_h_ += _h_ * 31 + this.history.hashCode();
		_h_ += _h_ * 31 + this.name.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(bag).append(",");
		_sb_.append(skilledInfo).append(",");
		_sb_.append(talentMap).append(",");
		_sb_.append(level).append(",");
		_sb_.append(curSuitBag).append(",");
		_sb_.append(hp).append(",");
		_sb_.append(curHonor).append(",");
		_sb_.append(wpos).append(",");
		_sb_.append(dailyHonor).append(",");
		_sb_.append(deathCount).append(",");
		_sb_.append(deathCd).append(",");
		_sb_.append(history).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
