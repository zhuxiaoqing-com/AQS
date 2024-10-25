package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerUpAward extends warrior.mdb.XBean {
    private xbean.logic.LGActLogicInfo actInfo;  // 活动数据
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> UpAwardItems;  // 心愿UP道具信息
    private int upAwardNums;  // 心愿UP抽奖次数
    private java.util.concurrent.CopyOnWriteArrayList<Integer> upAwardReceivedInfo;  // 心愿UP抽奖次数奖励领取信息 已领取的次数id列表
    private long dayRefresh;  // 日期更新时间
    private java.util.concurrent.CopyOnWriteArraySet<Integer> bannedItems;  // 当天被禁选的心愿道具列表

    PlayerUpAward(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.actInfo = new LGActLogicInfo(this);
		this.UpAwardItems = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.upAwardReceivedInfo = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		this.bannedItems = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public PlayerUpAward() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerUpAward(PlayerUpAward _o_) {
		this(_o_, null);
	}

	PlayerUpAward(PlayerUpAward _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
		this.UpAwardItems = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.UpAwardItems.forEach((_k_, _v_) -> this.UpAwardItems.put(_k_, _v_));
		this.upAwardNums = _o_.upAwardNums;
		this.upAwardReceivedInfo = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.upAwardReceivedInfo.forEach(_v_ -> this.upAwardReceivedInfo.add(_v_));
		this.dayRefresh = _o_.dayRefresh;
		this.bannedItems = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.bannedItems.forEach(_v_ -> this.bannedItems.add(_v_));
	}

	public void copyFrom(PlayerUpAward _o_) {
        this.actInfo = new LGActLogicInfo(_o_.actInfo, this);
        this.UpAwardItems = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.UpAwardItems.forEach((_k_, _v_) -> this.UpAwardItems.put(_k_, _v_));
        this.upAwardNums = _o_.upAwardNums;
        this.upAwardReceivedInfo = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.upAwardReceivedInfo.forEach(_v_ -> this.upAwardReceivedInfo.add(_v_));
        this.dayRefresh = _o_.dayRefresh;
        this.bannedItems = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.bannedItems.forEach(_v_ -> this.bannedItems.add(_v_));
	}


	@JSONField(name = "1")
	public xbean.logic.LGActLogicInfo getActInfo(){
		return this.actInfo;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getUpAwardItems(){
		return this.UpAwardItems;
	}

	@JSONField(name = "3")
	public int getUpAwardNums(){
		return this.upAwardNums;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getUpAwardReceivedInfo(){
		return this.upAwardReceivedInfo;
	}

	@JSONField(name = "5")
	public long getDayRefresh(){
		return this.dayRefresh;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getBannedItems(){
		return this.bannedItems;
	}

	@JSONField(name = "1")
	public void setActInfo(xbean.logic.LGActLogicInfo _v_){
		this.actInfo = _v_;
	}

	@JSONField(name = "2")
	public void setUpAwardItems(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.UpAwardItems = _v_;
	}

	@JSONField(name = "3")
	public void setUpAwardNums(int _v_){
		this.upAwardNums = _v_;
	}

	@JSONField(name = "4")
	public void setUpAwardReceivedInfo(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.upAwardReceivedInfo = _v_;
	}

	@JSONField(name = "5")
	public void setDayRefresh(long _v_){
		this.dayRefresh = _v_;
	}

	@JSONField(name = "6")
	public void setBannedItems(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.bannedItems = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerUpAward) {
			PlayerUpAward _o_ = (PlayerUpAward)_o1_;
			if (!this.actInfo.equals(_o_.actInfo)) return false;
			if (!this.UpAwardItems.equals(_o_.UpAwardItems)) return false;
			if (this.upAwardNums != _o_.upAwardNums) return false;
			if (!this.upAwardReceivedInfo.equals(_o_.upAwardReceivedInfo)) return false;
			if (this.dayRefresh != _o_.dayRefresh) return false;
			if (!this.bannedItems.equals(_o_.bannedItems)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.actInfo.hashCode();
		_h_ += _h_ * 31 + this.UpAwardItems.hashCode();
		_h_ += _h_ * 31 + (int)(this.upAwardNums);
		_h_ += _h_ * 31 + this.upAwardReceivedInfo.hashCode();
		_h_ += _h_ * 31 + (int)(dayRefresh ^ (dayRefresh >>> 32));
		_h_ += _h_ * 31 + this.bannedItems.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(actInfo).append(",");
		_sb_.append(UpAwardItems).append(",");
		_sb_.append(upAwardNums).append(",");
		_sb_.append(upAwardReceivedInfo).append(",");
		_sb_.append(dayRefresh).append(",");
		_sb_.append(bannedItems).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
