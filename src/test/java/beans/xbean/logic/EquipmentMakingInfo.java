package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class EquipmentMakingInfo extends warrior.mdb.XBean {
    public static final int LINE_UP = 0;   // 排队中
    public static final int RUN = 1;   // 制作中
    public static final int COMPLETE = 2;   // 已完成

    private long msrl;  // 制作序列号
    private int mid;  // 制作id
    private int mstate;  // 制作状态 0排队中 1制作中 2已完成
    private long addTime;  // 添加队列时间
    private long smtime;  // 制作开始时间

    EquipmentMakingInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public EquipmentMakingInfo() {
		this((warrior.mdb.XBean)null);
	}

	public EquipmentMakingInfo(EquipmentMakingInfo _o_) {
		this(_o_, null);
	}

	EquipmentMakingInfo(EquipmentMakingInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.msrl = _o_.msrl;
		this.mid = _o_.mid;
		this.mstate = _o_.mstate;
		this.addTime = _o_.addTime;
		this.smtime = _o_.smtime;
	}

	public void copyFrom(EquipmentMakingInfo _o_) {
        this.msrl = _o_.msrl;
        this.mid = _o_.mid;
        this.mstate = _o_.mstate;
        this.addTime = _o_.addTime;
        this.smtime = _o_.smtime;
	}


	@JSONField(name = "1")
	public long getMsrl(){
		return this.msrl;
	}

	@JSONField(name = "2")
	public int getMid(){
		return this.mid;
	}

	@JSONField(name = "3")
	public int getMstate(){
		return this.mstate;
	}

	@JSONField(name = "4")
	public long getAddTime(){
		return this.addTime;
	}

	@JSONField(name = "5")
	public long getSmtime(){
		return this.smtime;
	}

	@JSONField(name = "1")
	public void setMsrl(long _v_){
		this.msrl = _v_;
	}

	@JSONField(name = "2")
	public void setMid(int _v_){
		this.mid = _v_;
	}

	@JSONField(name = "3")
	public void setMstate(int _v_){
		this.mstate = _v_;
	}

	@JSONField(name = "4")
	public void setAddTime(long _v_){
		this.addTime = _v_;
	}

	@JSONField(name = "5")
	public void setSmtime(long _v_){
		this.smtime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EquipmentMakingInfo) {
			EquipmentMakingInfo _o_ = (EquipmentMakingInfo)_o1_;
			if (this.msrl != _o_.msrl) return false;
			if (this.mid != _o_.mid) return false;
			if (this.mstate != _o_.mstate) return false;
			if (this.addTime != _o_.addTime) return false;
			if (this.smtime != _o_.smtime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(msrl ^ (msrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.mid);
		_h_ += _h_ * 31 + (int)(this.mstate);
		_h_ += _h_ * 31 + (int)(addTime ^ (addTime >>> 32));
		_h_ += _h_ * 31 + (int)(smtime ^ (smtime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(msrl).append(",");
		_sb_.append(mid).append(",");
		_sb_.append(mstate).append(",");
		_sb_.append(addTime).append(",");
		_sb_.append(smtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
