package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensImpeach extends warrior.mdb.XBean {
    private long createSrl;  // 弹劾发起者
    private long createTime;  // 弹劾发起时间
    private int totalNum;  // 发起弹劾时的总人数
    private java.util.concurrent.CopyOnWriteArraySet<Long> agree;  // 同意的人
    private java.util.concurrent.CopyOnWriteArraySet<Long> disagree;  // 不同意的人

    GensImpeach(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.agree = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.disagree = new java.util.concurrent.CopyOnWriteArraySet<Long>();
	}

	public GensImpeach() {
		this((warrior.mdb.XBean)null);
	}

	public GensImpeach(GensImpeach _o_) {
		this(_o_, null);
	}

	GensImpeach(GensImpeach _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.createSrl = _o_.createSrl;
		this.createTime = _o_.createTime;
		this.totalNum = _o_.totalNum;
		this.agree = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.agree.forEach(_v_ -> this.agree.add(_v_));
		this.disagree = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.disagree.forEach(_v_ -> this.disagree.add(_v_));
	}

	public void copyFrom(GensImpeach _o_) {
        this.createSrl = _o_.createSrl;
        this.createTime = _o_.createTime;
        this.totalNum = _o_.totalNum;
        this.agree = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.agree.forEach(_v_ -> this.agree.add(_v_));
        this.disagree = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.disagree.forEach(_v_ -> this.disagree.add(_v_));
	}


	@JSONField(name = "1")
	public long getCreateSrl(){
		return this.createSrl;
	}

	@JSONField(name = "2")
	public long getCreateTime(){
		return this.createTime;
	}

	@JSONField(name = "3")
	public int getTotalNum(){
		return this.totalNum;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getAgree(){
		return this.agree;
	}

	@JSONField(name = "5")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getDisagree(){
		return this.disagree;
	}

	@JSONField(name = "1")
	public void setCreateSrl(long _v_){
		this.createSrl = _v_;
	}

	@JSONField(name = "2")
	public void setCreateTime(long _v_){
		this.createTime = _v_;
	}

	@JSONField(name = "3")
	public void setTotalNum(int _v_){
		this.totalNum = _v_;
	}

	@JSONField(name = "4")
	public void setAgree(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.agree = _v_;
	}

	@JSONField(name = "5")
	public void setDisagree(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.disagree = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensImpeach) {
			GensImpeach _o_ = (GensImpeach)_o1_;
			if (this.createSrl != _o_.createSrl) return false;
			if (this.createTime != _o_.createTime) return false;
			if (this.totalNum != _o_.totalNum) return false;
			if (!this.agree.equals(_o_.agree)) return false;
			if (!this.disagree.equals(_o_.disagree)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(createSrl ^ (createSrl >>> 32));
		_h_ += _h_ * 31 + (int)(createTime ^ (createTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.totalNum);
		_h_ += _h_ * 31 + this.agree.hashCode();
		_h_ += _h_ * 31 + this.disagree.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(createSrl).append(",");
		_sb_.append(createTime).append(",");
		_sb_.append(totalNum).append(",");
		_sb_.append(agree).append(",");
		_sb_.append(disagree).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
