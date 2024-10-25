package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerResBase extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, PlayerResBuilding> buildings;  // 建筑列表
    private java.util.concurrent.CopyOnWriteArrayList<Long> stealCount;  // 偷取的玩家列表
    private int beStealCount;  // 被偷取次数
    private int dayNum;  // 当前日期 20231030 用于记录每日清零
    private int exploreNum;  // 当前已探索次数
    private int inited;  // 是否初始化过，当功能解锁以后为1
    private java.util.concurrent.ConcurrentHashMap<Long, ResBaseChange> changeHistory;  // 变更记录
    private java.util.concurrent.CopyOnWriteArrayList<ResBaseStealLog> log;  // 偷取日志
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> resStoreLv;  // 资源存储等级
    private java.util.concurrent.CopyOnWriteArrayList<ResBaseExploreQueue> exploreQueue;  // 探索队列
    private long exploreCd;  // 探索的操作cd，默认1秒

    PlayerResBase(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.buildings = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerResBuilding>();
		this.stealCount = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.changeHistory = new java.util.concurrent.ConcurrentHashMap<Long, ResBaseChange>();
		this.log = new java.util.concurrent.CopyOnWriteArrayList<ResBaseStealLog>();
		this.resStoreLv = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.exploreQueue = new java.util.concurrent.CopyOnWriteArrayList<ResBaseExploreQueue>();
	}

	public PlayerResBase() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerResBase(PlayerResBase _o_) {
		this(_o_, null);
	}

	PlayerResBase(PlayerResBase _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.buildings = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerResBuilding>();
		_o_.buildings.forEach((_k_, _v_) -> this.buildings.put(_k_, new PlayerResBuilding(_v_)));
		this.stealCount = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.stealCount.forEach(_v_ -> this.stealCount.add(_v_));
		this.beStealCount = _o_.beStealCount;
		this.dayNum = _o_.dayNum;
		this.exploreNum = _o_.exploreNum;
		this.inited = _o_.inited;
		this.changeHistory = new java.util.concurrent.ConcurrentHashMap<Long, ResBaseChange>();
		_o_.changeHistory.forEach((_k_, _v_) -> this.changeHistory.put(_k_, new ResBaseChange(_v_)));
		this.log = new java.util.concurrent.CopyOnWriteArrayList<ResBaseStealLog>();
		_o_.log.forEach(_v_ -> this.log.add(new ResBaseStealLog(_v_)));
		this.resStoreLv = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resStoreLv.forEach((_k_, _v_) -> this.resStoreLv.put(_k_, _v_));
		this.exploreQueue = new java.util.concurrent.CopyOnWriteArrayList<ResBaseExploreQueue>();
		_o_.exploreQueue.forEach(_v_ -> this.exploreQueue.add(new ResBaseExploreQueue(_v_)));
		this.exploreCd = _o_.exploreCd;
	}

	public void copyFrom(PlayerResBase _o_) {
        this.buildings = new java.util.concurrent.ConcurrentHashMap<Integer, PlayerResBuilding>();
		_o_.buildings.forEach((_k_, _v_) -> this.buildings.put(_k_, new PlayerResBuilding(_v_)));
        this.stealCount = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.stealCount.forEach(_v_ -> this.stealCount.add(_v_));
        this.beStealCount = _o_.beStealCount;
        this.dayNum = _o_.dayNum;
        this.exploreNum = _o_.exploreNum;
        this.inited = _o_.inited;
        this.changeHistory = new java.util.concurrent.ConcurrentHashMap<Long, ResBaseChange>();
		_o_.changeHistory.forEach((_k_, _v_) -> this.changeHistory.put(_k_, new ResBaseChange(_v_)));
        this.log = new java.util.concurrent.CopyOnWriteArrayList<ResBaseStealLog>();
		_o_.log.forEach(_v_ -> this.log.add(new ResBaseStealLog(_v_)));
        this.resStoreLv = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resStoreLv.forEach((_k_, _v_) -> this.resStoreLv.put(_k_, _v_));
        this.exploreQueue = new java.util.concurrent.CopyOnWriteArrayList<ResBaseExploreQueue>();
		_o_.exploreQueue.forEach(_v_ -> this.exploreQueue.add(new ResBaseExploreQueue(_v_)));
        this.exploreCd = _o_.exploreCd;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, PlayerResBuilding> getBuildings(){
		return this.buildings;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getStealCount(){
		return this.stealCount;
	}

	@JSONField(name = "3")
	public int getBeStealCount(){
		return this.beStealCount;
	}

	@JSONField(name = "4")
	public int getDayNum(){
		return this.dayNum;
	}

	@JSONField(name = "5")
	public int getExploreNum(){
		return this.exploreNum;
	}

	@JSONField(name = "6")
	public int getInited(){
		return this.inited;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Long, ResBaseChange> getChangeHistory(){
		return this.changeHistory;
	}

	@JSONField(name = "9")
	public java.util.concurrent.CopyOnWriteArrayList<ResBaseStealLog> getLog(){
		return this.log;
	}

	@JSONField(name = "10")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getResStoreLv(){
		return this.resStoreLv;
	}

	@JSONField(name = "13")
	public java.util.concurrent.CopyOnWriteArrayList<ResBaseExploreQueue> getExploreQueue(){
		return this.exploreQueue;
	}

	@JSONField(name = "14")
	public long getExploreCd(){
		return this.exploreCd;
	}

	@JSONField(name = "1")
	public void setBuildings(java.util.concurrent.ConcurrentHashMap<Integer, PlayerResBuilding> _v_){
		this.buildings = _v_;
	}

	@JSONField(name = "2")
	public void setStealCount(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.stealCount = _v_;
	}

	@JSONField(name = "3")
	public void setBeStealCount(int _v_){
		this.beStealCount = _v_;
	}

	@JSONField(name = "4")
	public void setDayNum(int _v_){
		this.dayNum = _v_;
	}

	@JSONField(name = "5")
	public void setExploreNum(int _v_){
		this.exploreNum = _v_;
	}

	@JSONField(name = "6")
	public void setInited(int _v_){
		this.inited = _v_;
	}

	@JSONField(name = "8")
	public void setChangeHistory(java.util.concurrent.ConcurrentHashMap<Long, ResBaseChange> _v_){
		this.changeHistory = _v_;
	}

	@JSONField(name = "9")
	public void setLog(java.util.concurrent.CopyOnWriteArrayList<ResBaseStealLog> _v_){
		this.log = _v_;
	}

	@JSONField(name = "10")
	public void setResStoreLv(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.resStoreLv = _v_;
	}

	@JSONField(name = "13")
	public void setExploreQueue(java.util.concurrent.CopyOnWriteArrayList<ResBaseExploreQueue> _v_){
		this.exploreQueue = _v_;
	}

	@JSONField(name = "14")
	public void setExploreCd(long _v_){
		this.exploreCd = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerResBase) {
			PlayerResBase _o_ = (PlayerResBase)_o1_;
			if (!this.buildings.equals(_o_.buildings)) return false;
			if (!this.stealCount.equals(_o_.stealCount)) return false;
			if (this.beStealCount != _o_.beStealCount) return false;
			if (this.dayNum != _o_.dayNum) return false;
			if (this.exploreNum != _o_.exploreNum) return false;
			if (this.inited != _o_.inited) return false;
			if (!this.changeHistory.equals(_o_.changeHistory)) return false;
			if (!this.log.equals(_o_.log)) return false;
			if (!this.resStoreLv.equals(_o_.resStoreLv)) return false;
			if (!this.exploreQueue.equals(_o_.exploreQueue)) return false;
			if (this.exploreCd != _o_.exploreCd) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.buildings.hashCode();
		_h_ += _h_ * 31 + this.stealCount.hashCode();
		_h_ += _h_ * 31 + (int)(this.beStealCount);
		_h_ += _h_ * 31 + (int)(this.dayNum);
		_h_ += _h_ * 31 + (int)(this.exploreNum);
		_h_ += _h_ * 31 + (int)(this.inited);
		_h_ += _h_ * 31 + this.changeHistory.hashCode();
		_h_ += _h_ * 31 + this.log.hashCode();
		_h_ += _h_ * 31 + this.resStoreLv.hashCode();
		_h_ += _h_ * 31 + this.exploreQueue.hashCode();
		_h_ += _h_ * 31 + (int)(exploreCd ^ (exploreCd >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(buildings).append(",");
		_sb_.append(stealCount).append(",");
		_sb_.append(beStealCount).append(",");
		_sb_.append(dayNum).append(",");
		_sb_.append(exploreNum).append(",");
		_sb_.append(inited).append(",");
		_sb_.append(changeHistory).append(",");
		_sb_.append(log).append(",");
		_sb_.append(resStoreLv).append(",");
		_sb_.append(exploreQueue).append(",");
		_sb_.append(exploreCd).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
