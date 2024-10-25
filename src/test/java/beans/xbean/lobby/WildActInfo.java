package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class WildActInfo extends warrior.mdb.XBean {
    private int faction;  // 阵营
    private xbean.lobby.ActTime actTime;  // 活动开始结束时间
    private boolean canOpen;  // 是否条件达成可以开启
    private int startNum;  // 开始场次 阵营战开始+1
    private int dayStartNum;  // 当天开始场次
    private int condition;  // 条件数量
    private boolean specialOpen;  // 特殊场次是否已经开启过
    private long nextFightNotice;  // 下一次战争中消息发送时间

    WildActInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actTime = new ActTime(this);
	}

	public WildActInfo() {
		this((warrior.mdb.XBean)null);
	}

	public WildActInfo(WildActInfo _o_) {
		this(_o_, null);
	}

	WildActInfo(WildActInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.faction = _o_.faction;
		this.actTime = new ActTime(_o_.actTime, this);
		this.canOpen = _o_.canOpen;
		this.startNum = _o_.startNum;
		this.dayStartNum = _o_.dayStartNum;
		this.condition = _o_.condition;
		this.specialOpen = _o_.specialOpen;
		this.nextFightNotice = _o_.nextFightNotice;
	}

	public void copyFrom(WildActInfo _o_) {
        this.faction = _o_.faction;
        this.actTime = new ActTime(_o_.actTime, this);
        this.canOpen = _o_.canOpen;
        this.startNum = _o_.startNum;
        this.dayStartNum = _o_.dayStartNum;
        this.condition = _o_.condition;
        this.specialOpen = _o_.specialOpen;
        this.nextFightNotice = _o_.nextFightNotice;
	}


	@JSONField(name = "1")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "2")
	public xbean.lobby.ActTime getActTime(){
		return this.actTime;
	}

	@JSONField(name = "3")
	public boolean getCanOpen(){
		return this.canOpen;
	}

	@JSONField(name = "8")
	public int getStartNum(){
		return this.startNum;
	}

	@JSONField(name = "9")
	public int getDayStartNum(){
		return this.dayStartNum;
	}

	@JSONField(name = "10")
	public int getCondition(){
		return this.condition;
	}

	@JSONField(name = "11")
	public boolean getSpecialOpen(){
		return this.specialOpen;
	}

	@JSONField(name = "12")
	public long getNextFightNotice(){
		return this.nextFightNotice;
	}

	@JSONField(name = "1")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "2")
	public void setActTime(xbean.lobby.ActTime _v_){
		this.actTime = _v_;
	}

	@JSONField(name = "3")
	public void setCanOpen(boolean _v_){
		this.canOpen = _v_;
	}

	@JSONField(name = "8")
	public void setStartNum(int _v_){
		this.startNum = _v_;
	}

	@JSONField(name = "9")
	public void setDayStartNum(int _v_){
		this.dayStartNum = _v_;
	}

	@JSONField(name = "10")
	public void setCondition(int _v_){
		this.condition = _v_;
	}

	@JSONField(name = "11")
	public void setSpecialOpen(boolean _v_){
		this.specialOpen = _v_;
	}

	@JSONField(name = "12")
	public void setNextFightNotice(long _v_){
		this.nextFightNotice = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WildActInfo) {
			WildActInfo _o_ = (WildActInfo)_o1_;
			if (this.faction != _o_.faction) return false;
			if (!this.actTime.equals(_o_.actTime)) return false;
			if (this.canOpen != _o_.canOpen) return false;
			if (this.startNum != _o_.startNum) return false;
			if (this.dayStartNum != _o_.dayStartNum) return false;
			if (this.condition != _o_.condition) return false;
			if (this.specialOpen != _o_.specialOpen) return false;
			if (this.nextFightNotice != _o_.nextFightNotice) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + this.actTime.hashCode();
		_h_ += _h_ * 31 + (this.canOpen ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.startNum);
		_h_ += _h_ * 31 + (int)(this.dayStartNum);
		_h_ += _h_ * 31 + (int)(this.condition);
		_h_ += _h_ * 31 + (this.specialOpen ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(nextFightNotice ^ (nextFightNotice >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(faction).append(",");
		_sb_.append(actTime).append(",");
		_sb_.append(canOpen).append(",");
		_sb_.append(startNum).append(",");
		_sb_.append(dayStartNum).append(",");
		_sb_.append(condition).append(",");
		_sb_.append(specialOpen).append(",");
		_sb_.append(nextFightNotice).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
