package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GentesQuestInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, GentesQuest> quest; 
    private long gensId; 

    GentesQuestInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.quest = new java.util.concurrent.ConcurrentHashMap<Integer, GentesQuest>();
	}

	public GentesQuestInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GentesQuestInfo(GentesQuestInfo _o_) {
		this(_o_, null);
	}

	GentesQuestInfo(GentesQuestInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.quest = new java.util.concurrent.ConcurrentHashMap<Integer, GentesQuest>();
		_o_.quest.forEach((_k_, _v_) -> this.quest.put(_k_, new GentesQuest(_v_)));
		this.gensId = _o_.gensId;
	}

	public void copyFrom(GentesQuestInfo _o_) {
        this.quest = new java.util.concurrent.ConcurrentHashMap<Integer, GentesQuest>();
		_o_.quest.forEach((_k_, _v_) -> this.quest.put(_k_, new GentesQuest(_v_)));
        this.gensId = _o_.gensId;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, GentesQuest> getQuest(){
		return this.quest;
	}

	@JSONField(name = "2")
	public long getGensId(){
		return this.gensId;
	}

	@JSONField(name = "1")
	public void setQuest(java.util.concurrent.ConcurrentHashMap<Integer, GentesQuest> _v_){
		this.quest = _v_;
	}

	@JSONField(name = "2")
	public void setGensId(long _v_){
		this.gensId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GentesQuestInfo) {
			GentesQuestInfo _o_ = (GentesQuestInfo)_o1_;
			if (!this.quest.equals(_o_.quest)) return false;
			if (this.gensId != _o_.gensId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.quest.hashCode();
		_h_ += _h_ * 31 + (int)(gensId ^ (gensId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(quest).append(",");
		_sb_.append(gensId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
