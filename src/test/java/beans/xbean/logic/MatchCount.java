package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class MatchCount extends warrior.mdb.XBean {
    private int id;  // id
    private int count;  // 次数
    private long lastRefresh;  // 上一次刷新时间
    private int resetCount;  // 今日奖励重置次数

    MatchCount(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public MatchCount() {
		this((warrior.mdb.XBean)null);
	}

	public MatchCount(MatchCount _o_) {
		this(_o_, null);
	}

	MatchCount(MatchCount _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.count = _o_.count;
		this.lastRefresh = _o_.lastRefresh;
		this.resetCount = _o_.resetCount;
	}

	public void copyFrom(MatchCount _o_) {
        this.id = _o_.id;
        this.count = _o_.count;
        this.lastRefresh = _o_.lastRefresh;
        this.resetCount = _o_.resetCount;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getCount(){
		return this.count;
	}

	@JSONField(name = "3")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "4")
	public int getResetCount(){
		return this.resetCount;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setCount(int _v_){
		this.count = _v_;
	}

	@JSONField(name = "3")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@JSONField(name = "4")
	public void setResetCount(int _v_){
		this.resetCount = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MatchCount) {
			MatchCount _o_ = (MatchCount)_o1_;
			if (this.id != _o_.id) return false;
			if (this.count != _o_.count) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			if (this.resetCount != _o_.resetCount) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.count);
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.resetCount);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(count).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(resetCount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
