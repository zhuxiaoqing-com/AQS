package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class CollectionInfo extends warrior.mdb.XBean {
    private int resId;  // 采集物id
    private int level;  // 采集物等级
    private int exp;  // 经验

    CollectionInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public CollectionInfo() {
		this((warrior.mdb.XBean)null);
	}

	public CollectionInfo(CollectionInfo _o_) {
		this(_o_, null);
	}

	CollectionInfo(CollectionInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.resId = _o_.resId;
		this.level = _o_.level;
		this.exp = _o_.exp;
	}

	public void copyFrom(CollectionInfo _o_) {
        this.resId = _o_.resId;
        this.level = _o_.level;
        this.exp = _o_.exp;
	}


	@JSONField(name = "1")
	public int getResId(){
		return this.resId;
	}

	@JSONField(name = "2")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "3")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "1")
	public void setResId(int _v_){
		this.resId = _v_;
	}

	@JSONField(name = "2")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "3")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CollectionInfo) {
			CollectionInfo _o_ = (CollectionInfo)_o1_;
			if (this.resId != _o_.resId) return false;
			if (this.level != _o_.level) return false;
			if (this.exp != _o_.exp) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.resId);
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + (int)(this.exp);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(resId).append(",");
		_sb_.append(level).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
