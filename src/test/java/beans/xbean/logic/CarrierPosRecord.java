package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class CarrierPosRecord extends warrior.mdb.XBean {
    private int x;  // x
    private int z;  // z
    private int dirX;  // dirX
    private int dirZ;  // dirZ
    private int dungeonId;  // 副本id
    private String pbPBEnterMapInfoBytes;  // 进入地图数据Json

    CarrierPosRecord(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.pbPBEnterMapInfoBytes = "";
	}

	public CarrierPosRecord() {
		this((warrior.mdb.XBean)null);
	}

	public CarrierPosRecord(CarrierPosRecord _o_) {
		this(_o_, null);
	}

	CarrierPosRecord(CarrierPosRecord _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.x = _o_.x;
		this.z = _o_.z;
		this.dirX = _o_.dirX;
		this.dirZ = _o_.dirZ;
		this.dungeonId = _o_.dungeonId;
		this.pbPBEnterMapInfoBytes = _o_.pbPBEnterMapInfoBytes;
	}

	public void copyFrom(CarrierPosRecord _o_) {
        this.x = _o_.x;
        this.z = _o_.z;
        this.dirX = _o_.dirX;
        this.dirZ = _o_.dirZ;
        this.dungeonId = _o_.dungeonId;
        this.pbPBEnterMapInfoBytes = _o_.pbPBEnterMapInfoBytes;
	}


	@JSONField(name = "2")
	public int getX(){
		return this.x;
	}

	@JSONField(name = "3")
	public int getZ(){
		return this.z;
	}

	@JSONField(name = "4")
	public int getDirX(){
		return this.dirX;
	}

	@JSONField(name = "5")
	public int getDirZ(){
		return this.dirZ;
	}

	@JSONField(name = "6")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "8")
	public String getPbPBEnterMapInfoBytes(){
		return this.pbPBEnterMapInfoBytes;
	}

	@JSONField(name = "2")
	public void setX(int _v_){
		this.x = _v_;
	}

	@JSONField(name = "3")
	public void setZ(int _v_){
		this.z = _v_;
	}

	@JSONField(name = "4")
	public void setDirX(int _v_){
		this.dirX = _v_;
	}

	@JSONField(name = "5")
	public void setDirZ(int _v_){
		this.dirZ = _v_;
	}

	@JSONField(name = "6")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "8")
	public void setPbPBEnterMapInfoBytes(String _v_){
		this.pbPBEnterMapInfoBytes = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CarrierPosRecord) {
			CarrierPosRecord _o_ = (CarrierPosRecord)_o1_;
			if (this.x != _o_.x) return false;
			if (this.z != _o_.z) return false;
			if (this.dirX != _o_.dirX) return false;
			if (this.dirZ != _o_.dirZ) return false;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (!this.pbPBEnterMapInfoBytes.equals(_o_.pbPBEnterMapInfoBytes)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.x);
		_h_ += _h_ * 31 + (int)(this.z);
		_h_ += _h_ * 31 + (int)(this.dirX);
		_h_ += _h_ * 31 + (int)(this.dirZ);
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + this.pbPBEnterMapInfoBytes.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(x).append(",");
		_sb_.append(z).append(",");
		_sb_.append(dirX).append(",");
		_sb_.append(dirZ).append(",");
		_sb_.append(dungeonId).append(",");
		_sb_.append("T").append(pbPBEnterMapInfoBytes.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
