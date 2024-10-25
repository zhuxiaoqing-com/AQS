package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerCollectLimit extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> collectNumMap;  // key:采集物id value:已经采集次数
    private java.util.concurrent.CopyOnWriteArraySet<Integer> monsterNumMap;  // key:怪物id

    PlayerCollectLimit(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.collectNumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.monsterNumMap = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public PlayerCollectLimit() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerCollectLimit(PlayerCollectLimit _o_) {
		this(_o_, null);
	}

	PlayerCollectLimit(PlayerCollectLimit _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.collectNumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectNumMap.forEach((_k_, _v_) -> this.collectNumMap.put(_k_, _v_));
		this.monsterNumMap = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monsterNumMap.forEach(_v_ -> this.monsterNumMap.add(_v_));
	}

	public void copyFrom(PlayerCollectLimit _o_) {
        this.collectNumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.collectNumMap.forEach((_k_, _v_) -> this.collectNumMap.put(_k_, _v_));
        this.monsterNumMap = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monsterNumMap.forEach(_v_ -> this.monsterNumMap.add(_v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCollectNumMap(){
		return this.collectNumMap;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getMonsterNumMap(){
		return this.monsterNumMap;
	}

	@JSONField(name = "1")
	public void setCollectNumMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.collectNumMap = _v_;
	}

	@JSONField(name = "2")
	public void setMonsterNumMap(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.monsterNumMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerCollectLimit) {
			PlayerCollectLimit _o_ = (PlayerCollectLimit)_o1_;
			if (!this.collectNumMap.equals(_o_.collectNumMap)) return false;
			if (!this.monsterNumMap.equals(_o_.monsterNumMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.collectNumMap.hashCode();
		_h_ += _h_ * 31 + this.monsterNumMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(collectNumMap).append(",");
		_sb_.append(monsterNumMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
