package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerClassifyLog extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, ClassifyLog> log;  // key:武器类型，载具，副本
    private int weapon;  // 最爱武器ID
    private int carrier;  // 最爱载具ID
    private int map;  // 最爱副本ID

    PlayerClassifyLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.log = new java.util.concurrent.ConcurrentHashMap<Integer, ClassifyLog>();
	}

	public PlayerClassifyLog() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerClassifyLog(PlayerClassifyLog _o_) {
		this(_o_, null);
	}

	PlayerClassifyLog(PlayerClassifyLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.log = new java.util.concurrent.ConcurrentHashMap<Integer, ClassifyLog>();
		_o_.log.forEach((_k_, _v_) -> this.log.put(_k_, new ClassifyLog(_v_)));
		this.weapon = _o_.weapon;
		this.carrier = _o_.carrier;
		this.map = _o_.map;
	}

	public void copyFrom(PlayerClassifyLog _o_) {
        this.log = new java.util.concurrent.ConcurrentHashMap<Integer, ClassifyLog>();
		_o_.log.forEach((_k_, _v_) -> this.log.put(_k_, new ClassifyLog(_v_)));
        this.weapon = _o_.weapon;
        this.carrier = _o_.carrier;
        this.map = _o_.map;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, ClassifyLog> getLog(){
		return this.log;
	}

	@JSONField(name = "2")
	public int getWeapon(){
		return this.weapon;
	}

	@JSONField(name = "3")
	public int getCarrier(){
		return this.carrier;
	}

	@JSONField(name = "4")
	public int getMap(){
		return this.map;
	}

	@JSONField(name = "1")
	public void setLog(java.util.concurrent.ConcurrentHashMap<Integer, ClassifyLog> _v_){
		this.log = _v_;
	}

	@JSONField(name = "2")
	public void setWeapon(int _v_){
		this.weapon = _v_;
	}

	@JSONField(name = "3")
	public void setCarrier(int _v_){
		this.carrier = _v_;
	}

	@JSONField(name = "4")
	public void setMap(int _v_){
		this.map = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerClassifyLog) {
			PlayerClassifyLog _o_ = (PlayerClassifyLog)_o1_;
			if (!this.log.equals(_o_.log)) return false;
			if (this.weapon != _o_.weapon) return false;
			if (this.carrier != _o_.carrier) return false;
			if (this.map != _o_.map) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.log.hashCode();
		_h_ += _h_ * 31 + (int)(this.weapon);
		_h_ += _h_ * 31 + (int)(this.carrier);
		_h_ += _h_ * 31 + (int)(this.map);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(log).append(",");
		_sb_.append(weapon).append(",");
		_sb_.append(carrier).append(",");
		_sb_.append(map).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
