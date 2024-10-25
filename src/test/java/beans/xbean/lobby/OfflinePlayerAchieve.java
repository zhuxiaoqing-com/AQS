package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class OfflinePlayerAchieve extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<OfflineAchieveInfo> playerachieves; 

    OfflinePlayerAchieve(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerachieves = new java.util.concurrent.CopyOnWriteArrayList<OfflineAchieveInfo>();
	}

	public OfflinePlayerAchieve() {
		this((warrior.mdb.XBean)null);
	}

	public OfflinePlayerAchieve(OfflinePlayerAchieve _o_) {
		this(_o_, null);
	}

	OfflinePlayerAchieve(OfflinePlayerAchieve _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerachieves = new java.util.concurrent.CopyOnWriteArrayList<OfflineAchieveInfo>();
		_o_.playerachieves.forEach(_v_ -> this.playerachieves.add(new OfflineAchieveInfo(_v_)));
	}

	public void copyFrom(OfflinePlayerAchieve _o_) {
        this.playerachieves = new java.util.concurrent.CopyOnWriteArrayList<OfflineAchieveInfo>();
		_o_.playerachieves.forEach(_v_ -> this.playerachieves.add(new OfflineAchieveInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<OfflineAchieveInfo> getPlayerachieves(){
		return this.playerachieves;
	}

	@JSONField(name = "1")
	public void setPlayerachieves(java.util.concurrent.CopyOnWriteArrayList<OfflineAchieveInfo> _v_){
		this.playerachieves = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof OfflinePlayerAchieve) {
			OfflinePlayerAchieve _o_ = (OfflinePlayerAchieve)_o1_;
			if (!this.playerachieves.equals(_o_.playerachieves)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.playerachieves.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerachieves).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
