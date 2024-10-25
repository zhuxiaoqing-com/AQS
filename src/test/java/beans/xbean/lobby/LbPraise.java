package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LbPraise extends warrior.mdb.XBean {
    private int id;  // 任务id
    private long srl;  // srl
    private int num;  // 当前生效点赞数
    private int createMill;  // 创建时间戳

    LbPraise(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public LbPraise() {
		this((warrior.mdb.XBean)null);
	}

	public LbPraise(LbPraise _o_) {
		this(_o_, null);
	}

	LbPraise(LbPraise _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.srl = _o_.srl;
		this.num = _o_.num;
		this.createMill = _o_.createMill;
	}

	public void copyFrom(LbPraise _o_) {
        this.id = _o_.id;
        this.srl = _o_.srl;
        this.num = _o_.num;
        this.createMill = _o_.createMill;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "3")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "4")
	public int getCreateMill(){
		return this.createMill;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "3")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@JSONField(name = "4")
	public void setCreateMill(int _v_){
		this.createMill = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LbPraise) {
			LbPraise _o_ = (LbPraise)_o1_;
			if (this.id != _o_.id) return false;
			if (this.srl != _o_.srl) return false;
			if (this.num != _o_.num) return false;
			if (this.createMill != _o_.createMill) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(this.num);
		_h_ += _h_ * 31 + (int)(this.createMill);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(srl).append(",");
		_sb_.append(num).append(",");
		_sb_.append(createMill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
