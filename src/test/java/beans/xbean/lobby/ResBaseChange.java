package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ResBaseChange extends warrior.mdb.XBean {
    private int changeType;  // 1=增加，2=减少
    private int num;  // 数值
    private int resType;  // 资源类型
    private int gainType;  // 产出来源

    ResBaseChange(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public ResBaseChange() {
		this((warrior.mdb.XBean)null);
	}

	public ResBaseChange(ResBaseChange _o_) {
		this(_o_, null);
	}

	ResBaseChange(ResBaseChange _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.changeType = _o_.changeType;
		this.num = _o_.num;
		this.resType = _o_.resType;
		this.gainType = _o_.gainType;
	}

	public void copyFrom(ResBaseChange _o_) {
        this.changeType = _o_.changeType;
        this.num = _o_.num;
        this.resType = _o_.resType;
        this.gainType = _o_.gainType;
	}


	@JSONField(name = "1")
	public int getChangeType(){
		return this.changeType;
	}

	@JSONField(name = "2")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "3")
	public int getResType(){
		return this.resType;
	}

	@JSONField(name = "4")
	public int getGainType(){
		return this.gainType;
	}

	@JSONField(name = "1")
	public void setChangeType(int _v_){
		this.changeType = _v_;
	}

	@JSONField(name = "2")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@JSONField(name = "3")
	public void setResType(int _v_){
		this.resType = _v_;
	}

	@JSONField(name = "4")
	public void setGainType(int _v_){
		this.gainType = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ResBaseChange) {
			ResBaseChange _o_ = (ResBaseChange)_o1_;
			if (this.changeType != _o_.changeType) return false;
			if (this.num != _o_.num) return false;
			if (this.resType != _o_.resType) return false;
			if (this.gainType != _o_.gainType) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.changeType);
		_h_ += _h_ * 31 + (int)(this.num);
		_h_ += _h_ * 31 + (int)(this.resType);
		_h_ += _h_ * 31 + (int)(this.gainType);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(changeType).append(",");
		_sb_.append(num).append(",");
		_sb_.append(resType).append(",");
		_sb_.append(gainType).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
