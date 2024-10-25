package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class MentorTask extends warrior.mdb.XBean {
    private int id;  // 任务id
    private int state;  // 任务完成状态 0:未完成，1:已完成, 2:已领取
    private int count;  // 任务计数

    MentorTask(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public MentorTask() {
		this((warrior.mdb.XBean)null);
	}

	public MentorTask(MentorTask _o_) {
		this(_o_, null);
	}

	MentorTask(MentorTask _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.state = _o_.state;
		this.count = _o_.count;
	}

	public void copyFrom(MentorTask _o_) {
        this.id = _o_.id;
        this.state = _o_.state;
        this.count = _o_.count;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "3")
	public int getCount(){
		return this.count;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "3")
	public void setCount(int _v_){
		this.count = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MentorTask) {
			MentorTask _o_ = (MentorTask)_o1_;
			if (this.id != _o_.id) return false;
			if (this.state != _o_.state) return false;
			if (this.count != _o_.count) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(this.count);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(state).append(",");
		_sb_.append(count).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
