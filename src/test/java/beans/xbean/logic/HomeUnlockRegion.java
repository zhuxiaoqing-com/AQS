package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class HomeUnlockRegion extends warrior.mdb.XBean {
    private int id;  // 当前Id
    private long lftime;  // 上次资源刷新时间

    HomeUnlockRegion(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public HomeUnlockRegion() {
		this((warrior.mdb.XBean)null);
	}

	public HomeUnlockRegion(HomeUnlockRegion _o_) {
		this(_o_, null);
	}

	HomeUnlockRegion(HomeUnlockRegion _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.lftime = _o_.lftime;
	}

	public void copyFrom(HomeUnlockRegion _o_) {
        this.id = _o_.id;
        this.lftime = _o_.lftime;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public long getLftime(){
		return this.lftime;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setLftime(long _v_){
		this.lftime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof HomeUnlockRegion) {
			HomeUnlockRegion _o_ = (HomeUnlockRegion)_o1_;
			if (this.id != _o_.id) return false;
			if (this.lftime != _o_.lftime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(lftime ^ (lftime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(lftime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
