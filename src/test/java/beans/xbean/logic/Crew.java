package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Crew extends warrior.mdb.XBean {
    private long srl;  // 乘员唯一id
    private int cfgid;  // 乘员配置表id
    private int level;  // 乘员等级
    private int exp;  // 乘员经验
    private boolean isNew;  // 是否新获得
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> skill;  // key:技能槽位  value:技能id

    Crew(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.skill = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public Crew() {
		this((warrior.mdb.XBean)null);
	}

	public Crew(Crew _o_) {
		this(_o_, null);
	}

	Crew(Crew _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.cfgid = _o_.cfgid;
		this.level = _o_.level;
		this.exp = _o_.exp;
		this.isNew = _o_.isNew;
		this.skill = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.skill.forEach((_k_, _v_) -> this.skill.put(_k_, _v_));
	}

	public void copyFrom(Crew _o_) {
        this.srl = _o_.srl;
        this.cfgid = _o_.cfgid;
        this.level = _o_.level;
        this.exp = _o_.exp;
        this.isNew = _o_.isNew;
        this.skill = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.skill.forEach((_k_, _v_) -> this.skill.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public int getCfgid(){
		return this.cfgid;
	}

	@JSONField(name = "3")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "4")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "5")
	public boolean getIsNew(){
		return this.isNew;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getSkill(){
		return this.skill;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setCfgid(int _v_){
		this.cfgid = _v_;
	}

	@JSONField(name = "3")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "4")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@JSONField(name = "5")
	public void setIsNew(boolean _v_){
		this.isNew = _v_;
	}

	@JSONField(name = "6")
	public void setSkill(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.skill = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Crew) {
			Crew _o_ = (Crew)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.cfgid != _o_.cfgid) return false;
			if (this.level != _o_.level) return false;
			if (this.exp != _o_.exp) return false;
			if (this.isNew != _o_.isNew) return false;
			if (!this.skill.equals(_o_.skill)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(this.cfgid);
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + (int)(this.exp);
		_h_ += _h_ * 31 + (this.isNew ? 1231 : 1237);
		_h_ += _h_ * 31 + this.skill.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(cfgid).append(",");
		_sb_.append(level).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(isNew).append(",");
		_sb_.append(skill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
