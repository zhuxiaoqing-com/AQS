package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class AchieveInfo extends warrior.mdb.XBean {
    private int id;  // 成就id
    private int count;  // 当前成就的参数值
    private int level;  // 当前成就的等级
    private int gainTimes;  // 成就达成次数
    private int attrLevel;  // 属性最大等级

    AchieveInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public AchieveInfo() {
		this((warrior.mdb.XBean)null);
	}

	public AchieveInfo(AchieveInfo _o_) {
		this(_o_, null);
	}

	AchieveInfo(AchieveInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.count = _o_.count;
		this.level = _o_.level;
		this.gainTimes = _o_.gainTimes;
		this.attrLevel = _o_.attrLevel;
	}

	public void copyFrom(AchieveInfo _o_) {
        this.id = _o_.id;
        this.count = _o_.count;
        this.level = _o_.level;
        this.gainTimes = _o_.gainTimes;
        this.attrLevel = _o_.attrLevel;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getCount(){
		return this.count;
	}

	@JSONField(name = "3")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "4")
	public int getGainTimes(){
		return this.gainTimes;
	}

	@JSONField(name = "5")
	public int getAttrLevel(){
		return this.attrLevel;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setCount(int _v_){
		this.count = _v_;
	}

	@JSONField(name = "3")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "4")
	public void setGainTimes(int _v_){
		this.gainTimes = _v_;
	}

	@JSONField(name = "5")
	public void setAttrLevel(int _v_){
		this.attrLevel = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AchieveInfo) {
			AchieveInfo _o_ = (AchieveInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (this.count != _o_.count) return false;
			if (this.level != _o_.level) return false;
			if (this.gainTimes != _o_.gainTimes) return false;
			if (this.attrLevel != _o_.attrLevel) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.count);
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + (int)(this.gainTimes);
		_h_ += _h_ * 31 + (int)(this.attrLevel);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(count).append(",");
		_sb_.append(level).append(",");
		_sb_.append(gainTimes).append(",");
		_sb_.append(attrLevel).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
