package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AllianceJoinApply extends warrior.mdb.XBean {
    private long gid;  // 军团id
    private long applyTime;  // 申请时间
    private long endTime;  // 失效时间
    private int giftMoney;  // 礼金数额
    private String message;  // 申请留言

    AllianceJoinApply(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.message = "";
	}

	public AllianceJoinApply() {
		this((warrior.mdb.XBean)null);
	}

	public AllianceJoinApply(AllianceJoinApply _o_) {
		this(_o_, null);
	}

	AllianceJoinApply(AllianceJoinApply _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gid = _o_.gid;
		this.applyTime = _o_.applyTime;
		this.endTime = _o_.endTime;
		this.giftMoney = _o_.giftMoney;
		this.message = _o_.message;
	}

	public void copyFrom(AllianceJoinApply _o_) {
        this.gid = _o_.gid;
        this.applyTime = _o_.applyTime;
        this.endTime = _o_.endTime;
        this.giftMoney = _o_.giftMoney;
        this.message = _o_.message;
	}


	@JSONField(name = "1")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "3")
	public long getApplyTime(){
		return this.applyTime;
	}

	@JSONField(name = "4")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "5")
	public int getGiftMoney(){
		return this.giftMoney;
	}

	@JSONField(name = "6")
	public String getMessage(){
		return this.message;
	}

	@JSONField(name = "1")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "3")
	public void setApplyTime(long _v_){
		this.applyTime = _v_;
	}

	@JSONField(name = "4")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "5")
	public void setGiftMoney(int _v_){
		this.giftMoney = _v_;
	}

	@JSONField(name = "6")
	public void setMessage(String _v_){
		this.message = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AllianceJoinApply) {
			AllianceJoinApply _o_ = (AllianceJoinApply)_o1_;
			if (this.gid != _o_.gid) return false;
			if (this.applyTime != _o_.applyTime) return false;
			if (this.endTime != _o_.endTime) return false;
			if (this.giftMoney != _o_.giftMoney) return false;
			if (!this.message.equals(_o_.message)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + (int)(applyTime ^ (applyTime >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.giftMoney);
		_h_ += _h_ * 31 + this.message.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gid).append(",");
		_sb_.append(applyTime).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(giftMoney).append(",");
		_sb_.append("T").append(message.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
