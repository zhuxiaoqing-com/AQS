package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionTarget extends warrior.mdb.XBean {
    private int id;  // 任务id
    private int state;  // 任务状态
    private int num;  // 数量

    FactionTarget(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public FactionTarget() {
		this((warrior.mdb.XBean)null);
	}

	public FactionTarget(FactionTarget _o_) {
		this(_o_, null);
	}

	FactionTarget(FactionTarget _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.state = _o_.state;
		this.num = _o_.num;
	}

	public void copyFrom(FactionTarget _o_) {
        this.id = _o_.id;
        this.state = _o_.state;
        this.num = _o_.num;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "3")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "3")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionTarget) {
			FactionTarget _o_ = (FactionTarget)_o1_;
			if (this.id != _o_.id) return false;
			if (this.state != _o_.state) return false;
			if (this.num != _o_.num) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(this.num);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(state).append(",");
		_sb_.append(num).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
