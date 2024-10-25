package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class NewbieGuide extends warrior.mdb.XBean {
    private int newbieGroupId;  // 正在进行的新手引导组id
    private java.util.concurrent.CopyOnWriteArraySet<Integer> finishedNewbieGroup;  // 已完成的新手引导组id列表

    NewbieGuide(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.finishedNewbieGroup = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public NewbieGuide() {
		this((warrior.mdb.XBean)null);
	}

	public NewbieGuide(NewbieGuide _o_) {
		this(_o_, null);
	}

	NewbieGuide(NewbieGuide _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.newbieGroupId = _o_.newbieGroupId;
		this.finishedNewbieGroup = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.finishedNewbieGroup.forEach(_v_ -> this.finishedNewbieGroup.add(_v_));
	}

	public void copyFrom(NewbieGuide _o_) {
        this.newbieGroupId = _o_.newbieGroupId;
        this.finishedNewbieGroup = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.finishedNewbieGroup.forEach(_v_ -> this.finishedNewbieGroup.add(_v_));
	}


	@JSONField(name = "1")
	public int getNewbieGroupId(){
		return this.newbieGroupId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getFinishedNewbieGroup(){
		return this.finishedNewbieGroup;
	}

	@JSONField(name = "1")
	public void setNewbieGroupId(int _v_){
		this.newbieGroupId = _v_;
	}

	@JSONField(name = "2")
	public void setFinishedNewbieGroup(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.finishedNewbieGroup = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof NewbieGuide) {
			NewbieGuide _o_ = (NewbieGuide)_o1_;
			if (this.newbieGroupId != _o_.newbieGroupId) return false;
			if (!this.finishedNewbieGroup.equals(_o_.finishedNewbieGroup)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.newbieGroupId);
		_h_ += _h_ * 31 + this.finishedNewbieGroup.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(newbieGroupId).append(",");
		_sb_.append(finishedNewbieGroup).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
