package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class EmailInfo extends warrior.mdb.XBean {
    public static final int NO_READ = 0;   // 未阅读
    public static final int READED = 1;   // 已阅读
    public static final int NO_RECEIVE = 0;   // 未领取
    public static final int RECEIVE = 1;   // 已领取
    public static final int NO_DELETE = 0;   // 未删除
    public static final int DELETE = 1;   // 已删除
    public static final int EMAIL_PLAYER = 0;   // 个人邮件
    public static final int EMAIL_SERVER = 1;   // 全服邮件

    private long emailSrl;  // 邮件唯一id
    private int rstate;  // 邮件阅读状态
    private int istate;  // 道具领取状态
    private java.util.concurrent.CopyOnWriteArrayList<Item> items;  // 道具
    private java.util.concurrent.CopyOnWriteArrayList<ItemInfoBean> itemInfos;  // 资源
    private long dtime;  // 邮件删除时间
    private long stime;  // 邮件发送时间
    private int dstate;  // 邮件手动删除的状态,由于需要全服系统邮件领取比对,故不能删除整个邮件
    private int type;  // 邮件类型
    private java.util.concurrent.CopyOnWriteArrayList<String> tparams;  // 标题填充集合
    private java.util.concurrent.CopyOnWriteArrayList<String> cparsms;  // 内容填充集合
    private int emid;  // 邮件模板配置类型

    EmailInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.CopyOnWriteArrayList<Item>();
		this.itemInfos = new java.util.concurrent.CopyOnWriteArrayList<ItemInfoBean>();
		this.tparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		this.cparsms = new java.util.concurrent.CopyOnWriteArrayList<String>();
	}

	public EmailInfo() {
		this((warrior.mdb.XBean)null);
	}

	public EmailInfo(EmailInfo _o_) {
		this(_o_, null);
	}

	EmailInfo(EmailInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.emailSrl = _o_.emailSrl;
		this.rstate = _o_.rstate;
		this.istate = _o_.istate;
		this.items = new java.util.concurrent.CopyOnWriteArrayList<Item>();
		_o_.items.forEach(_v_ -> this.items.add(new Item(_v_)));
		this.itemInfos = new java.util.concurrent.CopyOnWriteArrayList<ItemInfoBean>();
		_o_.itemInfos.forEach(_v_ -> this.itemInfos.add(new ItemInfoBean(_v_)));
		this.dtime = _o_.dtime;
		this.stime = _o_.stime;
		this.dstate = _o_.dstate;
		this.type = _o_.type;
		this.tparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.tparams.forEach(_v_ -> this.tparams.add(_v_));
		this.cparsms = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.cparsms.forEach(_v_ -> this.cparsms.add(_v_));
		this.emid = _o_.emid;
	}

	public void copyFrom(EmailInfo _o_) {
        this.emailSrl = _o_.emailSrl;
        this.rstate = _o_.rstate;
        this.istate = _o_.istate;
        this.items = new java.util.concurrent.CopyOnWriteArrayList<Item>();
		_o_.items.forEach(_v_ -> this.items.add(new Item(_v_)));
        this.itemInfos = new java.util.concurrent.CopyOnWriteArrayList<ItemInfoBean>();
		_o_.itemInfos.forEach(_v_ -> this.itemInfos.add(new ItemInfoBean(_v_)));
        this.dtime = _o_.dtime;
        this.stime = _o_.stime;
        this.dstate = _o_.dstate;
        this.type = _o_.type;
        this.tparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.tparams.forEach(_v_ -> this.tparams.add(_v_));
        this.cparsms = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.cparsms.forEach(_v_ -> this.cparsms.add(_v_));
        this.emid = _o_.emid;
	}


	@JSONField(name = "1")
	public long getEmailSrl(){
		return this.emailSrl;
	}

	@JSONField(name = "3")
	public int getRstate(){
		return this.rstate;
	}

	@JSONField(name = "4")
	public int getIstate(){
		return this.istate;
	}

	@JSONField(name = "5")
	public java.util.concurrent.CopyOnWriteArrayList<Item> getItems(){
		return this.items;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArrayList<ItemInfoBean> getItemInfos(){
		return this.itemInfos;
	}

	@JSONField(name = "7")
	public long getDtime(){
		return this.dtime;
	}

	@JSONField(name = "8")
	public long getStime(){
		return this.stime;
	}

	@JSONField(name = "9")
	public int getDstate(){
		return this.dstate;
	}

	@JSONField(name = "10")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "11")
	public java.util.concurrent.CopyOnWriteArrayList<String> getTparams(){
		return this.tparams;
	}

	@JSONField(name = "12")
	public java.util.concurrent.CopyOnWriteArrayList<String> getCparsms(){
		return this.cparsms;
	}

	@JSONField(name = "13")
	public int getEmid(){
		return this.emid;
	}

	@JSONField(name = "1")
	public void setEmailSrl(long _v_){
		this.emailSrl = _v_;
	}

	@JSONField(name = "3")
	public void setRstate(int _v_){
		this.rstate = _v_;
	}

	@JSONField(name = "4")
	public void setIstate(int _v_){
		this.istate = _v_;
	}

	@JSONField(name = "5")
	public void setItems(java.util.concurrent.CopyOnWriteArrayList<Item> _v_){
		this.items = _v_;
	}

	@JSONField(name = "6")
	public void setItemInfos(java.util.concurrent.CopyOnWriteArrayList<ItemInfoBean> _v_){
		this.itemInfos = _v_;
	}

	@JSONField(name = "7")
	public void setDtime(long _v_){
		this.dtime = _v_;
	}

	@JSONField(name = "8")
	public void setStime(long _v_){
		this.stime = _v_;
	}

	@JSONField(name = "9")
	public void setDstate(int _v_){
		this.dstate = _v_;
	}

	@JSONField(name = "10")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "11")
	public void setTparams(java.util.concurrent.CopyOnWriteArrayList<String> _v_){
		this.tparams = _v_;
	}

	@JSONField(name = "12")
	public void setCparsms(java.util.concurrent.CopyOnWriteArrayList<String> _v_){
		this.cparsms = _v_;
	}

	@JSONField(name = "13")
	public void setEmid(int _v_){
		this.emid = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EmailInfo) {
			EmailInfo _o_ = (EmailInfo)_o1_;
			if (this.emailSrl != _o_.emailSrl) return false;
			if (this.rstate != _o_.rstate) return false;
			if (this.istate != _o_.istate) return false;
			if (!this.items.equals(_o_.items)) return false;
			if (!this.itemInfos.equals(_o_.itemInfos)) return false;
			if (this.dtime != _o_.dtime) return false;
			if (this.stime != _o_.stime) return false;
			if (this.dstate != _o_.dstate) return false;
			if (this.type != _o_.type) return false;
			if (!this.tparams.equals(_o_.tparams)) return false;
			if (!this.cparsms.equals(_o_.cparsms)) return false;
			if (this.emid != _o_.emid) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(emailSrl ^ (emailSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.rstate);
		_h_ += _h_ * 31 + (int)(this.istate);
		_h_ += _h_ * 31 + this.items.hashCode();
		_h_ += _h_ * 31 + this.itemInfos.hashCode();
		_h_ += _h_ * 31 + (int)(dtime ^ (dtime >>> 32));
		_h_ += _h_ * 31 + (int)(stime ^ (stime >>> 32));
		_h_ += _h_ * 31 + (int)(this.dstate);
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + this.tparams.hashCode();
		_h_ += _h_ * 31 + this.cparsms.hashCode();
		_h_ += _h_ * 31 + (int)(this.emid);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(emailSrl).append(",");
		_sb_.append(rstate).append(",");
		_sb_.append(istate).append(",");
		_sb_.append(items).append(",");
		_sb_.append(itemInfos).append(",");
		_sb_.append(dtime).append(",");
		_sb_.append(stime).append(",");
		_sb_.append(dstate).append(",");
		_sb_.append(type).append(",");
		_sb_.append(tparams).append(",");
		_sb_.append(cparsms).append(",");
		_sb_.append(emid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
