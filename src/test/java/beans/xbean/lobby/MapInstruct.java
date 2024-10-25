package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class MapInstruct extends warrior.mdb.XBean {
    private int instruct;  // 小地图指令
    private int dungeonId;  // dungeonId
    private double x;  // X
    private double y;  // Y
    private double z;  // Z

    MapInstruct(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public MapInstruct() {
		this((warrior.mdb.XBean)null);
	}

	public MapInstruct(MapInstruct _o_) {
		this(_o_, null);
	}

	MapInstruct(MapInstruct _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.instruct = _o_.instruct;
		this.dungeonId = _o_.dungeonId;
		this.x = _o_.x;
		this.y = _o_.y;
		this.z = _o_.z;
	}

	public void copyFrom(MapInstruct _o_) {
        this.instruct = _o_.instruct;
        this.dungeonId = _o_.dungeonId;
        this.x = _o_.x;
        this.y = _o_.y;
        this.z = _o_.z;
	}


	@JSONField(name = "1")
	public int getInstruct(){
		return this.instruct;
	}

	@JSONField(name = "2")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "3")
	public double getX(){
		return this.x;
	}

	@JSONField(name = "4")
	public double getY(){
		return this.y;
	}

	@JSONField(name = "5")
	public double getZ(){
		return this.z;
	}

	@JSONField(name = "1")
	public void setInstruct(int _v_){
		this.instruct = _v_;
	}

	@JSONField(name = "2")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "3")
	public void setX(double _v_){
		this.x = _v_;
	}

	@JSONField(name = "4")
	public void setY(double _v_){
		this.y = _v_;
	}

	@JSONField(name = "5")
	public void setZ(double _v_){
		this.z = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MapInstruct) {
			MapInstruct _o_ = (MapInstruct)_o1_;
			if (this.instruct != _o_.instruct) return false;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (this.x != _o_.x) return false;
			if (this.y != _o_.y) return false;
			if (this.z != _o_.z) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.instruct);
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + Double.valueOf(x).hashCode();
		_h_ += _h_ * 31 + Double.valueOf(y).hashCode();
		_h_ += _h_ * 31 + Double.valueOf(z).hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(instruct).append(",");
		_sb_.append(dungeonId).append(",");
		_sb_.append(x).append(",");
		_sb_.append(y).append(",");
		_sb_.append(z).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
