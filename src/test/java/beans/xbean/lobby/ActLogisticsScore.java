package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ActLogisticsScore extends warrior.mdb.XBean {
    private xbean.lobby.LBActLogicInfo actInfo;  // 活动数据
    private java.util.concurrent.CopyOnWriteArraySet<Integer> received;  // 领取过奖励id

    ActLogisticsScore(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actInfo = new LBActLogicInfo(this);
		this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public ActLogisticsScore() {
		this((warrior.mdb.XBean)null);
	}

	public ActLogisticsScore(ActLogisticsScore _o_) {
		this(_o_, null);
	}

	ActLogisticsScore(ActLogisticsScore _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actInfo = new LBActLogicInfo(_o_.actInfo, this);
		this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.received.forEach(_v_ -> this.received.add(_v_));
	}

	public void copyFrom(ActLogisticsScore _o_) {
        this.actInfo = new LBActLogicInfo(_o_.actInfo, this);
        this.received = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.received.forEach(_v_ -> this.received.add(_v_));
	}


	@JSONField(name = "1")
	public xbean.lobby.LBActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getReceived(){
		return this.received;
	}

	@JSONField(name = "1")
	public void setActInfo(xbean.lobby.LBActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@JSONField(name = "2")
	public void setReceived(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.received = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ActLogisticsScore) {
			ActLogisticsScore _o_ = (ActLogisticsScore)_o1_;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			if (!this.received.equals(_o_.received)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		_h_ += _h_ * 31 + this.received.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actInfo).append(",");
		_sb_.append(received).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
