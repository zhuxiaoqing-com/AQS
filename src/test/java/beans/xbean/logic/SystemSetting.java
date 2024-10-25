package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class SystemSetting extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, SystemSettingBean> settings;  // type value

    SystemSetting(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.settings = new java.util.concurrent.ConcurrentHashMap<Integer, SystemSettingBean>();
	}

	public SystemSetting() {
		this((warrior.mdb.XBean)null);
	}

	public SystemSetting(SystemSetting _o_) {
		this(_o_, null);
	}

	SystemSetting(SystemSetting _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.settings = new java.util.concurrent.ConcurrentHashMap<Integer, SystemSettingBean>();
		_o_.settings.forEach((_k_, _v_) -> this.settings.put(_k_, new SystemSettingBean(_v_)));
	}

	public void copyFrom(SystemSetting _o_) {
        this.settings = new java.util.concurrent.ConcurrentHashMap<Integer, SystemSettingBean>();
		_o_.settings.forEach((_k_, _v_) -> this.settings.put(_k_, new SystemSettingBean(_v_)));
	}


	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, SystemSettingBean> getSettings(){
		return this.settings;
	}

	@JSONField(name = "2")
	public void setSettings(java.util.concurrent.ConcurrentHashMap<Integer, SystemSettingBean> _v_){
		this.settings = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SystemSetting) {
			SystemSetting _o_ = (SystemSetting)_o1_;
			if (!this.settings.equals(_o_.settings)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.settings.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(settings).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
