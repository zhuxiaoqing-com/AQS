package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AllianceBaseInfo extends warrior.mdb.XBean {
    private long id; 
    private String name; 
    private String shortCall; 

    AllianceBaseInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.shortCall = "";
	}

	public AllianceBaseInfo() {
		this((warrior.mdb.XBean)null);
	}

	public AllianceBaseInfo(AllianceBaseInfo _o_) {
		this(_o_, null);
	}

	AllianceBaseInfo(AllianceBaseInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.name = _o_.name;
		this.shortCall = _o_.shortCall;
	}

	public void copyFrom(AllianceBaseInfo _o_) {
        this.id = _o_.id;
        this.name = _o_.name;
        this.shortCall = _o_.shortCall;
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "3")
	public String getShortCall(){
		return this.shortCall;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "3")
	public void setShortCall(String _v_){
		this.shortCall = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AllianceBaseInfo) {
			AllianceBaseInfo _o_ = (AllianceBaseInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (!this.shortCall.equals(_o_.shortCall)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + this.shortCall.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append("T").append(shortCall.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
