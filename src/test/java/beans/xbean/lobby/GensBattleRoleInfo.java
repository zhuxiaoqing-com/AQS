package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensBattleRoleInfo extends warrior.mdb.XBean {
    private long pid; 
    private String pname; 
    private long damage; 
    private int kill; 
    private int dead; 
    private int assist; 
    private int camp; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> icon;  // 工会图标

    GensBattleRoleInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.pname = "";
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GensBattleRoleInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensBattleRoleInfo(GensBattleRoleInfo _o_) {
		this(_o_, null);
	}

	GensBattleRoleInfo(GensBattleRoleInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.pid = _o_.pid;
		this.pname = _o_.pname;
		this.damage = _o_.damage;
		this.kill = _o_.kill;
		this.dead = _o_.dead;
		this.assist = _o_.assist;
		this.camp = _o_.camp;
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
	}

	public void copyFrom(GensBattleRoleInfo _o_) {
        this.pid = _o_.pid;
        this.pname = _o_.pname;
        this.damage = _o_.damage;
        this.kill = _o_.kill;
        this.dead = _o_.dead;
        this.assist = _o_.assist;
        this.camp = _o_.camp;
        this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getPid(){
		return this.pid;
	}

	@JSONField(name = "2")
	public String getPname(){
		return this.pname;
	}

	@JSONField(name = "3")
	public long getDamage(){
		return this.damage;
	}

	@JSONField(name = "4")
	public int getKill(){
		return this.kill;
	}

	@JSONField(name = "5")
	public int getDead(){
		return this.dead;
	}

	@JSONField(name = "6")
	public int getAssist(){
		return this.assist;
	}

	@JSONField(name = "7")
	public int getCamp(){
		return this.camp;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getIcon(){
		return this.icon;
	}

	@JSONField(name = "1")
	public void setPid(long _v_){
		this.pid = _v_;
	}

	@JSONField(name = "2")
	public void setPname(String _v_){
		this.pname = _v_;
	}

	@JSONField(name = "3")
	public void setDamage(long _v_){
		this.damage = _v_;
	}

	@JSONField(name = "4")
	public void setKill(int _v_){
		this.kill = _v_;
	}

	@JSONField(name = "5")
	public void setDead(int _v_){
		this.dead = _v_;
	}

	@JSONField(name = "6")
	public void setAssist(int _v_){
		this.assist = _v_;
	}

	@JSONField(name = "7")
	public void setCamp(int _v_){
		this.camp = _v_;
	}

	@JSONField(name = "8")
	public void setIcon(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.icon = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensBattleRoleInfo) {
			GensBattleRoleInfo _o_ = (GensBattleRoleInfo)_o1_;
			if (this.pid != _o_.pid) return false;
			if (!this.pname.equals(_o_.pname)) return false;
			if (this.damage != _o_.damage) return false;
			if (this.kill != _o_.kill) return false;
			if (this.dead != _o_.dead) return false;
			if (this.assist != _o_.assist) return false;
			if (this.camp != _o_.camp) return false;
			if (!this.icon.equals(_o_.icon)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(pid ^ (pid >>> 32));
		_h_ += _h_ * 31 + this.pname.hashCode();
		_h_ += _h_ * 31 + (int)(damage ^ (damage >>> 32));
		_h_ += _h_ * 31 + (int)(this.kill);
		_h_ += _h_ * 31 + (int)(this.dead);
		_h_ += _h_ * 31 + (int)(this.assist);
		_h_ += _h_ * 31 + (int)(this.camp);
		_h_ += _h_ * 31 + this.icon.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(pid).append(",");
		_sb_.append("T").append(pname.length()).append(",");
		_sb_.append(damage).append(",");
		_sb_.append(kill).append(",");
		_sb_.append(dead).append(",");
		_sb_.append(assist).append(",");
		_sb_.append(camp).append(",");
		_sb_.append(icon).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
