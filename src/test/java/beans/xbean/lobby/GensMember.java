package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensMember extends warrior.mdb.XBean {
    private long pid;  // 玩家id
    private xbean.lobby.PlayerBaseInfo playerInfo;  // 玩家头像id
    private long offline;  // 下线时间
    private long joind;  // 加入时间
    private long gid;  // 公会id
    private int gpid;  // 公会分组ID
    private int contribute;  // 贡献值
    private int dailyContrib;  // 每天公会贡献
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dayDepotNum;  // 每天公会仓库已领取次数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> weeklyReceived;  // 已经领取过的每周公会奖励Id次数
    private long weeklyLastRefresh;  // 每周公会贡献值刷新时间
    private long dayLastRefresh;  // 每天公会仓库领取次数刷新
    private long armPoint;  // 武装度
    private int dailyHonor;  // 每日荣誉上限
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyGensDonationNum;  // 每日军团捐献的次数
    private int totalResDonate;  // 资源捐献总
    private int weeklyResDonate;  // 每周资源捐献
    private int totalGoldOffer;  // 总军团资金捐献
    private int weeklyGoldOffer;  // 每周军团资金捐献
    private int dayReceiveSupplyNum;  // 每天领取军团补给的次数
    private java.util.concurrent.CopyOnWriteArrayList<Integer> takenGensQuestId;  // 已领取过的军团任务id
    private boolean isCheckIn;  // 今日是否签到
    private java.util.concurrent.CopyOnWriteArraySet<Integer> monthlyCheckInInfo;  // 本月签到总数
    private java.util.concurrent.CopyOnWriteArraySet<Integer> monthlyTakenCheckInReward;  // 本月已领取签到奖励id
    private long lastUseAssembleTime;  // 上一次使用集结传送的时间
    private int weeklyContribution;  // 本周贡献
    private long monthlyLastRefresh;  // 上一次月刷新的时间

    GensMember(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerInfo = new PlayerBaseInfo(this);
		this.dayDepotNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.weeklyReceived = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.dailyGensDonationNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.takenGensQuestId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		this.monthlyCheckInInfo = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyTakenCheckInReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public GensMember() {
		this((warrior.mdb.XBean)null);
	}

	public GensMember(GensMember _o_) {
		this(_o_, null);
	}

	GensMember(GensMember _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.pid = _o_.pid;
		this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
		this.offline = _o_.offline;
		this.joind = _o_.joind;
		this.gid = _o_.gid;
		this.gpid = _o_.gpid;
		this.contribute = _o_.contribute;
		this.dailyContrib = _o_.dailyContrib;
		this.dayDepotNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dayDepotNum.forEach((_k_, _v_) -> this.dayDepotNum.put(_k_, _v_));
		this.weeklyReceived = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.weeklyReceived.forEach((_k_, _v_) -> this.weeklyReceived.put(_k_, _v_));
		this.weeklyLastRefresh = _o_.weeklyLastRefresh;
		this.dayLastRefresh = _o_.dayLastRefresh;
		this.armPoint = _o_.armPoint;
		this.dailyHonor = _o_.dailyHonor;
		this.dailyGensDonationNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyGensDonationNum.forEach((_k_, _v_) -> this.dailyGensDonationNum.put(_k_, _v_));
		this.totalResDonate = _o_.totalResDonate;
		this.weeklyResDonate = _o_.weeklyResDonate;
		this.totalGoldOffer = _o_.totalGoldOffer;
		this.weeklyGoldOffer = _o_.weeklyGoldOffer;
		this.dayReceiveSupplyNum = _o_.dayReceiveSupplyNum;
		this.takenGensQuestId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.takenGensQuestId.forEach(_v_ -> this.takenGensQuestId.add(_v_));
		this.isCheckIn = _o_.isCheckIn;
		this.monthlyCheckInInfo = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyCheckInInfo.forEach(_v_ -> this.monthlyCheckInInfo.add(_v_));
		this.monthlyTakenCheckInReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyTakenCheckInReward.forEach(_v_ -> this.monthlyTakenCheckInReward.add(_v_));
		this.lastUseAssembleTime = _o_.lastUseAssembleTime;
		this.weeklyContribution = _o_.weeklyContribution;
		this.monthlyLastRefresh = _o_.monthlyLastRefresh;
	}

	public void copyFrom(GensMember _o_) {
        this.pid = _o_.pid;
        this.playerInfo = new PlayerBaseInfo(_o_.playerInfo, this);
        this.offline = _o_.offline;
        this.joind = _o_.joind;
        this.gid = _o_.gid;
        this.gpid = _o_.gpid;
        this.contribute = _o_.contribute;
        this.dailyContrib = _o_.dailyContrib;
        this.dayDepotNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dayDepotNum.forEach((_k_, _v_) -> this.dayDepotNum.put(_k_, _v_));
        this.weeklyReceived = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.weeklyReceived.forEach((_k_, _v_) -> this.weeklyReceived.put(_k_, _v_));
        this.weeklyLastRefresh = _o_.weeklyLastRefresh;
        this.dayLastRefresh = _o_.dayLastRefresh;
        this.armPoint = _o_.armPoint;
        this.dailyHonor = _o_.dailyHonor;
        this.dailyGensDonationNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyGensDonationNum.forEach((_k_, _v_) -> this.dailyGensDonationNum.put(_k_, _v_));
        this.totalResDonate = _o_.totalResDonate;
        this.weeklyResDonate = _o_.weeklyResDonate;
        this.totalGoldOffer = _o_.totalGoldOffer;
        this.weeklyGoldOffer = _o_.weeklyGoldOffer;
        this.dayReceiveSupplyNum = _o_.dayReceiveSupplyNum;
        this.takenGensQuestId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.takenGensQuestId.forEach(_v_ -> this.takenGensQuestId.add(_v_));
        this.isCheckIn = _o_.isCheckIn;
        this.monthlyCheckInInfo = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyCheckInInfo.forEach(_v_ -> this.monthlyCheckInInfo.add(_v_));
        this.monthlyTakenCheckInReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyTakenCheckInReward.forEach(_v_ -> this.monthlyTakenCheckInReward.add(_v_));
        this.lastUseAssembleTime = _o_.lastUseAssembleTime;
        this.weeklyContribution = _o_.weeklyContribution;
        this.monthlyLastRefresh = _o_.monthlyLastRefresh;
	}


	@JSONField(name = "1")
	public long getPid(){
		return this.pid;
	}

	@JSONField(name = "2")
	public xbean.lobby.PlayerBaseInfo getPlayerInfo(){
		return this.playerInfo;
	}

	@JSONField(name = "3")
	public long getOffline(){
		return this.offline;
	}

	@JSONField(name = "4")
	public long getJoind(){
		return this.joind;
	}

	@JSONField(name = "5")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "6")
	public int getGpid(){
		return this.gpid;
	}

	@JSONField(name = "7")
	public int getContribute(){
		return this.contribute;
	}

	@JSONField(name = "9")
	public int getDailyContrib(){
		return this.dailyContrib;
	}

	@JSONField(name = "10")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDayDepotNum(){
		return this.dayDepotNum;
	}

	@JSONField(name = "11")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getWeeklyReceived(){
		return this.weeklyReceived;
	}

	@JSONField(name = "12")
	public long getWeeklyLastRefresh(){
		return this.weeklyLastRefresh;
	}

	@JSONField(name = "13")
	public long getDayLastRefresh(){
		return this.dayLastRefresh;
	}

	@JSONField(name = "14")
	public long getArmPoint(){
		return this.armPoint;
	}

	@JSONField(name = "15")
	public int getDailyHonor(){
		return this.dailyHonor;
	}

	@JSONField(name = "16")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyGensDonationNum(){
		return this.dailyGensDonationNum;
	}

	@JSONField(name = "17")
	public int getTotalResDonate(){
		return this.totalResDonate;
	}

	@JSONField(name = "18")
	public int getWeeklyResDonate(){
		return this.weeklyResDonate;
	}

	@JSONField(name = "19")
	public int getTotalGoldOffer(){
		return this.totalGoldOffer;
	}

	@JSONField(name = "20")
	public int getWeeklyGoldOffer(){
		return this.weeklyGoldOffer;
	}

	@JSONField(name = "21")
	public int getDayReceiveSupplyNum(){
		return this.dayReceiveSupplyNum;
	}

	@JSONField(name = "22")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getTakenGensQuestId(){
		return this.takenGensQuestId;
	}

	@JSONField(name = "23")
	public boolean getIsCheckIn(){
		return this.isCheckIn;
	}

	@JSONField(name = "24")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getMonthlyCheckInInfo(){
		return this.monthlyCheckInInfo;
	}

	@JSONField(name = "25")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getMonthlyTakenCheckInReward(){
		return this.monthlyTakenCheckInReward;
	}

	@JSONField(name = "26")
	public long getLastUseAssembleTime(){
		return this.lastUseAssembleTime;
	}

	@JSONField(name = "27")
	public int getWeeklyContribution(){
		return this.weeklyContribution;
	}

	@JSONField(name = "28")
	public long getMonthlyLastRefresh(){
		return this.monthlyLastRefresh;
	}

	@JSONField(name = "1")
	public void setPid(long _v_){
		this.pid = _v_;
	}

	@JSONField(name = "2")
	public void setPlayerInfo(xbean.lobby.PlayerBaseInfo _v_){
		this.playerInfo = _v_;
	}

	@JSONField(name = "3")
	public void setOffline(long _v_){
		this.offline = _v_;
	}

	@JSONField(name = "4")
	public void setJoind(long _v_){
		this.joind = _v_;
	}

	@JSONField(name = "5")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "6")
	public void setGpid(int _v_){
		this.gpid = _v_;
	}

	@JSONField(name = "7")
	public void setContribute(int _v_){
		this.contribute = _v_;
	}

	@JSONField(name = "9")
	public void setDailyContrib(int _v_){
		this.dailyContrib = _v_;
	}

	@JSONField(name = "10")
	public void setDayDepotNum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dayDepotNum = _v_;
	}

	@JSONField(name = "11")
	public void setWeeklyReceived(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.weeklyReceived = _v_;
	}

	@JSONField(name = "12")
	public void setWeeklyLastRefresh(long _v_){
		this.weeklyLastRefresh = _v_;
	}

	@JSONField(name = "13")
	public void setDayLastRefresh(long _v_){
		this.dayLastRefresh = _v_;
	}

	@JSONField(name = "14")
	public void setArmPoint(long _v_){
		this.armPoint = _v_;
	}

	@JSONField(name = "15")
	public void setDailyHonor(int _v_){
		this.dailyHonor = _v_;
	}

	@JSONField(name = "16")
	public void setDailyGensDonationNum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyGensDonationNum = _v_;
	}

	@JSONField(name = "17")
	public void setTotalResDonate(int _v_){
		this.totalResDonate = _v_;
	}

	@JSONField(name = "18")
	public void setWeeklyResDonate(int _v_){
		this.weeklyResDonate = _v_;
	}

	@JSONField(name = "19")
	public void setTotalGoldOffer(int _v_){
		this.totalGoldOffer = _v_;
	}

	@JSONField(name = "20")
	public void setWeeklyGoldOffer(int _v_){
		this.weeklyGoldOffer = _v_;
	}

	@JSONField(name = "21")
	public void setDayReceiveSupplyNum(int _v_){
		this.dayReceiveSupplyNum = _v_;
	}

	@JSONField(name = "22")
	public void setTakenGensQuestId(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.takenGensQuestId = _v_;
	}

	@JSONField(name = "23")
	public void setIsCheckIn(boolean _v_){
		this.isCheckIn = _v_;
	}

	@JSONField(name = "24")
	public void setMonthlyCheckInInfo(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.monthlyCheckInInfo = _v_;
	}

	@JSONField(name = "25")
	public void setMonthlyTakenCheckInReward(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.monthlyTakenCheckInReward = _v_;
	}

	@JSONField(name = "26")
	public void setLastUseAssembleTime(long _v_){
		this.lastUseAssembleTime = _v_;
	}

	@JSONField(name = "27")
	public void setWeeklyContribution(int _v_){
		this.weeklyContribution = _v_;
	}

	@JSONField(name = "28")
	public void setMonthlyLastRefresh(long _v_){
		this.monthlyLastRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensMember) {
			GensMember _o_ = (GensMember)_o1_;
			if (this.pid != _o_.pid) return false;
			if (!this.playerInfo.equals(_o_.playerInfo)) return false;
			if (this.offline != _o_.offline) return false;
			if (this.joind != _o_.joind) return false;
			if (this.gid != _o_.gid) return false;
			if (this.gpid != _o_.gpid) return false;
			if (this.contribute != _o_.contribute) return false;
			if (this.dailyContrib != _o_.dailyContrib) return false;
			if (!this.dayDepotNum.equals(_o_.dayDepotNum)) return false;
			if (!this.weeklyReceived.equals(_o_.weeklyReceived)) return false;
			if (this.weeklyLastRefresh != _o_.weeklyLastRefresh) return false;
			if (this.dayLastRefresh != _o_.dayLastRefresh) return false;
			if (this.armPoint != _o_.armPoint) return false;
			if (this.dailyHonor != _o_.dailyHonor) return false;
			if (!this.dailyGensDonationNum.equals(_o_.dailyGensDonationNum)) return false;
			if (this.totalResDonate != _o_.totalResDonate) return false;
			if (this.weeklyResDonate != _o_.weeklyResDonate) return false;
			if (this.totalGoldOffer != _o_.totalGoldOffer) return false;
			if (this.weeklyGoldOffer != _o_.weeklyGoldOffer) return false;
			if (this.dayReceiveSupplyNum != _o_.dayReceiveSupplyNum) return false;
			if (!this.takenGensQuestId.equals(_o_.takenGensQuestId)) return false;
			if (this.isCheckIn != _o_.isCheckIn) return false;
			if (!this.monthlyCheckInInfo.equals(_o_.monthlyCheckInInfo)) return false;
			if (!this.monthlyTakenCheckInReward.equals(_o_.monthlyTakenCheckInReward)) return false;
			if (this.lastUseAssembleTime != _o_.lastUseAssembleTime) return false;
			if (this.weeklyContribution != _o_.weeklyContribution) return false;
			if (this.monthlyLastRefresh != _o_.monthlyLastRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(pid ^ (pid >>> 32));
		_h_ += _h_ * 31 + this.playerInfo.hashCode();
		_h_ += _h_ * 31 + (int)(offline ^ (offline >>> 32));
		_h_ += _h_ * 31 + (int)(joind ^ (joind >>> 32));
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + (int)(this.gpid);
		_h_ += _h_ * 31 + (int)(this.contribute);
		_h_ += _h_ * 31 + (int)(this.dailyContrib);
		_h_ += _h_ * 31 + this.dayDepotNum.hashCode();
		_h_ += _h_ * 31 + this.weeklyReceived.hashCode();
		_h_ += _h_ * 31 + (int)(weeklyLastRefresh ^ (weeklyLastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(dayLastRefresh ^ (dayLastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(armPoint ^ (armPoint >>> 32));
		_h_ += _h_ * 31 + (int)(this.dailyHonor);
		_h_ += _h_ * 31 + this.dailyGensDonationNum.hashCode();
		_h_ += _h_ * 31 + (int)(this.totalResDonate);
		_h_ += _h_ * 31 + (int)(this.weeklyResDonate);
		_h_ += _h_ * 31 + (int)(this.totalGoldOffer);
		_h_ += _h_ * 31 + (int)(this.weeklyGoldOffer);
		_h_ += _h_ * 31 + (int)(this.dayReceiveSupplyNum);
		_h_ += _h_ * 31 + this.takenGensQuestId.hashCode();
		_h_ += _h_ * 31 + (this.isCheckIn ? 1231 : 1237);
		_h_ += _h_ * 31 + this.monthlyCheckInInfo.hashCode();
		_h_ += _h_ * 31 + this.monthlyTakenCheckInReward.hashCode();
		_h_ += _h_ * 31 + (int)(lastUseAssembleTime ^ (lastUseAssembleTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.weeklyContribution);
		_h_ += _h_ * 31 + (int)(monthlyLastRefresh ^ (monthlyLastRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(pid).append(",");
		_sb_.append(playerInfo).append(",");
		_sb_.append(offline).append(",");
		_sb_.append(joind).append(",");
		_sb_.append(gid).append(",");
		_sb_.append(gpid).append(",");
		_sb_.append(contribute).append(",");
		_sb_.append(dailyContrib).append(",");
		_sb_.append(dayDepotNum).append(",");
		_sb_.append(weeklyReceived).append(",");
		_sb_.append(weeklyLastRefresh).append(",");
		_sb_.append(dayLastRefresh).append(",");
		_sb_.append(armPoint).append(",");
		_sb_.append(dailyHonor).append(",");
		_sb_.append(dailyGensDonationNum).append(",");
		_sb_.append(totalResDonate).append(",");
		_sb_.append(weeklyResDonate).append(",");
		_sb_.append(totalGoldOffer).append(",");
		_sb_.append(weeklyGoldOffer).append(",");
		_sb_.append(dayReceiveSupplyNum).append(",");
		_sb_.append(takenGensQuestId).append(",");
		_sb_.append(isCheckIn).append(",");
		_sb_.append(monthlyCheckInInfo).append(",");
		_sb_.append(monthlyTakenCheckInReward).append(",");
		_sb_.append(lastUseAssembleTime).append(",");
		_sb_.append(weeklyContribution).append(",");
		_sb_.append(monthlyLastRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
