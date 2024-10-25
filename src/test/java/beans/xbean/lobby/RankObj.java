package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class RankObj extends warrior.mdb.XBean {
    private long id;  // 排行对象id
    private double score;  // 分数
    private String name;  // 玩家名字
    private String gensName;  // 军团名字
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> gensIcon;  // 军团icon
    private xbean.lobby.PlayerBaseInfo baseInfo;  // 相关base

    RankObj(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.gensName = "";
		this.gensIcon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
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
		this.gensName = _o_.gensName;
		this.gensIcon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.gensIcon.forEach((_k_, _v_) -> this.gensIcon.put(_k_, _v_));
		this.baseInfo = new PlayerBaseInfo(_o_.baseInfo, this);
	}

	public void copyFrom(RankObj _o_) {
        this.id = _o_.id;
        this.score = _o_.score;
        this.name = _o_.name;
        this.gensName = _o_.gensName;
        this.gensIcon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.gensIcon.forEach((_k_, _v_) -> this.gensIcon.put(_k_, _v_));
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
	public String getGensName(){
		return this.gensName;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getGensIcon(){
		return this.gensIcon;
	}

	@JSONField(name = "7")
	public xbean.lobby.PlayerBaseInfo getBaseInfo(){
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
	public void setGensName(String _v_){
		this.gensName = _v_;
	}

	@JSONField(name = "6")
	public void setGensIcon(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.gensIcon = _v_;
	}

	@JSONField(name = "7")
	public void setBaseInfo(xbean.lobby.PlayerBaseInfo _v_){
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
			if (!this.gensName.equals(_o_.gensName)) return false;
			if (!this.gensIcon.equals(_o_.gensIcon)) return false;
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
		_h_ += _h_ * 31 + this.gensName.hashCode();
		_h_ += _h_ * 31 + this.gensIcon.hashCode();
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
		_sb_.append("T").append(gensName.length()).append(",");
		_sb_.append(gensIcon).append(",");
		_sb_.append(baseInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
