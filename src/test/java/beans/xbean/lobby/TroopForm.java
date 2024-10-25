package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class TroopForm extends warrior.mdb.XBean {
    private long id; 
    private String name; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> icons; 
    private String qq; 
    private long endTime;  // 结束时间
    private java.util.concurrent.CopyOnWriteArraySet<Long> partner;  // 响应玩家
    private int faction; 
    private long initiator;  // 发起人
    private long supior;  // 预关联的上级部队id
    private String outNotice;  // 对外公告

    TroopForm(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.icons = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.qq = "";
		this.partner = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.outNotice = "";
	}

	public TroopForm() {
		this((warrior.mdb.XBean)null);
	}

	public TroopForm(TroopForm _o_) {
		this(_o_, null);
	}

	TroopForm(TroopForm _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.name = _o_.name;
		this.icons = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icons.forEach((_k_, _v_) -> this.icons.put(_k_, _v_));
		this.qq = _o_.qq;
		this.endTime = _o_.endTime;
		this.partner = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.partner.forEach(_v_ -> this.partner.add(_v_));
		this.faction = _o_.faction;
		this.initiator = _o_.initiator;
		this.supior = _o_.supior;
		this.outNotice = _o_.outNotice;
	}

	public void copyFrom(TroopForm _o_) {
        this.id = _o_.id;
        this.name = _o_.name;
        this.icons = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icons.forEach((_k_, _v_) -> this.icons.put(_k_, _v_));
        this.qq = _o_.qq;
        this.endTime = _o_.endTime;
        this.partner = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.partner.forEach(_v_ -> this.partner.add(_v_));
        this.faction = _o_.faction;
        this.initiator = _o_.initiator;
        this.supior = _o_.supior;
        this.outNotice = _o_.outNotice;
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getIcons(){
		return this.icons;
	}

	@JSONField(name = "4")
	public String getQq(){
		return this.qq;
	}

	@JSONField(name = "5")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "6")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getPartner(){
		return this.partner;
	}

	@JSONField(name = "7")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "8")
	public long getInitiator(){
		return this.initiator;
	}

	@JSONField(name = "9")
	public long getSupior(){
		return this.supior;
	}

	@JSONField(name = "10")
	public String getOutNotice(){
		return this.outNotice;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "3")
	public void setIcons(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.icons = _v_;
	}

	@JSONField(name = "4")
	public void setQq(String _v_){
		this.qq = _v_;
	}

	@JSONField(name = "5")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "6")
	public void setPartner(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.partner = _v_;
	}

	@JSONField(name = "7")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "8")
	public void setInitiator(long _v_){
		this.initiator = _v_;
	}

	@JSONField(name = "9")
	public void setSupior(long _v_){
		this.supior = _v_;
	}

	@JSONField(name = "10")
	public void setOutNotice(String _v_){
		this.outNotice = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TroopForm) {
			TroopForm _o_ = (TroopForm)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (!this.icons.equals(_o_.icons)) return false;
			if (!this.qq.equals(_o_.qq)) return false;
			if (this.endTime != _o_.endTime) return false;
			if (!this.partner.equals(_o_.partner)) return false;
			if (this.faction != _o_.faction) return false;
			if (this.initiator != _o_.initiator) return false;
			if (this.supior != _o_.supior) return false;
			if (!this.outNotice.equals(_o_.outNotice)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + this.icons.hashCode();
		_h_ += _h_ * 31 + this.qq.hashCode();
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + this.partner.hashCode();
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(initiator ^ (initiator >>> 32));
		_h_ += _h_ * 31 + (int)(supior ^ (supior >>> 32));
		_h_ += _h_ * 31 + this.outNotice.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(icons).append(",");
		_sb_.append("T").append(qq.length()).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(partner).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(initiator).append(",");
		_sb_.append(supior).append(",");
		_sb_.append("T").append(outNotice.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
