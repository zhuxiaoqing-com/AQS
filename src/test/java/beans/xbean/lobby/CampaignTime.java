package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CampaignTime extends warrior.mdb.XBean {
    private long nowOpenTime;  // 当前轮次开启时间
    private long nowCloseTime;  // 当前轮次结束时间
    private long nextOpenTime;  // 下一轮开启时间
    private long nextCloseTime;  // 下一轮结束时间
    private long firstActOpenTime;  // 本服首次竞选日活动开始时间
    private long firstActCloseTime;  // 本服首次竞选日结束时间
    private boolean open; 

    CampaignTime(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public CampaignTime() {
		this((warrior.mdb.XBean)null);
	}

	public CampaignTime(CampaignTime _o_) {
		this(_o_, null);
	}

	CampaignTime(CampaignTime _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.nowOpenTime = _o_.nowOpenTime;
		this.nowCloseTime = _o_.nowCloseTime;
		this.nextOpenTime = _o_.nextOpenTime;
		this.nextCloseTime = _o_.nextCloseTime;
		this.firstActOpenTime = _o_.firstActOpenTime;
		this.firstActCloseTime = _o_.firstActCloseTime;
		this.open = _o_.open;
	}

	public void copyFrom(CampaignTime _o_) {
        this.nowOpenTime = _o_.nowOpenTime;
        this.nowCloseTime = _o_.nowCloseTime;
        this.nextOpenTime = _o_.nextOpenTime;
        this.nextCloseTime = _o_.nextCloseTime;
        this.firstActOpenTime = _o_.firstActOpenTime;
        this.firstActCloseTime = _o_.firstActCloseTime;
        this.open = _o_.open;
	}


	@JSONField(name = "1")
	public long getNowOpenTime(){
		return this.nowOpenTime;
	}

	@JSONField(name = "2")
	public long getNowCloseTime(){
		return this.nowCloseTime;
	}

	@JSONField(name = "3")
	public long getNextOpenTime(){
		return this.nextOpenTime;
	}

	@JSONField(name = "4")
	public long getNextCloseTime(){
		return this.nextCloseTime;
	}

	@JSONField(name = "5")
	public long getFirstActOpenTime(){
		return this.firstActOpenTime;
	}

	@JSONField(name = "6")
	public long getFirstActCloseTime(){
		return this.firstActCloseTime;
	}

	@JSONField(name = "7")
	public boolean getOpen(){
		return this.open;
	}

	@JSONField(name = "1")
	public void setNowOpenTime(long _v_){
		this.nowOpenTime = _v_;
	}

	@JSONField(name = "2")
	public void setNowCloseTime(long _v_){
		this.nowCloseTime = _v_;
	}

	@JSONField(name = "3")
	public void setNextOpenTime(long _v_){
		this.nextOpenTime = _v_;
	}

	@JSONField(name = "4")
	public void setNextCloseTime(long _v_){
		this.nextCloseTime = _v_;
	}

	@JSONField(name = "5")
	public void setFirstActOpenTime(long _v_){
		this.firstActOpenTime = _v_;
	}

	@JSONField(name = "6")
	public void setFirstActCloseTime(long _v_){
		this.firstActCloseTime = _v_;
	}

	@JSONField(name = "7")
	public void setOpen(boolean _v_){
		this.open = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CampaignTime) {
			CampaignTime _o_ = (CampaignTime)_o1_;
			if (this.nowOpenTime != _o_.nowOpenTime) return false;
			if (this.nowCloseTime != _o_.nowCloseTime) return false;
			if (this.nextOpenTime != _o_.nextOpenTime) return false;
			if (this.nextCloseTime != _o_.nextCloseTime) return false;
			if (this.firstActOpenTime != _o_.firstActOpenTime) return false;
			if (this.firstActCloseTime != _o_.firstActCloseTime) return false;
			if (this.open != _o_.open) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(nowOpenTime ^ (nowOpenTime >>> 32));
		_h_ += _h_ * 31 + (int)(nowCloseTime ^ (nowCloseTime >>> 32));
		_h_ += _h_ * 31 + (int)(nextOpenTime ^ (nextOpenTime >>> 32));
		_h_ += _h_ * 31 + (int)(nextCloseTime ^ (nextCloseTime >>> 32));
		_h_ += _h_ * 31 + (int)(firstActOpenTime ^ (firstActOpenTime >>> 32));
		_h_ += _h_ * 31 + (int)(firstActCloseTime ^ (firstActCloseTime >>> 32));
		_h_ += _h_ * 31 + (this.open ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(nowOpenTime).append(",");
		_sb_.append(nowCloseTime).append(",");
		_sb_.append(nextOpenTime).append(",");
		_sb_.append(nextCloseTime).append(",");
		_sb_.append(firstActOpenTime).append(",");
		_sb_.append(firstActCloseTime).append(",");
		_sb_.append(open).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
