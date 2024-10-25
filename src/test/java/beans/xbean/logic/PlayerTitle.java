package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerTitle extends warrior.mdb.XBean {
    private int curTitleId; 
    private java.util.concurrent.CopyOnWriteArraySet<Integer> titles; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> titleCounter; 

    PlayerTitle(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.titles = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.titleCounter = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public PlayerTitle() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerTitle(PlayerTitle _o_) {
		this(_o_, null);
	}

	PlayerTitle(PlayerTitle _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.curTitleId = _o_.curTitleId;
		this.titles = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.titles.forEach(_v_ -> this.titles.add(_v_));
		this.titleCounter = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.titleCounter.forEach((_k_, _v_) -> this.titleCounter.put(_k_, _v_));
	}

	public void copyFrom(PlayerTitle _o_) {
        this.curTitleId = _o_.curTitleId;
        this.titles = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.titles.forEach(_v_ -> this.titles.add(_v_));
        this.titleCounter = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.titleCounter.forEach((_k_, _v_) -> this.titleCounter.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getCurTitleId(){
		return this.curTitleId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getTitles(){
		return this.titles;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTitleCounter(){
		return this.titleCounter;
	}

	@JSONField(name = "1")
	public void setCurTitleId(int _v_){
		this.curTitleId = _v_;
	}

	@JSONField(name = "2")
	public void setTitles(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.titles = _v_;
	}

	@JSONField(name = "3")
	public void setTitleCounter(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.titleCounter = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerTitle) {
			PlayerTitle _o_ = (PlayerTitle)_o1_;
			if (this.curTitleId != _o_.curTitleId) return false;
			if (!this.titles.equals(_o_.titles)) return false;
			if (!this.titleCounter.equals(_o_.titleCounter)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.curTitleId);
		_h_ += _h_ * 31 + this.titles.hashCode();
		_h_ += _h_ * 31 + this.titleCounter.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(curTitleId).append(",");
		_sb_.append(titles).append(",");
		_sb_.append(titleCounter).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
