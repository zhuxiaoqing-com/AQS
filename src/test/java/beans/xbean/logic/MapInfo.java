package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class MapInfo extends warrior.mdb.XBean {
    private int area;  // 区域id
    private int x;  // x
    private int y;  // y
    private int z;  // z
    private int dungeonId;  // 副本Id
    private int battleServerId;  // bt服务器id
    private String pBEnterMapInfoBytes; 
    private String pBEnterMapInfoStr; 
    private String pbPBDungeonPlayerParamBytes; 
    private String pbPBDungeonPlayerParamStr; 

    MapInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.pBEnterMapInfoBytes = "";
		this.pBEnterMapInfoStr = "";
		this.pbPBDungeonPlayerParamBytes = "";
		this.pbPBDungeonPlayerParamStr = "";
	}

	public MapInfo() {
		this((warrior.mdb.XBean)null);
	}

	public MapInfo(MapInfo _o_) {
		this(_o_, null);
	}

	MapInfo(MapInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.area = _o_.area;
		this.x = _o_.x;
		this.y = _o_.y;
		this.z = _o_.z;
		this.dungeonId = _o_.dungeonId;
		this.battleServerId = _o_.battleServerId;
		this.pBEnterMapInfoBytes = _o_.pBEnterMapInfoBytes;
		this.pBEnterMapInfoStr = _o_.pBEnterMapInfoStr;
		this.pbPBDungeonPlayerParamBytes = _o_.pbPBDungeonPlayerParamBytes;
		this.pbPBDungeonPlayerParamStr = _o_.pbPBDungeonPlayerParamStr;
	}

	public void copyFrom(MapInfo _o_) {
        this.area = _o_.area;
        this.x = _o_.x;
        this.y = _o_.y;
        this.z = _o_.z;
        this.dungeonId = _o_.dungeonId;
        this.battleServerId = _o_.battleServerId;
        this.pBEnterMapInfoBytes = _o_.pBEnterMapInfoBytes;
        this.pBEnterMapInfoStr = _o_.pBEnterMapInfoStr;
        this.pbPBDungeonPlayerParamBytes = _o_.pbPBDungeonPlayerParamBytes;
        this.pbPBDungeonPlayerParamStr = _o_.pbPBDungeonPlayerParamStr;
	}


	@JSONField(name = "1")
	public int getArea(){
		return this.area;
	}

	@JSONField(name = "2")
	public int getX(){
		return this.x;
	}

	@JSONField(name = "3")
	public int getY(){
		return this.y;
	}

	@JSONField(name = "4")
	public int getZ(){
		return this.z;
	}

	@JSONField(name = "5")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "7")
	public int getBattleServerId(){
		return this.battleServerId;
	}

	@JSONField(name = "8")
	public String getPBEnterMapInfoBytes(){
		return this.pBEnterMapInfoBytes;
	}

	@JSONField(name = "9")
	public String getPBEnterMapInfoStr(){
		return this.pBEnterMapInfoStr;
	}

	@JSONField(name = "10")
	public String getPbPBDungeonPlayerParamBytes(){
		return this.pbPBDungeonPlayerParamBytes;
	}

	@JSONField(name = "11")
	public String getPbPBDungeonPlayerParamStr(){
		return this.pbPBDungeonPlayerParamStr;
	}

	@JSONField(name = "1")
	public void setArea(int _v_){
		this.area = _v_;
	}

	@JSONField(name = "2")
	public void setX(int _v_){
		this.x = _v_;
	}

	@JSONField(name = "3")
	public void setY(int _v_){
		this.y = _v_;
	}

	@JSONField(name = "4")
	public void setZ(int _v_){
		this.z = _v_;
	}

	@JSONField(name = "5")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "7")
	public void setBattleServerId(int _v_){
		this.battleServerId = _v_;
	}

	@JSONField(name = "8")
	public void setPBEnterMapInfoBytes(String _v_){
		this.pBEnterMapInfoBytes = _v_;
	}

	@JSONField(name = "9")
	public void setPBEnterMapInfoStr(String _v_){
		this.pBEnterMapInfoStr = _v_;
	}

	@JSONField(name = "10")
	public void setPbPBDungeonPlayerParamBytes(String _v_){
		this.pbPBDungeonPlayerParamBytes = _v_;
	}

	@JSONField(name = "11")
	public void setPbPBDungeonPlayerParamStr(String _v_){
		this.pbPBDungeonPlayerParamStr = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MapInfo) {
			MapInfo _o_ = (MapInfo)_o1_;
			if (this.area != _o_.area) return false;
			if (this.x != _o_.x) return false;
			if (this.y != _o_.y) return false;
			if (this.z != _o_.z) return false;
			if (this.dungeonId != _o_.dungeonId) return false;
			if (this.battleServerId != _o_.battleServerId) return false;
			if (!this.pBEnterMapInfoBytes.equals(_o_.pBEnterMapInfoBytes)) return false;
			if (!this.pBEnterMapInfoStr.equals(_o_.pBEnterMapInfoStr)) return false;
			if (!this.pbPBDungeonPlayerParamBytes.equals(_o_.pbPBDungeonPlayerParamBytes)) return false;
			if (!this.pbPBDungeonPlayerParamStr.equals(_o_.pbPBDungeonPlayerParamStr)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.area);
		_h_ += _h_ * 31 + (int)(this.x);
		_h_ += _h_ * 31 + (int)(this.y);
		_h_ += _h_ * 31 + (int)(this.z);
		_h_ += _h_ * 31 + (int)(this.dungeonId);
		_h_ += _h_ * 31 + (int)(this.battleServerId);
		_h_ += _h_ * 31 + this.pBEnterMapInfoBytes.hashCode();
		_h_ += _h_ * 31 + this.pBEnterMapInfoStr.hashCode();
		_h_ += _h_ * 31 + this.pbPBDungeonPlayerParamBytes.hashCode();
		_h_ += _h_ * 31 + this.pbPBDungeonPlayerParamStr.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(area).append(",");
		_sb_.append(x).append(",");
		_sb_.append(y).append(",");
		_sb_.append(z).append(",");
		_sb_.append(dungeonId).append(",");
		_sb_.append(battleServerId).append(",");
		_sb_.append("T").append(pBEnterMapInfoBytes.length()).append(",");
		_sb_.append("T").append(pBEnterMapInfoStr.length()).append(",");
		_sb_.append("T").append(pbPBDungeonPlayerParamBytes.length()).append(",");
		_sb_.append("T").append(pbPBDungeonPlayerParamStr.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
