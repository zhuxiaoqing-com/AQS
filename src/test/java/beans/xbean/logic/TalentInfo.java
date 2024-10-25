package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class TalentInfo extends warrior.mdb.XBean {
    private int id;  // 天赋id
    private int level;  // 当前等级
    private long levelEndMill;  // 等级结束毫秒值

    TalentInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public TalentInfo() {
		this((warrior.mdb.XBean)null);
	}

	public TalentInfo(TalentInfo _o_) {
		this(_o_, null);
	}

	TalentInfo(TalentInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.level = _o_.level;
		this.levelEndMill = _o_.levelEndMill;
	}

	public void copyFrom(TalentInfo _o_) {
        this.id = _o_.id;
        this.level = _o_.level;
        this.levelEndMill = _o_.levelEndMill;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "3")
	public long getLevelEndMill(){
		return this.levelEndMill;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "3")
	public void setLevelEndMill(long _v_){
		this.levelEndMill = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TalentInfo) {
			TalentInfo _o_ = (TalentInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (this.level != _o_.level) return false;
			if (this.levelEndMill != _o_.levelEndMill) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + (int)(levelEndMill ^ (levelEndMill >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(level).append(",");
		_sb_.append(levelEndMill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
