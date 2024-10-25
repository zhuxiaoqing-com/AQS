package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class MentorPlayerInfo extends warrior.mdb.XBean {
    private long playerId; 
    private String name; 
    private int nation; 
    private int military;  // 本国军衔
    private int title;  // 称号
    private long gid;  // 工会Id
    private String gname;  // 工会名
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> icons;  // 工会图标
    private int avatar;  // 头像
    private long createTime;  // 创建时间

    MentorPlayerInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.gname = "";
		this.icons = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public MentorPlayerInfo() {
		this((warrior.mdb.XBean)null);
	}

	public MentorPlayerInfo(MentorPlayerInfo _o_) {
		this(_o_, null);
	}

	MentorPlayerInfo(MentorPlayerInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.name = _o_.name;
		this.nation = _o_.nation;
		this.military = _o_.military;
		this.title = _o_.title;
		this.gid = _o_.gid;
		this.gname = _o_.gname;
		this.icons = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icons.forEach((_k_, _v_) -> this.icons.put(_k_, _v_));
		this.avatar = _o_.avatar;
		this.createTime = _o_.createTime;
	}

	public void copyFrom(MentorPlayerInfo _o_) {
        this.playerId = _o_.playerId;
        this.name = _o_.name;
        this.nation = _o_.nation;
        this.military = _o_.military;
        this.title = _o_.title;
        this.gid = _o_.gid;
        this.gname = _o_.gname;
        this.icons = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icons.forEach((_k_, _v_) -> this.icons.put(_k_, _v_));
        this.avatar = _o_.avatar;
        this.createTime = _o_.createTime;
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "2")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "3")
	public int getNation(){
		return this.nation;
	}

	@JSONField(name = "4")
	public int getMilitary(){
		return this.military;
	}

	@JSONField(name = "5")
	public int getTitle(){
		return this.title;
	}

	@JSONField(name = "6")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "7")
	public String getGname(){
		return this.gname;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getIcons(){
		return this.icons;
	}

	@JSONField(name = "9")
	public int getAvatar(){
		return this.avatar;
	}

	@JSONField(name = "10")
	public long getCreateTime(){
		return this.createTime;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "2")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "3")
	public void setNation(int _v_){
		this.nation = _v_;
	}

	@JSONField(name = "4")
	public void setMilitary(int _v_){
		this.military = _v_;
	}

	@JSONField(name = "5")
	public void setTitle(int _v_){
		this.title = _v_;
	}

	@JSONField(name = "6")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "7")
	public void setGname(String _v_){
		this.gname = _v_;
	}

	@JSONField(name = "8")
	public void setIcons(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.icons = _v_;
	}

	@JSONField(name = "9")
	public void setAvatar(int _v_){
		this.avatar = _v_;
	}

	@JSONField(name = "10")
	public void setCreateTime(long _v_){
		this.createTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MentorPlayerInfo) {
			MentorPlayerInfo _o_ = (MentorPlayerInfo)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (this.nation != _o_.nation) return false;
			if (this.military != _o_.military) return false;
			if (this.title != _o_.title) return false;
			if (this.gid != _o_.gid) return false;
			if (!this.gname.equals(_o_.gname)) return false;
			if (!this.icons.equals(_o_.icons)) return false;
			if (this.avatar != _o_.avatar) return false;
			if (this.createTime != _o_.createTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + (int)(this.nation);
		_h_ += _h_ * 31 + (int)(this.military);
		_h_ += _h_ * 31 + (int)(this.title);
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + this.gname.hashCode();
		_h_ += _h_ * 31 + this.icons.hashCode();
		_h_ += _h_ * 31 + (int)(this.avatar);
		_h_ += _h_ * 31 + (int)(createTime ^ (createTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(nation).append(",");
		_sb_.append(military).append(",");
		_sb_.append(title).append(",");
		_sb_.append(gid).append(",");
		_sb_.append("T").append(gname.length()).append(",");
		_sb_.append(icons).append(",");
		_sb_.append(avatar).append(",");
		_sb_.append(createTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
