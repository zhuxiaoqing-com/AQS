package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CarrierMakeInfo extends warrior.mdb.XBean {
    private long srl;  // 载具制造srl
    private int createType; 
    private int carrierId;  // 载具id
    private long createTime;  // 创建时间
    private java.util.concurrent.ConcurrentHashMap<Integer, CarrierMakePart> partMap;  // 部位Map
    private boolean finish;  // 时间完成
    private xbean.lobby.PlayerBaseInfo creator;  // 创建者
    private xbean.lobby.LBGensBaseInfo gensBaseInfo; 
    private boolean returnOwnerRes;  // returnOwnerRes 是否只返回制造者的资源

    CarrierMakeInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.partMap = new java.util.concurrent.ConcurrentHashMap<Integer, CarrierMakePart>();
		this.creator = new PlayerBaseInfo(this);
		this.gensBaseInfo = new LBGensBaseInfo(this);
	}

	public CarrierMakeInfo() {
		this((warrior.mdb.XBean)null);
	}

	public CarrierMakeInfo(CarrierMakeInfo _o_) {
		this(_o_, null);
	}

	CarrierMakeInfo(CarrierMakeInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.createType = _o_.createType;
		this.carrierId = _o_.carrierId;
		this.createTime = _o_.createTime;
		this.partMap = new java.util.concurrent.ConcurrentHashMap<Integer, CarrierMakePart>();
		_o_.partMap.forEach((_k_, _v_) -> this.partMap.put(_k_, new CarrierMakePart(_v_)));
		this.finish = _o_.finish;
		this.creator = new PlayerBaseInfo(_o_.creator, this);
		this.gensBaseInfo = new LBGensBaseInfo(_o_.gensBaseInfo, this);
		this.returnOwnerRes = _o_.returnOwnerRes;
	}

	public void copyFrom(CarrierMakeInfo _o_) {
        this.srl = _o_.srl;
        this.createType = _o_.createType;
        this.carrierId = _o_.carrierId;
        this.createTime = _o_.createTime;
        this.partMap = new java.util.concurrent.ConcurrentHashMap<Integer, CarrierMakePart>();
		_o_.partMap.forEach((_k_, _v_) -> this.partMap.put(_k_, new CarrierMakePart(_v_)));
        this.finish = _o_.finish;
        this.creator = new PlayerBaseInfo(_o_.creator, this);
        this.gensBaseInfo = new LBGensBaseInfo(_o_.gensBaseInfo, this);
        this.returnOwnerRes = _o_.returnOwnerRes;
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public int getCreateType(){
		return this.createType;
	}

	@JSONField(name = "3")
	public int getCarrierId(){
		return this.carrierId;
	}

	@JSONField(name = "5")
	public long getCreateTime(){
		return this.createTime;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, CarrierMakePart> getPartMap(){
		return this.partMap;
	}

	@JSONField(name = "7")
	public boolean getFinish(){
		return this.finish;
	}

	@JSONField(name = "9")
	public xbean.lobby.PlayerBaseInfo getCreator(){
		return this.creator;
	}

	@JSONField(name = "10")
	public xbean.lobby.LBGensBaseInfo getGensBaseInfo(){
		return this.gensBaseInfo;
	}

	@JSONField(name = "11")
	public boolean getReturnOwnerRes(){
		return this.returnOwnerRes;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setCreateType(int _v_){
		this.createType = _v_;
	}

	@JSONField(name = "3")
	public void setCarrierId(int _v_){
		this.carrierId = _v_;
	}

	@JSONField(name = "5")
	public void setCreateTime(long _v_){
		this.createTime = _v_;
	}

	@JSONField(name = "6")
	public void setPartMap(java.util.concurrent.ConcurrentHashMap<Integer, CarrierMakePart> _v_){
		this.partMap = _v_;
	}

	@JSONField(name = "7")
	public void setFinish(boolean _v_){
		this.finish = _v_;
	}

	@JSONField(name = "9")
	public void setCreator(xbean.lobby.PlayerBaseInfo _v_){
		this.creator = _v_;
	}

	@JSONField(name = "10")
	public void setGensBaseInfo(xbean.lobby.LBGensBaseInfo _v_){
		this.gensBaseInfo = _v_;
	}

	@JSONField(name = "11")
	public void setReturnOwnerRes(boolean _v_){
		this.returnOwnerRes = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CarrierMakeInfo) {
			CarrierMakeInfo _o_ = (CarrierMakeInfo)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.createType != _o_.createType) return false;
			if (this.carrierId != _o_.carrierId) return false;
			if (this.createTime != _o_.createTime) return false;
			if (!this.partMap.equals(_o_.partMap)) return false;
			if (this.finish != _o_.finish) return false;
			if (!this.creator.equals(_o_.creator)) return false;
			if (!this.gensBaseInfo.equals(_o_.gensBaseInfo)) return false;
			if (this.returnOwnerRes != _o_.returnOwnerRes) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(this.createType);
		_h_ += _h_ * 31 + (int)(this.carrierId);
		_h_ += _h_ * 31 + (int)(createTime ^ (createTime >>> 32));
		_h_ += _h_ * 31 + this.partMap.hashCode();
		_h_ += _h_ * 31 + (this.finish ? 1231 : 1237);
		_h_ += _h_ * 31 + this.creator.hashCode();
		_h_ += _h_ * 31 + this.gensBaseInfo.hashCode();
		_h_ += _h_ * 31 + (this.returnOwnerRes ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(createType).append(",");
		_sb_.append(carrierId).append(",");
		_sb_.append(createTime).append(",");
		_sb_.append(partMap).append(",");
		_sb_.append(finish).append(",");
		_sb_.append(creator).append(",");
		_sb_.append(gensBaseInfo).append(",");
		_sb_.append(returnOwnerRes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
