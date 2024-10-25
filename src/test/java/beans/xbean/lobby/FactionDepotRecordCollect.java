package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class FactionDepotRecordCollect extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap> itemBriefInfoDayList;  // 分天的阵营仓库道具简略infoList
    private java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap> resBriefInfoDayList;  // 分天的阵营仓库资源简略infoList
    private long lastRefresh;  // 上次刷新时间

    FactionDepotRecordCollect(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.itemBriefInfoDayList = new java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap>();
		this.resBriefInfoDayList = new java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap>();
	}

	public FactionDepotRecordCollect() {
		this((warrior.mdb.XBean)null);
	}

	public FactionDepotRecordCollect(FactionDepotRecordCollect _o_) {
		this(_o_, null);
	}

	FactionDepotRecordCollect(FactionDepotRecordCollect _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.itemBriefInfoDayList = new java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap>();
		_o_.itemBriefInfoDayList.forEach(_v_ -> this.itemBriefInfoDayList.add(new ItemBriefInfoMap(_v_)));
		this.resBriefInfoDayList = new java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap>();
		_o_.resBriefInfoDayList.forEach(_v_ -> this.resBriefInfoDayList.add(new ItemBriefInfoMap(_v_)));
		this.lastRefresh = _o_.lastRefresh;
	}

	public void copyFrom(FactionDepotRecordCollect _o_) {
        this.itemBriefInfoDayList = new java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap>();
		_o_.itemBriefInfoDayList.forEach(_v_ -> this.itemBriefInfoDayList.add(new ItemBriefInfoMap(_v_)));
        this.resBriefInfoDayList = new java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap>();
		_o_.resBriefInfoDayList.forEach(_v_ -> this.resBriefInfoDayList.add(new ItemBriefInfoMap(_v_)));
        this.lastRefresh = _o_.lastRefresh;
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap> getItemBriefInfoDayList(){
		return this.itemBriefInfoDayList;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap> getResBriefInfoDayList(){
		return this.resBriefInfoDayList;
	}

	@JSONField(name = "3")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "1")
	public void setItemBriefInfoDayList(java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap> _v_){
		this.itemBriefInfoDayList = _v_;
	}

	@JSONField(name = "2")
	public void setResBriefInfoDayList(java.util.concurrent.CopyOnWriteArrayList<ItemBriefInfoMap> _v_){
		this.resBriefInfoDayList = _v_;
	}

	@JSONField(name = "3")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FactionDepotRecordCollect) {
			FactionDepotRecordCollect _o_ = (FactionDepotRecordCollect)_o1_;
			if (!this.itemBriefInfoDayList.equals(_o_.itemBriefInfoDayList)) return false;
			if (!this.resBriefInfoDayList.equals(_o_.resBriefInfoDayList)) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.itemBriefInfoDayList.hashCode();
		_h_ += _h_ * 31 + this.resBriefInfoDayList.hashCode();
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(itemBriefInfoDayList).append(",");
		_sb_.append(resBriefInfoDayList).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
