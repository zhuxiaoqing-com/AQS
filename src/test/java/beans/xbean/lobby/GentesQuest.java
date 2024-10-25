package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GentesQuest extends warrior.mdb.XBean {
    private int id; 
    private int progress;  // 进度值
    private int state;  // 状态

    GentesQuest(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public GentesQuest() {
		this((warrior.mdb.XBean)null);
	}

	public GentesQuest(GentesQuest _o_) {
		this(_o_, null);
	}

	GentesQuest(GentesQuest _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.progress = _o_.progress;
		this.state = _o_.state;
	}

	public void copyFrom(GentesQuest _o_) {
        this.id = _o_.id;
        this.progress = _o_.progress;
        this.state = _o_.state;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getProgress(){
		return this.progress;
	}

	@JSONField(name = "3")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setProgress(int _v_){
		this.progress = _v_;
	}

	@JSONField(name = "3")
	public void setState(int _v_){
		this.state = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GentesQuest) {
			GentesQuest _o_ = (GentesQuest)_o1_;
			if (this.id != _o_.id) return false;
			if (this.progress != _o_.progress) return false;
			if (this.state != _o_.state) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.progress);
		_h_ += _h_ * 31 + (int)(this.state);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(progress).append(",");
		_sb_.append(state).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
