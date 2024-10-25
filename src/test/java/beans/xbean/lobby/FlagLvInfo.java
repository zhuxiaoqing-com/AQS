package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FlagLvInfo extends warrior.mdb.XBean {
    private int lvType; 
    private int lv; 
    private int exp; 

    FlagLvInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public FlagLvInfo() {
		this((warrior.mdb.XBean)null);
	}

	public FlagLvInfo(FlagLvInfo _o_) {
		this(_o_, null);
	}

	FlagLvInfo(FlagLvInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.lvType = _o_.lvType;
		this.lv = _o_.lv;
		this.exp = _o_.exp;
	}

	public void copyFrom(FlagLvInfo _o_) {
        this.lvType = _o_.lvType;
        this.lv = _o_.lv;
        this.exp = _o_.exp;
	}


	@JSONField(name = "1")
	public int getLvType(){
		return this.lvType;
	}

	@JSONField(name = "2")
	public int getLv(){
		return this.lv;
	}

	@JSONField(name = "3")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "1")
	public void setLvType(int _v_){
		this.lvType = _v_;
	}

	@JSONField(name = "2")
	public void setLv(int _v_){
		this.lv = _v_;
	}

	@JSONField(name = "3")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FlagLvInfo) {
			FlagLvInfo _o_ = (FlagLvInfo)_o1_;
			if (this.lvType != _o_.lvType) return false;
			if (this.lv != _o_.lv) return false;
			if (this.exp != _o_.exp) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.lvType);
		_h_ += _h_ * 31 + (int)(this.lv);
		_h_ += _h_ * 31 + (int)(this.exp);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(lvType).append(",");
		_sb_.append(lv).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
