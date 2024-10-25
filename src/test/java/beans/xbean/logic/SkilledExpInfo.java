package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class SkilledExpInfo extends warrior.mdb.XBean {
    private int id;  // 配置ID
    private int lv;  // 当前等级
    private int exp;  // 当前经验
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> itemExp;  // 道具获得的经验 k:条件表id

    SkilledExpInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.itemExp = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public SkilledExpInfo() {
		this((warrior.mdb.XBean)null);
	}

	public SkilledExpInfo(SkilledExpInfo _o_) {
		this(_o_, null);
	}

	SkilledExpInfo(SkilledExpInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.lv = _o_.lv;
		this.exp = _o_.exp;
		this.itemExp = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.itemExp.forEach((_k_, _v_) -> this.itemExp.put(_k_, _v_));
	}

	public void copyFrom(SkilledExpInfo _o_) {
        this.id = _o_.id;
        this.lv = _o_.lv;
        this.exp = _o_.exp;
        this.itemExp = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.itemExp.forEach((_k_, _v_) -> this.itemExp.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getLv(){
		return this.lv;
	}

	@JSONField(name = "3")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getItemExp(){
		return this.itemExp;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setLv(int _v_){
		this.lv = _v_;
	}

	@JSONField(name = "3")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@JSONField(name = "4")
	public void setItemExp(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.itemExp = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SkilledExpInfo) {
			SkilledExpInfo _o_ = (SkilledExpInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (this.lv != _o_.lv) return false;
			if (this.exp != _o_.exp) return false;
			if (!this.itemExp.equals(_o_.itemExp)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.lv);
		_h_ += _h_ * 31 + (int)(this.exp);
		_h_ += _h_ * 31 + this.itemExp.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(lv).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(itemExp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
