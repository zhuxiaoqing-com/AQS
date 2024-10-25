package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class WarfareInfo extends warrior.mdb.XBean {
    private int warfareId;  // 战役id
    private int starNum;  // 星星数
    private long passTime;  // 通关时间(毫秒)
    private int useCount;  // 已使用次数
    private int buyCount;  // 已购买次数

    WarfareInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public WarfareInfo() {
		this((warrior.mdb.XBean)null);
	}

	public WarfareInfo(WarfareInfo _o_) {
		this(_o_, null);
	}

	WarfareInfo(WarfareInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.warfareId = _o_.warfareId;
		this.starNum = _o_.starNum;
		this.passTime = _o_.passTime;
		this.useCount = _o_.useCount;
		this.buyCount = _o_.buyCount;
	}

	public void copyFrom(WarfareInfo _o_) {
        this.warfareId = _o_.warfareId;
        this.starNum = _o_.starNum;
        this.passTime = _o_.passTime;
        this.useCount = _o_.useCount;
        this.buyCount = _o_.buyCount;
	}


	@JSONField(name = "1")
	public int getWarfareId(){
		return this.warfareId;
	}

	@JSONField(name = "2")
	public int getStarNum(){
		return this.starNum;
	}

	@JSONField(name = "3")
	public long getPassTime(){
		return this.passTime;
	}

	@JSONField(name = "4")
	public int getUseCount(){
		return this.useCount;
	}

	@JSONField(name = "5")
	public int getBuyCount(){
		return this.buyCount;
	}

	@JSONField(name = "1")
	public void setWarfareId(int _v_){
		this.warfareId = _v_;
	}

	@JSONField(name = "2")
	public void setStarNum(int _v_){
		this.starNum = _v_;
	}

	@JSONField(name = "3")
	public void setPassTime(long _v_){
		this.passTime = _v_;
	}

	@JSONField(name = "4")
	public void setUseCount(int _v_){
		this.useCount = _v_;
	}

	@JSONField(name = "5")
	public void setBuyCount(int _v_){
		this.buyCount = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WarfareInfo) {
			WarfareInfo _o_ = (WarfareInfo)_o1_;
			if (this.warfareId != _o_.warfareId) return false;
			if (this.starNum != _o_.starNum) return false;
			if (this.passTime != _o_.passTime) return false;
			if (this.useCount != _o_.useCount) return false;
			if (this.buyCount != _o_.buyCount) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.warfareId);
		_h_ += _h_ * 31 + (int)(this.starNum);
		_h_ += _h_ * 31 + (int)(passTime ^ (passTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.useCount);
		_h_ += _h_ * 31 + (int)(this.buyCount);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(warfareId).append(",");
		_sb_.append(starNum).append(",");
		_sb_.append(passTime).append(",");
		_sb_.append(useCount).append(",");
		_sb_.append(buyCount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
