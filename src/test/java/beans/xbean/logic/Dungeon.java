package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Dungeon extends warrior.mdb.XBean {
    private int did;  // 副本Id
    private java.util.concurrent.ConcurrentHashMap<Long, DungeonResource> resources;  // 全部资源
    private java.util.concurrent.ConcurrentHashMap<Long, Obstacles> hurtObstacles;  // 受伤的的静态阻挡物
    private long leaveTime;  // 离开该副本的时间

    Dungeon(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.resources = new java.util.concurrent.ConcurrentHashMap<Long, DungeonResource>();
		this.hurtObstacles = new java.util.concurrent.ConcurrentHashMap<Long, Obstacles>();
	}

	public Dungeon() {
		this((warrior.mdb.XBean)null);
	}

	public Dungeon(Dungeon _o_) {
		this(_o_, null);
	}

	Dungeon(Dungeon _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.did = _o_.did;
		this.resources = new java.util.concurrent.ConcurrentHashMap<Long, DungeonResource>();
		_o_.resources.forEach((_k_, _v_) -> this.resources.put(_k_, new DungeonResource(_v_)));
		this.hurtObstacles = new java.util.concurrent.ConcurrentHashMap<Long, Obstacles>();
		_o_.hurtObstacles.forEach((_k_, _v_) -> this.hurtObstacles.put(_k_, new Obstacles(_v_)));
		this.leaveTime = _o_.leaveTime;
	}

	public void copyFrom(Dungeon _o_) {
        this.did = _o_.did;
        this.resources = new java.util.concurrent.ConcurrentHashMap<Long, DungeonResource>();
		_o_.resources.forEach((_k_, _v_) -> this.resources.put(_k_, new DungeonResource(_v_)));
        this.hurtObstacles = new java.util.concurrent.ConcurrentHashMap<Long, Obstacles>();
		_o_.hurtObstacles.forEach((_k_, _v_) -> this.hurtObstacles.put(_k_, new Obstacles(_v_)));
        this.leaveTime = _o_.leaveTime;
	}


	@JSONField(name = "1")
	public int getDid(){
		return this.did;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, DungeonResource> getResources(){
		return this.resources;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Long, Obstacles> getHurtObstacles(){
		return this.hurtObstacles;
	}

	@JSONField(name = "4")
	public long getLeaveTime(){
		return this.leaveTime;
	}

	@JSONField(name = "1")
	public void setDid(int _v_){
		this.did = _v_;
	}

	@JSONField(name = "2")
	public void setResources(java.util.concurrent.ConcurrentHashMap<Long, DungeonResource> _v_){
		this.resources = _v_;
	}

	@JSONField(name = "3")
	public void setHurtObstacles(java.util.concurrent.ConcurrentHashMap<Long, Obstacles> _v_){
		this.hurtObstacles = _v_;
	}

	@JSONField(name = "4")
	public void setLeaveTime(long _v_){
		this.leaveTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Dungeon) {
			Dungeon _o_ = (Dungeon)_o1_;
			if (this.did != _o_.did) return false;
			if (!this.resources.equals(_o_.resources)) return false;
			if (!this.hurtObstacles.equals(_o_.hurtObstacles)) return false;
			if (this.leaveTime != _o_.leaveTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.did);
		_h_ += _h_ * 31 + this.resources.hashCode();
		_h_ += _h_ * 31 + this.hurtObstacles.hashCode();
		_h_ += _h_ * 31 + (int)(leaveTime ^ (leaveTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(did).append(",");
		_sb_.append(resources).append(",");
		_sb_.append(hurtObstacles).append(",");
		_sb_.append(leaveTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
