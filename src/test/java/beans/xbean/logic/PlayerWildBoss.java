package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerWildBoss extends warrior.mdb.XBean {
    private int rankCount; 
    private int followCount; 
    private int killCount; 
    private long lastResetTimeMills; 

    PlayerWildBoss(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public PlayerWildBoss() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerWildBoss(PlayerWildBoss _o_) {
		this(_o_, null);
	}

	PlayerWildBoss(PlayerWildBoss _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.rankCount = _o_.rankCount;
		this.followCount = _o_.followCount;
		this.killCount = _o_.killCount;
		this.lastResetTimeMills = _o_.lastResetTimeMills;
	}

	public void copyFrom(PlayerWildBoss _o_) {
        this.rankCount = _o_.rankCount;
        this.followCount = _o_.followCount;
        this.killCount = _o_.killCount;
        this.lastResetTimeMills = _o_.lastResetTimeMills;
	}


	@JSONField(name = "1")
	public int getRankCount(){
		return this.rankCount;
	}

	@JSONField(name = "2")
	public int getFollowCount(){
		return this.followCount;
	}

	@JSONField(name = "3")
	public int getKillCount(){
		return this.killCount;
	}

	@JSONField(name = "4")
	public long getLastResetTimeMills(){
		return this.lastResetTimeMills;
	}

	@JSONField(name = "1")
	public void setRankCount(int _v_){
		this.rankCount = _v_;
	}

	@JSONField(name = "2")
	public void setFollowCount(int _v_){
		this.followCount = _v_;
	}

	@JSONField(name = "3")
	public void setKillCount(int _v_){
		this.killCount = _v_;
	}

	@JSONField(name = "4")
	public void setLastResetTimeMills(long _v_){
		this.lastResetTimeMills = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerWildBoss) {
			PlayerWildBoss _o_ = (PlayerWildBoss)_o1_;
			if (this.rankCount != _o_.rankCount) return false;
			if (this.followCount != _o_.followCount) return false;
			if (this.killCount != _o_.killCount) return false;
			if (this.lastResetTimeMills != _o_.lastResetTimeMills) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.rankCount);
		_h_ += _h_ * 31 + (int)(this.followCount);
		_h_ += _h_ * 31 + (int)(this.killCount);
		_h_ += _h_ * 31 + (int)(lastResetTimeMills ^ (lastResetTimeMills >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(rankCount).append(",");
		_sb_.append(followCount).append(",");
		_sb_.append(killCount).append(",");
		_sb_.append(lastResetTimeMills).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
