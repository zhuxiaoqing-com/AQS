package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBBattleInfo extends warrior.mdb.XBean {
    private String pBPVPBattleAwardWrapBytes; 
    private String pBPVPBattleAwardWrapStr; 

    LBBattleInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.pBPVPBattleAwardWrapBytes = "";
		this.pBPVPBattleAwardWrapStr = "";
	}

	public LBBattleInfo() {
		this((warrior.mdb.XBean)null);
	}

	public LBBattleInfo(LBBattleInfo _o_) {
		this(_o_, null);
	}

	LBBattleInfo(LBBattleInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.pBPVPBattleAwardWrapBytes = _o_.pBPVPBattleAwardWrapBytes;
		this.pBPVPBattleAwardWrapStr = _o_.pBPVPBattleAwardWrapStr;
	}

	public void copyFrom(LBBattleInfo _o_) {
        this.pBPVPBattleAwardWrapBytes = _o_.pBPVPBattleAwardWrapBytes;
        this.pBPVPBattleAwardWrapStr = _o_.pBPVPBattleAwardWrapStr;
	}


	@JSONField(name = "1")
	public String getPBPVPBattleAwardWrapBytes(){
		return this.pBPVPBattleAwardWrapBytes;
	}

	@JSONField(name = "2")
	public String getPBPVPBattleAwardWrapStr(){
		return this.pBPVPBattleAwardWrapStr;
	}

	@JSONField(name = "1")
	public void setPBPVPBattleAwardWrapBytes(String _v_){
		this.pBPVPBattleAwardWrapBytes = _v_;
	}

	@JSONField(name = "2")
	public void setPBPVPBattleAwardWrapStr(String _v_){
		this.pBPVPBattleAwardWrapStr = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBBattleInfo) {
			LBBattleInfo _o_ = (LBBattleInfo)_o1_;
			if (!this.pBPVPBattleAwardWrapBytes.equals(_o_.pBPVPBattleAwardWrapBytes)) return false;
			if (!this.pBPVPBattleAwardWrapStr.equals(_o_.pBPVPBattleAwardWrapStr)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.pBPVPBattleAwardWrapBytes.hashCode();
		_h_ += _h_ * 31 + this.pBPVPBattleAwardWrapStr.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append("T").append(pBPVPBattleAwardWrapBytes.length()).append(",");
		_sb_.append("T").append(pBPVPBattleAwardWrapStr.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
