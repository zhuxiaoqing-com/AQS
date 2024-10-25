package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class NewbieQuestHelp extends warrior.mdb.XBean {
    private long helper; 
    private int questId; 
    private boolean finish; 

    NewbieQuestHelp(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public NewbieQuestHelp() {
		this((warrior.mdb.XBean)null);
	}

	public NewbieQuestHelp(NewbieQuestHelp _o_) {
		this(_o_, null);
	}

	NewbieQuestHelp(NewbieQuestHelp _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.helper = _o_.helper;
		this.questId = _o_.questId;
		this.finish = _o_.finish;
	}

	public void copyFrom(NewbieQuestHelp _o_) {
        this.helper = _o_.helper;
        this.questId = _o_.questId;
        this.finish = _o_.finish;
	}


	@JSONField(name = "1")
	public long getHelper(){
		return this.helper;
	}

	@JSONField(name = "2")
	public int getQuestId(){
		return this.questId;
	}

	@JSONField(name = "3")
	public boolean getFinish(){
		return this.finish;
	}

	@JSONField(name = "1")
	public void setHelper(long _v_){
		this.helper = _v_;
	}

	@JSONField(name = "2")
	public void setQuestId(int _v_){
		this.questId = _v_;
	}

	@JSONField(name = "3")
	public void setFinish(boolean _v_){
		this.finish = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof NewbieQuestHelp) {
			NewbieQuestHelp _o_ = (NewbieQuestHelp)_o1_;
			if (this.helper != _o_.helper) return false;
			if (this.questId != _o_.questId) return false;
			if (this.finish != _o_.finish) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(helper ^ (helper >>> 32));
		_h_ += _h_ * 31 + (int)(this.questId);
		_h_ += _h_ * 31 + (this.finish ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(helper).append(",");
		_sb_.append(questId).append(",");
		_sb_.append(finish).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
