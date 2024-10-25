package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Pos extends warrior.mdb.XBean {
    private int x;  // x
    private int z;  // z

    Pos(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public Pos() {
		this((warrior.mdb.XBean)null);
	}

	public Pos(Pos _o_) {
		this(_o_, null);
	}

	Pos(Pos _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.x = _o_.x;
		this.z = _o_.z;
	}

	public void copyFrom(Pos _o_) {
        this.x = _o_.x;
        this.z = _o_.z;
	}


	@JSONField(name = "1")
	public int getX(){
		return this.x;
	}

	@JSONField(name = "2")
	public int getZ(){
		return this.z;
	}

	@JSONField(name = "1")
	public void setX(int _v_){
		this.x = _v_;
	}

	@JSONField(name = "2")
	public void setZ(int _v_){
		this.z = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Pos) {
			Pos _o_ = (Pos)_o1_;
			if (this.x != _o_.x) return false;
			if (this.z != _o_.z) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.x);
		_h_ += _h_ * 31 + (int)(this.z);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(x).append(",");
		_sb_.append(z).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
