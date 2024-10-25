package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBActLogicInfo extends warrior.mdb.XBean {
    private long actOpen;  // 活动开启时间
    private boolean isOpen;  // 是否是开启状态
    private int actId;  // 活动Id

    LBActLogicInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public LBActLogicInfo() {
		this((warrior.mdb.XBean)null);
	}

	public LBActLogicInfo(LBActLogicInfo _o_) {
		this(_o_, null);
	}

	LBActLogicInfo(LBActLogicInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actOpen = _o_.actOpen;
		this.isOpen = _o_.isOpen;
		this.actId = _o_.actId;
	}

	public void copyFrom(LBActLogicInfo _o_) {
        this.actOpen = _o_.actOpen;
        this.isOpen = _o_.isOpen;
        this.actId = _o_.actId;
	}


	@JSONField(name = "1")
	public long getActOpen(){
		return this.actOpen;
	}

	@JSONField(name = "2")
	public boolean getIsOpen(){
		return this.isOpen;
	}

	@JSONField(name = "3")
	public int getActId(){
		return this.actId;
	}

	@JSONField(name = "1")
	public void setActOpen(long _v_){
		this.actOpen = _v_;
	}

	@JSONField(name = "2")
	public void setIsOpen(boolean _v_){
		this.isOpen = _v_;
	}

	@JSONField(name = "3")
	public void setActId(int _v_){
		this.actId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBActLogicInfo) {
			LBActLogicInfo _o_ = (LBActLogicInfo)_o1_;
			if (this.actOpen != _o_.actOpen) return false;
			if (this.isOpen != _o_.isOpen) return false;
			if (this.actId != _o_.actId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(actOpen ^ (actOpen >>> 32));
		_h_ += _h_ * 31 + (this.isOpen ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.actId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actOpen).append(",");
		_sb_.append(isOpen).append(",");
		_sb_.append(actId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
