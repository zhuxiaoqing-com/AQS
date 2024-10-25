package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class OfflineAchieveInfo extends warrior.mdb.XBean {
    private int type;  // 成就类型
    private int num;  // 完成数量
    private int targetId;  // 目标ID

    OfflineAchieveInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public OfflineAchieveInfo() {
		this((warrior.mdb.XBean)null);
	}

	public OfflineAchieveInfo(OfflineAchieveInfo _o_) {
		this(_o_, null);
	}

	OfflineAchieveInfo(OfflineAchieveInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.type = _o_.type;
		this.num = _o_.num;
		this.targetId = _o_.targetId;
	}

	public void copyFrom(OfflineAchieveInfo _o_) {
        this.type = _o_.type;
        this.num = _o_.num;
        this.targetId = _o_.targetId;
	}


	@JSONField(name = "1")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "2")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "3")
	public int getTargetId(){
		return this.targetId;
	}

	@JSONField(name = "1")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "2")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@JSONField(name = "3")
	public void setTargetId(int _v_){
		this.targetId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof OfflineAchieveInfo) {
			OfflineAchieveInfo _o_ = (OfflineAchieveInfo)_o1_;
			if (this.type != _o_.type) return false;
			if (this.num != _o_.num) return false;
			if (this.targetId != _o_.targetId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(this.num);
		_h_ += _h_ * 31 + (int)(this.targetId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(type).append(",");
		_sb_.append(num).append(",");
		_sb_.append(targetId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
