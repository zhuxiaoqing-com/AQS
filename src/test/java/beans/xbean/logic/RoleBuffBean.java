package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class RoleBuffBean extends warrior.mdb.XBean {
    private int buffId; 
    private int time; 
    private int count; 
    private int overridCount; 
    private String buffBeanExt; 
    private long attachTime; 

    RoleBuffBean(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.buffBeanExt = "";
	}

	public RoleBuffBean() {
		this((warrior.mdb.XBean)null);
	}

	public RoleBuffBean(RoleBuffBean _o_) {
		this(_o_, null);
	}

	RoleBuffBean(RoleBuffBean _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.buffId = _o_.buffId;
		this.time = _o_.time;
		this.count = _o_.count;
		this.overridCount = _o_.overridCount;
		this.buffBeanExt = _o_.buffBeanExt;
		this.attachTime = _o_.attachTime;
	}

	public void copyFrom(RoleBuffBean _o_) {
        this.buffId = _o_.buffId;
        this.time = _o_.time;
        this.count = _o_.count;
        this.overridCount = _o_.overridCount;
        this.buffBeanExt = _o_.buffBeanExt;
        this.attachTime = _o_.attachTime;
	}


	@JSONField(name = "1")
	public int getBuffId(){
		return this.buffId;
	}

	@JSONField(name = "2")
	public int getTime(){
		return this.time;
	}

	@JSONField(name = "3")
	public int getCount(){
		return this.count;
	}

	@JSONField(name = "6")
	public int getOverridCount(){
		return this.overridCount;
	}

	@JSONField(name = "7")
	public String getBuffBeanExt(){
		return this.buffBeanExt;
	}

	@JSONField(name = "8")
	public long getAttachTime(){
		return this.attachTime;
	}

	@JSONField(name = "1")
	public void setBuffId(int _v_){
		this.buffId = _v_;
	}

	@JSONField(name = "2")
	public void setTime(int _v_){
		this.time = _v_;
	}

	@JSONField(name = "3")
	public void setCount(int _v_){
		this.count = _v_;
	}

	@JSONField(name = "6")
	public void setOverridCount(int _v_){
		this.overridCount = _v_;
	}

	@JSONField(name = "7")
	public void setBuffBeanExt(String _v_){
		this.buffBeanExt = _v_;
	}

	@JSONField(name = "8")
	public void setAttachTime(long _v_){
		this.attachTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleBuffBean) {
			RoleBuffBean _o_ = (RoleBuffBean)_o1_;
			if (this.buffId != _o_.buffId) return false;
			if (this.time != _o_.time) return false;
			if (this.count != _o_.count) return false;
			if (this.overridCount != _o_.overridCount) return false;
			if (!this.buffBeanExt.equals(_o_.buffBeanExt)) return false;
			if (this.attachTime != _o_.attachTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.buffId);
		_h_ += _h_ * 31 + (int)(this.time);
		_h_ += _h_ * 31 + (int)(this.count);
		_h_ += _h_ * 31 + (int)(this.overridCount);
		_h_ += _h_ * 31 + this.buffBeanExt.hashCode();
		_h_ += _h_ * 31 + (int)(attachTime ^ (attachTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(buffId).append(",");
		_sb_.append(time).append(",");
		_sb_.append(count).append(",");
		_sb_.append(overridCount).append(",");
		_sb_.append("T").append(buffBeanExt.length()).append(",");
		_sb_.append(attachTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
