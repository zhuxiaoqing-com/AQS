package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerAchieve extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, AchieveInfo> info;  // key:成就类型

    PlayerAchieve(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.info = new java.util.concurrent.ConcurrentHashMap<Integer, AchieveInfo>();
	}

	public PlayerAchieve() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerAchieve(PlayerAchieve _o_) {
		this(_o_, null);
	}

	PlayerAchieve(PlayerAchieve _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.info = new java.util.concurrent.ConcurrentHashMap<Integer, AchieveInfo>();
		_o_.info.forEach((_k_, _v_) -> this.info.put(_k_, new AchieveInfo(_v_)));
	}

	public void copyFrom(PlayerAchieve _o_) {
        this.info = new java.util.concurrent.ConcurrentHashMap<Integer, AchieveInfo>();
		_o_.info.forEach((_k_, _v_) -> this.info.put(_k_, new AchieveInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, AchieveInfo> getInfo(){
		return this.info;
	}

	@JSONField(name = "1")
	public void setInfo(java.util.concurrent.ConcurrentHashMap<Integer, AchieveInfo> _v_){
		this.info = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerAchieve) {
			PlayerAchieve _o_ = (PlayerAchieve)_o1_;
			if (!this.info.equals(_o_.info)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.info.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(info).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
