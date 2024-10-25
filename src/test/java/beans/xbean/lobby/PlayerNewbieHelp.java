package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerNewbieHelp extends warrior.mdb.XBean {
    private boolean isNewbie; 
    private java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp> helpMe; 
    private java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp> helpOther; 
    private long lastSeekHelp;  // 上次发起求助的时间戳

    PlayerNewbieHelp(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.helpMe = new java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp>();
		this.helpOther = new java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp>();
	}

	public PlayerNewbieHelp() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerNewbieHelp(PlayerNewbieHelp _o_) {
		this(_o_, null);
	}

	PlayerNewbieHelp(PlayerNewbieHelp _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.isNewbie = _o_.isNewbie;
		this.helpMe = new java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp>();
		_o_.helpMe.forEach(_v_ -> this.helpMe.add(new NewbieQuestHelp(_v_)));
		this.helpOther = new java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp>();
		_o_.helpOther.forEach(_v_ -> this.helpOther.add(new NewbieQuestHelp(_v_)));
		this.lastSeekHelp = _o_.lastSeekHelp;
	}

	public void copyFrom(PlayerNewbieHelp _o_) {
        this.isNewbie = _o_.isNewbie;
        this.helpMe = new java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp>();
		_o_.helpMe.forEach(_v_ -> this.helpMe.add(new NewbieQuestHelp(_v_)));
        this.helpOther = new java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp>();
		_o_.helpOther.forEach(_v_ -> this.helpOther.add(new NewbieQuestHelp(_v_)));
        this.lastSeekHelp = _o_.lastSeekHelp;
	}


	@JSONField(name = "1")
	public boolean getIsNewbie(){
		return this.isNewbie;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp> getHelpMe(){
		return this.helpMe;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp> getHelpOther(){
		return this.helpOther;
	}

	@JSONField(name = "4")
	public long getLastSeekHelp(){
		return this.lastSeekHelp;
	}

	@JSONField(name = "1")
	public void setIsNewbie(boolean _v_){
		this.isNewbie = _v_;
	}

	@JSONField(name = "2")
	public void setHelpMe(java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp> _v_){
		this.helpMe = _v_;
	}

	@JSONField(name = "3")
	public void setHelpOther(java.util.concurrent.CopyOnWriteArrayList<NewbieQuestHelp> _v_){
		this.helpOther = _v_;
	}

	@JSONField(name = "4")
	public void setLastSeekHelp(long _v_){
		this.lastSeekHelp = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerNewbieHelp) {
			PlayerNewbieHelp _o_ = (PlayerNewbieHelp)_o1_;
			if (this.isNewbie != _o_.isNewbie) return false;
			if (!this.helpMe.equals(_o_.helpMe)) return false;
			if (!this.helpOther.equals(_o_.helpOther)) return false;
			if (this.lastSeekHelp != _o_.lastSeekHelp) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (this.isNewbie ? 1231 : 1237);
		_h_ += _h_ * 31 + this.helpMe.hashCode();
		_h_ += _h_ * 31 + this.helpOther.hashCode();
		_h_ += _h_ * 31 + (int)(lastSeekHelp ^ (lastSeekHelp >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(isNewbie).append(",");
		_sb_.append(helpMe).append(",");
		_sb_.append(helpOther).append(",");
		_sb_.append(lastSeekHelp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
