package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerFaction extends warrior.mdb.XBean {
    private int factionTotalDonate;  // 累计捐献
    private int factionDailyDonate;  // 今日捐献
    private java.util.concurrent.CopyOnWriteArraySet<Integer> takenDailyAward;  // 领取过的每日捐献奖励
    private java.util.concurrent.CopyOnWriteArraySet<Integer> takenGrandAward;  // 领取过的累计捐献奖励
    private long lastDailyFresh;  // 上次每日刷新时间戳
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyDonateCount;  // id->捐献次数
    private long lastFactionWarResetTime;  // 上次阵营战结束刷新时间
    private int dailyDonateHonor;  // 今日捐献获得荣誉
    private int dailyTechDonateHonor;  // 今日科技投资获得荣誉
    private long lastWeeklyFresh;  // 上次每周刷新时间戳
    private long lastMonthFresh;  // 上次每月刷新时间戳
    private int factionWeekDonate;  // 本周捐献
    private int factionMonthDonate;  // 本月捐献
    private int factionTotalNormalDonate;  // 累计阵营募捐
    private int factionDailyNormalDonate;  // 每日阵营募捐
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyArmamentMap;  // 每日捐献战备点
    private int todayReceiveResOutput;  // 今日领取地区产出资源次数

    PlayerFaction(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.takenDailyAward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.takenGrandAward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.dailyDonateCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.dailyArmamentMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerFaction() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerFaction(PlayerFaction _o_) {
		this(_o_, null);
	}

	PlayerFaction(PlayerFaction _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.factionTotalDonate = _o_.factionTotalDonate;
		this.factionDailyDonate = _o_.factionDailyDonate;
		this.takenDailyAward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.takenDailyAward.forEach(_v_ -> this.takenDailyAward.add(_v_));
		this.takenGrandAward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.takenGrandAward.forEach(_v_ -> this.takenGrandAward.add(_v_));
		this.lastDailyFresh = _o_.lastDailyFresh;
		this.dailyDonateCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyDonateCount.forEach((_k_, _v_) -> this.dailyDonateCount.put(_k_, _v_));
		this.lastFactionWarResetTime = _o_.lastFactionWarResetTime;
		this.dailyDonateHonor = _o_.dailyDonateHonor;
		this.dailyTechDonateHonor = _o_.dailyTechDonateHonor;
		this.lastWeeklyFresh = _o_.lastWeeklyFresh;
		this.lastMonthFresh = _o_.lastMonthFresh;
		this.factionWeekDonate = _o_.factionWeekDonate;
		this.factionMonthDonate = _o_.factionMonthDonate;
		this.factionTotalNormalDonate = _o_.factionTotalNormalDonate;
		this.factionDailyNormalDonate = _o_.factionDailyNormalDonate;
		this.dailyArmamentMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyArmamentMap.forEach((_k_, _v_) -> this.dailyArmamentMap.put(_k_, _v_));
		this.todayReceiveResOutput = _o_.todayReceiveResOutput;
	}

	public void copyFrom(PlayerFaction _o_) {
        this.factionTotalDonate = _o_.factionTotalDonate;
        this.factionDailyDonate = _o_.factionDailyDonate;
        this.takenDailyAward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.takenDailyAward.forEach(_v_ -> this.takenDailyAward.add(_v_));
        this.takenGrandAward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.takenGrandAward.forEach(_v_ -> this.takenGrandAward.add(_v_));
        this.lastDailyFresh = _o_.lastDailyFresh;
        this.dailyDonateCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyDonateCount.forEach((_k_, _v_) -> this.dailyDonateCount.put(_k_, _v_));
        this.lastFactionWarResetTime = _o_.lastFactionWarResetTime;
        this.dailyDonateHonor = _o_.dailyDonateHonor;
        this.dailyTechDonateHonor = _o_.dailyTechDonateHonor;
        this.lastWeeklyFresh = _o_.lastWeeklyFresh;
        this.lastMonthFresh = _o_.lastMonthFresh;
        this.factionWeekDonate = _o_.factionWeekDonate;
        this.factionMonthDonate = _o_.factionMonthDonate;
        this.factionTotalNormalDonate = _o_.factionTotalNormalDonate;
        this.factionDailyNormalDonate = _o_.factionDailyNormalDonate;
        this.dailyArmamentMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyArmamentMap.forEach((_k_, _v_) -> this.dailyArmamentMap.put(_k_, _v_));
        this.todayReceiveResOutput = _o_.todayReceiveResOutput;
	}


	@JSONField(name = "1")
	public int getFactionTotalDonate(){
		return this.factionTotalDonate;
	}

	@JSONField(name = "2")
	public int getFactionDailyDonate(){
		return this.factionDailyDonate;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getTakenDailyAward(){
		return this.takenDailyAward;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getTakenGrandAward(){
		return this.takenGrandAward;
	}

	@JSONField(name = "5")
	public long getLastDailyFresh(){
		return this.lastDailyFresh;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyDonateCount(){
		return this.dailyDonateCount;
	}

	@JSONField(name = "7")
	public long getLastFactionWarResetTime(){
		return this.lastFactionWarResetTime;
	}

	@JSONField(name = "8")
	public int getDailyDonateHonor(){
		return this.dailyDonateHonor;
	}

	@JSONField(name = "9")
	public int getDailyTechDonateHonor(){
		return this.dailyTechDonateHonor;
	}

	@JSONField(name = "10")
	public long getLastWeeklyFresh(){
		return this.lastWeeklyFresh;
	}

	@JSONField(name = "11")
	public long getLastMonthFresh(){
		return this.lastMonthFresh;
	}

	@JSONField(name = "12")
	public int getFactionWeekDonate(){
		return this.factionWeekDonate;
	}

	@JSONField(name = "13")
	public int getFactionMonthDonate(){
		return this.factionMonthDonate;
	}

	@JSONField(name = "14")
	public int getFactionTotalNormalDonate(){
		return this.factionTotalNormalDonate;
	}

	@JSONField(name = "15")
	public int getFactionDailyNormalDonate(){
		return this.factionDailyNormalDonate;
	}

	@JSONField(name = "16")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyArmamentMap(){
		return this.dailyArmamentMap;
	}

	@JSONField(name = "17")
	public int getTodayReceiveResOutput(){
		return this.todayReceiveResOutput;
	}

	@JSONField(name = "1")
	public void setFactionTotalDonate(int _v_){
		this.factionTotalDonate = _v_;
	}

	@JSONField(name = "2")
	public void setFactionDailyDonate(int _v_){
		this.factionDailyDonate = _v_;
	}

	@JSONField(name = "3")
	public void setTakenDailyAward(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.takenDailyAward = _v_;
	}

	@JSONField(name = "4")
	public void setTakenGrandAward(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.takenGrandAward = _v_;
	}

	@JSONField(name = "5")
	public void setLastDailyFresh(long _v_){
		this.lastDailyFresh = _v_;
	}

	@JSONField(name = "6")
	public void setDailyDonateCount(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyDonateCount = _v_;
	}

	@JSONField(name = "7")
	public void setLastFactionWarResetTime(long _v_){
		this.lastFactionWarResetTime = _v_;
	}

	@JSONField(name = "8")
	public void setDailyDonateHonor(int _v_){
		this.dailyDonateHonor = _v_;
	}

	@JSONField(name = "9")
	public void setDailyTechDonateHonor(int _v_){
		this.dailyTechDonateHonor = _v_;
	}

	@JSONField(name = "10")
	public void setLastWeeklyFresh(long _v_){
		this.lastWeeklyFresh = _v_;
	}

	@JSONField(name = "11")
	public void setLastMonthFresh(long _v_){
		this.lastMonthFresh = _v_;
	}

	@JSONField(name = "12")
	public void setFactionWeekDonate(int _v_){
		this.factionWeekDonate = _v_;
	}

	@JSONField(name = "13")
	public void setFactionMonthDonate(int _v_){
		this.factionMonthDonate = _v_;
	}

	@JSONField(name = "14")
	public void setFactionTotalNormalDonate(int _v_){
		this.factionTotalNormalDonate = _v_;
	}

	@JSONField(name = "15")
	public void setFactionDailyNormalDonate(int _v_){
		this.factionDailyNormalDonate = _v_;
	}

	@JSONField(name = "16")
	public void setDailyArmamentMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyArmamentMap = _v_;
	}

	@JSONField(name = "17")
	public void setTodayReceiveResOutput(int _v_){
		this.todayReceiveResOutput = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerFaction) {
			PlayerFaction _o_ = (PlayerFaction)_o1_;
			if (this.factionTotalDonate != _o_.factionTotalDonate) return false;
			if (this.factionDailyDonate != _o_.factionDailyDonate) return false;
			if (!this.takenDailyAward.equals(_o_.takenDailyAward)) return false;
			if (!this.takenGrandAward.equals(_o_.takenGrandAward)) return false;
			if (this.lastDailyFresh != _o_.lastDailyFresh) return false;
			if (!this.dailyDonateCount.equals(_o_.dailyDonateCount)) return false;
			if (this.lastFactionWarResetTime != _o_.lastFactionWarResetTime) return false;
			if (this.dailyDonateHonor != _o_.dailyDonateHonor) return false;
			if (this.dailyTechDonateHonor != _o_.dailyTechDonateHonor) return false;
			if (this.lastWeeklyFresh != _o_.lastWeeklyFresh) return false;
			if (this.lastMonthFresh != _o_.lastMonthFresh) return false;
			if (this.factionWeekDonate != _o_.factionWeekDonate) return false;
			if (this.factionMonthDonate != _o_.factionMonthDonate) return false;
			if (this.factionTotalNormalDonate != _o_.factionTotalNormalDonate) return false;
			if (this.factionDailyNormalDonate != _o_.factionDailyNormalDonate) return false;
			if (!this.dailyArmamentMap.equals(_o_.dailyArmamentMap)) return false;
			if (this.todayReceiveResOutput != _o_.todayReceiveResOutput) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.factionTotalDonate);
		_h_ += _h_ * 31 + (int)(this.factionDailyDonate);
		_h_ += _h_ * 31 + this.takenDailyAward.hashCode();
		_h_ += _h_ * 31 + this.takenGrandAward.hashCode();
		_h_ += _h_ * 31 + (int)(lastDailyFresh ^ (lastDailyFresh >>> 32));
		_h_ += _h_ * 31 + this.dailyDonateCount.hashCode();
		_h_ += _h_ * 31 + (int)(lastFactionWarResetTime ^ (lastFactionWarResetTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.dailyDonateHonor);
		_h_ += _h_ * 31 + (int)(this.dailyTechDonateHonor);
		_h_ += _h_ * 31 + (int)(lastWeeklyFresh ^ (lastWeeklyFresh >>> 32));
		_h_ += _h_ * 31 + (int)(lastMonthFresh ^ (lastMonthFresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.factionWeekDonate);
		_h_ += _h_ * 31 + (int)(this.factionMonthDonate);
		_h_ += _h_ * 31 + (int)(this.factionTotalNormalDonate);
		_h_ += _h_ * 31 + (int)(this.factionDailyNormalDonate);
		_h_ += _h_ * 31 + this.dailyArmamentMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.todayReceiveResOutput);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(factionTotalDonate).append(",");
		_sb_.append(factionDailyDonate).append(",");
		_sb_.append(takenDailyAward).append(",");
		_sb_.append(takenGrandAward).append(",");
		_sb_.append(lastDailyFresh).append(",");
		_sb_.append(dailyDonateCount).append(",");
		_sb_.append(lastFactionWarResetTime).append(",");
		_sb_.append(dailyDonateHonor).append(",");
		_sb_.append(dailyTechDonateHonor).append(",");
		_sb_.append(lastWeeklyFresh).append(",");
		_sb_.append(lastMonthFresh).append(",");
		_sb_.append(factionWeekDonate).append(",");
		_sb_.append(factionMonthDonate).append(",");
		_sb_.append(factionTotalNormalDonate).append(",");
		_sb_.append(factionDailyNormalDonate).append(",");
		_sb_.append(dailyArmamentMap).append(",");
		_sb_.append(todayReceiveResOutput).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
