package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class SupplyApply extends warrior.mdb.XBean {
    private long playerId; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> supply;  // 申请装备
    private String approverName;  // 批准者名字
    private long approveTime;  // 批准时间戳
    private int approve;  // 是否批准 -1未操作,0拒绝,1批准
    private long applyTime;  // 提交时间戳

    SupplyApply(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.supply = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.approverName = "";
	}

	public SupplyApply() {
		this((warrior.mdb.XBean)null);
	}

	public SupplyApply(SupplyApply _o_) {
		this(_o_, null);
	}

	SupplyApply(SupplyApply _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.supply = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.supply.forEach((_k_, _v_) -> this.supply.put(_k_, _v_));
		this.approverName = _o_.approverName;
		this.approveTime = _o_.approveTime;
		this.approve = _o_.approve;
		this.applyTime = _o_.applyTime;
	}

	public void copyFrom(SupplyApply _o_) {
        this.playerId = _o_.playerId;
        this.supply = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.supply.forEach((_k_, _v_) -> this.supply.put(_k_, _v_));
        this.approverName = _o_.approverName;
        this.approveTime = _o_.approveTime;
        this.approve = _o_.approve;
        this.applyTime = _o_.applyTime;
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getSupply(){
		return this.supply;
	}

	@JSONField(name = "3")
	public String getApproverName(){
		return this.approverName;
	}

	@JSONField(name = "4")
	public long getApproveTime(){
		return this.approveTime;
	}

	@JSONField(name = "5")
	public int getApprove(){
		return this.approve;
	}

	@JSONField(name = "6")
	public long getApplyTime(){
		return this.applyTime;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "2")
	public void setSupply(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.supply = _v_;
	}

	@JSONField(name = "3")
	public void setApproverName(String _v_){
		this.approverName = _v_;
	}

	@JSONField(name = "4")
	public void setApproveTime(long _v_){
		this.approveTime = _v_;
	}

	@JSONField(name = "5")
	public void setApprove(int _v_){
		this.approve = _v_;
	}

	@JSONField(name = "6")
	public void setApplyTime(long _v_){
		this.applyTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SupplyApply) {
			SupplyApply _o_ = (SupplyApply)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (!this.supply.equals(_o_.supply)) return false;
			if (!this.approverName.equals(_o_.approverName)) return false;
			if (this.approveTime != _o_.approveTime) return false;
			if (this.approve != _o_.approve) return false;
			if (this.applyTime != _o_.applyTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + this.supply.hashCode();
		_h_ += _h_ * 31 + this.approverName.hashCode();
		_h_ += _h_ * 31 + (int)(approveTime ^ (approveTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.approve);
		_h_ += _h_ * 31 + (int)(applyTime ^ (applyTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append(supply).append(",");
		_sb_.append("T").append(approverName.length()).append(",");
		_sb_.append(approveTime).append(",");
		_sb_.append(approve).append(",");
		_sb_.append(applyTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
