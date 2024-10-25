package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensWarLandInfo extends warrior.mdb.XBean {
    public static final int PEACE = 1;   // 和平状态
    public static final int DECLARE = 2;   // 宣战中
    public static final int FIGHTING = 3;   // 交战中
    public static final int PROTECT = 4;   // 保护状态(当天结束后为保护状态)

    private int landId;  // 地块id
    private xbean.global.GensBaseInfo occupyGens;  // 归属工会
    private long occupyTime;  // 被占领时间
    private xbean.global.GensBaseInfo declareGens;  // 宣战工会
    private long declareTime;  // 宣战时间
    private long battleTime;  // 开战时间
    private int toLandId;  // 向某方宣战
    private int fromLandId;  // 被某方宣战
    private boolean preCreated;  // 地图是否已经预创建
    private long lastAddRes;  // 上一次增加资源时间
    private int state;  // 状态

    GensWarLandInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.occupyGens = new GensBaseInfo(this);
		this.declareGens = new GensBaseInfo(this);
	}

	public GensWarLandInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensWarLandInfo(GensWarLandInfo _o_) {
		this(_o_, null);
	}

	GensWarLandInfo(GensWarLandInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.landId = _o_.landId;
		this.occupyGens = new GensBaseInfo(_o_.occupyGens, this);
		this.occupyTime = _o_.occupyTime;
		this.declareGens = new GensBaseInfo(_o_.declareGens, this);
		this.declareTime = _o_.declareTime;
		this.battleTime = _o_.battleTime;
		this.toLandId = _o_.toLandId;
		this.fromLandId = _o_.fromLandId;
		this.preCreated = _o_.preCreated;
		this.lastAddRes = _o_.lastAddRes;
		this.state = _o_.state;
	}

	public void copyFrom(GensWarLandInfo _o_) {
        this.landId = _o_.landId;
        this.occupyGens = new GensBaseInfo(_o_.occupyGens, this);
        this.occupyTime = _o_.occupyTime;
        this.declareGens = new GensBaseInfo(_o_.declareGens, this);
        this.declareTime = _o_.declareTime;
        this.battleTime = _o_.battleTime;
        this.toLandId = _o_.toLandId;
        this.fromLandId = _o_.fromLandId;
        this.preCreated = _o_.preCreated;
        this.lastAddRes = _o_.lastAddRes;
        this.state = _o_.state;
	}


	@JSONField(name = "1")
	public int getLandId(){
		return this.landId;
	}

	@JSONField(name = "2")
	public xbean.global.GensBaseInfo getOccupyGens(){
		return this.occupyGens;
	}

	@JSONField(name = "3")
	public long getOccupyTime(){
		return this.occupyTime;
	}

	@JSONField(name = "4")
	public xbean.global.GensBaseInfo getDeclareGens(){
		return this.declareGens;
	}

	@JSONField(name = "5")
	public long getDeclareTime(){
		return this.declareTime;
	}

	@JSONField(name = "6")
	public long getBattleTime(){
		return this.battleTime;
	}

	@JSONField(name = "7")
	public int getToLandId(){
		return this.toLandId;
	}

	@JSONField(name = "8")
	public int getFromLandId(){
		return this.fromLandId;
	}

	@JSONField(name = "9")
	public boolean getPreCreated(){
		return this.preCreated;
	}

	@JSONField(name = "10")
	public long getLastAddRes(){
		return this.lastAddRes;
	}

	@JSONField(name = "11")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "1")
	public void setLandId(int _v_){
		this.landId = _v_;
	}

	@JSONField(name = "2")
	public void setOccupyGens(xbean.global.GensBaseInfo _v_){
		this.occupyGens = _v_;
	}

	@JSONField(name = "3")
	public void setOccupyTime(long _v_){
		this.occupyTime = _v_;
	}

	@JSONField(name = "4")
	public void setDeclareGens(xbean.global.GensBaseInfo _v_){
		this.declareGens = _v_;
	}

	@JSONField(name = "5")
	public void setDeclareTime(long _v_){
		this.declareTime = _v_;
	}

	@JSONField(name = "6")
	public void setBattleTime(long _v_){
		this.battleTime = _v_;
	}

	@JSONField(name = "7")
	public void setToLandId(int _v_){
		this.toLandId = _v_;
	}

	@JSONField(name = "8")
	public void setFromLandId(int _v_){
		this.fromLandId = _v_;
	}

	@JSONField(name = "9")
	public void setPreCreated(boolean _v_){
		this.preCreated = _v_;
	}

	@JSONField(name = "10")
	public void setLastAddRes(long _v_){
		this.lastAddRes = _v_;
	}

	@JSONField(name = "11")
	public void setState(int _v_){
		this.state = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensWarLandInfo) {
			GensWarLandInfo _o_ = (GensWarLandInfo)_o1_;
			if (this.landId != _o_.landId) return false;
			if (!this.occupyGens.equals(_o_.occupyGens)) return false;
			if (this.occupyTime != _o_.occupyTime) return false;
			if (!this.declareGens.equals(_o_.declareGens)) return false;
			if (this.declareTime != _o_.declareTime) return false;
			if (this.battleTime != _o_.battleTime) return false;
			if (this.toLandId != _o_.toLandId) return false;
			if (this.fromLandId != _o_.fromLandId) return false;
			if (this.preCreated != _o_.preCreated) return false;
			if (this.lastAddRes != _o_.lastAddRes) return false;
			if (this.state != _o_.state) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.landId);
		_h_ += _h_ * 31 + this.occupyGens.hashCode();
		_h_ += _h_ * 31 + (int)(occupyTime ^ (occupyTime >>> 32));
		_h_ += _h_ * 31 + this.declareGens.hashCode();
		_h_ += _h_ * 31 + (int)(declareTime ^ (declareTime >>> 32));
		_h_ += _h_ * 31 + (int)(battleTime ^ (battleTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.toLandId);
		_h_ += _h_ * 31 + (int)(this.fromLandId);
		_h_ += _h_ * 31 + (this.preCreated ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(lastAddRes ^ (lastAddRes >>> 32));
		_h_ += _h_ * 31 + (int)(this.state);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(landId).append(",");
		_sb_.append(occupyGens).append(",");
		_sb_.append(occupyTime).append(",");
		_sb_.append(declareGens).append(",");
		_sb_.append(declareTime).append(",");
		_sb_.append(battleTime).append(",");
		_sb_.append(toLandId).append(",");
		_sb_.append(fromLandId).append(",");
		_sb_.append(preCreated).append(",");
		_sb_.append(lastAddRes).append(",");
		_sb_.append(state).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
