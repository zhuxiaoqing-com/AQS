package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensGroup extends warrior.mdb.XBean {
    private int gpid;  // 分组Id
    private String gpname;  // 分组名称
    private int pt;  // 排序位置
    private long tLeader;  // 分组组长id
    private int roleNum;  // 玩家数量
    private java.util.concurrent.CopyOnWriteArraySet<Long> pids; 
    private xbean.lobby.MapInstruct instruct;  // 小地图指令

    GensGroup(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gpname = "";
		this.pids = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.instruct = new MapInstruct(this);
	}

	public GensGroup() {
		this((warrior.mdb.XBean)null);
	}

	public GensGroup(GensGroup _o_) {
		this(_o_, null);
	}

	GensGroup(GensGroup _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gpid = _o_.gpid;
		this.gpname = _o_.gpname;
		this.pt = _o_.pt;
		this.tLeader = _o_.tLeader;
		this.roleNum = _o_.roleNum;
		this.pids = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.pids.forEach(_v_ -> this.pids.add(_v_));
		this.instruct = new MapInstruct(_o_.instruct, this);
	}

	public void copyFrom(GensGroup _o_) {
        this.gpid = _o_.gpid;
        this.gpname = _o_.gpname;
        this.pt = _o_.pt;
        this.tLeader = _o_.tLeader;
        this.roleNum = _o_.roleNum;
        this.pids = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.pids.forEach(_v_ -> this.pids.add(_v_));
        this.instruct = new MapInstruct(_o_.instruct, this);
	}


	@JSONField(name = "1")
	public int getGpid(){
		return this.gpid;
	}

	@JSONField(name = "2")
	public String getGpname(){
		return this.gpname;
	}

	@JSONField(name = "3")
	public int getPt(){
		return this.pt;
	}

	@JSONField(name = "4")
	public long getTLeader(){
		return this.tLeader;
	}

	@JSONField(name = "5")
	public int getRoleNum(){
		return this.roleNum;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getPids(){
		return this.pids;
	}

	@JSONField(name = "7")
	public xbean.lobby.MapInstruct getInstruct(){
		return this.instruct;
	}

	@JSONField(name = "1")
	public void setGpid(int _v_){
		this.gpid = _v_;
	}

	@JSONField(name = "2")
	public void setGpname(String _v_){
		this.gpname = _v_;
	}

	@JSONField(name = "3")
	public void setPt(int _v_){
		this.pt = _v_;
	}

	@JSONField(name = "4")
	public void setTLeader(long _v_){
		this.tLeader = _v_;
	}

	@JSONField(name = "5")
	public void setRoleNum(int _v_){
		this.roleNum = _v_;
	}

	@JSONField(name = "6")
	public void setPids(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.pids = _v_;
	}

	@JSONField(name = "7")
	public void setInstruct(xbean.lobby.MapInstruct _v_){
		this.instruct = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensGroup) {
			GensGroup _o_ = (GensGroup)_o1_;
			if (this.gpid != _o_.gpid) return false;
			if (!this.gpname.equals(_o_.gpname)) return false;
			if (this.pt != _o_.pt) return false;
			if (this.tLeader != _o_.tLeader) return false;
			if (this.roleNum != _o_.roleNum) return false;
			if (!this.pids.equals(_o_.pids)) return false;
			if (!this.instruct.equals(_o_.instruct)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.gpid);
		_h_ += _h_ * 31 + this.gpname.hashCode();
		_h_ += _h_ * 31 + (int)(this.pt);
		_h_ += _h_ * 31 + (int)(tLeader ^ (tLeader >>> 32));
		_h_ += _h_ * 31 + (int)(this.roleNum);
		_h_ += _h_ * 31 + this.pids.hashCode();
		_h_ += _h_ * 31 + this.instruct.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gpid).append(",");
		_sb_.append("T").append(gpname.length()).append(",");
		_sb_.append(pt).append(",");
		_sb_.append(tLeader).append(",");
		_sb_.append(roleNum).append(",");
		_sb_.append(pids).append(",");
		_sb_.append(instruct).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
