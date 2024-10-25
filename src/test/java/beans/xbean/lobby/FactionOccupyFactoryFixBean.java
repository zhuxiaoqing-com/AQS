package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionOccupyFactoryFixBean extends warrior.mdb.XBean {
    private int areaId;  // 区域id
    private int totalDonate;  // 当前总捐献值

    FactionOccupyFactoryFixBean(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public FactionOccupyFactoryFixBean() {
		this((warrior.mdb.XBean)null);
	}

	public FactionOccupyFactoryFixBean(FactionOccupyFactoryFixBean _o_) {
		this(_o_, null);
	}

	FactionOccupyFactoryFixBean(FactionOccupyFactoryFixBean _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaId = _o_.areaId;
		this.totalDonate = _o_.totalDonate;
	}

	public void copyFrom(FactionOccupyFactoryFixBean _o_) {
        this.areaId = _o_.areaId;
        this.totalDonate = _o_.totalDonate;
	}


	@JSONField(name = "1")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "2")
	public int getTotalDonate(){
		return this.totalDonate;
	}

	@JSONField(name = "1")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "2")
	public void setTotalDonate(int _v_){
		this.totalDonate = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionOccupyFactoryFixBean) {
			FactionOccupyFactoryFixBean _o_ = (FactionOccupyFactoryFixBean)_o1_;
			if (this.areaId != _o_.areaId) return false;
			if (this.totalDonate != _o_.totalDonate) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + (int)(this.totalDonate);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaId).append(",");
		_sb_.append(totalDonate).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
