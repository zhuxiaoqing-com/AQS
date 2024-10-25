package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class RankObj extends warrior.mdb.XBean {
    private long id;  // 排行对象id
    private double score;  // 分数
    private String name;  // 玩家名字
    private int serverId; 
    private int faction; 
    private xbean.global.PlayerBaseInfo baseInfo;  // 相关base

    RankObj(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.baseInfo = new PlayerBaseInfo(this);
	}

	public RankObj() {
		this((warrior.mdb.XBean)null);
	}

	public RankObj(RankObj _o_) {
		this(_o_, null);
	}

	RankObj(RankObj _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.score = _o_.score;
		this.name = _o_.name;
		this.serverId = _o_.serverId;
		this.faction = _o_.faction;
		this.baseInfo = new PlayerBaseInfo(_o_.baseInfo, this);
	}

	public void copyFrom(RankObj _o_) {
        this.id = _o_.id;
        this.score = _o_.score;
        this.name = _o_.name;
        this.serverId = _o_.serverId;
        this.faction = _o_.faction;
        this.baseInfo = new PlayerBaseInfo(_o_.baseInfo, this);
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public double getScore(){
		return this.score;
	}

	@JSONField(name = "3")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "4")
	public int getServerId(){
		return this.serverId;
	}

	@JSONField(name = "5")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "7")
	public xbean.global.PlayerBaseInfo getBaseInfo(){
		return this.baseInfo;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setScore(double _v_){
		this.score = _v_;
	}

	@JSONField(name = "3")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "4")
	public void setServerId(int _v_){
		this.serverId = _v_;
	}

	@JSONField(name = "5")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "7")
	public void setBaseInfo(xbean.global.PlayerBaseInfo _v_){
		this.baseInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RankObj) {
			RankObj _o_ = (RankObj)_o1_;
			if (this.id != _o_.id) return false;
			if (this.score != _o_.score) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (this.serverId != _o_.serverId) return false;
			if (this.faction != _o_.faction) return false;
			if (!this.baseInfo.equals(_o_.baseInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + Double.valueOf(score).hashCode();
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + (int)(this.serverId);
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + this.baseInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(score).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(serverId).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(baseInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
