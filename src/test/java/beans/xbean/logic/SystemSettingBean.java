package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class SystemSettingBean extends warrior.mdb.XBean {
    private int type; 
    private int value1; 
    private long setTime;  // 设置时间

    SystemSettingBean(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public SystemSettingBean() {
		this((warrior.mdb.XBean)null);
	}

	public SystemSettingBean(SystemSettingBean _o_) {
		this(_o_, null);
	}

	SystemSettingBean(SystemSettingBean _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.type = _o_.type;
		this.value1 = _o_.value1;
		this.setTime = _o_.setTime;
	}

	public void copyFrom(SystemSettingBean _o_) {
        this.type = _o_.type;
        this.value1 = _o_.value1;
        this.setTime = _o_.setTime;
	}


	@JSONField(name = "1")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "2")
	public int getValue1(){
		return this.value1;
	}

	@JSONField(name = "3")
	public long getSetTime(){
		return this.setTime;
	}

	@JSONField(name = "1")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "2")
	public void setValue1(int _v_){
		this.value1 = _v_;
	}

	@JSONField(name = "3")
	public void setSetTime(long _v_){
		this.setTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SystemSettingBean) {
			SystemSettingBean _o_ = (SystemSettingBean)_o1_;
			if (this.type != _o_.type) return false;
			if (this.value1 != _o_.value1) return false;
			if (this.setTime != _o_.setTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(this.value1);
		_h_ += _h_ * 31 + (int)(setTime ^ (setTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(type).append(",");
		_sb_.append(value1).append(",");
		_sb_.append(setTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
