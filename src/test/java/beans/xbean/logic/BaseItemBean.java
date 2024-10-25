package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class BaseItemBean extends warrior.mdb.XBean {
    private int id;  // 道具id
    private int num;  // 道具数量
    private int weight;  // 权重值

    BaseItemBean(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public BaseItemBean() {
		this((warrior.mdb.XBean)null);
	}

	public BaseItemBean(BaseItemBean _o_) {
		this(_o_, null);
	}

	BaseItemBean(BaseItemBean _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.num = _o_.num;
		this.weight = _o_.weight;
	}

	public void copyFrom(BaseItemBean _o_) {
        this.id = _o_.id;
        this.num = _o_.num;
        this.weight = _o_.weight;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "3")
	public int getWeight(){
		return this.weight;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@JSONField(name = "3")
	public void setWeight(int _v_){
		this.weight = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BaseItemBean) {
			BaseItemBean _o_ = (BaseItemBean)_o1_;
			if (this.id != _o_.id) return false;
			if (this.num != _o_.num) return false;
			if (this.weight != _o_.weight) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.num);
		_h_ += _h_ * 31 + (int)(this.weight);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(num).append(",");
		_sb_.append(weight).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
