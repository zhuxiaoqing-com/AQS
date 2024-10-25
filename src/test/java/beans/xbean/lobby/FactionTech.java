package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionTech extends warrior.mdb.XBean {
    private int id;  // id
    private int progress;  // 进度值
    private int level;  // 等级
    private int dailyDonate;  // 今日投资人数
    private int dailyOfferProgress; 
    private java.util.concurrent.CopyOnWriteArraySet<Long> dailyMemberCount; 

    FactionTech(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dailyMemberCount = new java.util.concurrent.CopyOnWriteArraySet<Long>();
	}

	public FactionTech() {
		this((warrior.mdb.XBean)null);
	}

	public FactionTech(FactionTech _o_) {
		this(_o_, null);
	}

	FactionTech(FactionTech _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.progress = _o_.progress;
		this.level = _o_.level;
		this.dailyDonate = _o_.dailyDonate;
		this.dailyOfferProgress = _o_.dailyOfferProgress;
		this.dailyMemberCount = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.dailyMemberCount.forEach(_v_ -> this.dailyMemberCount.add(_v_));
	}

	public void copyFrom(FactionTech _o_) {
        this.id = _o_.id;
        this.progress = _o_.progress;
        this.level = _o_.level;
        this.dailyDonate = _o_.dailyDonate;
        this.dailyOfferProgress = _o_.dailyOfferProgress;
        this.dailyMemberCount = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.dailyMemberCount.forEach(_v_ -> this.dailyMemberCount.add(_v_));
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getProgress(){
		return this.progress;
	}

	@JSONField(name = "3")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "4")
	public int getDailyDonate(){
		return this.dailyDonate;
	}

	@JSONField(name = "5")
	public int getDailyOfferProgress(){
		return this.dailyOfferProgress;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getDailyMemberCount(){
		return this.dailyMemberCount;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setProgress(int _v_){
		this.progress = _v_;
	}

	@JSONField(name = "3")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "4")
	public void setDailyDonate(int _v_){
		this.dailyDonate = _v_;
	}

	@JSONField(name = "5")
	public void setDailyOfferProgress(int _v_){
		this.dailyOfferProgress = _v_;
	}

	@JSONField(name = "6")
	public void setDailyMemberCount(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.dailyMemberCount = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionTech) {
			FactionTech _o_ = (FactionTech)_o1_;
			if (this.id != _o_.id) return false;
			if (this.progress != _o_.progress) return false;
			if (this.level != _o_.level) return false;
			if (this.dailyDonate != _o_.dailyDonate) return false;
			if (this.dailyOfferProgress != _o_.dailyOfferProgress) return false;
			if (!this.dailyMemberCount.equals(_o_.dailyMemberCount)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.progress);
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + (int)(this.dailyDonate);
		_h_ += _h_ * 31 + (int)(this.dailyOfferProgress);
		_h_ += _h_ * 31 + this.dailyMemberCount.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(progress).append(",");
		_sb_.append(level).append(",");
		_sb_.append(dailyDonate).append(",");
		_sb_.append(dailyOfferProgress).append(",");
		_sb_.append(dailyMemberCount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
