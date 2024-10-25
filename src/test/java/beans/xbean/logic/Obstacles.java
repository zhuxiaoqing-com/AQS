package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Obstacles extends warrior.mdb.XBean {
    private long msrl;  // 资源关卡序列号
    private int hp;  // 当前血量

    Obstacles(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public Obstacles() {
		this((warrior.mdb.XBean)null);
	}

	public Obstacles(Obstacles _o_) {
		this(_o_, null);
	}

	Obstacles(Obstacles _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.msrl = _o_.msrl;
		this.hp = _o_.hp;
	}

	public void copyFrom(Obstacles _o_) {
        this.msrl = _o_.msrl;
        this.hp = _o_.hp;
	}


	@JSONField(name = "1")
	public long getMsrl(){
		return this.msrl;
	}

	@JSONField(name = "2")
	public int getHp(){
		return this.hp;
	}

	@JSONField(name = "1")
	public void setMsrl(long _v_){
		this.msrl = _v_;
	}

	@JSONField(name = "2")
	public void setHp(int _v_){
		this.hp = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Obstacles) {
			Obstacles _o_ = (Obstacles)_o1_;
			if (this.msrl != _o_.msrl) return false;
			if (this.hp != _o_.hp) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(msrl ^ (msrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.hp);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(msrl).append(",");
		_sb_.append(hp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
