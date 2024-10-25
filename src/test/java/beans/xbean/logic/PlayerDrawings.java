package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerDrawings extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<Integer> opendws;  // 已经开启的图纸

    PlayerDrawings(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.opendws = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public PlayerDrawings() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerDrawings(PlayerDrawings _o_) {
		this(_o_, null);
	}

	PlayerDrawings(PlayerDrawings _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.opendws = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.opendws.forEach(_v_ -> this.opendws.add(_v_));
	}

	public void copyFrom(PlayerDrawings _o_) {
        this.opendws = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.opendws.forEach(_v_ -> this.opendws.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getOpendws(){
		return this.opendws;
	}

	@JSONField(name = "1")
	public void setOpendws(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.opendws = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerDrawings) {
			PlayerDrawings _o_ = (PlayerDrawings)_o1_;
			if (!this.opendws.equals(_o_.opendws)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.opendws.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(opendws).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
