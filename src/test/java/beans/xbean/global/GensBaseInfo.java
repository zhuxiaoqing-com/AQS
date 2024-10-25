package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensBaseInfo extends warrior.mdb.XBean {
    private long id;  // 公会id
    private String name;  // 公会名字
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> icon;  // 公会icon
    private int ally;  // 联盟

    GensBaseInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GensBaseInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensBaseInfo(GensBaseInfo _o_) {
		this(_o_, null);
	}

	GensBaseInfo(GensBaseInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.name = _o_.name;
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
		this.ally = _o_.ally;
	}

	public void copyFrom(GensBaseInfo _o_) {
        this.id = _o_.id;
        this.name = _o_.name;
        this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
        this.ally = _o_.ally;
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getIcon(){
		return this.icon;
	}

	@JSONField(name = "4")
	public int getAlly(){
		return this.ally;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "3")
	public void setIcon(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.icon = _v_;
	}

	@JSONField(name = "4")
	public void setAlly(int _v_){
		this.ally = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensBaseInfo) {
			GensBaseInfo _o_ = (GensBaseInfo)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (!this.icon.equals(_o_.icon)) return false;
			if (this.ally != _o_.ally) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + this.icon.hashCode();
		_h_ += _h_ * 31 + (int)(this.ally);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(icon).append(",");
		_sb_.append(ally).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
