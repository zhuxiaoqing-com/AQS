package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class EatChickenSave extends warrior.mdb.XBean {
    private boolean empty;  // 是否是空的(是否没有保存数据)
    private xbean.logic.Bag playerBag;  // 玩家背包
    private xbean.logic.Bag equipBag;  // 装备背包
    private xbean.logic.PlayerMarkEquipPos playerMarkEquip;  // 玩家装备槽位数据
    private int wpos;  // 玩家当前持有的武器id

    EatChickenSave(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.playerBag = new Bag(this);
		this.equipBag = new Bag(this);
		this.playerMarkEquip = new PlayerMarkEquipPos(this);
	}

	public EatChickenSave() {
		this((warrior.mdb.XBean)null);
	}

	public EatChickenSave(EatChickenSave _o_) {
		this(_o_, null);
	}

	EatChickenSave(EatChickenSave _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.empty = _o_.empty;
		this.playerBag = new Bag(_o_.playerBag, this);
		this.equipBag = new Bag(_o_.equipBag, this);
		this.playerMarkEquip = new PlayerMarkEquipPos(_o_.playerMarkEquip, this);
		this.wpos = _o_.wpos;
	}

	public void copyFrom(EatChickenSave _o_) {
        this.empty = _o_.empty;
        this.playerBag = new Bag(_o_.playerBag, this);
        this.equipBag = new Bag(_o_.equipBag, this);
        this.playerMarkEquip = new PlayerMarkEquipPos(_o_.playerMarkEquip, this);
        this.wpos = _o_.wpos;
	}


	@JSONField(name = "1")
	public boolean getEmpty(){
		return this.empty;
	}

	@JSONField(name = "2")
	public xbean.logic.Bag getPlayerBag(){
		return this.playerBag;
	}

	@JSONField(name = "3")
	public xbean.logic.Bag getEquipBag(){
		return this.equipBag;
	}

	@JSONField(name = "4")
	public xbean.logic.PlayerMarkEquipPos getPlayerMarkEquip(){
		return this.playerMarkEquip;
	}

	@JSONField(name = "5")
	public int getWpos(){
		return this.wpos;
	}

	@JSONField(name = "1")
	public void setEmpty(boolean _v_){
		this.empty = _v_;
	}

	@JSONField(name = "2")
	public void setPlayerBag(xbean.logic.Bag _v_){
		this.playerBag = _v_;
	}

	@JSONField(name = "3")
	public void setEquipBag(xbean.logic.Bag _v_){
		this.equipBag = _v_;
	}

	@JSONField(name = "4")
	public void setPlayerMarkEquip(xbean.logic.PlayerMarkEquipPos _v_){
		this.playerMarkEquip = _v_;
	}

	@JSONField(name = "5")
	public void setWpos(int _v_){
		this.wpos = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EatChickenSave) {
			EatChickenSave _o_ = (EatChickenSave)_o1_;
			if (this.empty != _o_.empty) return false;
			if (!this.playerBag.equals(_o_.playerBag)) return false;
			if (!this.equipBag.equals(_o_.equipBag)) return false;
			if (!this.playerMarkEquip.equals(_o_.playerMarkEquip)) return false;
			if (this.wpos != _o_.wpos) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (this.empty ? 1231 : 1237);
		_h_ += _h_ * 31 + this.playerBag.hashCode();
		_h_ += _h_ * 31 + this.equipBag.hashCode();
		_h_ += _h_ * 31 + this.playerMarkEquip.hashCode();
		_h_ += _h_ * 31 + (int)(this.wpos);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(empty).append(",");
		_sb_.append(playerBag).append(",");
		_sb_.append(equipBag).append(",");
		_sb_.append(playerMarkEquip).append(",");
		_sb_.append(wpos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
