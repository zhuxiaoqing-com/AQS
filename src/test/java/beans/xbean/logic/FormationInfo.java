package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class FormationInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> formation;  // 阵型

    FormationInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.formation = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public FormationInfo() {
		this((warrior.mdb.XBean)null);
	}

	public FormationInfo(FormationInfo _o_) {
		this(_o_, null);
	}

	FormationInfo(FormationInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.formation = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.formation.forEach((_k_, _v_) -> this.formation.put(_k_, _v_));
	}

	public void copyFrom(FormationInfo _o_) {
        this.formation = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.formation.forEach((_k_, _v_) -> this.formation.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getFormation(){
		return this.formation;
	}

	@JSONField(name = "1")
	public void setFormation(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.formation = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FormationInfo) {
			FormationInfo _o_ = (FormationInfo)_o1_;
			if (!this.formation.equals(_o_.formation)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.formation.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(formation).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
