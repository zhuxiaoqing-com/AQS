package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerSupplyBox extends warrior.mdb.XBean {
    private int hbox;  // 拥有箱子数量
    private java.util.concurrent.CopyOnWriteArrayList<BaseItemBean> items;  // 权重随到的10个道具
    private int state;  // 当前箱子状态 0未打开  1未领取
    private int index;  // 抽中的箱子(baseItems的index下标)
    private long cdtime;  // 箱子倒计时,开始的时间

    PlayerSupplyBox(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.items = new java.util.concurrent.CopyOnWriteArrayList<BaseItemBean>();
	}

	public PlayerSupplyBox() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerSupplyBox(PlayerSupplyBox _o_) {
		this(_o_, null);
	}

	PlayerSupplyBox(PlayerSupplyBox _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.hbox = _o_.hbox;
		this.items = new java.util.concurrent.CopyOnWriteArrayList<BaseItemBean>();
		_o_.items.forEach(_v_ -> this.items.add(new BaseItemBean(_v_)));
		this.state = _o_.state;
		this.index = _o_.index;
		this.cdtime = _o_.cdtime;
	}

	public void copyFrom(PlayerSupplyBox _o_) {
        this.hbox = _o_.hbox;
        this.items = new java.util.concurrent.CopyOnWriteArrayList<BaseItemBean>();
		_o_.items.forEach(_v_ -> this.items.add(new BaseItemBean(_v_)));
        this.state = _o_.state;
        this.index = _o_.index;
        this.cdtime = _o_.cdtime;
	}


	@JSONField(name = "1")
	public int getHbox(){
		return this.hbox;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<BaseItemBean> getItems(){
		return this.items;
	}

	@JSONField(name = "3")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "4")
	public int getIndex(){
		return this.index;
	}

	@JSONField(name = "5")
	public long getCdtime(){
		return this.cdtime;
	}

	@JSONField(name = "1")
	public void setHbox(int _v_){
		this.hbox = _v_;
	}

	@JSONField(name = "2")
	public void setItems(java.util.concurrent.CopyOnWriteArrayList<BaseItemBean> _v_){
		this.items = _v_;
	}

	@JSONField(name = "3")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "4")
	public void setIndex(int _v_){
		this.index = _v_;
	}

	@JSONField(name = "5")
	public void setCdtime(long _v_){
		this.cdtime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerSupplyBox) {
			PlayerSupplyBox _o_ = (PlayerSupplyBox)_o1_;
			if (this.hbox != _o_.hbox) return false;
			if (!this.items.equals(_o_.items)) return false;
			if (this.state != _o_.state) return false;
			if (this.index != _o_.index) return false;
			if (this.cdtime != _o_.cdtime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.hbox);
		_h_ += _h_ * 31 + this.items.hashCode();
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(this.index);
		_h_ += _h_ * 31 + (int)(cdtime ^ (cdtime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(hbox).append(",");
		_sb_.append(items).append(",");
		_sb_.append(state).append(",");
		_sb_.append(index).append(",");
		_sb_.append(cdtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
