package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class CrossSeatInfo extends warrior.mdb.XBean {
    private boolean crossSeatOpen;  // 哪个阵营参加跨服战是否已经确定
    private boolean isOccupyCity;  // 是否是占领主城结束导致跨服战开启
    private int winFaction;  // 胜利阵营
    private long winTimeMill;  // 胜利时刻毫秒时间戳
    private int occupyAreaNum;  // 胜利时占领区域数量
    private int occupyCityId;  // 胜利时占领区域数量

    CrossSeatInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public CrossSeatInfo() {
		this((warrior.mdb.XBean)null);
	}

	public CrossSeatInfo(CrossSeatInfo _o_) {
		this(_o_, null);
	}

	CrossSeatInfo(CrossSeatInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.crossSeatOpen = _o_.crossSeatOpen;
		this.isOccupyCity = _o_.isOccupyCity;
		this.winFaction = _o_.winFaction;
		this.winTimeMill = _o_.winTimeMill;
		this.occupyAreaNum = _o_.occupyAreaNum;
		this.occupyCityId = _o_.occupyCityId;
	}

	public void copyFrom(CrossSeatInfo _o_) {
        this.crossSeatOpen = _o_.crossSeatOpen;
        this.isOccupyCity = _o_.isOccupyCity;
        this.winFaction = _o_.winFaction;
        this.winTimeMill = _o_.winTimeMill;
        this.occupyAreaNum = _o_.occupyAreaNum;
        this.occupyCityId = _o_.occupyCityId;
	}


	@JSONField(name = "1")
	public boolean getCrossSeatOpen(){
		return this.crossSeatOpen;
	}

	@JSONField(name = "2")
	public boolean getIsOccupyCity(){
		return this.isOccupyCity;
	}

	@JSONField(name = "3")
	public int getWinFaction(){
		return this.winFaction;
	}

	@JSONField(name = "4")
	public long getWinTimeMill(){
		return this.winTimeMill;
	}

	@JSONField(name = "5")
	public int getOccupyAreaNum(){
		return this.occupyAreaNum;
	}

	@JSONField(name = "6")
	public int getOccupyCityId(){
		return this.occupyCityId;
	}

	@JSONField(name = "1")
	public void setCrossSeatOpen(boolean _v_){
		this.crossSeatOpen = _v_;
	}

	@JSONField(name = "2")
	public void setIsOccupyCity(boolean _v_){
		this.isOccupyCity = _v_;
	}

	@JSONField(name = "3")
	public void setWinFaction(int _v_){
		this.winFaction = _v_;
	}

	@JSONField(name = "4")
	public void setWinTimeMill(long _v_){
		this.winTimeMill = _v_;
	}

	@JSONField(name = "5")
	public void setOccupyAreaNum(int _v_){
		this.occupyAreaNum = _v_;
	}

	@JSONField(name = "6")
	public void setOccupyCityId(int _v_){
		this.occupyCityId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CrossSeatInfo) {
			CrossSeatInfo _o_ = (CrossSeatInfo)_o1_;
			if (this.crossSeatOpen != _o_.crossSeatOpen) return false;
			if (this.isOccupyCity != _o_.isOccupyCity) return false;
			if (this.winFaction != _o_.winFaction) return false;
			if (this.winTimeMill != _o_.winTimeMill) return false;
			if (this.occupyAreaNum != _o_.occupyAreaNum) return false;
			if (this.occupyCityId != _o_.occupyCityId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (this.crossSeatOpen ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.isOccupyCity ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.winFaction);
		_h_ += _h_ * 31 + (int)(winTimeMill ^ (winTimeMill >>> 32));
		_h_ += _h_ * 31 + (int)(this.occupyAreaNum);
		_h_ += _h_ * 31 + (int)(this.occupyCityId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(crossSeatOpen).append(",");
		_sb_.append(isOccupyCity).append(",");
		_sb_.append(winFaction).append(",");
		_sb_.append(winTimeMill).append(",");
		_sb_.append(occupyAreaNum).append(",");
		_sb_.append(occupyCityId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
