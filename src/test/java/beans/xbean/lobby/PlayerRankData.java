package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerRankData extends warrior.mdb.XBean {
    private long dailyLastFresh; 
    private long weekLastFresh; 
    private long monthLastFresh; 
    private long dailyFleetHonor; 
    private long weekFleetHonor; 
    private long monthFleetHonor; 
    private long totalFleetHonor; 
    private long dailyFactionDonate; 
    private long weekFactionDonate; 
    private long monthFactionDonate; 
    private long totalFactionDonate; 

    PlayerRankData(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public PlayerRankData() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerRankData(PlayerRankData _o_) {
		this(_o_, null);
	}

	PlayerRankData(PlayerRankData _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.dailyLastFresh = _o_.dailyLastFresh;
		this.weekLastFresh = _o_.weekLastFresh;
		this.monthLastFresh = _o_.monthLastFresh;
		this.dailyFleetHonor = _o_.dailyFleetHonor;
		this.weekFleetHonor = _o_.weekFleetHonor;
		this.monthFleetHonor = _o_.monthFleetHonor;
		this.totalFleetHonor = _o_.totalFleetHonor;
		this.dailyFactionDonate = _o_.dailyFactionDonate;
		this.weekFactionDonate = _o_.weekFactionDonate;
		this.monthFactionDonate = _o_.monthFactionDonate;
		this.totalFactionDonate = _o_.totalFactionDonate;
	}

	public void copyFrom(PlayerRankData _o_) {
        this.dailyLastFresh = _o_.dailyLastFresh;
        this.weekLastFresh = _o_.weekLastFresh;
        this.monthLastFresh = _o_.monthLastFresh;
        this.dailyFleetHonor = _o_.dailyFleetHonor;
        this.weekFleetHonor = _o_.weekFleetHonor;
        this.monthFleetHonor = _o_.monthFleetHonor;
        this.totalFleetHonor = _o_.totalFleetHonor;
        this.dailyFactionDonate = _o_.dailyFactionDonate;
        this.weekFactionDonate = _o_.weekFactionDonate;
        this.monthFactionDonate = _o_.monthFactionDonate;
        this.totalFactionDonate = _o_.totalFactionDonate;
	}


	@JSONField(name = "1")
	public long getDailyLastFresh(){
		return this.dailyLastFresh;
	}

	@JSONField(name = "2")
	public long getWeekLastFresh(){
		return this.weekLastFresh;
	}

	@JSONField(name = "3")
	public long getMonthLastFresh(){
		return this.monthLastFresh;
	}

	@JSONField(name = "4")
	public long getDailyFleetHonor(){
		return this.dailyFleetHonor;
	}

	@JSONField(name = "5")
	public long getWeekFleetHonor(){
		return this.weekFleetHonor;
	}

	@JSONField(name = "6")
	public long getMonthFleetHonor(){
		return this.monthFleetHonor;
	}

	@JSONField(name = "7")
	public long getTotalFleetHonor(){
		return this.totalFleetHonor;
	}

	@JSONField(name = "8")
	public long getDailyFactionDonate(){
		return this.dailyFactionDonate;
	}

	@JSONField(name = "9")
	public long getWeekFactionDonate(){
		return this.weekFactionDonate;
	}

	@JSONField(name = "10")
	public long getMonthFactionDonate(){
		return this.monthFactionDonate;
	}

	@JSONField(name = "11")
	public long getTotalFactionDonate(){
		return this.totalFactionDonate;
	}

	@JSONField(name = "1")
	public void setDailyLastFresh(long _v_){
		this.dailyLastFresh = _v_;
	}

	@JSONField(name = "2")
	public void setWeekLastFresh(long _v_){
		this.weekLastFresh = _v_;
	}

	@JSONField(name = "3")
	public void setMonthLastFresh(long _v_){
		this.monthLastFresh = _v_;
	}

	@JSONField(name = "4")
	public void setDailyFleetHonor(long _v_){
		this.dailyFleetHonor = _v_;
	}

	@JSONField(name = "5")
	public void setWeekFleetHonor(long _v_){
		this.weekFleetHonor = _v_;
	}

	@JSONField(name = "6")
	public void setMonthFleetHonor(long _v_){
		this.monthFleetHonor = _v_;
	}

	@JSONField(name = "7")
	public void setTotalFleetHonor(long _v_){
		this.totalFleetHonor = _v_;
	}

	@JSONField(name = "8")
	public void setDailyFactionDonate(long _v_){
		this.dailyFactionDonate = _v_;
	}

	@JSONField(name = "9")
	public void setWeekFactionDonate(long _v_){
		this.weekFactionDonate = _v_;
	}

	@JSONField(name = "10")
	public void setMonthFactionDonate(long _v_){
		this.monthFactionDonate = _v_;
	}

	@JSONField(name = "11")
	public void setTotalFactionDonate(long _v_){
		this.totalFactionDonate = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerRankData) {
			PlayerRankData _o_ = (PlayerRankData)_o1_;
			if (this.dailyLastFresh != _o_.dailyLastFresh) return false;
			if (this.weekLastFresh != _o_.weekLastFresh) return false;
			if (this.monthLastFresh != _o_.monthLastFresh) return false;
			if (this.dailyFleetHonor != _o_.dailyFleetHonor) return false;
			if (this.weekFleetHonor != _o_.weekFleetHonor) return false;
			if (this.monthFleetHonor != _o_.monthFleetHonor) return false;
			if (this.totalFleetHonor != _o_.totalFleetHonor) return false;
			if (this.dailyFactionDonate != _o_.dailyFactionDonate) return false;
			if (this.weekFactionDonate != _o_.weekFactionDonate) return false;
			if (this.monthFactionDonate != _o_.monthFactionDonate) return false;
			if (this.totalFactionDonate != _o_.totalFactionDonate) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(dailyLastFresh ^ (dailyLastFresh >>> 32));
		_h_ += _h_ * 31 + (int)(weekLastFresh ^ (weekLastFresh >>> 32));
		_h_ += _h_ * 31 + (int)(monthLastFresh ^ (monthLastFresh >>> 32));
		_h_ += _h_ * 31 + (int)(dailyFleetHonor ^ (dailyFleetHonor >>> 32));
		_h_ += _h_ * 31 + (int)(weekFleetHonor ^ (weekFleetHonor >>> 32));
		_h_ += _h_ * 31 + (int)(monthFleetHonor ^ (monthFleetHonor >>> 32));
		_h_ += _h_ * 31 + (int)(totalFleetHonor ^ (totalFleetHonor >>> 32));
		_h_ += _h_ * 31 + (int)(dailyFactionDonate ^ (dailyFactionDonate >>> 32));
		_h_ += _h_ * 31 + (int)(weekFactionDonate ^ (weekFactionDonate >>> 32));
		_h_ += _h_ * 31 + (int)(monthFactionDonate ^ (monthFactionDonate >>> 32));
		_h_ += _h_ * 31 + (int)(totalFactionDonate ^ (totalFactionDonate >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(dailyLastFresh).append(",");
		_sb_.append(weekLastFresh).append(",");
		_sb_.append(monthLastFresh).append(",");
		_sb_.append(dailyFleetHonor).append(",");
		_sb_.append(weekFleetHonor).append(",");
		_sb_.append(monthFleetHonor).append(",");
		_sb_.append(totalFleetHonor).append(",");
		_sb_.append(dailyFactionDonate).append(",");
		_sb_.append(weekFactionDonate).append(",");
		_sb_.append(monthFactionDonate).append(",");
		_sb_.append(totalFactionDonate).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
