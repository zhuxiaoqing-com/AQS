package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class MakingLog extends warrior.mdb.XBean {
    private long time; 
    private int id; 
    private int num; 
    private int type; 

    MakingLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public MakingLog() {
		this((warrior.mdb.XBean)null);
	}

	public MakingLog(MakingLog _o_) {
		this(_o_, null);
	}

	MakingLog(MakingLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.time = _o_.time;
		this.id = _o_.id;
		this.num = _o_.num;
		this.type = _o_.type;
	}

	public void copyFrom(MakingLog _o_) {
        this.time = _o_.time;
        this.id = _o_.id;
        this.num = _o_.num;
        this.type = _o_.type;
	}


	@JSONField(name = "1")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "2")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "3")
	public int getNum(){
		return this.num;
	}

	@JSONField(name = "4")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "1")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@JSONField(name = "2")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "3")
	public void setNum(int _v_){
		this.num = _v_;
	}

	@JSONField(name = "4")
	public void setType(int _v_){
		this.type = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MakingLog) {
			MakingLog _o_ = (MakingLog)_o1_;
			if (this.time != _o_.time) return false;
			if (this.id != _o_.id) return false;
			if (this.num != _o_.num) return false;
			if (this.type != _o_.type) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.num);
		_h_ += _h_ * 31 + (int)(this.type);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(time).append(",");
		_sb_.append(id).append(",");
		_sb_.append(num).append(",");
		_sb_.append(type).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
