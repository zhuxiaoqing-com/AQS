package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class TalentReceiveRemain extends warrior.mdb.XBean {
    private int cfgId;  // 免费领取分组表id
    private java.util.concurrent.CopyOnWriteArraySet<Integer> remainItems;  // 上次领取该套装中未领取成功的道具

    TalentReceiveRemain(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.remainItems = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public TalentReceiveRemain() {
		this((warrior.mdb.XBean)null);
	}

	public TalentReceiveRemain(TalentReceiveRemain _o_) {
		this(_o_, null);
	}

	TalentReceiveRemain(TalentReceiveRemain _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.cfgId = _o_.cfgId;
		this.remainItems = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.remainItems.forEach(_v_ -> this.remainItems.add(_v_));
	}

	public void copyFrom(TalentReceiveRemain _o_) {
        this.cfgId = _o_.cfgId;
        this.remainItems = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.remainItems.forEach(_v_ -> this.remainItems.add(_v_));
	}


	@JSONField(name = "1")
	public int getCfgId(){
		return this.cfgId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getRemainItems(){
		return this.remainItems;
	}

	@JSONField(name = "1")
	public void setCfgId(int _v_){
		this.cfgId = _v_;
	}

	@JSONField(name = "2")
	public void setRemainItems(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.remainItems = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TalentReceiveRemain) {
			TalentReceiveRemain _o_ = (TalentReceiveRemain)_o1_;
			if (this.cfgId != _o_.cfgId) return false;
			if (!this.remainItems.equals(_o_.remainItems)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.cfgId);
		_h_ += _h_ * 31 + this.remainItems.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(cfgId).append(",");
		_sb_.append(remainItems).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
