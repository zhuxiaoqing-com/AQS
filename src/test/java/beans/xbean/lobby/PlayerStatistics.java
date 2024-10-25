package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerStatistics extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> totalDungeonLog;  // key:type,num
    private java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog> weaponList;  // 前100把的武器Log日志 k:weaponId v:WeaponLog
    private java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog> tankList;  // 前100把的载具Log日志 k:weaponId v:WeaponLog
    private int likeWeapon;  // 最爱的武器
    private int likeTank;  // 最爱的坦克
    private java.util.concurrent.CopyOnWriteArrayList<DungeonLog> dungeonList;  // 前100把的游戏
    private java.util.concurrent.ConcurrentHashMap<Long, Integer> killRole;  // 击杀统计
    private java.util.concurrent.ConcurrentHashMap<Long, Integer> beKillRole;  // 被击杀统计

    PlayerStatistics(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.totalDungeonLog = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.weaponList = new java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog>();
		this.tankList = new java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog>();
		this.dungeonList = new java.util.concurrent.CopyOnWriteArrayList<DungeonLog>();
		this.killRole = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		this.beKillRole = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
	}

	public PlayerStatistics() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerStatistics(PlayerStatistics _o_) {
		this(_o_, null);
	}

	PlayerStatistics(PlayerStatistics _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.totalDungeonLog = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.totalDungeonLog.forEach((_k_, _v_) -> this.totalDungeonLog.put(_k_, _v_));
		this.weaponList = new java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog>();
		_o_.weaponList.forEach((_k_, _v_) -> this.weaponList.put(_k_, new WeaponLog(_v_)));
		this.tankList = new java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog>();
		_o_.tankList.forEach((_k_, _v_) -> this.tankList.put(_k_, new WeaponLog(_v_)));
		this.likeWeapon = _o_.likeWeapon;
		this.likeTank = _o_.likeTank;
		this.dungeonList = new java.util.concurrent.CopyOnWriteArrayList<DungeonLog>();
		_o_.dungeonList.forEach(_v_ -> this.dungeonList.add(new DungeonLog(_v_)));
		this.killRole = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.killRole.forEach((_k_, _v_) -> this.killRole.put(_k_, _v_));
		this.beKillRole = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.beKillRole.forEach((_k_, _v_) -> this.beKillRole.put(_k_, _v_));
	}

	public void copyFrom(PlayerStatistics _o_) {
        this.totalDungeonLog = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.totalDungeonLog.forEach((_k_, _v_) -> this.totalDungeonLog.put(_k_, _v_));
        this.weaponList = new java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog>();
		_o_.weaponList.forEach((_k_, _v_) -> this.weaponList.put(_k_, new WeaponLog(_v_)));
        this.tankList = new java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog>();
		_o_.tankList.forEach((_k_, _v_) -> this.tankList.put(_k_, new WeaponLog(_v_)));
        this.likeWeapon = _o_.likeWeapon;
        this.likeTank = _o_.likeTank;
        this.dungeonList = new java.util.concurrent.CopyOnWriteArrayList<DungeonLog>();
		_o_.dungeonList.forEach(_v_ -> this.dungeonList.add(new DungeonLog(_v_)));
        this.killRole = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.killRole.forEach((_k_, _v_) -> this.killRole.put(_k_, _v_));
        this.beKillRole = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.beKillRole.forEach((_k_, _v_) -> this.beKillRole.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getTotalDungeonLog(){
		return this.totalDungeonLog;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog> getWeaponList(){
		return this.weaponList;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog> getTankList(){
		return this.tankList;
	}

	@JSONField(name = "4")
	public int getLikeWeapon(){
		return this.likeWeapon;
	}

	@JSONField(name = "5")
	public int getLikeTank(){
		return this.likeTank;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArrayList<DungeonLog> getDungeonList(){
		return this.dungeonList;
	}

	@JSONField(name = "7")
	public java.util.concurrent.ConcurrentHashMap<Long, Integer> getKillRole(){
		return this.killRole;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Long, Integer> getBeKillRole(){
		return this.beKillRole;
	}

	@JSONField(name = "1")
	public void setTotalDungeonLog(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.totalDungeonLog = _v_;
	}

	@JSONField(name = "2")
	public void setWeaponList(java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog> _v_){
		this.weaponList = _v_;
	}

	@JSONField(name = "3")
	public void setTankList(java.util.concurrent.ConcurrentHashMap<Integer, WeaponLog> _v_){
		this.tankList = _v_;
	}

	@JSONField(name = "4")
	public void setLikeWeapon(int _v_){
		this.likeWeapon = _v_;
	}

	@JSONField(name = "5")
	public void setLikeTank(int _v_){
		this.likeTank = _v_;
	}

	@JSONField(name = "6")
	public void setDungeonList(java.util.concurrent.CopyOnWriteArrayList<DungeonLog> _v_){
		this.dungeonList = _v_;
	}

	@JSONField(name = "7")
	public void setKillRole(java.util.concurrent.ConcurrentHashMap<Long, Integer> _v_){
		this.killRole = _v_;
	}

	@JSONField(name = "8")
	public void setBeKillRole(java.util.concurrent.ConcurrentHashMap<Long, Integer> _v_){
		this.beKillRole = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerStatistics) {
			PlayerStatistics _o_ = (PlayerStatistics)_o1_;
			if (!this.totalDungeonLog.equals(_o_.totalDungeonLog)) return false;
			if (!this.weaponList.equals(_o_.weaponList)) return false;
			if (!this.tankList.equals(_o_.tankList)) return false;
			if (this.likeWeapon != _o_.likeWeapon) return false;
			if (this.likeTank != _o_.likeTank) return false;
			if (!this.dungeonList.equals(_o_.dungeonList)) return false;
			if (!this.killRole.equals(_o_.killRole)) return false;
			if (!this.beKillRole.equals(_o_.beKillRole)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.totalDungeonLog.hashCode();
		_h_ += _h_ * 31 + this.weaponList.hashCode();
		_h_ += _h_ * 31 + this.tankList.hashCode();
		_h_ += _h_ * 31 + (int)(this.likeWeapon);
		_h_ += _h_ * 31 + (int)(this.likeTank);
		_h_ += _h_ * 31 + this.dungeonList.hashCode();
		_h_ += _h_ * 31 + this.killRole.hashCode();
		_h_ += _h_ * 31 + this.beKillRole.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(totalDungeonLog).append(",");
		_sb_.append(weaponList).append(",");
		_sb_.append(tankList).append(",");
		_sb_.append(likeWeapon).append(",");
		_sb_.append(likeTank).append(",");
		_sb_.append(dungeonList).append(",");
		_sb_.append(killRole).append(",");
		_sb_.append(beKillRole).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
