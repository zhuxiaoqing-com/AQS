package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class BattleAward extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean> items; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> skilleds; 
    private java.util.concurrent.ConcurrentHashMap<Long, Integer> tanks; 

    BattleAward(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean>();
		this.skilleds = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.tanks = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
	}

	public BattleAward() {
		this((warrior.mdb.XBean)null);
	}

	public BattleAward(BattleAward _o_) {
		this(_o_, null);
	}

	BattleAward(BattleAward _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.items = new java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean>();
		_o_.items.forEach(_v_ -> this.items.add(new LBItemInfoBean(_v_)));
		this.skilleds = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.skilleds.forEach((_k_, _v_) -> this.skilleds.put(_k_, _v_));
		this.tanks = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.tanks.forEach((_k_, _v_) -> this.tanks.put(_k_, _v_));
	}

	public void copyFrom(BattleAward _o_) {
        this.items = new java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean>();
		_o_.items.forEach(_v_ -> this.items.add(new LBItemInfoBean(_v_)));
        this.skilleds = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.skilleds.forEach((_k_, _v_) -> this.skilleds.put(_k_, _v_));
        this.tanks = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.tanks.forEach((_k_, _v_) -> this.tanks.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean> getItems(){
		return this.items;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getSkilleds(){
		return this.skilleds;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Long, Integer> getTanks(){
		return this.tanks;
	}

	@JSONField(name = "1")
	public void setItems(java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean> _v_){
		this.items = _v_;
	}

	@JSONField(name = "2")
	public void setSkilleds(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.skilleds = _v_;
	}

	@JSONField(name = "3")
	public void setTanks(java.util.concurrent.ConcurrentHashMap<Long, Integer> _v_){
		this.tanks = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BattleAward) {
			BattleAward _o_ = (BattleAward)_o1_;
			if (!this.items.equals(_o_.items)) return false;
			if (!this.skilleds.equals(_o_.skilleds)) return false;
			if (!this.tanks.equals(_o_.tanks)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.items.hashCode();
		_h_ += _h_ * 31 + this.skilleds.hashCode();
		_h_ += _h_ * 31 + this.tanks.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(items).append(",");
		_sb_.append(skilleds).append(",");
		_sb_.append(tanks).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
