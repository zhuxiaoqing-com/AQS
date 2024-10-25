package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerEquipWare extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp> helpOthers;  // 收到的学习
    private java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp> helpMe;  // 自己发出的求助学习
    private int todayHelpOthers; 
    private int todaySendHelp; 
    private long lastDailyFresh; 
    private int studyEquipId; 
    private int studyProgress; 

    PlayerEquipWare(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.helpOthers = new java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp>();
		this.helpMe = new java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp>();
	}

	public PlayerEquipWare() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerEquipWare(PlayerEquipWare _o_) {
		this(_o_, null);
	}

	PlayerEquipWare(PlayerEquipWare _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.helpOthers = new java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp>();
		_o_.helpOthers.forEach((_k_, _v_) -> this.helpOthers.put(_k_, new EquipWareHelp(_v_)));
		this.helpMe = new java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp>();
		_o_.helpMe.forEach((_k_, _v_) -> this.helpMe.put(_k_, new EquipWareHelp(_v_)));
		this.todayHelpOthers = _o_.todayHelpOthers;
		this.todaySendHelp = _o_.todaySendHelp;
		this.lastDailyFresh = _o_.lastDailyFresh;
		this.studyEquipId = _o_.studyEquipId;
		this.studyProgress = _o_.studyProgress;
	}

	public void copyFrom(PlayerEquipWare _o_) {
        this.helpOthers = new java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp>();
		_o_.helpOthers.forEach((_k_, _v_) -> this.helpOthers.put(_k_, new EquipWareHelp(_v_)));
        this.helpMe = new java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp>();
		_o_.helpMe.forEach((_k_, _v_) -> this.helpMe.put(_k_, new EquipWareHelp(_v_)));
        this.todayHelpOthers = _o_.todayHelpOthers;
        this.todaySendHelp = _o_.todaySendHelp;
        this.lastDailyFresh = _o_.lastDailyFresh;
        this.studyEquipId = _o_.studyEquipId;
        this.studyProgress = _o_.studyProgress;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp> getHelpOthers(){
		return this.helpOthers;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp> getHelpMe(){
		return this.helpMe;
	}

	@JSONField(name = "3")
	public int getTodayHelpOthers(){
		return this.todayHelpOthers;
	}

	@JSONField(name = "4")
	public int getTodaySendHelp(){
		return this.todaySendHelp;
	}

	@JSONField(name = "5")
	public long getLastDailyFresh(){
		return this.lastDailyFresh;
	}

	@JSONField(name = "6")
	public int getStudyEquipId(){
		return this.studyEquipId;
	}

	@JSONField(name = "7")
	public int getStudyProgress(){
		return this.studyProgress;
	}

	@JSONField(name = "1")
	public void setHelpOthers(java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp> _v_){
		this.helpOthers = _v_;
	}

	@JSONField(name = "2")
	public void setHelpMe(java.util.concurrent.ConcurrentHashMap<Long, EquipWareHelp> _v_){
		this.helpMe = _v_;
	}

	@JSONField(name = "3")
	public void setTodayHelpOthers(int _v_){
		this.todayHelpOthers = _v_;
	}

	@JSONField(name = "4")
	public void setTodaySendHelp(int _v_){
		this.todaySendHelp = _v_;
	}

	@JSONField(name = "5")
	public void setLastDailyFresh(long _v_){
		this.lastDailyFresh = _v_;
	}

	@JSONField(name = "6")
	public void setStudyEquipId(int _v_){
		this.studyEquipId = _v_;
	}

	@JSONField(name = "7")
	public void setStudyProgress(int _v_){
		this.studyProgress = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerEquipWare) {
			PlayerEquipWare _o_ = (PlayerEquipWare)_o1_;
			if (!this.helpOthers.equals(_o_.helpOthers)) return false;
			if (!this.helpMe.equals(_o_.helpMe)) return false;
			if (this.todayHelpOthers != _o_.todayHelpOthers) return false;
			if (this.todaySendHelp != _o_.todaySendHelp) return false;
			if (this.lastDailyFresh != _o_.lastDailyFresh) return false;
			if (this.studyEquipId != _o_.studyEquipId) return false;
			if (this.studyProgress != _o_.studyProgress) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.helpOthers.hashCode();
		_h_ += _h_ * 31 + this.helpMe.hashCode();
		_h_ += _h_ * 31 + (int)(this.todayHelpOthers);
		_h_ += _h_ * 31 + (int)(this.todaySendHelp);
		_h_ += _h_ * 31 + (int)(lastDailyFresh ^ (lastDailyFresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.studyEquipId);
		_h_ += _h_ * 31 + (int)(this.studyProgress);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(helpOthers).append(",");
		_sb_.append(helpMe).append(",");
		_sb_.append(todayHelpOthers).append(",");
		_sb_.append(todaySendHelp).append(",");
		_sb_.append(lastDailyFresh).append(",");
		_sb_.append(studyEquipId).append(",");
		_sb_.append(studyProgress).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
