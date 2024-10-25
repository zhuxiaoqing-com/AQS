package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class GiftLog extends warrior.mdb.XBean {
    private long time;  // 抽取时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> itemMap;  // 抽取奖励

    GiftLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.itemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public GiftLog() {
		this((warrior.mdb.XBean)null);
	}

	public GiftLog(GiftLog _o_) {
		this(_o_, null);
	}

	GiftLog(GiftLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.time = _o_.time;
		this.itemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.itemMap.forEach((_k_, _v_) -> this.itemMap.put(_k_, _v_));
	}

	public void copyFrom(GiftLog _o_) {
        this.time = _o_.time;
        this.itemMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.itemMap.forEach((_k_, _v_) -> this.itemMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getItemMap(){
		return this.itemMap;
	}

	@JSONField(name = "1")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@JSONField(name = "2")
	public void setItemMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.itemMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GiftLog) {
			GiftLog _o_ = (GiftLog)_o1_;
			if (this.time != _o_.time) return false;
			if (!this.itemMap.equals(_o_.itemMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		_h_ += _h_ * 31 + this.itemMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(time).append(",");
		_sb_.append(itemMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
