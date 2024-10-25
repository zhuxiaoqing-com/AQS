package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class VipInfo extends warrior.mdb.XBean {
    private int id;  // 月卡ID
    private long endtime;  // vip到期时间
    private boolean valid;  // 是否生效
    private long lastTime;  // 上一次刷新时间
    private int buyMakeCount;  // 每日已购买制造次数
    private long lastBuyTime;  // 上一次购买时间

    VipInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public VipInfo() {
		this((warrior.mdb.XBean)null);
	}

	public VipInfo(VipInfo _o_) {
		this(_o_, null);
	}

	VipInfo(VipInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.endtime = _o_.endtime;
		this.valid = _o_.valid;
		this.lastTime = _o_.lastTime;
		this.buyMakeCount = _o_.buyMakeCount;
		this.lastBuyTime = _o_.lastBuyTime;
	}

	public void copyFrom(VipInfo _o_) {
        this.id = _o_.id;
        this.endtime = _o_.endtime;
        this.valid = _o_.valid;
        this.lastTime = _o_.lastTime;
        this.buyMakeCount = _o_.buyMakeCount;
        this.lastBuyTime = _o_.lastBuyTime;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public long getEndtime(){
		return this.endtime;
	}

	@JSONField(name = "3")
	public boolean getValid(){
		return this.valid;
	}

	@JSONField(name = "5")
	public long getLastTime(){
		return this.lastTime;
	}

	@JSONField(name = "6")
	public int getBuyMakeCount(){
		return this.buyMakeCount;
	}

	@JSONField(name = "7")
	public long getLastBuyTime(){
		return this.lastBuyTime;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setEndtime(long _v_){
		this.endtime = _v_;
	}

	@JSONField(name = "3")
	public void setValid(boolean _v_){
		this.valid = _v_;
	}

	@JSONField(name = "5")
	public void setLastTime(long _v_){
		this.lastTime = _v_;
	}

	@JSONField(name = "6")
	public void setBuyMakeCount(int _v_){
		this.buyMakeCount = _v_;
	}

	@JSONField(name = "7")
	public void setLastBuyTime(long _v_){
		this.lastBuyTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof VipInfo) {
			VipInfo _o_ = (VipInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (this.endtime != _o_.endtime) return false;
			if (this.valid != _o_.valid) return false;
			if (this.lastTime != _o_.lastTime) return false;
			if (this.buyMakeCount != _o_.buyMakeCount) return false;
			if (this.lastBuyTime != _o_.lastBuyTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(endtime ^ (endtime >>> 32));
		_h_ += _h_ * 31 + (this.valid ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(lastTime ^ (lastTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.buyMakeCount);
		_h_ += _h_ * 31 + (int)(lastBuyTime ^ (lastBuyTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(endtime).append(",");
		_sb_.append(valid).append(",");
		_sb_.append(lastTime).append(",");
		_sb_.append(buyMakeCount).append(",");
		_sb_.append(lastBuyTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
