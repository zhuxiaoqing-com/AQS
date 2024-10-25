package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ActRankTarget extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArraySet<Integer> received;  // 领取过奖励的排行榜类型

    ActRankTarget(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public ActRankTarget() {
		this((warrior.mdb.XBean)null);
	}

	public ActRankTarget(ActRankTarget _o_) {
		this(_o_, null);
	}

	ActRankTarget(ActRankTarget _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.received.forEach(_v_ -> this.received.add(_v_));
	}

	public void copyFrom(ActRankTarget _o_) {
        this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.received.forEach(_v_ -> this.received.add(_v_));
	}


	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getReceived(){
		return this.received;
	}

	@JSONField(name = "2")
	public void setReceived(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.received = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ActRankTarget) {
			ActRankTarget _o_ = (ActRankTarget)_o1_;
			if (!this.received.equals(_o_.received)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.received.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(received).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
