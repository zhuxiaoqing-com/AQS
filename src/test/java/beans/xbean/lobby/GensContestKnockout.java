package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensContestKnockout extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<GensContestGroup> fightGroup; 
    private int state;  // 0：未开始 1：进行中 2:已结束
    private int round; 

    GensContestKnockout(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.fightGroup = new java.util.concurrent.CopyOnWriteArrayList<GensContestGroup>();
	}

	public GensContestKnockout() {
		this((warrior.mdb.XBean)null);
	}

	public GensContestKnockout(GensContestKnockout _o_) {
		this(_o_, null);
	}

	GensContestKnockout(GensContestKnockout _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.fightGroup = new java.util.concurrent.CopyOnWriteArrayList<GensContestGroup>();
		_o_.fightGroup.forEach(_v_ -> this.fightGroup.add(new GensContestGroup(_v_)));
		this.state = _o_.state;
		this.round = _o_.round;
	}

	public void copyFrom(GensContestKnockout _o_) {
        this.fightGroup = new java.util.concurrent.CopyOnWriteArrayList<GensContestGroup>();
		_o_.fightGroup.forEach(_v_ -> this.fightGroup.add(new GensContestGroup(_v_)));
        this.state = _o_.state;
        this.round = _o_.round;
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<GensContestGroup> getFightGroup(){
		return this.fightGroup;
	}

	@JSONField(name = "2")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "3")
	public int getRound(){
		return this.round;
	}

	@JSONField(name = "1")
	public void setFightGroup(java.util.concurrent.CopyOnWriteArrayList<GensContestGroup> _v_){
		this.fightGroup = _v_;
	}

	@JSONField(name = "2")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "3")
	public void setRound(int _v_){
		this.round = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensContestKnockout) {
			GensContestKnockout _o_ = (GensContestKnockout)_o1_;
			if (!this.fightGroup.equals(_o_.fightGroup)) return false;
			if (this.state != _o_.state) return false;
			if (this.round != _o_.round) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.fightGroup.hashCode();
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(this.round);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(fightGroup).append(",");
		_sb_.append(state).append(",");
		_sb_.append(round).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
