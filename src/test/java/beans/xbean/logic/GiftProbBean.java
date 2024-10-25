package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class GiftProbBean extends warrior.mdb.XBean {
    private int id;  // 抽取id
    private int probTimes;  // 概率已经随机过的次数
    private boolean cd;  // 是否处于cd状态
    private int cDTimes;  // 概率已经经过的CD次数

    GiftProbBean(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public GiftProbBean() {
		this((warrior.mdb.XBean)null);
	}

	public GiftProbBean(GiftProbBean _o_) {
		this(_o_, null);
	}

	GiftProbBean(GiftProbBean _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.probTimes = _o_.probTimes;
		this.cd = _o_.cd;
		this.cDTimes = _o_.cDTimes;
	}

	public void copyFrom(GiftProbBean _o_) {
        this.id = _o_.id;
        this.probTimes = _o_.probTimes;
        this.cd = _o_.cd;
        this.cDTimes = _o_.cDTimes;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getProbTimes(){
		return this.probTimes;
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
	public void setProbTimes(int _v_){
		this.probTimes = _v_;
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
		if (_o1_ instanceof GiftProbBean) {
			GiftProbBean _o_ = (GiftProbBean)_o1_;
			if (this.id != _o_.id) return false;
			if (this.probTimes != _o_.probTimes) return false;
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
		_h_ += _h_ * 31 + (int)(this.probTimes);
		_h_ += _h_ * 31 + (this.cd ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.cDTimes);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(probTimes).append(",");
		_sb_.append(cd).append(",");
		_sb_.append(cDTimes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
