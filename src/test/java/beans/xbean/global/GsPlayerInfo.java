package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class GsPlayerInfo extends warrior.mdb.XBean {
    private xbean.global.PlayerBaseInfo baseInfo; 
    private long totalCFWScore; 

    GsPlayerInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.baseInfo = new PlayerBaseInfo(this);
	}

	public GsPlayerInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GsPlayerInfo(GsPlayerInfo _o_) {
		this(_o_, null);
	}

	GsPlayerInfo(GsPlayerInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.baseInfo = new PlayerBaseInfo(_o_.baseInfo, this);
		this.totalCFWScore = _o_.totalCFWScore;
	}

	public void copyFrom(GsPlayerInfo _o_) {
        this.baseInfo = new PlayerBaseInfo(_o_.baseInfo, this);
        this.totalCFWScore = _o_.totalCFWScore;
	}


	@JSONField(name = "1")
	public xbean.global.PlayerBaseInfo getBaseInfo(){
		return this.baseInfo;
	}

	@JSONField(name = "2")
	public long getTotalCFWScore(){
		return this.totalCFWScore;
	}

	@JSONField(name = "1")
	public void setBaseInfo(xbean.global.PlayerBaseInfo _v_){
		this.baseInfo = _v_;
	}

	@JSONField(name = "2")
	public void setTotalCFWScore(long _v_){
		this.totalCFWScore = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GsPlayerInfo) {
			GsPlayerInfo _o_ = (GsPlayerInfo)_o1_;
			if (!this.baseInfo.equals(_o_.baseInfo)) return false;
			if (this.totalCFWScore != _o_.totalCFWScore) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.baseInfo.hashCode();
		_h_ += _h_ * 31 + (int)(totalCFWScore ^ (totalCFWScore >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(baseInfo).append(",");
		_sb_.append(totalCFWScore).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
