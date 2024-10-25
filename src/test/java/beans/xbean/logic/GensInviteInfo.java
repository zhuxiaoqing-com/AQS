package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensInviteInfo extends warrior.mdb.XBean {
    private long gensId; 
    private String gName; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> icon; 
    private long sendTime; 

    GensInviteInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gName = "";
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GensInviteInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensInviteInfo(GensInviteInfo _o_) {
		this(_o_, null);
	}

	GensInviteInfo(GensInviteInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gensId = _o_.gensId;
		this.gName = _o_.gName;
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
		this.sendTime = _o_.sendTime;
	}

	public void copyFrom(GensInviteInfo _o_) {
        this.gensId = _o_.gensId;
        this.gName = _o_.gName;
        this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
        this.sendTime = _o_.sendTime;
	}


	@JSONField(name = "1")
	public long getGensId(){
		return this.gensId;
	}

	@JSONField(name = "2")
	public String getGName(){
		return this.gName;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getIcon(){
		return this.icon;
	}

	@JSONField(name = "4")
	public long getSendTime(){
		return this.sendTime;
	}

	@JSONField(name = "1")
	public void setGensId(long _v_){
		this.gensId = _v_;
	}

	@JSONField(name = "2")
	public void setGName(String _v_){
		this.gName = _v_;
	}

	@JSONField(name = "3")
	public void setIcon(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.icon = _v_;
	}

	@JSONField(name = "4")
	public void setSendTime(long _v_){
		this.sendTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensInviteInfo) {
			GensInviteInfo _o_ = (GensInviteInfo)_o1_;
			if (this.gensId != _o_.gensId) return false;
			if (!this.gName.equals(_o_.gName)) return false;
			if (!this.icon.equals(_o_.icon)) return false;
			if (this.sendTime != _o_.sendTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gensId ^ (gensId >>> 32));
		_h_ += _h_ * 31 + this.gName.hashCode();
		_h_ += _h_ * 31 + this.icon.hashCode();
		_h_ += _h_ * 31 + (int)(sendTime ^ (sendTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gensId).append(",");
		_sb_.append("T").append(gName.length()).append(",");
		_sb_.append(icon).append(",");
		_sb_.append(sendTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
