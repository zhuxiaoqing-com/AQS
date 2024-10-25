package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class GiftWrightBean extends warrior.mdb.XBean {
    private int id;  // 抽取id
    private int wrightNum;  // 权重值
    private boolean cd;  // 是否处于cd状态
    private int cDTimes;  // 概率已经经过的CD次数

    GiftWrightBean(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public GiftWrightBean() {
		this((warrior.mdb.XBean)null);
	}

	public GiftWrightBean(GiftWrightBean _o_) {
		this(_o_, null);
	}

	GiftWrightBean(GiftWrightBean _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.wrightNum = _o_.wrightNum;
		this.cd = _o_.cd;
		this.cDTimes = _o_.cDTimes;
	}

	public void copyFrom(GiftWrightBean _o_) {
        this.id = _o_.id;
        this.wrightNum = _o_.wrightNum;
        this.cd = _o_.cd;
        this.cDTimes = _o_.cDTimes;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getWrightNum(){
		return this.wrightNum;
	}

	@JSONField(name = "3")
	public boolean getCd(){
		return this.cd;
	}

	@JSONField(name = "4")
	public int getCDTimes(){
		return this.cDTimes;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setWrightNum(int _v_){
		this.wrightNum = _v_;
	}

	@JSONField(name = "3")
	public void setCd(boolean _v_){
		this.cd = _v_;
	}

	@JSONField(name = "4")
	public void setCDTimes(int _v_){
		this.cDTimes = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GiftWrightBean) {
			GiftWrightBean _o_ = (GiftWrightBean)_o1_;
			if (this.id != _o_.id) return false;
			if (this.wrightNum != _o_.wrightNum) return false;
			if (this.cd != _o_.cd) return false;
			if (this.cDTimes != _o_.cDTimes) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.wrightNum);
		_h_ += _h_ * 31 + (this.cd ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.cDTimes);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(wrightNum).append(",");
		_sb_.append(cd).append(",");
		_sb_.append(cDTimes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
