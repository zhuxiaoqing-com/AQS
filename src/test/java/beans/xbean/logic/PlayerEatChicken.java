package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerEatChicken extends warrior.mdb.XBean {
    private xbean.logic.EatChickenSave save;  // save数据
    private int firstNum;  // 第一名次数
    private java.util.concurrent.CopyOnWriteArrayList<EatChickenRecord> recordList;  // 最近n场表现

    PlayerEatChicken(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.save = new EatChickenSave(this);
		this.recordList = new java.util.concurrent.CopyOnWriteArrayList<EatChickenRecord>();
	}

	public PlayerEatChicken() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerEatChicken(PlayerEatChicken _o_) {
		this(_o_, null);
	}

	PlayerEatChicken(PlayerEatChicken _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.save = new EatChickenSave(_o_.save, this);
		this.firstNum = _o_.firstNum;
		this.recordList = new java.util.concurrent.CopyOnWriteArrayList<EatChickenRecord>();
		_o_.recordList.forEach(_v_ -> this.recordList.add(new EatChickenRecord(_v_)));
	}

	public void copyFrom(PlayerEatChicken _o_) {
        this.save = new EatChickenSave(_o_.save, this);
        this.firstNum = _o_.firstNum;
        this.recordList = new java.util.concurrent.CopyOnWriteArrayList<EatChickenRecord>();
		_o_.recordList.forEach(_v_ -> this.recordList.add(new EatChickenRecord(_v_)));
	}


	@JSONField(name = "1")
	public xbean.logic.EatChickenSave getSave(){
		return this.save;
	}

	@JSONField(name = "2")
	public int getFirstNum(){
		return this.firstNum;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<EatChickenRecord> getRecordList(){
		return this.recordList;
	}

	@JSONField(name = "1")
	public void setSave(xbean.logic.EatChickenSave _v_){
		this.save = _v_;
	}

	@JSONField(name = "2")
	public void setFirstNum(int _v_){
		this.firstNum = _v_;
	}

	@JSONField(name = "3")
	public void setRecordList(java.util.concurrent.CopyOnWriteArrayList<EatChickenRecord> _v_){
		this.recordList = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerEatChicken) {
			PlayerEatChicken _o_ = (PlayerEatChicken)_o1_;
			if (!this.save.equals(_o_.save)) return false;
			if (this.firstNum != _o_.firstNum) return false;
			if (!this.recordList.equals(_o_.recordList)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.save.hashCode();
		_h_ += _h_ * 31 + (int)(this.firstNum);
		_h_ += _h_ * 31 + this.recordList.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(save).append(",");
		_sb_.append(firstNum).append(",");
		_sb_.append(recordList).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
