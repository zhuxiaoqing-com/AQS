package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class CarrierTable extends warrior.mdb.XBean {
    public static final int LEISURE = 0;   // 空闲
    public static final int RUN = 1;   // 制作中
    public static final int COMPLETE = 2;   // 已完成

    private int mid;  // 制作id
    private int mstate;  // 制作状态 0空闲 1制作中 2已完成
    private long smtime;  // 制作开始时间

    CarrierTable(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public CarrierTable() {
		this((warrior.mdb.XBean)null);
	}

	public CarrierTable(CarrierTable _o_) {
		this(_o_, null);
	}

	CarrierTable(CarrierTable _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.mid = _o_.mid;
		this.mstate = _o_.mstate;
		this.smtime = _o_.smtime;
	}

	public void copyFrom(CarrierTable _o_) {
        this.mid = _o_.mid;
        this.mstate = _o_.mstate;
        this.smtime = _o_.smtime;
	}


	@JSONField(name = "1")
	public int getMid(){
		return this.mid;
	}

	@JSONField(name = "2")
	public int getMstate(){
		return this.mstate;
	}

	@JSONField(name = "3")
	public long getSmtime(){
		return this.smtime;
	}

	@JSONField(name = "1")
	public void setMid(int _v_){
		this.mid = _v_;
	}

	@JSONField(name = "2")
	public void setMstate(int _v_){
		this.mstate = _v_;
	}

	@JSONField(name = "3")
	public void setSmtime(long _v_){
		this.smtime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CarrierTable) {
			CarrierTable _o_ = (CarrierTable)_o1_;
			if (this.mid != _o_.mid) return false;
			if (this.mstate != _o_.mstate) return false;
			if (this.smtime != _o_.smtime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.mid);
		_h_ += _h_ * 31 + (int)(this.mstate);
		_h_ += _h_ * 31 + (int)(smtime ^ (smtime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(mid).append(",");
		_sb_.append(mstate).append(",");
		_sb_.append(smtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
