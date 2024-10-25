package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class EquipWareHelp extends warrior.mdb.XBean {
    private long id; 
    private long initor; 
    private long acceptor; 
    private int equipWareId; 
    private long finishTime; 

    EquipWareHelp(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public EquipWareHelp() {
		this((warrior.mdb.XBean)null);
	}

	public EquipWareHelp(EquipWareHelp _o_) {
		this(_o_, null);
	}

	EquipWareHelp(EquipWareHelp _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.initor = _o_.initor;
		this.acceptor = _o_.acceptor;
		this.equipWareId = _o_.equipWareId;
		this.finishTime = _o_.finishTime;
	}

	public void copyFrom(EquipWareHelp _o_) {
        this.id = _o_.id;
        this.initor = _o_.initor;
        this.acceptor = _o_.acceptor;
        this.equipWareId = _o_.equipWareId;
        this.finishTime = _o_.finishTime;
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public long getInitor(){
		return this.initor;
	}

	@JSONField(name = "3")
	public long getAcceptor(){
		return this.acceptor;
	}

	@JSONField(name = "4")
	public int getEquipWareId(){
		return this.equipWareId;
	}

	@JSONField(name = "5")
	public long getFinishTime(){
		return this.finishTime;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setInitor(long _v_){
		this.initor = _v_;
	}

	@JSONField(name = "3")
	public void setAcceptor(long _v_){
		this.acceptor = _v_;
	}

	@JSONField(name = "4")
	public void setEquipWareId(int _v_){
		this.equipWareId = _v_;
	}

	@JSONField(name = "5")
	public void setFinishTime(long _v_){
		this.finishTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EquipWareHelp) {
			EquipWareHelp _o_ = (EquipWareHelp)_o1_;
			if (this.id != _o_.id) return false;
			if (this.initor != _o_.initor) return false;
			if (this.acceptor != _o_.acceptor) return false;
			if (this.equipWareId != _o_.equipWareId) return false;
			if (this.finishTime != _o_.finishTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + (int)(initor ^ (initor >>> 32));
		_h_ += _h_ * 31 + (int)(acceptor ^ (acceptor >>> 32));
		_h_ += _h_ * 31 + (int)(this.equipWareId);
		_h_ += _h_ * 31 + (int)(finishTime ^ (finishTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(initor).append(",");
		_sb_.append(acceptor).append(",");
		_sb_.append(equipWareId).append(",");
		_sb_.append(finishTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
