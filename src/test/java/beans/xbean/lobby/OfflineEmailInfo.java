package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class OfflineEmailInfo extends warrior.mdb.XBean {
    private int emid;  // 邮件模板ID
    private java.util.concurrent.ConcurrentHashMap<String, String> aparams;  // 掉落奖励填充参数集合
    private java.util.concurrent.CopyOnWriteArrayList<String> tparams;  // 标题填充集合
    private java.util.concurrent.CopyOnWriteArrayList<String> cParams;  // 内容填充集合
    private java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean> itemInfo;  // 邮件自定义奖励道具
    private java.util.concurrent.CopyOnWriteArrayList<Integer> dropId;  // 掉落Id
    private long sendTime;  // 发送时间
    private long delTime;  // 删除时间
    private long emailSrl;  // 邮件唯一id
    private java.util.concurrent.CopyOnWriteArrayList<LBItem> items;  // items

    OfflineEmailInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.aparams = new java.util.concurrent.ConcurrentHashMap<String, String>();
		this.tparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		this.cParams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		this.itemInfo = new java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean>();
		this.dropId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		this.items = new java.util.concurrent.CopyOnWriteArrayList<LBItem>();
	}

	public OfflineEmailInfo() {
		this((warrior.mdb.XBean)null);
	}

	public OfflineEmailInfo(OfflineEmailInfo _o_) {
		this(_o_, null);
	}

	OfflineEmailInfo(OfflineEmailInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.emid = _o_.emid;
		this.aparams = new java.util.concurrent.ConcurrentHashMap<String, String>();
		_o_.aparams.forEach((_k_, _v_) -> this.aparams.put(_k_, _v_));
		this.tparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.tparams.forEach(_v_ -> this.tparams.add(_v_));
		this.cParams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.cParams.forEach(_v_ -> this.cParams.add(_v_));
		this.itemInfo = new java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean>();
		_o_.itemInfo.forEach(_v_ -> this.itemInfo.add(new LBItemInfoBean(_v_)));
		this.dropId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.dropId.forEach(_v_ -> this.dropId.add(_v_));
		this.sendTime = _o_.sendTime;
		this.delTime = _o_.delTime;
		this.emailSrl = _o_.emailSrl;
		this.items = new java.util.concurrent.CopyOnWriteArrayList<LBItem>();
		_o_.items.forEach(_v_ -> this.items.add(new LBItem(_v_)));
	}

	public void copyFrom(OfflineEmailInfo _o_) {
        this.emid = _o_.emid;
        this.aparams = new java.util.concurrent.ConcurrentHashMap<String, String>();
		_o_.aparams.forEach((_k_, _v_) -> this.aparams.put(_k_, _v_));
        this.tparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.tparams.forEach(_v_ -> this.tparams.add(_v_));
        this.cParams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.cParams.forEach(_v_ -> this.cParams.add(_v_));
        this.itemInfo = new java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean>();
		_o_.itemInfo.forEach(_v_ -> this.itemInfo.add(new LBItemInfoBean(_v_)));
        this.dropId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.dropId.forEach(_v_ -> this.dropId.add(_v_));
        this.sendTime = _o_.sendTime;
        this.delTime = _o_.delTime;
        this.emailSrl = _o_.emailSrl;
        this.items = new java.util.concurrent.CopyOnWriteArrayList<LBItem>();
		_o_.items.forEach(_v_ -> this.items.add(new LBItem(_v_)));
	}


	@JSONField(name = "1")
	public int getEmid(){
		return this.emid;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<String, String> getAparams(){
		return this.aparams;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<String> getTparams(){
		return this.tparams;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArrayList<String> getCParams(){
		return this.cParams;
	}

	@JSONField(name = "5")
	public java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean> getItemInfo(){
		return this.itemInfo;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getDropId(){
		return this.dropId;
	}

	@JSONField(name = "7")
	public long getSendTime(){
		return this.sendTime;
	}

	@JSONField(name = "8")
	public long getDelTime(){
		return this.delTime;
	}

	@JSONField(name = "9")
	public long getEmailSrl(){
		return this.emailSrl;
	}

	@JSONField(name = "10")
	public java.util.concurrent.CopyOnWriteArrayList<LBItem> getItems(){
		return this.items;
	}

	@JSONField(name = "1")
	public void setEmid(int _v_){
		this.emid = _v_;
	}

	@JSONField(name = "2")
	public void setAparams(java.util.concurrent.ConcurrentHashMap<String, String> _v_){
		this.aparams = _v_;
	}

	@JSONField(name = "3")
	public void setTparams(java.util.concurrent.CopyOnWriteArrayList<String> _v_){
		this.tparams = _v_;
	}

	@JSONField(name = "4")
	public void setCParams(java.util.concurrent.CopyOnWriteArrayList<String> _v_){
		this.cParams = _v_;
	}

	@JSONField(name = "5")
	public void setItemInfo(java.util.concurrent.CopyOnWriteArrayList<LBItemInfoBean> _v_){
		this.itemInfo = _v_;
	}

	@JSONField(name = "6")
	public void setDropId(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.dropId = _v_;
	}

	@JSONField(name = "7")
	public void setSendTime(long _v_){
		this.sendTime = _v_;
	}

	@JSONField(name = "8")
	public void setDelTime(long _v_){
		this.delTime = _v_;
	}

	@JSONField(name = "9")
	public void setEmailSrl(long _v_){
		this.emailSrl = _v_;
	}

	@JSONField(name = "10")
	public void setItems(java.util.concurrent.CopyOnWriteArrayList<LBItem> _v_){
		this.items = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof OfflineEmailInfo) {
			OfflineEmailInfo _o_ = (OfflineEmailInfo)_o1_;
			if (this.emid != _o_.emid) return false;
			if (!this.aparams.equals(_o_.aparams)) return false;
			if (!this.tparams.equals(_o_.tparams)) return false;
			if (!this.cParams.equals(_o_.cParams)) return false;
			if (!this.itemInfo.equals(_o_.itemInfo)) return false;
			if (!this.dropId.equals(_o_.dropId)) return false;
			if (this.sendTime != _o_.sendTime) return false;
			if (this.delTime != _o_.delTime) return false;
			if (this.emailSrl != _o_.emailSrl) return false;
			if (!this.items.equals(_o_.items)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.emid);
		_h_ += _h_ * 31 + this.aparams.hashCode();
		_h_ += _h_ * 31 + this.tparams.hashCode();
		_h_ += _h_ * 31 + this.cParams.hashCode();
		_h_ += _h_ * 31 + this.itemInfo.hashCode();
		_h_ += _h_ * 31 + this.dropId.hashCode();
		_h_ += _h_ * 31 + (int)(sendTime ^ (sendTime >>> 32));
		_h_ += _h_ * 31 + (int)(delTime ^ (delTime >>> 32));
		_h_ += _h_ * 31 + (int)(emailSrl ^ (emailSrl >>> 32));
		_h_ += _h_ * 31 + this.items.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(emid).append(",");
		_sb_.append(aparams).append(",");
		_sb_.append(tparams).append(",");
		_sb_.append(cParams).append(",");
		_sb_.append(itemInfo).append(",");
		_sb_.append(dropId).append(",");
		_sb_.append(sendTime).append(",");
		_sb_.append(delTime).append(",");
		_sb_.append(emailSrl).append(",");
		_sb_.append(items).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
