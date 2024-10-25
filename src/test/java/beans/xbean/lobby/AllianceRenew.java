package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AllianceRenew extends warrior.mdb.XBean {
    private int day;  // 续期的天数
    private java.util.concurrent.ConcurrentHashMap<Long, Integer> respGensId;  // 已反馈的军团(同意1,拒绝0)
    private long expire;  // 过期时间
    private int margin;  // 续期保证金
    private boolean approve;  // 是否生效

    AllianceRenew(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.respGensId = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
	}

	public AllianceRenew() {
		this((warrior.mdb.XBean)null);
	}

	public AllianceRenew(AllianceRenew _o_) {
		this(_o_, null);
	}

	AllianceRenew(AllianceRenew _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.day = _o_.day;
		this.respGensId = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.respGensId.forEach((_k_, _v_) -> this.respGensId.put(_k_, _v_));
		this.expire = _o_.expire;
		this.margin = _o_.margin;
		this.approve = _o_.approve;
	}

	public void copyFrom(AllianceRenew _o_) {
        this.day = _o_.day;
        this.respGensId = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.respGensId.forEach((_k_, _v_) -> this.respGensId.put(_k_, _v_));
        this.expire = _o_.expire;
        this.margin = _o_.margin;
        this.approve = _o_.approve;
	}


	@JSONField(name = "1")
	public int getDay(){
		return this.day;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, Integer> getRespGensId(){
		return this.respGensId;
	}

	@JSONField(name = "3")
	public long getExpire(){
		return this.expire;
	}

	@JSONField(name = "4")
	public int getMargin(){
		return this.margin;
	}

	@JSONField(name = "5")
	public boolean getApprove(){
		return this.approve;
	}

	@JSONField(name = "1")
	public void setDay(int _v_){
		this.day = _v_;
	}

	@JSONField(name = "2")
	public void setRespGensId(java.util.concurrent.ConcurrentHashMap<Long, Integer> _v_){
		this.respGensId = _v_;
	}

	@JSONField(name = "3")
	public void setExpire(long _v_){
		this.expire = _v_;
	}

	@JSONField(name = "4")
	public void setMargin(int _v_){
		this.margin = _v_;
	}

	@JSONField(name = "5")
	public void setApprove(boolean _v_){
		this.approve = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AllianceRenew) {
			AllianceRenew _o_ = (AllianceRenew)_o1_;
			if (this.day != _o_.day) return false;
			if (!this.respGensId.equals(_o_.respGensId)) return false;
			if (this.expire != _o_.expire) return false;
			if (this.margin != _o_.margin) return false;
			if (this.approve != _o_.approve) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.day);
		_h_ += _h_ * 31 + this.respGensId.hashCode();
		_h_ += _h_ * 31 + (int)(expire ^ (expire >>> 32));
		_h_ += _h_ * 31 + (int)(this.margin);
		_h_ += _h_ * 31 + (this.approve ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(day).append(",");
		_sb_.append(respGensId).append(",");
		_sb_.append(expire).append(",");
		_sb_.append(margin).append(",");
		_sb_.append(approve).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
