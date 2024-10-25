package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class EnteringMap extends warrior.mdb.XBean {
    private int dungeonId;  // 副本Id
    private String pBEnterMapInfoBytes; 
    private String pBEnterMapInfoStr; 
    private String pbPBDungeonPlayerParamBytes; 
    private String pbPBDungeonPlayerParamStr; 

    EnteringMap(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.pBEnterMapInfoBytes = "";
		this.pBEnterMapInfoStr = "";
		this.pbPBDungeonPlayerParamBytes = "";
		this.pbPBDungeonPlayerParamStr = "";
	}

	public EnteringMap() {
		this((warrior.mdb.XBean)null);
	}

	public EnteringMap(EnteringMap _o_) {
		this(_o_, null);
	}

	EnteringMap(EnteringMap _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.dungeonId = _o_.dungeonId;
		this.pBEnterMapInfoBytes = _o_.pBEnterMapInfoBytes;
		this.pBEnterMapInfoStr = _o_.pBEnterMapInfoStr;
		this.pbPBDungeonPlayerParamBytes = _o_.pbPBDungeonPlayerParamBytes;
		this.pbPBDungeonPlayerParamStr = _o_.pbPBDungeonPlayerParamStr;
	}

	public void copyFrom(EnteringMap _o_) {
        this.dungeonId = _o_.dungeonId;
        this.pBEnterMapInfoBytes = _o_.pBEnterMapInfoBytes;
        this.pBEnterMapInfoStr = _o_.pBEnterMapInfoStr;
        this.pbPBDungeonPlayerParamBytes = _o_.pbPBDungeonPlayerParamBytes;
        this.pbPBDungeonPlayerParamStr = _o_.pbPBDungeonPlayerParamStr;
	}


	@JSONField(name = "1")
	public int getDungeonId(){
		return this.dungeonId;
	}

	@JSONField(name = "6")
	public String getPBEnterMapInfoBytes(){
		return this.pBEnterMapInfoBytes;
	}

	@JSONField(name = "7")
	public String getPBEnterMapInfoStr(){
		return this.pBEnterMapInfoStr;
	}

	@JSONField(name = "8")
	public String getPbPBDungeonPlayerParamBytes(){
		return this.pbPBDungeonPlayerParamBytes;
	}

	@JSONField(name = "9")
	public String getPbPBDungeonPlayerParamStr(){
		return this.pbPBDungeonPlayerParamStr;
	}

	@JSONField(name = "1")
	public void setDungeonId(int _v_){
		this.dungeonId = _v_;
	}

	@JSONField(name = "6")
	public void setPBEnterMapInfoBytes(String _v_){
		this.pBEnterMapInfoBytes = _v_;
	}

	@JSONField(name = "7")
	public void setPBEnterMapInfoStr(String _v_){
		this.pBEnterMapInfoStr = _v_;
	}

	@JSONField(name = "8")
	public void setPbPBDungeonPlayerParamBytes(String _v_){
		this.pbPBDungeonPlayerParamBytes = _v_;
	}

	@JSONField(name = "9")
	public void setPbPBDungeonPlayerParamStr(String _v_){
		this.pbPBDungeonPlayerParamStr = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EnteringMap) {
			EnteringMap _o_ = (EnteringMap)_o1_;
			if (this.dungeonId != _o_.dungeonId) return false;
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
		_h_ += _h_ * 31 + (int)(this.dungeonId);
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
		_sb_.append(dungeonId).append(",");
		_sb_.append("T").append(pBEnterMapInfoBytes.length()).append(",");
		_sb_.append("T").append(pBEnterMapInfoStr.length()).append(",");
		_sb_.append("T").append(pbPBDungeonPlayerParamBytes.length()).append(",");
		_sb_.append("T").append(pbPBDungeonPlayerParamStr.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
