package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerBattleLog extends warrior.mdb.XBean {
    private long srl; 
    private long startTime; 
    private long endTime; 
    private xbean.lobby.PlayerAreaReportInfo playerReport;  // 个人数据
    private boolean alreadyReceive;  // 已经领取过奖励

    PlayerBattleLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerReport = new PlayerAreaReportInfo(this);
	}

	public PlayerBattleLog() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerBattleLog(PlayerBattleLog _o_) {
		this(_o_, null);
	}

	PlayerBattleLog(PlayerBattleLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.startTime = _o_.startTime;
		this.endTime = _o_.endTime;
		this.playerReport = new PlayerAreaReportInfo(_o_.playerReport, this);
		this.alreadyReceive = _o_.alreadyReceive;
	}

	public void copyFrom(PlayerBattleLog _o_) {
        this.srl = _o_.srl;
        this.startTime = _o_.startTime;
        this.endTime = _o_.endTime;
        this.playerReport = new PlayerAreaReportInfo(_o_.playerReport, this);
        this.alreadyReceive = _o_.alreadyReceive;
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "3")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "6")
	public xbean.lobby.PlayerAreaReportInfo getPlayerReport(){
		return this.playerReport;
	}

	@JSONField(name = "7")
	public boolean getAlreadyReceive(){
		return this.alreadyReceive;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "3")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "6")
	public void setPlayerReport(xbean.lobby.PlayerAreaReportInfo _v_){
		this.playerReport = _v_;
	}

	@JSONField(name = "7")
	public void setAlreadyReceive(boolean _v_){
		this.alreadyReceive = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerBattleLog) {
			PlayerBattleLog _o_ = (PlayerBattleLog)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.startTime != _o_.startTime) return false;
			if (this.endTime != _o_.endTime) return false;
			if (!this.playerReport.equals(_o_.playerReport)) return false;
			if (this.alreadyReceive != _o_.alreadyReceive) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + this.playerReport.hashCode();
		_h_ += _h_ * 31 + (this.alreadyReceive ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(playerReport).append(",");
		_sb_.append(alreadyReceive).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
