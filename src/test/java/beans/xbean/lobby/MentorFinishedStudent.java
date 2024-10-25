package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class MentorFinishedStudent extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<Long> players;  // 已完成该任务的学员

    MentorFinishedStudent(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.players = new java.util.concurrent.CopyOnWriteArrayList<Long>();
	}

	public MentorFinishedStudent() {
		this((warrior.mdb.XBean)null);
	}

	public MentorFinishedStudent(MentorFinishedStudent _o_) {
		this(_o_, null);
	}

	MentorFinishedStudent(MentorFinishedStudent _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.players = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.players.forEach(_v_ -> this.players.add(_v_));
	}

	public void copyFrom(MentorFinishedStudent _o_) {
        this.players = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.players.forEach(_v_ -> this.players.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getPlayers(){
		return this.players;
	}

	@JSONField(name = "1")
	public void setPlayers(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.players = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MentorFinishedStudent) {
			MentorFinishedStudent _o_ = (MentorFinishedStudent)_o1_;
			if (!this.players.equals(_o_.players)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.players.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(players).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
