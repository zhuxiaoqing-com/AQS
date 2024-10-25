package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ClassifyLog extends warrior.mdb.XBean {
    private int type;  // 类型(武器的分类，载具，副本)
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> info;  // key:自定义类型，value: 击杀总数，死亡，训练，总耐久（油量）消耗
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> consume;  // key:(道具ID，载具ID，副本ID) value:每个武器的使用耐久，每个载具的油料消耗，每个副本的完成次数
    private int cuid;  // 当前类型最爱的武器ID或者最爱的坦克ID

    ClassifyLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.info = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.consume = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
	}

	public ClassifyLog() {
		this((warrior.mdb.XBean)null);
	}

	public ClassifyLog(ClassifyLog _o_) {
		this(_o_, null);
	}

	ClassifyLog(ClassifyLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.type = _o_.type;
		this.info = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.info.forEach((_k_, _v_) -> this.info.put(_k_, _v_));
		this.consume = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.consume.forEach((_k_, _v_) -> this.consume.put(_k_, _v_));
		this.cuid = _o_.cuid;
	}

	public void copyFrom(ClassifyLog _o_) {
        this.type = _o_.type;
        this.info = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.info.forEach((_k_, _v_) -> this.info.put(_k_, _v_));
        this.consume = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.consume.forEach((_k_, _v_) -> this.consume.put(_k_, _v_));
        this.cuid = _o_.cuid;
	}


	@JSONField(name = "1")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getInfo(){
		return this.info;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getConsume(){
		return this.consume;
	}

	@JSONField(name = "4")
	public int getCuid(){
		return this.cuid;
	}

	@JSONField(name = "1")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "2")
	public void setInfo(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.info = _v_;
	}

	@JSONField(name = "3")
	public void setConsume(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.consume = _v_;
	}

	@JSONField(name = "4")
	public void setCuid(int _v_){
		this.cuid = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ClassifyLog) {
			ClassifyLog _o_ = (ClassifyLog)_o1_;
			if (this.type != _o_.type) return false;
			if (!this.info.equals(_o_.info)) return false;
			if (!this.consume.equals(_o_.consume)) return false;
			if (this.cuid != _o_.cuid) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + this.info.hashCode();
		_h_ += _h_ * 31 + this.consume.hashCode();
		_h_ += _h_ * 31 + (int)(this.cuid);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(type).append(",");
		_sb_.append(info).append(",");
		_sb_.append(consume).append(",");
		_sb_.append(cuid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
