package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class WarOrder extends warrior.mdb.XBean {
    private int id; 
    private int exp;  // 总经验
    private java.util.concurrent.CopyOnWriteArraySet<Integer> type;  // 战令类型
    private int dailyExp;  // 每天获得的经验
    private java.util.concurrent.CopyOnWriteArraySet<Integer> freeList;  // 已经领取过的免费奖励
    private java.util.concurrent.CopyOnWriteArraySet<Integer> advancedList;  // 已经领取过的进阶奖励
    private java.util.concurrent.CopyOnWriteArraySet<Integer> advanced2List;  // 已领取过的进阶2奖励

    WarOrder(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.type = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.freeList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.advancedList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.advanced2List = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public WarOrder() {
		this((warrior.mdb.XBean)null);
	}

	public WarOrder(WarOrder _o_) {
		this(_o_, null);
	}

	WarOrder(WarOrder _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.exp = _o_.exp;
		this.type = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.type.forEach(_v_ -> this.type.add(_v_));
		this.dailyExp = _o_.dailyExp;
		this.freeList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.freeList.forEach(_v_ -> this.freeList.add(_v_));
		this.advancedList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.advancedList.forEach(_v_ -> this.advancedList.add(_v_));
		this.advanced2List = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.advanced2List.forEach(_v_ -> this.advanced2List.add(_v_));
	}

	public void copyFrom(WarOrder _o_) {
        this.id = _o_.id;
        this.exp = _o_.exp;
        this.type = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.type.forEach(_v_ -> this.type.add(_v_));
        this.dailyExp = _o_.dailyExp;
        this.freeList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.freeList.forEach(_v_ -> this.freeList.add(_v_));
        this.advancedList = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.advancedList.forEach(_v_ -> this.advancedList.add(_v_));
        this.advanced2List = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.advanced2List.forEach(_v_ -> this.advanced2List.add(_v_));
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "3")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getType(){
		return this.type;
	}

	@JSONField(name = "5")
	public int getDailyExp(){
		return this.dailyExp;
	}

	@JSONField(name = "7")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getFreeList(){
		return this.freeList;
	}

	@JSONField(name = "8")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getAdvancedList(){
		return this.advancedList;
	}

	@JSONField(name = "9")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getAdvanced2List(){
		return this.advanced2List;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "3")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@JSONField(name = "4")
	public void setType(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.type = _v_;
	}

	@JSONField(name = "5")
	public void setDailyExp(int _v_){
		this.dailyExp = _v_;
	}

	@JSONField(name = "7")
	public void setFreeList(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.freeList = _v_;
	}

	@JSONField(name = "8")
	public void setAdvancedList(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.advancedList = _v_;
	}

	@JSONField(name = "9")
	public void setAdvanced2List(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.advanced2List = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WarOrder) {
			WarOrder _o_ = (WarOrder)_o1_;
			if (this.id != _o_.id) return false;
			if (this.exp != _o_.exp) return false;
			if (!this.type.equals(_o_.type)) return false;
			if (this.dailyExp != _o_.dailyExp) return false;
			if (!this.freeList.equals(_o_.freeList)) return false;
			if (!this.advancedList.equals(_o_.advancedList)) return false;
			if (!this.advanced2List.equals(_o_.advanced2List)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.exp);
		_h_ += _h_ * 31 + this.type.hashCode();
		_h_ += _h_ * 31 + (int)(this.dailyExp);
		_h_ += _h_ * 31 + this.freeList.hashCode();
		_h_ += _h_ * 31 + this.advancedList.hashCode();
		_h_ += _h_ * 31 + this.advanced2List.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(type).append(",");
		_sb_.append(dailyExp).append(",");
		_sb_.append(freeList).append(",");
		_sb_.append(advancedList).append(",");
		_sb_.append(advanced2List).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
