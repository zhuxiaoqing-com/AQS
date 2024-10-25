package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerWarMakes extends warrior.mdb.XBean {
    private xbean.logic.LGActLogicInfo actInfo;  // 活动数据
    private int exp;  // 总经验
    private int type;  // 战令类型
    private int dailyExp;  // 每天获得的经验
    private boolean weeklyGift;  // 每周奖励是否已经领取
    private java.util.concurrent.CopyOnWriteArraySet<Integer> freeList;  // 已经领取过的免费奖励
    private java.util.concurrent.CopyOnWriteArraySet<Integer> advancedList;  // 已经领取过的进阶奖励
    private long lastRefresh;  // 上一次刷新时间

    PlayerWarMakes(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actInfo = new LGActLogicInfo(this);
		this.freeList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.advancedList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public PlayerWarMakes() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerWarMakes(PlayerWarMakes _o_) {
		this(_o_, null);
	}

	PlayerWarMakes(PlayerWarMakes _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
		this.exp = _o_.exp;
		this.type = _o_.type;
		this.dailyExp = _o_.dailyExp;
		this.weeklyGift = _o_.weeklyGift;
		this.freeList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.freeList.forEach(_v_ -> this.freeList.add(_v_));
		this.advancedList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.advancedList.forEach(_v_ -> this.advancedList.add(_v_));
		this.lastRefresh = _o_.lastRefresh;
	}

	public void copyFrom(PlayerWarMakes _o_) {
        this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
        this.exp = _o_.exp;
        this.type = _o_.type;
        this.dailyExp = _o_.dailyExp;
        this.weeklyGift = _o_.weeklyGift;
        this.freeList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.freeList.forEach(_v_ -> this.freeList.add(_v_));
        this.advancedList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.advancedList.forEach(_v_ -> this.advancedList.add(_v_));
        this.lastRefresh = _o_.lastRefresh;
	}


	@JSONField(name = "1")
	public xbean.logic.LGActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "3")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "4")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "5")
	public int getDailyExp(){
		return this.dailyExp;
	}

	@JSONField(name = "6")
	public boolean getWeeklyGift(){
		return this.weeklyGift;
	}

	@JSONField(name = "7")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getFreeList(){
		return this.freeList;
	}

	@JSONField(name = "8")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getAdvancedList(){
		return this.advancedList;
	}

	@JSONField(name = "11")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "1")
	public void setActInfo(xbean.logic.LGActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@JSONField(name = "3")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@JSONField(name = "4")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "5")
	public void setDailyExp(int _v_){
		this.dailyExp = _v_;
	}

	@JSONField(name = "6")
	public void setWeeklyGift(boolean _v_){
		this.weeklyGift = _v_;
	}

	@JSONField(name = "7")
	public void setFreeList(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.freeList = _v_;
	}

	@JSONField(name = "8")
	public void setAdvancedList(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.advancedList = _v_;
	}

	@JSONField(name = "11")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerWarMakes) {
			PlayerWarMakes _o_ = (PlayerWarMakes)_o1_;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			if (this.exp != _o_.exp) return false;
			if (this.type != _o_.type) return false;
			if (this.dailyExp != _o_.dailyExp) return false;
			if (this.weeklyGift != _o_.weeklyGift) return false;
			if (!this.freeList.equals(_o_.freeList)) return false;
			if (!this.advancedList.equals(_o_.advancedList)) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		_h_ += _h_ * 31 + (int)(this.exp);
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(this.dailyExp);
		_h_ += _h_ * 31 + (this.weeklyGift ? 1231 : 1237);
		_h_ += _h_ * 31 + this.freeList.hashCode();
		_h_ += _h_ * 31 + this.advancedList.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actInfo).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(type).append(",");
		_sb_.append(dailyExp).append(",");
		_sb_.append(weeklyGift).append(",");
		_sb_.append(freeList).append(",");
		_sb_.append(advancedList).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
