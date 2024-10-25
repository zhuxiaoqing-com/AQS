package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class HomeDepot extends warrior.mdb.XBean {
    private String name;  // 仓库名称
    private xbean.logic.Bag items;  // 仓库道具

    HomeDepot(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.items = new Bag(this);
	}

	public HomeDepot() {
		this((warrior.mdb.XBean)null);
	}

	public HomeDepot(HomeDepot _o_) {
		this(_o_, null);
	}

	HomeDepot(HomeDepot _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.name = _o_.name;
		this.items = new Bag(_o_.items, this);
	}

	public void copyFrom(HomeDepot _o_) {
        this.name = _o_.name;
        this.items = new Bag(_o_.items, this);
	}


	@JSONField(name = "1")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "2")
	public xbean.logic.Bag getItems(){
		return this.items;
	}

	@JSONField(name = "1")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "2")
	public void setItems(xbean.logic.Bag _v_){
		this.items = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof HomeDepot) {
			HomeDepot _o_ = (HomeDepot)_o1_;
			if (!this.name.equals(_o_.name)) return false;
			if (!this.items.equals(_o_.items)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + this.items.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(items).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
