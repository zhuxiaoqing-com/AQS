package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class NationInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> donateScore;  // 国家发展度 key:国家id value:国家发展度

    NationInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.donateScore = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public NationInfo() {
		this((warrior.mdb.XBean)null);
	}

	public NationInfo(NationInfo _o_) {
		this(_o_, null);
	}

	NationInfo(NationInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.donateScore = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.donateScore.forEach((_k_, _v_) -> this.donateScore.put(_k_, _v_));
	}

	public void copyFrom(NationInfo _o_) {
        this.donateScore = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.donateScore.forEach((_k_, _v_) -> this.donateScore.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDonateScore(){
		return this.donateScore;
	}

	@JSONField(name = "1")
	public void setDonateScore(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.donateScore = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof NationInfo) {
			NationInfo _o_ = (NationInfo)_o1_;
			if (!this.donateScore.equals(_o_.donateScore)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.donateScore.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(donateScore).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
