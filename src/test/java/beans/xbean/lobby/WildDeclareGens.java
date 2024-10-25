package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class WildDeclareGens extends warrior.mdb.XBean {
    private long gensId; 
    private long declareTime;  // 宣战时间
    private int gatherId;  // 集结点Id

    WildDeclareGens(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public WildDeclareGens() {
		this((warrior.mdb.XBean)null);
	}

	public WildDeclareGens(WildDeclareGens _o_) {
		this(_o_, null);
	}

	WildDeclareGens(WildDeclareGens _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gensId = _o_.gensId;
		this.declareTime = _o_.declareTime;
		this.gatherId = _o_.gatherId;
	}

	public void copyFrom(WildDeclareGens _o_) {
        this.gensId = _o_.gensId;
        this.declareTime = _o_.declareTime;
        this.gatherId = _o_.gatherId;
	}


	@JSONField(name = "1")
	public long getGensId(){
		return this.gensId;
	}

	@JSONField(name = "2")
	public long getDeclareTime(){
		return this.declareTime;
	}

	@JSONField(name = "3")
	public int getGatherId(){
		return this.gatherId;
	}

	@JSONField(name = "1")
	public void setGensId(long _v_){
		this.gensId = _v_;
	}

	@JSONField(name = "2")
	public void setDeclareTime(long _v_){
		this.declareTime = _v_;
	}

	@JSONField(name = "3")
	public void setGatherId(int _v_){
		this.gatherId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WildDeclareGens) {
			WildDeclareGens _o_ = (WildDeclareGens)_o1_;
			if (this.gensId != _o_.gensId) return false;
			if (this.declareTime != _o_.declareTime) return false;
			if (this.gatherId != _o_.gatherId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gensId ^ (gensId >>> 32));
		_h_ += _h_ * 31 + (int)(declareTime ^ (declareTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.gatherId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gensId).append(",");
		_sb_.append(declareTime).append(",");
		_sb_.append(gatherId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
