package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ResBaseStealLog extends warrior.mdb.XBean {
    private String name;  // 名字
    private int resId;  // 资源id
    private int resNum;  // 数量
    private long time;  // 时间

    ResBaseStealLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
	}

	public ResBaseStealLog() {
		this((warrior.mdb.XBean)null);
	}

	public ResBaseStealLog(ResBaseStealLog _o_) {
		this(_o_, null);
	}

	ResBaseStealLog(ResBaseStealLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.name = _o_.name;
		this.resId = _o_.resId;
		this.resNum = _o_.resNum;
		this.time = _o_.time;
	}

	public void copyFrom(ResBaseStealLog _o_) {
        this.name = _o_.name;
        this.resId = _o_.resId;
        this.resNum = _o_.resNum;
        this.time = _o_.time;
	}


	@JSONField(name = "1")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "2")
	public int getResId(){
		return this.resId;
	}

	@JSONField(name = "3")
	public int getResNum(){
		return this.resNum;
	}

	@JSONField(name = "4")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "1")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "2")
	public void setResId(int _v_){
		this.resId = _v_;
	}

	@JSONField(name = "3")
	public void setResNum(int _v_){
		this.resNum = _v_;
	}

	@JSONField(name = "4")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ResBaseStealLog) {
			ResBaseStealLog _o_ = (ResBaseStealLog)_o1_;
			if (!this.name.equals(_o_.name)) return false;
			if (this.resId != _o_.resId) return false;
			if (this.resNum != _o_.resNum) return false;
			if (this.time != _o_.time) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + (int)(this.resId);
		_h_ += _h_ * 31 + (int)(this.resNum);
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(resId).append(",");
		_sb_.append(resNum).append(",");
		_sb_.append(time).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
