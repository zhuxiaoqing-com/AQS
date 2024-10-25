package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Pendant extends warrior.mdb.XBean {
    private long srl;  // 道具实例唯一id
    private int id;  // 道具模型id
    private int bstate;  // 道具绑定状态 -1不区分 0不绑定 1绑定
    private long cread;  // 道具获得时间
    private int level;  // 挂件等级
    private int quality;  // 挂件品质
    private int exp;  // 挂件经验

    Pendant(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public Pendant() {
		this((warrior.mdb.XBean)null);
	}

	public Pendant(Pendant _o_) {
		this(_o_, null);
	}

	Pendant(Pendant _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.id = _o_.id;
		this.bstate = _o_.bstate;
		this.cread = _o_.cread;
		this.level = _o_.level;
		this.quality = _o_.quality;
		this.exp = _o_.exp;
	}

	public void copyFrom(Pendant _o_) {
        this.srl = _o_.srl;
        this.id = _o_.id;
        this.bstate = _o_.bstate;
        this.cread = _o_.cread;
        this.level = _o_.level;
        this.quality = _o_.quality;
        this.exp = _o_.exp;
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "3")
	public int getBstate(){
		return this.bstate;
	}

	@JSONField(name = "4")
	public long getCread(){
		return this.cread;
	}

	@JSONField(name = "6")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "7")
	public int getQuality(){
		return this.quality;
	}

	@JSONField(name = "8")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "3")
	public void setBstate(int _v_){
		this.bstate = _v_;
	}

	@JSONField(name = "4")
	public void setCread(long _v_){
		this.cread = _v_;
	}

	@JSONField(name = "6")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "7")
	public void setQuality(int _v_){
		this.quality = _v_;
	}

	@JSONField(name = "8")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Pendant) {
			Pendant _o_ = (Pendant)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.id != _o_.id) return false;
			if (this.bstate != _o_.bstate) return false;
			if (this.cread != _o_.cread) return false;
			if (this.level != _o_.level) return false;
			if (this.quality != _o_.quality) return false;
			if (this.exp != _o_.exp) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.bstate);
		_h_ += _h_ * 31 + (int)(cread ^ (cread >>> 32));
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + (int)(this.quality);
		_h_ += _h_ * 31 + (int)(this.exp);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(id).append(",");
		_sb_.append(bstate).append(",");
		_sb_.append(cread).append(",");
		_sb_.append(level).append(",");
		_sb_.append(quality).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
