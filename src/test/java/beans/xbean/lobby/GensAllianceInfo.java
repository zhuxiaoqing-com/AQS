package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensAllianceInfo extends warrior.mdb.XBean {
    private long allianceId;  // 联盟id
    private java.util.concurrent.ConcurrentHashMap<Long, AllianceInvite> allianceInvite;  // 收到的入盟邀请 k:allianceId
    private java.util.concurrent.ConcurrentHashMap<Long, AllianceForm> allianceForm;  // 组建联盟邀请  k:gensId
    private long allianceCD;  // 下次能加入联盟的时间戳

    GensAllianceInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.allianceInvite = new java.util.concurrent.ConcurrentHashMap<Long, AllianceInvite>();
		this.allianceForm = new java.util.concurrent.ConcurrentHashMap<Long, AllianceForm>();
	}

	public GensAllianceInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensAllianceInfo(GensAllianceInfo _o_) {
		this(_o_, null);
	}

	GensAllianceInfo(GensAllianceInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.allianceId = _o_.allianceId;
		this.allianceInvite = new java.util.concurrent.ConcurrentHashMap<Long, AllianceInvite>();
		_o_.allianceInvite.forEach((_k_, _v_) -> this.allianceInvite.put(_k_, new AllianceInvite(_v_)));
		this.allianceForm = new java.util.concurrent.ConcurrentHashMap<Long, AllianceForm>();
		_o_.allianceForm.forEach((_k_, _v_) -> this.allianceForm.put(_k_, new AllianceForm(_v_)));
		this.allianceCD = _o_.allianceCD;
	}

	public void copyFrom(GensAllianceInfo _o_) {
        this.allianceId = _o_.allianceId;
        this.allianceInvite = new java.util.concurrent.ConcurrentHashMap<Long, AllianceInvite>();
		_o_.allianceInvite.forEach((_k_, _v_) -> this.allianceInvite.put(_k_, new AllianceInvite(_v_)));
        this.allianceForm = new java.util.concurrent.ConcurrentHashMap<Long, AllianceForm>();
		_o_.allianceForm.forEach((_k_, _v_) -> this.allianceForm.put(_k_, new AllianceForm(_v_)));
        this.allianceCD = _o_.allianceCD;
	}


	@JSONField(name = "1")
	public long getAllianceId(){
		return this.allianceId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, AllianceInvite> getAllianceInvite(){
		return this.allianceInvite;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Long, AllianceForm> getAllianceForm(){
		return this.allianceForm;
	}

	@JSONField(name = "4")
	public long getAllianceCD(){
		return this.allianceCD;
	}

	@JSONField(name = "1")
	public void setAllianceId(long _v_){
		this.allianceId = _v_;
	}

	@JSONField(name = "2")
	public void setAllianceInvite(java.util.concurrent.ConcurrentHashMap<Long, AllianceInvite> _v_){
		this.allianceInvite = _v_;
	}

	@JSONField(name = "3")
	public void setAllianceForm(java.util.concurrent.ConcurrentHashMap<Long, AllianceForm> _v_){
		this.allianceForm = _v_;
	}

	@JSONField(name = "4")
	public void setAllianceCD(long _v_){
		this.allianceCD = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensAllianceInfo) {
			GensAllianceInfo _o_ = (GensAllianceInfo)_o1_;
			if (this.allianceId != _o_.allianceId) return false;
			if (!this.allianceInvite.equals(_o_.allianceInvite)) return false;
			if (!this.allianceForm.equals(_o_.allianceForm)) return false;
			if (this.allianceCD != _o_.allianceCD) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(allianceId ^ (allianceId >>> 32));
		_h_ += _h_ * 31 + this.allianceInvite.hashCode();
		_h_ += _h_ * 31 + this.allianceForm.hashCode();
		_h_ += _h_ * 31 + (int)(allianceCD ^ (allianceCD >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(allianceId).append(",");
		_sb_.append(allianceInvite).append(",");
		_sb_.append(allianceForm).append(",");
		_sb_.append(allianceCD).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
