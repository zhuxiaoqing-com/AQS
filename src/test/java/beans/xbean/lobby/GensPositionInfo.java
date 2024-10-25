package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensPositionInfo extends warrior.mdb.XBean {
    private int id; 
    private int dailyDonate;  // 每日贡献值
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> defaultDepotReceive;  // 仓库领取数量
    private java.util.concurrent.CopyOnWriteArraySet<Integer> powerType;  // 拥有的权限

    GensPositionInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.defaultDepotReceive = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.powerType = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public GensPositionInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensPositionInfo(GensPositionInfo _o_) {
		this(_o_, null);
	}

	GensPositionInfo(GensPositionInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.dailyDonate = _o_.dailyDonate;
		this.defaultDepotReceive = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.defaultDepotReceive.forEach((_k_, _v_) -> this.defaultDepotReceive.put(_k_, _v_));
		this.powerType = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.powerType.forEach(_v_ -> this.powerType.add(_v_));
	}

	public void copyFrom(GensPositionInfo _o_) {
        this.id = _o_.id;
        this.dailyDonate = _o_.dailyDonate;
        this.defaultDepotReceive = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.defaultDepotReceive.forEach((_k_, _v_) -> this.defaultDepotReceive.put(_k_, _v_));
        this.powerType = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.powerType.forEach(_v_ -> this.powerType.add(_v_));
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getDailyDonate(){
		return this.dailyDonate;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDefaultDepotReceive(){
		return this.defaultDepotReceive;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getPowerType(){
		return this.powerType;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setDailyDonate(int _v_){
		this.dailyDonate = _v_;
	}

	@JSONField(name = "3")
	public void setDefaultDepotReceive(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.defaultDepotReceive = _v_;
	}

	@JSONField(name = "4")
	public void setPowerType(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.powerType = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensPositionInfo) {
			GensPositionInfo _o_ = (GensPositionInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (this.dailyDonate != _o_.dailyDonate) return false;
			if (!this.defaultDepotReceive.equals(_o_.defaultDepotReceive)) return false;
			if (!this.powerType.equals(_o_.powerType)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.dailyDonate);
		_h_ += _h_ * 31 + this.defaultDepotReceive.hashCode();
		_h_ += _h_ * 31 + this.powerType.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(dailyDonate).append(",");
		_sb_.append(defaultDepotReceive).append(",");
		_sb_.append(powerType).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
