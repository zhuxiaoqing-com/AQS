package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensWarDonateBean extends warrior.mdb.XBean {
    private String pname;  // 捐赠人
    private int num;  // 捐赠数量
    private long time;  // 捐赠时间

    GensWarDonateBean(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.pname = "";
	}

	public GensWarDonateBean() {
		this((warrior.mdb.XBean)null);
	}

	public GensWarDonateBean(GensWarDonateBean _o_) {
		this(_o_, null);
	}

	GensWarDonateBean(GensWarDonateBean _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.pname = _o_.pname;
		this.num = _o_.num;
		this.time = _o_.time;
	}

	public void copyFrom(GensWarDonateBean _o_) {
        this.pname = _o_.pname;
        this.num = _o_.num;
        this.time = _o_.time;
	}


	@JSONField(name = "1")
	public String getPname(){
		return this.pname;
	}

	@JSONField(name = "2")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "3")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "1")
	public void setPname(String _v_){
		this.pname = _v_;
	}

	@JSONField(name = "2")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@JSONField(name = "3")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensWarDonateBean) {
			GensWarDonateBean _o_ = (GensWarDonateBean)_o1_;
			if (!this.pname.equals(_o_.pname)) return false;
			if (this.num != _o_.num) return false;
			if (this.time != _o_.time) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.pname.hashCode();
		_h_ += _h_ * 31 + (int)(this.num);
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append("T").append(pname.length()).append(",");
		_sb_.append(num).append(",");
		_sb_.append(time).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
