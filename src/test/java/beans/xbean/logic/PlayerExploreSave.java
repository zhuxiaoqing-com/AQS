package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerExploreSave extends warrior.mdb.XBean {
    private xbean.logic.PlayerDungeonMirror mirror;  // 副本镜像
    private boolean finish;  // 是否完成
    private xbean.logic.LGActLogicInfo actInfo;  // 活动数据
    private int boxNum;  // 开箱数量
    private boolean sendAward;  // 奖励是否发送

    PlayerExploreSave(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.mirror = new PlayerDungeonMirror(this);
		this.actInfo = new LGActLogicInfo(this);
	}

	public PlayerExploreSave() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerExploreSave(PlayerExploreSave _o_) {
		this(_o_, null);
	}

	PlayerExploreSave(PlayerExploreSave _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.mirror = new PlayerDungeonMirror(_o_.mirror, this);
		this.finish = _o_.finish;
		this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
		this.boxNum = _o_.boxNum;
		this.sendAward = _o_.sendAward;
	}

	public void copyFrom(PlayerExploreSave _o_) {
        this.mirror = new PlayerDungeonMirror(_o_.mirror, this);
        this.finish = _o_.finish;
        this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
        this.boxNum = _o_.boxNum;
        this.sendAward = _o_.sendAward;
	}


	@JSONField(name = "1")
	public xbean.logic.PlayerDungeonMirror getMirror(){
		return this.mirror;
	}

	@JSONField(name = "2")
	public boolean getFinish(){
		return this.finish;
	}

	@JSONField(name = "4")
	public xbean.logic.LGActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "5")
	public int getBoxNum(){
		return this.boxNum;
	}

	@JSONField(name = "6")
	public boolean getSendAward(){
		return this.sendAward;
	}

	@JSONField(name = "1")
	public void setMirror(xbean.logic.PlayerDungeonMirror _v_){
		this.mirror = _v_;
	}

	@JSONField(name = "2")
	public void setFinish(boolean _v_){
		this.finish = _v_;
	}

	@JSONField(name = "4")
	public void setActInfo(xbean.logic.LGActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@JSONField(name = "5")
	public void setBoxNum(int _v_){
		this.boxNum = _v_;
	}

	@JSONField(name = "6")
	public void setSendAward(boolean _v_){
		this.sendAward = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerExploreSave) {
			PlayerExploreSave _o_ = (PlayerExploreSave)_o1_;
			if (!this.mirror.equals(_o_.mirror)) return false;
			if (this.finish != _o_.finish) return false;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			if (this.boxNum != _o_.boxNum) return false;
			if (this.sendAward != _o_.sendAward) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.mirror.hashCode();
		_h_ += _h_ * 31 + (this.finish ? 1231 : 1237);
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		_h_ += _h_ * 31 + (int)(this.boxNum);
		_h_ += _h_ * 31 + (this.sendAward ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(mirror).append(",");
		_sb_.append(finish).append(",");
		_sb_.append(actInfo).append(",");
		_sb_.append(boxNum).append(",");
		_sb_.append(sendAward).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
