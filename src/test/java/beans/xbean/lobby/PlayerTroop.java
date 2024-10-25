package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerTroop extends warrior.mdb.XBean {
    private long playerId; 
    private long troopId; 
    private java.util.concurrent.CopyOnWriteArrayList<Long> forms;  // 响应的自建连队
    private long leadTroop;  // 所领导的部队id
    private java.util.concurrent.CopyOnWriteArraySet<Long> applyTroop;  // 申请加入的部队id
    private java.util.concurrent.CopyOnWriteArraySet<Integer> monthlyCheckInInfo;  // 本月签到总数
    private java.util.concurrent.CopyOnWriteArraySet<Integer> monthlyTakenCheckInReward;  // 本月已领取签到奖励id
    private long monthlyLastRefresh; 
    private long dailyLastRefresh; 
    private long recallEndTime; 
    private String recallCode; 
    private long impeachTroop;  // 发起的弹劾
    private java.util.concurrent.CopyOnWriteArraySet<Long> yesImpeach;  // 已处理的弹劾
    private java.util.concurrent.CopyOnWriteArraySet<Long> noImpeach;  // 已处理的弹劾
    private java.util.concurrent.ConcurrentHashMap<Long, Long> voteTroopElect;  // 竞选日已投票的部队id
    private long joinCampaign;  // 已参选部队
    private int recallCount;  // 召回码被使用次数
    private java.util.concurrent.CopyOnWriteArraySet<Integer> recallRewardTakeId;  // 已领取召回次数奖励id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> practiceQuestScore;  // 作训得分
    private int joinTroopNum;  // 加入部队次数
    private int checkInTotalNum;  // 签到总数
    private boolean isInstructor;  // 是否为教官
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> inTroopActionRecord;  // 在当前连队期间的行为记录
    private java.util.concurrent.CopyOnWriteArraySet<Integer> receivedOrientationQuest;  // 已领取的迎新任务id
    private long newRecruitNextCanPraiseTime;  // 下次新手可点赞时间
    private int newRecruitDailyPraiseCount;  // 今日新手点赞次数
    private long nextSendInviteTime;  // 下次可发送连队邀请的时间戳
    private int dailyInviteSendCount;  // 当天发送邀请次数
    private java.util.concurrent.ConcurrentHashMap<Integer, LBQuest> orientationQuest; 
    private long nextCanJoinTroop;  // 允许加入连队的时间戳
    private int dailyUpgradeDonate1;  // 当天升级捐献1次数
    private int dailyUpgradeDonate2;  // 当天升级捐献2次数
    private int composeTechScore;  // 制造科技分数

    PlayerTroop(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.forms = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.applyTroop = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.monthlyCheckInInfo = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyTakenCheckInReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.recallCode = "";
		this.yesImpeach = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.noImpeach = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.voteTroopElect = new java.util.concurrent.ConcurrentHashMap<Long, Long>();
		this.recallRewardTakeId = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.practiceQuestScore = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.inTroopActionRecord = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.receivedOrientationQuest = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.orientationQuest = new java.util.concurrent.ConcurrentHashMap<Integer, LBQuest>();
	}

	public PlayerTroop() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerTroop(PlayerTroop _o_) {
		this(_o_, null);
	}

	PlayerTroop(PlayerTroop _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.troopId = _o_.troopId;
		this.forms = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.forms.forEach(_v_ -> this.forms.add(_v_));
		this.leadTroop = _o_.leadTroop;
		this.applyTroop = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.applyTroop.forEach(_v_ -> this.applyTroop.add(_v_));
		this.monthlyCheckInInfo = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyCheckInInfo.forEach(_v_ -> this.monthlyCheckInInfo.add(_v_));
		this.monthlyTakenCheckInReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyTakenCheckInReward.forEach(_v_ -> this.monthlyTakenCheckInReward.add(_v_));
		this.monthlyLastRefresh = _o_.monthlyLastRefresh;
		this.dailyLastRefresh = _o_.dailyLastRefresh;
		this.recallEndTime = _o_.recallEndTime;
		this.recallCode = _o_.recallCode;
		this.impeachTroop = _o_.impeachTroop;
		this.yesImpeach = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.yesImpeach.forEach(_v_ -> this.yesImpeach.add(_v_));
		this.noImpeach = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.noImpeach.forEach(_v_ -> this.noImpeach.add(_v_));
		this.voteTroopElect = new java.util.concurrent.ConcurrentHashMap<Long, Long>();
		_o_.voteTroopElect.forEach((_k_, _v_) -> this.voteTroopElect.put(_k_, _v_));
		this.joinCampaign = _o_.joinCampaign;
		this.recallCount = _o_.recallCount;
		this.recallRewardTakeId = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.recallRewardTakeId.forEach(_v_ -> this.recallRewardTakeId.add(_v_));
		this.practiceQuestScore = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.practiceQuestScore.forEach((_k_, _v_) -> this.practiceQuestScore.put(_k_, _v_));
		this.joinTroopNum = _o_.joinTroopNum;
		this.checkInTotalNum = _o_.checkInTotalNum;
		this.isInstructor = _o_.isInstructor;
		this.inTroopActionRecord = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.inTroopActionRecord.forEach((_k_, _v_) -> this.inTroopActionRecord.put(_k_, _v_));
		this.receivedOrientationQuest = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.receivedOrientationQuest.forEach(_v_ -> this.receivedOrientationQuest.add(_v_));
		this.newRecruitNextCanPraiseTime = _o_.newRecruitNextCanPraiseTime;
		this.newRecruitDailyPraiseCount = _o_.newRecruitDailyPraiseCount;
		this.nextSendInviteTime = _o_.nextSendInviteTime;
		this.dailyInviteSendCount = _o_.dailyInviteSendCount;
		this.orientationQuest = new java.util.concurrent.ConcurrentHashMap<Integer, LBQuest>();
		_o_.orientationQuest.forEach((_k_, _v_) -> this.orientationQuest.put(_k_, new LBQuest(_v_)));
		this.nextCanJoinTroop = _o_.nextCanJoinTroop;
		this.dailyUpgradeDonate1 = _o_.dailyUpgradeDonate1;
		this.dailyUpgradeDonate2 = _o_.dailyUpgradeDonate2;
		this.composeTechScore = _o_.composeTechScore;
	}

	public void copyFrom(PlayerTroop _o_) {
        this.playerId = _o_.playerId;
        this.troopId = _o_.troopId;
        this.forms = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.forms.forEach(_v_ -> this.forms.add(_v_));
        this.leadTroop = _o_.leadTroop;
        this.applyTroop = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.applyTroop.forEach(_v_ -> this.applyTroop.add(_v_));
        this.monthlyCheckInInfo = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyCheckInInfo.forEach(_v_ -> this.monthlyCheckInInfo.add(_v_));
        this.monthlyTakenCheckInReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyTakenCheckInReward.forEach(_v_ -> this.monthlyTakenCheckInReward.add(_v_));
        this.monthlyLastRefresh = _o_.monthlyLastRefresh;
        this.dailyLastRefresh = _o_.dailyLastRefresh;
        this.recallEndTime = _o_.recallEndTime;
        this.recallCode = _o_.recallCode;
        this.impeachTroop = _o_.impeachTroop;
        this.yesImpeach = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.yesImpeach.forEach(_v_ -> this.yesImpeach.add(_v_));
        this.noImpeach = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.noImpeach.forEach(_v_ -> this.noImpeach.add(_v_));
        this.voteTroopElect = new java.util.concurrent.ConcurrentHashMap<Long, Long>();
		_o_.voteTroopElect.forEach((_k_, _v_) -> this.voteTroopElect.put(_k_, _v_));
        this.joinCampaign = _o_.joinCampaign;
        this.recallCount = _o_.recallCount;
        this.recallRewardTakeId = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.recallRewardTakeId.forEach(_v_ -> this.recallRewardTakeId.add(_v_));
        this.practiceQuestScore = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.practiceQuestScore.forEach((_k_, _v_) -> this.practiceQuestScore.put(_k_, _v_));
        this.joinTroopNum = _o_.joinTroopNum;
        this.checkInTotalNum = _o_.checkInTotalNum;
        this.isInstructor = _o_.isInstructor;
        this.inTroopActionRecord = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.inTroopActionRecord.forEach((_k_, _v_) -> this.inTroopActionRecord.put(_k_, _v_));
        this.receivedOrientationQuest = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.receivedOrientationQuest.forEach(_v_ -> this.receivedOrientationQuest.add(_v_));
        this.newRecruitNextCanPraiseTime = _o_.newRecruitNextCanPraiseTime;
        this.newRecruitDailyPraiseCount = _o_.newRecruitDailyPraiseCount;
        this.nextSendInviteTime = _o_.nextSendInviteTime;
        this.dailyInviteSendCount = _o_.dailyInviteSendCount;
        this.orientationQuest = new java.util.concurrent.ConcurrentHashMap<Integer, LBQuest>();
		_o_.orientationQuest.forEach((_k_, _v_) -> this.orientationQuest.put(_k_, new LBQuest(_v_)));
        this.nextCanJoinTroop = _o_.nextCanJoinTroop;
        this.dailyUpgradeDonate1 = _o_.dailyUpgradeDonate1;
        this.dailyUpgradeDonate2 = _o_.dailyUpgradeDonate2;
        this.composeTechScore = _o_.composeTechScore;
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "2")
	public long getTroopId(){
		return this.troopId;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getForms(){
		return this.forms;
	}

	@JSONField(name = "4")
	public long getLeadTroop(){
		return this.leadTroop;
	}

	@JSONField(name = "5")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getApplyTroop(){
		return this.applyTroop;
	}

	@JSONField(name = "7")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getMonthlyCheckInInfo(){
		return this.monthlyCheckInInfo;
	}

	@JSONField(name = "8")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getMonthlyTakenCheckInReward(){
		return this.monthlyTakenCheckInReward;
	}

	@JSONField(name = "9")
	public long getMonthlyLastRefresh(){
		return this.monthlyLastRefresh;
	}

	@JSONField(name = "10")
	public long getDailyLastRefresh(){
		return this.dailyLastRefresh;
	}

	@JSONField(name = "11")
	public long getRecallEndTime(){
		return this.recallEndTime;
	}

	@JSONField(name = "12")
	public String getRecallCode(){
		return this.recallCode;
	}

	@JSONField(name = "13")
	public long getImpeachTroop(){
		return this.impeachTroop;
	}

	@JSONField(name = "14")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getYesImpeach(){
		return this.yesImpeach;
	}

	@JSONField(name = "15")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getNoImpeach(){
		return this.noImpeach;
	}

	@JSONField(name = "16")
	public java.util.concurrent.ConcurrentHashMap<Long, Long> getVoteTroopElect(){
		return this.voteTroopElect;
	}

	@JSONField(name = "17")
	public long getJoinCampaign(){
		return this.joinCampaign;
	}

	@JSONField(name = "18")
	public int getRecallCount(){
		return this.recallCount;
	}

	@JSONField(name = "19")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getRecallRewardTakeId(){
		return this.recallRewardTakeId;
	}

	@JSONField(name = "20")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getPracticeQuestScore(){
		return this.practiceQuestScore;
	}

	@JSONField(name = "21")
	public int getJoinTroopNum(){
		return this.joinTroopNum;
	}

	@JSONField(name = "22")
	public int getCheckInTotalNum(){
		return this.checkInTotalNum;
	}

	@JSONField(name = "25")
	public boolean getIsInstructor(){
		return this.isInstructor;
	}

	@JSONField(name = "26")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getInTroopActionRecord(){
		return this.inTroopActionRecord;
	}

	@JSONField(name = "27")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getReceivedOrientationQuest(){
		return this.receivedOrientationQuest;
	}

	@JSONField(name = "28")
	public long getNewRecruitNextCanPraiseTime(){
		return this.newRecruitNextCanPraiseTime;
	}

	@JSONField(name = "29")
	public int getNewRecruitDailyPraiseCount(){
		return this.newRecruitDailyPraiseCount;
	}

	@JSONField(name = "30")
	public long getNextSendInviteTime(){
		return this.nextSendInviteTime;
	}

	@JSONField(name = "31")
	public int getDailyInviteSendCount(){
		return this.dailyInviteSendCount;
	}

	@JSONField(name = "32")
	public java.util.concurrent.ConcurrentHashMap<Integer, LBQuest> getOrientationQuest(){
		return this.orientationQuest;
	}

	@JSONField(name = "33")
	public long getNextCanJoinTroop(){
		return this.nextCanJoinTroop;
	}

	@JSONField(name = "34")
	public int getDailyUpgradeDonate1(){
		return this.dailyUpgradeDonate1;
	}

	@JSONField(name = "35")
	public int getDailyUpgradeDonate2(){
		return this.dailyUpgradeDonate2;
	}

	@JSONField(name = "36")
	public int getComposeTechScore(){
		return this.composeTechScore;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "2")
	public void setTroopId(long _v_){
		this.troopId = _v_;
	}

	@JSONField(name = "3")
	public void setForms(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.forms = _v_;
	}

	@JSONField(name = "4")
	public void setLeadTroop(long _v_){
		this.leadTroop = _v_;
	}

	@JSONField(name = "5")
	public void setApplyTroop(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.applyTroop = _v_;
	}

	@JSONField(name = "7")
	public void setMonthlyCheckInInfo(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.monthlyCheckInInfo = _v_;
	}

	@JSONField(name = "8")
	public void setMonthlyTakenCheckInReward(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.monthlyTakenCheckInReward = _v_;
	}

	@JSONField(name = "9")
	public void setMonthlyLastRefresh(long _v_){
		this.monthlyLastRefresh = _v_;
	}

	@JSONField(name = "10")
	public void setDailyLastRefresh(long _v_){
		this.dailyLastRefresh = _v_;
	}

	@JSONField(name = "11")
	public void setRecallEndTime(long _v_){
		this.recallEndTime = _v_;
	}

	@JSONField(name = "12")
	public void setRecallCode(String _v_){
		this.recallCode = _v_;
	}

	@JSONField(name = "13")
	public void setImpeachTroop(long _v_){
		this.impeachTroop = _v_;
	}

	@JSONField(name = "14")
	public void setYesImpeach(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.yesImpeach = _v_;
	}

	@JSONField(name = "15")
	public void setNoImpeach(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.noImpeach = _v_;
	}

	@JSONField(name = "16")
	public void setVoteTroopElect(java.util.concurrent.ConcurrentHashMap<Long, Long> _v_){
		this.voteTroopElect = _v_;
	}

	@JSONField(name = "17")
	public void setJoinCampaign(long _v_){
		this.joinCampaign = _v_;
	}

	@JSONField(name = "18")
	public void setRecallCount(int _v_){
		this.recallCount = _v_;
	}

	@JSONField(name = "19")
	public void setRecallRewardTakeId(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.recallRewardTakeId = _v_;
	}

	@JSONField(name = "20")
	public void setPracticeQuestScore(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.practiceQuestScore = _v_;
	}

	@JSONField(name = "21")
	public void setJoinTroopNum(int _v_){
		this.joinTroopNum = _v_;
	}

	@JSONField(name = "22")
	public void setCheckInTotalNum(int _v_){
		this.checkInTotalNum = _v_;
	}

	@JSONField(name = "25")
	public void setIsInstructor(boolean _v_){
		this.isInstructor = _v_;
	}

	@JSONField(name = "26")
	public void setInTroopActionRecord(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.inTroopActionRecord = _v_;
	}

	@JSONField(name = "27")
	public void setReceivedOrientationQuest(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.receivedOrientationQuest = _v_;
	}

	@JSONField(name = "28")
	public void setNewRecruitNextCanPraiseTime(long _v_){
		this.newRecruitNextCanPraiseTime = _v_;
	}

	@JSONField(name = "29")
	public void setNewRecruitDailyPraiseCount(int _v_){
		this.newRecruitDailyPraiseCount = _v_;
	}

	@JSONField(name = "30")
	public void setNextSendInviteTime(long _v_){
		this.nextSendInviteTime = _v_;
	}

	@JSONField(name = "31")
	public void setDailyInviteSendCount(int _v_){
		this.dailyInviteSendCount = _v_;
	}

	@JSONField(name = "32")
	public void setOrientationQuest(java.util.concurrent.ConcurrentHashMap<Integer, LBQuest> _v_){
		this.orientationQuest = _v_;
	}

	@JSONField(name = "33")
	public void setNextCanJoinTroop(long _v_){
		this.nextCanJoinTroop = _v_;
	}

	@JSONField(name = "34")
	public void setDailyUpgradeDonate1(int _v_){
		this.dailyUpgradeDonate1 = _v_;
	}

	@JSONField(name = "35")
	public void setDailyUpgradeDonate2(int _v_){
		this.dailyUpgradeDonate2 = _v_;
	}

	@JSONField(name = "36")
	public void setComposeTechScore(int _v_){
		this.composeTechScore = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerTroop) {
			PlayerTroop _o_ = (PlayerTroop)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (this.troopId != _o_.troopId) return false;
			if (!this.forms.equals(_o_.forms)) return false;
			if (this.leadTroop != _o_.leadTroop) return false;
			if (!this.applyTroop.equals(_o_.applyTroop)) return false;
			if (!this.monthlyCheckInInfo.equals(_o_.monthlyCheckInInfo)) return false;
			if (!this.monthlyTakenCheckInReward.equals(_o_.monthlyTakenCheckInReward)) return false;
			if (this.monthlyLastRefresh != _o_.monthlyLastRefresh) return false;
			if (this.dailyLastRefresh != _o_.dailyLastRefresh) return false;
			if (this.recallEndTime != _o_.recallEndTime) return false;
			if (!this.recallCode.equals(_o_.recallCode)) return false;
			if (this.impeachTroop != _o_.impeachTroop) return false;
			if (!this.yesImpeach.equals(_o_.yesImpeach)) return false;
			if (!this.noImpeach.equals(_o_.noImpeach)) return false;
			if (!this.voteTroopElect.equals(_o_.voteTroopElect)) return false;
			if (this.joinCampaign != _o_.joinCampaign) return false;
			if (this.recallCount != _o_.recallCount) return false;
			if (!this.recallRewardTakeId.equals(_o_.recallRewardTakeId)) return false;
			if (!this.practiceQuestScore.equals(_o_.practiceQuestScore)) return false;
			if (this.joinTroopNum != _o_.joinTroopNum) return false;
			if (this.checkInTotalNum != _o_.checkInTotalNum) return false;
			if (this.isInstructor != _o_.isInstructor) return false;
			if (!this.inTroopActionRecord.equals(_o_.inTroopActionRecord)) return false;
			if (!this.receivedOrientationQuest.equals(_o_.receivedOrientationQuest)) return false;
			if (this.newRecruitNextCanPraiseTime != _o_.newRecruitNextCanPraiseTime) return false;
			if (this.newRecruitDailyPraiseCount != _o_.newRecruitDailyPraiseCount) return false;
			if (this.nextSendInviteTime != _o_.nextSendInviteTime) return false;
			if (this.dailyInviteSendCount != _o_.dailyInviteSendCount) return false;
			if (!this.orientationQuest.equals(_o_.orientationQuest)) return false;
			if (this.nextCanJoinTroop != _o_.nextCanJoinTroop) return false;
			if (this.dailyUpgradeDonate1 != _o_.dailyUpgradeDonate1) return false;
			if (this.dailyUpgradeDonate2 != _o_.dailyUpgradeDonate2) return false;
			if (this.composeTechScore != _o_.composeTechScore) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + (int)(troopId ^ (troopId >>> 32));
		_h_ += _h_ * 31 + this.forms.hashCode();
		_h_ += _h_ * 31 + (int)(leadTroop ^ (leadTroop >>> 32));
		_h_ += _h_ * 31 + this.applyTroop.hashCode();
		_h_ += _h_ * 31 + this.monthlyCheckInInfo.hashCode();
		_h_ += _h_ * 31 + this.monthlyTakenCheckInReward.hashCode();
		_h_ += _h_ * 31 + (int)(monthlyLastRefresh ^ (monthlyLastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(dailyLastRefresh ^ (dailyLastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(recallEndTime ^ (recallEndTime >>> 32));
		_h_ += _h_ * 31 + this.recallCode.hashCode();
		_h_ += _h_ * 31 + (int)(impeachTroop ^ (impeachTroop >>> 32));
		_h_ += _h_ * 31 + this.yesImpeach.hashCode();
		_h_ += _h_ * 31 + this.noImpeach.hashCode();
		_h_ += _h_ * 31 + this.voteTroopElect.hashCode();
		_h_ += _h_ * 31 + (int)(joinCampaign ^ (joinCampaign >>> 32));
		_h_ += _h_ * 31 + (int)(this.recallCount);
		_h_ += _h_ * 31 + this.recallRewardTakeId.hashCode();
		_h_ += _h_ * 31 + this.practiceQuestScore.hashCode();
		_h_ += _h_ * 31 + (int)(this.joinTroopNum);
		_h_ += _h_ * 31 + (int)(this.checkInTotalNum);
		_h_ += _h_ * 31 + (this.isInstructor ? 1231 : 1237);
		_h_ += _h_ * 31 + this.inTroopActionRecord.hashCode();
		_h_ += _h_ * 31 + this.receivedOrientationQuest.hashCode();
		_h_ += _h_ * 31 + (int)(newRecruitNextCanPraiseTime ^ (newRecruitNextCanPraiseTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.newRecruitDailyPraiseCount);
		_h_ += _h_ * 31 + (int)(nextSendInviteTime ^ (nextSendInviteTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.dailyInviteSendCount);
		_h_ += _h_ * 31 + this.orientationQuest.hashCode();
		_h_ += _h_ * 31 + (int)(nextCanJoinTroop ^ (nextCanJoinTroop >>> 32));
		_h_ += _h_ * 31 + (int)(this.dailyUpgradeDonate1);
		_h_ += _h_ * 31 + (int)(this.dailyUpgradeDonate2);
		_h_ += _h_ * 31 + (int)(this.composeTechScore);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append(troopId).append(",");
		_sb_.append(forms).append(",");
		_sb_.append(leadTroop).append(",");
		_sb_.append(applyTroop).append(",");
		_sb_.append(monthlyCheckInInfo).append(",");
		_sb_.append(monthlyTakenCheckInReward).append(",");
		_sb_.append(monthlyLastRefresh).append(",");
		_sb_.append(dailyLastRefresh).append(",");
		_sb_.append(recallEndTime).append(",");
		_sb_.append("T").append(recallCode.length()).append(",");
		_sb_.append(impeachTroop).append(",");
		_sb_.append(yesImpeach).append(",");
		_sb_.append(noImpeach).append(",");
		_sb_.append(voteTroopElect).append(",");
		_sb_.append(joinCampaign).append(",");
		_sb_.append(recallCount).append(",");
		_sb_.append(recallRewardTakeId).append(",");
		_sb_.append(practiceQuestScore).append(",");
		_sb_.append(joinTroopNum).append(",");
		_sb_.append(checkInTotalNum).append(",");
		_sb_.append(isInstructor).append(",");
		_sb_.append(inTroopActionRecord).append(",");
		_sb_.append(receivedOrientationQuest).append(",");
		_sb_.append(newRecruitNextCanPraiseTime).append(",");
		_sb_.append(newRecruitDailyPraiseCount).append(",");
		_sb_.append(nextSendInviteTime).append(",");
		_sb_.append(dailyInviteSendCount).append(",");
		_sb_.append(orientationQuest).append(",");
		_sb_.append(nextCanJoinTroop).append(",");
		_sb_.append(dailyUpgradeDonate1).append(",");
		_sb_.append(dailyUpgradeDonate2).append(",");
		_sb_.append(composeTechScore).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
