package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerSevenLogin extends warrior.mdb.XBean {
    private xbean.logic.LGActLogicInfo actInfo;  // 活动数据
    private int day; 
    private long dayRefresh;  // 日期更新时间
    private java.util.concurrent.CopyOnWriteArraySet<Integer> received;  // 领取过的奖励

    PlayerSevenLogin(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actInfo = new LGActLogicInfo(this);
		this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public PlayerSevenLogin() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerSevenLogin(PlayerSevenLogin _o_) {
		this(_o_, null);
	}

	PlayerSevenLogin(PlayerSevenLogin _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
		this.day = _o_.day;
		this.dayRefresh = _o_.dayRefresh;
		this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.received.forEach(_v_ -> this.received.add(_v_));
	}

	public void copyFrom(PlayerSevenLogin _o_) {
        this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
        this.day = _o_.day;
        this.dayRefresh = _o_.dayRefresh;
        this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.received.forEach(_v_ -> this.received.add(_v_));
	}


	@JSONField(name = "1")
	public xbean.logic.LGActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "3")
	public int getDay(){
		return this.day;
	}

	@JSONField(name = "4")
	public long getDayRefresh(){
		return this.dayRefresh;
	}

	@JSONField(name = "5")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getReceived(){
		return this.received;
	}

	@JSONField(name = "1")
	public void setActInfo(xbean.logic.LGActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@JSONField(name = "3")
	public void setDay(int _v_){
		this.day = _v_;
	}

	@JSONField(name = "4")
	public void setDayRefresh(long _v_){
		this.dayRefresh = _v_;
	}

	@JSONField(name = "5")
	public void setReceived(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.received = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerSevenLogin) {
			PlayerSevenLogin _o_ = (PlayerSevenLogin)_o1_;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			if (this.day != _o_.day) return false;
			if (this.dayRefresh != _o_.dayRefresh) return false;
			if (!this.received.equals(_o_.received)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		_h_ += _h_ * 31 + (int)(this.day);
		_h_ += _h_ * 31 + (int)(dayRefresh ^ (dayRefresh >>> 32));
		_h_ += _h_ * 31 + this.received.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actInfo).append(",");
		_sb_.append(day).append(",");
		_sb_.append(dayRefresh).append(",");
		_sb_.append(received).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
