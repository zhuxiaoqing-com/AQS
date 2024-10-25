package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PrizeWheelInfo extends warrior.mdb.XBean {
    private int alreadyNodeRound;  // 已经领取过奖励的轮次
    private java.util.concurrent.CopyOnWriteArraySet<Integer> nodeRewardSet;  // 该轮已经领取过的奖励Id

    PrizeWheelInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.nodeRewardSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public PrizeWheelInfo() {
		this((warrior.mdb.XBean)null);
	}

	public PrizeWheelInfo(PrizeWheelInfo _o_) {
		this(_o_, null);
	}

	PrizeWheelInfo(PrizeWheelInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.alreadyNodeRound = _o_.alreadyNodeRound;
		this.nodeRewardSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.nodeRewardSet.forEach(_v_ -> this.nodeRewardSet.add(_v_));
	}

	public void copyFrom(PrizeWheelInfo _o_) {
        this.alreadyNodeRound = _o_.alreadyNodeRound;
        this.nodeRewardSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.nodeRewardSet.forEach(_v_ -> this.nodeRewardSet.add(_v_));
	}


	@JSONField(name = "1")
	public int getAlreadyNodeRound(){
		return this.alreadyNodeRound;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getNodeRewardSet(){
		return this.nodeRewardSet;
	}

	@JSONField(name = "1")
	public void setAlreadyNodeRound(int _v_){
		this.alreadyNodeRound = _v_;
	}

	@JSONField(name = "2")
	public void setNodeRewardSet(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.nodeRewardSet = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PrizeWheelInfo) {
			PrizeWheelInfo _o_ = (PrizeWheelInfo)_o1_;
			if (this.alreadyNodeRound != _o_.alreadyNodeRound) return false;
			if (!this.nodeRewardSet.equals(_o_.nodeRewardSet)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.alreadyNodeRound);
		_h_ += _h_ * 31 + this.nodeRewardSet.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(alreadyNodeRound).append(",");
		_sb_.append(nodeRewardSet).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
