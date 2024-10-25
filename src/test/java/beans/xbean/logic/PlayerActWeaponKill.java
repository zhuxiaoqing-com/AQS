package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerActWeaponKill extends warrior.mdb.XBean {
    private xbean.logic.LGActLogicInfo actInfo;  // 活动数据
    private java.util.concurrent.ConcurrentHashMap<Integer, NewHandTarget> target; 
    private java.util.concurrent.CopyOnWriteArraySet<Integer> accepted;  // 进行中的目标(作为一个索引, 在 updateCondition 的时候使用)

    PlayerActWeaponKill(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actInfo = new LGActLogicInfo(this);
		this.target = new java.util.concurrent.ConcurrentHashMap<Integer, NewHandTarget>();
		this.accepted = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public PlayerActWeaponKill() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerActWeaponKill(PlayerActWeaponKill _o_) {
		this(_o_, null);
	}

	PlayerActWeaponKill(PlayerActWeaponKill _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
		this.target = new java.util.concurrent.ConcurrentHashMap<Integer, NewHandTarget>();
		_o_.target.forEach((_k_, _v_) -> this.target.put(_k_, new NewHandTarget(_v_)));
		this.accepted = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.accepted.forEach(_v_ -> this.accepted.add(_v_));
	}

	public void copyFrom(PlayerActWeaponKill _o_) {
        this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
        this.target = new java.util.concurrent.ConcurrentHashMap<Integer, NewHandTarget>();
		_o_.target.forEach((_k_, _v_) -> this.target.put(_k_, new NewHandTarget(_v_)));
        this.accepted = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.accepted.forEach(_v_ -> this.accepted.add(_v_));
	}


	@JSONField(name = "1")
	public xbean.logic.LGActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, NewHandTarget> getTarget(){
		return this.target;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getAccepted(){
		return this.accepted;
	}

	@JSONField(name = "1")
	public void setActInfo(xbean.logic.LGActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@JSONField(name = "2")
	public void setTarget(java.util.concurrent.ConcurrentHashMap<Integer, NewHandTarget> _v_){
		this.target = _v_;
	}

	@JSONField(name = "3")
	public void setAccepted(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.accepted = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerActWeaponKill) {
			PlayerActWeaponKill _o_ = (PlayerActWeaponKill)_o1_;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			if (!this.target.equals(_o_.target)) return false;
			if (!this.accepted.equals(_o_.accepted)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		_h_ += _h_ * 31 + this.target.hashCode();
		_h_ += _h_ * 31 + this.accepted.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actInfo).append(",");
		_sb_.append(target).append(",");
		_sb_.append(accepted).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
