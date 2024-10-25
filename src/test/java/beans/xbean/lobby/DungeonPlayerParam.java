package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class DungeonPlayerParam extends warrior.mdb.XBean {
    private int count1; 
    private int count2; 

    DungeonPlayerParam(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public DungeonPlayerParam() {
		this((warrior.mdb.XBean)null);
	}

	public DungeonPlayerParam(DungeonPlayerParam _o_) {
		this(_o_, null);
	}

	DungeonPlayerParam(DungeonPlayerParam _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.count1 = _o_.count1;
		this.count2 = _o_.count2;
	}

	public void copyFrom(DungeonPlayerParam _o_) {
        this.count1 = _o_.count1;
        this.count2 = _o_.count2;
	}


	@JSONField(name = "1")
	public int getCount1(){
		return this.count1;
	}

	@JSONField(name = "2")
	public int getCount2(){
		return this.count2;
	}

	@JSONField(name = "1")
	public void setCount1(int _v_){
		this.count1 = _v_;
	}

	@JSONField(name = "2")
	public void setCount2(int _v_){
		this.count2 = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof DungeonPlayerParam) {
			DungeonPlayerParam _o_ = (DungeonPlayerParam)_o1_;
			if (this.count1 != _o_.count1) return false;
			if (this.count2 != _o_.count2) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.count1);
		_h_ += _h_ * 31 + (int)(this.count2);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(count1).append(",");
		_sb_.append(count2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
